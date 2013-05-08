import java.io.File;
import java.math.BigInteger;

import generated.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.util.Scanner;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		// Vorgaben durch Erstellung
		JAXBContext jc = JAXBContext.newInstance("generated");
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Sportverzeichnis sv = (Sportverzeichnis) unmarshaller.unmarshal(new File(
				"Ausarbeitungen/XmlFuerSchema Vol2.xml"));
		
		
		for (int i=0; i<sv.getSportgruppenM().size(); i++){
			//Liste aller Sportgruppen
			SportgruppenM sgm = (SportgruppenM) sv.getSportgruppenM().get(i);
	
			
			for (int j=0; j<sgm.getSportgruppe().size(); j++){
				//konrete Sportgruppe
				Sportgruppe sg = (Sportgruppe) sgm.getSportgruppe().get(j);
				System.out.println("Name der Sporgruppe: " +sg.getSGName());
				
				for (int k=0; k<sg.getSportartenM().size(); k++){
					//Liste aller Sportarten dieser Sporgruppe
					SportartenM sm = (SportartenM) sg.getSportartenM().get(k);
					
					for (int l=0; l<sm.getSportart().size(); l++){
						//Konkrete Sportart
						Sportart s = (Sportart) sm.getSportart().get(l);
						
						System.out.println("Sportart: " + s.getSBeschreibung());
						
						
						for (int m=0; m<s.getVeranstaltungenM().getVeranstaltung().size(); m++){
							//Veranstaltungen (Veranstaltungsliste 1x fŸr gewisse Sportart):
							Veranstaltung v = (Veranstaltung) s.getVeranstaltungenM().getVeranstaltung().get(m);
							
							
							System.out.println("Veranstaltung: " + v.getVBeschreibung());
							
						}
						
					
					}
					
				}
			}
		}
				
		
//	//EinfŸgen in die XML - marshallen
//			Marshaller marshaller = jc.createMarshaller();
//			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//			
//			//Output
//			marshaller.marshal(sv, (new File("Ausarbeitungen/XmlFuerSchema.xml")));
}
	
	}
