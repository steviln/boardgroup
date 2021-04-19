package menu;

import session.Session;

public class MenuItem {
	
	protected String url = "";
	protected String navn = "";
	protected int num_id = 0;
	
	public MenuItem() {
		
	}
	
	public MenuItem(int tnum, String ul, String nam) {
		this.url = ul;
		this.navn = nam;
		this.num_id = tnum;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public String getNavn() {
		return this.navn;
	}
	
	public int getNumId() {
		return this.num_id;
	}
	

	

}
