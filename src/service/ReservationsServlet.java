package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import data.models.UserInfo;
/**
 * Servlet implementation class Reservations
 */
@WebServlet(name = "Oscars MPC Reservation Manager",
			urlPatterns = {"/Reservation"},
			loadOnStartup = 1
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
	public void init() throws ServletException {
		
	
		
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		
		if (action.equals("login")) {

			StringBuffer bufferedString = new StringBuffer();
			Gson JSONReader = new Gson();
			try 
			{
				BufferedReader reader = request.getReader();
				String line = null;
				while ((line = reader.readLine()) !=  null )
				{
					bufferedString.append(line);
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			String rawMessage = bufferedString.toString();
			UserInfo currentUser = JSONReader.fromJson(rawMessage, UserInfo.class);
			
			 response.setContentType("text/html");
			 PrintWriter out = response.getWriter();
			    out.write("A new user " + currentUser.getUsername() + " has been created.");
			    out.flush();
			    out.close();
			
		}
	}
	

}


