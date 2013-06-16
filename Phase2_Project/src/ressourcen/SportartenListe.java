package ressourcen;

import generated.SportartenM;
import generated.Sportgruppe;
import generated.SportgruppenM;
import generated.Sportverzeichnis;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import jaxb.Unmarshalling;

@Path("sportgruppen/{spgId}/sportarten")
/**
 * Klasse fŸr die HTTP-Methoden auf die Ressource 'Sportarten-Liste' (innerhalb einer Sportgruppe).
 * @author CrackX
 *
 */
public class SportartenListe {
	
	Sportverzeichnis sv;
	
	public SportartenListe(){
		// Unmarshalling
		Unmarshalling um = new Unmarshalling();
		try {
			sv = um.xmlUnmarshallen();
		} catch (Exception e) {
			System.out.println("Fehler beim Unmarshallen!");
			e.printStackTrace();
		}
	}
	
	//Hole zur konkreten Sportgruppe die Sportarten-Liste
	/**
	 * Sportarten-Liste per GET angefordert.
	 * Liefert das JAXB-Object 'SportartenM'.
	 * 
	 * MIME-TYPE: application/xml.
	 * 
	 * @param spgId Sportgruppen URI-Parameter. Hierbei handelt es sich um die Ÿbergebene id in der URI.
	 * @return Sportarten-Liste (JAXB-Object) bzw. NULL wenn Sportgruppen-ID falsch. 
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public SportartenM getSportarten(@PathParam("spgId") String spgId){

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
		

		return sm;
		
		
	}
}
