package ressourcen;

import generated.Gebaeude;
import generated.GebaeudeM;
import generated.Ort;
import generated.OrteM;
import generated.Sportverzeichnis;
import generated.VeranstalterM;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import jaxb.Unmarshalling;

@Path("/orte/{oId}/gebaeude/{gId}")
public class VeranstalterListe {
	
Sportverzeichnis sv;

	public VeranstalterListe() throws Exception{
		// Unmarshalling
		Unmarshalling um = new Unmarshalling();
		sv = um.xmlUnmarshallen();
	}
	
	/**
	 * Veranstalter-Liste per GET angefordert.
	 * Gibt die Veranstalter-Liste in Form eines JAXB-Objects('VeranstalterM') zurueck.
	 * 
	 * MIME-TYPE: application/xml.
	 * @return liefert das entsprechende Veranstalterlisten-JABX Object bzw. null, wenn id nicht vorhanden.
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public VeranstalterM getVeranstalter(){
		
		VeranstalterM vrm = (VeranstalterM) sv.getVeranstalterM();
		return vrm;
	}
}