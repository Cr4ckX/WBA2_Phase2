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

	/**
	 * Standard XML-Datei nach �nderungen wieder Marshallen.
	 * @throws Exception Wenn das Marshallen fehlschl�gt.
	 */
	public void xmlMarshallen() throws Exception{
		marshaller = jc.createMarshaller();
	    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    marshaller.marshal(sv, new File("Ausarbeitungen/XmlFuerSchema Vol2.xml"));

	}
	/**
	 * �nderungen in eine andere als die standard XML-Datei schreiben.
	 * @param xmlPfad Pfad in der die XML-�nderungen gemarshallt werden sollen.
	 * @throws Exception Wenn das Marshallen fehlschl�gt.
	 */
	public void xmlMarshallen(String xmlPfad) throws Exception{
		marshaller = jc.createMarshaller();
	    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    marshaller.marshal(sv, new File(xmlPfad));

	}
}
