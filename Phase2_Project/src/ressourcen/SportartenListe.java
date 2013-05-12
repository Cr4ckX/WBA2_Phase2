package ressourcen;

import generated.Sportart;
import generated.SportartenM;
import generated.Sportgruppe;
import generated.SportgruppenM;
import generated.Sportverzeichnis;


import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import jaxb.Unmarshalling;

@Path("sportgruppen/{spgId}/sportarten")
public class SportartenListe {
	
	Sportverzeichnis sv;
	
	public SportartenListe() throws Exception{
		// Unmarshalling
		Unmarshalling um = new Unmarshalling();
		sv = um.xmlUnmarshallen();
	}
	
	//Hole zur konkreten Sportgruppe die Sportarten-Liste
	/**
	 * Sportarten-Liste per GET angefordert.
	 * Bei der Ausgabe handelt es sich um einen zusammengesetzen String der einzelnen Sportarten. 
	 * 
	 * MIME-TYPE: text/plain. Momentan noch keine Unterstützung für application/xml.
	 * 
	 * @param spgId Sportgruppen URI-Parameter. Hierbei handelt es sich um die übergebene id in der URI.
	 * @return liefert die einzelnen konkreten Sportarten der zugehörigen Sportgruppe
	 * @throws Exception
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getSportarten(@PathParam("spgId") String spgId) throws Exception {

		String ausgabe = "";

		SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM();

		for (int j = 0; j < sgm.getSportgruppe().size(); j++) {
			// konkrete Sportgruppe
			Sportgruppe sg = (Sportgruppe) sgm.getSportgruppe().get(j);

			if (spgId.equals(sg.getId())) {
				
				// Liste aller Sportarten dieser Sportgruppe
				SportartenM sm = (SportartenM) sg.getSportartenM();

				for (int l = 0; l < sm.getSportart().size(); l++) {
					// Konkrete Sportart
					Sportart s = (Sportart) sm.getSportart().get(l);
					
					ausgabe += s.getId() + " " + s.getSName();
					
					//Damit am Ende nicht noch ein "\n" angefügt wird.
					if(l+1 < sm.getSportart().size())
						ausgabe += "\n";

				}	
				return ausgabe;	
			}
		}
		return "Failture (keine Sportarten in dieser Gruppe/Falsche Gruppe)";
	}
}
