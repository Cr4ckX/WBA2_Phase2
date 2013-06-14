package restService;

import generated.VeranstaltungenM;
/**
 * Diese Klasse zeigt die Verwendung des Clients beispielhaft.
 * @author CrackX
 *
 */
public class ClientTestUse {

	public static void main(String[] args) {

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
	}
}
