<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
	<head>
	    <style>
	        .image-container {
	            position: relative;
	            width: 65%;
	            height: auto;
	            border-radius: 25px;
	        }
	
	        .image-container img {
	            border-radius: 25px;
	            aspect-ratio: auto;
	        }
	
	        .down-arrow {
	            position: absolute;
	            bottom: -50px;
	            left: 50%;
	            transform: translateX(-50%);
	            width: 100px;
	            height: 100px;
	            border-radius: 50%;
	            background-color: #ffffff;
	            color: #0a0a0b;
	            border: none;
	            z-index: 1000;
	        }
	        
	        .cardCSS {
	        	background-color: white; 
	        	color: white; 
	        	border-radius: 25px !important;
	        	box-shadow: 0px 0px 25px -10px;
	        }
	        
	        .cardFooter {
	        	background-color: cornflowerblue !important; 
	        	color: black !important;
	        	border-radius: 5px !important;
	        }
	    </style>
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
	    <script src="js/HomeScript.js"></script>
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	    <link href="css/bootstrap.css" rel="stylesheet">
	</head>
	<body style="background-color: #0a0a0b">
	<%@include file="NavBar.jsp" %>
	<div style="margin: 0 auto; width: 65%">
	    <div id="carouselExampleFade" class="carousel slide carousel-fade">
	        <div class="carousel-inner">
	            <div class="carousel-item active">
	                <img src="resources/Background2.png" class="d-block w-100" alt="...">
	            </div>
	            <div class="carousel-item">
	                <img src="resources/Background1.png" class="d-block w-100" alt="...">
	            </div>
	            <div class="carousel-item">
	                <img src="resources/Background3.png" class="d-block w-100" alt="...">
	            </div>
	            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="prev">
				    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
				    <span class="visually-hidden">Previous</span>
				  </button>
				  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="next">
				    <span class="carousel-control-next-icon" aria-hidden="true"></span>
				    <span class="visually-hidden">Next</span>
				  </button>
	        </div>
	        <button class="down-arrow" onClick="scrollToHosts()">
	    		<svg xmlns="http://www.w3.org/2000/svg" width="64" height="64" fill="currentColor" class="bi bi-chevron-down" viewBox="0 0 16 16">
	  				<path fill-rule="evenodd" d="M1.646 4.646a.5.5 0 0 1 .708 0L8 10.293l5.646-5.647a.5.5 0 0 1 .708.708l-6 6a.5.5 0 0 1-.708 0l-6-6a.5.5 0 0 1 0-.708z"/>
				</svg>
			</button>
	    </div>
	</div>
	
<div class="row justify-content-center" style="margin-top: 25%; width: 100%">
  <div class="col-md-8">
    <div class="card center-card" style="width: 80%; background-color: rgba(0, 0, 0, 0); color: white;">
      <div class="card-body">
        <h1 class="card-title">Who We Are?</h1>
        <p class="card-text">
          AllAirbnb is a global overview of how Airbnb is really used in cities around the world. This
          product provides in-depth filtering on Airbnb features to address complex queries like how many
          listings are in a particular neighborhood and where they are located, or how many houses and
          apartments are being rented out frequently to tourists and not to long-term residents? For
          renters who are unhappy with basic filters provided on the Airbnb website, they can use
          AllAirbnb to find out more specific information on the available properties in the area of interest.
          Examples include knowing which hosts are running a business with multiple listings and where
          they are? For hosts who want to evaluate the performance of their property, they will have
          insight on the popularity and average rental price of other Airbnbs in the area so they can make
          adjustments accordingly.
        </p>
      </div>
    </div>
  </div>
  <div class="col-md-4">
    <div class="card center-card" style="width: 100%; background-color: rgba(0, 0, 0, 0); color: white;">
      <div class="card-body text-center">
        <img src="resources/AboutImage.png" class="img-fluid mb-3" style="object-fit: cover; width: 40%;" />
      </div>
    </div>
  </div>
</div>

	
    <div class="container-fluid" style="margin-top: 15%; padding-left: 3%; padding-right: 3%" id="hosts">
        <div class="row">
            <c:forEach items="${listings}" var="listing" varStatus="loop">
				<div class="col-sm-6 col-md-4 col-lg-3" style="margin-bottom: 30px;">
				    <div class="card card-default" style="background-color: #2C2C31; color: white; border-radius: 15px; height: 100%;">
				        <img class="card-img-top" src="${listing.getXlPhotoUrl()}" onerror="this.src='resources/ImageError.png'" style="object-fit: cover; height: 200px">
				        <div class="card-body">
					      <h5 class="card-title">
					        <div class="text-truncate">
					          <c:out value="${listing.getName()}" />
					        </div>
					      </h5>
				            <p class="card-text">
				                <c:out value="${listing.getPropertyType()}" /><br>
				                <c:out value="${listing.getHost().getHostName()}" />
				            </p>
				            <div class="card-footer text-center cardFooter" onclick="window.open('${listing.getListingUrl()}', '_blank')">
							    View on Airbnb
							</div>
				        </div>
				    </div>
				</div>
            </c:forEach>
        </div>
    </div>
	</body>
</html>
