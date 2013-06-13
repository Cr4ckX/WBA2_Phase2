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
public class SportartenListe {
	
	Sportverzeichnis sv;
	
	public SportartenListe(){
		// Unmarshalling
		Unmarshalling um = new Unmarshalling();
		try {
			sv = um.xmlUnmarshallen();
		} catch (Exception e) {
			System.out.println("Fehler beim Unmarshallen!");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Hole zur konkreten Sportgruppe die Sportarten-Liste
	/**
	 * Sportarten-Liste per GET angefordert.
	 * Bei der Ausgabe handelt es sich um einen zusammengesetzen String der einzelnen Sportarten. 
	 * 
	 * Lieftert das JAXB-Object 'SportartenM'.
	 * 
	 * MIME-TYPE: application/xml.
	 * 
	 * @param spgId Sportgruppen URI-Parameter. Hierbei handelt es sich um die Ÿbergebene id in der URI.
	 * @return liefert Sportarten-Liste bzw. NULL wenn Sportgruppen-ID falsch. 
	 * @throws Exception
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public SportartenM getSportarten(@PathParam("spgId") String spgId) throws Exception {

		//Sportgruppen (mehrzahl)
		SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM();		
		//Sportgruppe (einzahl)
		Sportgruppe sg = sgm.getSportgruppe().get(Integer.parseInt(spgId));		
		//Sportarten (mehrzahl)
		SportartenM sm = sg.getSportartenM();
		
		if (spgId.equals(sg.getId())){
			return sm;
		}
		
		System.out.println("Die angeforderte Sporgruppen-ID ist nicht vorhanden");
		return null;	
		
	}
}
