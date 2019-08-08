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
@Table(name="user_h5web_browse_log")
public class UserH5webBrowseLog extends IdEntity {
	
    @Column(name="h5web_log_id")
    private String h5webLogId;

	public String getH5webLogId() {
		return h5webLogId;
	}

	public void setH5webLogId(String h5webLogId) {
		this.h5webLogId = h5webLogId;
	}

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name="visit_time")
    private Date visitTime;
	
	@Column(name="report_time")
    private Date reportTime;
	
	@Column(name="province")
    private String province;
	
	@Column(name="city")
    private String city;
	
	@Column(name="referer_url")
    private String refererUrl;
	
	@Column(name="url")
    private String url;
	
	@Column(name="client")
    private String client;
	
	@Column(name="lon")
    private String lon;
	
	@Column(name="lat")
    private String lat;
	

	@Column(name="user_id", updatable=false, insertable=false)
    private String userId;//店铺id
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name="commodity_id", updatable=false, insertable=false)
    private String commodityId;//店铺id
	
	public String getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "commodity_id")
    private Goods good;

    public Goods getGood() {
		return good;
	}

	public void setGood(Goods good) {
		this.good = good;
	}

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getRefererUrl() {
        return refererUrl;
    }

    public void setRefererUrl(String refererUrl) {
        this.refererUrl = refererUrl == null ? null : refererUrl.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client == null ? null : client.trim();
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