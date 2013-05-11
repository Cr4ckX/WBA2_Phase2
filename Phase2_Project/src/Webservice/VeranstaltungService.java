package Webservice;

import generated.Sportart;
import generated.SportartenM;
import generated.Sportgruppe;
import generated.SportgruppenM;
import generated.Sportverzeichnis;
import generated.Veranstaltung;

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
// Nachricht-Ressource
public class VeranstaltungService {

	String ausgabe = "";
	String sportartinfo = "";

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
	
	
	// ------------------------------------------------- Test ------------------------------------------------- //
	@Path("Test")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<SportartenM> getTest() throws Exception {

		// Unmarshalling
		JAXBContext jc = JAXBContext.newInstance("generated");
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Sportverzeichnis sv = (Sportverzeichnis) unmarshaller
				.unmarshal(new File("Ausarbeitungen/XmlFuerSchema Vol2.xml"));
 

		return sv.getSportgruppenM().get(0).getSportgruppe().get(0).getSportartenM();
	}
	
	// ------------------------------------------------- Test ------------------------------------------------- //

	
	
	
	
	
	
	
	
	//Hole Sportgruppen-Liste
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

	//Hole konkrete Sportgruppe
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{spgId}")
	public String getSportgruppen(@PathParam("spgId") String spgId)
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

