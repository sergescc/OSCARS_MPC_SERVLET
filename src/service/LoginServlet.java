package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.Crypt;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import config.Configuration;
/**
 * Servlet implementation class Reservations
 */
@WebServlet(name = "login",
			urlPatterns = {"/login"}
			)
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		
	System.out.println("Server Started");
		
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
		
		//Read Http Request

		String messageLine;
		BufferedReader requestStream = new BufferedReader(request.getReader());
		Gson JSONInterpreter = new Gson();
		StringBuffer compiledJSON = new StringBuffer();
		JsonObject JSONrequest = null;
		
		while ((messageLine = requestStream.readLine()) != null)
		{
		
			compiledJSON.append(messageLine);
		}
		
		JSONrequest = JSONInterpreter.fromJson(compiledJSON.toString(), JsonObject.class);
		
		String username = JSONrequest.get("user").getAsString();
		String password = JSONrequest.get("pass").getAsString();
		
		//Query Database
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(Configuration.mysqlLocation, Configuration.mysqlUser, Configuration.mysqlPassword);
			stmt = conn.createStatement();
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Could not Load Mysql Class to prepare queries");
			e.printStackTrace();
		}
		
		ResultSet result = null;
		String serverSalt = null;
		String serverPass = null;
		boolean userExists = false;
		
		String getAuthInfo = "SELECT distinct password, salt FROM mpcUsersDB.users where username='"+ username +"'";
		try {
			result = stmt.executeQuery(getAuthInfo);
			if (result.next())
			{
				userExists = true;
				serverPass = result.getString("password");
				serverSalt = result.getString("salt");
			}
		} catch (SQLException e) {
			System.out.println("Error querying for password");
			e.printStackTrace();
		}
		
		
		
		
		//Prepare Response 
		
		response.setContentType("application/json");
		JsonObject responseMessage = new JsonObject();
		
		new Crypt();
        

		if (userExists)
		{
			String clientPassword = Crypt.crypt(password, "$5$" + serverSalt);
			
			if (clientPassword.equals(serverPass))
			{
				response.setStatus(200);
				responseMessage.addProperty("success", true);
				
				
			}
		}
		else 
		{
			response.setStatus(401);
			responseMessage.addProperty("success", false);
		}
		
		
		response.getWriter().print(responseMessage);
		
	}

}


