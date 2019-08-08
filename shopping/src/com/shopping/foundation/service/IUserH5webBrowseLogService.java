package com.shopping.foundation.service;

import com.shopping.core.query.support.IPageList;
import com.shopping.core.query.support.IQueryObject;
import com.shopping.foundation.domain.UserH5webBrowseLog;
import java.util.List;
import java.util.Map;

public abstract interface IUserH5webBrowseLogService
{
  public abstract boolean save(UserH5webBrowseLog paramUserH5webBrowseLog);

  public abstract boolean delete(Long paramLong);

  public abstract boolean update(UserH5webBrowseLog paramUserH5webBrowseLog);

  public abstract IPageList list(IQueryObject paramIQueryObject);

  public abstract UserH5webBrowseLog getObjById(Long paramLong);

  public abstract UserH5webBrowseLog getObjByProperty(String paramString1, String paramString2);

  public abstract List<UserH5webBrowseLog> query(String paramString, Map paramMap, int paramInt1, int paramInt2);
}



 
 