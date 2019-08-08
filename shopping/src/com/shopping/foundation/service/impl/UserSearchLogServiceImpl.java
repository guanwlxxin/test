 package com.shopping.foundation.service.impl;
 
 import com.shopping.core.dao.IGenericDAO;
 import com.shopping.core.query.GenericPageList;
 import com.shopping.core.query.PageObject;
 import com.shopping.core.query.support.IPageList;
 import com.shopping.core.query.support.IQueryObject;
import com.shopping.foundation.domain.UserPcwebBrowseLog;
import com.shopping.foundation.domain.UserSearchLog;
 import com.shopping.foundation.service.IUserSearchLogService;
 import java.io.Serializable;
 import java.util.List;
 import java.util.Map;
 import javax.annotation.Resource;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 
 @Service
 @Transactional
 public class UserSearchLogServiceImpl
   implements IUserSearchLogService
 {
 
   @Resource(name="userSearchLogDAO")
   private IGenericDAO<UserSearchLog> UserSearchLogDao;
 
   public boolean save(UserSearchLog UserSearchLog)
   {
     try
     {
       this.UserSearchLogDao.save(UserSearchLog);
       return true;
     } catch (Exception e) {
       e.printStackTrace();
     }return false;
   }
 
   public UserSearchLog getObjById(Long id)
   {
     UserSearchLog UserSearchLog = (UserSearchLog)this.UserSearchLogDao.get(id);
     if (UserSearchLog != null) {
       return UserSearchLog;
     }
     return null;
   }
 
   public boolean delete(Long id) {
     try {
       this.UserSearchLogDao.remove(id);
       return true;
     } catch (Exception e) {
       e.printStackTrace();
     }return false;
   }
 
   public boolean batchDelete(List<Serializable> UserSearchLogIds)
   {
     for (Serializable id : UserSearchLogIds) {
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
     GenericPageList pList = new GenericPageList(UserSearchLog.class, query, 
       params, this.UserSearchLogDao);
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
 
   public boolean update(UserSearchLog UserSearchLog) {
     try {
       this.UserSearchLogDao.update(UserSearchLog);
       return true;
     } catch (Exception e) {
       e.printStackTrace();
     }return false;
   }
 
   public List<UserSearchLog> query(String query, Map params, int begin, int max) {
     return this.UserSearchLogDao.query(query, params, begin, max);
   }

	public UserSearchLog getObjByProperty(String paramString1, String paramString2) {
		return (UserSearchLog)this.UserSearchLogDao.getBy(paramString1, paramString2);
	}
 }



 
 