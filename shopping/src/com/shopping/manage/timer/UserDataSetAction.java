 package com.shopping.manage.timer;
 
 import com.alibaba.fastjson.JSONObject;
import com.shopping.core.domain.virtual.SysMap;
import com.shopping.foundation.domain.Favorite;
import com.shopping.foundation.domain.Goods;
import com.shopping.foundation.domain.GoodsCart;
import com.shopping.foundation.domain.OrderForm;
import com.shopping.foundation.domain.StoreCart;
import com.shopping.foundation.domain.TestModel;
import com.shopping.foundation.domain.User;
import com.shopping.foundation.domain.UserAppBrowseLog;
import com.shopping.foundation.domain.UserBehavior;
import com.shopping.foundation.domain.UserH5webBrowseLog;
import com.shopping.foundation.domain.UserPcwebBrowseLog;
import com.shopping.foundation.service.IEvaluateService;
import com.shopping.foundation.service.IFavoriteService;
import com.shopping.foundation.service.IGoodsCartService;
import com.shopping.foundation.service.IGoodsService;
import com.shopping.foundation.service.IOrderFormService;
import com.shopping.foundation.service.IStoreCartService;
import com.shopping.foundation.service.IUserAppBrowseLogService;
import com.shopping.foundation.service.IUserBehaviorService;
import com.shopping.foundation.service.IUserH5webBrowseLogService;
import com.shopping.foundation.service.IUserPcwebBrowseLogService;
import com.shopping.foundation.service.IUserSearchLogService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
 import java.util.HashMap;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.PersistenceUnit;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.ClassUtils;
 
