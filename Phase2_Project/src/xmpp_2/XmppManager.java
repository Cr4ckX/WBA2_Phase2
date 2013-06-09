package xmpp_2;

import java.util.Iterator;

import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.ServiceDiscoveryManager;
import org.jivesoftware.smackx.packet.DiscoverInfo;
import org.jivesoftware.smackx.packet.DiscoverItems;
import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.LeafNode;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.PubSubManager;
import org.jivesoftware.smackx.pubsub.SimplePayload;

public class XmppManager {
	
	Connection cn;
	PubSubManager psm;
	
	public boolean verbinden(){
		
		XmppConnection xc;
		
		try {
			xc = new XmppConnection();		
			cn = xc.connect();
			//cn.login("veranstalter", "veranstalter");
			return true;
			
		} catch (XMPPException e) {
			System.out.println("Beim Verbinden und Einloggen ist ein Fehler aufgetreten");
			e.printStackTrace();
			return false;
		}	
	}
	
	public boolean managePubSub(){
		if (cn.isConnected() || cn.isSecureConnection()){
			psm = new PubSubManager(cn, "pubsub." + cn.getHost());
			return true;
		}
		else
			return false;
	}
	
	public boolean login(String username, String password){
		if (cn.isConnected() || cn.isSecureConnection()){
			try {
				cn.login(username, password);
				return true;
			} catch (XMPPException e) {
				System.out.println("Beim Einloggen ist ein Fehler aufgetreten");
				e.printStackTrace();
				return false;
			}
		}
		else
			return false;
	}
	
	public boolean createLeaf(String leafName){
		//Vielleicht abfragen nach Vorhandensein
		try {
			LeafNode leaf = psm.createNode(leafName);
			return true;
		} catch (XMPPException e) {
			System.out.println("Beim erstellen eines LeafNodes ist ein Fehler aufgetreten");
			e.printStackTrace();
			return false;
		}	
	}
		
	
	/**
	 * Versucht zu dem angegebenen Leaf zu publishen. Mit dem Parameter 'erzeuge' kann angegeben werden,
	 * ob der LeafNode erzeugt werden soll, wenn es den Node noch nicht gibt.
	 * 
	 * @param leafName Name des LeafNodes, zu dem gepublished werden soll.
	 * @param payLoad Zu publishenden Payload 
	 * @param erzeuge true, wenn ein neues Leaf bei Nichtvorhandensein erzeugt werden soll.
	 * @param wurzelElement Wurzelelement des Payloads, für Payload notwendig.
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean publishToLeaf(String leafName, String payLoad, boolean erzeuge, String wurzelElement){

		LeafNode pubLeaf;
		try {
			pubLeaf = psm.getNode(leafName);
		} catch (XMPPException e) {
			/* Beim Holen des Nodes ist ein Fehler aufgetreten.
			 * Ist der LeafNode evtl. noch nicht vorhanden?
			 * 
			 * Wenn die 'erzeuge' variable 'true' ist, so ist es möglich,
			 * zu dem zu publishendem Leaf zu erzeugen. Daraufhin wird der LeafNode
			 * wieder geholt.
			 */ 
			System.out.println("Beim publishen ist ein Fehler aufgetreten.");
			e.printStackTrace();
			if (erzeuge == true){
				System.out.println("Es wird versucht, diesen Leaf zu erstellen.");
				createLeaf(leafName);
				try {
					pubLeaf = psm.getNode(leafName);
				} catch (XMPPException e1) {
					//Auch der neu erstellte Node konnte nicht dem zugeordnet werden. Das Publishen wird abgebrochen.
					System.out.println("Der neu erstellte LeafNode konnte nicht zugeordnet werden.");
					e1.printStackTrace();
					return false;
				}
			}
			else
				return false;
			
			
		
		}
		try {
			/*
			 * PayloadItem(itemId (null = Server setzt id), PayLoad)
			 * SimplePayload(RootElement (des PayLoads), Namcespace, xmlPayLoad)
			 * Namespace wenn keiner angegeben: 'xmlns="http://jabber.org/protocol/pubsub'
			 */
			pubLeaf.send(new PayloadItem(null, new SimplePayload(wurzelElement, null, payLoad)));
			
			return true;
			
		} catch (XMPPException e) {
			System.out.println("Publishen Fehlgeschlagen");
			e.printStackTrace();
			return false;
		}	
	}
	
		
	//Vorhandenen Leafs noch suchen
	public void getLeafs() throws XMPPException{
		if (cn.isConnected() == false || cn.isSecureConnection() == false){
			System.out.println("Es besteht keine Verbindung!");
			//return "failture, no connection!";
		}
		ServiceDiscoveryManager sdm = ServiceDiscoveryManager.getInstanceFor(cn);
		DiscoverItems dci = psm.discoverNodes(null);
		
		Iterator it = dci.getItems();
		while (it.hasNext()) {
	          DiscoverItems.Item item = (DiscoverItems.Item) it.next();
	          System.out.println("Node: " + item.getNode());
	      }
}
	

	//Eigenschaften eines Leafs anzeigen!
	public String getLeafService(String LeafNode) throws XMPPException{
		String discoveredService = "";
		
		if (cn.isConnected() == false || cn.isSecureConnection() == false){
			System.out.println("Es besteht keine Verbindung!");
			return "failture, no connection!";
		}
		
		ServiceDiscoveryManager sdm = ServiceDiscoveryManager.getInstanceFor(cn);
		
		DiscoverInfo dci = sdm.discoverInfo("pubsub."+cn.getHost(), LeafNode);
		Iterator it = dci.getIdentities();
		while (it.hasNext()) {
			DiscoverInfo.Identity identity = (DiscoverInfo.Identity) it.next();
			discoveredService += 
					"Name: " + identity.getName() + 
					"\nType: " + identity.getType() + 
					"\nCategory: " + identity.getCategory() + "\n";	
		}
		return discoveredService;
	}
	

	
	public boolean subscribeLeaf(String leafNode){

		
		LeafNode subLeaf;
		try {
			subLeaf = psm.getNode(leafNode);
			subLeaf.addItemEventListener(new ItemEventCoordinator<Item>());
		    subLeaf.subscribe(cn.getUser());
		    return true;
		} catch (XMPPException e) {
			System.out.println("Beim Subscriben ist ein Fehler aufgetreten");
			e.printStackTrace();
			return false;
		}

	}
	
	public boolean disconnect(){
		cn.disconnect();
		return true;
		
	}
	public void verbinden(String host, int port) throws XMPPException{
			
		XmppConnection xc = new XmppConnection(host, port);
		cn = xc.connect();
	}

	
}
