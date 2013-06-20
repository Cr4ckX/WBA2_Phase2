package jaxb;

import generated.Sportverzeichnis;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Klasse zum vereinfachten unmarshallen.
 * @author CrackX
 *
 */
public class Unmarshalling {

	JAXBContext jc;
	Unmarshaller unmarshaller;
	Sportverzeichnis sv;
	
	/**
	 * Standard XML-Datei unmarshallen und in JAXB-Instanzen wandeln.
	 * @return Das Wurzelelement 'Sportverzeichnis' des festgelegten XML-Schemas als JAXB-Instanz. 
	 * @throws Exception Wenn das Unmarshallen fehlschlägt.
	 */
	public Sportverzeichnis xmlUnmarshallen() throws Exception{
		jc = JAXBContext.newInstance("generated");
		unmarshaller = jc.createUnmarshaller();
		
		return sv = (Sportverzeichnis) unmarshaller.unmarshal(new File("Ausarbeitungen/XmlFuerSchema Vol2.xml"));
	}
	/**
	 * Übergebene XML-Datei in JAXB-Instanzen unmarshallen.
	 * @param xmlPfad Pfad der XML-Datei die unmarshallt werden soll.
	 * @return Das Wurzelelement 'Sportverzeichnis' des festgelegten XML-Schemas als JAXB-Instanz. 
	 * @throws Exception Wenn das Unmarshallen fehlschlägt.
	 */
	public Sportverzeichnis xmlUnmarshallen(String xmlPfad) throws Exception{
		jc = JAXBContext.newInstance("generated");
		unmarshaller = jc.createUnmarshaller();
				
		return sv = (Sportverzeichnis) unmarshaller.unmarshal(new File(xmlPfad));		
	}
}
