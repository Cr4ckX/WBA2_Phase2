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
public class InteressentGUI {
	
	public static void main(String[] args) throws XMPPException{
	
		List<String> nodeList;
		List<String> subNodeList;
		XmppManager verbindung = new XmppManager();
		
		if(verbindung.verbinden() == false){
			System.out.println("Verbindung konnte nicht hergestellt werden!");
			System.exit(1);
		}
		if(verbindung.managePubSub() == false){
			System.out.println("Pub-Sub Manager konnte nicht erstellt werden");
			System.exit(1);
		}
		
		if(verbindung.login("interessent", "interessent") == false){
			System.out.println("Fehler beim Einloggen");
			System.exit(1);
		}
		
		//Liste in der die Verfuegbaren Nodes drin steht. 
		nodeList = verbindung.getLeafs();
		System.out.println("Zu Verfüfung stehende Nodes:");
		for(String leafVerfuegbar:nodeList){
			System.out.println(leafVerfuegbar);
		}
		System.out.println("Ende der zu Verfüfung stehenden Nodes");
		
		//Zeige mir meine Nodes, zu denen ich Subscribed habe:
		subNodeList = verbindung.showSubscriptions();
		System.out.println("Zu folgenden Nodes besteht eine Subscription:");
		for(String subNode:subNodeList){
			System.out.println(subNode);			
		}
		
		//Wenn subscribed: Subscribe Button ausgrauen..
//		if(verbindung.isSubscribed("bla")){
//			System.out.println("Bereits zu dem Node 'bla' subscribed.");
//		}
//		else{
//			verbindung.subscribeLeaf("bla");
//			System.out.println("Erfolgreich zum Node 'bla' Subscribed!");
//		}
		
////		boolean unsubscribe = verbindung.unSubscribePlural("bla");
////		//System.out.println(unsubscribe);
////		if(unsubscribe){
////			System.out.println("Erfolgreich unsubscribed!");
////		}
		
		
//		LeafNode test;
//		
//		if(verbindung.deleteLeaf("node1")){
//			System.out.println("Gelöscht.!");
//		}
		
	
	}
}
