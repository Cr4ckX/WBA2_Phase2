package ressourcen;

import generated.Sportgruppe;
import generated.SportgruppenM;
import generated.Sportverzeichnis;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import restService.Stringteger;

import jaxb.Unmarshalling;

@Path("sportgruppen")
public class SportgruppenListe {
	
	Sportverzeichnis sv;
	
	public SportgruppenListe() throws Exception{
		// Unmarshalling
		Unmarshalling um = new Unmarshalling();
		sv = um.xmlUnmarshallen();
	}
	
	//Hole Sportgruppen-Liste
	/**
	 * 
	 * Sportgruppen-Liste per GET angefordert.
	 * Gibt die konkreten Sportgruppen innerhalb der Sportgruppenliste aus.
	 * Diese Ausgabe ist ein(!) String, das heißt, die einzelnen Sporgruppen wurden
	 * nicht als Liste (o.Ä) übergeben, sondern wurden zu einem String konkateniert.
	 * 
	 * MIME-TYPE: text/plain. Momentan noch keine Unterstützung für application/xml.
	 * @return konkrete Sportgruppen aus der Sportgruppen-Liste
	 * @throws Exception
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public List<Stringteger> getSportgruppen() throws Exception {
		
		List<Stringteger> liste = new ArrayList<Stringteger>();
		String ausgabe = "";

		SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM(); 
		
		for (int i = 0; i < sgm.getSportgruppe().size(); i++) {
			
			// konkrete Sportgruppe
			Sportgruppe sg = (Sportgruppe) sgm.getSportgruppe().get(i);
			ausgabe = sg.getId() + " " + sg.getSGName() + "\n";
			Stringteger si = new Stringteger(sg.getSGName(), Integer.parseInt(sg.getId()));
			
			liste.add(si);
	
		}
		return liste;
	}
}
