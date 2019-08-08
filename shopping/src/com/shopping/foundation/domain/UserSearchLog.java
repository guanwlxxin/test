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
@Table(name="user_search_log")
public class UserSearchLog extends IdEntity{
	
	@Column(name="pcweb_log_id")
    private String searchLogId;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name="search_time")
	private Date searchTime;

	@Column(name="search_key")
    private String searchKey;

	@Column(name="tag_name")
    private String tagName;

	@Column(name="create_time")
    private Date createTime;

	@Column(name="client_system")
    private String clientSystem;

	@Column(name="lon")
    private String lon;

	@Column(name="lat")
    private String lat;

    public String getSearchLogId() {
        return searchLogId;
    }

    public void setSearchLogId(String searchLogId) {
        this.searchLogId = searchLogId == null ? null : searchLogId.trim();
    }



    public Date getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(Date searchTime) {
        this.searchTime = searchTime;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey == null ? null : searchKey.trim();
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getClientSystem() {
        return clientSystem;
    }

    public void setClientSystem(String clientSystem) {
        this.clientSystem = clientSystem == null ? null : clientSystem.trim();
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon == null ? null : lon.trim();
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat == null ? null : lat.trim();
    }
}