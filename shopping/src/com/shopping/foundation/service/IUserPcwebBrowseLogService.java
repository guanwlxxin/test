package com.shopping.foundation.service;

import com.shopping.core.query.support.IPageList;
import com.shopping.core.query.support.IQueryObject;
import com.shopping.foundation.domain.UserPcwebBrowseLog;
import java.util.List;
import java.util.Map;

public abstract interface IUserPcwebBrowseLogService
{
  public abstract boolean save(UserPcwebBrowseLog paramUserPcwebBrowseLog);

  public abstract boolean delete(Long paramLong);

  public abstract boolean update(UserPcwebBrowseLog paramUserPcwebBrowseLog);

  public abstract IPageList list(IQueryObject paramIQueryObject);

  public abstract UserPcwebBrowseLog getObjById(Long paramLong);

  public abstract UserPcwebBrowseLog getObjByProperty(String paramString1, String paramString2);

  public abstract List<UserPcwebBrowseLog> query(String paramString, Map paramMap, int paramInt1, int paramInt2);
}



 
 