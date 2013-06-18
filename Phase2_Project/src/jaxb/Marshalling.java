package jaxb;

import java.io.File;

import generated.Sportverzeichnis;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

/**
 * Klasse zum vereinfachten marshalling.
 * @author CrackX
 *
 */
public class Marshalling {

	JAXBContext jc;
	Marshaller marshaller;
	Sportverzeichnis sv;

	public void xmlMarshallen() throws Exception{
		marshaller = jc.createMarshaller();
	    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    marshaller.marshal(sv, new File("Ausarbeitungen/XmlFuerSchema Vol2.xml"));

	}
	
	public void xmlMarshallen(String xmlPfad) throws Exception{
		marshaller = jc.createMarshaller();
	    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    marshaller.marshal(sv, new File(xmlPfad));

	}
}
