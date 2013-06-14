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
	 * Orte-Liste per GET angefordert.
	 * Liefert die Orte-Liste als 'OrteM' Object.
	 * 
	 * MIME-TYPE: application/xml.
	 * @return Die Liste aller Orte als JAXB-Object.
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public OrteM getOrte(){
		
		OrteM om = (OrteM) sv.getOrteM(); 
		return om;
	}
}
