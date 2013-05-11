package Webservice;

import generated.*;

import java.io.File;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.XMLGregorianCalendar;

@Path("sportgruppen")
//Evtl. in mehrere Klassen aufteilen
public class VeranstaltungService {

	String ausgabe = "";
	//String sportartinfo = "";

	/*
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

	*/

	// Wie gehe ich mit Mengenwertigen ausgaben um?
	
	
	// ------------------------------------------------- GET XML Repräsentation ------------------------------------------------- //
	@Path("Test")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	/**
	 * 
	 * @return über gibt die Sportarten-Liste der Sporgruppe.
	 * @throws Exception
	 * 
	 * Test GET-Methode.
	 * Hierbei ist der MIME-Type application/xml.
	 * Besonderheit: Eine Liste wird übergeben. Nicht wie bei den anderen GET-Methoden,
	 * dort wird ein konkatenierter String übergeben. Diese Liste könnte evtl. noch nützlich
	 * werden, sollte mit dem Client gearbeitet werden. Der Client könnte dann diese Liste als Entität
	 * übermittelt bekommen und dann XML Elemente einzelnd auswerten (so ist die Idee).
	 */
	public SportartenM getTest() throws Exception {

		// Unmarshalling
		JAXBContext jc = JAXBContext.newInstance("generated");
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Sportverzeichnis sv = (Sportverzeichnis) unmarshaller
				.unmarshal(new File("Ausarbeitungen/XmlFuerSchema Vol2.xml"));
 
		//TODO: Checken !!, return-Type keine Liste mehr !!
		return sv.getSportgruppenM().getSportgruppe().get(0).getSportartenM();
	}
	// ------------------------------------------------- GET XML Repräsentation ------------------------------------------------- //

	
	//Hole Sportgruppen-Liste
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	/**
	 * 
	 * @return konkrete Sportgruppen aus der Sportgruppen-Liste
	 * @throws Exception
	 * 
	 * Sportgruppen-Liste per GET angefordert.
	 * Gibt die konkreten Sportgruppen innerhalb der Sportgruppenliste aus.
	 * Diese Ausgabe ist ein(!) String, das heißt, die einzelnen Sporgruppen wurden
	 * nicht als Liste (o.Ä) übergeben, sondern wurden zu einem String konkateniert.
	 * 
	 * MIME-TYPE: text/plain. Momentan noch keine Unterstützung für application/xml.
	 */
	public String getSportgruppen() throws Exception {

		// Unmarshalling
		JAXBContext jc = JAXBContext.newInstance("generated");
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Sportverzeichnis sv = (Sportverzeichnis) unmarshaller
				.unmarshal(new File("Ausarbeitungen/XmlFuerSchema Vol2.xml"));

		SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM(); 
		
		for (int i = 0; i < sgm.getSportgruppe().size(); i++) {
			// konrete Sportgruppe
			Sportgruppe sg = (Sportgruppe) sgm.getSportgruppe().get(i);
			ausgabe += sg.getId() + " " + sg.getSGName() + "\n";
		}

		return ausgabe;
	}

	//Hole konkrete Sportgruppe(ninformationen)
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{spgId}")
	/**
	 * 
	 * @param spgId Sportgruppen URI-Parameter. Hierbei handelt es sich um die übergebene id in der URI.
	 * @return Liefert Sportgruppeninformationen bei vorhandener id. Bei nichtvorhandener id wird 
	 * ein Fehler in textueller Form ausgegeben. TODO: exception-Handling wäre wünschenswert.
	 * @throws Exception
	 * 
	 * Konkrete Sporgruppe per GET angefordert.
	 * Es wird die zugehörigen Sporgruppeninformationen (Name + Beschreibung) in textueller
	 * form (return value: String) ausgegeben. 
	 * 
	 * MIME-TYPE: text/plain. Momentan noch keine Unterstützung für application/xml.
	 */
	public String getSportgruppen(@PathParam("spgId") String spgId)
			throws Exception {

		// Unmarshalling
		JAXBContext jc = JAXBContext.newInstance("generated");
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Sportverzeichnis sv = (Sportverzeichnis) unmarshaller
				.unmarshal(new File("Ausarbeitungen/XmlFuerSchema Vol2.xml"));

		SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM();

		for (int i = 0; i < sgm.getSportgruppe().size(); i++) {
			// konrete Sportgruppe
			Sportgruppe sg = (Sportgruppe) sgm.getSportgruppe().get(i);

			//Wenn übergebene URI-id mit Sportgruppenid übereinstimmt, gib die Infos aus.
			if (spgId.equals(sg.getId())) {
				return	"Name: " +sg.getSGName() + 
						"\nBeschreibung: " + sg.getSGBeschreibung();
			}
		}
		return "Failture falsche Sportgruppe";
	}

