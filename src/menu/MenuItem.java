package menu;

public class MenuItem {
	
	private String url = "";
	private String navn = "";
	private int num_id = 0;
	
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
