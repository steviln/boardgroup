package session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionHandler {
	
	public static session.Session init_session(HttpServletRequest request) {
		
		HttpSession usessesion = request.getSession();
		
		session.Session usesec = null;
		
		usesec = (session.Session) usessesion.getAttribute("sessobj");
		if(usesec == null) {
			usesec = new session.Session();
			usessesion.setAttribute("sessobj",usesec);
		}
		
		//usesec.accessCount++;
		//System.out.println(usesec.accessCount);
		
		return usesec;
	}

}
