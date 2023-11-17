package airbnb.servlet.hosts;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import airbnb.dao.HostsDao;
import airbnb.model.Hosts;

/**
 * Servlet implementation class EditHosts
 */
@WebServlet("/EditHosts")
public class EditHosts extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected HostsDao hostsDao;
    
    @Override
    public void init() throws ServletException {
    	hostsDao = HostsDao.getInstance();
    }
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int hostId = Integer.parseInt(request.getParameter("hostId"));
		String hostUrl = request.getParameter("hostURL");
		String hostName = request.getParameter("hostName");
		Date hostSince = null;
		try {
		    SimpleDateFormat inputDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
		    hostSince = inputDateFormat.parse(request.getParameter("hostSince"));
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		String hostResponseTime = request.getParameter("hostResponseTime");
		int hostResponseRate = Integer.parseInt(request.getParameter("hostResponseRate"));
		int hostTotalListingCount = Integer.parseInt(request.getParameter("hostTotalListingCount"));
		String hostVerification = request.getParameter("hostVerification");
		
		try {
			hostsDao.Update(new Hosts(hostId, hostUrl, hostName, hostSince, 
					hostResponseTime, hostResponseRate, hostTotalListingCount, hostVerification));
			response.sendRedirect("/AirbnbApplication/allHosts");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String hostIdParam = request.getParameter("hostId");
	    int hostId = -1;
	    if (hostIdParam != null && !hostIdParam.isEmpty()) {
	        try {
	            hostId = Integer.parseInt(hostIdParam);
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	        }
	    }
	    try {
			hostsDao.delete(hostId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
