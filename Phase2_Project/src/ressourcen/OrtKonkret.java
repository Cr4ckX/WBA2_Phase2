package ressourcen;

import generated.Ort;
import generated.OrteM;
import generated.Sportverzeichnis;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jaxb.Unmarshalling;

@Path("/orte/{oId}/")
/**
 * Klasse fŸr die HTTP-Methoden auf die Ressource 'Konkreter Ort' (innerhalb der Orte-Liste).
 * @author CrackX
 *
 */
public class OrtKonkret {
	
Sportverzeichnis sv;
	
	public OrtKonkret() throws Exception{
		// Unmarshalling
		Unmarshalling um = new Unmarshalling();
		sv = um.xmlUnmarshallen();
	}

	
	/**
	 * Konkreter Ort per GET angefordert.
	 * Liefert ein Object des Typs: 'OrtÔ (JAXB)
	 * 
	 * 
	 * MIME-TYPE: application/xml.
	 * @param oId Orte URI-Parameter. Hierbei handelt es sich um die Ÿbergebende ID in der URI.
	 * @return liefert das entsprechende Ort-JABX Object bzw. null, wenn id nicht vorhanden.
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Ort getGebaeude(
			@PathParam("oId") String oId){

		OrteM om = (OrteM) sv.getOrteM();
		
		if(Integer.parseInt(oId) > om.getOrt().size() -1){
			System.out.println("Die angeforderte Ort-ID ist nicht vorhanden.");
			return null;
		}
		
		Ort o = (Ort) om.getOrt().get(Integer.parseInt(oId));
		return o;
		}
}
