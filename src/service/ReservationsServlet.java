package service;

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
public class ReservationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
    /**
     * Default constructor. 
     */
    public ReservationsServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		

		
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


