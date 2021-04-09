import org.apache.velocity.Template;

import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

import org.hibernate.*;
import com.mysql.cj.log.Log;

import hibernate.HibernateUtil;
import datastore.player;
import datastore.playerRanking;
import datastore.gameList;
import interfaces.*;

import java.util.Iterator;
import java.util.List;

import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;
import org.apache.velocity.app.Velocity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 


@SuppressWarnings("serial")
public class FrontpageServlet extends VelocityViewServlet implements ConnectionCheck{
	
	private Session session = null;
	
	public void openSession() {
		this.session = HibernateUtil.getSessionFactory().openSession();
	}
	
	public void closeSession() {
		if(this.session != null) {
			this.session.close();
			this.session = null;
		}
	}
	
	public void finalize() {
		this.closeSession();
	}
	

	@Override
    public Template handleRequest(HttpServletRequest request,HttpServletResponse response,Context context) {
        Template template = null;                
        Transaction transact = null;
        
        this.openSession();
        
        try {
        	transact = this.session.beginTransaction();
        	List<playerRanking> players = session.createQuery("SELECT v FROM playerRanking v",playerRanking.class).getResultList();
        	context.put("players", players);
        	List<gameList> games = session.createQuery("SELECT a FROM gameList a",gameList.class).getResultList();
        	context.put("games", games);
        	
        	transact.commit();
        }catch(Exception e) {
        	log(e.toString());
        }finally {
        	this.closeSession();
        }

        try {
        	//log("HERE TEST");
            template = getTemplate("hello.vm");
            response.setHeader("Template Returned", "Success");
        } catch (Exception e) {
        	log(e.toString());
            e.printStackTrace();
        }
        
        return template;
    }
	


}
