package Webservice;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.RequestBuilder;

import com.sun.jersey.api.*;

public class ClientClass {


	public static void main(String[] args) {
		
		WebResource sportgruppenRes = Client.create().resource("http://localhost:8080/sportgruppen");
			Builder sportgruppe = sportgruppenRes.path("SG00").accept(MediaType.TEXT_PLAIN);
				Builder sportarten = sportgruppenRes.path("SG00").path("sportarten").accept(MediaType.TEXT_PLAIN);
					Builder sportart = sportgruppenRes.path("SG00").path("sportarten").path("S00").accept(MediaType.TEXT_PLAIN);
						Builder veranstaltungen = sportgruppenRes.path("SG00").path("sportarten").path("S00").path("veranstaltungen").
								accept(MediaType.TEXT_PLAIN);
							Builder veranstaltung = sportgruppenRes.path("SG00").path("sportarten").path("S00").path("veranstaltungen").path("V00").
									accept(MediaType.TEXT_PLAIN);
							
//		WebResource sportgruppeRes = Client.create().resource("http://localhost:8080/sportgruppen/{id}");
//		WebResource sportartenRes = Client.create().resource("http://localhost:8080/sportgruppen/{id}/sportarten");
		
							
		System.out.println(sportgruppe.get(String.class));
		System.out.println("");
		System.out.println(sportarten.get(String.class));
		System.out.println("");
		System.out.println(sportart.get(String.class));
		System.out.println("");
		System.out.println(veranstaltungen.get(String.class));
		System.out.println("");
		System.out.println(veranstaltung.get(String.class));
		
				
		ClientRequest clientRequest1 = Client.create().resource("http://localhost:8080/sportgruppen/SG00/sportarten/S00/veranstaltungen/V00").get(ClientRequest.class);
		clientRequest1.setEntity(clientRequest1);
		
		
		
		sportgruppenRes.path("SG00").path("sportarten").path("S00")
				.path("veranstaltungen").path("V00").accept(MediaType.TEXT_PLAIN)
				.put(clientRequest1.getEntity());
		System.out.println(veranstaltung.get(String.class));
		
	//	System.out.println(sportgruppen.path("sportgruppen").accept(MediaType.APPLICATION_XML).get(String.class));

		
//		
//		ClientResponse clientResponse1 = Client.create().resource(
//		          "http://localhost:8080/sportgruppen" ).get(ClientResponse.class);
//		System.out.println( clientResponse1.getStatus() );                
//		if (clientResponse1.hasEntity())
//		  System.out.println(clientResponse1.getEntity( String.class)); 
		

		
	}

}
