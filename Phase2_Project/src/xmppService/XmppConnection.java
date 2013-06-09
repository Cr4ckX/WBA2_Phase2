package xmppService;

import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;



public class XmppConnection {
	
	private String host;
	private int port;
	/**
	 * Erzeugt ein Verbindungsobjekt mit den übergebenen Parametern.
	 * 
	 * @param host Server zu dem die Verbindung aufgebaut werden soll. 
	 * @param port Port zu dem die Verbindung aufgebaut werden soll. 5223 für SSL.
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
		this.connect();
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
