package Webservice;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ClientClass {


	public static void main(String[] args) {
		
		WebResource sportgruppen = Client.create().resource("http://localhost:8080/");
	//	System.out.println(sportgruppen.path("sportgruppen").accept(MediaType.APPLICATION_XML).get(String.class));

		
		
		ClientResponse clientResponse1 = Client.create().resource(
		          "http://localhost:8080/sportgruppen" ).get( ClientResponse.class );
		System.out.println( clientResponse1.getStatus() );                // 200
//		if ( clientResponse1.hasEntity() )
//		  System.out.println( clientResponse1.getEntity( String.class )); // Yea!
	}

}
