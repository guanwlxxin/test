 package com.shopping.foundation.domain;
 
 import com.shopping.core.domain.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
 import javax.persistence.FetchType;
 import javax.persistence.ManyToOne;
 import javax.persistence.Table;
 import org.hibernate.annotations.Cache;
 import org.hibernate.annotations.CacheConcurrencyStrategy;
 
 @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
 @Entity
 @Table(name="shopping_favorite")
 public class Favorite extends IdEntity
 {
   private int type;
 
   @ManyToOne(fetch=FetchType.LAZY)
   private Goods goods;
 
   @ManyToOne(fetch=FetchType.LAZY)
   private Store store;
 
   @ManyToOne(fetch=FetchType.LAZY)
   private User user;
   @Column(name="goods_id", updatable=false, insertable=false)
   private String goodsId;
   @Column(name="user_id", updatable=false, insertable=false)
   private String userId;
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGoodsId() {
		return goodsId;
	}
	
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
   public int getType()
   {
     return this.type;
   }
 
   public void setType(int type) {
     this.type = type;
   }
 
   public Goods getGoods() {
     return this.goods;
   }
 
   public void setGoods(Goods goods) {
     this.goods = goods;
   }
 
   public Store getStore() {
     return this.store;
   }
 
   public void setStore(Store store) {
     this.store = store;
   }
 
   public User getUser() {
     return this.user;
   }
 
   public void setUser(User user) {
     this.user = user;
   }
 }



 
 