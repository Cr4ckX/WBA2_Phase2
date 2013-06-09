package xmpp_2;

import java.util.Scanner;

import org.jivesoftware.smack.XMPPException;

public class Interessent {

	public void interessentErzeugen(XmppManager interessent) throws XMPPException {
		
		boolean erfolgreich = true;
		Scanner sc = new Scanner(System.in);
		int wahl;
		String garbagecollect;
		String input;
		String publishIn;
			
		if (interessent.login("interessent", "interessent") == false){
			System.out.println("Das Einloggen war nicht möglich.");
			erfolgreich = false;
		}
		System.out.println("Erfolgreich als Interessent eingeloggt.");
		
		//Wahl:
		System.out.println("Bitte wählen Sie:");;
		System.out.println("1 um alle Leafs anzeigen zu lassen.");
		System.out.println("2 um zu einem Leaf zu subscriben.");
		System.out.println("3 Programm beenden!");
		
		
		while (erfolgreich){
			System.out.print("Bitte Zahl eingeben: ");
			wahl = sc.nextInt();
			sc.nextLine(); //EOL entfernen
			
			switch(wahl){
				case 1:
					interessent.getLeafs();
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
					if (interessent.disconnect())
						System.out.println("Erfolgreich disconnected");
					erfolgreich = false;
			}
		}
	}
}
