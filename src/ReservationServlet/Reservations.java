package ReservationServlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;



/**
 * Servlet implementation class Reservations
 */
@WebServlet(name = "Oscars MPC Reservation Manager",
			urlPatterns = {"/Reservation"}
			)
public class Reservations extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ServletController mpcServletControl;
    /**
     * Default constructor. 
     */
    public Reservations() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		
		mpcServletControl = new ServletController(config.getServletContext().getRealPath("/WebContent/WEB-INF/certs/client.jks"));	
		mpcServletControl.getAllUnicastGRIs();		// Invoke a request to List all unicast GRIs
		mpcServletControl.refreshMPGriLists();
		System.out.println("OSCARS MPC Servlet Intitialized");
		
		
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Gson JSONWriter = new Gson();
		
		String action = request.getParameter("action");
		String selectedMPGRI = request.getParameter("MPGRI");
		
		switch (action)
		{
		case("listMPGris"):
		{
			setResponsetoJson(response);			
			String listMPGRIs = JSONWriter.toJson(mpcServletControl.getMPGRIs());
			response.getWriter().println(listMPGRIs);
			
			break;
		}
		case("listUniGRIs"):
		{
			setResponsetoJson(response);
			String listUniGRIs = JSONWriter.toJson(mpcServletControl.getAllUnicastGRIs());
			response.getWriter().println(listUniGRIs);
		}
		case("listForMPGRI"):
		{
			setResponsetoJson(response);
			String listUniGRIs = JSONWriter.toJson(mpcServletControl.getGroupedGRIs(selectedMPGRI));
			response.getWriter().println(listUniGRIs);
			break;
		}
		default:
			
			break;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private void setResponsetoJson (HttpServletResponse response) throws UnsupportedEncodingException
	{
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
	}

}


