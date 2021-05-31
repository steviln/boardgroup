package helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.hibernate.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import datastore.player;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.velocity.context.Context;

import hibernate.HibernateUtil;

public class Save {
	
	public static void SavePlayer(HttpServletRequest request,session.Session websession) {
		
		session.AjaxDbSession db = new session.AjaxDbSession();
		try {
			String jsonput = fetchPostBody(request, db);
			//System.out.println(jsonput);
			JSONParser jsonleser = new JSONParser();
			JSONObject obj = (JSONObject) jsonleser.parse(jsonput);
			
			int objID = (int) obj.get("id");
			String fornavn = (String) obj.get("fornavn");
			String etternavn = (String) obj.get("etternavn");
			String epost = (String) obj.get("epost");
			String brukernavn = (String) obj.get("brukernavn");
			String passorden = (String) obj.get("passorden");
			String passordto = (String) obj.get("passordto");
			String passord = "";
			
			if(objID == 0) {
				
			}else {
				
			}
			
			datastore.player spiller = new datastore.player(fornavn, etternavn, passorden, epost, brukernavn, passord, null, null, null);
			
			System.out.println("got the object " + fornavn);
		} catch (ParseException p) {
			db.closeSession();
		}
		
	}
	
	public static String fetchPostBody(HttpServletRequest request, session.AjaxDbSession db){
		String data = "";
		StringBuilder buffer = new StringBuilder();
	    BufferedReader reader;
		try {
			reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			data = buffer.toString();
		} catch (IOException e) {
			db.closeSession();
			e.printStackTrace();
		}
	    
	    return data;
	}

}