	//Hole zur konkreten Sportgruppe die Sportarten-Liste
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{spgId}/sportarten")
	/**
	 * 
	 * @param spgId Sportgruppen URI-Parameter. Hierbei handelt es sich um die übergebene id in der URI.
	 * @return liefert die einzelnen konkreten Sportarten der zugehörigen Sportgruppe
	 * @throws Exception
	 * 
	 * Sportarten-Liste per GET angefordert.
	 * Bei der Ausgabe handelt es sich um einen zusammengesetzen String der einzelnen Sportarten. 
	 * 
	 * MIME-TYPE: text/plain. Momentan noch keine Unterstützung für application/xml.
	 */
	public String getSportarten(@PathParam("spgId") String spgId) throws Exception {

		// Unmarshalling
		JAXBContext jc = JAXBContext.newInstance("generated");
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Sportverzeichnis sv = (Sportverzeichnis) unmarshaller
				.unmarshal(new File("Ausarbeitungen/XmlFuerSchema Vol2.xml"));

		SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM();

		for (int j = 0; j < sgm.getSportgruppe().size(); j++) {
			// konrete Sportgruppe
			Sportgruppe sg = (Sportgruppe) sgm.getSportgruppe().get(j);

			if (spgId.equals(sg.getId())) {
				
				// Liste aller Sportarten dieser Sportgruppe
				SportartenM sm = (SportartenM) sg.getSportartenM();

				for (int l = 0; l < sm.getSportart().size(); l++) {
					// Konkrete Sportart
					Sportart s = (Sportart) sm.getSportart().get(l);
					
					ausgabe += s.getId() + " " + s.getSName();
					
					//Damit am Ende nicht noch ein "\n" angefügt wird.
					if(l+1 < sm.getSportart().size())
						ausgabe += "\n";

				}	
				return ausgabe;	
			}
		}
		return "Failture (keine Sportarten in dieser Gruppe/Falsche Gruppe)";
	}
	
	//Hole zu der konkreten Sportart die zugehörigen Informationen
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{spgId}/sportarten/{spaId}/")
	/**
	 * 
	 * @param spgId spgId Sportgruppen URI-Parameter. Hierbei handelt es sich um die übergebene id in der URI.
	 * @param spaId spgId Sportarten URI-Parameter. Hierbei handelt es sich um die übergebene id in der URI.
	 * @return Gibt die Informationen der angeforderteren Sportart zurück. Ist die URI-Id nicht vorhanden, 
	 * so wird eine Fehlermeldung ausgegeben.
	 * @throws Exception
	 * 
	 * Konkrete Sportart per GET angefordert.
	 * Bei der Ausgabe handelt es sich um einen zusammengesetzen String der einzelnen Elemente der Sportarteninformationen. 
	 * 
	 * MIME-TYPE: text/plain. Momentan noch keine Unterstützung für application/xml.
	 */
	public String getSportarten(@PathParam("spgId") String spgId,
			@PathParam("spaId") String spaId) throws Exception {

		// Unmarshalling
		JAXBContext jc = JAXBContext.newInstance("generated");
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Sportverzeichnis sv = (Sportverzeichnis) unmarshaller
				.unmarshal(new File("Ausarbeitungen/XmlFuerSchema Vol2.xml"));

		SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM();

		for (int j = 0; j < sgm.getSportgruppe().size(); j++) {
			// konrete Sportgruppe
			Sportgruppe sg = (Sportgruppe) sgm.getSportgruppe().get(j);

			if (spgId.equals(sg.getId())) {
				

				// Liste aller Sportarten dieser Sportgruppe
				SportartenM sm = (SportartenM) sg.getSportartenM();

				for (int l = 0; l < sm.getSportart().size(); l++) {
					// Konkrete Sportart
					Sportart s = (Sportart) sm.getSportart().get(l);
					
					if (spaId.equals(s.getId())) {
						
						return "Sportart: " + s.getSName() +
							   "\nBeschreibung: " + s.getSBeschreibung() + 
							   "\nHerkunft: " + s.getSHerkunft() +
							   "\nVorraussetzungen: " + s.getSVorraussetzung() + 
							   "\nRegeln: " + s.getSRegeln();
					}				
				}
			}

		}

		return "Keine Informationen zu der Sportart/Falsche Sportart";
	}
	
