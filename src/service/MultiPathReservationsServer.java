package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import datastructs.MPReservation;




@ServerEndpoint("/Reservations/{username}")
public class MultiPathReservationsServer {

	private ServletController mpcServletControl = new ServletController();
	private static Map<Long,UserSession> sessionInfo = new Hashtable<>();
	private static ObjectMapper mapper = new ObjectMapper();

	@OnOpen
	public void onOpen(Session session, @PathParam("username") String username) {

		
		mpcSession thisSession = new mpcSession();
		thisSession.username = username;
		thisSession.userId = session;
		thisSession.userSession  =  new UserSession(username);
		

	}

	@OnMessage
	public void onMessage(Session session, String message,
			@PathParam("userId") String username) {

		Gson JSONWriter = new Gson();

		List<String> action = session.getRequestParameterMap().get("action");
		List<String> selectedGRIs = session.getRequestParameterMap().get("MPGRI");

		for (String currentAction : action) {
			switch (currentAction) {
			case ("listMPGris"): {
				sendJsonMessage(session,username, mpcServletControl
						.getMPGRIs());

				break;
			}
			case ("listUniGRIs"): {

				String listUniGRIs = JSONWriter.toJson(mpcServletControl
						.getAllUnicastGRIs());
	
			}
			case ("listForSelectedMPGRI"): {

				ArrayList<MPReservation> selectionDetails = new ArrayList<MPReservation>();

				for (String GRI : selectedGRIs) {

					selectionDetails.add(loadMPReservations(GRI,
							mpcServletControl.getArrayOfGroupedGRIs(GRI)));
					//this.sendJsonMessage();

				}

				break;
			}
			default:

				break;
			}
		}
	}
	
	private MPReservation loadMPReservations(String GRI, ArrayList<String> groupedGris)
	{
		MPReservation newMPReservation = new MPReservation();
		
		newMPReservation.setMPGRI(GRI);
		newMPReservation.setUniGris(groupedGris);
		
		return newMPReservation;
	}
	
	
	private void sendJsonMessage(Session session,  String username, Object[] objects)
    {
        try
        {
            session.getBasicRemote()
                   .sendText(MultiPathReservationsServer.mapper.writeValueAsString(objects));
        }
        catch(IOException e)
        {
        }
    }
	
	public static class mpcSession
	{
		public String username;
		
		public Session userId;
		
		public UserSession userSession;
	}
	
	public static abstract class DataMessage
    {
		String action;

        public DataMessage(String action)
        {
            this.action = action;
        }

        public String getData()
        {
            return this.action;
        }
    }
	
	public static class  sendForSelected extends DataMessage 
	{
		ArrayList<MPReservation> gRIsForSelected;
		
		public  sendForSelected(ArrayList<MPReservation> gRIsForSelection)
		{
			super("gRIsForSelection");
			this.gRIsForSelected = gRIsForSelection;
			
		}
		
	}	
}