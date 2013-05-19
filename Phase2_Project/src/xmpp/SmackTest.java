package xmpp;

import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.bytestreams.socks5.Socks5Proxy;


public class SmackTest {

	public static void main(String[] args) throws InterruptedException, XMPPException {
		
		ConnectionConfiguration config = new ConnectionConfiguration("localhost", 5222);
		config.setRosterLoadedAtLogin(false);
		
		Connection cn1 = new XMPPConnection(config);
	
		//Workaround für 'Socks5Proxy Port 7777 bereits in use' Fehler
		SmackConfiguration.setLocalSocks5ProxyPort(-2326);
		
		
		try {
			cn1.connect();
			if (cn1.isConnected()){}
				//cn1.login("Client", "client");
		} 
		
		catch (XMPPException e) {
			
			if (cn1.isConnected() == false)
				System.out.println("Client konnte nicht verbinden.");			
		}
		
		if (cn1.isSecureConnection())
			System.out.println("Sichere Verbindung!");
		else
			System.out.println("keine sichere Verbindung!");
		
		Thread.sleep(100000);
		cn1.disconnect();
		System.out.println("Disconnected");
	}

}
