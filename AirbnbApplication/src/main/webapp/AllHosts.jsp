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
	        	height: fit;
	        }
	    </style>
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
	    <script src="js/HomeScript.js"></script>
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	    <link href="css/bootstrap.css" rel="stylesheet">
	</head>
	<body style="background-color: #0a0a0b">
	<%@include file="NavBar.jsp" %>
	<div style="margin: 0 auto;">
	</div>
	    <div class="container-fluid" style="margin-top: 5%; padding-left: 3%; padding-right: 3%" id="hosts">
	        <div class="row">
	            <c:forEach items="${hosts}" var="host" varStatus="loop">
					<div class="col-sm-6 col-md-4 col-lg-3" style="margin-bottom: 30px;">
					    <div class="card card-default" style="background-color: #2C2C31; color: white; border-radius: 15px; height: 100%;">
					        <div class="card-body">
						      <h5 class="card-title">
						        <div class="text-truncate">
						          <c:out value="${host.getHostName()}" />
						        </div>
						      </h5>
					            <p class="card-text">
					                Since: <fmt:formatDate value="${host.getHostSince()}" /><br>
					                Listings Owned: <c:out value="${host.getHostTotalListingCount()}" /></br>
					                Response Rate: <c:out value="${host.getHostResponseRate()}" />
					            </p>
					            <div class="card-footer text-center cardFooter" onclick="window.open('${host.getHostUrl()}', '_blank')">
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
