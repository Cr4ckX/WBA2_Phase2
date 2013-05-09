package Webservice;

import generated.Sportgruppe;
import generated.SportgruppenM;
import generated.Sportverzeichnis;

import java.io.File;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

@Path("sportgruppen")
//Nachricht-Ressource
public class Rest_Service {
	
	
	public static List<Sportgruppe> getSportgruppenListe() throws Exception{
		// Unmarshalling
		JAXBContext jc = JAXBContext.newInstance("generated");
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Sportverzeichnis sv = (Sportverzeichnis) unmarshaller.unmarshal(new File("Ausarbeitungen/XmlFuerSchema Vol2.xml"));
			
		SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM().get(0); //Hole die SportgruppenListe, im Schema nochmal anpassen, da es nur eine gibt.
		
		return sgm.getSportgruppe();
	}
	
	
	
	//Wie gehe ich mit Mengenwertigen ausgaben um?
	@GET
	@Produces(MediaType.TEXT_XML)
	public String getSporgruppen() throws Exception{
		
		// Unmarshalling
		JAXBContext jc = JAXBContext.newInstance("generated");
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Sportverzeichnis sv = (Sportverzeichnis) unmarshaller.unmarshal(new File("Ausarbeitungen/XmlFuerSchema Vol2.xml"));
			
		SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM().get(0); //Hole die SportgruppenListe, im Schema nochmal anpassen, da es nur eine gibt.
		
		for (int i = 0; i<sgm.getSportgruppe().size(); i++){
			// konrete Sportgruppe
			Sportgruppe sg = (Sportgruppe) sgm.getSportgruppe().get(i);
			System.out.println("Name der Sporgruppe: " + sg.getSGName());
		}
				
			

		
		return "";
	}
	
	
	
//	
//	@GET
//	@Produces (MediaType.TEXT_PLAIN)
//	@Path ("sport/{uriId}")
//	public String nachricht(@PathParam("uriId") String uriId) throws Exception{
//		//verarbeiten(String uriId);
////		if (uriId == sgId){
////			return sgName;
////		}
//		return ausgabe;
//	}
//	


	
}