/*
 * @Descrpit:初始化商品数据集
 * @Author:wlx
 * 
 * */
 @Component("UserDataSet")
 public class UserDataSetAction
 {
	    @PersistenceUnit
	     private EntityManagerFactory emFactory;
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
  
		@Autowired
		private IUserBehaviorService userBehaviorService;
		
		@Autowired
		private IStoreCartService storeCartService;
 
   private void execute()
    
   {   
	 //在线程中添加数据库Session
		boolean participate = false;
	    if (TransactionSynchronizationManager.hasResource(emFactory)) {
			participate = true;
		}else{
			try {
				EntityManager em = emFactory.createEntityManager();
				EntityManagerHolder emHolder = new EntityManagerHolder(em);
				TransactionSynchronizationManager.bindResource(emFactory, emHolder);
			}
			catch (PersistenceException ex) {
				throw new DataAccessResourceFailureException("Could not create JPA EntityManager at Thread", ex);
			}
		}

	   try {
	   Map<String,String> map = new HashMap<String, String>();
	   String tempComVal = "";
			//获取3个日志表的商品ID
		   List<UserAppBrowseLog> appLog = this.userAppBrowseLogService.query("select obj from UserAppBrowseLog obj", null, -1, -1);
		   boolean appLogFlag = false;
		   for(UserAppBrowseLog ua :appLog) {
			   if(map.containsKey(ua.getUserId())) {
				   tempComVal = map.get(ua.getUserId());
				   for(int i=0;i<tempComVal.split(",").length;i++) {
					  if(tempComVal.split(",")[i].equals(ua.getCommodityId())) {
						  appLogFlag = true;
					  } 
				   }
				   if(!appLogFlag) {
					   tempComVal =tempComVal+","+  ua.getCommodityId();
					   map.put(ua.getUserId(), tempComVal); 
				   }
				   appLogFlag = false;
			   }else {
				   map.put(ua.getUserId(), ua.getCommodityId());  
			   }
			   
		   }
		   List<UserH5webBrowseLog> h5Log = this.userH5webBrowseLogService.query("select obj from UserH5webBrowseLog obj", null, -1, -1);
		   boolean h5LogFlag = false;
		   for(UserH5webBrowseLog ua :h5Log) {
			   if(map.containsKey(ua.getUserId())) {
				   tempComVal = map.get(ua.getUserId());
				   for(int i=0;i<tempComVal.split(",").length;i++) {
					  if(tempComVal.split(",")[i].equals(ua.getCommodityId())) {
						  h5LogFlag = true;
					  } 
				   }
				   if(!h5LogFlag) {
					   tempComVal =tempComVal+","+  ua.getCommodityId();
					   map.put(ua.getUserId(), tempComVal); 
				   }
				   h5LogFlag = false;
			   }else {
				   map.put(ua.getUserId(), ua.getCommodityId());  
			   }
			   
		   }
		   List<UserPcwebBrowseLog> webLog = this.userPcwebBrowseLogService.query("select obj from UserPcwebBrowseLog obj", null, -1, -1);
		   boolean webLogFlag = false;
		   for(UserPcwebBrowseLog ua :webLog) {
			   if(map.containsKey(ua.getUserId())) {
				   tempComVal = map.get(ua.getUserId());
				   for(int i=0;i<tempComVal.split(",").length;i++) {
					  if(tempComVal.split(",")[i].equals(ua.getCommodityId())) {
						  webLogFlag = true;
					  } 
				   }
				   if(!webLogFlag) {
					   tempComVal =tempComVal+","+  ua.getCommodityId();
					   map.put(ua.getUserId(), tempComVal); 
				   }
				   webLogFlag = false;
			   }else {
				   map.put(ua.getUserId(), ua.getCommodityId());  
			   }
			   
		   }
		   for(Map.Entry<String,String> m :map.entrySet()) {
			   UserBehavior ub = new UserBehavior();
			   ub.setUserId(m.getKey());
			   ub.setBehaviorLabel(m.getValue());
			   ub.setDeleteStatus(false);
			   ub.setAddTime(new Date());
			   ub.setBehaviorType("浏览");
			   ub.setBehaviorTime(new Date());
			   this.userBehaviorService.save(ub);
		   }
		 //收藏车表商品ID
	      Map<String,String> mapFavorite = new HashMap<String, String>();
	      Map mq = new HashMap();
	      mq.put("type",  Integer.valueOf(0));
	      //new SysMap("type", Integer.valueOf(1))
	      List<Favorite>  favoriteList = this.favoriteService.query("select obj from Favorite obj where obj.type=:type ", mq, -1, -1);
	      boolean favoriteFlag = false;
		  for(Favorite ua :favoriteList) {
		   if(mapFavorite.containsKey(ua.getUserId())) {
			   tempComVal = mapFavorite.get(ua.getUserId());
			   for(int i=0;i<tempComVal.split(",").length;i++) {
				  if(tempComVal.split(",")[i].equals(ua.getGoodsId())) {
					  favoriteFlag = true;
				  } 
			   }
			   if(!favoriteFlag) {
				   tempComVal =tempComVal+","+  ua.getGoodsId();
				   mapFavorite.put(ua.getUserId(), tempComVal); 
			   }
			   favoriteFlag = false;
		   }else {
			   mapFavorite.put(ua.getUserId(), ua.getGoodsId());  
		   }
		   
	      }
		  for(Map.Entry<String,String> m :mapFavorite.entrySet()) {
		   UserBehavior ub = new UserBehavior();
		   ub.setUserId(m.getKey());
		   ub.setBehaviorLabel(m.getValue());
		   ub.setDeleteStatus(false);
		   ub.setAddTime(new Date());
		   ub.setBehaviorType("收藏");
		   ub.setBehaviorTime(new Date());
		   this.userBehaviorService.save(ub);
	      }
		 //购物车表商品ID
		 Map<String,String> mapCart = new HashMap<String, String>();
		 Map params = new HashMap();
         params.put("sc_status", Integer.valueOf(0));
         List<StoreCart> cartList = this.storeCartService .query("select obj from StoreCart obj where obj.user.id is not null and obj.sc_status=:sc_status", 
		           params, -1, -1);
		 boolean cartFlag = false;
		 for(StoreCart sc:cartList) {
			 for (GoodsCart gc : ((StoreCart)sc).getGcs()) {
				
				   if(mapCart.containsKey(sc.getUserId())) {
				   tempComVal = mapCart.get(sc.getUserId());
				   for(int i=0;i<tempComVal.split(",").length;i++) {
					  if(tempComVal.split(",")[i].equals(gc.getGoodsId())) {
						  cartFlag = true;
					  } 
				   }
				   if(!cartFlag) {
					   tempComVal =tempComVal+","+ gc.getGoodsId();
					   mapCart.put(sc.getUserId(), tempComVal); 
				   }
				   cartFlag = false;
			   }else {
				   mapCart.put(sc.getUserId(), gc.getGoodsId());  
			   }
	      }
		 }
		   for(Map.Entry<String,String> m :mapCart.entrySet()) {
		   UserBehavior ub = new UserBehavior();
		   ub.setUserId(m.getKey());
		   ub.setBehaviorLabel(m.getValue());
		   ub.setDeleteStatus(false);
		   ub.setAddTime(new Date());
		   ub.setBehaviorType("添加购物车");
		   ub.setBehaviorTime(new Date());
		   this.userBehaviorService.save(ub);
	   }
		 //评价表--》条件待定
			
		 //订单表商品ID
		Map paramsOf = new HashMap();
		paramsOf.put("order_status", Integer.valueOf(20));
		paramsOf.put("order_status1", Integer.valueOf(30));
		paramsOf.put("order_status2", Integer.valueOf(40));
		List<OrderForm> ofList =  this.orderFormService.query("select obj from OrderForm obj where "
				+" obj.order_status =:order_status or obj.order_status =:order_status1 or obj.order_status =:order_status2",
				paramsOf, -1, -1);
		boolean ofFlag = false;
		Map<String,String> mapOf = new HashMap<String, String>();
		   for(OrderForm of:ofList) {
				 for (GoodsCart gc : of.getGcs()) {
					
					   if(mapOf.containsKey(of.getUserId())) {
					   tempComVal = mapOf.get(of.getUserId());
					   for(int i=0;i<tempComVal.split(",").length;i++) {
						  if(tempComVal.split(",")[i].equals(gc.getGoodsId())) {
							  ofFlag = true;
						  } 
					   }
					   if(!ofFlag) {
						   tempComVal =tempComVal+","+ gc.getGoodsId();
						   mapOf.put(of.getUserId(), tempComVal); 
					   }
					   ofFlag = false;
				   }else {
					   mapOf.put(of.getUserId(), gc.getGoodsId());  
				   }
		      }	   
		   }
		   for(Map.Entry<String,String> m :mapOf.entrySet()) {
			   UserBehavior ub = new UserBehavior();
			   ub.setUserId(m.getKey());
			   ub.setBehaviorLabel(m.getValue());
			   ub.setDeleteStatus(false);
			   ub.setAddTime(new Date());
			   ub.setBehaviorType("购买");
			   ub.setBehaviorTime(new Date());
			   this.userBehaviorService.save(ub);
	      }
		 //搜索日志的标签
		 
	     //用户数据集添加
	   String tempUserVal = "";
	   List<UserBehavior> listUB = this.userBehaviorService.query("select obj from UserBehavior obj", null, -1, -1);
	   Map<String,String> mapTemp = new HashMap<String, String>();
	   for(UserBehavior ub :listUB) {
		   if(mapTemp.containsKey(ub.getUserId())) {
			   tempUserVal= removalDuplicate(ub.getBehaviorLabel().split(","),mapTemp.get(ub.getUserId()).split(","));
			   mapTemp.put(ub.getUserId(), tempUserVal);   
		   }else {
			   
			   mapTemp.put(ub.getUserId(), ub.getBehaviorLabel());   
		   }
		   
	   }
	   //初始化用户数据集
	   StringBuilder sb = new StringBuilder();
	   sb.append("{");
	   for (Map.Entry<String, String> entry : mapTemp.entrySet()) { 
		   sb.append("'"+entry.getKey()+"':{");
		   tempUserVal =  entry.getValue();
		   for(String tt:tempUserVal.split(",")) {
			   sb.append("'"+tt+"':1,");
		   }
			sb.deleteCharAt(sb.length()-1);
			sb.append("},");
		 }
	   sb.deleteCharAt(sb.length()-1);
	   sb.append("}");
	   JSONObject json  = JSONObject.parseObject(sb.toString());
	   List<TestModel> listItem = new ArrayList<TestModel>();
	   Map<String,List<TestModel>> mapF = new HashMap<String, List<TestModel>>();
	   StringBuilder sbUser = new StringBuilder();
	   sbUser.append("{");
	   for (Map.Entry<String, String> entry : mapTemp.entrySet()) { 
		   JSONObject json2  = JSONObject.parseObject(json.get(entry.getKey()).toString());
		   Set<String> set = json2.keySet();
		   listItem =   sim_cos(json,entry.getKey(),set);
		   sbUser.append("'"+entry.getKey()+"':{");
		   sbUser.append("'user':{");
		   for(TestModel tm :listItem) {
			   sbUser.append(tm.toString()+",");
		   		}
		   sbUser.deleteCharAt(sbUser.length()-1);
		   sbUser.append("},");
		   sbUser.append("'good':'"+mapTemp.get(entry.getKey())+"'},");
		}
	   sbUser.deleteCharAt(sbUser.length()-1);
	   sbUser.append("}");
	   String path =ClassUtils.getDefaultClassLoader().getResource("").getPath().replace("WEB-INF/classes/", "")+"resources/";
	   FileWriter fw;
	    // ServletContext.getRealPath("/")+ "goods.txt";
        File file=new File(path+"/users.txt");
       	 fw = new FileWriter(file);
       	 BufferedWriter bw = new BufferedWriter(fw);
       	// for(int i=0;i<500000;i++){
       		 bw.write(sbUser.toString());
       	//	 }
       	 bw.flush();
       	 bw.close(); 
	   if(!participate){
				EntityManagerHolder emHolder = (EntityManagerHolder)
				TransactionSynchronizationManager.unbindResource(emFactory);		 
		        EntityManagerFactoryUtils.closeEntityManager(emHolder.getEntityManager());
			}

	   } catch (Exception e) {
		System.out.println(e.getMessage());
	   }
	   
   }
   
	 public  String removalDuplicate(String[] a,String[] b) {
		   Map<String,String> map = new HashMap<String,String>();
	       for (int i = 0; i < a.length; i++) {
	           map.put(a[i].trim(),a[i].trim());
	       }
	       for (int i = 0; i < b.length; i++) {
	           map.put(b[i].trim(),b[i].trim());
	       }
	       Collection<String> values = map.values();
	       Iterator<String> iterator = values.iterator();
	       String c [] = new String[values.size()];
	       int m = 0;
	       while (iterator.hasNext()){
	           c[m++] = iterator.next();
	       }
	       String re = Arrays.toString(c);
	       re = re.substring(1, re.length()-1).replaceAll(" ", "");
	       return  re;
	   }
	//余弦定理评价  0为不一样 ，  1为完全相同
		public static List<TestModel> sim_cos(JSONObject json,String person1,Set<String> personset) {
			List<TestModel> list = new ArrayList<TestModel>();
			double sumT = 0.0;
			double sumSq = 0.0;
			double sumSq1 = 0.0;
			Set<String> set = json.keySet(); 
	   	   for(String str : set) {
	   		   if(!str.equals(person1)) {
	   			JSONObject json1  = JSONObject.parseObject(json.get(person1).toString());
				JSONObject json2  = JSONObject.parseObject(json.get(str).toString());
				for(String str1 :personset) {
					if(json2.containsKey(str1)) {
						sumT+= Double.parseDouble(json1.get(str1).toString())*Double.parseDouble(json2.get(str1).toString());
						sumSq+=	Math.pow(Double.parseDouble(json1.get(str1).toString()),2);
						sumSq1+=	Math.pow(Double.parseDouble(json2.get(str1).toString()),2);
					}else {
						sumT+= Double.parseDouble(json1.get(str1).toString())*Double.parseDouble("0");
						sumSq+=	Math.pow(Double.parseDouble(json1.get(str1).toString()),2);
						sumSq1+=	Math.pow(Double.parseDouble("0"),2);
					}
					
				}
				list.add(new TestModel(str, sumT/(Math.sqrt(sumSq)*Math.sqrt(sumSq1))));;
	   		   }
	   	   }
	   	 Collections.sort(list,new Comparator<TestModel>(){
				public int compare(TestModel o1, TestModel o2) {
					  if(o1.getVal() >= o2.getVal()){
				             return -1;
				        }
					        return 1;
				}
	         });
	   
	   	return list;	
		}
//   //计算两个商品之间的相似程度
//   public void double_distance() {
//	   
//	   double x1 = 0;
//	   double x2 = 0;
//	   double y1 = 0;
//	   double y2 = 0;
//	   double z1 = 0;
//	   double z2 = 0;
//	   
//	   double rs =1/Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2)+Math.pow(z1-z2,2)) ;
//   }
//	//余弦定理评价  0为不一样 ，  1为完全相同
//		public static double sim_cos(JSONObject json,String person1,String person2,String[] aS) {
//			double sumT = 0.0;
//			double sumSq = 0.0;
//			double sumSq1 = 0.0;
//			if(json.get(person1)!=null&&json.get(person2)!=null) {
//				JSONObject json1  = JSONObject.parseObject(json.get(person1).toString());
//				JSONObject json2  = JSONObject.parseObject(json.get(person2).toString());
//				for (int i=0;i<aS.length;i++) {
//					if(json2.containsKey(aS[i])) {
//						sumT+= Double.parseDouble(json1.get(aS[i]).toString())*Double.parseDouble(json2.get(aS[i]).toString());
//						sumSq+=	Math.pow(Double.parseDouble(json1.get(aS[i]).toString()),2);
//						sumSq1+=	Math.pow(Double.parseDouble(json2.get(aS[i]).toString()),2);
//						
//					}else {
//						sumT+= Double.parseDouble(json1.get(aS[i]).toString())*Double.parseDouble("0");
//						sumSq+=	Math.pow(Double.parseDouble(json1.get(aS[i]).toString()),2);
//						sumSq1+=	Math.pow(Double.parseDouble("0"),2);
//					}
//				}
//			   if(sumSq==0.0||sumSq1==0.0) {
//				   return 0.0;
//			   }
//				return sumT/(Math.sqrt(sumSq)*Math.sqrt(sumSq1));
//			}else {
//				
//				return 0;
//			}	
//		}
	
 }


 
 
 