<%@ page import="airbnb.model.Hosts" %>
<%@ page import="airbnb.model.ListingFilter" %>
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
    <div class="container mx-auto m-3 custom-outer-container">
    	<h2>Filter</h2>
	    <form action="/AirbnbApplication/search" method="post" class="mb-4">
	        <div class="form-group">
	            <label for="hostName">Host Name:</label>
	            <input type="text" name="hostName" class="form-control" value="${fn:escapeXml(param.hostName)}">
	        </div>
	
	        <div class="form-group">
	            <label for="hostResponseTime">Host Response Time:</label>
	            <select name="hostResponseTime" class="form-control">
	                <option value="" ${empty param.hostResponseTime ? 'selected' : ''}>Select</option>
	                <option value="within_a_day" ${param.hostResponseTime == 'within_a_day' ? 'selected' : ''}>Within a Day</option>
	                <option value="within_a_few_hours" ${param.hostResponseTime == 'within_a_few_hours' ? 'selected' : ''}>Within a Few Hours</option>
	                <option value="within_an_hour" ${param.hostResponseTime == 'within_an_hour' ? 'selected' : ''}>Within an Hour</option>
	                <option value="a_few_days_or_more" ${param.hostResponseTime == 'a_few_days_or_more' ? 'selected' : ''}>A Few Days or More</option>
	            </select>
	        </div>
	
	        <div class="form-group">
	            <label for="hostResponseRate">Host Response Rate:</label>
	            <input type="text" name="hostResponseRate" class="form-control" value="${fn:escapeXml(param.hostResponseRate)}">
	        </div>
	
	        <div class="form-group">
	            <label for="hostTotalListingCount">Host Total Listing Count:</label>
	            <input type="text" name="hostTotalListingCount" class="form-control" value="${fn:escapeXml(param.hostTotalListingCount)}">
	        </div>
	
	        <div class="form-group">
	            <label for="hostVerification">Host Verification:</label>
	            <input type="text" name="hostVerification" class="form-control" value="${fn:escapeXml(param.hostVerification)}">
	        </div>
	        
	        <div class="form-group">
	            <label for="listingName">Listing Name:</label>
	            <input type="text" name="listingName" class="form-control" value="${fn:escapeXml(param.listingName)}">
	        </div>
	        
	        <div class="form-group">
	            <label for="amenities">Amenities:</label>
	            <input type="text" name="amenities" class="form-control" value="${fn:escapeXml(param.amenities)}">
	        </div>
	        
	        <div class="form-group">
	            <label for="propertyLocation">Property location:</label>
	            <input type="text" name="propertyLocation" class="form-control" value="${fn:escapeXml(param.propertyLocation)}">
	        </div>
	        
	        <div class="form-group">
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
	
	        <button type="submit" class="btn btn-primary">Filter</button>
	    </form>
	
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
										               
				            </tr>
		            	</c:when>
		            </c:choose>
		        </thead>
		        <tbody>
		            <c:forEach var="listing" items="${listings}" varStatus="loop">
		                <tr>
		                    <td class="text-truncate"><c:out value="${listing.getListingName()}" /></td>
		                	<td class="text-truncate" style="max-width:300px" title="<c:out value="${listing.getAmenities()}" />"><c:out value="${listing.getAmenities()}" /></td>
		                	<td class="text-truncate" style="max-width:300px" title="<c:out value="${listing.getStreet()}" />">
		                		Street: <c:out value="${listing.getStreet()}" /> <br/>
		                		Neighborhood: <c:out value="${listing.getNeighborhoodCleansed()}" /><br/>
		                		City: <c:out value="${listing.getCity()}" /><br/>
		                	</td>
		                	<td class="text-truncate"><c:out value="${listing.getPropertyType().getDisplayName()}" /></td>
		                </tr>
		            </c:forEach>
		        </tbody>
	    	</table>
	    </div>
    </div>

     <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>