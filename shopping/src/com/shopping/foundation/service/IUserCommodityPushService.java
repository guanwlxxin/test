package com.shopping.foundation.service;

import com.shopping.core.query.support.IPageList;
import com.shopping.core.query.support.IQueryObject;
import com.shopping.foundation.domain.UserCommodityPush;
import java.util.List;
import java.util.Map;

public abstract interface IUserCommodityPushService
{
  public abstract boolean save(UserCommodityPush paramUserCommodityPush);

  public abstract boolean delete(Long paramLong);

  public abstract boolean update(UserCommodityPush paramUserCommodityPush);

  public abstract IPageList list(IQueryObject paramIQueryObject);

  public abstract UserCommodityPush getObjById(Long paramLong);

  public abstract UserCommodityPush getObjByProperty(String paramString1, String paramString2);

  public abstract List<UserCommodityPush> query(String paramString, Map paramMap, int paramInt1, int paramInt2);
}



 
 