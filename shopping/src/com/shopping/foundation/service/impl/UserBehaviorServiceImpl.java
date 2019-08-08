 package com.shopping.foundation.service.impl;
 
 import com.shopping.core.dao.IGenericDAO;
 import com.shopping.core.query.GenericPageList;
 import com.shopping.core.query.PageObject;
 import com.shopping.core.query.support.IPageList;
 import com.shopping.core.query.support.IQueryObject;
import com.shopping.foundation.domain.Accessory;
import com.shopping.foundation.domain.UserBehavior;
 import com.shopping.foundation.service.IUserBehaviorService;
 import java.io.Serializable;
 import java.util.List;
 import java.util.Map;
 import javax.annotation.Resource;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 
 @Service
 @Transactional
 public class UserBehaviorServiceImpl
   implements IUserBehaviorService
 {
 
   @Resource(name="userBehaviorDAO")
   private IGenericDAO<UserBehavior> UserBehaviorDao;
 
   public boolean save(UserBehavior UserBehavior)
   {
     try
     {
       this.UserBehaviorDao.save(UserBehavior);
       return true;
     } catch (Exception e) {
       e.printStackTrace();
     }return false;
   }
 
   public UserBehavior getObjById(Long id)
   {
     UserBehavior UserBehavior = (UserBehavior)this.UserBehaviorDao.get(id);
     if (UserBehavior != null) {
       return UserBehavior;
     }
     return null;
   }
 
   public boolean delete(Long id) {
     try {
       this.UserBehaviorDao.remove(id);
       return true;
     } catch (Exception e) {
       e.printStackTrace();
     }return false;
   }
 
   public boolean batchDelete(List<Serializable> UserBehaviorIds)
   {
     for (Serializable id : UserBehaviorIds) {
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
     GenericPageList pList = new GenericPageList(UserBehavior.class, query, 
       params, this.UserBehaviorDao);
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
 
   public boolean update(UserBehavior UserBehavior) {
     try {
       this.UserBehaviorDao.update(UserBehavior);
       return true;
     } catch (Exception e) {
       e.printStackTrace();
     }return false;
   }
 
   public List<UserBehavior> query(String query, Map params, int begin, int max) {
     return this.UserBehaviorDao.query(query, params, begin, max);
   }

	public UserBehavior getObjByProperty(String paramString1, String paramString2) {
		 return (UserBehavior)this.UserBehaviorDao.getBy(paramString1, paramString2);
	}
 }



 
 