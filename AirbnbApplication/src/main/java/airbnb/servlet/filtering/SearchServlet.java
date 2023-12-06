package airbnb.servlet.filtering;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import airbnb.dao.HostsDao;
import airbnb.dao.ListingsDao;
import airbnb.model.Hosts;
import airbnb.model.ListingFilter;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PARAM_HOST_NAME = "hostName";
	private static final String PARAM_RESPONSE_TIME = "hostResponseTime";
	private static final String PARAM_RESPONSE_RATE = "hostResponseRate";
	private static final String PARAM_TOTAL_LISTING_COUNT = "hostTotalListingCount";
	private static final String PARAM_VERIFICATION = "hostVerification";
	private static final String PARAM_AMENITIES = "amenities";
	private static final String PARAM_PROPERTY_LOCATION = "propertyLocation";
	
	private Map<String, String> responseTimeMap = Map.of(
	        "within_a_day", "within a day",
	        "within_a_few_hours", "within a few hours",
	        "within_an_hour", "within an hour",
	        "a_few_days_or_more", "a few days or more"
	);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
        request.getRequestDispatcher("/search.jsp").forward(request, response);
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Hosts> filteredHosts = null;
		List<ListingFilter> filteredListingsList = null;
		
        try {
        	
        	filteredHosts = getHostFilteredResults(request);
        	filteredListingsList = getFilteredListings(request);
        	
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
      
       
        request.setAttribute("hosts", filteredHosts);
        request.setAttribute("listings", filteredListingsList);
        
        request.getRequestDispatcher("/search.jsp").forward(request, response);
	}

	private List<ListingFilter> getFilteredListings(HttpServletRequest request) throws SQLException {
		String propertyAmenities = request.getParameter(PARAM_AMENITIES);
		String propertyLocation = request.getParameter(PARAM_PROPERTY_LOCATION);
		ListingsDao listingsDao = ListingsDao.getInstance();
		
		List<ListingFilter> filteredListingsResult = listingsDao.getAllListingWithOtherTablesInfo();
		boolean hasFilter = false;
		if(propertyAmenities != null && !propertyAmenities.trim().isEmpty()) {
			hasFilter = true;
			filteredListingsResult = filteredListingsResult.stream()
			        .filter(listing -> listing.getAmenities().toLowerCase().contains(propertyAmenities.toLowerCase()))
			        .toList();
		}
		
		if (propertyLocation != null && !propertyLocation.trim().isEmpty()) {
		    hasFilter = true;
		    filteredListingsResult = filteredListingsResult.stream()
		        .filter(listing -> 
		            listing.getStreet().toLowerCase().contains(propertyLocation.toLowerCase()) ||
		            listing.getNeighborhoodCleansed().toLowerCase().contains(propertyLocation.toLowerCase()) ||
		            listing.getCity().toLowerCase().contains(propertyLocation.toLowerCase())
		        )
		        .collect(Collectors.toList());
		}

		return hasFilter ? filteredListingsResult : null;
	}

	private List<Hosts> getHostFilteredResults(HttpServletRequest request) throws SQLException {
		String hostName = request.getParameter(PARAM_HOST_NAME);
        String hostResponseTime = request.getParameter(PARAM_RESPONSE_TIME);
        String hostResponseRate = request.getParameter(PARAM_RESPONSE_RATE);
        String hostTotalListingCount = request.getParameter(PARAM_TOTAL_LISTING_COUNT);
        String hostVerification = request.getParameter(PARAM_VERIFICATION);
	     
        HostsDao hostDao = HostsDao.getInstance();
    	boolean hasFilter = false;
        List<Hosts> filteredHosts = hostDao.getAllHosts();
        
    	if (hostName != null && !hostName.trim().isEmpty()) {
    		hasFilter = true;
    	    filteredHosts = filteredHosts.stream()
    	            .filter(host -> hostName.equalsIgnoreCase(host.getHostName()))
    	            .toList();
    	}
    	
    	String responseTimeStr = responseTimeMap.getOrDefault(hostResponseTime, "");
    	if (!responseTimeStr.isEmpty()) {
    		hasFilter = true;
    	    filteredHosts = filteredHosts.stream()
    	            .filter(host -> responseTimeStr.equalsIgnoreCase(host.getHostResponseTime()))
    	            .toList();
    	}
    	
    	if (hostResponseRate != null && !hostResponseRate.trim().isEmpty()) {
    		hasFilter = true;
    	    int rate = Integer.parseInt(hostResponseRate);
    	    filteredHosts = filteredHosts.stream()
    	            .filter(host -> rate == host.getHostResponseRate())
    	            .toList();
    	}
			
		if(hostTotalListingCount != null && !hostTotalListingCount.trim().isEmpty()) {
			hasFilter = true;
			int count = Integer.parseInt(hostTotalListingCount);
			 filteredHosts = filteredHosts.stream()
                     .filter(host -> count == host.getHostTotalListingCount())
                     .toList();
		}
			
		if(hostVerification != null && !hostVerification.trim().isEmpty()) {
			hasFilter = true;
			filteredHosts = filteredHosts.stream()
			        .filter(host -> host.getHostVerification().toLowerCase().contains(hostVerification.toLowerCase()))
			        .toList();
		}
	        
		return hasFilter ? filteredHosts : null;
	}

}