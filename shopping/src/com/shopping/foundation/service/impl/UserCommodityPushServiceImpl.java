 package com.shopping.foundation.service.impl;
 
 import com.shopping.core.dao.IGenericDAO;
 import com.shopping.core.query.GenericPageList;
 import com.shopping.core.query.PageObject;
 import com.shopping.core.query.support.IPageList;
 import com.shopping.core.query.support.IQueryObject;
import com.shopping.foundation.domain.Accessory;
import com.shopping.foundation.domain.UserCommodityPush;
 import com.shopping.foundation.service.IUserCommodityPushService;
 import java.io.Serializable;
 import java.util.List;
 import java.util.Map;
 import javax.annotation.Resource;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 
 @Service
 @Transactional
 public class UserCommodityPushServiceImpl
   implements IUserCommodityPushService
 {
 
   @Resource(name="userCommodityPushDAO")
   private IGenericDAO<UserCommodityPush> UserCommodityPushDao;
 
   public boolean save(UserCommodityPush UserCommodityPush)
   {
     try
     {
       this.UserCommodityPushDao.save(UserCommodityPush);
       return true;
     } catch (Exception e) {
       e.printStackTrace();
     }return false;
   }
 
   public UserCommodityPush getObjById(Long id)
   {
     UserCommodityPush UserCommodityPush = (UserCommodityPush)this.UserCommodityPushDao.get(id);
     if (UserCommodityPush != null) {
       return UserCommodityPush;
     }
     return null;
   }
 
   public boolean delete(Long id) {
     try {
       this.UserCommodityPushDao.remove(id);
       return true;
     } catch (Exception e) {
       e.printStackTrace();
     }return false;
   }
 
   public boolean batchDelete(List<Serializable> UserCommodityPushIds)
   {
     for (Serializable id : UserCommodityPushIds) {
       delete((Long)id);
     }
     return true;
   }
 
   public IPageList list(IQueryObject properties) {
     if (properties == null) {
       return null;
     }
     String query = properties.getQuery();
     Map params = properties.getParameters();
     GenericPageList pList = new GenericPageList(UserCommodityPush.class, query, 
       params, this.UserCommodityPushDao);
     if (properties != null) {
       PageObject pageObj = properties.getPageObj();
       if (pageObj != null)
         pList.doList(pageObj.getCurrentPage() == null ? 0 : pageObj
           .getCurrentPage().intValue(), pageObj.getPageSize() == null ? 0 : 
           pageObj.getPageSize().intValue());
     } else {
       pList.doList(0, -1);
     }return pList;
   }
 
   public boolean update(UserCommodityPush UserCommodityPush) {
     try {
       this.UserCommodityPushDao.update(UserCommodityPush);
       return true;
     } catch (Exception e) {
       e.printStackTrace();
     }return false;
   }
 
   public List<UserCommodityPush> query(String query, Map params, int begin, int max) {
     return this.UserCommodityPushDao.query(query, params, begin, max);
   }

	public UserCommodityPush getObjByProperty(String paramString1, String paramString2) {
		return (UserCommodityPush)this.UserCommodityPushDao.getBy(paramString1, paramString2);
	}
 }



 
 