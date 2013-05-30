package xmpp_2;

import org.jivesoftware.smack.XMPPException;

public class ProgrammStart {

	/**
	 *  Programmstart mit dem die Clients aktiviert werden.
	 * @param args
	 * @throws XMPPException 
	 */
	public static void main(String[] args) throws XMPPException {
		
		boolean erfolgreich = true;
		
		while(erfolgreich){
			System.out.println("Es wird versucht eine Verbindung zum Server herzustellen");
			XmppManager verbindung = new XmppManager();
			if (verbindung.verbinden() == false){
				System.out.println("Nicht erfolgreich zum Server verbunden.");
				erfolgreich = false;
			}
			System.out.println("Erfolgreich mit dem Server verbunden.");
			
			verbindung.managePubSub();
			
			//Wahl zwischen Veranstalter oder Interessent:
			Veranstalter veranstalter = new Veranstalter();
			veranstalter.veranstalterErzeugen(verbindung);
			erfolgreich = false;
		
		}
	}
}
