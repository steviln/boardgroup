package menu;

import java.util.ArrayList;
import session.Session;
import java.util.List;

public class MenuHelper {
	
	public static List<menu.MenuItem> hentMeny(session.Session websession){
		
		List<menu.MenuItem> menyliste = new ArrayList<menu.MenuItem>();
		
		menyliste.add(new menu.MenuItem(1,"","Forside"));
		menyliste.add(new menu.MenuItem(2,"spill/list","Spill"));
		menyliste.add(new menu.MenuItem(3,"spillere","Spillere"));
		//menyliste.add(new menu.LoginItem(100,101,"login","Login",websession));
		
		return menyliste;
		
		
	}

}
