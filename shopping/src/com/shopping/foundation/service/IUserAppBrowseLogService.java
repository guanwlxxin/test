package com.shopping.foundation.service;

import com.shopping.core.query.support.IPageList;
import com.shopping.core.query.support.IQueryObject;
import com.shopping.foundation.domain.UserAppBrowseLog;
import java.util.List;
import java.util.Map;

public abstract interface IUserAppBrowseLogService
{
  public abstract boolean save(UserAppBrowseLog paramUserAppBrowseLog);

  public abstract boolean delete(Long paramLong);

  public abstract boolean update(UserAppBrowseLog paramUserAppBrowseLog);

  public abstract IPageList list(IQueryObject paramIQueryObject);

  public abstract UserAppBrowseLog getObjById(Long paramLong);

  public abstract UserAppBrowseLog getObjByProperty(String paramString1, String paramString2);

  public abstract List<UserAppBrowseLog> query(String paramString, Map paramMap, int paramInt1, int paramInt2);
}



 
 