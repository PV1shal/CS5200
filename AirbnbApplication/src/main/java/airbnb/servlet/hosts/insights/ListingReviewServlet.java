package airbnb.servlet.hosts.insights;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import airbnb.dao.insights.ListingReviewDAO;
import airbnb.model.insights.ListingReview;

/**
 * The `ListingReviewServlet` class is a servlet that handles requests related to listing reviews on Airbnb.
 * It retrieves the top listings using a data access object (DAO) and forwards the information to a JSP page.
 *
 * @author ambikakabra
 */
@WebServlet("/ListingReviewServlet")
public class ListingReviewServlet extends HttpServlet {
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
        ListingReviewDAO dao = ListingReviewDAO.getInstance();
        List<ListingReview> listingReviews = null;
		try {
			listingReviews = dao.getTopListings();
		} catch (SQLException e) {
			e.printStackTrace();
		}

        request.setAttribute("listingReviews", listingReviews);
        request.getRequestDispatcher("/listingReview.jsp").forward(request, response);
    }
}
