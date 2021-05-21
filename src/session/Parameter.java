package session;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class Parameter {
	
	private String command = "";
	private List<String> subcommands = new ArrayList<String>();
	private List<Integer> idnumbs = new ArrayList<Integer>();
	
	public Parameter(String urlstring, String mapper) {
		
		mapper = mapper.replace("/", "").trim();		
		int phase = 0;		
		String[] urldeler = urlstring.split("/");
		
		for(String check : urldeler) {
			check = check.trim();
			if(check.equals(mapper)) {
				phase = 1;
			}else if(phase == 1) {
				if(StringUtils.isNumeric(check)) {
					this.idnumbs.add(Integer.parseInt(check));
				}else if(this.command.equals("")) {
					this.command = check;
				}else {
					this.subcommands.add(check);
				}
			}
		}
	}
	
	public String getCommand() {
		return this.command;
	}
	
	public List<String> getSubcommands() {
		return this.subcommands;
	}
	
	public List<Integer> getIdnumbs() {
		return this.idnumbs;
	}

}
