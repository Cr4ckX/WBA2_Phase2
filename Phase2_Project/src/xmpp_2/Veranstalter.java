package xmpp_2;

import java.util.Scanner;

import org.jivesoftware.smack.XMPPException;

public class Veranstalter {

	public void veranstalterErzeugen(XmppManager veranstalter) throws XMPPException {
		
		boolean erfolgreich = true;
		Scanner sc = new Scanner(System.in);
		int wahl;
		String input;
		
		while (erfolgreich){
			
			
			if (veranstalter.login("veranstalter", "veranstalter") == false){
				System.out.println("Das Einloggen war nicht möglich.");
				erfolgreich = false;
			}
			System.out.println("Erfolgreich als Veranstalter eingeloggt.");
			
			//Wahl:
			System.out.println("Bitte wählen Sie:");
			System.out.println("1 um einen neuen LeafNode zu erzeugen.");
			System.out.println("2 um alle Leafs anzeigen zu lassen.");
			System.out.println("3 um zu einem Leaf zu publishen.");

			wahl = sc.nextInt();
			
			switch(wahl){
				case 1:
					System.out.print("Bitte LeafID eingeben: ");
					input = sc.nextLine();
					break;
					
				case 2:
					veranstalter.getLeafs();
					break;
					
				case 3:
					System.out.print("Bitte LeafID eingeben, zu der gepublisht werden soll: ");
					input = sc.nextLine();
					input = sc.nextLine();
					veranstalter.publishToLeaf(input, "<test>test</test>", false, "");
					break;
			}
			
			veranstalter.disconnect();
			erfolgreich = false;
			
		}
	}
}
