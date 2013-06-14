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
	

	//Hole zu der konkreten Sportart die zugehšrigen Informationen
	@GET
	@Produces(MediaType.APPLICATION_XML)
	/**
	 * Konkrete Sportart per GET angefordert.
	 * Liefert das JAXB-Obect 'Sportart'.
	 * 
	 * MIME-TYPE: application/xml.
	 * 
	 * @param spgId spgId Sportgruppen URI-Parameter. Hierbei handelt es sich um die Ÿbergebene id in der URI.
	 * @param spaId spgId Sportarten URI-Parameter. Hierbei handelt es sich um die Ÿbergebene id in der URI.
	 * @return Sportart (JAXB-Object) bzw. NULL wenn eine der ID's nicht stimmen.
	 * 
	 */
	public Sportart getSportart(@PathParam("spgId") String spgId,
			@PathParam("spaId") String spaId){

		//Sportgruppen (mehrzahl)
		SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM();	
		
		if(Integer.valueOf(spgId) > sgm.getSportgruppe().size() -1){
			System.out.println("Die angeforderte Sporgruppen-ID ist nicht vorhanden.");
			return null;
		}
		//Sportgruppe (einzahl)
		Sportgruppe sg = sgm.getSportgruppe().get(Integer.parseInt(spgId));		
		//Sportarten (mehrzahl)
		SportartenM sm = sg.getSportartenM();
		
		if(Integer.valueOf(spaId) > sm.getSportart().size() -1 ){
			System.out.println("Die angeforderte Sporarten-ID ist nicht vorhanden.");
			return null;
		}
		//Sportarte (einzahl)
		Sportart s = sm.getSportart().get(Integer.parseInt(spaId));

		return s;
		
	}
}
