<nav class="navbar navbar-expand-lg" style="background-color: #0a0a0b; height: 80px">
    <div class="container-fluid">
        <a class="navbar-brand me-3" href="/AirbnbApplication/home" style="color: white">
            <img src="resources/Logo.png" width="40" height="40">
            VizPro
        </a>
        <div class="collapse navbar-collapse" id="navbarNav">
	      <ul class="navbar-nav">
	        <li class="nav-item">
	          <a class="nav-link active" aria-current="page" href="/AirbnbApplication/allHosts" style="color: white">All Hosts</a>
	        </li>
	      </ul>
	    </div>
        <div class="d-flex ms-3">
        	<a class="btn btn-outline-primary" href="/AirbnbApplication/hostsInsights" role="button">Host Insights</a>
            <a class="btn" style="color: white;" data-bs-toggle="modal" data-bs-target="#newHostModal">Become a Host</a>
        </div>
    </div>
</nav>


	    
<div class="modal fade" id="newHostModal" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">
          Become a Host!
        </h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form id="hostForm" action="/AirbnbApplication/createHost" method="post">
          <div class="mb-3">
            <label for="hostName" class="form-label">Username</label>
            <input type="text" class="form-control" id="hostName" name="hostName">
          </div>
          <div class="mb-3">
            <label for="hostUrl" class="form-label">Host URL</label>
            <input type="text" class="form-control" id="hostUrl" name="hostUrl">
          </div>
          <div class="mb-3">
            <label for="hostSince" class="form-label">Host Since</label>
            <input type="text" class="form-control" id="hostSince" name="hostSince">
          </div>
          <div class="mb-3">
            <label for="hostResponseTime" class="form-label">Response Time</label>
            <input type="text" class="form-control" id="hostResponseTime" name="hostResponseTime">
          </div>
          <div class="mb-3">
            <label for="hostResponseRate" class="form-label">Response Rate</label>
            <input type="text" class="form-control" id="hostResponseRate" name="hostResponseRate">
          </div>
          <div class="mb-3">
            <label for="hostTotalListingCount" class="form-label">Total Listing Count</label>
            <input type="text" class="form-control" id="hostTotalListingCount" name="hostTotalListingCount">
          </div>
          <div class="mb-3">
            <label for="hostVerification" class="form-label">Verification</label>
            <input type="text" class="form-control" id="hostVerification" name="hostVerification">
          </div>
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="submit" class="btn btn-primary" id="submitBtn">Submit</button>
        </form>
      </div>
    </div>
  </div>
</div>