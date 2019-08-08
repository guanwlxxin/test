 package com.shopping.manage.timer;
 
 import com.alibaba.fastjson.JSONObject;
import com.shopping.foundation.domain.UserBehavior;
import com.shopping.foundation.domain.UserCommodityPush;
import com.shopping.foundation.service.IGoodsService;
 import com.shopping.foundation.service.IUserBehaviorService;
import com.shopping.foundation.service.IUserCommodityPushService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
 import java.util.HashMap;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
 
 @Component("shop_GoodPush")
 public class GoodPushManageAction
 {
   @Autowired
   private IUserBehaviorService userBehaviorService;
   
   @Autowired
   private IGoodsService goodsService; //商品表
   @Autowired
   private IUserCommodityPushService commodityPushService;
 
   private void execute()
   
   {   
	   try {
		   //获取所有用户行为
		   String path =ClassUtils.getDefaultClassLoader().getResource("").getPath().replace("WEB-INF/classes/", "")+"resources/";
		   JSONObject jsonUser  = JSONObject.parseObject(readFile(path+"/users.txt"));
		   JSONObject tempJson = new JSONObject();
		   List<String> tempList = new ArrayList<String>();
		   String nowUserGoods = "";
		   String simUserGoods = "";
		   Set<String> userKey = jsonUser.keySet();
		   Map<String,String> mapUser = new HashMap<String, String>();
		   for(String uKey :userKey) {
			   tempJson = jsonUser.getJSONObject(uKey).getJSONObject("user");
			   nowUserGoods = jsonUser.getJSONObject(uKey).getString("good");
			   Set<String> tempKey  = tempJson.keySet();
			   for(String tKey :tempKey) {
				  tempList.add(tKey); 
			   }
			   mapUser.put(uKey, pushGoodsByUser(jsonUser,tempList,nowUserGoods));
			 //  System.out.println("基于用户推荐："+uKey+"推荐为物品:"+pushGoodsByUser(jsonUser,tempList,nowUserGoods));
			   tempList.clear();
		   }
		   JSONObject jsonGood  = JSONObject.parseObject(readFile(path+"/goods.txt"));
		   Map<String,String> mapGood = new HashMap<String, String>();
		   for(String uKey :userKey) {
			   nowUserGoods = jsonUser.getJSONObject(uKey).getString("good");
			   mapGood.put(uKey, pushGoodsByGoods(jsonGood,nowUserGoods));
			  // System.out.println("基于商品推荐："+uKey+"推荐为物品:"+pushGoodsByGoods(jsonGood,nowUserGoods));
			   tempList.clear();
		   }
		   //合并推荐商品
		   String pushGoods="";
		   int pGCount = 0;
		   List<String> listPushAndUser = new ArrayList<String>();
		   for(Map.Entry<String, String> map1 :mapUser.entrySet()) {
			   String[]   arry1 = map1.getValue().split(",");
			   String[]   arry2 = mapGood.get(map1.getKey()).split(",");
			    pushGoods =   getDuplicate(arry1,arry2);
			    pGCount = pushGoods.split(",").length;
			    if(pushGoods.length()>0) {
			    	 pushGoods = pushGoods+",";
			    }
			    if(pGCount<5) {//暂定推荐5个商品
			    	for(int i=5-pGCount;i>=0;i--) {
			    		if(arry2.length>i) {
			    			pushGoods+=arry2[i]+",";
			    		}else if(arry1.length>i){
			    			pushGoods+=arry1[i]+",";
			    		}
			    	}
			    }
			    if(pushGoods.length()>0) {
			    	 pushGoods = pushGoods.substring(0, pushGoods.length()-1);
			    	 listPushAndUser.add(map1.getKey()+":"+pushGoods);
			    }
			    System.out.println(map1.getKey()+"推荐商品为："+pushGoods);
		   }
		   String uId = "";
		   for(String mt:listPushAndUser) {
			   uId = mt.split(":")[0];
			   String[] goodsG = mt.split(":")[1].split(",");
			 for(int i=0;i<goodsG.length;i++) {
				   UserCommodityPush ucp = new UserCommodityPush();
				   ucp.setUserId(uId);
				   ucp.setGood(this.goodsService.getObjById(Long.valueOf(goodsG[i])));
				   ucp.setAddTime(new Date());
				   this.commodityPushService.save(ucp);
			 }
			
		   }
		   System.out.println("商品推荐："+new Date().toString());
	} catch (Exception e) {
		e.printStackTrace();
	}
	  
   }
   //获取两次推荐相同商品
   public  String getDuplicate(String[] a,String[] b) {
	   List<String> arryList1 = Arrays.asList(a);
	   List<String> arryList2 = Arrays.asList(b);
	   Collection<String> values = CollectionUtils.intersection(arryList1, arryList2);
       Iterator<String> iterator = values.iterator();
       String c [] = new String[values.size()];
       int m = 0;
       while (iterator.hasNext()){
          c[m++] = iterator.next();
       }
       String re = Arrays.toString(c);
       re = re.substring(1, re.length()-1).replaceAll(" ", "");
       return  re;
//	   Map<String,String> map = new HashMap<String,String>();
//       for (int i = 0; i < a.length; i++) {
//           map.put(a[i].trim(),a[i].trim());
//       }
//       for (int i = 0; i < b.length; i++) {
//           map.put(b[i].trim(),b[i].trim());
//       }
//       Collection<String> values = map.values();
//       Iterator<String> iterator = values.iterator();
//       String c [] = new String[values.size()];
//       int m = 0;
//       while (iterator.hasNext()){
//           c[m++] = iterator.next();
//       }
//       String re = Arrays.toString(c);
//       re = re.substring(1, re.length()-1).replaceAll(" ", "");
//       return  re;
   }
   /*
    * 根据商品返回推荐的商品
    * */
   public String pushGoodsByGoods(JSONObject goodJson,String nowUserGoods) {
	   String simUserGoods = "";
	   String tempS = "";
	   String reGoods= "";
	   for(String nG :nowUserGoods.split(",")) {
		   String[] simGood = goodJson.getString(nG).split(",");
		   for(int i=0;i<2;i++) { //选择相似度前两名的商品
			   tempS = simGood[i].split(":")[0].replaceAll("\\{", "").replaceAll("\"", "");
			   if(reGoods.indexOf(tempS)==-1) {
				   reGoods+=tempS+",";
			   }
		   }
	   }
	   reGoods = reGoods.substring(0, reGoods.length()-1);
	   
	   return reGoods;
   }
   
   /*
    * 根据用户相似度返回推荐的商品
    * */
   public String pushGoodsByUser(JSONObject userJson,List<String> simUserList,String nowUserGoods) {
	   String simUserGoods = "";
	   String reGoods= "";
	   List<String> ul = simUserList.subList(0, 2);//这里选择与用户相似度前两名的用户
	   for(String uId :ul) {
		   simUserGoods =  userJson.getJSONObject(uId).getString("good"); 
		   for (String str : simUserGoods.split(";")) {
	            if (Arrays.binarySearch(nowUserGoods.split(";"), str) < 0) {
	              //  System.out.println("第二个数组中没有第一个数组的元素:" + str);
	                reGoods+=str+",";
	            }
	        }
	   }
	   reGoods = reGoods.substring(0, reGoods.length()-1);
	   
	   return reGoods;
   }
   
   public String readFile(String path) {
	   
	   File file = new File(path);
		//result用来存储文件内容
		StringBuilder result = new StringBuilder();
		//判断文件存在并且是文件
		Boolean boo = file.exists()&&file.isFile();
		//System.out.println(boo);
		if (boo) {
			BufferedReader bufferedReader = null;
			try {
				//构造一个BufferedReader类来读取文件
				bufferedReader = new BufferedReader(new FileReader(file));
				String linetxt = null;
			
				//按使用readLine方法，一次读一行
				while ((linetxt = bufferedReader.readLine()) != null) {
				//	System.out.println(linetxt);
					result.append(linetxt);
				}
				//输出读出的所有数据（StringBuilder类型）
			//	System.out.println(result);
			} catch (Exception e) {
				System.out.println("读取文件内容出错");
				e.printStackTrace();
			}finally {
				try {
					bufferedReader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
           }  
		}else{
			System.out.println("找不到指定的文件");
		}

      return result.toString();

   }
//   public static List<Map<String,Object>> reverseOrder(JSONObject cjson,JSONObject rjson) {
//		
//		List<Map<String,Object>> listItem = new ArrayList<Map<String,Object>>();
//		double simNum = 0.0;
//		Set<String> set = cjson.keySet(); 
//		Set<String> set1 = rjson.keySet(); 
//		for(String str1 : set1) {
//			List<TestModel> list = new ArrayList<TestModel>();
//			cjson.put(str1, rjson.get(str1));
//			JSONObject json  = JSONObject.parseObject(rjson.get(str1).toString());
//			Set<String> rset = json.keySet(); 
//			String rStr = "";
//			for (String str : rset) {
//				rStr+=str+",";
//			}
//			String[] aS =rStr.substring(0, rStr.length()-1).split(",");
//			for (String str : set) {
//				if(!str1.equals(str)) {
//					simNum =sim_cos(cjson,str1,str,aS);
//					list.add(new TestModel(str,simNum));
//				}
//			}
//			list.sort(Comparator.reverseOrder());//倒序比较
//			if(list.size()>5) {
//				list = list.subList(0, 5);
//			}
//			Map<String,Object> map = new HashMap<String, Object>();
//			map.put(str1, list);
//			listItem.add(map);
//			System.out.println(str1+":"+list);
//		//	list.clear();
//			cjson.remove(str1);
//		}
//		return listItem;
//	}
	//余弦定理评价  0为不一样 ，  1为完全相同
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


 
 
 