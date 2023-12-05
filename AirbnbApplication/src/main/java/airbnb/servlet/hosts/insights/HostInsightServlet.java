package airbnb.servlet.hosts.insights;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import airbnb.dao.insights.HostInsightDAO;
import airbnb.model.insights.*;

/**
 * The `HostInsightServlet` class is a servlet that handles requests related to host insights on Airbnb.
 * It retrieves using a data access object (DAO) and forwards the information to a JSP page.
 *
 * @author ambikakabra
 */
@WebServlet("/HostInsightServlet")
public class HostInsightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
     * Handles HTTP GET requests. Retrieves the top listing reviews and forwards the information to a JSP page.
     *
     * @param request  The HttpServletRequest object containing the client's request.
     * @param response The HttpServletResponse object for sending responses to the client.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an input or output error occurs while the servlet is handling the GET request.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HostInsightDAO dao = HostInsightDAO.getInstance();
        List<ListingReview> listingReviews = (List<ListingReview>) getServletContext().getAttribute("listingReviews");
        List<CityEarnings> cityEarningsList = (List<CityEarnings>) getServletContext().getAttribute("cityEarningsList");
        List<AvgAccommodatesByYear> avgAccommodatesByYearList = (List<AvgAccommodatesByYear>) getServletContext().getAttribute("avgAccommodatesByYearList");

		try {

			if (avgAccommodatesByYearList == null) {
				avgAccommodatesByYearList = dao.getAvgAccommodatesByYear();
				// Store the data in the application scope
		        getServletContext().setAttribute("avgAccommodatesByYearList", avgAccommodatesByYearList);
			}
			
			if(listingReviews == null) {
				listingReviews = dao.getTopListings();
				// Store the data in the application scope
		        getServletContext().setAttribute("listingReviews", listingReviews);
			}
			
			if(cityEarningsList == null) {
				cityEarningsList = dao.getTopEarningCities();
				// Store the data in the application scope
		        getServletContext().setAttribute("cityEarningsList", cityEarningsList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        request.setAttribute("listingReviews", listingReviews);
        request.setAttribute("cityEarningsList", cityEarningsList);
        request.setAttribute("avgAccommodatesByYearList", avgAccommodatesByYearList);
        request.getRequestDispatcher("/listingReview.jsp").forward(request, response);
    }
}
