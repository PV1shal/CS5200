package airbnb.model.insights;

/**
 * The `AvgAccommodatesByYear` class represents the average accommodates by year.
 * It includes information about the year and the corresponding average accommodates value.
 *
 * @author ambikakabra
 */
public class AvgAccommodatesByYear {
	private int year;
    private double averageAccommodates;

    // constructors
    public AvgAccommodatesByYear() {}
    
    public AvgAccommodatesByYear(int year, double averageAccommodates) {
		this.year = year;
		this.averageAccommodates = averageAccommodates;
	}
     
    //getters, and setters
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getAverageAccommodates() {
		return averageAccommodates;
	}

	public void setAverageAccommodates(double averageAccommodates) {
		this.averageAccommodates = averageAccommodates;
	}

	/**
     * Returns a string representation of the `AvgAccommodatesByYear` object.
     *
     * @return A string representation of the `AvgAccommodatesByYear` object.
     */
	@Override
    public String toString() {
        return "AvgAccommodatesByYear{" +
               "year=" + year +
               ", averageAccommodates=" + averageAccommodates +
               '}';
    }
}
