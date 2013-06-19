package debugging_trash;

import java.util.Scanner;

import org.jivesoftware.smack.XMPPException;

import xmppService.XmppManager;

/**
 * Dies ist die Klasse, welche die Klassen 'Interessent' und 'Veranstalter'
 * instanziiert und daraufhin das Testen ermöglicht. Sie stellt keine Relevanz 
 * für das spätere System dar und kann daher später auch entfernt werden.
 * @author CrackX
 *
 */
public class ProgrammStart {

	/**
	 * Programmstart mit dem die Clients aktiviert werden.
	 * @param args
	 * @throws XMPPException 
	 */
	public static void main(String[] args) throws XMPPException {
		
		Scanner sc = new Scanner(System.in);
		int wahl;
		boolean erfolgreich = true;
		
		while(erfolgreich){
			System.out.println("Es wird versucht eine Verbindung zum Server herzustellen");
			XmppManager verbindung = new XmppManager();
			if (verbindung.verbinden() == false){
				System.out.println("Nicht erfolgreich zum Server verbunden.");
				erfolgreich = false;
				continue;
			}
			System.out.println("Erfolgreich mit dem Server verbunden.");
			
			verbindung.managePubSub();

			
			//Wahl zwischen Veranstalter oder Interessent:
			System.out.println("Bitte wählen Sie zwischen Veranstalter (1) und Interessent (2).");
			wahl = sc.nextInt();
			sc.nextLine(); //EOL entfernen
			
			switch (wahl){
		
				case 1:
					Veranstalter veranstalter = new Veranstalter();
					veranstalter.veranstalterErzeugen(verbindung);
					verbindung.disconnect();
					erfolgreich = false;
					break;
					
				case 2:
					Interessent interessent = new Interessent();
					interessent.interessentErzeugen(verbindung);
					verbindung.disconnect();
					erfolgreich = false;
					break;
			}
		}
	}
}
