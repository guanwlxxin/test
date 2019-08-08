 package com.shopping.manage.timer;
 
 import com.shopping.core.security.support.SecurityUserHolder;
 import com.shopping.core.tools.CommUtil;
 import com.shopping.foundation.domain.Activity;
 import com.shopping.foundation.domain.ActivityGoods;
 import com.shopping.foundation.domain.DeliveryGoods;
 import com.shopping.foundation.domain.Evaluate;
 import com.shopping.foundation.domain.Goods;
 import com.shopping.foundation.domain.Group;
 import com.shopping.foundation.domain.GroupGoods;
 import com.shopping.foundation.domain.MobileVerifyCode;
 import com.shopping.foundation.domain.OrderForm;
 import com.shopping.foundation.domain.OrderFormLog;
 import com.shopping.foundation.domain.Payment;
 import com.shopping.foundation.domain.PredepositLog;
 import com.shopping.foundation.domain.Store;
 import com.shopping.foundation.domain.StoreClass;
 import com.shopping.foundation.domain.StorePoint;
 import com.shopping.foundation.domain.StoreStat;
 import com.shopping.foundation.domain.SysConfig;
 import com.shopping.foundation.domain.User;
 import com.shopping.foundation.service.IActivityGoodsService;
 import com.shopping.foundation.service.IActivityService;
 import com.shopping.foundation.service.IDeliveryGoodsService;
 import com.shopping.foundation.service.IEvaluateService;
import com.shopping.foundation.service.IFavoriteService;
import com.shopping.foundation.service.IGoodsCartService;
import com.shopping.foundation.service.IGoodsService;
 import com.shopping.foundation.service.IGroupGoodsService;
 import com.shopping.foundation.service.IGroupService;
 import com.shopping.foundation.service.IMobileVerifyCodeService;
 import com.shopping.foundation.service.IOrderFormLogService;
 import com.shopping.foundation.service.IOrderFormService;
 import com.shopping.foundation.service.IPaymentService;
 import com.shopping.foundation.service.IPredepositLogService;
 import com.shopping.foundation.service.IStoreClassService;
 import com.shopping.foundation.service.IStorePointService;
 import com.shopping.foundation.service.IStoreService;
 import com.shopping.foundation.service.IStoreStatService;
 import com.shopping.foundation.service.ISysConfigService;
 import com.shopping.foundation.service.ITemplateService;
import com.shopping.foundation.service.IUserAppBrowseLogService;
import com.shopping.foundation.service.IUserH5webBrowseLogService;
import com.shopping.foundation.service.IUserPcwebBrowseLogService;
import com.shopping.foundation.service.IUserSearchLogService;
import com.shopping.foundation.service.IUserService;
 import com.shopping.manage.admin.tools.MsgTools;
 import com.shopping.manage.admin.tools.StatTools;
 import java.io.File;
 import java.io.FileOutputStream;
 import java.io.OutputStreamWriter;
 import java.io.PrintWriter;
 import java.io.StringWriter;
 import java.math.BigDecimal;
 import java.text.DecimalFormat;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Map;
 import java.util.Properties;
 import org.apache.velocity.VelocityContext;
 import org.apache.velocity.app.Velocity;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
 
 @Component("shop_UserBehavior")
 public class UserBehaviorManageAction
 {  
	@Autowired
    private IUserAppBrowseLogService userAppBrowseLogService;
	
	@Autowired
    private IUserH5webBrowseLogService  userH5webBrowseLogService;
	
	@Autowired
	private IUserPcwebBrowseLogService userPcwebBrowseLogService;
	
	@Autowired
	private IEvaluateService evaluateService; //评价表
	
	@Autowired
	private IGoodsCartService goodsCartService; //购物车表
	
	@Autowired
	private IFavoriteService favoriteService; //收藏表
	
	@Autowired
	private IOrderFormService orderFormService;//订单表
	
	@Autowired
	private IUserSearchLogService userSearchLogService;
	
	
   private void execute()
     throws Exception
   {   
		//获取3个日志表的商品ID
//	   List appLog = this.userAppBrowseLogService.query("", null, -1, -1);
//	   
//	   List h5Log = this.userH5webBrowseLogService.query("", null, -1, -1);
//	   
//	   List webLog = this.userPcwebBrowseLogService.query("", null, -1, -1);
	   
	 //收藏车表商品ID
	   
	 //购物车表商品ID
	 //评价表--》条件待定
		
	 //订单表商品ID
	 //搜索日志的标签
	   
	   System.out.println("行为更新："+new Date().toString());
   }

 }


 
 
 