package ressourcen;

import generated.GebaeudeM;
import generated.Ort;
import generated.OrteM;
import generated.Sportverzeichnis;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jaxb.Unmarshalling;

@Path("/orte/{oId}/gebaeude")
/**
 * Klasse fŸr die HTTP-Methoden auf die Ressource 'GebŠude-Liste' innerhalb eines definierten Ortes.
 * @author CrackX
 *
 */
public class GebaeudeListe {
	
Sportverzeichnis sv;
	
	public GebaeudeListe() throws Exception{
		// Unmarshalling
		Unmarshalling um = new Unmarshalling();
		sv = um.xmlUnmarshallen();
	}

	
	/**
	 * Gebaeude-Liste per GET angefordert.
	 * Gibt die GebŠude-Liste in Form eines JAXB-Objects('GebaeudeM') zurueck.
	 * 
	 * MIME-TYPE: application/xml.
	 * @param oId Orte URI-Parameter. Hierbei handelt es sich um die Ÿbergebende ID in der URI.
	 * @return liefert das entsprechende Ort-JABX Object bzw. null, wenn id nicht vorhanden.
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public GebaeudeM getGebaeude(
			@PathParam("oId") String oId){

		OrteM om = (OrteM) sv.getOrteM();
		
		if(Integer.parseInt(oId) > om.getOrt().size() -1){
			System.out.println("Die angeforderte Ort-ID ist nicht vorhanden.");
			return null;
		}
		
		Ort o = (Ort) om.getOrt().get(Integer.parseInt(oId));
		GebaeudeM gm = (GebaeudeM) o.getGebaeudeM();
		
		return gm;
	}

}
