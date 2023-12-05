package airbnb.model.insights;

/**
 * The `AveragePrice` class represents the average price of listings.
 * It includes information about the average price value.
 *
 * @author ambikakabra
 */
public class AveragePrice {
	private double avgPrice;

	//constructors
	public AveragePrice() {}
	
    public AveragePrice(double avgPrice) {
		super();
		this.avgPrice = avgPrice;
	}

    //getters and setters
	public double getAvgPrice() {
		return avgPrice;
	}


	public void setAvgPrice(double avgPrice) {
		this.avgPrice = avgPrice;
	}

	/**
     * Returns a string representation of the `AveragePrice` object.
     *
     * @return A string representation of the `AveragePrice` object.
     */
	@Override
    public String toString() {
        return "AveragePrice{" +
               "avgPrice=" + avgPrice +
               '}';
    }
}
