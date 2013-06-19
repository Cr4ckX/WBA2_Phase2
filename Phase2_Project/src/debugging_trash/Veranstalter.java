package debugging_trash;

import java.util.Scanner;

import org.jivesoftware.smack.XMPPException;

import xmppService.XmppManager;
/**
 * Hierbei handelt es sich lediglich um eine Testklasse, um den XMPP Server
 * hinsichtlich seiner Funktionalit�t f�r den Veranstalter zu testen.
 * 
 * Sp�ter kann sie gel�scht werden, da sie f�r das System keine Relevanz
 * darstellt. Daher befindet sich diese Klasse zun�chst im 'Trash-Package'.
 * @author CrackX
 *
 */
public class Veranstalter {

	public void veranstalterErzeugen(XmppManager veranstalter) throws XMPPException {
		
		boolean erfolgreich = true;
		Scanner sc = new Scanner(System.in);
		int wahl;
		String garbagecollect;
		String input;
		String publishIn;
			
		if (veranstalter.login("veranstalter", "veranstalter") == false){
			System.out.println("Das Einloggen war nicht m�glich.");
			erfolgreich = false;
		}
		System.out.println("Erfolgreich als Veranstalter eingeloggt.");
		
		//Wahl:
		System.out.println("Bitte w�hlen Sie:");
		System.out.println("1 um einen neuen LeafNode zu erzeugen.");
		System.out.println("2 um alle Leafs anzeigen zu lassen.");
		System.out.println("3 um zu einem Leaf zu publishen.");
		System.out.println("4 Programm beenden!");
		
		
		while (erfolgreich){
			System.out.print("Bitte Zahl eingeben: ");
			wahl = sc.nextInt();
			sc.nextLine(); //EOL entfernen
			
			switch(wahl){
				case 1:
					System.out.print("Bitte LeafID eingeben: ");
					input = sc.nextLine();
					veranstalter.createLeaf(input);
					break;
					
				case 2:
					veranstalter.getLeafs();
					break;
					
				case 3:
					System.out.print("Bitte LeafID eingeben, zu der gepublisht werden soll: ");
					input = sc.nextLine();
					System.out.print("Bitte Inhalt eingeben, welcher gepublisht werden soll: ");
					publishIn = sc.nextLine();
					//veranstalter.publishToLeaf(input, "<test>"+publishIn+"</test>", false, "");
					break;
					
				case 4:
					veranstalter.disconnect();
					System.out.println("Erfolgreich disconnected");
					erfolgreich = false;
			}
		}
	}
}
