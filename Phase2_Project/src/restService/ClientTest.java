package restService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.RequestBuilder;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

public class ClientTest {

	public static void main(String[] args) {

		WebResource service2 = Client.create().resource("http://localhost:8080/");
		
		
		List<Stringteger> liste = new ArrayList<Stringteger>();
		
		//liste = service2.path("sportgruppen").accept(MediaType.TEXT_PLAIN).get(ArrayList.class);
		liste = service2.path("sportgruppen").type(MediaType.TEXT_PLAIN).get(ArrayList.class);
//		
//		for (Stringteger item: liste) {
//			   System.out.println(item.zahl + " " + item.zeichenkette);
//			}
	}
	
}
