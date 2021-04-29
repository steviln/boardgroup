package session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.velocity.context.Context;

public class SessionHandler {
	
	public static session.Session init_session(HttpServletRequest request, Context context) {
		
		HttpSession usessesion = request.getSession();
		
		session.Session usesec = null;
		
		usesec = (session.Session) usessesion.getAttribute("sessobj");
		if(usesec == null) {
			usesec = new session.Session();
			usessesion.setAttribute("sessobj",usesec);
		}
		
		if(usesec.getUserID() == 0) {
			context.put("logged",0);
		}else {
			context.put("logged",1);
		}
		//usesec.accessCount++;
		//System.out.println(usesec.accessCount);
		
		return usesec;
	}

}
