package com.shopping.foundation.service;

import com.shopping.core.query.support.IPageList;
import com.shopping.core.query.support.IQueryObject;
import com.shopping.foundation.domain.UserBehavior;
import java.util.List;
import java.util.Map;

public abstract interface IUserBehaviorService
{
  public abstract boolean save(UserBehavior paramUserBehavior);

  public abstract boolean delete(Long paramLong);

  public abstract boolean update(UserBehavior paramUserBehavior);

  public abstract IPageList list(IQueryObject paramIQueryObject);

  public abstract UserBehavior getObjById(Long paramLong);

  public abstract UserBehavior getObjByProperty(String paramString1, String paramString2);

  public abstract List<UserBehavior> query(String paramString, Map paramMap, int paramInt1, int paramInt2);
}



 
 