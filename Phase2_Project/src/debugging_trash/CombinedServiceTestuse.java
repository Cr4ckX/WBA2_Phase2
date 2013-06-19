package debugging_trash;

import generated.Veranstaltung;

import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

import servicesCombined.CombinedServicesVeranstalter;
/**
 * Diese Klasse wird dazu verwendet die CombinedServices-Klassen zu testen.
 * Wenn keine weiteren Fehler gefunden werden und die Kombinierung funktioniert,
 * so kann diese Klasse gelšscht werden.
 * @author CrackX
 *
 */
public class CombinedServiceTestuse {


	public static void main(String[] args) throws DatatypeConfigurationException {
		CombinedServicesVeranstalter csv = new CombinedServicesVeranstalter();
		List<String> testListe;

		
		try {
			csv.initialize();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		boolean info;
	//	info = csv.deleteVeranstaltung("0", "0", "0");
		/*info = csv.postVeranstaltung("0", "0", neueVeranstaltung); - ERST POSTEN WENN MARSHALLING INTEGRIERT!*/
		//System.out.println(info);

		Veranstaltung v = csv.buildVeranstaltung("Testveranstaltung", "", csv.buildXMLDate(2013, 03, 14), csv.buildXMLTime(18, 12), "niedrig", "", null, "0");
	
	//	csv.postVeranstaltung("0", "0", v);
		csv.deleteVeranstaltung("0", "0", "0");
//		testListe = csv.getSportgruppen();
//		for(String konkreteSportgruppe:testListe){
//			System.out.println(konkreteSportgruppe);
//		}
	}

}
