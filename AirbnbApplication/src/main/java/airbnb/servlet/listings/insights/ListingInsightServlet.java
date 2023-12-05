package airbnb.servlet.listings.insights;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import airbnb.dao.insights.ListingInsightDAO;
import airbnb.model.insights.*;

/**
 * Servlet implementation class ListingInsightServlet
 * 
 * @author ambikakabra
 */
@WebServlet("/ListingInsightServlet")
public class ListingInsightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ListingInsightDAO dao = ListingInsightDAO.getInstance();
		List<ListingCost> expensiveNeighborhoodList = (List<ListingCost>) getServletContext().getAttribute("expensiveNeighborhoodList");
		List<WirelessInternet> wirelessInternetList = (List<WirelessInternet>) getServletContext().getAttribute("wirelessInternetList");
		List<AmenitiesCount> amenitiesCountList = (List<AmenitiesCount>) getServletContext().getAttribute("amenitiesCountList");
		AveragePrice avgPrice = (AveragePrice) getServletContext().getAttribute("avgPrice");
		RoomTypeCount roomTypeCount = (RoomTypeCount) getServletContext().getAttribute("roomTypeCount");
		
		try {
			if(expensiveNeighborhoodList == null) {
				expensiveNeighborhoodList = dao.getExpensiveListingsInNeighborhood();
				// Store the data in the application scope
				getServletContext().setAttribute("expensiveNeighborhoodList", expensiveNeighborhoodList);
			}
			
			if(wirelessInternetList == null) {
				wirelessInternetList = dao.getListingsWithWirelessInternet();
				// Store the data in the application scope
				getServletContext().setAttribute("wirelessInternetList", wirelessInternetList);
			}
			
			if(amenitiesCountList == null) {
				amenitiesCountList = dao.getTopAmenitiesCount();
				// Store the data in the application scope
				getServletContext().setAttribute("amenitiesCountList", amenitiesCountList);
			}
			
			if(avgPrice == null) {
				avgPrice = dao.getAveragePrice();
				// Store the data in the application scope
				getServletContext().setAttribute("avgPrice", avgPrice);
			}

			if(roomTypeCount == null) {
				roomTypeCount = dao.getRoomTypeCount();
				// Store the data in the application scope
				getServletContext().setAttribute("roomTypeCount", roomTypeCount);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
        request.setAttribute("expensiveNeighborhoodList", expensiveNeighborhoodList);
        request.setAttribute("wirelessInternetList", wirelessInternetList);
        request.setAttribute("amenitiesCountList", amenitiesCountList);
        request.setAttribute("avgPrice", avgPrice);
        request.setAttribute("roomTypeCount", roomTypeCount);
        request.getRequestDispatcher("/listingInsight.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
