package ressourcen;

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

@Path("sportgruppen/{spgId}")
public class SportgruppeKonkret {
	
	Sportverzeichnis sv;
	
	public SportgruppeKonkret() throws Exception{
		// Unmarshalling
		Unmarshalling um = new Unmarshalling();
		sv = um.xmlUnmarshallen();
	}
	
	//Hole konkrete Sportgruppe(ninformationen)
	/**
	 * 
	 * @param spgId Sportgruppen URI-Parameter. Hierbei handelt es sich um die übergebene id in der URI.
	 * @return Liefert Sportgruppeninformationen bei vorhandener id. Bei nichtvorhandener id wird 
	 * ein Fehler in textueller Form ausgegeben. TODO: exception-Handling wäre wünschenswert.
	 * @throws Exception
	 * 
	 * Konkrete Sporgruppe per GET angefordert.
	 * Es wird die zugehörigen Sporgruppeninformationen (Name + Beschreibung) in textueller
	 * form (return value: String) ausgegeben. 
	 * 
	 * MIME-TYPE: text/plain. Momentan noch keine Unterstützung für application/xml.
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getSportgruppe(@PathParam("spgId") String spgId)
			throws Exception {

		SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM();

		for (int i = 0; i < sgm.getSportgruppe().size(); i++) {
			// konrete Sportgruppe
			Sportgruppe sg = (Sportgruppe) sgm.getSportgruppe().get(i);

			//Wenn übergebene URI-id mit Sportgruppenid übereinstimmt, gib die Infos aus.
			if (spgId.equals(sg.getId())) {
				return	"Name: " +sg.getSGName() + 
						"\nBeschreibung: " + sg.getSGBeschreibung();
			}
		}
		return "Failture falsche Sportgruppe";
	}
}
