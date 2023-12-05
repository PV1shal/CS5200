<!-- @author ambika kabra -->
<%@ page import="java.util.List" %>
<%@ page import="airbnb.model.insights.WirelessInternet" %>
<%@ page import="airbnb.model.insights.ListingCost" %>
<%@ page import="airbnb.model.insights.AmenitiesCount" %>
<%@ page import="airbnb.model.insights.AveragePrice" %>
<%@ page import="airbnb.model.insights.RoomTypeCount" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
    <title>Listing Insights</title>
    <!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-dark">
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
	
	<ul class="nav nav-tabs mt-4">
	    <li class="nav-item">
	        <a class="nav-link active" id="topExpensiveListingTab" data-toggle="tab" href="#topExpensiveListing">Top Neighborhoods</a>
	    </li>
	     <li class="nav-item">
	        <a class="nav-link" id="wirelessInternetAmenitiesTab" data-toggle="tab" href="#wirelessInternetAmenities">Listings with wireless internet</a>
	    </li>
	     <li class="nav-item">
	        <a class="nav-link" id="amenitiesCountTab" data-toggle="tab" href="#amenitiesCount">Amenities with count</a>
	    </li>
	    <li class="nav-item">
	        <a class="nav-link" id="avgPriceTab" data-toggle="tab" href="#avgPrice">Average Price</a>
	    </li>
	    <li class="nav-item">
	        <a class="nav-link" id="roomTypeCountTab" data-toggle="tab" href="#roomTypeCount">Room type count in Seattle</a>
	    </li>
	</ul>
	<div class="tab-content mt-2">
		<div class="tab-pane fade show active" id="topExpensiveListing">
			<div class="container mx-auto m-3 custom-outer-container">
				<h2 class="text-white">Top 10 neighborhoods and their countries with the most expensive average listing price (Price + security
		deposit + and cleaning fee) of the APARTMENT PropertyType</h2>
				<table class="table table-bordered table-dark">
		            <thead>
		                <tr>
		                    <th scope="col">Neighborhood</th>
		                    <th scope="col">Country</th>
		                    <th scope="col">Listing Cost</th>
		                </tr>
		            </thead>
		            <tbody>
		                <c:forEach items="${expensiveNeighborhoodList}" varStatus="loop" var="expensiveNeighborhood" >
							<tr>
							    <td>
							    	<c:out value="${expensiveNeighborhood.getListingNeighborhood()}" />
							</td>
							 <td>
							 	<c:out value="${expensiveNeighborhood.getListingCountry()}" />
							</td>
							<td>
								<c:out value="${expensiveNeighborhood.getListingCost()}" />
							    </td>
							</tr>
						</c:forEach>
		           </tbody>
		       </table>
			</div>
		</div>
		<div class="tab-pane fade" id="wirelessInternetAmenities">
			<div class="container mx-auto m-3 custom-outer-container">
				<h2 class="text-white">Listings name, city, country have 'Wireless Internet' among their amenities, and have received perfect ratings (a score of 10) for cleanliness, check-in, location, and communication in their reviews</h2>
				<table class="table table-bordered table-dark">
		            <thead>
		                <tr>
		                	<th scope="col">Name</th>
		                	<th scope="col">City</th>
		                    <th scope="col">Country</th>
		                </tr>
		            </thead>
		            <tbody>
		                <c:forEach items="${wirelessInternetList}" varStatus="loop" var="wirelessInternet" >
							<tr>
								<td>
									<c:out value="${wirelessInternet.getName()}" />
							    </td>
							    <td>
							    	<c:out value="${wirelessInternet.getCity()}" />
								</td>
							 	<td>
							 		<c:out value="${wirelessInternet.getCountry()}" />
								</td>
							</tr>
						</c:forEach>
		           </tbody>
		       </table>
			</div>
		</div>
		<div class="tab-pane fade" id="amenitiesCount">
			<div class="container mx-auto m-3 custom-outer-container">
				<h2 class="text-white">10 most common amenities and their count</h2>
				<table class="table table-bordered table-dark">
		            <thead>
		                <tr>
		                	<th scope="col">Amenity</th>
                    		<th scope="col">Count</th>
		                </tr>
		            </thead>
		            <tbody>
		                <c:forEach items="${amenitiesCountList}" varStatus="loop" var="amenitiesCount" >
							<tr>
								<td>
									<c:out value="${amenitiesCount.getAmenity()}" />
							    </td>
							    <td>
							    	<c:out value="${amenitiesCount.getAmenityCount()}" />
								</td>
							</tr>
						</c:forEach>
		           </tbody>
		       </table>
			</div>
		</div>
		<div class="tab-pane fade" id="avgPrice">
			<div class="container mx-auto m-3 custom-outer-container">
				<h2 class="text-white">Average Price of Listings with More Than 5 Reviews</h2>
				<table class="table table-bordered table-dark">
		            <thead>
		                <tr>
		                	<th scope="col">Average Price</th>
		                </tr>
		            </thead>
		            <tbody>
						<tr>
							<td>
								${avgPrice.getAvgPrice()}
						    </td>
						  
						</tr>
						
		           </tbody>
		       </table>
			</div>
		</div>
		<div class="tab-pane fade" id="roomTypeCount">
			<div class="container mx-auto m-3 custom-outer-container">
				<table class="table table-bordered table-dark">
		            <thead>
		                <tr>
		                    <th scope="col">Room Type</th>
		                    <th scope="col">Room Type Count</th>
		                </tr>
		            </thead>
		            <tbody>
		                <tr>
		                    <td>${roomTypeCount.getRoomType()}</td>
		                    <td>${roomTypeCount.getRoomTypeCount()}</td>
		                </tr>
		            </tbody>
		        </table>
			</div>
		</div>
	</div>
     <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>
