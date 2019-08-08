 package com.shopping.manage.timer;
 
 import com.alibaba.fastjson.JSONObject;
import com.shopping.foundation.domain.Goods;
import com.shopping.foundation.domain.TestModel;
import com.shopping.foundation.service.IGoodsService;
 import com.shopping.foundation.service.IUserBehaviorService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
 import java.util.HashMap;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
 
/*
 * @Descrpit:初始化商品数据集
 * @Author:wlx
 * 
 * */
 @Component("GoodDataSet")
 public class GoodDataSetAction
 {

   @Autowired
   private IGoodsService goodsService; //商品表
  
 
   private void execute()
    
   {   
	   try {
		 //获取所有商品
		   String goodId="0",goodGCId="0",goodBrandId="0",goodStoreId="0";
		   List<Goods> ubList = this.goodsService.query("select obj from Goods obj ", null, -1, -1);
		 String jsonStr = "{";
		   for(Goods gds : ubList) {
			 if(gds.getId()!=null)   goodId = gds.getId().toString();
			 if(gds.getGcId()!=null)   goodGCId = gds.getGcId().toString();
			 if(gds.getGbId()!=null)  goodBrandId =gds.getGbId().toString();
			 if(gds.getGsId()!=null)   goodStoreId = gds.getGsId().toString();
			 jsonStr+="'"+goodId+"':{'gcId':"+goodGCId+",'gbId':"+goodBrandId+",'gsId':"+goodStoreId+"},";
		  //  System.out.println(goodId+","+goodGCId+","+goodBrandId+","+goodStoreId);
		    goodId="0";goodGCId="0";goodBrandId="0";goodStoreId="0";
		    
		   }
		   jsonStr = jsonStr.substring(0,jsonStr.length()-1);
           jsonStr+="}";
           String path =ClassUtils.getDefaultClassLoader().getResource("").getPath().replace("WEB-INF/classes/", "")+"resources/";
           //商品Items
   		   JSONObject json  = JSONObject.parseObject(jsonStr);
   		   List<TestModel> listItem = new ArrayList<TestModel>();
   		   Map<String,List<TestModel>> mapF = new HashMap<String, List<TestModel>>();
   		   StringBuilder sb = new StringBuilder();
   		   Set<String> set = json.keySet();
   		   //{'1':{'gcId':5,'gbId':2,'gsId':1},'3':{'gcId':5,'gbId':3,'gsId':1}}
   		   sb.append("{");
	   	   for(String str : set) {
		   		listItem =sim_cos(json,str,new String[]{"gcId","gbId","gsId"});
		   	    sb.append("'"+str+"':{");
		   		for(TestModel tm :listItem) {
		   		 sb.append(tm.toString()+",");
		   		}
		   		sb.deleteCharAt(sb.length()-1);
		   	 sb.append("},");
	   	   }
	   	 sb.deleteCharAt(sb.length()-1);
	  	 sb.append("}");
	     FileWriter fw;
	    // ServletContext.getRealPath("/")+ "goods.txt";
         File file=new File(path+"/goods.txt");
        	 fw = new FileWriter(file);
        	 BufferedWriter bw = new BufferedWriter(fw);
        	// for(int i=0;i<500000;i++){
        		 bw.write(sb.toString());
        	//	 }
        	 bw.flush();
        	 bw.close(); 
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
	   
   }
 //余弦定理评价  0为不一样 ，  1为完全相同
 		public static List<TestModel> sim_cos(JSONObject json,String person1,String[] aS) {
 			List<TestModel> list = new ArrayList<TestModel>();
 			double sumT = 0.0;
 			double sumSq = 0.0;
 			double sumSq1 = 0.0;
 			Set<String> set = json.keySet(); 
 	   	   for(String str : set) {
 	   		   if(!str.equals(person1)) {
 	   			JSONObject json1  = JSONObject.parseObject(json.get(person1).toString());
 				JSONObject json2  = JSONObject.parseObject(json.get(str).toString());
 				for (int i=0;i<aS.length;i++) {
 						sumT+= Double.parseDouble(json1.get(aS[i]).toString())*Double.parseDouble(json2.get(aS[i]).toString());
 						sumSq+=	Math.pow(Double.parseDouble(json1.get(aS[i]).toString()),2);
 						sumSq1+=	Math.pow(Double.parseDouble(json2.get(aS[i]).toString()),2);	
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


 
 
 