package xmppService;

import java.util.List;

import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.ItemPublishEvent;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;
import org.jivesoftware.smackx.pubsub.provider.ItemProvider;

public class ItemEventCoordinator<T> implements ItemEventListener {


    @Override
    public void handlePublishedItems(ItemPublishEvent items)
    {
       // System.out.println("Item count: " + items.getItems().size());
        //System.out.println(items.getItems().get(0));
     //   System.out.println(items);
        PayloadItem pi = (PayloadItem) items.getItems().get(0);
        PacketExtension payload = pi.getPayload();
        ItemProvider ip = new ItemProvider();

    	Item i = (Item) items.getItems().get(0);
       // String string = i.toXML();
    	String is = i.toXML();
    //	int pos = is.indexOf("Meldung");
    //	is = is.substring(pos);
      //  System.out.println(i.getElementName()+i.getId()+i.getNamespace()+i.getNode()+i.getClass());
        System.out.println(i.toXML());
	}

}
