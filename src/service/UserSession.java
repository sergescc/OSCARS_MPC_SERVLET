package service;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.websocket.Session;


public class UserSession {
	
	private static long sessionIdSequence = 1L;
	private static final Map<Long, UserSession> activeSessions = new Hashtable<>();
	
	
	
	private String username;
	private long sessionId;
	
	public UserSession(String username)
	{
		this.username = username;
		this.sessionId = mapSession(username);

	}
	
	public static long mapSession(String username)
    {
        long id = UserSession.sessionIdSequence++;
        UserSession.activeSessions.put(id, new UserSession(username));
        return id;
    }
	

	
}
