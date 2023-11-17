package airbnb.servlet.hosts;
import java.util.Date;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import airbnb.dao.HostsDao;
import airbnb.model.Hosts;

/**
 * Servlet implementation class CreateHost
 */
@WebServlet("/CreateHost")
public class CreateHost extends HttpServlet {
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
		String hostName = request.getParameter("hostName");
        String hostUrl = request.getParameter("hostUrl");
        Date hostSince = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            hostSince = dateFormat.parse(request.getParameter("hostSince"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String hostResponseTime = request.getParameter("hostResponseTime");
        int hostResponseRate = Integer.parseInt(request.getParameter("hostResponseRate"));
        int hostTotalListingCount = Integer.parseInt(request.getParameter("hostTotalListingCount"));
        String hostVerification = request.getParameter("hostVerification");

        try {
			Hosts h = hostsDao.create(new Hosts(0, hostUrl, hostName, hostSince, hostResponseTime, hostResponseRate, hostTotalListingCount, hostVerification));
			if(h != null) {
				response.sendRedirect("/AirbnbApplication/home");
			}
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
