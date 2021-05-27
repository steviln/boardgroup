import org.apache.velocity.Template;

import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

import org.hibernate.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

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
public class PlayerServlet extends VelocityViewServlet implements ConnectionCheck{
	
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
        String templateName = "players.vm";
        
        context.put("userid", websession.getUserID());
        context.put("editpermission", 0);
		
		String kommando = parameter.getCommand();
        System.out.print("Kommandoen er " + kommando);
        //System.out.print(String.valueOf(websession.getUserID()));
        
        this.openSession();
        
        if(kommando.equals("show")) {
        	
        }else if(kommando.equals("edit")){
        	templateName = "editplayer.vm";
        	int useID = parameter.returnFirstNumber();

    		Query<datastore.player> findplay = this.dbsession.createQuery("FROM player pl WHERE pl.id = :i");
    		findplay.setParameter("i",useID);
    		List<datastore.player> rightuser = findplay.list();
    		if(rightuser.size() == 0) {    		
    			context.put("player",new datastore.player("", "", "", "", "", "", 0, 0, 0) );
    		}else {    			
    			context.put("player", rightuser.get(0));
    		}
        }else {
        	transact = this.dbsession.beginTransaction();
        	List<player> players = dbsession.createQuery("SELECT a FROM player a",player.class).list();
        	context.put("players", players);
        	
        }
        
        List<menu.MenuItem> menu = MenuHelper.hentMeny(websession);
    	context.put("menu", menu);
        
        
        try {

        }catch(Exception e) {
        	log(e.toString());
        }finally {
        	this.closeSession();
        }

        try {
        	//log("HERE TEST");
            template = getTemplate(templateName);
            response.setHeader("Template Returned", "Success");
        } catch (Exception e) {
        	log(e.toString());
            e.printStackTrace();
        }
        
        return template;
    }

}