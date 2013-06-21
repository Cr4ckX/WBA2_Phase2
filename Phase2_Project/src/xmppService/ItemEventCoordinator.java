package xmppService;

import generated.*;

import java.util.ArrayList;
import java.util.List;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.ItemPublishEvent;
import org.jivesoftware.smackx.pubsub.LeafNode;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.PubSubManager;
import org.jivesoftware.smackx.pubsub.SimplePayload;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;
import org.jivesoftware.smackx.pubsub.provider.ItemProvider;

import GUI.GUI;

import restService.ClientRest;

/**
 * Diese Klasse dient dazu, die entsprechenden Handlungen auszuführen, sobald zu einem
 * subscribten LeafNode ein neues Item gepublisht wurde. <br/><br/>
 * 
 * Das übertragene Item enthält Informationen darüber, zu welcher Veranstaltung/Sportart
 * ein Publish vorgenommen wurde und wertet dieses entsprechend aus.<br/>
 * Das Item enthält als ersten Character einen Präfix, welcher bestimmt, um welche
 * Art sich es bei der Änderung durch den Veranstalter handelt:<br/>
 * A = add (Es wurde ein Post ausgeübt, Subscriber der entsprechenden Sportart
 * erhalten eine Benachrichtigung<br/>
 * N = neue Sportgruppenlist (Es wurde eine Veranstaltung aus der subscribten
 * Sportart gelöscht. Subscriber erhalten entsprechend eine Benachrichtigung.<br/>
 * D = deltete (einer konkreten subscribten Veranstaltung; entsprechend bekommen alle
 * Subscriber dieser konkreten Veranstaltung eine Benachrichtigung, dass mit der
 * gelöschten Veranstaltung (HTTP DELETE), auch ihre Subscription entfernt wird.
 * R = refresh (einer konkreten subscribten Veranstaltung; wenn die Veranstaltung mittels
 * PUT geändert wurde, bekommen die Subscriber dieser Veranstaltung eine Benachrichtigung.
 *  
 *  Es wird also lediglich eine Meldung ausgegeben, der Benutzer muss, um sich genauere
 *  Informationen zu der Änderung/Löschung zu beschaffen,.. selber informieren, indem 
 *  er sich in das entsprechende Menü (Sportart/konkrete Veranstaltung) bewegt.
 * @author CrackX
 *
 */
public class ItemEventCoordinator implements ItemEventListener<Item> {

	ClientRest cr = new ClientRest();	
	GUI gui = new GUI();
	
