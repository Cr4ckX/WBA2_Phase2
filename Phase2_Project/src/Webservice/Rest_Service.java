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
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.XMLGregorianCalendar;

@Path("sportgruppen")
// Nachricht-Ressource
public class Rest_Service {

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
	
	//Hole zu der konkreten Sportart die zugehšrigen Informationen
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
									// Konkrete Veranstaltungen (Veranstaltungsliste 1x fŸr gewisse Sportart):
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
									// Konkrete Veranstaltungen (Veranstaltungsliste 1x fŸr gewisse Sportart):
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
	
	
	
	@PUT
	@Path("/{spgId}/sportarten/{spaId}/veranstaltungen/{vstId}")
	@Consumes (MediaType.TEXT_PLAIN)
	@Produces (MediaType.TEXT_PLAIN)
	public String putVeranstaltung(@PathParam("spgId") String spgId,
			@PathParam("spaId") String spaId, @PathParam("vstId") String vstId,
			String beschreibung, String info, XMLGregorianCalendar datum, XMLGregorianCalendar uhrzeit, 
			String niveau, String voraussetzungen) throws Exception{
		

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
									// Konkrete Veranstaltungen (Veranstaltungsliste 1x fŸr gewisse Sportart):
									Veranstaltung v = (Veranstaltung) s.getVeranstaltungenM().getVeranstaltung().get(m);
									
									if (v.getId().equals(vstId)){
										
										v.setVBeschreibung(beschreibung);
										v.setVInfo(info);
										v.setVDatum(datum);
										v.setVUhrzeit(uhrzeit);
										v.setVNiveau(niveau);
										v.setVVorraussetzungen(voraussetzungen);
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
	
}
