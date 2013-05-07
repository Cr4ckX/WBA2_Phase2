package minirestwebservice;

import javax.ws.rs.*;

@Path ("/helloworld")
public class HalloWeltService {

	@GET @Produces ("text/plain")
	public String halloText(@QueryParam("name") String name)
	   {
	      return "Hallo " + name;
	   }

	//Optional; ist eine zweite Repräsentation 
   @GET @Produces("text/html")
   public String halloHtml(@QueryParam("name") String name)
	   {
	      return "<html><title>HelloWorld</title><body><h2>Html: Hallo " + name + "</h2></body></html>";
	   }
	
}
