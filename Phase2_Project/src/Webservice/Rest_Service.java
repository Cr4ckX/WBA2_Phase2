package Webservice;

import generated.Sportart;
import generated.SportartenM;
import generated.Sportgruppe;
import generated.SportgruppenM;
import generated.Sportverzeichnis;

import java.io.File;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

@Path("sportgruppen")
// Nachricht-Ressource
public class Rest_Service {

	String ausgabe = "";

	// public static List<Sportgruppe> getSportgruppenListe() throws Exception{
	// // Unmarshalling
	// JAXBContext jc = JAXBContext.newInstance("generated");
	// Unmarshaller unmarshaller = jc.createUnmarshaller();
	// Sportverzeichnis sv = (Sportverzeichnis) unmarshaller.unmarshal(new
	// File("Ausarbeitungen/XmlFuerSchema Vol2.xml"));
	//
	// SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM().get(0); //Hole
	// die SportgruppenListe, im Schema nochmal anpassen, da es nur eine gibt.
	//
	// return sgm.getSportgruppe();
	// }

	// Wie gehe ich mit Mengenwertigen ausgaben um?
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getSporgruppen() throws Exception {

		// Unmarshalling
		JAXBContext jc = JAXBContext.newInstance("generated");
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Sportverzeichnis sv = (Sportverzeichnis) unmarshaller
				.unmarshal(new File("Ausarbeitungen/XmlFuerSchema Vol2.xml"));

		SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM().get(0); // Hole die SporgruppenListe, im Schema nochmal anpassen, da es nur eine gibt. 
		
		for (int i = 0; i < sgm.getSportgruppe().size(); i++) {
			// konrete Sportgruppe
			Sportgruppe sg = (Sportgruppe) sgm.getSportgruppe().get(i);
			ausgabe += sg.getId() + " " + sg.getSGName() + "\n";
		}

		return ausgabe;
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{uriId}")
	public String getSportgruppen(@PathParam("uriId") String uriId)
			throws Exception {

		// Unmarshalling
		JAXBContext jc = JAXBContext.newInstance("generated");
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Sportverzeichnis sv = (Sportverzeichnis) unmarshaller
				.unmarshal(new File("Ausarbeitungen/XmlFuerSchema Vol2.xml"));

		SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM().get(0); // Hole die SporgruppenListe, im Schema nochmal anpassen, da es nur eine gibt. 

		for (int i = 0; i < sgm.getSportgruppe().size(); i++) {
			// konrete Sportgruppe
			Sportgruppe sg = (Sportgruppe) sgm.getSportgruppe().get(i);

			System.out.println("SGID = " + sg.getId() + "| UriID = " + uriId);
			if (uriId.equals(sg.getId())) {
				return sg.getId() + " " + sg.getSGName();
			}

		}
		return "Failture";
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{uriId}/sportarten")
	public String getSportarten(@PathParam("uriId") String uriId) throws Exception {

		// Unmarshalling
		JAXBContext jc = JAXBContext.newInstance("generated");
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Sportverzeichnis sv = (Sportverzeichnis) unmarshaller
				.unmarshal(new File("Ausarbeitungen/XmlFuerSchema Vol2.xml"));

		for (int i = 0; i < sv.getSportgruppenM().size(); i++) {
			// Liste aller Sportgruppen
			SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM().get(i);

			for (int j = 0; j < sgm.getSportgruppe().size(); j++) {
				// konrete Sportgruppe
				Sportgruppe sg = (Sportgruppe) sgm.getSportgruppe().get(j);

		//		System.out.println("SGID = " + sg.getId() + "| UriID = " + uriId);
				if (uriId.equals(sg.getId())) {
					
					for (int k = 0; k < sg.getSportartenM().size(); k++) {
						// Liste aller Sportarten dieser Sportgruppe
						SportartenM sm = (SportartenM) sg.getSportartenM().get(k);

						for (int l = 0; l < sm.getSportart().size(); l++) {
							// Konkrete Sportart
							Sportart s = (Sportart) sm.getSportart().get(l);
							
							ausgabe += s.getId() + " " + s.getSName() + "\n";
							return ausgabe;
						}
					
					}

				}

			}
		}
		return "Failture";
	}
}
