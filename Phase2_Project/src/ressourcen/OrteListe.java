package ressourcen;

import generated.OrteM;
import generated.Sportverzeichnis;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jaxb.Unmarshalling;


@Path("orte")
/**
 * Klasse für die HTTP-Methoden auf die Ressource 'Orte-Liste'
 * @author CrackX
 *
 */
public class OrteListe {

Sportverzeichnis sv;
	
	public OrteListe() throws Exception{
		// Unmarshalling
		Unmarshalling um = new Unmarshalling();
		sv = um.xmlUnmarshallen();
	}
	
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
