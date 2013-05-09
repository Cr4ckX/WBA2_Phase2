import generated.Sportart;
import generated.SportartenM;
import generated.Sportgruppe;
import generated.SportgruppenM;
import generated.Sportverzeichnis;
import generated.Veranstaltung;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

public class Unmarshallen {

	// Unmarshalling
	JAXBContext jc;
	Unmarshaller unmarshaller;
	Sportverzeichnis sv;


	public void xmlUnmarshallen() throws Exception{
		jc = JAXBContext.newInstance("generated");
		unmarshaller = jc.createUnmarshaller();
		sv = (Sportverzeichnis) unmarshaller.unmarshal(new File("Ausarbeitungen/XmlFuerSchema Vol2.xml"));
	}
	

	
	@Path("")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	
	public String sportgruppen(){
		return "Hat funktioniert!";
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{id}")
	public String sportgruppen(@PathParam("id") String id){
		return String.format("ID = %s", id);
		
	}
	
	public void xmlDurchlaufen() throws Exception{
		
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
	
	
}