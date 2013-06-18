package xmppService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import org.jivesoftware.smackx.pubsub.Subscription;

public class XmppManager {
	
	private  Connection cn;
	//Public, damit ItemEventCoodinator Zugriff bekommt (Item-Bug in der Smack API)
	public PubSubManager psm;
	private ItemEventCoordinator iec = new ItemEventCoordinator();
	
	public boolean verbinden(){
		XmppConnection xc;
		try {
			xc = new XmppConnection();		
			cn = xc.connect();
			//cn.login("veranstalter", "veranstalter");
			return true;
			
		} catch (XMPPException e) {
			System.out.println("Beim Verbinden ist ein Fehler aufgetreten.");
			//e.printStackTrace();
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
	/**
	 * Erstellt einen neuen Leaf-Node. Der Name des Leafs wird uebergeben.
	 * Der Name muss folgende Form tragen:
	 * "xyzVeranstaltung", wobei x = Sportgruppe-ID, y = Sportart-ID, z = Veranstaltung-ID.
	 * 
	 * Wenn ein Leaf mit diesem Namen bereits vorhanden ist, wird false zurueckgegeben.
	 * @param leafName zu erstellenden Leaf.
	 * @return true, wenn erfolgreich hinzugefügt. false, wenn Fehler oder schon vorhanden.
	 */
	public boolean createLeaf(String leafName){
		//Vielleicht abfragen nach Vorhandensein
		try {
			List<String> bestehendeLeafs = getLeafs();
			for(String bestehenderLeaf : bestehendeLeafs){
				if(bestehenderLeaf.equals(leafName)){
					//System.out.println("Leaf schon vorhanden!");
					return false;
				}
			}
			
			psm.createNode(leafName);
			return true;
		} catch (XMPPException e) {
			System.out.println("Beim Erstellen eines LeafNodes ist ein Fehler aufgetreten");
			e.printStackTrace();
			return false;
		}	
	}
	
	public boolean deleteLeaf(String leafNode){
		try {
			psm.deleteNode(leafNode);
			return true;
		} catch (XMPPException e) {
			System.out.println("Beim Löschen des LeafNodes ist ein Fehler aufgetreten.");
			e.printStackTrace();
			return false;
		}	
	}
		
	
	/**
	 * Versucht zu dem angegebenen Leaf zu publishen. Mit dem Parameter 'erzeuge' kann angegeben werden,
	 * ob der LeafNode erzeugt werden soll, wenn es den Node noch nicht gibt.
	 * 
	 * @param leafName Name des LeafNodes, zu dem gepublished werden soll.
	 * .@param payLoad Zu publishenden Payload 
	 * @param erzeuge true, wenn ein neues Leaf bei Nichtvorhandensein erzeugt werden soll.
	 * .@param wurzelElement Wurzelelement des Payloads, für Payload notwendig.
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean publishToLeaf(String leafName, String payload, boolean erzeuge){

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
			pubLeaf.send(new PayloadItem(null, new SimplePayload(null, null, payload)));
			
			return true;
			
		} catch (XMPPException e) {
			System.out.println("Publishen fehlgeschlagen");
			e.printStackTrace();
			return false;
		}	
	}
	
		
	//Vorhandene Leafs suchen
	public List<String> getLeafs() throws XMPPException{
		List<String> nodeList = new ArrayList<String>();
		if (cn.isConnected() == false || cn.isSecureConnection() == false){
			System.out.println("Es besteht keine Verbindung!");
			System.exit(1);
		}
		
		ServiceDiscoveryManager sdm = ServiceDiscoveryManager.getInstanceFor(cn);
		DiscoverItems dci = psm.discoverNodes(null);
		
		Iterator it = dci.getItems();
		while (it.hasNext()) {
	          DiscoverItems.Item item = (DiscoverItems.Item) it.next();
	          nodeList.add(item.getNode());
	          //System.out.println("Node: " + item.getNode());
	    }
		return nodeList;
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
	

	public boolean isSubscribed(String leafNode){
		List<Subscription> subList;
		
		//Hole Subscriptions des aktuellen PSM's (bzw. des demnach eingeloggten Users)
		try {
			subList = psm.getSubscriptions();
			
			for (Subscription subscription: subList){
				if(subscription.getNode().equals(leafNode)){
					//System.out.println(subscription.getJid());
					//System.out.println("Bereits subscribed!");
					return true;
				}
			}
		} catch (XMPPException e) {
			System.out.println("Fehler beim holen der Subscriptions");
			e.printStackTrace();
		}


		return false;
	}
	/**
	 * Zeigt die Nodes an, zu denen bereits vom aktuellen USER subscribed wurde.
	 * @return Eine Liste mit Nodes. / Leere liste bei keinen Subscriptions.
	 */
	public List<String> showSubscriptions(){

		List<Subscription> subList;
		List<String> nodeList = new ArrayList<String>();
		try {
			subList = psm.getSubscriptions();
			for(Subscription subscription: subList){
				nodeList.add(subscription.getNode());
				//System.out.println(subscription.getId());
			}
			return nodeList;
			
		} catch (XMPPException e) {
			System.out.println("Fehler beim holen der Subscriptionliste");
			e.printStackTrace();
			return nodeList;
		}
	}

	public boolean restoreSubscriptions(){
		List<String> nodeList = showSubscriptions();
		boolean unsub;
		for(String subNode : nodeList){
			System.out.println("Erfolgreich unsubbed: " + subNode);
			unsub = unSubscribe(subNode);
			if(unsub == true){
				if(subscribeLeaf(subNode) == true){
					System.out.println("Erfolgreich subbed: " + subNode);
					continue;
				}
				else
					return false;
			}
			return false;
		}
		return true;	
	}
	
	public boolean subscribeLeaf(String leafNode){

		
		LeafNode subLeaf;
		try {
			
			if(isSubscribed(leafNode) == true){
				//System.out.println("Bereits subscribed!");
				return false;
			}
			subLeaf = psm.getNode(leafNode);
			subLeaf.addItemEventListener(iec);
		    subLeaf.subscribe(cn.getUser());
		    return true;
		} catch (XMPPException e) {
			System.out.println("Beim Subscriben ist ein Fehler aufgetreten");
			e.printStackTrace();
			return false;
		}

	}
	/**
	 * Entferne die Subscription, welche zuvor zu dem uebergebenen LeadNode vorgenommen worden musste.
	 * @param leafNode LeafNode an der die Subscription aufgeloest werden soll.
	 * @return true, wenn erfolgreich unsubscribed, false, wenn Fehler oder Node zuvor nicht subscribed.
	 */
	public boolean unSubscribe(String leafNode){
		LeafNode subLeaf;
		try {
			subLeaf = psm.getNode(leafNode);
			
			if(isSubscribed(leafNode) == true){
				subLeaf.unsubscribe(cn.getUser());
				subLeaf.removeItemEventListener(iec);
				//System.out.println("Erfolgreich den LeafNode " + leafNode + " unsubscribed!");
				return true;
			}
			else{
				System.out.println("LeafNode: " + leafNode + " konnte nicht unsubscribed werden," +
						"es liegt keine Subscription vor.");
				return false;
			}

		} catch (XMPPException e) {
			e.printStackTrace();
			System.out.println("Fehler beim Holen des Nodes.");
			return false;
		}
	}
	/**
	 * Diese Methode wird beim Unsubcriben benötigt, wenn zu einem Node 
	 * mehrere Subscriptions vorliegen. Zu jeder Subscription wird dann
	 * die Subscription-ID geholt, welche das Löschen einer genauen 
	 * Subscription vornimmt.<br/><br/>
	 * 
	 * Diese Methode sollte später nicht standardmäßig implementiert werden.
	 * Sie dient lediglich dafür, Debugging-Subscriptions wieder zu entfernen.
	 * Die Methode wird auch nicht benötigt, da jeder Nutzer nun nur noch einmal
	 * zu einem Node subscriben kann und somit die Methode unSubscribe(String leafNode)
	 * verwender werden sollte.
	 * 
	 * @param leafNode LeafNode an der die Subscription aufgeloest werden soll.
	 */
	public boolean unSubscribePlural(String leafNode){
		LeafNode subLeaf;
		List<Subscription> subList; 
		String subscriptionId;
		String subscriptionNode;

		try {
			subLeaf = psm.getNode(leafNode);
			
			subList = psm.getSubscriptions();
			for(Subscription subscription: subList){
					subscriptionNode = subscription.getNode();
					subscriptionId = subscription.getId();
					if(subscriptionNode.equals(leafNode)){
						if(isSubscribed(leafNode) == true){
							System.out.println("LeafNode: " + leafNode);
							System.out.println("SubNode: " + subscriptionNode);
							System.out.println("SubID: " + subscriptionId);
							System.out.println("User: " + cn.getUser());
							subLeaf.unsubscribe(cn.getUser(), subscriptionId);
							return true;
						}
					}
			}

		}
		
		catch (XMPPException e) {
			System.out.println("Beim Holen der Subscriptions ist ein Fehler aufgetreten");
			e.printStackTrace();
			return false;
		}
		return false;
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
