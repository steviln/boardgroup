
import javax.servlet.http.*;
import javax.servlet.*;  
import java.io.*;
import session.*;



@SuppressWarnings("serial")
public class AjaxServlet extends HttpServlet {
	
	private session.Session websession = null;
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException  {  
		PrintWriter pw = res.getWriter();//get the stream to write the data  
		this.executeRequest(req,res,pw);
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException  {  
		PrintWriter pw = res.getWriter();//get the stream to write the data  
		this.executeRequest(req,res,pw);
	}	
	
	private void executeRequest(HttpServletRequest req,HttpServletResponse res,PrintWriter pw) {
		//res.setContentType("text/html");//setting the content type  
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		
		session.Parameter parameter = new session.Parameter(req.getRequestURL().toString(), req.getServletPath());
		websession = session.SessionHandler.init_session(req, null);
		
		String kommando = parameter.getCommand();
		
		//System.out.println("kommando er: " + kommando.toString());
		
		if(kommando.equals("getsession")) {
			SessionHandler.printJsonSession(pw, websession);
		}else if(kommando.equals("login")) {
			SessionHandler.doLogin(req, pw);
		}
		
		  
		//writing html in the stream  
  
		  
		pw.close();//closing the stream  
	}

}
