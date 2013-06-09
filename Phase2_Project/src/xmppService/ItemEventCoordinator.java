package xmppService;

import org.jivesoftware.smackx.pubsub.ItemPublishEvent;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;

public class ItemEventCoordinator<T> implements ItemEventListener {

    @Override
    public void handlePublishedItems(ItemPublishEvent items)
    {
        System.out.println("Item count: " + items.getItems().size());
        System.out.println(items.getItems().get(0));
	}

}
