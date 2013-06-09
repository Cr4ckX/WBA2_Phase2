package xmpp;

import java.util.Iterator;

import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.ServiceDiscoveryManager;
import org.jivesoftware.smackx.packet.DiscoverInfo;
import org.jivesoftware.smackx.packet.DiscoverItems;
import org.jivesoftware.smackx.pubsub.AccessModel;
import org.jivesoftware.smackx.pubsub.ConfigureForm;
import org.jivesoftware.smackx.pubsub.FormType;
import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.ItemPublishEvent;
import org.jivesoftware.smackx.pubsub.LeafNode;
import org.jivesoftware.smackx.pubsub.Node;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.PubSubManager;
import org.jivesoftware.smackx.pubsub.PublishModel;
import org.jivesoftware.smackx.pubsub.SimplePayload;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;
import org.jivesoftware.smackx.pubsub.ConfigureForm;

public class PubSub {

	public static void main(String[] args) throws InterruptedException, XMPPException{
		// turn on the enhanced debugger
	    XMPPConnection.DEBUG_ENABLED = true;
			
		ConnectionConfiguration config = new ConnectionConfiguration("localhost", 5222);
		config.setRosterLoadedAtLogin(false); //weil keine Kontaktliste
		
		Connection cn1 = new XMPPConnection(config);
		cn1.connect();
		//Man muss vorher eingeloggt sein -.-
		cn1.login("client", "client", "MacBook");

		//PubSubManager auf bestehende Verbindung erzeugt
		PubSubManager psm = new PubSubManager(cn1, "pubsub.localhost");

		
		ConfigureForm form = new ConfigureForm(FormType.submit);
		form.setDeliverPayloads(false);

		//Leaf erzeugen
		//psm.deleteNode("node1");
//		LeafNode leaf = psm.createNode("node1");
//		LeafNode leaf = psm.createNode("node2");
//		Node leaf = psm.createNode("test", form);
//		LeafNode leaf = psm.createNode("blubbbb");
	

		//-------------------------------------------
		//Subscriben
		LeafNode subLeaf = psm.getNode("node1");
		
		subLeaf.addItemEventListener(new ItemEventCoordinator<Item>());
	    subLeaf.subscribe("client@localhost");

			    
	    
		//----------------------------------------
		//Zu einem Knoten publishen - Wer darf publishen??
		//Dazu muss er erstmal geholt werden
		
		LeafNode pubLeaf = psm.getNode("node1");

		//pubLeaf.send(new Item("item1"));
		///pubLeaf.send(); //synchrone Nachricht!, funktioniert nicht.
//		pubLeaf.publish(new Item("item1")); //asynchrone Nachricht! funktioniert!
		
		//subLeaf.send(new PayloadItem("test" + System.currentTimeMillis(), new SimplePayload("book", "pubsub", "")));
		
		pubLeaf.send(new PayloadItem(null, 
				new SimplePayload("book", "pubsub."+cn1.getHost(),
						"<book><title>Lord of the Rings</title></book>")));

		Thread.sleep(1000);
		ServiceDiscoveryManager sdm = ServiceDiscoveryManager.getInstanceFor(cn1);

//		DiscoverItems dci = psm.discoverNodes(null);
//
//		Iterator it = dci.getItems();
//		while (it.hasNext()) {
//	          DiscoverItems.Item item = (DiscoverItems.Item) it.next();
//	          System.out.println("Node: "+ item.getNode());
//
//	      }
		
		DiscoverInfo dci = sdm.discoverInfo("pubsub.localhost", "node1");
		Iterator it = dci.getIdentities();
		while (it.hasNext()) {
			DiscoverInfo.Identity identity = (DiscoverInfo.Identity) it.next();
			System.out.println("Name: " + identity.getName());
			System.out.println("Type: " + identity.getType());
			System.out.println("Category: "+ identity.getCategory());
		}
		
		Thread.sleep(10000);
	    //Node wieder entfernen
//    	psm.deleteNode("node1");
		
		cn1.disconnect();
	}
	

}
