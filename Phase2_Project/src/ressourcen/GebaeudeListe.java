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

@Path("/orte/{oId}/gebaeude")
public class GebaeudeListe {
	
Sportverzeichnis sv;
	
	public GebaeudeListe() throws Exception{
		// Unmarshalling
		Unmarshalling um = new Unmarshalling();
		sv = um.xmlUnmarshallen();
	}

	
	//Hole zur konkreten Veranstaltung die Gebaeude-Liste
	/**
	 * Gebaeude-Liste per GET angefordert.
	 * Bei der Ausgabe handelt es sich um einen zusammengesetzen String der einzelnen Gebaeude.
	 * 
	 * MIME-TYPE: text/plain. Momentan noch keine Unterstützung für application/xml.
	 * @param oId Orte URI-Parameter. Hierbei handelt es sich um die übergebende ID in der URI.
	 * @return liefert die einzelnden konkreten Gebäude der zugehörigen Orte.
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
		
			if (oId.equals(o.getId())) {
			
				// Liste aller Gebaeude dieses Ortes
				GebaeudeM gm = (GebaeudeM) o.getGebaeudeM();

				for (int l = 0; l < gm.getGebaeude().size(); l++) {
					// Konkretes Gebaeude
					Gebaeude g = (Gebaeude) gm.getGebaeude().get(l);

					ausgabe += g.getId() + " " + g.getGName();
					
					//Damit am Ende nicht noch ein "\n" angefügt wird.
					if(l+1 < gm.getGebaeude().size())
						ausgabe += "\n";
					
				}	
			return ausgabe;	
			}
		}

		return "Failture (kein Gebaeude in diesem Ort /Falscher Ort)";
		}
}
