package xmppService;

import java.util.List;

import generated.Sportart;
import generated.SportartenM;
import generated.Sportgruppe;
import generated.SportgruppenM;
import generated.Veranstaltung;
import generated.VeranstaltungenM;

import org.jivesoftware.smack.XMPPException;

import restService.ClientRest;
/**
 * Diese Klasse dient dazu, alle vorhandenen LeafNodes automatisch zu löschen.
 * 
 *
 * Alle auf dem Server vorhandenen Leafs werden gelöscht, damit, falls neue Veranstaltungen
 * mit der GUI hinzugefügt worden sind, diese wieder gelöscht werden können. Anschließend,
 * nachdem die XML-Datei auch wieder bereinigt wurde, sollte das createLeafScript ausgeführt
 * werden.
 * @author CrackX
 *
 */
public class deleteLeafScript {

	public static void main(String[] args) throws XMPPException{
	
		ClientRest ct = new ClientRest();
		XmppManager xmppVerbindung = new XmppManager();
		
		
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
		
		List<String> leafList = xmppVerbindung.getLeafs();
		for(String leaf:leafList){
			xmppVerbindung.deleteLeaf(leaf);
		}
		
	}
}
