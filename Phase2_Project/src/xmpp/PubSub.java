package xmpp;

import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.pubsub.AccessModel;
import org.jivesoftware.smackx.pubsub.ConfigureForm;
import org.jivesoftware.smackx.pubsub.FormType;
import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.ItemPublishEvent;
import org.jivesoftware.smackx.pubsub.LeafNode;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.PubSubManager;
import org.jivesoftware.smackx.pubsub.PublishModel;
import org.jivesoftware.smackx.pubsub.SimplePayload;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;

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
		PubSubManager psm = new PubSubManager(cn1);

		//Leaf erzeugen
		//psm.deleteNode("node1");
//		LeafNode leaf = psm.createNode("node1");
/*		ConfigureForm form = new ConfigureForm(FormType.submit);
		form.setDeliverPayloads(false);
		leaf.sendConfigurationForm(form);
*/
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
				new SimplePayload("book", null,
						"<book><title>Lord of the Rings</title></book>")));

	 		
		Thread.sleep(10000);
	    //Node wieder entfernen
//    	psm.deleteNode("node1");
		
		cn1.disconnect();
	}
	

}
