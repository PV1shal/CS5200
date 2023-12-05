<!-- @author ambika kabra -->
<%@ page import="java.util.List" %>
<%@ page import="airbnb.model.insights.ListingReview" %>
<%@ page import="airbnb.model.insights.CityEarnings" %>
<%@ page import="airbnb.model.insights.AvgAccommodatesByYear" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
    <title>Host Insights</title>
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
	        <a class="nav-link active" id="topHostsTab" data-toggle="tab" href="#topHosts">Top Hosts</a>
	    </li>
	     <li class="nav-item">
	        <a class="nav-link" id="topCitiesTab" data-toggle="tab" href="#topCities">Top Cities with responsive hosts</a>
	    </li>
	    <li class="nav-item">
	        <a class="nav-link" id="avgAccommodatesByYearTab" data-toggle="tab" href="#avgAccommodatesByYear">Average Accommodates By Year</a>
	    </li>
	</ul>
	<div class="tab-content mt-2">
		 <!-- Top Hosts Tab -->
		<div class="tab-pane fade show active" id="topHosts">
		    <!-- Content for the Top Hosts tab -->
		    <div class="container mx-auto m-3 custom-outer-container">
				<h2 class="text-white">Top 10 hosts with over 100 listings who have the highest average ReviewScoreRating and the number of listings they own</h2>
				<canvas id="listingReviewChart"></canvas>
			</div>
		</div>
		<!-- Top Cities Tab -->
		
		<div class="tab-pane fade" id="topCities">
			<div class="container mx-auto m-3 custom-outer-container">
				<h2 class="text-white">Top 10 cities along with the country where responsive hosts(responses within an hour) have high
earnings from their listings</h2>
				<table class="table table-bordered table-dark">
		            <thead>
		                <tr>
		                	<th scope="col">City</th>
		                    <th scope="col">Country</th>
		                    <th scope="col">Listing Count</th>
		                    <th scope="col">Total Earnings</th>
		                </tr>
		            </thead>
		            <tbody>
		                <c:forEach items="${cityEarningsList}" varStatus="loop" var="cityEarnings" >
							<tr>
							    <td>
							    	<c:out value="${cityEarnings.getCity()}" />
								</td>
							 	<td>
							 		<c:out value="${cityEarnings.getCountry()}" />
								</td>
								<td>
									<c:out value="${cityEarnings.getListingCount()}" />
							    </td>
							    <td>
									<c:out value="${cityEarnings.getTotalEarnings()}" />
							    </td>
							</tr>
						</c:forEach>
		           </tbody>
		       </table>
			</div>
		</div>
		<div class="tab-pane fade" id="avgAccommodatesByYear">
			<div class="container mx-auto m-3 custom-outer-container">
				<h2 class="text-white">Trends in the average number of accommodates over time</h2>
				<canvas id="avgAccommodatesByYearChart"></canvas>
			</div>
		</div>
	</div>
        
	
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script>
        var labels = [];
        var avgReviewScores = [];
        var numberOfListings = [];

        <% List<ListingReview> listingReviews = (List<ListingReview>) request.getAttribute("listingReviews");
            for (ListingReview listingReview : listingReviews) { %>
                labels.push('<%= listingReview.getHostName() %>');
                avgReviewScores.push(<%= listingReview.getAvgReviewScore() %>);
                numberOfListings.push(<%= listingReview.getNumberOfListings() %>);
        <% } %>

        var data = {
            labels: labels,
            datasets: [{
                label: 'Average Review Score',
                data: avgReviewScores,
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
            },
            {
                label: 'Number of Listings',
                data: numberOfListings,
                backgroundColor: 'rgba(255, 99, 132, 0.2)',
                borderColor: 'rgba(255, 99, 132, 1)',
                borderWidth: 1
            }]
        };

        var options = {
            scales: {
            	y: {
                    ticks: {
                      color: "white",
                      font: {
                        size: 18,
                      },
                      stepSize: 1,
                      beginAtZero: true
                    }
                  },
                  x: { 
                    ticks: {
                      color: "white",  
                      font: {
                        size: 14
                      },
                      stepSize: 1,
                      beginAtZero: true,
                      callback: function (value) {
                          
                       // truncate the labels only in this axis
                          const lbl = this.getLabelForValue(value);
                          if (typeof lbl === 'string' && lbl.length > 10) {
                              return lbl.substring(0, 10)+"...";
                          }
                          return lbl;
                        }
									
                    }
                  }
        	},
        	plugins: {
                legend: {
                    display: true,
                    labels: {
                        color: 'white'
                    }
                }
            }
        };

        var ctx = document.getElementById('listingReviewChart').getContext('2d');

        var myChart = new Chart(ctx, {
            type: 'bar',
            data: data,
            options: options
        });
    </script>
    <script>
        var accommodatesLabels = [];
        var avgAccommodates = [];

        <% List<AvgAccommodatesByYear> avgAccommodatesByYears = (List<AvgAccommodatesByYear>) request.getAttribute("avgAccommodatesByYearList");
            for (AvgAccommodatesByYear avgAccommodatesByYear : avgAccommodatesByYears) { %>
            	accommodatesLabels.push('<%= avgAccommodatesByYear.getYear() %>');
            	avgAccommodates.push(<%= avgAccommodatesByYear.getAverageAccommodates() %>);
        <% } %>

        var data = {
            labels: accommodatesLabels,
            datasets: [
            {
                label: 'Average Accommodates',
                data: avgAccommodates,
                backgroundColor: 'rgba(255, 99, 132, 0.2)',
                borderColor: 'rgba(255, 99, 132, 1)',
                borderWidth: 1
            }]
        };

        var options = {
            scales: {
            	y: {
                    ticks: {
                      color: "white",
                      font: {
                        size: 18,
                      },
                      stepSize: 1,
                      beginAtZero: true
                    }
                  },
                  x: { 
                    ticks: {
                      color: "white",  
                      font: {
                        size: 14
                      },
                      stepSize: 1,
                      beginAtZero: true,
                      callback: function (value) {
                          
                       // truncate the labels only in this axis
                          const lbl = this.getLabelForValue(value);
                          if (typeof lbl === 'string' && lbl.length > 10) {
                              return lbl.substring(0, 10)+"...";
                          }
                          return lbl;
                        }
									
                    }
                  }
        	},
        	plugins: {
                legend: {
                    display: true,
                    labels: {
                        color: 'white'
                    }
                }
            }
        };

        var ctx = document.getElementById('avgAccommodatesByYearChart').getContext('2d');

        var myChart = new Chart(ctx, {
            type: 'bar',
            data: data,
            options: options
        });
    </script>
     <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>
