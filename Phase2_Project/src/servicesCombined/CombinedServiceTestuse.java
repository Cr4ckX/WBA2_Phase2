package servicesCombined;

import java.util.List;

public class CombinedServiceTestuse {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CombinedServicesVeranstalter csv = new CombinedServicesVeranstalter();
		List<String> testListe;
		try {
			csv.initialize();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		boolean info;
	//	info = csv.deleteVeranstaltung("0", "0", "0");
		/*info = csv.postVeranstaltung("0", "0", neueVeranstaltung); - ERST POSTEN WENN MARSHALLING INTEGRIERT!*/
		//System.out.println(info);
		
	
		testListe = csv.getSportgruppen();
		for(String konkreteSportgruppe:testListe){
			System.out.println(konkreteSportgruppe);
		}
	}

}
