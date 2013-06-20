package xmppService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
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
/**
 * Diese klasse realisiert den XMPP-Service.
 * @author CrackX
 *
 */
public class XmppManager {
	
	private Connection cn;
	private PubSubManager psm;
	private ItemEventCoordinator iec = new ItemEventCoordinator();
	
	/**
	 * Baut eine Verbindung zur Standardadresse (localhost) mit dem Standardport (5552) 
	 * zu dem XMPP-Server auf.
	 * 
	 * @return true, wenn erfolgreich mit dem XMPP-Server verbunden.
	 */
	public boolean verbinden(){
		String host = "localhost";
		int port = 5222;
		ConnectionConfiguration config = new ConnectionConfiguration(host, port);
		cn = new XMPPConnection(config);
		cn.DEBUG_ENABLED = true;
		try {
			cn.connect();
			return true;
		} catch (XMPPException e) {
			System.out.println("Fehler beim Verbinden!");
			return false;
		}
	}
	/**
	 * Baut eine Verbindung mit dem XMPP-Server zu dem angegebenen Socket auf.
	 * 
	 * @param host Die Adresse des Servers.
	 * @param port Der Port über den der Server als Client angesprochen werden kann.
	 * @return true, wenn erfolgreich mit dem XMPP-Server verbunden.
	 */
	public boolean verbinden(String host, int port){

		ConnectionConfiguration config = new ConnectionConfiguration(host, port);
		cn = new XMPPConnection(config);
		cn.DEBUG_ENABLED = true;
		try {
			cn.connect();
			return true;
		} catch (XMPPException e) {
			System.out.println("Fehler beim Verbinden!");
			return false;
		}
	}
	/**
	 * Erstellt einen PubSub Manager für die aktuelle Verbindung. Dieser wird benötigt,
	 * damit die Publish-Subscribe Funktionen verwendet werden können.
	 * Entsprechend wird eine bestehende Verbindung vorrausgesetzt.
	 * @return true, wenn PubSub-Manager erfolgreich erstellt.
	 */
	public boolean managePubSub(){
		if (cn.isConnected() || cn.isSecureConnection()){
			psm = new PubSubManager(cn, "pubsub." + cn.getHost());
			return true;
		}
		else
			return false;
	}
	/**
	 * Loggt einen User auf den Server ein, zu dem bereits eine Verbindung bestehen muss. 
	 * @param username Benutzername 
	 * @param password Passwort
	 * @return true, wenn erfolgreich eingeloggt.
	 */
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
	 * Erstellt einen neuen Leaf-Node auf dem verbundenen Server. Der Name des Leafs wird uebergeben.
	 * Entsprechend ist eine bereits bestehende Verbindung zu einem Server vorausgesetzt und wird deshalb 
	 * nicht abgefragt. Der Name muss folgende Form tragen:
	 * "xyzVeranstaltung", wobei x = Sportgruppe-ID, y = Sportart-ID, z = Veranstaltung-ID. Oder:<br/>
	 * "xySportart", wobei x = Sportgruppe-ID, y = Sportart-ID.
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
	/**
	 * Löscht einen bestehenden Leaf-Node vom Server.
	 * Entsprechend ist eine bereits bestehende Verbindung zu einem Server vorausgesetzt und wird 
	 * deshalb nicht abgefragt.<br/>
	 * Der Name des Leafs wir übergeben. Dieser Leaf muss natürlich auf dem Server vorhanden sein. 
	 * Andernfalls wird ein Fehler geworfen. Dieser Fehler wird nicht abgefangen, da die Leaf-Nodes
	 * eindeutig anhand der Veranstaltungen/Sportarten erzeugt werden sollen und damit im Programm 
	 * eindeutig und bekannt sind. Andernfalls kann mit getLeafs() zunächst eine Liste mit den zur
	 * Verfügung stehenden Leafs geholt werden, um sicherstellen zu können, dass dieser Leaf vorhanden ist.
	 * @param leafNode Leaf-Node Namen der Struktur, wie ein Leaf aufgebaut werden soll (siehe createLeaf())
	 * @return true, wenn Leaf erfolgreich gelöscht.
	 */
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
	 * @param erzeuge true, wenn ein neues Leaf bei Nichtvorhandensein erzeugt werden soll.
	 * @return true, wenn erfolgreich gepublished. False, wenn Leaf-Node bei erzeuge = true
	 * nicht erstellt werden konnte, der das Publishing allgemein fehlgeschlagen ist.
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
	
		
	/**
	 * Listet die auf dem Server (mit der aktuell eine Verbindung bestehen muss) vorhandenen Leafs.
	 * Diese werden als Lis zurückgegeben.
	 * @return Die auf dem Server verfügbaren Leafs als Liste/null, wenn keine Verbindung besteht.
	 * @throws XMPPException Wenn die Nodes nicht ermittelt werden können
	 */
	public List<String> getLeafs() throws XMPPException{
		List<String> nodeList = new ArrayList<String>();
		if (cn.isConnected() == false || cn.isSecureConnection() == false){
			System.out.println("Es besteht keine Verbindung!");
			return null;
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
	

	/**
	 * Zeigt die möglichen Services/Eigenschaften eines Leaf-Nodes an.
	 * Diese Funktion war für den Meilenstein 5 gefordert.<br/><br/>
	 * 
	 * Es wird geliefert: <br/>
	 * Der Name (mehr oder weniger überflüssig),<br/>
	 * der Typ,<br/>
	 * die Kategorie des Leaf-Nodes.<br/>
	 * 
	 * @param LeafNode LeafNode-Namen, über welchen Eigenschaften in Erfahrung gebracht werden sollen.
	 * @return Einen String mit den Informationen/null, wenn keine Verbindung besteht.
	 * @throws XMPPException Wenn die Discover-Funktion gehschlägt.
	 */
	public String getLeafService(String LeafNode) throws XMPPException{
		String discoveredService = "";
		
		if (cn.isConnected() == false || cn.isSecureConnection() == false){
			System.out.println("Es besteht keine Verbindung!");
			return null;
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
	
	/**
	 * Erfragt, ob der aktuelle Benutzer zu dem übergebenen Leaf-Node subscribed ist.
	 * @param leafNode Leaf-Node zu dem die Subscription abgefragt werden soll.
	 * @return true wenn subscribed.
	 */
	public boolean isSubscribed(String leafNode){
		List<Subscription> subList;
		
		//Hole Subscriptions des aktuellen PSM's (bzw. des demnach eingeloggten Users)
		try {
			subList = psm.getSubscriptions();
			
			for (Subscription subscription: subList){
				if(subscription.getNode().equals(leafNode)){
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
	 * Zeigt alle Nodes an, zu denen bereits vom aktuellen Benutzer subscribed wurde.
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

	/**
	 * Diese Methode wird dazu verwendet, alle Subscriptions zurückzusetzen, d.h.
	 * es werden alle Leafs zu denen eine Subscription besteht unsubscribed und wieder
	 * subscribed. <br/><br/>
	 * Diese Methode sollte beim Initialisieren der Verbindung zum XMPP-Server bzw. nach dem
	 * Login vollzogen werden, damit die Event-Listener für die Leafs wiederhergestellt werden.
	 * @return true, wenn erfolgreich alle Subscriptions restored.
	 */
	public boolean restoreSubscriptions(){
		List<String> nodeList = showSubscriptions();
		boolean unsub;
		for(String subNode : nodeList){
			unsub = unSubscribe(subNode);
			System.out.println("Erfolgreich unsubbed: " + subNode);
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
	
	/**
	 * Methode zum Subscriben zu einem übergebenen Leaf.<br/>
	 * Wenn bereits eine Subscription besteht, wird false zurückgegeben.
	 * @param leafNode Leaf-Node zu dem eine Subscription erstellt werden soll.
	 * @return true, wenn erfolgreich subscribed.
	 */
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

	/**
	 * Schließt die Verbindung zum XMPP-Server.
	 */
	public void disconnect(){
		cn.disconnect();
	}	
}
