package xmppService;

import java.util.List;
import java.util.Scanner;

import org.jivesoftware.smack.XMPPException;

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
		
		//Wahl:
		System.out.println("Bitte wählen Sie:");;
		System.out.println("1 um alle Leafs anzeigen zu lassen.");
		System.out.println("2 um zu einem Leaf zu subscriben.");
		System.out.println("3 um einen Leaf unzusubscriben.");
		System.out.println("4 Programm beenden!");
		
		
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
					input = sc.nextLine();
					interessent.unSubscribe(input);
					
				case 4:
					if (interessent.disconnect())
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
