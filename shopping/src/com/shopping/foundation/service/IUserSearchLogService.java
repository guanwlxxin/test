package com.shopping.foundation.service;

import com.shopping.core.query.support.IPageList;
import com.shopping.core.query.support.IQueryObject;
import com.shopping.foundation.domain.UserSearchLog;
import java.util.List;
import java.util.Map;

public abstract interface IUserSearchLogService
{
  public abstract boolean save(UserSearchLog paramUserSearchLog);

  public abstract boolean delete(Long paramLong);

  public abstract boolean update(UserSearchLog paramUserSearchLog);

  public abstract IPageList list(IQueryObject paramIQueryObject);

  public abstract UserSearchLog getObjById(Long paramLong);

  public abstract UserSearchLog getObjByProperty(String paramString1, String paramString2);

  public abstract List<UserSearchLog> query(String paramString, Map paramMap, int paramInt1, int paramInt2);
}



 
 