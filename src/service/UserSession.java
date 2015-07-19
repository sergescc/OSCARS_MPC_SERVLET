package service;

import java.util.Hashtable;
import java.util.Map;


public class UserSession {
	
	private static long sessionIdSequence = 1L;
	private static final Map<Long, String> pendingSessions = new Hashtable<>();
	private static final Map<Long, UserSession> activeSessions = new Hashtable<>();
	
	
	
	private String username;
	private long sessionId;
	
	public UserSession(String username)
	{
		this.setUsername(username);

	}
	
	public static long loadSession(String username)
    {
        long id = UserSession.sessionIdSequence++;
        UserSession.pendingSessions.put(id, username);
        return id;
    }
	
	public static boolean activateSession(Long id, String username)
	{
		if (pendingSessions.containsKey(id))
		{
			if (pendingSessions.get(id).equalsIgnoreCase(username));
			{
				UserSession.activeSessions.put(id,new UserSession(username));
				UserSession.pendingSessions.remove(id);
				return true;
			}
		}
		return false;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getSessionId() {
		return sessionId;
	}

	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}
	

	
}
