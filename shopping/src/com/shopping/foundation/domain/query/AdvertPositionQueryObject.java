 package com.shopping.foundation.domain.query;
 
 import com.shopping.core.query.QueryObject;
 import org.springframework.web.servlet.ModelAndView;
 
 public class AdvertPositionQueryObject extends QueryObject
 {
   public AdvertPositionQueryObject(String currentPage, ModelAndView mv, String orderBy, String orderType)
   {
     super(currentPage, mv, orderBy, orderType);
   }
 
   public AdvertPositionQueryObject()
   {
   }
 }



 
 