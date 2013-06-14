package ressourcen;

import generated.Gebaeude;
import generated.GebaeudeM;
import generated.Ort;
import generated.OrteM;
import generated.Sportverzeichnis;
import generated.Veranstalter;
import generated.VeranstalterM;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import jaxb.Unmarshalling;

@Path("/orte/{oId}/gebaeude/{gId}")
public class VeranstalterKonkret {
	
Sportverzeichnis sv;

	public VeranstalterKonkret() throws Exception{
		// Unmarshalling
		Unmarshalling um = new Unmarshalling();
		sv = um.xmlUnmarshallen();
	}
	
	/**
	 * Konkreter Veranstalter per GET angefordert.
	 * Gibt den konkreten Veranstalter in Form eines JAXB-Objects('Veranstalter') zurueck.
	 * 
	 * MIME-TYPE: application/xml.
	 * @param vrId Veranstalter URI-Parameter. Hierbei handelt es sich um die Ÿbergebende ID in der URI.
	 * @return liefert das entsprechende Veranstalter-JABX Object bzw. null, wenn id nicht vorhanden.
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Veranstalter getVeranstalter(String vrId){
		
		VeranstalterM vrm = (VeranstalterM) sv.getVeranstalterM();
		
		if(Integer.parseInt(vrId) > vrm.getVeranstalter().size() -1){
			System.out.println("Die angeforderte Veranstalter-ID ist nicht vorhanden.");
			return null;
		}
		Veranstalter vr = vrm.getVeranstalter().get(Integer.parseInt(vrId));
		return vr;
	}
}