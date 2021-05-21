package session;

import java.io.PrintWriter;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import org.hibernate.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import datastore.player;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.velocity.context.Context;

import hibernate.HibernateUtil;

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
	
	public static void logOff(PrintWriter skriver, session.Session usesec) {
		
		usesec.setUserID(0);
		
		JSONObject jsonretur = new JSONObject();				
		jsonretur.put("retur", 0);
		skriver.print(jsonretur.toString());
		
	}
	
	public static void doLogin(HttpServletRequest request, PrintWriter skriver, session.Session usesec) {
		
		int loginID = 0;
		
		org.hibernate.Session dbsession = HibernateUtil.getSessionFactory().openSession();
		Query<datastore.player> findlog = dbsession.createQuery("FROM player pl WHERE pl.brukernavn = :b AND pl.passord = :p");
		findlog.setParameter("b",request.getParameter("brukernavn"));
		findlog.setParameter("p",request.getParameter("passord"));
		
		List<datastore.player> rightuser = findlog.list();
		//System.out.println(String.valueOf(rightuser.size()));
		if(rightuser.size() > 0) {
			loginID = rightuser.get(0).getId();
			usesec.setUserID(loginID);
		}

		
		dbsession.close();
		
		JSONObject jsonretur = new JSONObject();				
		jsonretur.put("retur", loginID);
		skriver.print(jsonretur.toString());
		
	}

}
