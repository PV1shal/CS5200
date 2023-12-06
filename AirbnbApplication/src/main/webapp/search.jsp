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
	            <label for="amenities">Amenities:</label>
	            <input type="text" name="amenities" class="form-control" value="${fn:escapeXml(param.amenities)}">
	        </div>
	        
	        <div class="form-group">
	            <label for="propertyLocation">Property location:</label>
	            <input type="text" name="propertyLocation" class="form-control" value="${fn:escapeXml(param.propertyLocation)}">
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