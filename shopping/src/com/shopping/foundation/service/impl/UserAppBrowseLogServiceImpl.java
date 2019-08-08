 package com.shopping.foundation.service.impl;
 
 import com.shopping.core.dao.IGenericDAO;
 import com.shopping.core.query.GenericPageList;
 import com.shopping.core.query.PageObject;
 import com.shopping.core.query.support.IPageList;
 import com.shopping.core.query.support.IQueryObject;
import com.shopping.foundation.domain.Accessory;
import com.shopping.foundation.domain.UserAppBrowseLog;
 import com.shopping.foundation.service.IUserAppBrowseLogService;
 import java.io.Serializable;
 import java.util.List;
 import java.util.Map;
 import javax.annotation.Resource;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 
 @Service
 @Transactional
 public class UserAppBrowseLogServiceImpl
   implements IUserAppBrowseLogService
 {
 
   @Resource(name="userAppBrowseLogDAO")
   private IGenericDAO<UserAppBrowseLog> UserAppBrowseLogDao;
 
   public boolean save(UserAppBrowseLog UserAppBrowseLog)
   {
     try
     {
       this.UserAppBrowseLogDao.save(UserAppBrowseLog);
       return true;
     } catch (Exception e) {
       e.printStackTrace();
     }return false;
   }
 
   public UserAppBrowseLog getObjById(Long id)
   {
     UserAppBrowseLog UserAppBrowseLog = (UserAppBrowseLog)this.UserAppBrowseLogDao.get(id);
     if (UserAppBrowseLog != null) {
       return UserAppBrowseLog;
     }
     return null;
   }
 
   public boolean delete(Long id) {
     try {
       this.UserAppBrowseLogDao.remove(id);
       return true;
     } catch (Exception e) {
       e.printStackTrace();
     }return false;
   }
 
   public boolean batchDelete(List<Serializable> UserAppBrowseLogIds)
   {
     for (Serializable id : UserAppBrowseLogIds) {
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
     GenericPageList pList = new GenericPageList(UserAppBrowseLog.class, query, 
       params, this.UserAppBrowseLogDao);
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
 
   public boolean update(UserAppBrowseLog UserAppBrowseLog) {
     try {
       this.UserAppBrowseLogDao.update(UserAppBrowseLog);
       return true;
     } catch (Exception e) {
       e.printStackTrace();
     }return false;
   }
 
   public List<UserAppBrowseLog> query(String query, Map params, int begin, int max) {
     return this.UserAppBrowseLogDao.query(query, params, begin, max);
   }

	public UserAppBrowseLog getObjByProperty(String paramString1, String paramString2) {
		return (UserAppBrowseLog)this.UserAppBrowseLogDao.getBy(paramString1, paramString2);
	}
 }



 
 