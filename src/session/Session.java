package session;

public class Session {
	
	private int userID = 0;
	//public int accessCount = 0;
	
	public Session() {
		
	}
	
	public void setUserID(int useid) {
		this.userID = useid;
	}
	
	public int getUserID() {
		return this.userID;
	}

}
