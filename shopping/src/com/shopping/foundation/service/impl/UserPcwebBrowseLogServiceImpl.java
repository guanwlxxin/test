 package com.shopping.foundation.service.impl;
 
 import com.shopping.core.dao.IGenericDAO;
 import com.shopping.core.query.GenericPageList;
 import com.shopping.core.query.PageObject;
 import com.shopping.core.query.support.IPageList;
 import com.shopping.core.query.support.IQueryObject;
import com.shopping.foundation.domain.UserH5webBrowseLog;
import com.shopping.foundation.domain.UserPcwebBrowseLog;
 import com.shopping.foundation.service.IUserPcwebBrowseLogService;
 import java.io.Serializable;
 import java.util.List;
 import java.util.Map;
 import javax.annotation.Resource;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 
 @Service
 @Transactional
 public class UserPcwebBrowseLogServiceImpl
   implements IUserPcwebBrowseLogService
 {
 
   @Resource(name="userPcwebBrowseLogDAO")
   private IGenericDAO<UserPcwebBrowseLog> UserPcwebBrowseLogDao;
 
   public boolean save(UserPcwebBrowseLog UserPcwebBrowseLog)
   {
     try
     {
       this.UserPcwebBrowseLogDao.save(UserPcwebBrowseLog);
       return true;
     } catch (Exception e) {
       e.printStackTrace();
     }return false;
   }
 
   public UserPcwebBrowseLog getObjById(Long id)
   {
     UserPcwebBrowseLog UserPcwebBrowseLog = (UserPcwebBrowseLog)this.UserPcwebBrowseLogDao.get(id);
     if (UserPcwebBrowseLog != null) {
       return UserPcwebBrowseLog;
     }
     return null;
   }
 
   public boolean delete(Long id) {
     try {
       this.UserPcwebBrowseLogDao.remove(id);
       return true;
     } catch (Exception e) {
       e.printStackTrace();
     }return false;
   }
 
   public boolean batchDelete(List<Serializable> UserPcwebBrowseLogIds)
   {
     for (Serializable id : UserPcwebBrowseLogIds) {
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
     GenericPageList pList = new GenericPageList(UserPcwebBrowseLog.class, query, 
       params, this.UserPcwebBrowseLogDao);
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
 
   public boolean update(UserPcwebBrowseLog UserPcwebBrowseLog) {
     try {
       this.UserPcwebBrowseLogDao.update(UserPcwebBrowseLog);
       return true;
     } catch (Exception e) {
       e.printStackTrace();
     }return false;
   }
 
   public List<UserPcwebBrowseLog> query(String query, Map params, int begin, int max) {
     return this.UserPcwebBrowseLogDao.query(query, params, begin, max);
   }

	public UserPcwebBrowseLog getObjByProperty(String paramString1, String paramString2) {
		 return (UserPcwebBrowseLog)this.UserPcwebBrowseLogDao.getBy(paramString1, paramString2);
	}
 }



 
 