	//Hole zu der konkreten Sportart die Veranstaltungs-Liste
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{spgId}/sportarten/{spaId}/veranstaltungen")
	public String getVeranstaltungen(@PathParam("spgId") String spgId,
			@PathParam("spaId") String spaId) throws Exception {

		// Unmarshalling
		JAXBContext jc = JAXBContext.newInstance("generated");
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Sportverzeichnis sv = (Sportverzeichnis) unmarshaller
				.unmarshal(new File("Ausarbeitungen/XmlFuerSchema Vol2.xml"));
		
		SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM();

		for (int j = 0; j < sgm.getSportgruppe().size(); j++) {
			// konrete Sportgruppe
			Sportgruppe sg = (Sportgruppe) sgm.getSportgruppe().get(j);


			if (spgId.equals(sg.getId())) {
				

				// Liste aller Sportarten dieser Sportgruppe
				SportartenM sm = (SportartenM) sg.getSportartenM();

				for (int l = 0; l < sm.getSportart().size(); l++) {
					// Konkrete Sportart
					Sportart s = (Sportart) sm.getSportart().get(l);
					
					if (spaId.equals(s.getId()) && s.getVeranstaltungenM().getVeranstaltung().size() > 0) {
						for (int m = 0; m < s.getVeranstaltungenM().getVeranstaltung().size(); m++) {
							// Konkrete Veranstaltungen (Veranstaltungsliste 1x für gewisse Sportart):
							Veranstaltung v = (Veranstaltung) s.getVeranstaltungenM().getVeranstaltung().get(m);
							
							ausgabe += v.getId() + " " + v.getVBeschreibung();
							
							//Damit am Ende nicht noch ein "\n" angefügt wird.
							if(m+1<s.getVeranstaltungenM().getVeranstaltung().size())
								ausgabe += "\n";
							
							
						}
						return ausgabe;
					}					
				}
			}
		}
	
		return "Keine Veranstaltungen zu der Sportart/Falsche Sportart";
	}
	
