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
				"Ausarbeitungen/XmlFuerSchema.xml"));
		
		for (int i = 0; i < sv.getVeranstaltungenM().size(); i++) {
			System.out.println(sv.getVeranstaltungenM().get(i).getVeranstaltung().getVBeschreibung());		
			//System.out.println(sv.getVeranstaltungenM().get(1).getVeranstaltung().getVBeschreibung());		

		}
				
		
//	//EinfŸgen in die XML - marshallen
//			Marshaller marshaller = jc.createMarshaller();
//			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//			
//			//Output
//			marshaller.marshal(sv, (new File("Ausarbeitungen/XmlFuerSchema.xml")));
}
	
	}
