package com.shopping.foundation.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.shopping.core.domain.IdEntity;
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name="user_behavior")
public class UserBehavior  extends IdEntity{
	@Column(name="behavior_id")
    private String behaviorId;
	
	@Column(name="user_id")
    private String userId;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

//	@OneToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name = "user_id")
//    private User user;
//
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

	@Column(name="behavior_label")
    private String behaviorLabel;

	@Column(name="behavior_num")
    private Integer behaviorNum;

	@Column(name="behavior_type")
    private String behaviorType;

	@Column(name="behavior_time")
    private Date behaviorTime;

	@Column(name="weight_value")
    private Long weightValue;

    public String getBehaviorId() {
        return behaviorId;
    }

    public void setBehaviorId(String behaviorId) {
        this.behaviorId = behaviorId == null ? null : behaviorId.trim();
    }

    public String getBehaviorLabel() {
        return behaviorLabel;
    }

    public void setBehaviorLabel(String behaviorLabel) {
        this.behaviorLabel = behaviorLabel == null ? null : behaviorLabel.trim();
    }

    public Integer getBehaviorNum() {
        return behaviorNum;
    }

    public void setBehaviorNum(Integer behaviorNum) {
        this.behaviorNum = behaviorNum;
    }

    public String getBehaviorType() {
        return behaviorType;
    }

    public void setBehaviorType(String behaviorType) {
        this.behaviorType = behaviorType == null ? null : behaviorType.trim();
    }

    public Date getBehaviorTime() {
        return behaviorTime;
    }

    public void setBehaviorTime(Date behaviorTime) {
        this.behaviorTime = behaviorTime;
    }

    public Long getWeightValue() {
        return weightValue;
    }

    public void setWeightValue(Long weightValue) {
        this.weightValue = weightValue;
    }
}