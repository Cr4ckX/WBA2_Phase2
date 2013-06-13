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
	 * Konkrete Sportgruppe per GET angefordert.
	 * Lieftert das JAXB-Object 'Sportgruppe'. Aus diesem können dann für die Sportgruppe
	 * zugehörigen Informationen ausgelesen werden.
	 * 
	 * MIME-TYPE: application/xml.
	 * 
	 * @param spgId Sportgruppen URI-Parameter. Hierbei handelt es sich um die übergebene id in der URI.
	 * @return Liefert Sportgruppeninformationen bei vorhandener id. Bei nichtvorhandener id wird NULL zurückgegeben.
	 * 
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Sportgruppe getSportgruppe(@PathParam("spgId") String spgId){

		SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM();
		Sportgruppe sg = (Sportgruppe) sgm.getSportgruppe().get(Integer.parseInt(spgId));

		if (spgId.equals(sg.getId())){
			return sg;
		}
		
		System.out.println("Die angeforderte Sporgruppen-ID ist nicht vorhanden");
		return null;
	}
}
