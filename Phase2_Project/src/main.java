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
		System.out.println("Dies ist ein Test");
		
		// TODO Auto-generated method stub

	}

	
	public static void ausgabe () throws Exception{
		
		
		// Vorgaben durch Erstellung
				JAXBContext jc = JAXBContext.newInstance("generated");
				Unmarshaller unmarshaller = jc.createUnmarshaller();
				Sportverzeichnis sv = (Sportverzeichnis) unmarshaller.unmarshal(new File(
						"src/AUfgabe3d.xml"));

		
	}
}
