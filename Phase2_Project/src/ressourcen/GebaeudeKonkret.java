package ressourcen;

import generated.Gebaeude;
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

@Path("/orte/{oId}/gebaeude/{gId}")
public class GebaeudeKonkret {
	
Sportverzeichnis sv;
	
	public GebaeudeKonkret() throws Exception{
		// Unmarshalling
		Unmarshalling um = new Unmarshalling();
		sv = um.xmlUnmarshallen();
	}


	/**
	 * Konkretes Gebaeude per GET angefordert.
	 * Gibt das konkrete GebŠude in Form eines JAXB-Objects('Gebaeude') zurueck.
	 * 
	 * MIME-TYPE: application/xml.
	 * @param oId Orte URI-Parameter. Hierbei handelt es sich um die Ÿbergebende ID in der URI.
	 * @param gId Gebaeude URI-Parameter. Hierbei handelt es sich um die Ÿbergebende ID in der URI. 
	 * @return liefert das entsprechende Ort-JABX Object bzw. null, wenn id nicht vorhanden.
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Gebaeude getGebaeude(
			@PathParam("oId") String oId,
			@PathParam("gId") String gId){

		OrteM om = (OrteM) sv.getOrteM();
		
		if(Integer.parseInt(oId) > om.getOrt().size() -1){
			System.out.println("Die angeforderte Ort-ID ist nicht vorhanden.");
			return null;
		}
		
		Ort o = (Ort) om.getOrt().get(Integer.parseInt(oId));
		GebaeudeM gm = (GebaeudeM) o.getGebaeudeM();
		
		if(Integer.parseInt(gId) > gm.getGebaeude().size() -1){
			System.out.println("Die angeforderte Ort-ID ist nicht vorhanden.");
			return null;
		}
		Gebaeude g = gm.getGebaeude().get(Integer.parseInt(gId));
		return g;
	}
}
