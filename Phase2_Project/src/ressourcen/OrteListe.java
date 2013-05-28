package ressourcen;
import generated.Ort;
import generated.OrteM;
import generated.Sportverzeichnis;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import jaxb.Unmarshalling;


@Path("orte")
public class OrteListe {

Sportverzeichnis sv;
	
	public OrteListe() throws Exception{
		// Unmarshalling
		Unmarshalling um = new Unmarshalling();
		sv = um.xmlUnmarshallen();
	}
	
	//Hole Orte-Liste
	/**
	 * 
	 * Orte-Liste per GET angefordert.
	 * Gibt die konkreten Orte innerhalb der OrteListe aus.
	 * 
	 * MIME-TYPE: text/plain. Momentan noch keine Unterstützung für application/xml.
	 * @return 
	 * @throws Exception
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getOrte() throws Exception {
		
		String ausgabe = "";
	
		OrteM om = (OrteM) sv.getOrteM(); 
		
		for (int i = 0; i < om.getOrt().size(); i++) {
			
			// konkreter Ort
			Ort o = (Ort) om.getOrt().get(i);
			ausgabe += o.getId() + " " + o.getOName() + "\n";
			
		}
		return ausgabe;
	}
}
