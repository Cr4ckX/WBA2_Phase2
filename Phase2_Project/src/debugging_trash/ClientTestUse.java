package debugging_trash;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

import restService.ClientRest;

import generated.Veranstaltung;
import generated.VeranstaltungenM;
/**
 * Diese Klasse zeigt die Verwendung des Clients beispielhaft. Sie dient hauptsŠchlich zur intenen Verwendung (Debugging,..)
 * und sollte daher spŠter nicht Bestandteil des Systems sein.
 * @author CrackX
 *
 */
public class ClientTestUse {

	public static void main(String[] args) throws DatatypeConfigurationException {

		ClientRest ct = new ClientRest();
		System.out.println(ct.getSportgruppen());
		
		//SportgruppenM spg = ct.getSportgruppen();
		//spg.getSportgruppe().size();  -> Um Groesse festzustellen.
		
		System.out.println(ct.getSportgruppe("3"));
		System.out.println(ct.getSportarten("0"));
		System.out.println(ct.getSportart("0", "0"));
		
		//VeranstaltungenM vm = ct.getVeranstaltungen("0", "0", true);
		VeranstaltungenM vm = ct.getVeranstaltungen("0", "0", false);
		for(int i = 0; i < vm.getVeranstaltung().size(); i++){
			System.out.println(vm.getVeranstaltung().get(i).getVBeschreibung());
		}
		System.out.println(ct.getVeranstaltung("0", "0", "0"));
		System.out.println(ct.getOrte());
		System.out.println(ct.getOrt("0"));
		System.out.println(ct.getVeranstalterM());
		System.out.println(ct.getVeranstalter("0"));
		
		XMLGregorianCalendar time = ct.buildXMLTime(03,00);
		XMLGregorianCalendar date = ct.buildXMLDate(2013, 03, 15);
		
		Veranstaltung newVeranstaltung = ct.buildVeranstaltung(
		 		"Test",	"Info", date, time,	"schwer", "keine", "", "0");
		
		int neueVeranstaltungsId = ct.postVeranstaltung("0", "0", newVeranstaltung);
		System.out.println(neueVeranstaltungsId);
		//ct.deleteVeranstaltung("0", "0", "0");
		ct.putVeranstaltung("0", "0", "0", newVeranstaltung);
	}
}
