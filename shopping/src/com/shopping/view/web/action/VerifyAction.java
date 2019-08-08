 package com.shopping.view.web.action;
 
 import com.shopping.core.tools.CommUtil;
import com.shopping.foundation.domain.MobileVerifyCode;
import com.shopping.foundation.service.IGroupService;
import com.shopping.foundation.service.IMobileVerifyCodeService;
import com.shopping.foundation.service.IStoreService;
import com.shopping.foundation.service.ISysConfigService;
import com.shopping.foundation.service.IUserService;
import com.shopping.manage.admin.tools.MsgTools;
import com.shopping.manage.admin.tools.SmsUtil;

import java.awt.Color;
 import java.awt.Font;
 import java.awt.Graphics;
 import java.awt.image.BufferedImage;
 import java.io.IOException;
 import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import java.util.Random;
 import javax.imageio.ImageIO;
 import javax.servlet.ServletOutputStream;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import javax.servlet.http.HttpSession;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 
 @Controller
 public class VerifyAction
 {
 
   @Autowired
   private IUserService userService;
 
   @Autowired
   private IStoreService storeService;
 
   @Autowired
   private IGroupService groupService;
   @Autowired
   private IMobileVerifyCodeService mobileverifycodeService;
   @Autowired
   private ISysConfigService configService;

   @Autowired
   private MsgTools msgTools;
   @Autowired
   private SmsUtil smsUtil;
   
   @RequestMapping({"/verify_code.htm"})
   public void validate_code(HttpServletRequest request, HttpServletResponse response, String code, String code_name)
   {
     HttpSession session = request.getSession(false);
     String verify_code = "";
     if (CommUtil.null2String(code_name).equals(""))
       verify_code = (String)session.getAttribute("verify_code");
     else {
       verify_code = (String)session.getAttribute(code_name);
     }
     boolean ret = true;
     if ((verify_code != null) && (!verify_code.equals("")) && 
       (!CommUtil.null2String(code.toUpperCase()).equals(verify_code))) {
       ret = false;
     }
 
     response.setContentType("text/plain");
     response.setHeader("Cache-Control", "no-cache");
     response.setCharacterEncoding("UTF-8");
     try
     {
       PrintWriter writer = response.getWriter();
       writer.print(ret);
     }
     catch (IOException e) {
       e.printStackTrace();
     }
   }
 
   @RequestMapping({"/verify_username.htm"})
   public void verify_username(HttpServletRequest request, HttpServletResponse response, String userName, String id)
   {
     boolean ret = true;
     Map params = new HashMap();
     params.put("userName", userName);
     params.put("id", CommUtil.null2Long(id));
     List users = this.userService
       .query("select obj from User obj where obj.userName=:userName and obj.id!=:id", 
       params, -1, -1);
     if ((users != null) && (users.size() > 0)) {
       ret = false;
     }
     response.setContentType("text/plain");
     response.setHeader("Cache-Control", "no-cache");
     response.setCharacterEncoding("UTF-8");
     try
     {
       PrintWriter writer = response.getWriter();
       writer.print(ret);
     }
     catch (IOException e) {
       e.printStackTrace();
     }
   }
 
   @RequestMapping({"/verify_email.htm"})
   public void verify_email(HttpServletRequest request, HttpServletResponse response, String email, String id)
   {
     boolean ret = true;
     Map params = new HashMap();
     params.put("email", email);
     params.put("id", CommUtil.null2Long(id));
     List users = this.userService
       .query("select obj from User obj where obj.email=:email and obj.id!=:id", 
       params, -1, -1);
     if ((users != null) && (users.size() > 0)) {
       ret = false;
     }
     response.setContentType("text/plain");
     response.setHeader("Cache-Control", "no-cache");
     response.setCharacterEncoding("UTF-8");
     try
     {
       PrintWriter writer = response.getWriter();
       writer.print(ret);
     }
     catch (IOException e) {
       e.printStackTrace();
     }
   }
 
   @RequestMapping({"/verify_storename.htm"})
   public void verify_storename(HttpServletRequest request, HttpServletResponse response, String store_name, String id)
   {
     boolean ret = true;
     Map params = new HashMap();
     params.put("store_name", store_name);
     params.put("id", CommUtil.null2Long(id));
     List users = this.storeService
       .query("select obj from Store obj where obj.store_name=:store_name and obj.id!=:id", 
       params, -1, -1);
     if ((users != null) && (users.size() > 0)) {
       ret = false;
     }
     response.setContentType("text/plain");
     response.setHeader("Cache-Control", "no-cache");
     response.setCharacterEncoding("UTF-8");
     try
     {
       PrintWriter writer = response.getWriter();
       writer.print(ret);
     }
     catch (IOException e) {
       e.printStackTrace();
     }
   }
 
   @RequestMapping({"/verify_mobile.htm"})
   public void verify_mobile(HttpServletRequest request, HttpServletResponse response, String mobile, String id)
   {
     boolean ret = true;
     Map params = new HashMap();
     params.put("mobile", mobile);
     params.put("id", CommUtil.null2Long(id));
     List users = this.userService
       .query("select obj from User obj where obj.mobile=:mobile and obj.id!=:id", 
       params, -1, -1);
     if ((users != null) && (users.size() > 0)) {
       ret = false;
     }
     response.setContentType("text/plain");
     response.setHeader("Cache-Control", "no-cache");
     response.setCharacterEncoding("UTF-8");
     try
     {
       PrintWriter writer = response.getWriter();
       writer.print(ret);
     }
     catch (IOException e) {
       e.printStackTrace();
     }
   }
   /*
    * @Descript:手机号验证码发送
    * @Author:wlx
    * 
    * */
   @RequestMapping({"/send_code.htm"})
   public void send_code(HttpServletRequest request, HttpServletResponse response, String type, String mobile) throws UnsupportedEncodingException
   {
	   String ret = "100";
	     if (type.equals("mobile_vetify_code")) {
	    //   String code = CommUtil.randomString(4).toUpperCase();
	       int code = (int)((Math.random()*9+1)*100000);
//	       String content = "尊敬的" + 
//	         SecurityUserHolder.getCurrentUser().getUserName() + 
//	         "您好，您在试图修改" + 
//	         this.configService.getSysConfig().getWebsiteName() + 
	       String content =    "手机验证码为：" + Integer.toString(code) + "。[" + 
	         this.configService.getSysConfig().getTitle() + "]";
	       if (this.configService.getSysConfig().isSmsEnbale()) {
	         boolean ret1 =true;//this.smsUtil.SendMessage(mobile, content);//  this.msgTools.sendSMS(mobile, content);
	         if (ret1) {
	           MobileVerifyCode mvc = this.mobileverifycodeService
	             .getObjByProperty("mobile", mobile);
	           if (mvc == null) {
	             mvc = new MobileVerifyCode();
	           }
	           mvc.setAddTime(new Date());
	           mvc.setCode(Integer.toString(code));
	           mvc.setMobile(mobile);
	           this.mobileverifycodeService.update(mvc);
	         } else {
	           ret = "200";
	         }
	       } else {
	         ret = "300";
	       }
	       response.setContentType("text/plain");
	       response.setHeader("Cache-Control", "no-cache");
	       response.setCharacterEncoding("UTF-8");
	       try
	       {
	         PrintWriter writer = response.getWriter();
	         writer.print(ret);
	       }
	       catch (IOException e) {
	         e.printStackTrace();
	       }
	     }
   }
   /*
    * @Descript:手机验证码验证
    * @Author:wlx
    * 
    * */
   @RequestMapping({"/verify_sms_code.htm"})
   public void verify_sms_code(HttpServletRequest request, HttpServletResponse response, String code, String mobile) throws UnsupportedEncodingException
   { 
	   boolean ret = true;
	   MobileVerifyCode mvc = this.mobileverifycodeService.getObjByProperty(
		       "mobile", mobile);
		     if ((mvc != null) && (mvc.getCode().equalsIgnoreCase(code))) {
		      
		     }else {
		    	 ret = false;
		     }
	   response.setContentType("text/plain");
	   response.setHeader("Cache-Control", "no-cache");
	   response.setCharacterEncoding("UTF-8");
	   try
	   {
	     PrintWriter writer = response.getWriter();
	     writer.print(ret);
	   }
	   catch (IOException e) {
	     e.printStackTrace();
	   }
	  
   }
   /*
    * @Descript:手机号验证
    * @Author:wlx
    * 
    * */
   @RequestMapping({"/verify_sms_mobile.htm"})
   public void verify_sms_mobile(HttpServletRequest request, HttpServletResponse response, String mobile)
   {
     boolean ret = true;
     Map params = new HashMap();
     params.put("mobile", mobile);
   
     List users = this.userService
       .query("select obj from User obj where obj.mobile=:mobile ", 
       params, -1, -1);
     if ((users != null) && (users.size() > 0)) {
       ret = false;
     }
     response.setContentType("text/plain");
     response.setHeader("Cache-Control", "no-cache");
     response.setCharacterEncoding("UTF-8");
     try
     {
       PrintWriter writer = response.getWriter();
       writer.print(ret);
     }
     catch (IOException e) {
       e.printStackTrace();
     }
   }
   @RequestMapping({"/verify.htm"})
   public void verify(HttpServletRequest request, HttpServletResponse response, String name)
     throws IOException
   {
     response.setContentType("image/jpeg");
     response.setHeader("Pragma", "No-cache");
     response.setHeader("Cache-Control", "no-cache");
     response.setDateHeader("Expires", 0L);
     HttpSession session = request.getSession(false);
 
     int width = 73; int height = 27;
     BufferedImage image = new BufferedImage(width, height, 
       1);
 
     Graphics g = image.getGraphics();
 
     Random random = new Random();
 
     g.setColor(getRandColor(200, 250));
     g.fillRect(0, 0, width, height);
 
     g.setFont(new Font("Times New Roman", 0, 24));
 
     g.setColor(getRandColor(160, 200));
     for (int i = 0; i < 155; i++) {
       int x = random.nextInt(width);
       int y = random.nextInt(height);
       int xl = random.nextInt(12);
       int yl = random.nextInt(12);
       g.drawLine(x, y, x + xl, y + yl);
     }
 
     String sRand = "";
     for (int i = 0; i < 4; i++) {
       String rand = CommUtil.randomInt(1).toUpperCase();
       sRand = sRand + rand;
 
       g.setColor(
         new Color(20 + random.nextInt(110), 20 + random
         .nextInt(110), 20 + random.nextInt(110)));
       g.drawString(rand, 13 * i + 6, 24);
     }
 
     if (CommUtil.null2String(name).equals(""))
       session.setAttribute("verify_code", sRand);
     else {
       session.setAttribute(name, sRand);
     }
 
     g.dispose();
     ServletOutputStream responseOutputStream = response.getOutputStream();
 
     ImageIO.write(image, "JPEG", responseOutputStream);
 
     responseOutputStream.flush();
     responseOutputStream.close();
   }
 
   private Color getRandColor(int fc, int bc) {
     Random random = new Random();
     if (fc > 255)
       fc = 255;
     if (bc > 255)
       bc = 255;
     int r = fc + random.nextInt(bc - fc);
     int g = fc + random.nextInt(bc - fc);
     int b = fc + random.nextInt(bc - fc);
     return new Color(r, g, b);
   }
 }


 
 
 