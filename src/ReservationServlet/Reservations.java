package ReservationServlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



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
		
	
		mpcServletControl = new ServletController();
		mpcServletControl.getAllUnicastGRIs();
		mpcServletControl.getMPGRIs();
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
		//String action = request.getParameter("action");
		//switch (action)
		//{
		//case "getTopologyNodes":
		//	response.setContentType("text/html");
	//		response.setCharacterEncoding("UTF-8");
			//response.getWriter().println(mpcServletControl.getTopologyNodes());
			
		//}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
