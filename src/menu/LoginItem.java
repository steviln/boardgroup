package menu;

import session.Session;

public class LoginItem extends MenuItem {	
	
	public LoginItem(int tnum, int altnum, String ul, String nam,session.Session websec) {
		this.url = ul;
		this.navn = nam;
		this.num_id = tnum;
		if(websec.getUserID() > 0) {
			this.num_id = altnum;
			this.navn = "Log ut";
		}
	}

}
