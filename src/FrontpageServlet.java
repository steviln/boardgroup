import org.apache.velocity.Template;
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
