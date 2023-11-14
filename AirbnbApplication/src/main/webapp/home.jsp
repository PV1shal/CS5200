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
	        	border-radius: 25px !important;
	        }
	    </style>
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
	    <script src="js/HomeScript.js"></script>
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	    <link href="css/bootstrap.css" rel="stylesheet">
		<nav class="navbar navbar-expand-lg" style="background-color: #0a0a0b; height: 80px">
		    <div class="container-fluid">
		        <a class="navbar-brand me-3" href="#" style="color: white">
		            <img src="resources/Logo.png" width="40" height="40">
		            VizPro
		        </a>
		        <div class="d-flex ms-3">
		            <a class="btn" style="color: white;" data-bs-toggle="modal" data-bs-target="#newHostModal">Become a Host</a>
		        </div>
		    </div>
		</nav>
	</head>
	<body style="background-color: #0a0a0b">
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
	    <!-- Hosts -->
	</div>
	    <div class="container-fluid" style="margin-top: 50%; padding-left: 3%; padding-right: 3%" id="hosts">
	        <div class="row">
	            <c:forEach items="${listings}" var="listing" varStatus="loop">
					<div class="col-sm-6 col-md-4 col-lg-3" style="margin-bottom: 30px;">
					    <div class="card card-default" style="background-color: #2C2C31; color: white; border-radius: 15px; height: 100%;">
					        <img class="card-img-top" src="${listing.getXlPhotoUrl()}" onerror="this.src='resources/ImageError.png'" style="object-fit: cover; height: 300px">
					        <div class="card-body">
					            <h4 class="card-title">
					                <c:out value="${listing.getName()}" />
					            </h4>
					            <p class="card-text">
					                <c:out value="${listing.getPropertyType()}" /><br>
					                <c:out value="${listing.getHost().getHostName()}" />
					            </p>
					            <div class="card-footer text-center cardFooter" onclick="window.open('${listing.getListingUrl()}', '_blank')">
								    View on Airbnb.
								</div>
					        </div>
					    </div>
					</div>
	            </c:forEach>
	        </div>
	    </div>
	    
	    <div class="modal fade" id="newHostModal" aria-hidden="true">
	    	<div class="modal-dialog modal-dialog-centered">
	    		<div class="modal-content">
	    			<div class="modal-header">
	    				<h5 class="modal-title">
	    					Become a Host!
	    				</h5>
	    			</div>
	    			<div class="modal-body">
						<form>
						  <div class="mb-3">
						    <label for="exampleInputEmail1" class="form-label">Email address</label>
						    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
						    <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
						  </div>
						  <div class="mb-3">
						    <label for="exampleInputPassword1" class="form-label">Password</label>
						    <input type="password" class="form-control" id="exampleInputPassword1">
						  </div>
						  <div class="mb-3 form-check">
						    <input type="checkbox" class="form-check-input" id="exampleCheck1">
						    <label class="form-check-label" for="exampleCheck1">Check me out</label>
						  </div>
				          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
						  <button type="submit" class="btn btn-primary">Submit</button>
						</form>
	    			</div>
	    		</div>
	    	</div>
	    </div>
	</body>
</html>
