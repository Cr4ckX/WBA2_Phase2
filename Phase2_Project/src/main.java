import java.io.File;
import java.math.BigInteger;

import generated.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javax.ws.rs.*;

import Webservice.VeranstaltungService;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		// Unmarshalling
		JAXBContext jc = JAXBContext.newInstance("generated");
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Sportverzeichnis sv = (Sportverzeichnis) unmarshaller.unmarshal(new File("Ausarbeitungen/XmlFuerSchema Vol2.xml"));

		// Um ID später um 1 erhöhen zu können
		int anzahlVeranstaltungen = 0;

		for (int i = 0; i < sv.getSportgruppenM().size(); i++) {
			// Liste aller Sportgruppen
			SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM().get(i);

			

			
			
			
			
			
			for (int j = 0; j < sgm.getSportgruppe().size(); j++) {
				// konrete Sportgruppe
				Sportgruppe sg = (Sportgruppe) sgm.getSportgruppe().get(j);
				System.out.println("Name der Sporgruppe: " + sg.getSGName());

				for (int k = 0; k < sg.getSportartenM().size(); k++) {
					// Liste aller Sportarten dieser Sporgruppe
					SportartenM sm = (SportartenM) sg.getSportartenM().get(k);

					for (int l = 0; l < sm.getSportart().size(); l++) {
						// Konkrete Sportart
						Sportart s = (Sportart) sm.getSportart().get(l);

						System.out.println("Sportart: " + s.getSName());

						for (int m = 0; m < s.getVeranstaltungenM().getVeranstaltung().size(); m++) {
							// Konkrete Veranstaltungen (Veranstaltungsliste 1x
							// für gewisse Sportart):
							Veranstaltung v = (Veranstaltung) s.getVeranstaltungenM().getVeranstaltung().get(m);
							anzahlVeranstaltungen++;

							System.out.println("Veranstaltung: " + v.getVBeschreibung());

//							//Nachricht-Ressource
//							@Path("nachricht")
//							public class Rest_Service {
//								@GET
//								@Produces(MediaType.TEXT_PLAIN)
//								
//								public String nachricht(){
//									return "Hat funktioniert!";
//								}
							
						}

					}

				}
			}

			// Veranstalter

			// Orte

			// //Einfügen in die XML - marshallen
			// Marshaller marshaller = jc.createMarshaller();
			// marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			//
			// //Output
			// marshaller.marshal(sv, (new
			// File("Ausarbeitungen/XmlFuerSchema.xml")));
		}

	}
	
	@Path("sportgruppen")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	
	public String nachricht(){
		return "Hat funktioniert!";
	}

}
