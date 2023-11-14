<!-- @author ambika kabra -->
<%@ page import="java.util.List" %>
<%@ page import="airbnb.model.insights.ListingReview" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
	<div class="container mx-auto m-3 custom-outer-container">
		<h2 class="text-white">Top 10 hosts with over 100 listings who have the highest average ReviewScoreRating and the number of listings they own</h2>
		<canvas id="listingReviewChart"></canvas>
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
     <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>
