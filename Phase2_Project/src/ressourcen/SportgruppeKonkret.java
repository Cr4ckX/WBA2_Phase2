package ressourcen;

import generated.Sportgruppe;
import generated.SportgruppenM;
import generated.Sportverzeichnis;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
	 * Konkrete Sportgruppe per GET angefordert.
	 * Lieftert das JAXB-Object 'Sportgruppe'. Aus diesem k�nnen dann f�r die Sportgruppe
	 * zugeh�rigen Informationen ausgelesen werden.
	 * 
	 * MIME-TYPE: application/xml.
	 * 
	 * @param spgId Sportgruppen URI-Parameter. Hierbei handelt es sich um die �bergebene id in der URI.
	 * @return Liefert Sportgruppeninformationen bei vorhandener id. Bei nichtvorhandener id wird NULL zur�ckgegeben.
	 * 
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Sportgruppe getSportgruppe(@PathParam("spgId") String spgId){

		SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM();
		
		if(Integer.valueOf(spgId) > sgm.getSportgruppe().size() -1){
			System.out.println("Die angeforderte Sporgruppen-ID ist nicht vorhanden.");
			return null;
		}
		
		Sportgruppe sg = (Sportgruppe) sgm.getSportgruppe().get(Integer.parseInt(spgId));
		return sg;

	}
}