			System.out.println("SGID = " + sg.getId() + "| spgId = " + spgId);
			if (spgId.equals(sg.getId())) {
				return sg.getId() + " " + sg.getSGName();
			}

		}
		return "Failture falsche Sportgruppe";
	}

	//Hole zur konkreten Sportgruppe die Sportarten-Liste
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{spgId}/sportarten")
	public String getSportarten(@PathParam("spgId") String spgId) throws Exception {

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

		//		System.out.println("SGID = " + sg.getId() + "| spgId = " + spgId);
				if (spgId.equals(sg.getId())) {
					
					for (int k = 0; k < sg.getSportartenM().size(); k++) {
						// Liste aller Sportarten dieser Sportgruppe
						SportartenM sm = (SportartenM) sg.getSportartenM().get(k);

						for (int l = 0; l < sm.getSportart().size(); l++) {
							// Konkrete Sportart
							Sportart s = (Sportart) sm.getSportart().get(l);
							
							ausgabe += s.getId() + " " + s.getSName();// + "\n";
							if(l+1 < sm.getSportart().size())
								ausgabe += "\n";

						}
				
					}
					return ausgabe;	
				}

			}
		}
		return "Failture (keine Sportarten in dieser Gruppe/Falsche Gruppe)";
	}
	
	//Hole zu der konkreten Sportart die zugehörigen Informationen
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{spgId}/sportarten/{spaId}/")
	public String getSportarten(@PathParam("spgId") String spgId,
			@PathParam("spaId") String spaId) throws Exception {

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

	
				if (spgId.equals(sg.getId())) {
					
					for (int k = 0; k < sg.getSportartenM().size(); k++) {
						// Liste aller Sportarten dieser Sportgruppe
						SportartenM sm = (SportartenM) sg.getSportartenM().get(k);

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

		for (int i = 0; i < sv.getSportgruppenM().size(); i++) {
			// Liste aller Sportgruppen
			SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM().get(i);

			for (int j = 0; j < sgm.getSportgruppe().size(); j++) {
				// konrete Sportgruppe
				Sportgruppe sg = (Sportgruppe) sgm.getSportgruppe().get(j);

	
				if (spgId.equals(sg.getId())) {
					
					for (int k = 0; k < sg.getSportartenM().size(); k++) {
						// Liste aller Sportarten dieser Sportgruppe
						SportartenM sm = (SportartenM) sg.getSportartenM().get(k);

						for (int l = 0; l < sm.getSportart().size(); l++) {
							// Konkrete Sportart
							Sportart s = (Sportart) sm.getSportart().get(l);
							
							if (spaId.equals(s.getId()) && s.getVeranstaltungenM().getVeranstaltung().size() > 0) {
								for (int m = 0; m < s.getVeranstaltungenM().getVeranstaltung().size(); m++) {
									// Konkrete Veranstaltungen (Veranstaltungsliste 1x für gewisse Sportart):
									Veranstaltung v = (Veranstaltung) s.getVeranstaltungenM().getVeranstaltung().get(m);
									
									ausgabe += v.getId() + " " + v.getVBeschreibung();// + "\n";
									if(m+1<s.getVeranstaltungenM().getVeranstaltung().size())
										ausgabe += "\n";
									
									
								}
								return ausgabe;
							}
							
						}
				
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

		for (int i = 0; i < sv.getSportgruppenM().size(); i++) {
			// Liste aller Sportgruppen
			SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM().get(i);

			for (int j = 0; j < sgm.getSportgruppe().size(); j++) {
				// konrete Sportgruppe
				Sportgruppe sg = (Sportgruppe) sgm.getSportgruppe().get(j);

	
				if (spgId.equals(sg.getId())) {
					
					for (int k = 0; k < sg.getSportartenM().size(); k++) {
						// Liste aller Sportarten dieser Sportgruppe
						SportartenM sm = (SportartenM) sg.getSportartenM().get(k);

						for (int l = 0; l < sm.getSportart().size(); l++) {
							// Konkrete Sportart
							Sportart s = (Sportart) sm.getSportart().get(l);
							
							if (spaId.equals(s.getId()) && s.getVeranstaltungenM().getVeranstaltung().size() > 0) {
								for (int m = 0; m < s.getVeranstaltungenM().getVeranstaltung().size(); m++) {
									// Konkrete Veranstaltungen (Veranstaltungsliste 1x für gewisse Sportart):
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

		for (int i = 0; i < sv.getSportgruppenM().size(); i++) {
			// Liste aller Sportgruppen
			SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM().get(i);

			for (int j = 0; j < sgm.getSportgruppe().size(); j++) {
				// konrete Sportgruppe
				Sportgruppe sg = (Sportgruppe) sgm.getSportgruppe().get(j);

	
				if (spgId.equals(sg.getId())) {
					
					for (int k = 0; k < sg.getSportartenM().size(); k++) {
						// Liste aller Sportarten dieser Sportgruppe
						SportartenM sm = (SportartenM) sg.getSportartenM().get(k);

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

		for (int i = 0; i < sv.getSportgruppenM().size(); i++) {
			// Liste aller Sportgruppen
			SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM().get(i);

			for (int j = 0; j < sgm.getSportgruppe().size(); j++) {
				// konrete Sportgruppe
				Sportgruppe sg = (Sportgruppe) sgm.getSportgruppe().get(j);

	
				if (spgId.equals(sg.getId())) {
					
					for (int k = 0; k < sg.getSportartenM().size(); k++) {
						// Liste aller Sportarten dieser Sportgruppe
						SportartenM sm = (SportartenM) sg.getSportartenM().get(k);

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
			}
		}
		return "Fehlgeschlagen die Veranstaltungs-ID " + vstId + " zu löschen";
	}		
	
	//POST - Füge Veranstaltung hinzu
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

		for (int i = 0; i < sv.getSportgruppenM().size(); i++) {
			// Liste aller Sportgruppen
			SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM().get(i);

			for (int j = 0; j < sgm.getSportgruppe().size(); j++) {
				// konrete Sportgruppe
				Sportgruppe sg = (Sportgruppe) sgm.getSportgruppe().get(j);

	
				if (spgId.equals(sg.getId())) {
					
					for (int k = 0; k < sg.getSportartenM().size(); k++) {
						// Liste aller Sportarten dieser Sportgruppe
						SportartenM sm = (SportartenM) sg.getSportartenM().get(k);

						for (int l = 0; l < sm.getSportart().size(); l++) {
							// Konkrete Sportart
							Sportart s = (Sportart) sm.getSportart().get(l);
							
							if (spaId.equals(s.getId()) && s.getVeranstaltungenM().getVeranstaltung().size() > 0) {
								//for (int m = 0; m < s.getVeranstaltungenM().getVeranstaltung().size(); m++) {
									
									int index = s.getVeranstaltungenM().getVeranstaltung().size();
									
									//s.getVeranstaltungenM().getVeranstaltung().set(index, uebergabe); -> für PUT?
									
									
									s.getVeranstaltungenM().getVeranstaltung().add(index, uebergabe);
									s.getVeranstaltungenM().getVeranstaltung().get(index).setId("V02");
									Marshaller marshaller = jc.createMarshaller();
									marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

									//Output
									marshaller.marshal(sv, System.out);

									return "Veranstaltung " + s.getVeranstaltungenM().getVeranstaltung()
											.get(index).getVBeschreibung() + " hinzugefügt.";
									
									
								//}
								
							}
							
						}
				
					}

				}

			}
		}
		return "Hinzufügen fehlgeschlagen!";
		
	}
	

	
}
