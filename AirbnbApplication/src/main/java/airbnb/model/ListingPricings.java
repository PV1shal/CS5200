package airbnb.model;

public class ListingPricings {
	protected Listings listing;
	protected int price;
	protected int weeklyPrice;
	protected int monthlyPrice;
	protected int securityDeposit;
	protected int cleaningFee;
	
	
	public ListingPricings(Listings listing, int price, int weeklyPrice, int monthlyPrice, int securityDeposit,
			int cleaningFee) {
		super();
		this.listing = listing;
		this.price = price;
		this.weeklyPrice = weeklyPrice;
		this.monthlyPrice = monthlyPrice;
		this.securityDeposit = securityDeposit;
		this.cleaningFee = cleaningFee;
	}


	public Listings getListing() {
		return listing;
	}


	public void setListing(Listings listing) {
		this.listing = listing;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public int getWeeklyPrice() {
		return weeklyPrice;
	}


	public void setWeeklyPrice(int weeklyPrice) {
		this.weeklyPrice = weeklyPrice;
	}


	public int getMonthlyPrice() {
		return monthlyPrice;
	}


	public void setMonthlyPrice(int montlyPrice) {
		this.monthlyPrice = montlyPrice;
	}


	public int getSecurityDeposit() {
		return securityDeposit;
	}


	public void setSecurityDeposit(int securityDeposit) {
		this.securityDeposit = securityDeposit;
	}


	public int getCleaningFee() {
		return cleaningFee;
	}


	public void setCleaningFee(int cleaningFee) {
		this.cleaningFee = cleaningFee;
	}
	
	
	
	
	
	
	
}