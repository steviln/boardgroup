package session;

import hibernate.HibernateUtil;
import interfaces.ConnectionCheck;

public class AjaxDbSession implements ConnectionCheck{
	
	private org.hibernate.Session dbsession = null;
	
	public AjaxDbSession() {
		this.openSession();
	}
	
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

}
