 package com.shopping.foundation.service.impl;
 
 import com.shopping.core.dao.IGenericDAO;
 import com.shopping.core.query.GenericPageList;
 import com.shopping.core.query.PageObject;
 import com.shopping.core.query.support.IPageList;
 import com.shopping.core.query.support.IQueryObject;
import com.shopping.foundation.domain.UserBehavior;
import com.shopping.foundation.domain.UserH5webBrowseLog;
 import com.shopping.foundation.service.IUserH5webBrowseLogService;
 import java.io.Serializable;
 import java.util.List;
 import java.util.Map;
 import javax.annotation.Resource;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 
 @Service
 @Transactional
 public class UserH5webBrowseLogServiceImpl
   implements IUserH5webBrowseLogService
 {
 
   @Resource(name="userH5webBrowseLogDAO")
   private IGenericDAO<UserH5webBrowseLog> UserH5webBrowseLogDao;
 
   public boolean save(UserH5webBrowseLog UserH5webBrowseLog)
   {
     try
     {
       this.UserH5webBrowseLogDao.save(UserH5webBrowseLog);
       return true;
     } catch (Exception e) {
       e.printStackTrace();
     }return false;
   }
 
   public UserH5webBrowseLog getObjById(Long id)
   {
     UserH5webBrowseLog UserH5webBrowseLog = (UserH5webBrowseLog)this.UserH5webBrowseLogDao.get(id);
     if (UserH5webBrowseLog != null) {
       return UserH5webBrowseLog;
     }
     return null;
   }
 
   public boolean delete(Long id) {
     try {
       this.UserH5webBrowseLogDao.remove(id);
       return true;
     } catch (Exception e) {
       e.printStackTrace();
     }return false;
   }
 
   public boolean batchDelete(List<Serializable> UserH5webBrowseLogIds)
   {
     for (Serializable id : UserH5webBrowseLogIds) {
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
     GenericPageList pList = new GenericPageList(UserH5webBrowseLog.class, query, 
       params, this.UserH5webBrowseLogDao);
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
 
   public boolean update(UserH5webBrowseLog UserH5webBrowseLog) {
     try {
       this.UserH5webBrowseLogDao.update(UserH5webBrowseLog);
       return true;
     } catch (Exception e) {
       e.printStackTrace();
     }return false;
   }
 
   public List<UserH5webBrowseLog> query(String query, Map params, int begin, int max) {
     return this.UserH5webBrowseLogDao.query(query, params, begin, max);
   }

	public UserH5webBrowseLog getObjByProperty(String paramString1, String paramString2) {
		 return (UserH5webBrowseLog)this.UserH5webBrowseLogDao.getBy(paramString1, paramString2);
	}
 }



 
 