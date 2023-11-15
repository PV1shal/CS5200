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
	    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	    <script>
		    function deleteHost(hostId) {
		        var confirmDelete = confirm("Are you sure you want to delete this host?");
		        if (confirmDelete) {
		            $.ajax({
		                type: 'DELETE',
		                url: '/AirbnbApplication/editHost' + '?' + $.param({"hostId": hostId}),
		                success: function (data) {
		                    location.reload();
		                },
		                error: function (error) {
		                    alert("Error deleting host: " + error.responseText);
		                }
		            });
		        }
		    }
		</script>
	    <link href="css/bootstrap.css" rel="stylesheet">
	</head>
	<body style="background-color: #0a0a0b">
	<%@include file="NavBar.jsp" %>
	<div class="container-fluid" style="margin-top: 5%; padding-left: 3%; padding-right: 3%" id="hosts">
	    <div class="row">
	        <c:forEach items="${hosts}" var="host" varStatus="loop">
	            <div class="col-sm-6 col-md-4 col-lg-3" style="margin-bottom: 30px;">
	                <div class="card card-default" style="background-color: #2C2C31; color: white; border-radius: 15px; height: 100%;" data-bs-toggle="modal" data-bs-target="#editHostModal${host.getHostId()}">
	                    <div class="card-body">
	                        <h5 class="card-title">
	                            <div class="text-truncate">
	                                <c:out value="${host.getHostName()}" />
	                            </div>
	                        </h5>
	                        <p class="card-text">
	                            Since: <fmt:formatDate value="${host.getHostSince()}" /><br>
	                            Listings Owned: <c:out value="${host.getHostTotalListingCount()}" /></br>
	                            Response Time: <c:out value="${host.getHostResponseTime()}" />
	                        </p>
	                        <div class="card-footer text-center cardFooter" onclick="window.open('${host.hostUrl}', '_blank')">
	                            View on Airbnb
	                        </div>
	                    </div>
	                </div>
	            </div>
	            
				<div class="modal fade" id="editHostModal${host.getHostId()}" tabindex="-1" aria-labelledby="editHostModal${host.getHostId()}Label" aria-hidden="true">
				    <div class="modal-dialog modal-dialog-centered">
				        <div class="modal-content">
				            <div class="modal-header">
				                <h1 class="modal-title fs-5" id="editHostModalLabel">Edit <b>${host.hostName}</b></h1>
				                <div>
				                	<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				                </div>
				            </div>
				            <div class="modal-body">
				                <form id="editHostForm" action="/AirbnbApplication/editHost" method="post">
				                    <input type="hidden" id="hostId" name="hostId" value="${host.getHostId()}"/>
				                    <input type="hidden" id="hostURL" name="hostURL" value="${host.getHostUrl()}"/>
				                    <input type="hidden" id="hostResponseRate" name="hostResponseRate" value="${host.getHostResponseRate()}"/>
				                    <input type="hidden" id="hostVerification" name="hostVerification" value="${host.getHostVerification()}"/>
				                    <div class="mb-3">
				                        <label for="hostName">Name:</label>
				                        <input type="text" class="form-control" id="hostName" name="hostName" value="${host.getHostName()}">
				                    </div>
				                    <div class="mb-3">
				                        <label for="hostSince">Since:</label>
				                        <input type="text" class="form-control" id="hostSince" name="hostSince" value="${host.getHostSince()}">
				                    </div>
				                    <div class="mb-3">
				                        <label for="hostTotalListingCount">Listings Owned:</label>
				                        <input type="text" class="form-control" id="hostTotalListingCount" name="hostTotalListingCount" value="${host.getHostTotalListingCount()}">
				                    </div>
				                    <div class="mb-3">
				                        <label for="hostResponseRate">Response Time:</label>
				                        <input type="text" class="form-control" id="hostResponseTime" name="hostResponseTime" value="${host.getHostResponseTime()}">
				                    </div>
					                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
					                <button type="button" class="btn btn-danger" onclick="deleteHost(${host.getHostId()})">
									    Delete
									    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
									        <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6Z"/>
									        <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1ZM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118ZM2.5 3h11V2h-11v1Z"/>
									    </svg>
									</button>
					                <button type="submit" class="btn btn-primary">Save changes</button>
				                </form>
				            </div>
				        </div>
				    </div>
				</div>
	        </c:forEach>
	    </div>
	</div>
	</body>
</html>
