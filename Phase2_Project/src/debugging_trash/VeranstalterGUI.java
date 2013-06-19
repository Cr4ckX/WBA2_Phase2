package debugging_trash;

import java.util.ArrayList;
import java.util.List;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.pubsub.LeafNode;

import xmppService.XmppManager;

/**
 * Klasse, welche die Funktionalitaet, welche spaeter von der GUI ausgefuehrt 
 * werden soll, zeigt.
 * 
 * Diese Klasse ist im späterem System nicht Bestandteil der Applikation und 
 * kann dann entfernt werden.
 * @author CrackX
 *
 */
public class VeranstalterGUI{
	
	public static void main(String[] args) throws XMPPException{
	
//		List<String> nodeList;
//		List<String> subNodeList;
		XmppManager verbindung = new XmppManager();
		
		if(verbindung.verbinden() == false){
			System.out.println("Verbindung konnte nicht hergestellt werden!");
			System.exit(1);
		}
		if(verbindung.managePubSub() == false){
			System.out.println("Pub-Sub Manager konnte nicht erstellt werden");
			System.exit(1);
		}
		
		if(verbindung.login("veranstalter", "veranstalter") == false){
			System.out.println("Fehler beim Einloggen");
			System.exit(1);
		}
		
//		if(verbindung.createLeaf("bla") == false){
//			System.out.println("Fehler beim Erstellen eines Leafs.");
//		}
//		else{
//			System.out.println("Leaf wurde erstellt.");
//		}
		

	}
}