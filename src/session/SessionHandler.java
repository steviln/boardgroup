package session;

import java.io.PrintWriter;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

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
		
		if(context != null && usesec.getUserID() == 0) {
			context.put("logged",0);
		}else if(context != null){
			context.put("logged",1);
		}
		//usesec.accessCount++;
		//System.out.println(usesec.accessCount);
		
		return usesec;
	}
	
	public static void printJsonSession(PrintWriter skriver, session.Session usesec) {
		JSONObject jsonretur = new JSONObject();
		jsonretur.put("loggid", usesec.getUserID());
		skriver.print(jsonretur.toString());
	}
	
	public static void doLogin(HttpServletRequest request, PrintWriter skriver) {
		JSONObject jsonretur = new JSONObject();
		jsonretur.put("retur", 0);
		skriver.print(jsonretur.toString());
		
	}

}