    @Override
    public void handlePublishedItems(ItemPublishEvent<Item> items){

    	List<Item> itemList = items.getItems();
    	List<String> refreshList = new ArrayList<String>();
        PayloadItem<SimplePayload> pi;

        for(int i = 0; i<itemList.size(); i++){

        	pi = (PayloadItem<SimplePayload>) itemList.get(i);
        	refreshList.add(pi.getPayload().getElementName());
        }
        
        //A = add (post), R = refresh(put), D = delete, N = newSportgruppenlist
        for (String refresh:refreshList){
        	String temp = refresh;
        	if(temp.startsWith("A")){
        		String spg_id = String.valueOf(temp.charAt(1)); // = Sportgruppe
        		String spa_id = String.valueOf(temp.charAt(2)); // = Sportart
        		String vst_id = String.valueOf(temp.charAt(3)); // = neue Veranstaltung
        		throwAddNotice(spg_id, spa_id, vst_id);
        	}
        	
        	if(temp.startsWith("N")){
        		String spg_id = String.valueOf(temp.charAt(1)); // = Sportgruppe
        		String spa_id = String.valueOf(temp.charAt(2)); // = Sportart
        		String vst_id = String.valueOf(temp.charAt(3)); // = aktualisierte/gelöschte Veranstaltung
        		throwDeleteNoticeSportart(spg_id, spa_id, vst_id);
        	}
        	
        	if(temp.startsWith("R")){
        		String spg_id = String.valueOf(temp.charAt(1)); // = Sportgruppe
        		String spa_id = String.valueOf(temp.charAt(2)); // = Sportart
        		String vst_id = String.valueOf(temp.charAt(3)); // = aktualisierte/gelöschte Veranstaltung
        		throwRefreshNotice(spg_id, spa_id, vst_id);
        	} 
        	
        	if(temp.startsWith("D")){
        		String spg_id = String.valueOf(temp.charAt(1)); // = Sportgruppe
        		String spa_id = String.valueOf(temp.charAt(2)); // = Sportart
        		String vst_id = String.valueOf(temp.charAt(3)); // = aktualisierte/gelöschte Veranstaltung
        		throwDeleteNotice(spg_id, spa_id, vst_id);
        	}
        }         
    }
    /**
     * Methode zum Benachrichtigen für den Sportarten-Abonnent, wenn eine Veranstaltung in der abonnierten
     * Sportart gelöscht wird.
     * 
     * Der Unterschied zur 'throwDeleteNotice' Methode ist, dass auch der Sportart Abonnent eine
     * Benachrichtigung bekommt, wenn eine Veranstaltung gelöscht wird, nicht nur, wenn eine hinzugefügt wird.
     *
     *
     * TODO: Meldung nicht per Syso ausgeben, sondern irgendwie übergeben. (diese Klasse evtl. in die GUI?)
     * 
     * @param sportgruppeId Sportgruppen-ID
     * @param sportartId Sportart-ID (benachrichtigte Sportgruppe)
     * @param veranstaltungId Veranstaltung-ID (gelöschte Veranstaltung)
     */
	private void throwDeleteNoticeSportart(String sportgruppeId, String sportartId, String veranstaltungId){
    	
    	String meldung = "Es wurd eine Veranstaltung gelöscht. Welche ist leider unklar." + "\r\n";
    	String nameOfDeletedVeranstaltungen;
    	
    	VeranstaltungenM deletedVeranstaltungen = cr.getVeranstaltungen(sportgruppeId, sportartId, true);
    	Sportart newSportList = cr.getSportart(sportgruppeId, sportartId);
    	
    	meldung = "Es wurde eine Veranstaltung aus der Sportart " + newSportList.getSName() + " gelöscht.";
    	
    	for(Veranstaltung veranstaltung : deletedVeranstaltungen.getVeranstaltung()){
    		if(veranstaltung.getId().equals(veranstaltungId)){
    			nameOfDeletedVeranstaltungen = veranstaltung.getVBeschreibung();
            	meldung = "Die von Ihnen abonnierte Sportart: '" + newSportList.getSName() +
            			"', enthielt eine Veranstaltung (" + nameOfDeletedVeranstaltungen + "), welche leider gelöscht wurde." + "\r\n";
    		}
    	}
    	//System.out.println(meldung);
    	gui.addInteressentMeldung(meldung);
		
	}
	/**
	 * Methode zum Benachrichtigen für den Sportarten-Abonnent, wenn eine Veranstaltung in der abonnierten
     * Sportart hinzugefügt wird.
     * 
	 * @param sportgruppeId Sportgruppen-ID
	 * @param sportartId Sportart-ID (benachrichtigte Sportgruppe)
	 * @param veranstaltungId (hinzugefügte Veranstaltung)
	 */
	private void throwAddNotice(String sportgruppeId, String sportartId, String veranstaltungId){
    	
    	String meldung;
    	Sportart newSportList = cr.getSportart(sportgruppeId, sportartId);
    	Veranstaltung newVeranstaltung = cr.getVeranstaltung(sportgruppeId, sportartId, veranstaltungId);
    	  	
    	meldung = "Ihre abonnierte Sportart " + newSportList.getSName() + " wurde eine neue Veranstaltung (" + 
    			newVeranstaltung.getVBeschreibung() + ") hinzugefügt."  + "\r\n";
    	//System.out.println(meldung);
    	gui.addInteressentMeldung(meldung);
    }
    /**
     * Methode zum Benachrichtigen, dass eine abonnierte Veranstaltung geändert wurde.
     * 
     * @param sportgruppeId Sportgruppen-ID
     * @param sportartId Sportarten-ID
     * @param veranstaltungId Veranstaltung-ID (Benachrichtigung über geänderte Veranstaltung)
     */
    private void throwRefreshNotice(String sportgruppeId, String sportartId, String veranstaltungId){
    	
    	String meldung;
    	Sportgruppe sportgruppe = cr.getSportgruppe(sportgruppeId);
    	Sportart sportart = cr.getSportart(sportgruppeId, sportartId);
    	Veranstaltung newVeranstaltung = cr.getVeranstaltung(sportgruppeId, sportartId, veranstaltungId);
    	
    	meldung = "Ihre abonnierte Veranstaltung " + newVeranstaltung.getVBeschreibung() + " in der Sportart " +
    			sportart.getSName() + " der Sportgruppe " + sportgruppe.getSGName() +  " wurde aktualisiert."  + "\r\n";
    	//System.out.println(meldung);
    	gui.addInteressentMeldung(meldung);
    }
    
    /**
     * Methode zum Benachrichtigen, wenn die abonnierte Veranstaltung gelöscht wurde.
     * 
     * @param sportgruppeId Sportgruppen-ID
     * @param sportartId Sportarten-ID
     * @param veranstaltungId Veranstaltung-ID (Benachrichtung über gelöschte Veranstaltung)
     */
    private void throwDeleteNotice(String sportgruppeId, String sportartId, String veranstaltungId){
    	
  
    	String meldung = "Es wurd eine Veranstaltung gelöscht. Welche ist leider unklar." + "\r\n";
    	String nameOfDeletedVeranstaltungen;
    	VeranstaltungenM deletedVeranstaltungen = cr.getVeranstaltungen(sportgruppeId, sportartId, true);
    	for(Veranstaltung veranstaltung : deletedVeranstaltungen.getVeranstaltung()){
    	//s	System.out.println(veranstaltung.getId() + " == " + veranstaltungId);
    		if(veranstaltung.getId().equals(veranstaltungId)){
    			nameOfDeletedVeranstaltungen = veranstaltung.getVBeschreibung();
            	meldung = "Die von Ihnen abonnierte Veranstaltung: " + nameOfDeletedVeranstaltungen + " wurde leider gelöscht." +
            			"Damit wird auch Ihre Subscription zu dieser Veranstaltung aufgelöst." + "\r\n";
    		}
    	}
    	//System.out.println(meldung);
    	gui.addInteressentMeldung(meldung);

    	
    }
}
