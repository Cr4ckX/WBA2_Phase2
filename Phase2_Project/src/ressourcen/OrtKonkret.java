package ressourcen;

import generated.Gebaeude;
import generated.GebaeudeM;
import generated.Ort;
import generated.OrteM;
import generated.Sportverzeichnis;
import java.io.File;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import jaxb.Unmarshalling;

@Path("/orte/{oId}/")
public class OrtKonkret {
	
Sportverzeichnis sv;
	
	public OrtKonkret() throws Exception{
		// Unmarshalling
		Unmarshalling um = new Unmarshalling();
		sv = um.xmlUnmarshallen();
	}

	
	//Hole zur konkreten Veranstaltung die Gebaeude-Liste
	/**
	 * Gebaeude-Liste per GET angefordert.
	 * Bei der Ausgabe handelt es sich um einen zusammengesetzen String der einzelnen Gebaeude.
	 * 
	 * MIME-TYPE: text/plain. Momentan noch keine Unterst�tzung f�r application/xml.
	 * @param oId Orte URI-Parameter. Hierbei handelt es sich um die �bergebende ID in der URI.
	 * @return liefert die einzelnden konkreten Geb�ude der zugeh�rigen Orte.
	 * @throws Exception
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getGebaeude(
			@PathParam("oId") String oId) throws Exception {

		String ausgabe = "";
						
		OrteM om = sv.getOrteM();
		for (int k = 0; k < om.getOrt().size(); k++) {
			//konkreter Ort
			Ort o = (Ort) om.getOrt().get(k);
		
			if (oId.equals(o.getId())) 
				return o.getOName();	

		}

		return "Failture (kein Ort in dieser Liste /Falscher Ort)";
		}
}
