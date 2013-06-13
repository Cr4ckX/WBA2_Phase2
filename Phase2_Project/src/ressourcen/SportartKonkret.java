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

@Path("sportgruppen/{spgId}/sportarten/{spaId}/")
public class SportartKonkret {
	
	Sportverzeichnis sv;
	
	public SportartKonkret() throws Exception{
		// Unmarshalling
		Unmarshalling um = new Unmarshalling();
		sv = um.xmlUnmarshallen();
	}
	

	//Hole zu der konkreten Sportart die zugehörigen Informationen
	@GET
	@Produces(MediaType.APPLICATION_XML)
	/**
	 * 
	 * @param spgId spgId Sportgruppen URI-Parameter. Hierbei handelt es sich um die übergebene id in der URI.
	 * @param spaId spgId Sportarten URI-Parameter. Hierbei handelt es sich um die übergebene id in der URI.
	 * @return Gibt die Informationen der angeforderteren Sportart zurück. Ist die URI-Id nicht vorhanden, 
	 * so wird eine Fehlermeldung ausgegeben.
	 * @throws Exception
	 * 
	 * Konkrete Sportart per GET angefordert.
	 * Bei der Ausgabe handelt es sich um einen zusammengesetzen String der einzelnen Elemente der Sportarteninformationen. 
	 * 
	 * MIME-TYPE: text/plain. Momentan noch keine Unterstützung für application/xml.
	 */
	public Sportart getSportart(@PathParam("spgId") String spgId,
			@PathParam("spaId") String spaId) throws Exception {

		//Sportgruppen (mehrzahl)
		SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM();		
		//Sportgruppe (einzahl)
		Sportgruppe sg = sgm.getSportgruppe().get(Integer.parseInt(spgId));		
		//Sportarten (mehrzahl)
		SportartenM sm = sg.getSportartenM();
		//Sportarte (einzahl)
		Sportart s = sm.getSportart().get(Integer.parseInt(spaId));

		if(spgId.equals(sg.getId()) && spaId.equals(s.getId())){
			return s;
		}
			
		System.out.println("Die angeforderte Sporgruppen-ID oder Sportart-ID ist nicht vorhanden");
		return null;
	}

	
}
