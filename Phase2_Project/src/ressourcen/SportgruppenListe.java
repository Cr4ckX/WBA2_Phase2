package ressourcen;


import generated.SportgruppenM;
import generated.Sportverzeichnis;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
	 * Sportgruppen-Liste per GET anfordern.
	 * 
	 * MIME-TYPE: application/xml.
	 * @return Sportgruppen-Liste
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public SportgruppenM getSportgruppen(){
		
		SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM();     
		return sgm;
	}
}
