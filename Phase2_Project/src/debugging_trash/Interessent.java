package debugging_trash;

import java.util.List;
import java.util.Scanner;

import org.jivesoftware.smack.XMPPException;

import xmppService.XmppManager;

/**
 * Hierbei handelt es sich lediglich um eine Testklasse, um den XMPP Server
 * hinsichtlich seiner Funktionalität für den Interessenten zu testen.
 * 
 * Später kann sie gelöscht werden, da sie für das System keine Relevanz
 * darstellt. Daher befindet sich diese Klasse zunächst im 'Trash-Package'.
 * @author CrackX
 *
 */
public class Interessent {

	public void interessentErzeugen(XmppManager interessent) throws XMPPException {
		
		boolean erfolgreich = true;
		Scanner sc = new Scanner(System.in);
		int wahl;
		String input;
		List<String> nodeList;

			
		if (interessent.login("interessent", "interessent") == false){
			System.out.println("Das Einloggen war nicht möglich.");
			erfolgreich = false;
		}
		System.out.println("Erfolgreich als Interessent eingeloggt.");
		System.out.println(interessent.restoreSubscriptions());
		//Wahl:
		System.out.println("Bitte wählen Sie:");;
		System.out.println("1 um alle Leafs anzeigen zu lassen.");
		System.out.println("2 um zu einem Leaf zu subscriben.");
		System.out.println("3 um einen Leaf unzusubscriben.");
		System.out.println("4 Programm beenden!");
		System.out.println("5 um gesubbte Leafs anzuzeigen!");
		
		
		while (erfolgreich){
			System.out.print("Bitte Zahl eingeben: ");
			wahl = sc.nextInt();
			sc.nextLine(); //EOL entfernen
			
			switch(wahl){
				case 1:
					nodeList = interessent.getLeafs();
					for (String node:nodeList){
						System.out.println(node);
					}
					break;
					
				case 2:
					System.out.print("NodeId des zu subscribenen Leafs eingeben: ");
					input = sc.nextLine();
					if (interessent.subscribeLeaf(input)){
						System.out.println("Erfolgreich zu " + input +" subscribed.");
						break;
					}
					else{
						System.out.println("Nicht erfolgreich zu " + input +" subscribed.");
						break;
					}
				
				case 3:
					System.out.print("Node ID des zu unsubscribenden Leafs eingeben:");
					input = sc.nextLine();
					interessent.unSubscribe(input);
					System.out.println("Unsubscribed!");
					
				case 4:
					interessent.disconnect();
					System.out.println("Erfolgreich disconnected");
					erfolgreich = false;
					
				case 5:
					List<String> sublist= interessent.showSubscriptions();
					for(String sub:sublist){
						System.out.println(sub);
					}
					
			}
		}
	}
}
