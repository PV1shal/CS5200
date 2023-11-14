package airbnb.servlet.listings;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import airbnb.dao.HostsDao;
import airbnb.dao.ListingsDao;
import airbnb.model.Hosts;
import airbnb.model.Listings;

@WebServlet("/HomeLisintgs")
public class HomeListings extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected ListingsDao listingsDao;
	protected HostsDao hostsDao;

	public void init() throws ServletException {
		listingsDao = ListingsDao.getInstance();
		hostsDao = HostsDao.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Listings> listings = new ArrayList<Listings>();
		try {
			listings = listingsDao.getAllListings();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		request.setAttribute("listings", listings);
		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
