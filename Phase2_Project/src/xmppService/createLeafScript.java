package xmppService;

import generated.Sportart;
import generated.SportartenM;
import generated.Sportgruppe;
import generated.SportgruppenM;
import generated.Veranstaltung;
import generated.VeranstaltungenM;

import org.jivesoftware.smack.XMPPException;

import restService.ClientRest;
/**
 * Diese Klasse dient dazu, neue LeafNodes automatisch zu erstellen.
 * 
 * Die XML-Datei wird durchlaufen und zu jeder Veranstaltung und
 * Sportart wird ein eindeutiger Leaf-Node auf dem Server erzeugt.
 * @author CrackX
 *
 */
public class createLeafScript {

	public static void main(String[] args) throws XMPPException{
	
		ClientRest ct = new ClientRest();
		XmppManager xmppVerbindung = new XmppManager();
		String sportgruppenId;
		String sportartId;
		String veranstaltungId;
		int bestehendeVeranstaltungen = 0;
		int bestehendeSportarten = 0;
		
		
		if(xmppVerbindung.verbinden() == false){
			System.out.println("Verbindung konnte nicht hergestellt werden!");
			System.exit(1);
		}
		if(xmppVerbindung.managePubSub() == false){
			System.out.println("Pub-Sub Manager konnte nicht erstellt werden");
			System.exit(1);
		}
		
		if(xmppVerbindung.login("veranstalter", "veranstalter") == false){
			System.out.println("Fehler beim Einloggen");
			System.exit(1);
		}
		
		SportgruppenM sgm = ct.getSportgruppen();
		for (Sportgruppe sportgruppenKonkret : sgm.getSportgruppe()){
			Sportgruppe sg = sportgruppenKonkret;
			sportgruppenId = sg.getId();
			
			SportartenM sm = sg.getSportartenM();
			
			for (Sportart sportartKonkret : sm.getSportart()){
				Sportart s = sportartKonkret;
				sportartId = s.getId();
	
				
				VeranstaltungenM vm = s.getVeranstaltungenM();
				
				/*
				 * xySportart für das Abonnieren einer konkreten Sportart.
				 * Jedoch wird die VeranstaltungsListe (VeranstaltungenM) geholt,
				 * da dort die Veranstaltungen enthalten sind.
				 */
				if(xmppVerbindung.createLeaf(sportgruppenId + sportartId + "Sportart") == false){
					bestehendeSportarten++;
				}
				else{
					System.out.println("Leaf hinzugefügt: " + sportgruppenId + sportartId + "Sportart");
				}
				
				for (Veranstaltung veranstaltungKonkret : vm.getVeranstaltung()){
					Veranstaltung v = veranstaltungKonkret;
					veranstaltungId = v.getId();
					
					//xyzVeranstaltung Leaf für das Abonnieren einer konkreten Veranstaltung
					if(xmppVerbindung.createLeaf(sportgruppenId + sportartId + veranstaltungId + "Veranstaltung") == false){
						bestehendeVeranstaltungen++;
					}
					else{
						System.out.println("Leaf hinzugefügt: " + sportgruppenId + sportartId + veranstaltungId + "Veranstaltung");
					}
				}
			}
		}
		System.out.println("Es wurden " + bestehendeVeranstaltungen + " Verastaltungs-Leafs nicht hinzugefuegt, " +
				"da diese bereits vorhanden sind.");
		System.out.println("Es wurden " + bestehendeSportarten + " Sportarten-Leafs nicht hinzugefuegt, " +
					"da diese bereits vorhanden sind.");
	}
}
