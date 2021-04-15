package menu;

import java.util.ArrayList;
import java.util.List;

public class MenuHelper {
	
	public static List<menu.MenuItem> hentMeny(){
		
		List<menu.MenuItem> menyliste = new ArrayList<menu.MenuItem>();
		
		menyliste.add(new menu.MenuItem(1,"","Forside"));
		menyliste.add(new menu.MenuItem(2,"spill","Spill"));
		menyliste.add(new menu.MenuItem(3,"spillere","Spillere"));
		
		return menyliste;
		
		
	}

}
