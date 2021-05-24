import org.apache.velocity.Template;

import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

import org.hibernate.*;
import org.hibernate.Session;

import com.mysql.cj.log.Log;

import hibernate.HibernateUtil;
import datastore.player;
import datastore.playerRanking;
import datastore.gameList;
import menu.*;
import interfaces.*;
import session.*;

import java.util.Iterator;
import java.util.List;

import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;
import org.apache.velocity.app.Velocity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 

@SuppressWarnings("serial")
public class Gameservlet extends VelocityViewServlet implements ConnectionCheck{
	
	private org.hibernate.Session dbsession = null;
	private session.Session websession = null;
	
	
	public void openSession() {
		this.dbsession = HibernateUtil.getSessionFactory().openSession();
	}
	
	public void closeSession() {
		if(this.dbsession != null) {
			this.dbsession.close();
			this.dbsession = null;
		}
	}
	
	public void finalize() {
		this.closeSession();
	}
	
	@Override
    public Template handleRequest(HttpServletRequest request,HttpServletResponse response,Context context) {
        Template template = null;                
        Transaction transact = null;
        
        websession = session.SessionHandler.init_session(request, context);
        session.Parameter parameter = new session.Parameter(request.getRequestURL().toString(), request.getServletPath());
		
		String kommando = parameter.getCommand();
        System.out.print("Kommandoen er " + kommando);
        //System.out.print(String.valueOf(websession.getUserID()));
        
        this.openSession();
        
        if(kommando.equals("show")) {
        	
        }else {
        	transact = this.dbsession.beginTransaction();
        	List<gameList> games = dbsession.createQuery("SELECT a FROM gameList a",gameList.class).getResultList();
        	context.put("games", games);
        	List<menu.MenuItem> menu = MenuHelper.hentMeny(websession);
        	context.put("menu", menu);
        }
        
        
        try {

        }catch(Exception e) {
        	log(e.toString());
        }finally {
        	this.closeSession();
        }

        try {
        	//log("HERE TEST");
            template = getTemplate("games.vm");
            response.setHeader("Template Returned", "Success");
        } catch (Exception e) {
        	log(e.toString());
            e.printStackTrace();
        }
        
        return template;
    }

}
