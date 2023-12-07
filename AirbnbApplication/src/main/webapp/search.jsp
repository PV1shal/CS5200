<%@ page import="airbnb.model.Hosts" %>
<%@ page import="airbnb.model.ListingFilter" %>
<%@ page import="airbnb.model.ListingRestrictions.CancellationPolicy" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Filter</title>
        <!-- Bootstrap -->
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<style>
		/* Style for host-specific fields */
        .host-fields {
            display: none;
        }
        
        /* Style for host-specific fields */
        .listing-fields {
            display: none;
        }
        /* Style for form container */
        .form-container {
            margin: auto;
        }

        /* Style for form labels */
        .form-label {
            max-width: 30%;
            margin-right: 10px;
            display: inline-block;
            margin-top: 10px;
        }
        
        /* Style for form submit button */
        .form-submit {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
    </style>
   
</head>
<body class="bg-dark text-white">
	<nav class="navbar navbar-expand-lg" style="background-color: #0a0a0b; height: 80px">
	    <div class="container-fluid">
	        <a class="navbar-brand me-3" href="/AirbnbApplication/home" style="color: white">
	            <img src="resources/Logo.png" width="40" height="40">
	            VizPro
	        </a>
	        <div class="d-flex ms-3">
	        	<a class="btn btn-outline-primary" href="/AirbnbApplication/home" role="button">Back to home</a>
	        </div>
	    </div>
	</nav>
    <div class="container-fluid mx-auto m-3 custom-outer-container">
    	<h2>Filter</h2>
        <div class="form-check">
		  <input class="form-check-input" type="radio" name="flexRadioDefault" id="listingRadio" ${fn:length(listings) > 0 ? 'checked' : ''}>
		  <label class="form-check-label" for="listingRadio">
		   Listings
		  </label>
		</div>
		<div class="form-check">
		  <input class="form-check-input" type="radio" name="flexRadioDefault" id="hostRadio" ${fn:length(hosts) > 0 ? 'checked' : ''} >
		  <label class="form-check-label" for="hostRadio">
		    Hosts
		  </label>
		</div>
    	<div class="form-container">
	    <form action="/AirbnbApplication/search" method="post" class="mb-4">
	        <div id="hostFields" class="host-fields">
	       
		        <div class="form-label">
		            <label for="hostName">Host Name:</label>
		            <input type="text" name="hostName" class="form-control" value="${fn:escapeXml(param.hostName)}">
		        </div>
		
		        <div class="form-label">
		            <label for="hostResponseTime">Host Response Time:</label>
		            <select name="hostResponseTime" class="form-control">
		                <option value="" ${empty param.hostResponseTime ? 'selected' : ''}>Select</option>
		                <option value="within_a_day" ${param.hostResponseTime == 'within_a_day' ? 'selected' : ''}>Within a Day</option>
		                <option value="within_a_few_hours" ${param.hostResponseTime == 'within_a_few_hours' ? 'selected' : ''}>Within a Few Hours</option>
		                <option value="within_an_hour" ${param.hostResponseTime == 'within_an_hour' ? 'selected' : ''}>Within an Hour</option>
		                <option value="a_few_days_or_more" ${param.hostResponseTime == 'a_few_days_or_more' ? 'selected' : ''}>A Few Days or More</option>
		            </select>
		        </div>
			
		        <div class="form-label">
		            <label for="hostResponseRate">Host Response Rate:</label>
		            <input type="text" name="hostResponseRate" class="form-control" value="${fn:escapeXml(param.hostResponseRate)}">
		        </div>
		
		        <div class="form-label">
		            <label for="hostTotalListingCount">Host Total Listing Count:</label>
		            <input type="text" name="hostTotalListingCount" class="form-control" value="${fn:escapeXml(param.hostTotalListingCount)}">
		        </div>
		
		        <div class="form-label">
		            <label for="hostVerification">Host Verification:</label>
		            <input type="text" name="hostVerification" class="form-control" value="${fn:escapeXml(param.hostVerification)}">
		        </div>
	        </div>

	        <div id="listingFields" class="listing-fields">
	        	<div class="form-label">
		            <label for="listingName">Listing Name:</label>
		            <input type="text" name="listingName" class="form-control" value="${fn:escapeXml(param.listingName)}">
		        </div>
	        	<div class="form-label">
		            <label for="amenities">Amenities:</label>
		            <input type="text" name="amenities" class="form-control" value="${fn:escapeXml(param.amenities)}">
		        </div>
	        
		        <div class="form-label">
		            <label for="propertyLocation">Property location:</label>
		            <input type="text" name="propertyLocation" class="form-control" value="${fn:escapeXml(param.propertyLocation)}">
		        </div>
	        
		        <div class="form-label">
		            <label for="propertyType">Property type:</label>
		            <select name="propertyType" class="form-control">
		                <option value="" ${empty param.propertyType ? 'selected' : ''}>Select</option>
		                <option value="cave" ${param.propertyType == 'cave' ? 'selected' : ''}>Cave</option>
		                <option value="earth_house" ${param.propertyType == 'earth_house' ? 'selected' : ''}>Earth House</option>
		                <option value="entire_floor" ${param.propertyType == 'entire_floor' ? 'selected' : ''}>Entire Floor</option>
		                <option value="tipi" ${param.propertyType == 'tipi' ? 'selected' : ''}>Tipi</option>
		                <option value="train" ${param.propertyType == 'train' ? 'selected' : ''}>Train</option>
		                <option value="tree_house" ${param.propertyType == 'tree_house' ? 'selected' : ''}>Tree House</option>
		                <option value="apartment" ${param.propertyType == 'apartment' ? 'selected' : ''}>Apartment</option>
		                <option value="house" ${param.propertyType == 'house' ? 'selected' : ''}>House</option>
		                <option value="bed_breakfast" ${param.propertyType == 'bed_breakfast' ? 'selected' : ''}>Bed and Breakfast</option>
		                <option value="heritage_hotel" ${param.propertyType == 'heritage_hotel' ? 'selected' : ''}>Heritage Hotel</option>
		                <option value="hotel" ${param.propertyType == 'hotel' ? 'selected' : ''}>Hotel</option>
		                <option value="hut" ${param.propertyType == 'hut' ? 'selected' : ''}>Hut</option>
		                <option value="vacation_home" ${param.propertyType == 'vacation_home' ? 'selected' : ''}>Vacation Home</option>
		                <option value="condominium" ${param.propertyType == 'condominium' ? 'selected' : ''}>Condominium</option>
		                <option value="boat" ${param.propertyType == 'boat' ? 'selected' : ''}>Boat</option>
		                <option value="villa" ${param.propertyType == 'villa' ? 'selected' : ''}>Villa</option>
		                <option value="castle" ${param.propertyType == 'castle' ? 'selected' : ''}>Castle</option>
		                <option value="igloo" ${param.propertyType == 'igloo' ? 'selected' : ''}>Igloo</option>
		                <option value="in_law" ${param.propertyType == 'in_law' ? 'selected' : ''}>In-law</option>
		                <option value="island" ${param.propertyType == 'island' ? 'selected' : ''}>Island</option>
		                <option value="lighthouse" ${param.propertyType == 'lighthouse' ? 'selected' : ''}>Lighthouse</option>
		                <option value="van" ${param.propertyType == 'van' ? 'selected' : ''}>Van</option>
		                <option value="yurt" ${param.propertyType == 'yurt' ? 'selected' : ''}>Yurt</option>
		                <option value="townhouse" ${param.propertyType == 'townhouse' ? 'selected' : ''}>Townhouse</option>
		                <option value="loft" ${param.propertyType == 'loft' ? 'selected' : ''}>Loft</option>
		                <option value="cabin" ${param.propertyType == 'cabin' ? 'selected' : ''}>Cabin</option>
		                <option value="nature_lodge" ${param.propertyType == 'nature_lodge' ? 'selected' : ''}>Nature Lodge</option>
		                <option value="parking_space" ${param.propertyType == 'parking_space' ? 'selected' : ''}>Parking Space</option>
		                <option value="pension" ${param.propertyType == 'pension' ? 'selected' : ''}>Pension</option>
		                <option value="car" ${param.propertyType == 'car' ? 'selected' : ''}>Car</option>
		                <option value="boutique_hotel" ${param.propertyType == 'boutique_hotel' ? 'selected' : ''}>Boutique Hotel</option>
		                <option value="bungalow" ${param.propertyType == 'bungalow' ? 'selected' : ''}>Bungalow</option>
		                <option value="camper_rv" ${param.propertyType == 'camper_rv' ? 'selected' : ''}>Camper/RV</option>
		                <option value="plane" ${param.propertyType == 'plane' ? 'selected' : ''}>Plane</option>
		                <option value="ryokan" ${param.propertyType == 'ryokan' ? 'selected' : ''}>Ryokan</option>
		                <option value="tent" ${param.propertyType == 'tent' ? 'selected' : ''}>Tent</option>
		                <option value="timeshare" ${param.propertyType == 'timeshare' ? 'selected' : ''}>Timeshare</option>
		                <option value="casa_particular" ${param.propertyType == 'casa_particular' ? 'selected' : ''}>Casa Particular</option>
		                <option value="hostel" ${param.propertyType == 'hostel' ? 'selected' : ''}>Hostel</option>
		                <option value="dorm" ${param.propertyType == 'dorm' ? 'selected' : ''}>Dorm</option>
		                <option value="guesthouse" ${param.propertyType == 'guesthouse' ? 'selected' : ''}>GuestHouse</option>
		                <option value="guest_suite" ${param.propertyType == 'guest_suite' ? 'selected' : ''}>Guest Suite</option>
		                <option value="serviced_apartment" ${param.propertyType == 'serviced_apartment' ? 'selected' : ''}>Serviced Apartment</option>
		                <option value="chalet" ${param.propertyType == 'chalet' ? 'selected' : ''}>Chalet</option>
		                <option value="other" ${param.propertyType == 'other' ? 'selected' : ''}>Other</option>   
		            </select>
		        </div>
		        <div class="form-label">
		            <label for="propertyPrice">Property price:</label>
		            <input type="text" name="propertyPrice" class="form-control" value="${fn:escapeXml(param.propertyPrice)}">
		        </div>
		        
		        <div class="form-label">
		            <label for="securityDeposit">Property security deposit:</label>
		            <input type="text" name="securityDeposit" class="form-control" value="${fn:escapeXml(param.securityDeposit)}">
		        </div>
		        
		        <div class="form-label">
		            <label for="cleaningFee">Property cleaning fee:</label>
		            <input type="text" name="cleaningFee" class="form-control" value="${fn:escapeXml(param.cleaningFee)}">
		        </div>
		        
		        <div class="form-label">
		            <label for="cancellationPolicy">Cancellation Policy:</label>
		            <select name="cancellationPolicy" class="form-control">
					    <option value="" ${empty param.cancellationPolicy ? 'selected' : ''}>Select</option>
					    <option value="STRICT" ${param.cancellationPolicy == 'STRICT' ? 'selected' : ''}>STRICT</option>
					    <option value="MODERATE" ${param.cancellationPolicy == 'MODERATE' ? 'selected' : ''}>MODERATE</option>
					    <option value="FLEXIBLE" ${param.cancellationPolicy == 'FLEXIBLE' ? 'selected' : ''}>FLEXIBLE</option>
					    <option value="SUPER_STRICT_60" ${param.cancellationPolicy == 'SUPER_STRICT_60' ? 'selected' : ''}>SUPER_STRICT_60</option>
					    <option value="SUPER_STRICT_60_NEW" ${param.cancellationPolicy == 'SUPER_STRICT_60_NEW' ? 'selected' : ''}>SUPER_STRICT_60_NEW</option>
					    <option value="SUPER_STRICT_30" ${param.cancellationPolicy == 'SUPER_STRICT_30' ? 'selected' : ''}>SUPER_STRICT_30</option>
					    <option value="SUPER_STRICT_30_NEW" ${param.cancellationPolicy == 'SUPER_STRICT_30_NEW' ? 'selected' : ''}>SUPER_STRICT_30_NEW</option>
					    <option value="STRICT_NEW" ${param.cancellationPolicy == 'STRICT_NEW' ? 'selected' : ''}>STRICT_NEW</option>
					    <option value="FLEXIBLE_NEW" ${param.cancellationPolicy == 'FLEXIBLE_NEW' ? 'selected' : ''}>FLEXIBLE_NEW</option>
					    <option value="MODERATE_NEW" ${param.cancellationPolicy == 'MODERATE_NEW' ? 'selected' : ''}>MODERATE_NEW</option>
					    <option value="LONG_TERM" ${param.cancellationPolicy == 'LONG_TERM' ? 'selected' : ''}>LONG_TERM</option>
					    <option value="NO_REFUNDS" ${param.cancellationPolicy == 'NO_REFUNDS' ? 'selected' : ''}>NO_REFUNDS</option>
					</select>
		        </div>
		        
		        <div class="form-label">
		            <label for="guestsIncluded">Guests included:</label>
		            <input type="text" name="guestsIncluded" class="form-control" value="${fn:escapeXml(param.guestsIncluded)}">
		        </div>
		        
		        <div class="form-label">
		            <label for="minimumNights">Minimum Nights:</label>
		            <input type="text" name="minimumNights" class="form-control" value="${fn:escapeXml(param.minimumNights)}">
		        </div>
		        
		        <div class="form-label">
		            <label for="maximumNights">Maximum Nights:</label>
		            <input type="text" name="maximumNights" class="form-control" value="${fn:escapeXml(param.maximumNights)}">
		        </div>
		        
		         <div class="form-label">
		            <label for="roomType">Room Type:</label>
		            <select name="roomType" class="form-control">
		                <option value="" ${empty param.roomType ? 'selected' : ''}>Select</option>
		                <option value="ENTIRE_HOME_APT" ${param.roomType == 'ENTIRE_HOME_APT' ? 'selected' : ''}>ENTIRE HOME/APT</option>
		                <option value="PRIVATE_ROOM" ${param.roomType == 'PRIVATE_ROOM' ? 'selected' : ''}>PRIVATE ROOM</option>
		                <option value="SHARED_ROOM" ${param.roomType == 'SHARED_ROOM' ? 'selected' : ''}>SHARED ROOM</option>
		            </select>
		        </div>
		        
		         <div class="form-label">
		            <label for="bedType">Bed Type:</label>
		            <select name="bedType" class="form-control">
		                <option value="" ${empty param.bedType ? 'selected' : ''}>Select</option>
		                <option value="REAL_BED" ${param.bedType == 'REAL_BED' ? 'selected' : ''}>REAL BED</option>
		                <option value="PULL_OUT_SOFA" ${param.bedType == 'PULL_OUT_SOFA' ? 'selected' : ''}>PULL-OUT SOFA</option>
		                <option value="FUTON" ${param.bedType == 'FUTON' ? 'selected' : ''}>FUTON</option>
		                <option value="AIRBED" ${param.bedType == 'AIRBED' ? 'selected' : ''}>AIRBED</option>
		                <option value="COUCH" ${param.bedType == 'COUCH' ? 'selected' : ''}>COUCH</option>
		            </select>
		        </div>
		        
		         <div class="form-label">
		            <label for="bedrooms">Bedrooms:</label>
		            <input type="text" name="bedrooms" class="form-control" value="${fn:escapeXml(param.bedrooms)}">
		        </div>
		        
		         <div class="form-label">
		            <label for="beds">Beds:</label>
		            <input type="text" name="beds" class="form-control" value="${fn:escapeXml(param.beds)}">
		        </div>
		        
		        <div class="form-label">
		            <label for="bathrooms">Bathrooms:</label>
		            <input type="text" name="bathrooms" class="form-control" value="${fn:escapeXml(param.bathrooms)}">
		        </div>
		        
		        <div class="form-label">
		            <label for="accommodates">Accommodates:</label>
		            <input type="text" name="accommodates" class="form-control" value="${fn:escapeXml(param.accommodates)}">
		        </div>
	        </div>
	        <button type="submit" class="btn btn-primary mt-2">Filter</button>
	    </form>
	    </div>
	
	    <h3>Search Results</h3>
	    <div class="table-responsive">
	    	<table class="table table-bordered text-white">
		        <thead>
		          <c:choose>
	           			<c:when test="${fn:length(hosts) > 0}">
			            <tr>
			                <th scope="col">Host Name</th>
			                <th scope="col">Host Response Time</th>
			                <th scope="col">Host Response Rate</th>
			                <th scope="col">Host Total Listing Count</th>
			                <th scope="col">Host Verification</th>
			               
			            </tr>
			            </c:when>
		            </c:choose>
		        </thead>
		        <tbody>
		            <c:forEach var="host" items="${hosts}" varStatus="loop">
		                <tr>
		                    <td><c:out value="${host.getHostName()}" /></td>
		                    <td><c:out value="${host.getHostResponseTime()}" /></td>
		                    <td><c:out value="${host.getHostResponseRate()}" /></td>
		                    <td><c:out value="${host.getHostTotalListingCount()}" /></td>
		                    <td class="text-truncate" style="max-width:300px" title="<c:out value="${host.getHostVerification()}" />"><c:out value="${host.getHostVerification()}" /></td>
		                </tr>
		            </c:forEach>
		        </tbody>
	    	</table>
	    </div>
		<div class="table-responsive">
	    	<table class="table table-bordered text-white">
		        <thead>
		        	<c:choose>
	           			<c:when test="${fn:length(listings) > 0}">
				            <tr>
				                <th scope="col">Listing Name</th>
				                <th scope="col">Amenities</th>
				               	<th scope="col">Location</th>
				               	<th scope="col">Property Type</th>          
								<th scope="col">Price</th>
								<th scope="col">Security Deposit</th>
								<th scope="col">Cleaning fee</th>
								<th scope="col">Cancellation Policy</th>
								<th scope="col">Guests Included</th>
								<th scope="col">Maximum nights</th>
								<th scope="col">Minimum nights</th>
								<th scope="col">Room Type</th>
								<th scope="col">Bed Type</th>
								<th scope="col">Bedrooms</th>
								<th scope="col">Beds</th>
								<th scope="col">Bathrooms</th>
								<th scope="col">Accommodates</th>
				            </tr>
		            	</c:when>
		            </c:choose>
		        </thead>
		        <tbody>
		            <c:forEach var="listing" items="${listings}" varStatus="loop">
		                <tr>
		                    <td class="text-truncate" style="max-width:300px"  title="<c:out value="${listing.getListingName()}" />"><c:out value="${listing.getListingName()}" /></td>
		                	<td class="text-truncate" style="max-width:300px" title="<c:out value="${listing.getAmenities()}" />"><c:out value="${listing.getAmenities()}" /></td>
		                	<td class="text-truncate" style="max-width:300px" title="<c:out value="${listing.getStreet()}" />">
		                		Street: <c:out value="${listing.getStreet()}" /> <br/>
		                		Neighborhood: <c:out value="${listing.getNeighborhoodCleansed()}" /><br/>
		                		City: <c:out value="${listing.getCity()}" /><br/>
		                	</td>
		                	<td class="text-truncate">
		                		<c:out value="${listing.getPropertyType().getDisplayName()}" />
		                	</td>
		                	<td>
		                		<c:if test="${listing.getPrice() >= 0}">
		                			<c:out value="${listing.getPrice()}" />
		                		</c:if>
		                	</td>
		                	<td>
		                		<c:if test="${listing.getSecurityDeposit() >= 0}"> 
		                			<c:out value="${listing.getSecurityDeposit()}" />
		                		</c:if>
		                	</td>
		                	<td>
		                		<c:if test="${listing.getCleaningFee() >= 0}">
		                			<c:out value="${listing.getCleaningFee()}" />
		                		</c:if>
		                	</td> 
		                	<td>
		                		<c:out value="${listing.getCancellationPolicy()}" />
		                	</td>
		                	<td>
		                		<c:if test="${listing.getGuestsIncluded() >= 0}"> 
		                			<c:out value="${listing.getGuestsIncluded()}" />
		                		</c:if>
		                	</td>
		                	<td>
		                		<c:if test="${listing.getMaximumNights() >= 0}"> 
		                			<c:out value="${listing.getMaximumNights()}" />
		                		</c:if>
		                	</td>
		                	<td>
		                		<c:if test="${listing.getMinimumNights() >= 0}"> 
		                			<c:out value="${listing.getMinimumNights()}" />
		                		</c:if>
		                	</td>
		                	
		                	<td>
		                		<c:out value="${listing.getRoomType()}" />
		                	</td>
		                	<td>
		                		<c:out value="${listing.getBedtype()}" />
		                	</td>
		                	<td>
		                		<c:if test="${listing.getBedrooms() >= 0}"> 
		                			<c:out value="${listing.getBedrooms()}" />
		                		</c:if>
		                	</td>
		                	<td>
		                		<c:if test="${listing.getBeds() >= 0}"> 
		                			<c:out value="${listing.getBeds()}" />
		                		</c:if>
		                	</td>
		                	<td>
		                		<c:if test="${listing.getBathrooms() >= 0}"> 
		                			<c:out value="${listing.getBathrooms()}" />
		                		</c:if>
		                	</td>
		                	<td>
		                		<c:if test="${listing.getAccommodates() >= 0}"> 
		                			<c:out value="${listing.getAccommodates()}" />
		                		</c:if>
		                	</td>
		                </tr>
		            </c:forEach>
		        </tbody>
	    	</table>
	    </div>
    </div>

	<script>
        // Add this script to your HTML file
        window.onload = function() {
            // Check if the listing radio button is checked on window load
            if (document.getElementById('listingRadio').checked) {
            	
                toggleHostFields();
            }
            
            if (document.getElementById('hostRadio').checked) {
  
                toggleHostFields();
            }
        };

        function toggleHostFields() {
            var hostFields = document.getElementById("hostFields");
            var listingFields = document.getElementById("listingFields");
            hostFields.style.display = document.getElementById("hostRadio").checked ? "block" : "none";
            listingFields.style.display = document.getElementById("hostRadio").checked ? "none" : "block";
            
            hostFields.style.display = document.getElementById("listingRadio").checked ? "none" : "block";
            listingFields.style.display = document.getElementById("listingRadio").checked ? "block" : "none";
           
        }
        
	     // Function to clear form input fields
	        function clearFormFields() {
	        	 // Host-specific fields
	            document.getElementsByName('hostName')[0].value = '';
	            document.getElementsByName('hostResponseTime')[0].value = '';
	            document.getElementsByName('hostResponseRate')[0].value = '';
	            document.getElementsByName('hostTotalListingCount')[0].value = '';
	            document.getElementsByName('hostVerification')[0].value = '';

	            // Listing-specific fields
	            document.getElementsByName('propertyType')[0].value = '';
	            document.getElementsByName('listingName')[0].value = '';
	            document.getElementsByName('amenities')[0].value = '';
	            document.getElementsByName('propertyLocation')[0].value = '';
	            document.getElementsByName('propertyPrice')[0].value = '';
	            document.getElementsByName('securityDeposit')[0].value = '';
	            document.getElementsByName('cleaningFee')[0].value = '';
	            document.getElementsByName('cancellationPolicy')[0].value = '';
	            document.getElementsByName('guestsIncluded')[0].value = '';
	            document.getElementsByName('minimumNights')[0].value = '';
	            document.getElementsByName('maximumNights')[0].value = '';
	            document.getElementsByName('roomType')[0].value = '';
	            document.getElementsByName('bedType')[0].value = '';
	            document.getElementsByName('bedrooms')[0].value = '';
	            document.getElementsByName('beds')[0].value = '';
	            document.getElementsByName('bathrooms')[0].value = '';
	            document.getElementsByName('accommodates')[0].value = '';
	        }
       
        document.getElementById('listingRadio').addEventListener('click', function() {
        	
            toggleHostFields();
            clearFormFields();
        });
        
        document.getElementById('hostRadio').addEventListener('click', function() {
        	
            toggleHostFields();
            clearFormFields();
        });
    </script>
     <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>