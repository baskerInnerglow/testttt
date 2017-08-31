package com.cmacgm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Application {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	@Column(name = "APPLICATION_NAME")
	private String applicationName;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "LAST_UPDATED_TIME")
	private Date lastUpdatedTime;
	@Column(name = "CREATED_BY")
	private String createdBy;
	@Column(name = "ENABLE")
	private boolean enable;
	private Date lastSyncTime;
	//how often the job runs
	private long syncJobRate = 3600000l;
	//delay from application startup that the job starts
	private long syncJobInitialDelay = 10000l;

	@Column(name = "CREATED_ON")
	private Date createdOn;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
	name="app_appurl",
	joinColumns=
	@JoinColumn(name="app_id", referencedColumnName="id"),
	inverseJoinColumns=
	@JoinColumn(name="app_url_id", referencedColumnName="id")
	)
	 @JsonIgnore
	private List<ApplicationUrl> applicationUrl= new ArrayList<>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}
	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public List<ApplicationUrl> getApplicationUrl() {
		return applicationUrl;
	}
	public void setApplicationUrl(List<ApplicationUrl> applicationUrl) {
		this.applicationUrl = applicationUrl;
	}
	public Date getLastSyncTime() {
		return lastSyncTime;
	}
	public void setLastSyncTime(Date lastSyncTime) {
		this.lastSyncTime = lastSyncTime;
	}
	public long getSyncJobRate() {
		return syncJobRate;
	}
	public void setSyncJobRate(long syncJobRate) {
		this.syncJobRate = syncJobRate;
	}
	public long getSyncJobInitialDelay() {
		return syncJobInitialDelay;
	}
	public void setSyncJobInitialDelay(long syncJobInitialDelay) {
		this.syncJobInitialDelay = syncJobInitialDelay;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	
	 public Application(){}
	public Application(String applicationName, String description, Date lastUpdatedTime, boolean enable,
			Date lastSyncTime, long syncJobRate, long syncJobInitialDelay, Date createdOn,
			List<ApplicationUrl> applicationUrl,String createdBy) {
		super();
		this.applicationName = applicationName;
		this.description = description;
		this.lastUpdatedTime = lastUpdatedTime;
		this.enable = enable;		
		this.lastSyncTime = lastSyncTime;
		this.syncJobRate = syncJobRate;
		this.syncJobInitialDelay = syncJobInitialDelay;
		this.createdOn = createdOn;
		this.applicationUrl = applicationUrl;
		this.createdBy=createdBy;
	}

	
	
   
	
	
}
