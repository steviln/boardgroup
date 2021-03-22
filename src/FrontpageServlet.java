import org.apache.velocity.Template;

import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;
import datastore.player;
import org.hibernate.*;

import com.mysql.cj.log.Log;

import hibernate.HibernateUtil;

import java.util.Iterator;
import java.util.List;

import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;
import org.apache.velocity.app.Velocity;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 


@SuppressWarnings("serial")
public class FrontpageServlet extends VelocityViewServlet {
	

	@Override
    public Template handleRequest(HttpServletRequest request,HttpServletResponse response,Context context) {
        Template template = null;
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transact = null;
        
        try {
        	transact = session.beginTransaction();
        	List players = session.createQuery("FROM player").list();
        	for (Iterator iterate = players.iterator(); iterate.hasNext();){
        		player spiller = (player) iterate.next();
        		log(spiller.getFornavn() + ' ' + spiller.getEtternavn());
        	}
        	
        	transact.commit();
        }catch(Exception e) {
        	log(e.toString());
        }finally {
        	session.close();
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
