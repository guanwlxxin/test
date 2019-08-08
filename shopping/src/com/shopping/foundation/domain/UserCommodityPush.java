package com.shopping.foundation.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.shopping.core.domain.IdEntity;
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name="user_commodity_push")
public class UserCommodityPush extends IdEntity {
	
	@Column(name="push_id")
    private String pushId;
    
	@Column(name="user_id")
    private String userId;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
	
	public String getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}

	@Column(name="commodity_id", updatable=false, insertable=false)
    private String commodityId;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "commodity_id")
    private Goods good;

    public Goods getGood() {
		return good;
	}

	public void setGood(Goods good) {
		this.good = good;
	}

	@Column(name="is_buy")
    private Integer isBuy;

	@Column(name="is_click")
    private Integer isClick;

	@Column(name="browse_num")
    private Integer browseNum;

	@Column(name="create_time")
    private Date createTime;

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId == null ? null : pushId.trim();
    }

    public Integer getIsBuy() {
        return isBuy;
    }

    public void setIsBuy(Integer isBuy) {
        this.isBuy = isBuy;
    }

    public Integer getIsClick() {
        return isClick;
    }

    public void setIsClick(Integer isClick) {
        this.isClick = isClick;
    }

    public Integer getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(Integer browseNum) {
        this.browseNum = browseNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}