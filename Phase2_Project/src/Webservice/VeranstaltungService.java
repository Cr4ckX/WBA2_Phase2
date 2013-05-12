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
	/**
	 * 
	 * Sportgruppen-Liste per GET angefordert.
	 * Gibt die konkreten Sportgruppen innerhalb der Sportgruppenliste aus.
	 * Diese Ausgabe ist ein(!) String, das heißt, die einzelnen Sporgruppen wurden
	 * nicht als Liste (o.Ä) übergeben, sondern wurden zu einem String konkateniert.
	 * 
	 * MIME-TYPE: text/plain. Momentan noch keine Unterstützung für application/xml.
	 * @return konkrete Sportgruppen aus der Sportgruppen-Liste
	 * @throws Exception
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
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
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{spgId}")
	public String getSportgruppe(@PathParam("spgId") String spgId)
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
	/**
	 * Sportarten-Liste per GET angefordert.
	 * Bei der Ausgabe handelt es sich um einen zusammengesetzen String der einzelnen Sportarten. 
	 * 
	 * MIME-TYPE: text/plain. Momentan noch keine Unterstützung für application/xml.
	 * 
	 * @param spgId Sportgruppen URI-Parameter. Hierbei handelt es sich um die übergebene id in der URI.
	 * @return liefert die einzelnen konkreten Sportarten der zugehörigen Sportgruppe
	 * @throws Exception
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{spgId}/sportarten")
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
	public String getSportart(@PathParam("spgId") String spgId,
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
	public String getVeranstaltungen(
			@PathParam("spgId") String spgId,
			@PathParam("spaId") String spaId,
			@QueryParam("deleted") boolean deleted) throws Exception {

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
							
							
							if (deleted == true){
								if (v.isDeleted() == true){
									ausgabe += v.getId() + " " + v.getVBeschreibung();
									
									//Damit am Ende nicht noch ein "\n" angefügt wird.
									if(m+1<s.getVeranstaltungenM().getVeranstaltung().size())
										ausgabe += "\n";
								}
							}
							else if (deleted == false){
								if (v.isDeleted() == false){
									ausgabe += v.getId() + " " + v.getVBeschreibung();
									
									//Damit am Ende nicht noch ein "\n" angefügt wird.
									if(m+1<s.getVeranstaltungenM().getVeranstaltung().size())
										ausgabe += "\n";
								}
							}
							
							//Standard / keine Eingabe.
							else{
								if (v.isDeleted() == false){
									ausgabe += v.getId() + " " + v.getVBeschreibung();
									
										//Damit am Ende nicht noch ein "\n" angefügt wird.
										if(m+1<s.getVeranstaltungenM().getVeranstaltung().size())
											ausgabe += "\n";
								}
							}
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
	public String getVeranstaltung(@PathParam("spgId") String spgId,
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
							
							
							if (v.getId().equals(vstId) && v.isDeleted() == false){
								return "Beschreibung: " + v.getVBeschreibung() +
										"\nInfo: " + v.getVInfo() + 
										"\nDatum " + v.getVDatum() +
										"\nUhrzeit: " + v.getVUhrzeit() + 
										"\nNiveau: " + v.getVNiveau() + 
										"\nVorraussetzungen: " + v.getVVorraussetzungen();
							}
							else if(v.getId().equals(vstId)){
								return "Die Veranstaltung mit der id " + vstId + " ist gelöscht und daher nicht aufrufbar.";
							}
						}							
					}					
				}			
			}
		}

		return "Keine Infos zu der Veranstaltung/falsche Veranstaltung";
	}
		
	//PUT - Setze Veranstaltungselemente
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
							
							int aktuelleVeranstaltungId = Integer.parseInt(v.getId());
							aktuelleVeranstaltungId = aktuelleVeranstaltungId + 1;
							System.out.print(aktuelleVeranstaltungId);
							if (v.getId().equals(vstId) && v.isDeleted() == false){
								
								 //ID wird vom Client bestimmt.
								 uebergabe.setId(vstId);
								 
								 s.getVeranstaltungenM().getVeranstaltung().set(m, uebergabe);

								 //Attribut "deleted" ist auf false gesetzt.
								 v.setDeleted(false);
								 
								 Marshaller marshaller = jc.createMarshaller();
								 marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
								 //Output
								 marshaller.marshal(sv, System.out);
								 
								return "Veranstaltung mit der ID " +vstId+ " aktualisiert";
							}	
						}	
						//TODO: Veranstaltung mittels Put hinzufügen
					}
				}
			}
		}
		return "Das aktualisieren der Veranstaltung mit der ID " + vstId + " hat nicht funktioniert.";	
	}
	
	//DELETE - Lösche Veranstaltung
	/**
	 * 
	 * "Löscht" die gewünschte Veranstaltung - Das 'deleted' Attribut wird auf true gesetzt. Die Veranstaltung
	 * bleibt weiterhin vorhanden, jedoch wird sie bei der Abfrage nicht mehr gelistet, da dort nur Veranstaltungen
	 * mit dem Attribut 'deleted=false' behandelet werden. 
	 * Kaskadierendes Löschen ist noch nicht berücksichtigt.
	 * Die Returnwerte müssen noch angepasst werden und am besten mit Exceptions behandelt werden
	 * 
	 * @param spgId - In welcher Sportgruppe befindet sich die Veranstaltung?
	 * @param spaId - Zu welcher Sportart wird die Veranstaltung ausgetragen?
	 * @param vstId - Um welche Veranstaltung handelt es sich genau? (Zu löschende Veranstaltung)
	 * @return Gelöscht - Wenn die gewünschte Veranstaltung erfolgreich gelöscht wird.
	 * Fehlgeschlagen.. - Wenn die Veranstaltung aufgrund von Fehlern nicht gelöscht wird.
	 * @throws Exception - Für das unmarshallen. (TOODOO: Sollte noch bearbeitet werden)
	 */
	@DELETE
	@Path("/{spgId}/sportarten/{spaId}/veranstaltungen/{vstId}")
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
					
					if(spaId.equals(s.getId()))
					{	
						 for (int m = 0; m < s.getVeranstaltungenM().getVeranstaltung().size(); m++){
							 Veranstaltung v = (Veranstaltung) s.getVeranstaltungenM().getVeranstaltung().get(m);
							 
							 System.out.println("Übergebene ID: "+ vstId + ", Veranstaltung-ID: " + v.getId());
							 if (vstId.equals(v.getId()))
							 {
								 //Remove erwartet Index, nicht ID ! (Deswegen rm(m)!)
								 //s.getVeranstaltungenM().getVeranstaltung().remove(m);
								 
								 v.setDeleted(true);
								 Marshaller marshaller = jc.createMarshaller();
								 marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
								
								 //Output: Konsole
								 marshaller.marshal(sv, System.out);
								return "Veranstaltung mit der id " + vstId + " wurde gelöscht."; //Kaskadierendes Löschen fehlt noch.
							 }
						 }		
					}
				}
			}
		}

		return "Fehlgeschlagen die Veranstaltungs-ID " + vstId + " zu löschen";
	}		
	
	//POST - Füge Veranstaltung hinzu
	/**
	 * 	 
	 *Die POST-Übergabe muss die XML-Struktur einer Veranstaltung aufweisen:
	 * <Veranstaltung>
	 * 		<VBeschreibung>VeranstaltungAdd</VBeschreibung>
	 * 		<VDatum>2014-04-27</VDatum>
	 * 		<VUhrzeit>15:00:00</VUhrzeit>
	 * 		<GebaeudeIDRef>G00</GebaeudeIDRef>
	 * 		<VeranstalterIDRef>VT00</VeranstalterIDRef>
	 * </Veranstaltung>
	 * 
	 * TODO - Text anpassen:"Die Ressource wird vom Server gegeben."
	 * @param spgId
	 * @param spaId
	 * @param uebergabe
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/{spgId}/sportarten/{spaId}/veranstaltungen/")
	@Consumes (MediaType.APPLICATION_XML)
	@Produces (MediaType.APPLICATION_XML)
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
			
							//Index: Die wievielte Veranstaltung in zu dieser Sportart + 1
							int index = s.getVeranstaltungenM().getVeranstaltung().size();
							
							//Setze die übergebene Veranstaltung an der Position index ein.
							s.getVeranstaltungenM().getVeranstaltung().add(index, uebergabe);
							
							//Setze die ID noch richtig (POST = Server setzt ID/Ressource)
							s.getVeranstaltungenM().getVeranstaltung().get(index).setId(String.valueOf(index));
							
							//Attribut "deleted" noch auf false setzen
							s.getVeranstaltungenM().getVeranstaltung().get(index).setDeleted(false);
							
							Marshaller marshaller = jc.createMarshaller();
							marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
							
							//Output: Console
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