	//Hole Infos zu der konkreten Veranstaltung
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{spgId}/sportarten/{spaId}/veranstaltungen/{vstId}")
	public String getVeranstaltungen(@PathParam("spgId") String spgId,
			@PathParam("spaId") String spaId, @PathParam("vstId") String vstId) throws Exception {

		// Unmarshalling
		JAXBContext jc = JAXBContext.newInstance("generated");
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Sportverzeichnis sv = (Sportverzeichnis) unmarshaller
				.unmarshal(new File("Ausarbeitungen/XmlFuerSchema Vol2.xml"));


		SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM();

		for (int j = 0; j < sgm.getSportgruppe().size(); j++) {
			// konrete Sportgruppe
			Sportgruppe sg = (Sportgruppe) sgm.getSportgruppe().get(j);

			if (spgId.equals(sg.getId())) {
				
				// Liste aller Sportarten dieser Sportgruppe
				SportartenM sm = (SportartenM) sg.getSportartenM();

				for (int l = 0; l < sm.getSportart().size(); l++) {
					// Konkrete Sportart
					Sportart s = (Sportart) sm.getSportart().get(l);
					
					if (spaId.equals(s.getId()) && s.getVeranstaltungenM().getVeranstaltung().size() > 0) {
						for (int m = 0; m < s.getVeranstaltungenM().getVeranstaltung().size(); m++) {
							// Konkrete Veranstaltungen 
							Veranstaltung v = (Veranstaltung) s.getVeranstaltungenM().getVeranstaltung().get(m);
							
							if (v.getId().equals(vstId)){
								return "Beschreibung: " + v.getVBeschreibung() +
										"\nInfo: " + v.getVInfo() + 
										"\nDatum " + v.getVDatum() +
										"\nUhrzeit: " + v.getVUhrzeit() + 
										"\nNiveau: " + v.getVNiveau() + 
										"\nVorraussetzungen: " + v.getVVorraussetzungen();
							}								
						}							
					}					
				}			
			}
		}

		return "Keine Veranstaltungen zu der Sportart/Falsche Sportart";
	}
	
	
	//PUT - Setze Veranstaltungselemente - noch Buggy!
	@PUT
	@Path("/{spgId}/sportarten/{spaId}/veranstaltungen/{vstId}")
	@Consumes (MediaType.APPLICATION_XML)
	@Produces (MediaType.APPLICATION_XML)
	public String putVeranstaltung(@PathParam("spgId") String spgId,
			@PathParam("spaId") String spaId, @PathParam("vstId") String vstId, Veranstaltung uebergabe) throws Exception{
		

		// Unmarshalling
		JAXBContext jc = JAXBContext.newInstance("generated");
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Sportverzeichnis sv = (Sportverzeichnis) unmarshaller
				.unmarshal(new File("Ausarbeitungen/XmlFuerSchema Vol2.xml"));


		SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM();

		for (int j = 0; j < sgm.getSportgruppe().size(); j++) {
			// konrete Sportgruppe
			Sportgruppe sg = (Sportgruppe) sgm.getSportgruppe().get(j);


			if (spgId.equals(sg.getId())) {
				
			
				// Liste aller Sportarten dieser Sportgruppe
				SportartenM sm = (SportartenM) sg.getSportartenM();

				for (int l = 0; l < sm.getSportart().size(); l++) {
					// Konkrete Sportart
					Sportart s = (Sportart) sm.getSportart().get(l);
					
					if (spaId.equals(s.getId()) && s.getVeranstaltungenM().getVeranstaltung().size() > 0) {
						for (int m = 0; m < s.getVeranstaltungenM().getVeranstaltung().size(); m++) {
							// Konkrete Veranstaltungen (Veranstaltungsliste 1x für gewisse Sportart):
							Veranstaltung v = (Veranstaltung) s.getVeranstaltungenM().getVeranstaltung().get(m);
							
							if (v.getId().equals(vstId)){
								
								 Marshaller marshaller = jc.createMarshaller();
								 marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);																				

								 v.setVBeschreibung(uebergabe.getVBeschreibung());
								 v.setVInfo(uebergabe.getVBeschreibung());
								 v.setVDatum(uebergabe.getVDatum());
								 v.setVUhrzeit(uebergabe.getVUhrzeit());
								 v.setVNiveau(uebergabe.getVNiveau());
								 v.setVVorraussetzungen(uebergabe.getVVorraussetzungen());
								 v.setGebaeudeIDRef(uebergabe.getGebaeudeIDRef());
								 v.setVeranstalterIDRef(uebergabe.getGebaeudeIDRef());
								 
								 //s.getVeranstaltungenM().getVeranstaltung().set(m, uebergabe);

								 //Output
								 marshaller.marshal(sv, System.out);

								return "Alles sauber verlaufen!";
							}	
						}	
					}
				}
			}
		}
		return "Keine Ahnung was als Return-Wert erwartet wird";	
	}
	
	//DELETE - Lösche Veranstaltung
	@DELETE
	@Path("/{spgId}/sportarten/{spaId}/veranstaltungen/{vstId}")
	/**
	 * 
	 * @param spgId - In welcher Sportgruppe befindet sich die Veranstaltung?
	 * @param spaId - Zu welcher Sportart wird die Veranstaltung ausgetragen?
	 * @param vstId - Um welche Veranstaltung handelt es sich genau? (Zu löschende Veranstaltung)
	 * @return Gelöscht - Wenn die gewünschte Veranstaltung erfolgreich gelöscht wird.
	 * @return Fehlgeschlagen.. - Wenn die Veranstaltung aufgrund von Fehlern nicht gelöscht wird.
	 * @throws Exception - Für das unmarshallen. (TOODOO: Sollte noch bearbeitet werden)
	 * 
	 * Löscht die gewünschte Veranstaltung.
	 * Kaskadierendes Löschen ist noch nicht berücksichtigt.
	 * Die Returnwerte müssen noch angepasst werden und am besten mit Exceptions behandelt werden
	 */
	public String deleteVeranstaltung(@PathParam("spgId") String spgId,
			@PathParam("spaId") String spaId, @PathParam("vstId") String vstId) throws Exception {

		// Unmarshalling
		JAXBContext jc = JAXBContext.newInstance("generated");
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Sportverzeichnis sv = (Sportverzeichnis) unmarshaller
				.unmarshal(new File("Ausarbeitungen/XmlFuerSchema Vol2.xml"));

		SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM();

		for (int j = 0; j < sgm.getSportgruppe().size(); j++) {
			// konrete Sportgruppe
			Sportgruppe sg = (Sportgruppe) sgm.getSportgruppe().get(j);


			if (spgId.equals(sg.getId())) {
				
				// Liste aller Sportarten dieser Sportgruppe
				SportartenM sm = (SportartenM) sg.getSportartenM();

				for (int l = 0; l < sm.getSportart().size(); l++) {
					// Konkrete Sportart
					Sportart s = (Sportart) sm.getSportart().get(l);
					
					 for (int m = 0; m < s.getVeranstaltungenM().getVeranstaltung().size(); m++){
						 Veranstaltung v = (Veranstaltung) s.getVeranstaltungenM().getVeranstaltung().get(m);
						 
						 System.out.println("Übergebene ID: "+ vstId + ", Veranstaltung-ID: " + v.getId());
						 if (vstId.equals(v.getId()))
						 {
							 //Remove erwartet Index, nicht ID ! (Deswegen rm(m)!)
							 s.getVeranstaltungenM().getVeranstaltung().remove(m);
							 Marshaller marshaller = jc.createMarshaller();
							 marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
							
							 //Output: Konsole
							 marshaller.marshal(sv, System.out);
							return "Gelöscht"; //Kaskadierendes Löschen fehlt noch.
						 }
					 }		
				}														
			}
		}

		return "Fehlgeschlagen die Veranstaltungs-ID " + vstId + " zu löschen";
	}		
	
	//POST - Füge Veranstaltung hinzu
	@POST
	@Path("/{spgId}/sportarten/{spaId}/veranstaltungen/")
	@Consumes (MediaType.APPLICATION_XML)
	@Produces (MediaType.APPLICATION_XML)
	/**
	 * 
	 * @param spgId
	 * @param spaId
	 * @param uebergabe
	 * @return
	 * @throws Exception
	 * 
	 * Die POST-Übergabe muss die XML-Struktur einer Veranstaltung aufweisen:
		 * <Veranstaltung>
	   	 * 		<VBeschreibung>VeranstaltungAdd</VBeschreibung>
	   	 * 		<VDatum>2014-04-27</VDatum>
	     * 		<VUhrzeit>15:00:00</VUhrzeit>
	   	 * 		<GebaeudeIDRef>G00</GebaeudeIDRef>
	  	 * 		<VeranstalterIDRef>VT00</VeranstalterIDRef>
	  	 * </Veranstaltung>
	 * TODO - Text anpassen:"Die Ressource wird vom Server gegeben."
	 */
	public String postVeranstaltung(@PathParam("spgId") String spgId,
			@PathParam("spaId") String spaId, Veranstaltung uebergabe) throws Exception{
		

		// Unmarshalling
		JAXBContext jc = JAXBContext.newInstance("generated");
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Sportverzeichnis sv = (Sportverzeichnis) unmarshaller
				.unmarshal(new File("Ausarbeitungen/XmlFuerSchema Vol2.xml"));

		SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM();

		for (int j = 0; j < sgm.getSportgruppe().size(); j++) {
			// konrete Sportgruppe
			Sportgruppe sg = (Sportgruppe) sgm.getSportgruppe().get(j);


			if (spgId.equals(sg.getId())) {
				
				// Liste aller Sportarten dieser Sportgruppe
				SportartenM sm = (SportartenM) sg.getSportartenM();

				for (int l = 0; l < sm.getSportart().size(); l++) {
					// Konkrete Sportart
					Sportart s = (Sportart) sm.getSportart().get(l);
					
					if (spaId.equals(s.getId()) && s.getVeranstaltungenM().getVeranstaltung().size() > 0) {
			
							int index = s.getVeranstaltungenM().getVeranstaltung().size();
							s.getVeranstaltungenM().getVeranstaltung().add(index, uebergabe);
							s.getVeranstaltungenM().getVeranstaltung().get(index).setId(String.valueOf(index));
							Marshaller marshaller = jc.createMarshaller();
							marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

							//Output
							marshaller.marshal(sv, System.out);

							return "Veranstaltung " + s.getVeranstaltungenM().getVeranstaltung()
									.get(index).getVBeschreibung() + " hinzugefügt.";
				
					}						
				}			
			}
		}
		return "Hinzufügen fehlgeschlagen!";
		
	}
	

	
}
