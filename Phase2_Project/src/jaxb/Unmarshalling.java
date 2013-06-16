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
	
//	public Unmarshalling() throws Exception  {
//		jc = JAXBContext.newInstance("generated");
//		unmarshaller = jc.createUnmarshaller();
//		
//		Sportverzeichnis sv = (Sportverzeichnis) unmarshaller.unmarshal(new File("Ausarbeitungen/XmlFuerSchema Vol2.xml"));
//	}
//	
//	public Unmarshalling(String xmlPfad) throws Exception{
//		jc = JAXBContext.newInstance("generated");
//		unmarshaller = jc.createUnmarshaller();
//		Sportverzeichnis sv =  (Sportverzeichnis) unmarshaller.unmarshal(new File(xmlPfad));
//				
//	//	return sv = (Sportverzeichnis) unmarshaller.unmarshal(new File(xmlPfad));	
//	}

	public Sportverzeichnis xmlUnmarshallen() throws Exception{
		jc = JAXBContext.newInstance("generated");
		unmarshaller = jc.createUnmarshaller();
		
		return sv = (Sportverzeichnis) unmarshaller.unmarshal(new File("Ausarbeitungen/XmlFuerSchema Vol2.xml"));
	}
	
	public Sportverzeichnis xmlUnmarshallen(String xmlPfad) throws Exception{
		jc = JAXBContext.newInstance("generated");
		unmarshaller = jc.createUnmarshaller();
				
		return sv = (Sportverzeichnis) unmarshaller.unmarshal(new File(xmlPfad));		
	}
}
