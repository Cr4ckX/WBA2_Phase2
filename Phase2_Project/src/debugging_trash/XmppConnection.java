package debugging_trash;

import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

/**
 * Klasse zum Erstellen einer Verbindung zu einem XMPP-Server.
 * Diese Klasse ist nun �berfl�ssig und kann entfernt werden.
 * @author CrackX
 *
 */
public class XmppConnection {
	
	private String host;
	private int port;
	
	/**
	 * Erzeugt ein Verbindungsobjekt mit den �bergebenen Parametern.
	 * 
	 * @param host Server zu dem die Verbindung aufgebaut werden soll. 
	 * @param port Port zu dem die Verbindung aufgebaut werden soll. 5223 f�r SSL.
	 */
	public XmppConnection(String host, int port){
		this.host = host;
		this.port = port;
	}
	/**
	 * Parameterloses Verbindungsobjekt.<br/>
	 * Baut Verbindung mit den Standardeinstellungen auf:<br/><br/>
	 * 
	 * Host: localhost<br/>
	 * Port: 5222
	 * @throws XMPPException 
	 * 
	 */
	public XmppConnection() throws XMPPException{
		this.host = "localhost";
		this.port = 5222;
	}
	
	public Connection connect() throws XMPPException{
		
		XMPPConnection.DEBUG_ENABLED = true;
		
		ConnectionConfiguration config = new ConnectionConfiguration(host, port);
		config.setRosterLoadedAtLogin(false); //weil keine Kontaktliste
		Connection cn = new XMPPConnection(config);
		cn.connect();
		return cn;
	}
}
