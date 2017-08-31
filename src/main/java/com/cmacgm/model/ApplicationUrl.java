package com.cmacgm.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class ApplicationUrl {

	public ApplicationUrl() {

	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;
	@OneToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	private ServerType serverType;
	@Column(name = "APPLICATION_URL")
	private String applicationUrl;
	@Column(name = "IP_ADDRESS")
	private String ipAddress;
	@Column(name = "HOST_PORT_NUMBER")
	private String hostPortNumber;
	public String getHostPortNumber() {
		return hostPortNumber;
	}
	public void setHostPortNumber(String hostPortNumber) {
		this.hostPortNumber = hostPortNumber;
	}


	@Column(name = "STATUS_CODE")
	private String statusCode;
	@Column(name = "STATUS")
	private boolean status;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "CREATED_ON")
	private Date createdOn;
	@Column(name = "LAST_UPDATED_TIME")
	private Date lastUpdatedTime;
	@Column(name = "CREATED_BY")
	private String createdBy;
	@Column(name = "CREATED_ON")
	 @JsonManagedReference
	 @ManyToMany(mappedBy = "applicationUrl")
	    public List<Application> application;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public ServerType getServerType() {
		return serverType;
	}
	public void setServerType(ServerType serverType) {
		this.serverType = serverType;
	}
	public String getApplicationUrl() {
		return applicationUrl;
	}
	public void setApplicationUrl(String applicationUrl) {
		this.applicationUrl = applicationUrl;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public ApplicationUrl(long id, ServerType serverType, String ipAddress, String hostPortNumber, String statusCode,
			boolean status, String description, Date createdOn, List<Application> application,String createdBy) {
		this.id = id;
		this.serverType = serverType;
		this.ipAddress = ipAddress;
		this.hostPortNumber = hostPortNumber;
		this.statusCode = statusCode;
		this.status = status;
		this.description = description;
		this.createdOn = createdOn;
		this.application = application;
		this.createdBy=createdBy;
	}
	public List<Application> getApplication() {
		return application;
	}
	public void setApplication(List<Application> application) {
		this.application = application;
	}
	
	
	
   
	
}
