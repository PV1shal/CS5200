package airbnb.servlet.hosts;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import airbnb.dao.HostsDao;
import airbnb.model.Hosts;

/**
 * Servlet implementation class FindHosts
 */
@WebServlet("/FindHosts")
public class FindHosts extends HttpServlet {
	
	protected HostsDao hostsDao;

    @Override 
    public void init() throws ServletException {
    	hostsDao = HostsDao.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Hosts> hosts = new ArrayList<Hosts>();
		try {
			hosts = hostsDao.getHosts(10);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		request.setAttribute("hosts", hosts);
		request.getRequestDispatcher("/AllHosts.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
