 package com.shopping.foundation.domain.query;
 
 import com.shopping.core.query.QueryObject;
 import org.springframework.web.servlet.ModelAndView;
 
 public class DeliveryGoodsQueryObject extends QueryObject
 {
   public DeliveryGoodsQueryObject(String currentPage, ModelAndView mv, String orderBy, String orderType)
   {
     super(currentPage, mv, orderBy, orderType);
   }
 
   public DeliveryGoodsQueryObject()
   {
   }
 }



 
 