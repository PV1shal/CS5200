package airbnb.servlet.filtering;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
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
	private static final String PARAM_PROPERTYTYPE = "propertyType";
	private static final String PARAM_AMENITIES = "amenities";
	private static final String PARAM_PROPERTY_LOCATION = "propertyLocation";
	private static final String PARAM_LISTING_NAME = "listingName";
	private static final String PARAM_PRICE = "propertyPrice";
	private static final String PARAM_SECURITY_DEPOSIT = "securityDeposit";
	private static final String PARAM_CLEANING_FEE = "cleaningFee";
	private static final String PARAM_CANCELLATION_POLICY = "cancellationPolicy";
	private static final String PARAM_GUESTS_INCLUDED = "guestsIncluded";
	private static final String PARAM_MINIMUM_NIGHTS = "minimumNights";
	private static final String PARAM_MAXIMUM_NIGHTS = "maximumNights";
	private static final String PARAM_ACCOMMODATES = "accommodates";
	private static final String PARAM_BATHROOMS = "bathrooms";
	private static final String PARAM_BEDROOMS = "bedrooms";
	private static final String PARAM_BEDS = "beds";
	private static final String PARAM_ROOM_TYPE = "roomType";
	private static final String PARAM_BEDTYPE = "bedtype";
	
	private static final Map<String, String> responseTimeMap = Map.of(
	        "within_a_day", "within a day",
	        "within_a_few_hours", "within a few hours",
	        "within_an_hour", "within an hour",
	        "a_few_days_or_more", "a few days or more"
	);
	private static final Map<String, String> bedTypeMap = Map.of(
            "REAL_BED", "REAL BED",
            "PULL_OUT_SOFA", "PULL-OUT SOFA",
            "FUTON", "FUTON",
            "AIRBED", "AIRBED",
            "COUCH", "COUCH"
    );
	
	private static final Map<String, String> roomTypeMap = Map.of(
            "ENTIRE_HOME_APT", "ENTIRE HOME/APT",
            "PRIVATE_ROOM", "PRIVATE ROOM",
            "SHARED_ROOM", "SHARED ROOM"
    );
	
	private HashMap<String, String> propertyTypeMap = new HashMap<>();
	protected void populateMap() {
		propertyTypeMap.put("CAVE", "CAVE");
		propertyTypeMap.put("EARTH_HOUSE", "EARTH HOUSE");
		propertyTypeMap.put("ENTIRE_FLOOR", "ENTIRE FLOOR");
	    propertyTypeMap.put("TIPI", "TIPI");
	    propertyTypeMap.put("TRAIN", "TRAIN");
	    propertyTypeMap.put("TREEHOUSE", "TREEHOUSE");
	    propertyTypeMap.put("APARTMENT", "APARTMENT");
	    propertyTypeMap.put("HOUSE", "HOUSE");
	    propertyTypeMap.put("BED_BREAKFAST", "BED & BREAKFAST");
	    propertyTypeMap.put("HERITAGE_HOTEL", "HERITAGE HOTEL (INDIA)");
	    propertyTypeMap.put("HOTEL", "HOTEL");
	   	propertyTypeMap.put("HUT", "HUT");
	   	propertyTypeMap.put("VACATION_HOME", "VACATION HOME");
	   	propertyTypeMap.put("CONDOMINIUM", "CONDOMINIUM");
	   	propertyTypeMap.put("BOAT", "BOAT");
	   	propertyTypeMap.put("VILLA", "VILLA");
	   	propertyTypeMap.put("CASTLE", "CASTLE");
	   	propertyTypeMap.put("IGLOO", "IGLOO");
	    propertyTypeMap.put("IN_LAW", "IN-LAW");
	   	propertyTypeMap.put("ISLAND", "ISLAND");
	   	propertyTypeMap.put("LIGHTHOUSE", "LIGHTHOUSE");
	    propertyTypeMap.put("VAN", "VAN");
	   	propertyTypeMap.put("YURT", "YURT");
	   	propertyTypeMap.put("TOWNHOUSE", "TOWNHOUSE");
	   	propertyTypeMap.put("LOFT", "LOFT");
	    propertyTypeMap.put("CABIN", "CABIN");
	   	propertyTypeMap.put("NATURE_LODGE", "NATURE LODGE");
	   	propertyTypeMap.put("PARKING_SPACE", "PARKING SPACE");
	   	propertyTypeMap.put("PENSION", "PENSION (KOREA)");
	    propertyTypeMap.put("CAR", "CAR");
	   	propertyTypeMap.put("BOUTIQUE_HOTEL", "BOUTIQUE HOTEL");
	   	propertyTypeMap.put("BUNGALOW", "BUNGALOW");
	   	propertyTypeMap.put("CAMPER_RV", "CAMPER/RV");
	   	propertyTypeMap.put("PLANE", "PLANE");
	    propertyTypeMap.put("RYOKAN", "RYOKAN (JAPAN)");
	   	propertyTypeMap.put("TENT", "TENT");
	   	propertyTypeMap.put("TIMESHARE", "TIMESHARE");
	   	propertyTypeMap.put("CASA_PARTICULAR", "CASA PARTICULAR");
	   	propertyTypeMap.put("HOSTEL", "HOSTEL");
	    propertyTypeMap.put("DORM", "DORM");
	   	propertyTypeMap.put("GUESTHOUSE", "GUESTHOUSE");
	   	propertyTypeMap.put("GUEST_SUITE", "GUEST SUITE");
	   	propertyTypeMap.put("SERVICED_APARTMENT", "SERVICED APARTMENT");
	   	propertyTypeMap.put("CHALET", "CHALET");
	    propertyTypeMap.put("OTHER", "OTHER");
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        populateMap();
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
		String listingName = request.getParameter(PARAM_LISTING_NAME);		
		String propertyType = request.getParameter(PARAM_PROPERTYTYPE);
		String propertyAmenities = request.getParameter(PARAM_AMENITIES);
		String propertyLocation = request.getParameter(PARAM_PROPERTY_LOCATION);
		String propertyPrice = request.getParameter(PARAM_PRICE);
		String securityDeposity = request.getParameter(PARAM_SECURITY_DEPOSIT);
		String cleaningFee = request.getParameter(PARAM_CLEANING_FEE);
		String cancellationPolicy = request.getParameter(PARAM_CANCELLATION_POLICY);
		String guestsIncluded = request.getParameter(PARAM_GUESTS_INCLUDED);
		String minimumNights = request.getParameter(PARAM_MINIMUM_NIGHTS);
		String maximumNights = request.getParameter(PARAM_MAXIMUM_NIGHTS);
		
		String bedtype = request.getParameter(PARAM_BEDTYPE);
		String roomType = request.getParameter(PARAM_ROOM_TYPE);
		String beds = request.getParameter(PARAM_BEDS);
		String bedrooms = request.getParameter(PARAM_BEDROOMS);
		String bathrooms = request.getParameter(PARAM_BATHROOMS);
		String accommodates = request.getParameter(PARAM_ACCOMMODATES);
	   
		ListingsDao listingsDao = ListingsDao.getInstance();
		
		List<ListingFilter> filteredListingsResult = (List<ListingFilter>) getServletContext().getAttribute("filteredListingsResult");
		
		if (filteredListingsResult == null) {
			filteredListingsResult = listingsDao.getAllListingWithOtherTablesInfo(); 
			// Store the data in the application scope
	        getServletContext().setAttribute("filteredListingsResult", filteredListingsResult);
		}
		
		boolean hasFilter = false;
		
		if(listingName != null && !listingName.trim().isEmpty()) {
			hasFilter = true;
			filteredListingsResult = filteredListingsResult.stream()
			        .filter(listing -> listing.getListingName().toLowerCase().contains(listingName.toLowerCase()))
			        .toList();
		}
	
		if (propertyType != null && !propertyType.trim().isEmpty()) {
			String responsePropertyType = propertyTypeMap.getOrDefault(propertyType.toUpperCase(), "");
			if (!responsePropertyType.isEmpty()) {
				hasFilter = true;
				filteredListingsResult = filteredListingsResult.stream()
						.filter(listing -> listing.getPropertyType() != null && responsePropertyType.equalsIgnoreCase(listing.getPropertyType().getDisplayName()))
						.toList();
			}
		}
		
		if(propertyAmenities != null && !propertyAmenities.trim().isEmpty()) {
			hasFilter = true;
			filteredListingsResult = filteredListingsResult.stream()
			        .filter(listing -> listing.getAmenities().toLowerCase().contains(propertyAmenities.toLowerCase()))
			        .collect(Collectors.toList());
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
		
		if (propertyPrice != null && !propertyPrice.trim().isEmpty()) {
		    hasFilter = true;
		   
    	    int price = Integer.parseInt(propertyPrice);
    	    
		    filteredListingsResult = filteredListingsResult.stream()
		        .filter(listing -> listing.getPrice() == price)
		        .collect(Collectors.toList());
		}
		
		if (securityDeposity != null && !securityDeposity.trim().isEmpty()) {
		    hasFilter = true;
		   
    	    int security = Integer.parseInt(securityDeposity);
    	    
		    filteredListingsResult = filteredListingsResult.stream()
		        .filter(listing -> listing.getSecurityDeposit() == security)
		        .collect(Collectors.toList());
		}
		
		if (cleaningFee != null && !cleaningFee.trim().isEmpty()) {
		    hasFilter = true;
		   
    	    int fee = Integer.parseInt(cleaningFee);
    	    
		    filteredListingsResult = filteredListingsResult.stream()
		        .filter(listing -> listing.getCleaningFee() == fee)
		        .collect(Collectors.toList());
		}
		
		if (cancellationPolicy != null && !cancellationPolicy.trim().isEmpty()) {
    		hasFilter = true;
    		filteredListingsResult = filteredListingsResult.stream()
    	            .filter(listing -> cancellationPolicy.equalsIgnoreCase(listing.getCancellationPolicy()))
    	            .collect(Collectors.toList());
    	}
		
		if (guestsIncluded != null && !guestsIncluded.trim().isEmpty()) {
		    hasFilter = true;
		   
    	    int guests = Integer.parseInt(guestsIncluded);
    	    
		    filteredListingsResult = filteredListingsResult.stream()
		        .filter(listing -> listing.getGuestsIncluded() == guests)
		        .collect(Collectors.toList());
		}
		
		if (minimumNights != null && !minimumNights.trim().isEmpty()) {
		    hasFilter = true;
		   
    	    int minNights = Integer.parseInt(minimumNights);
    	    
		    filteredListingsResult = filteredListingsResult.stream()
		        .filter(listing -> listing.getMinimumNights() == minNights)
		        .collect(Collectors.toList());
		}
		
		if (maximumNights != null && !maximumNights.trim().isEmpty()) {
		    hasFilter = true;
		   
    	    int maxNights = Integer.parseInt(maximumNights);
    	    
		    filteredListingsResult = filteredListingsResult.stream()
		        .filter(listing -> listing.getMaximumNights() == maxNights)
		        .collect(Collectors.toList());
		}
		
		if(bedtype != null && !bedtype.trim().isEmpty()) {
			String stringBedType = bedTypeMap.getOrDefault(bedtype, "");
	    	if (!stringBedType.isEmpty()) {
	    		hasFilter = true;
	    		filteredListingsResult = filteredListingsResult.stream()
	    	            .filter(listing -> stringBedType.equalsIgnoreCase(listing.getBedtype()))
	    	            .collect(Collectors.toList());
	    	}
		}
		
		if(roomType != null && !roomType.trim().isEmpty()) {
			String stringRoomType = roomTypeMap.getOrDefault(roomType, "");
	    	if (!stringRoomType.isEmpty()) {
	    		hasFilter = true;
	    		filteredListingsResult = filteredListingsResult.stream()
	    	            .filter(listing -> stringRoomType.equalsIgnoreCase(listing.getRoomType()))
	    	            .collect(Collectors.toList());
	    	}
		}
    	
    	
    	if (beds != null && !beds.trim().isEmpty()) {
		    hasFilter = true;
		   
    	    int bedNumber = Integer.parseInt(beds);
    	    
		    filteredListingsResult = filteredListingsResult.stream()
		        .filter(listing -> listing.getBeds() == bedNumber)
		        .collect(Collectors.toList());
		}
    	
    	if (bedrooms != null && !bedrooms.trim().isEmpty()) {
		    hasFilter = true;
		   
    	    int bedroomsNumber = Integer.parseInt(bedrooms);
    	    
		    filteredListingsResult = filteredListingsResult.stream()
		        .filter(listing -> listing.getBedrooms() == bedroomsNumber)
		        .collect(Collectors.toList());
		}
    	
    	if (bathrooms != null && !bathrooms.trim().isEmpty()) {
		    hasFilter = true;
		   
    	    float bathroomsFloat = Float.parseFloat(bathrooms);
    	    
		    filteredListingsResult = filteredListingsResult.stream()
		        .filter(listing -> listing.getBathrooms() == bathroomsFloat)
		        .collect(Collectors.toList());
		}
    	
    	if (accommodates != null && !accommodates.trim().isEmpty()) {
		    hasFilter = true;
		   
    	    int accommodatesNumber = Integer.parseInt(accommodates);
    	    
		    filteredListingsResult = filteredListingsResult.stream()
		        .filter(listing -> listing.getAccommodates() == accommodatesNumber)
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
        List<Hosts> filteredHosts = (List<Hosts>) getServletContext().getAttribute("filteredHosts");
	
		if (filteredHosts == null) {
			filteredHosts = hostDao.getAllHosts(); 
			// Store the data in the application scope
	        getServletContext().setAttribute("filteredHosts", filteredHosts);
		}
        
    	if (hostName != null && !hostName.trim().isEmpty()) {
    		hasFilter = true;
    	    filteredHosts = filteredHosts.stream()
    	            .filter(host -> hostName.equalsIgnoreCase(host.getHostName()))
    	            .collect(Collectors.toList());
    	}
    	
    	String responseTimeStr = responseTimeMap.getOrDefault(hostResponseTime, "");
    	if (!responseTimeStr.isEmpty()) {
    		hasFilter = true;
    	    filteredHosts = filteredHosts.stream()
    	            .filter(host -> responseTimeStr.equalsIgnoreCase(host.getHostResponseTime()))
    	            .collect(Collectors.toList());
    	}
    	
    	if (hostResponseRate != null && !hostResponseRate.trim().isEmpty()) {
    		hasFilter = true;
    	    int rate = Integer.parseInt(hostResponseRate);
    	    filteredHosts = filteredHosts.stream()
    	            .filter(host -> rate == host.getHostResponseRate())
    	            .collect(Collectors.toList());
    	}
			
		if(hostTotalListingCount != null && !hostTotalListingCount.trim().isEmpty()) {
			hasFilter = true;
			int count = Integer.parseInt(hostTotalListingCount);
			 filteredHosts = filteredHosts.stream()
                     .filter(host -> count == host.getHostTotalListingCount())
                     .collect(Collectors.toList());
		}
			
		if(hostVerification != null && !hostVerification.trim().isEmpty()) {
			hasFilter = true;
			filteredHosts = filteredHosts.stream()
			        .filter(host -> host.getHostVerification().toLowerCase().contains(hostVerification.toLowerCase()))
			        .collect(Collectors.toList());
		}
	        
		return hasFilter ? filteredHosts : null;
	}

}
