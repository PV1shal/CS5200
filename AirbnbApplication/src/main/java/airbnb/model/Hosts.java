package airbnb.model;

import java.util.Date;

public class Hosts {
	protected int hostId;
	protected String hostUrl;
	protected String hostName;
	protected Date hostSince;
	protected String hostResponseTime;
	protected int hostResponseRate;
	protected int hostTotalListingCount;
	protected String hostVerification;
	
	
	public Hosts(int hostId, String hostUrl, String hostName, Date hostSince, String hostResponseTime,
			int hostResponseRate, int hostTotalListingCount, String hostVerification) {
		this.hostId = hostId;
		this.hostUrl = hostUrl;
		this.hostName = hostName;
		this.hostSince = hostSince;
		this.hostResponseTime = hostResponseTime;
		this.hostResponseRate = hostResponseRate;
		this.hostTotalListingCount = hostTotalListingCount;
		this.hostVerification = hostVerification;
	}
	
	
	public Hosts(int hostId) {
		this.hostId = hostId;
	}
	
	
	
	public int getHostId() {
		return hostId;
	}
	public void setHostId(int hostId) {
		this.hostId = hostId;
	}
	public String getHostUrl() {
		return hostUrl;
	}
	public void setHostUrl(String hostUrl) {
		this.hostUrl = hostUrl;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public Date getHostSince() {
		return hostSince;
	}
	public void setHostSince(Date hostSince) {
		this.hostSince = hostSince;
	}
	public String getHostResponseTime() {
		return hostResponseTime;
	}
	public void setHostResponseTime(String hostResponseTime) {
		this.hostResponseTime = hostResponseTime;
	}
	public int getHostResponseRate() {
		return hostResponseRate;
	}
	public void setHostResponseRate(int hostResponseRate) {
		this.hostResponseRate = hostResponseRate;
	}
	public int getHostTotalListingCount() {
		return hostTotalListingCount;
	}
	public void setHostTotalListingCount(int hostTotalListingCount) {
		this.hostTotalListingCount = hostTotalListingCount;
	}
	public String getHostVerification() {
		return hostVerification;
	}
	public void setHostVerification(String hostVerification) {
		this.hostVerification = hostVerification;
	}
	
	
	
	

}
