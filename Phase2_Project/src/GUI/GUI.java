package GUI;
import generated.Sportart;
import generated.SportartenM;
import generated.Sportgruppe;
import generated.Veranstaltung;
import generated.VeranstaltungenM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

import servicesCombined.CombinedServicesInteressent;
import servicesCombined.CombinedServicesVeranstalter;

 
public class GUI 
{
	static CombinedServicesVeranstalter csv = new CombinedServicesVeranstalter();
	static CombinedServicesInteressent csi = new CombinedServicesInteressent();
	
	static int sportgruppenIndex = 0;
	static int sportartenIndex = 0;
	static int veranstaltungenIndex = 0;
	
	static Vector<String> v;
	static ActionListener DropDownSAListen = new ActionListener()
	{
		public void actionPerformed(ActionEvent dropdownSAe) 
		{	
			String info;   
			String labelInfo;
			
			btnUnsubscribeSA.setVisible(true);
			btnSubscribeSA.setVisible(true);    		    			
			sportartenIndex = dropdownSA.getSelectedIndex();
			
			
			info = csi.getSportart(String.valueOf(sportgruppenIndex),
					String.valueOf(sportartenIndex));
			labelInfo = csi.getSportartElement(String.valueOf(sportgruppenIndex), String.valueOf(sportartenIndex))
				.getSName();
			
			labelAreaSG.setText("Sportart: " + labelInfo);
			AreaSG.setText(info);
			

			if(csi.isSubscribed(String.valueOf(sportgruppenIndex)+String.valueOf(sportartenIndex)
					+"Sportart")){
				btnSubscribeSA.setEnabled(false);
				btnSubscribeSA.setToolTipText("Sie bekommen bereits Benachrichtigungen!");
				btnUnsubscribeSA.setEnabled(true);
				btnUnsubscribeSA.setToolTipText("Klicken Sie hier, wenn sie keine Benachrichtigungen mehr bekommen möchten.");
			}
			else{
				btnSubscribeSA.setEnabled(true);
				btnSubscribeSA.setToolTipText("Klicken Sie hier zum subscriben!");
				btnUnsubscribeSA.setEnabled(false);
				btnUnsubscribeSA.setToolTipText("Unsubscriben nicht möglich, subscriben Sie erst ;)");
				}   
			
			dropdownV.removeActionListener(DropDownVListen);
			dropdownV.removeAllItems();
			showDropdownV();
			veranstaltungenIndex = -1;
			
			}
		};
	
	static ActionListener DropDownVListen = new ActionListener(){
		
		public void actionPerformed(ActionEvent dropdownVe)
		{
			String info;  
			String labelInfo;
			
			// != wenn gelöschte Veranstaltungen dazwischen
			veranstaltungenIndex = 0;
			String inhalt = dropdownV.getSelectedItem().toString();
			List<Veranstaltung> veranstaltungen = csi.getVeranstaltungenElement(String.valueOf(sportgruppenIndex), 
					String.valueOf(sportartenIndex)).getVeranstaltung();
			
			for(Veranstaltung veranstaltungKonkret : veranstaltungen){
				if(veranstaltungKonkret.getVBeschreibung().equals(inhalt)){
					info = csi.getVeranstaltung((String.valueOf(sportgruppenIndex)), 
							(String.valueOf(sportartenIndex)), veranstaltungKonkret.getId());
					
					labelInfo = csi.getVeranstaltungElement(String.valueOf(sportgruppenIndex), 
							String.valueOf(sportartenIndex), 
							veranstaltungKonkret.getId()).getVBeschreibung();
					
					veranstaltungenIndex = Integer.parseInt(veranstaltungKonkret.getId());
					
					labelAreaSG.setText("Veranstaltung: " + labelInfo);
					AreaSG.setText(info);
				}
			}
		
			
			if(csi.isSubscribed(String.valueOf(sportgruppenIndex)+String.valueOf(sportartenIndex)
					+String.valueOf(veranstaltungenIndex)+("Veranstaltung"))){
				btnSubscribeSA.setEnabled(false);
				btnSubscribeSA.setToolTipText("Sie bekommen bereits Benachrichtigungen!");
				btnUnsubscribeSA.setEnabled(true);
				btnUnsubscribeSA.setToolTipText("Klicken Sie hier, wenn sie keine Benachrichtigungen mehr bekommen möchten.");
			}
			else{
				btnSubscribeSA.setEnabled(true);
				btnSubscribeSA.setToolTipText("Klicken Sie hier zum subscriben!");
				btnUnsubscribeSA.setEnabled(false);
				btnUnsubscribeSA.setToolTipText("Unsubscriben nicht möglich, subscriben Sie erst ;)");
				}    				
		}
	};
	
	static ActionListener DropDownVSAListen = new ActionListener() {
		
   		public void actionPerformed(ActionEvent dropdownSAe) { 			
   			
   			String info, labelInfo;
   			
   			sportartenIndex = dropdownVSA.getSelectedIndex();
   			info = csv.getSportart(String.valueOf(sportgruppenIndex), String.valueOf(sportartenIndex));
   			labelInfo = csv.getSportartElement(String.valueOf(sportgruppenIndex), String.valueOf(sportartenIndex))
   					.getSName();
   			
   			labelAreaVSG.setText("Sportart: " +labelInfo);
   			AreaVSG.setText(info);
   			
   			showButtonNewV();
   			
   			if (countVV >0){
//					hideAreaVSG();
   				btnDeleteV.setVisible(false);
   				btnEditV.setVisible(false);
   			}
   			
   			if (countNew > 0){
				hideFieldsNewV();
				hideLabelsFields();
			}
   			
//   			for (int i = 0; i < countSA; i++) {
//   				hideAreaVSG();
//			}
   			
   			dropdownVV.removeActionListener(DropDownVVListen);
   			dropdownVV.removeAllItems();
   			veranstaltungenIndex = -1;
   			showDropdownVV();
   		}
	};
	
	static ActionListener DropDownVVListen = new ActionListener() {
   		
   		public void actionPerformed(ActionEvent dropdownVVe) { 			
   			
   			String info, labelInfo;
   			showBtnDelete();
   			
   			veranstaltungenIndex = 0;
   			String inhalt = dropdownVV.getSelectedItem().toString();
   			List<Veranstaltung> veranstaltungen = csv.getVeranstaltungenElement(String.valueOf(sportgruppenIndex),
   					String.valueOf(sportartenIndex)).getVeranstaltung();
   			
			for(Veranstaltung veranstaltungKonkret : veranstaltungen){
				if(veranstaltungKonkret.getVBeschreibung().equals(inhalt)){
					info = csv.getVeranstaltung((String.valueOf(sportgruppenIndex)), 
							(String.valueOf(sportartenIndex)), veranstaltungKonkret.getId());
					
					labelInfo = csv.getVeranstaltungElement(String.valueOf(sportgruppenIndex), 
							String.valueOf(sportartenIndex), 
							veranstaltungKonkret.getId()).getVBeschreibung();
					
					veranstaltungenIndex = Integer.parseInt(veranstaltungKonkret.getId());

					labelAreaVSG.setText("Veranstaltung: " + labelInfo);
					AreaVSG.setText(info);
				}
			}
			
			showBtnEdit();
   			
   			countVV++;
   			
   			if (countNew > 0){
   				hideFieldsNewV();
   				hideLabelsFields();
   			}
   		}
	};
	//TODO: Kann theoretisch wieder nach veranstaltungenIndex abfragen.
	static ActionListener BtnSubscribe = new ActionListener(){
		public void actionPerformed(ActionEvent subscribed){

			if(veranstaltungenIndex >= 0){
				String inhalt = dropdownV.getSelectedItem().toString();
	   			List<Veranstaltung> veranstaltungen = csi.getVeranstaltungenElement(String.valueOf(sportgruppenIndex),
	   					String.valueOf(sportartenIndex)).getVeranstaltung();
	   			
				for(Veranstaltung veranstaltungKonkret : veranstaltungen){
					
					if(veranstaltungKonkret.getVBeschreibung().equals(inhalt)){
						
						if(csi.subscribeLeaf(String.valueOf(sportgruppenIndex) +
								String.valueOf(sportartenIndex) +
								veranstaltungKonkret.getId() +"Veranstaltung")){
												
							try {
								//Neuste Meldungen immer ganz oben.
								AreaAllgemeinSG.getDocument().insertString(0, "Veranstaltung wurde subscribed!\n", null);
							} catch (BadLocationException e) {
								
								e.printStackTrace();
							}
						}
						else{
							System.out.println("Fehler beim Subscriben!");
						}
					}
				}
			}
			else{
				if(csi.subscribeLeaf(String.valueOf(sportgruppenIndex)+
						String.valueOf(sportartenIndex) +"Sportart")){
									
					try {
						AreaAllgemeinSG.getDocument().insertString(0, "Sportart wurde subscribed!\n", null);
					} catch (BadLocationException e){
						e.printStackTrace();
					}
				}
				else{
					System.out.println("Fehler beim Subscriben!");
				}
			}
			showSubscriptionsList();
			btnSubscribeSA.setEnabled(false);
			btnSubscribeSA.setToolTipText("Sie bekommen bereits Benachrichtigungen!");
			btnUnsubscribeSA.setEnabled(true);
			btnUnsubscribeSA.setToolTipText("Klicken Sie hier, wenn sie keine Benachrichtigungen mehr bekommen möchten.");
		}
	};
	
	static ActionListener BtnUnsubscribe = new ActionListener(){
		public void actionPerformed(ActionEvent unsubscribed){
			if(veranstaltungenIndex >= 0){
				if(csi.unSubscribe(String.valueOf(sportgruppenIndex) +
						String.valueOf(sportartenIndex) +
						String.valueOf(veranstaltungenIndex) +"Veranstaltung")){
				
					try {
						AreaAllgemeinSG.getDocument().insertString(0, "Veranstaltung wurde unsubscribed!\n", null);
					} catch (BadLocationException e) {
						
						e.printStackTrace();
					}
					
				}
				else{
					System.out.println("Fehler beim Unsubscriben!!");
				}
			}
			else{
				if(csi.unSubscribe(String.valueOf(sportgruppenIndex)+
						String.valueOf(sportartenIndex) +"Sportart")){
									
					try {
						AreaAllgemeinSG.getDocument().insertString(0, "Sportart wurde unsubscribed!\n", null);
					} catch (BadLocationException e){
						e.printStackTrace();
					}
				}
				else{
					System.out.println("Fehler beim Unsubscriben!");
				}
			}
			showSubscriptionsList();
			btnSubscribeSA.setEnabled(true);
			btnSubscribeSA.setToolTipText("Klicken Sie hier zum subscriben!");
			btnUnsubscribeSA.setEnabled(false);
			btnUnsubscribeSA.setToolTipText("Unsubscriben nicht möglich, subscriben Sie erst ;)");
		}
	};
	
	/********************************************/
	/**************Interessent*******************/
	/********************************************/
	private static JLabel labelSG, labelSA, labelV, labelO, labelAreaSG, 
		labelAreaV, labelAreaO, labelVS, labelSubList;
	
	private static JComboBox dropdownSG, dropdownSA, dropdownV, dropdownVS, 
		dropdownO;		
	
	private static JTextArea AreaSG, AreaV, AreaO;
	
	private static JButton btnSubscribeSA, btnUnsubscribeSA,
		btnUnsubscribeV, btnSubscribeV, btnzurueckSG, btnzurueckVS, btnzurueckO, btnEditOK;  
	private static JList subscriptions;
	
	
	/********************************************/
	/**************Veranstalter******************/
	/********************************************/	
	private static JButton btnDeleteV, btnEditV, btnSubscribeE, btnSubscribeG, 
		btnUnsubscribeE, btnUnsubscribeG, btnZurueckVV, btnNewV, btnNewOK, 
		btnAbortEdit, btnAbortNew;
	
	private static JLabel labelVSG, labelAreaVV, labelAreaVSG, labelAreaVSA, 
		labelVSA, labelVV, labelVEBeschr, labelVEInfo, labelVEDatum, 
		labelVEUhrzeit, labelVENiveau, labelVEVorraussetungen, labelVEGebäude,
		labelAreaAllgSGV, labelAreaAllgVV, 
		labelAreaAllgV, labelAreaAllgSA, labelAreaAllgVS, labelAreaAllgO;		
	private static JComboBox dropdownVSG, dropdownVSA, dropdownVV, dropdownDayE, 
		dropdownMonthE, dropdownYearE, dropdownHourE, dropdownMinuteE, 
		dropdownGebäudeE, 
		dropdownDayAE, dropdownMonthAE, dropdownYearAE, dropdownHourAE, 
		dropdownMinuteAE, dropdownGebäudeAE;
	private static JTextArea AreaVV, AreaVSG, AreaVSA, AreaInfoE, AreInfoAE, 
		AreaAllgemeinPanelV, AreaAllgemeinSG, AreaAllgemeinVS,AreaAllgemeinO,
		AreaAllgemeinSA, AreaAllgemeinV;
	
	private static JTextField fieldBeschrE, fieldNiveauE, fieldBeschrAE,
		fieldVorraussetzungenE, fieldNiveauAE, fieldVorraussetzungenAE;	

	private static JScrollPane scrollpaneAreaInfoE, scrollpaneAreaAllg, 
		scrollpaneAreaInfoAE, scrollpaneAreaAllgSG, scrollpaneAreaAllgVS, 
		scrollpaneAreaAllgO, scrollpaneAreaAllgSA, scrollpaneAreaAllgV;
	
	/********************************************/
	/**************KlickCounter******************/
	/********************************************/
	static int countVV = 0;
	static int countNew = 0;
	static int countSA = 0;
		
	final static JFrame fenster1 = new JFrame("Herzlich Willkommen");
	final static JFrame fenster2 = new JFrame("Herzlich Willkommen");
	final static JTabbedPane tabLeiste = new JTabbedPane();
	final static JTabbedPane tabLeiste2 = new JTabbedPane();
		
    static JPanel PanelMain = new JPanel ();
	static JPanel panelSG = new JPanel();
	static JPanel panelVS = new JPanel();
	static JPanel panelO = new JPanel();
	static JPanel panelE = new JPanel();
	static JPanel panelGB = new JPanel();
	static JPanel panelVV = new JPanel();
		
    public static void main(String[] args) {
                
    	
    	v = new Vector<String>();
    	/********************************************/
    	/*****************Windows********************/
    	/********************************************/
    	fenster1.setSize(1000, 600);
    	fenster1.setResizable(false);
        fenster1.setLocationRelativeTo(null);
        fenster1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenster1.add(tabLeiste);
                
        fenster2.setSize(1000, 600);
        fenster2.setResizable(false);
        fenster2.setLocationRelativeTo(null);
        fenster2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenster2.add(tabLeiste2);
        
        
        /********************************************/
    	/**************OptionPane********************/
    	/*********dient der Rollenabfrage************/ 
        /********************************************/

        Icon icon = new ImageIcon( "/Ausarbeitungen/icon.png" );
        Object[] options = {"Interessent", "Veranstalter"};
               
        int selected = JOptionPane.showOptionDialog(
        null, "Welche Rolle haben Sie?", "Alternativen", JOptionPane.DEFAULT_OPTION, 
        JOptionPane.INFORMATION_MESSAGE, icon, options, options[0]);

        	//Also wurde Interessent gewählt!
        	if(selected == 0){
        		try {
        			//Verbindung als Interessent aufbauen
					csi.initialize();
				} catch (InterruptedException e) {
					System.out.println("Fehler beim Verbinden!");
					e.printStackTrace();
				}
	            showPanelsI();
	            showDropdownSG();
	            showAreaO();
	            showAreaVS();
	            showButtonZurueckO();
	            showLogoutSG();
	            showButtonZurueckVS();
	            showAreaAllgemeinSG();	
	            showAreaAllgemeinVS();
	            showAreaAllgemeinO(); 
	            createDropdownSA();
	            createDropdownV();
	            createSubscriptionList();
	            showSubscriptionsList();
	            fenster1.setVisible(true);
	            fenster2.setVisible(false);
             } 

        	
        	//Veranstalter wurde gewählt
             if(selected == 1){
         		try {
        			//Verbindung als Veranstalter aufbauen
					csv.initialize();
				} catch (InterruptedException e) {
					System.out.println("Fehler beim Verbinden!");
					e.printStackTrace();
				}
               	showPanelsV();
               	showDropdownVSG();
               	showBtnLogoutVV();
	            showAreaAllgPanelV();
	            createDropdownVS();
	            createDropdownVSA();
	            createDropdownVV();
                fenster2.setVisible(true);
                fenster1.setVisible(false);
             }
            
            
         	
    		labelAreaSG = new JLabel("Informationen:");
            labelAreaSG.setBounds(600, 10, 300, 100);
            
            labelAreaVSG = new JLabel ("Informationen:");
            labelAreaVSG.setBounds(600, 10, 300, 100);
            
			showAreaSG();
			
			
			btnSubscribeSA = new JButton("Subscribe");
        	btnSubscribeSA.setBounds(600, 400, 150, 25);
        	panelSG.add(btnSubscribeSA);
        	btnSubscribeSA.setVisible(false);
        	btnSubscribeSA.addActionListener(BtnSubscribe);
        	
        	panelSG.validate();
        	panelSG.repaint();
        	
        	btnUnsubscribeSA = new JButton("Unsubscribe");
        	btnUnsubscribeSA.setBounds(800, 400, 150, 25);
        	panelSG.add(btnUnsubscribeSA);
        	btnUnsubscribeSA.setVisible(false);
        	btnUnsubscribeSA.addActionListener(BtnUnsubscribe);
        	panelSG.validate();
        	panelSG.repaint();
        	
        	btnEditV = new JButton("Ändern");
    		btnEditV.setBounds( 600, 450, 150, 25);
    		btnEditV.setVisible(false);
        	panelVV.add(btnEditV);
        	panelVV.validate();
        	panelVV.repaint();
        	
        	btnDeleteV = new JButton("Löschen");
    		btnDeleteV.setBounds(800, 450, 150, 25);
    		btnDeleteV.setVisible(false);
        	panelVV.add(btnDeleteV);
        	panelVV.validate();
        	panelVV.repaint();
        	
        	//Label Fields zum Erstellen
        	labelVEBeschr = new JLabel("Name/Beschreibung:");
    		labelVEBeschr.setBounds(650, 60, 200, 25);
    		labelVEBeschr.setVisible(false);
    		panelVV.add(labelVEBeschr);
    		panelVV.validate();
    		
    		labelVEInfo = new JLabel("Informationen:");
        	labelVEInfo.setBounds(650, 110, 200, 25);
        	labelVEInfo.setVisible(false);
    		panelVV.add(labelVEInfo);
    		panelVV.validate();
    		
        	labelVEDatum = new JLabel("Datum:");
        	labelVEDatum.setBounds(650, 185, 200, 25);
        	labelVEDatum.setVisible(false);
    		panelVV.add(labelVEDatum);
    		panelVV.validate();
    		
    		labelVEUhrzeit = new JLabel("Uhrzeit:");
        	labelVEUhrzeit.setBounds(650, 225, 200, 25);
        	labelVEUhrzeit.setVisible(false);
    		panelVV.add(labelVEUhrzeit);
    		panelVV.validate();

        	labelVENiveau = new JLabel("Niveau:");
        	labelVENiveau.setBounds(650, 270, 200, 25);
        	labelVENiveau.setVisible(false);
    		panelVV.add(labelVENiveau);
    		panelVV.validate();

        	labelVEVorraussetungen = new JLabel("Voraussetzungen:");
        	labelVEVorraussetungen.setBounds(650, 320, 200, 25);
        	labelVEVorraussetungen.setVisible(false);
    		panelVV.add(labelVEVorraussetungen);
    		panelVV.validate();

        	labelVEGebäude = new JLabel("Gebäude:");
        	labelVEGebäude.setBounds(650, 370, 200, 25);
        	labelVEGebäude.setVisible(false);
    		panelVV.add(labelVEGebäude);
    		panelVV.validate();
    		
    		
    		//Fields zum Erstellen
    		
    		fieldBeschrE = new JTextField("");
    		fieldBeschrE.setToolTipText("Bitte tragen Sie hier einen Namen und eine Beschreibung der Veranstaltung ein");
    		fieldBeschrE.setBounds(650, 80, 300, 25);
    		fieldBeschrE.setToolTipText("Geben Sie eine Beschreibung ein");
    		fieldBeschrE.setVisible(false);
        	panelVV.add(fieldBeschrE);
        	panelVV.validate();
    		
        	AreaInfoE = new JTextArea(5, 20);
        	AreaInfoE.setText("");
        	 AreaInfoE.setLineWrap(true);
        	 AreaInfoE.setWrapStyleWord(true);
             scrollpaneAreaInfoE = new JScrollPane(AreaInfoE); 
          	 scrollpaneAreaInfoE.setBounds(650, 130, 300, 50);
          	 scrollpaneAreaInfoE.setToolTipText("Geben Sie Informationen bezüglich der Informationen ein");
             panelVV.add(scrollpaneAreaInfoE);
             scrollpaneAreaInfoE.setVisible(false);
             
        	final String[] DropDownDay = new String[] {"01", "02","03","04","05","06","07","08", "09","10","11","12","13", "14","15","16","17","18","19","20","21","22","23", "24","25","26","27","28","29","30","31"};
        	dropdownDayE = new JComboBox(DropDownDay);
        	dropdownDayE.setBounds(650, 200, 80, 30);
        	dropdownDayE.setToolTipText("Tag");
        	dropdownDayE.setVisible(false);
        	panelVV.add(dropdownDayE);
        	panelVV.validate();
        	
        	final String[] DropDownMonth = new String[] {"01", "02","03","04","05","06","07","08", "09","10","11","12"};
        	dropdownMonthE = new JComboBox(DropDownMonth);
        	dropdownMonthE.setBounds(750, 200, 80, 30);
        	dropdownMonthE.setToolTipText("Monat");
        	dropdownMonthE.setVisible(false);
        	panelVV.add(dropdownMonthE);
        	panelVV.validate();
        	
        	
        	final String[] DropDownYear = new String[] {"2013", "2014", "2015", "2016"};
        	dropdownYearE = new JComboBox(DropDownYear);
        	dropdownYearE.setVisible(false);
        	dropdownYearE.setBounds(850, 200, 105, 30);
        	dropdownYearE.setToolTipText("Jahr");
        	panelVV.add(dropdownYearE);
        	panelVV.validate();

        	final String[] DropDownHour = new String[] {"00","01","02","03","04", "05", "06", "07", "08", "09", "10", "11", "12", "13","14","15","16","17","18","19","20","21", "22", "23"};
        	dropdownHourE = new JComboBox(DropDownHour);
        	dropdownHourE.setBounds(650, 240, 80, 30);
        	dropdownHourE.setToolTipText("Stunde");
        	dropdownHourE.setVisible(false);
        	panelVV.add(dropdownHourE);
        	panelVV.validate();
        	
        	
        	final String[] DropDownMinute = new String[] {"00","01", "02","03","04","05","06","07","08", "09","10","11","12","13", "14","15","16","17","18","19","20","21","22","23", "24","25","26","27","28","29","30","31", "32","33","34","35","36","37","38", "39","40","41","42","43", "44","45","46","47","48","49","50","51","52","53", "54","55","56","57","58","59"};
        	dropdownMinuteE = new JComboBox(DropDownMinute);
        	dropdownMinuteE.setBounds(740, 240, 80, 30);
        	dropdownMinuteE.setToolTipText("Minute");
        	dropdownMinuteE.setVisible(false);
        	panelVV.add(dropdownMinuteE);
        	panelVV.validate();
        	
        	
        	fieldNiveauE = new JTextField("");
        	fieldNiveauE.setBounds(650, 290, 300, 25);
        	fieldNiveauE.setToolTipText("Bitte geben Sie hier das Niveau der Veranstaltung ein!");
        	fieldNiveauE.setVisible(false);
        	panelVV.add(fieldNiveauE);
        	panelVV.validate();
        	
        	
        	fieldVorraussetzungenE = new JTextField("");
        	fieldVorraussetzungenE.setBounds(650, 340, 300, 25);
        	fieldVorraussetzungenE.setToolTipText("Bitte geben Sie hier die Voraussetzung der Veranstaltung ein!");
        	fieldVorraussetzungenE.setVisible(false);
        	panelVV.add(fieldVorraussetzungenE);
        	panelVV.validate();
        	
        	final String[] DropDownGebäude = new String[] {"G1", "G2", "10", "11", "12", "13"};
        	dropdownGebäudeE = new JComboBox(DropDownGebäude);
        	dropdownGebäudeE.setBounds(650, 390, 300, 30);
        	dropdownGebäudeE.setToolTipText("Wählen Sie ein Gebäude");
        	dropdownGebäudeE.setVisible(false);
        	panelVV.add(dropdownGebäudeE);
        	panelVV.validate();
        	
        	fieldBeschrAE = new JTextField("");
    		fieldBeschrAE.setBounds(650, 80, 300, 25);
    		fieldBeschrAE.setToolTipText("Geben Sie eine Beschreibung ein");
    		fieldBeschrAE.setVisible(false);
        	panelVV.add(fieldBeschrAE);
        	panelVV.validate();
        	
    		
        	AreInfoAE = new JTextArea(5, 20);
         	AreInfoAE.setText("");
         	AreInfoAE.setLineWrap(true);
         	AreInfoAE.setWrapStyleWord(true);
         	scrollpaneAreaInfoAE = new JScrollPane(AreInfoAE); 
         	scrollpaneAreaInfoAE.setBounds(650, 130, 300, 50);
         	scrollpaneAreaInfoAE.setToolTipText("Geben Sie eine Info bezüglich ihrer Beschreibung ein");
         	scrollpaneAreaInfoAE.setVisible(false);
            panelVV.add(scrollpaneAreaInfoAE);
        	
    		
        	final String[] DropDownDayAE = new String[] {"01", "02","03","04","05","06","07","08", "09","10","11","12","13", "14","15","16","17","18","19","20","21","22","23", "24","25","26","27","28","29","30","31"};
        	dropdownDayAE = new JComboBox(DropDownDayAE);
        	dropdownDayAE.setBounds(650, 200, 80, 30);
        	dropdownDayAE.setToolTipText("Tag");
        	dropdownDayAE.setVisible(false);
        	panelVV.add(dropdownDayAE);
        	panelVV.validate();
        	
        	final String[] DropDownMonthAE = new String[] {"01", "02","03","04","05","06","07","08", "09","10","11","12"};
        	dropdownMonthAE = new JComboBox(DropDownMonthAE);
        	dropdownMonthAE.setBounds(750, 200, 80, 30);
        	dropdownMonthAE.setToolTipText("Monat");
        	dropdownMonthAE.setVisible(false);
        	panelVV.add(dropdownMonthAE);
        	panelVV.validate();
        	
        	
        	final String[] DropDownYearAE = new String[] {"2013", "2014", "2015", "2016"};
        	dropdownYearAE = new JComboBox(DropDownYearAE);
        	dropdownYearAE.setBounds(850, 200, 105, 30);
        	dropdownYearAE.setToolTipText("Jahr");
        	dropdownYearAE.setVisible(false);
        	panelVV.add(dropdownYearAE);
        	panelVV.validate();

        	
        	final String[] DropDownHourAE = new String[] {"00","01","02","03","04", "05", "06", "07", "08", "09", "10", "11", "12", "13","14","15","16","17","18","19","20","21", "22", "23"};
        	dropdownHourAE = new JComboBox(DropDownHourAE);
        	dropdownHourAE.setBounds(650, 240, 80, 30);
        	dropdownHourAE.setToolTipText("Stunde");
        	dropdownHourAE.setVisible(false);
        	panelVV.add(dropdownHourAE);
        	panelVV.validate();
        	
        	
        	final String[] DropDownMinuteAE = new String[] {"00","01", "02","03","04","05","06","07","08", "09","10","11","12","13", "14","15","16","17","18","19","20","21","22","23", "24","25","26","27","28","29","30","31", "32","33","34","35","36","37","38", "39","40","41","42","43", "44","45","46","47","48","49","50","51","52","53", "54","55","56","57","58","59"};
        	dropdownMinuteAE = new JComboBox(DropDownMinuteAE);
        	dropdownMinuteAE.setBounds(740, 240, 80, 30);
        	dropdownMinuteAE.setToolTipText("Minute");
        	dropdownMinuteAE.setVisible(false);
        	panelVV.add(dropdownMinuteAE);
        	panelVV.validate();
        	
        	
        	fieldNiveauAE = new JTextField("");
        	fieldNiveauAE.setBounds(650, 290, 300, 25);
        	fieldNiveauAE.setToolTipText("Geben Sie ein Niveau für ihre Veranstaltung ein");
        	fieldNiveauAE.setVisible(false);
        	panelVV.add(fieldNiveauAE);
        	panelVV.validate();
        	
        	
        	fieldVorraussetzungenAE = new JTextField("");
        	fieldVorraussetzungenAE.setBounds(650, 340, 300, 25);
        	fieldVorraussetzungenAE.setVisible(false);
        	fieldVorraussetzungenAE.setToolTipText("Geben Sie de Voraussetzungen für ihre Veranstaltung ein");
        	panelVV.add(fieldVorraussetzungenAE);
        	panelVV.validate();
        	
        	
        	
        	final String[] DropDownGebäudeAE = new String[] {"G1", "G2", "10", "11", "12", "13"};
        	dropdownGebäudeAE = new JComboBox(DropDownGebäudeAE);
        	dropdownGebäudeAE.setBounds(650, 390, 300, 30);
        	dropdownGebäudeAE.setVisible(false);
        	panelVV.add(dropdownGebäudeAE);
        	panelVV.validate();
        	
        	btnNewV = new JButton("Neue Veranstaltung");
        	btnNewV.setBounds(220, 120, 150, 25);
        	btnNewV.setVisible(false);
        	panelVV.add(btnNewV);
        	panelVV.validate();
        	panelVV.repaint();
        	
        	btnNewOK = new JButton("OK");
    		btnNewOK.setBounds(800, 450, 150, 25);
    		btnNewOK.setVisible(false);
    		panelVV.add(btnNewOK);
    		panelVV.validate();
        	panelVV.repaint();
        	
        	btnAbortNew = new JButton("Abbrechen");
    		btnAbortNew.setBounds(650, 450, 150, 25);
    		btnAbortNew.setVisible(false);
    		panelVV.add(btnAbortNew);
    		panelVV.validate();
        	panelVV.repaint();
        	
        	btnEditOK = new JButton("Übernehmen");
    		btnEditOK.setBounds(800, 450, 150, 25);
    		btnEditOK.setVisible(false);
        	panelVV.add(btnEditOK);
        	panelVV.validate();
        	panelVV.repaint();
        	
        	btnAbortEdit = new JButton("Abbrechen");
    		btnAbortEdit.setBounds( 650, 450, 150, 25);
    		btnAbortEdit.setVisible(false);
        	panelVV.add(btnAbortEdit);
        	panelVV.validate();
        	panelVV.repaint();
        	
            panelVV.add(labelAreaVSG);
            labelAreaVSG.setVisible(true);
         	panelVV.validate();
         	panelVV.repaint();

            AreaVSG = new JTextArea();
            AreaVSG.setLineWrap(true);
            AreaVSG.setBounds(600, 90, 350, 300);
            AreaVSG.setEditable(false);
            panelVV.add(AreaVSG);
            panelVV.validate();
        	
    		
            //TODO: 0000000000
    }	
			
	
    
    /*Nützliche Schnipsel
     * 
     * Eine ComboBox (Dropdown) mit einem Array füllen
     * DefaultComboBoxModel model = new DefaultComboBoxModel( yourStringArray );
		comboBox.setModel( model );
     * 
     * removeAllItems() - Löscht alle Items der Combobox
     * addItem() - Fügt eins hinzu
     * 
     * 
     * */
    
    /**************************PANELS********************
     *Die einzelnen Tabs werden dem Panel hinzugefügt
     *Layouter wird gesetzt (null-Layout)
     *
     ****************************************************************/
    public static void showPanelsI(){
    		
	    	
	        
    		PanelMain.setLayout(null);
    		
	        panelSG.setLayout(null);	
	        tabLeiste.addTab("Sportgruppen", panelSG);
	            
	        
	        panelVS.setLayout(null);
	        tabLeiste.addTab("Veranstalter", panelVS);
	
	        panelO.setLayout(null);
	        tabLeiste.addTab("Orte", panelO);
    	}
    public static void showPanelsV(){
    		
	    	/***************************************************************/
	    	/**************************PANEL********************************/
	    	/***************************************************************/	
	        
    		panelVV.setLayout(null);
	        tabLeiste2.addTab("Sportveranstaltungen", panelVV);
    		
	        panelE.setLayout(null);	
	        tabLeiste2.addTab("Equiment", panelE);
	            
	        
	        panelGB.setLayout(null);
	        tabLeiste2.addTab("Gebäude", panelGB);
	
	        
    	}
    
    /**************************DropdownSG********************
     *Das Dropdown mit den Sportgruppen wird angezeigt.
     *Hierzu wird gleichzeitig das Label erstellt.
     *Sobald eine SG gewählt wurde, erscheint das DropDownSA sowie eine Area.
     *TODONE: In den String DropdownSG müssen die richtigen Sportgruppen rein
     *TODONE: AreaSG muss befüllt werden
     * @throws InterruptedException 
     ****************************************************************/
    public static void showDropdownSG()
    	{
	    		List<String> sportgruppenListe = csi.getSportgruppen();
	    		
	            labelSG = new JLabel("Bitte wählen Sie eine Sportgruppe!");
	            labelSG.setBounds(10, 50, 300, 25);
	            panelSG.add(labelSG);
	            
	            final String[] DropDownSG = sportgruppenListe.toArray(new String[sportgruppenListe.size()]);
	        	dropdownSG = new JComboBox(DropDownSG);
	        	dropdownSG.setBounds(10, 70, 200, 25);
	        	panelSG.add(dropdownSG);
	        	

	        	dropdownSG.addActionListener(new ActionListener()
	        	{
	        		public void actionPerformed(ActionEvent dropdownSGe) 
	        		{ 	
	        			String info;
	        			String labelInfo;
	        			sportgruppenIndex = dropdownSG.getSelectedIndex();
	        			
	        			labelInfo = csi.getSportgruppeElement(String.valueOf(sportgruppenIndex)).getSGName();
	        			info = csi.getSportgruppe(String.valueOf(sportgruppenIndex));
	        			
	        			AreaSG.setText(info);
	        			labelAreaSG.setText("Sportgruppe: " + labelInfo);
	        			
	        			dropdownSA.removeActionListener(DropDownSAListen);
	        			dropdownSA.setVisible(false);
 	        			dropdownSA.removeAllItems();
 	        			if(btnUnsubscribeSA.isVisible()){
 	 	        			btnUnsubscribeSA.setVisible(false);
 	 	        			btnSubscribeSA.setVisible(false);
 	        			}
 	        			
	        			showDropdownSA();
	        		}
	        	});
    	}

    /**************************DropdownSA********************
     *Das Dropdown mit den Sportarten wird angezeigt.
     *Hierzu wird gleichzeitig das Label erstellt.
     *Sobald eine SA gewählt wurde, erscheint das DropDownV sowie eine Area.
     *TODONE: In den String DropdownSA müssen die richtigen Sportarten rein
     *TODONE: AreaSA muss befüllt werden
     *****************************************************************/	
   public static void createDropdownSA(){
	   
	   labelSA = new JLabel("Bitte wählen Sie eine Sportart!");
	   labelSA.setBounds(10, 100, 300, 25);
	   labelSA.setVisible(false);
	   panelSG.add(labelSA);
	   panelSG.validate();
	   panelSG.repaint();
		
	   final String[] DropDownSA = {""};
	   dropdownSA = new JComboBox(DropDownSA);
	   dropdownSA.setBounds(10, 120, 200, 25);
	   dropdownSA.setVisible(false);
	   panelSG.add(dropdownSA);	
	   panelSG.validate();
	
   }

    public static void showDropdownSA(){
    		
    	List<String> sportartenListe = csi.getSportarten(String.valueOf(sportgruppenIndex));
    	labelSA.setVisible(true);
    	
    	for(String sportarten:sportartenListe){
        	dropdownSA.addItem(sportarten);
    	}
    	dropdownSA.setVisible(true);
    	dropdownSA.addActionListener(DropDownSAListen);
    }
    

    /**************************DropdownV********************
     * Das Dropdown mit den Veranstaltungen wird angezeigt.
     * Hierzu wird gleichzeitig das Label erstellt.
     * Sobald eine V gewählt wurde, erscheinen die Subscribe und Unsubrice Button sowie eine Area.
     * TODONE: In den String DropdownV müssen die richtigen Veranstaltungen rein
     * TODONE: AreaV muss befüllt werden
     * ****************************************************************/   	
    public static void createDropdownV(){
    	
    	labelV = new JLabel("Bitte wählen Sie eine Veranstaltung");
		labelV.setBounds(10, 150, 300, 25);
		labelV.setVisible(false);
		panelSG.add(labelV);
		panelSG.validate();
		
        final String[] DropDownV = {""};
    	
        dropdownV = new JComboBox(DropDownV);
    	dropdownV.setBounds(10, 170, 200, 25);
    	dropdownV.setVisible(false);
    	panelSG.add(dropdownV);
    	panelSG.validate();
    }
    
    public static void showDropdownV(){
    	
    	List<String> veranstaltungenListe = csi.getVeranstaltungen(String.valueOf(sportgruppenIndex), String.valueOf(sportartenIndex));
    	labelV.setVisible(true);
    	
    	for(String veranstaltungen:veranstaltungenListe){
        	dropdownV.addItem(veranstaltungen);
    	}
    	dropdownV.setVisible(true);
    	dropdownV.addActionListener(DropDownVListen);
    		
    }

    	
    /**************************DropdownVS********************
     * Das Dropdown mit den Veranstaltern wird angezeigt.
     * Hierzu wird gleichzeitig das Label erstellt.
     * Sobald ein VS gewählt wurde, erscheinen die Subscribe und Unsubrice Button sowie eine Area.
     * TODOEVLT: In den String DropdownVS müssen die richtigen Veranstalter rein
     * TODOEVTL: AreaVS muss befüllt werden
     * ****************************************************************/ 
   public static void createDropdownVS(){
		labelVS = new JLabel("Bitte wählen Sie eine(n) Veranstalter/in!");
		labelVS.setBounds(10, 10, 300, 100);
		labelVS.setVisible(false);
		panelVS.add(labelVS);      
		panelVS.validate();
       
	   	final String[] DropDownVS = new String[] {"Veranstalter", "David", "Laura", "Super", "Mentor"};
	   	dropdownVS = new JComboBox(DropDownVS);
	   	dropdownVS.setBounds(10, 70, 200, 25);
	   	dropdownVS.setVisible(false);
	   	panelVS.add(dropdownVS);
	   	panelVS.validate();
   }
    
    public static void showDropdownVS(){
    		labelVS.setVisible(true);
    		dropdownVS.setVisible(true);   	
    	}

    /**************************DropdownO********************
     * Das Dropdown mit den Orten wird angezeigt.
     * Hierzu wird gleichzeitig das Label erstellt.
     * Sobald ein O gewählt wurde, erscheinen die Subscribe und Unsubrice Button sowie eine Area.
     * TODOEVTL: In den String DropdownO müssen die richtigen Ort rein
     * TODOEVTL: AreaO muss befüllt werden
     * ****************************************************************/ 
    public static void createDropdownO(){
    	
    	labelO = new JLabel("Bitte wählen Sie einen Ort");
        labelO.setBounds(10, 10, 300, 100);
        labelO.setVisible(false);
        panelO.add(labelO);
        labelO.validate();
		
		final String[] DropDownO = new String[] {"Orte", "Bernberg", "Hesselbach", "Niederseßmar", "Wiehl"};
    	dropdownO = new JComboBox(DropDownO);
    	dropdownO.setVisible(false);
    	dropdownO.setBounds(10, 70, 200, 25);
    	panelO.add(dropdownO);
    	panelO.validate();
    }
    
    public static void showDropdownO(){ 		
        labelO.setVisible(true);
    	dropdownO.setVisible(true);
    	}

    /**************************Logout**********************************
     * Die Logout Button werden erstellt. 
     * Mittels ActionListenern wird erneut die OptionPane eingeblendet
     * ****************************************************************/ 
    public static void showLogoutSG(){
    		
    		btnzurueckSG = new JButton ("Logout");
        	btnzurueckSG.setBounds(800, 500, 150, 25);
        	btnzurueckSG.setToolTipText("Zurück zur Rollenauswahl");
        	panelSG.add(btnzurueckSG);
        	panelSG.validate();    	
        	
        	btnzurueckSG.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent btnzuruecke) {
				            	
            		fenster1.dispose();
                	
                	Object[] options = {"Interessent", "Veranstalter"};
                    
                    int selected = JOptionPane.showOptionDialog(null,
                      "Welche Rolle haben Sie?",
                      "Alternativen",
                      JOptionPane.DEFAULT_OPTION, 
                      JOptionPane.INFORMATION_MESSAGE, 
                      null, options, options[0]);
    					
                    if(selected == 0)
                    {
        	            showPanelsI();
        	            showDropdownSG();
        	            showDropdownO();
        	            showAreaO();
        	            showAreaVS();
        	            showButtonZurueckO();
        	            showLogoutSG();
        	            showButtonZurueckVS();
        	            showAreaAllgemeinSG();	
        	            showAreaAllgemeinVS();
        	            showAreaAllgemeinO();

        	             
        	            fenster1.setVisible(true);
        	            fenster2.setVisible(false);
                     } 

                     if(selected == 1)
                     {
                       	showPanelsV();
                       	showDropdownVSG();
                       	showBtnLogoutVV();
        	            showAreaAllgPanelV();

                        	
                        fenster2.setVisible(true);
                        fenster1.setVisible(false);
                     }
                     csi.logout();
            	}
        	});
    	}
 	
    public static void showButtonZurueckVS(){
    		
    		btnzurueckVS = new JButton ("Logout");
        	btnzurueckVS.setBounds(800, 500, 150, 25);
        	btnzurueckVS.setToolTipText("Zurück zur Rollenauswahl");
        	panelVS.add(btnzurueckVS);
        	panelVS.validate();

        	
        	
        	btnzurueckVS.addActionListener(new ActionListener() {
				
				
            	public void actionPerformed(ActionEvent btnzurueckVe) {
				
            	fenster1.dispose();
            	
            	Object[] options = {"Interessent", "Veranstalter"};
                
                int selected = JOptionPane.showOptionDialog(null,
                  "Welche Rolle haben Sie?",
                  "Alternativen",
                  JOptionPane.DEFAULT_OPTION, 
                  JOptionPane.INFORMATION_MESSAGE, 
                  null, options, options[0]);
					
                if(selected == 0)
                {
    	            showPanelsI();
    	            showDropdownSG();
    	            showDropdownVS();
    	            showDropdownO();
    	            showAreaO();
    	            showAreaVS();
    	            showButtonZurueckO();
    	            showLogoutSG();
    	            showButtonZurueckVS();
    	            showAreaAllgemeinSG();	
    	            showAreaAllgemeinVS();
    	            showAreaAllgemeinO();
    	            

    	             
    	            fenster1.setVisible(true);
    	            fenster2.setVisible(false);
                 } 

                 if(selected == 1)
                 {
                   	showPanelsV();
                   	showDropdownVSG();
                   	showBtnLogoutVV();
    	            showAreaAllgPanelV();

                    	
                    fenster2.setVisible(true);
                    fenster1.setVisible(false);
                 }
                
				}
			});
    	}

    public static void showButtonZurueckO(){
    		
    		btnzurueckO = new JButton ("Logout");
        	btnzurueckO.setBounds(800, 500, 150, 25);
        	btnzurueckO.setToolTipText("Zurück zur Rollenauswahl");
        	panelO.add(btnzurueckO);
        	panelO.validate();

        	
        	
        	btnzurueckO.addActionListener(new ActionListener() {
				
				
            	public void actionPerformed(ActionEvent btnzurueckOe) {
				
            		fenster1.dispose();
                	
                	Object[] options = {"Interessent", "Veranstalter"};
                    
                    int selected = JOptionPane.showOptionDialog(null,
                      "Welche Rolle haben Sie?",
                      "Alternativen",
                      JOptionPane.DEFAULT_OPTION, 
                      JOptionPane.INFORMATION_MESSAGE, 
                      null, options, options[0]);
    					
                    if(selected == 0)
                    {
        	            showPanelsI();
        	            showDropdownSG();
        	            showDropdownVS();
        	            showDropdownO();
        	            showAreaO();
        	            showAreaVS();
        	            showButtonZurueckO();
        	            showLogoutSG();
        	            showButtonZurueckVS();
        	            showAreaAllgemeinSG();	
        	            showAreaAllgemeinVS();
        	            showAreaAllgemeinO();

        	             
        	            fenster1.setVisible(true);
        	            fenster2.setVisible(false);
                     } 

                     if(selected == 1)
                     {
                       	showPanelsV();
                       	showDropdownVSG();
                       	showBtnLogoutVV();
        	            showAreaAllgPanelV();

                        	
                        fenster2.setVisible(true);
                        fenster1.setVisible(false);
                     }
                    
    				}
    			});
        	}

    /***************Areas zur Darstellung der Werte in der XML***********
     * 
     * Die Areas (rechts in der GUI) werden erstellt.
     * AreaSG, AreaVS und AreaO
     * 
     * 
     * TODO: Nur ein dynamisches Label.
     * TODONE: Areas mit Inhalt befüllen. 
     * Das macht man mit AreaXYZ.setText(t);
     *
     * ****************************************************************/
    public static void showAreaSG(){    		
            panelSG.add(labelAreaSG);
        	panelSG.validate();
        	panelSG.repaint();

            AreaSG = new JTextArea();
            AreaSG.setLineWrap(true);
    		AreaSG.setBounds(600, 90, 350, 300);
    		AreaSG.setEditable(false);
    		panelSG.add(AreaSG);
        	panelSG.validate();            
    	}
    	
    public static void showAreaVS(){
    	
            
            labelAreaV = new JLabel("Informationen bezüglich: ");
            labelAreaV.setBounds(600, 10, 300, 100);
            panelVS.add(labelAreaV);
            panelVS.validate();
            

            AreaV = new JTextArea();
            AreaV.setLineWrap(true);
    		AreaV.setBounds(600, 90, 350, 300);
    		panelVS.add(AreaV);
    		panelVS.validate();
    		
    		
    	}
    	
    public static void showAreaO(){
    	
            
            labelAreaO = new JLabel("Informationen bezüglich: ");
            labelAreaO.setBounds(600, 10, 300, 100);
            panelO.add(labelAreaO);
        	panelO.validate();

            

            AreaO = new JTextArea();
            AreaO.setLineWrap(true);
    		AreaO.setBounds(600, 90, 350, 300);
    		panelO.add(AreaO);
        	panelO.validate();

            
    		
    	}
    	
    public static void createSubscriptionList(){
    	
    	labelSubList = new JLabel("Ihre Abonnements:");
    	labelSubList.setBounds(350, 300, 300, 100);
    	labelSubList.setVisible(false);
        panelSG.add(labelSubList);
    	panelSG.validate();
    	panelSG.repaint();
    	
        String SubsribedItems[] = {""};
        subscriptions = new JList(SubsribedItems);
        subscriptions.setBounds(350, 370, 200, 150);
        subscriptions.setVisible(false);
        panelSG.add(subscriptions);
    }

    /**
     * Zeige und aktualisiere alle Subscriptions. 
     * Momentan werden alle möglichen, zu durchlaufenden Veranstaltungen und Sportarten nach 
     * Subscriptios durchsucht, da 'showSubscriptions' nur die LeafNodes lieferte.
     * Da dies zwar ausreichend wäre, der Benutzer jedoch nichts mit '012Veranstaltung',
     * '35Sportart' anfangen kann, wurde jede Veranstaltung und Sportart nach einer Subscription
     * des aktuellen Benutzers gefragt. Somit kann anhand der nun bekannten ID einfach der LeafNode
     * angesprochen werden und den Namen der Sportart/Veranstaltung lesbar ausgegeben werden.
     * Hinweis: Bei sehr vielen Veranstaltungen/Sportarten sollte eventuell später auf die
     * LeafNode Alternative zurückgegriffen werden, da die gesamte XML-Datendatei durchlaufen
     * werden muss. 
     */
    public static void showSubscriptionsList(){  
    	//List<String> subList = csi.showSubscriptions();
    	String sportgruppenId;
    	String sportartId;
    	String veranstaltungId;
    	
    	v.clear();
    //	for(String subscription : subList){
    		for(Sportgruppe sportgruppeKonkret : csi.getSportgruppenMElement().getSportgruppe()){
    			Sportgruppe sg = sportgruppeKonkret;
    			sportgruppenId = sg.getId();
    			
    			SportartenM sm = sg.getSportartenM();
    			
    			for (Sportart sportartKonkret : sm.getSportart()){
    				Sportart s = sportartKonkret;
    				sportartId = s.getId();
    				
    				if(csi.isSubscribed(sportgruppenId+sportartId+"Sportart")){
    					v.add(sm.getSportart().get(Integer.valueOf(sportartId)).getSName());
    				}
    				
    				VeranstaltungenM vm = s.getVeranstaltungenM();
    				
    				for (Veranstaltung veranstaltungKonkret : vm.getVeranstaltung()){
    					Veranstaltung veranst = veranstaltungKonkret;
    					veranstaltungId = veranst.getId();
    					
    					if(csi.isSubscribed(sportgruppenId+sportartId+veranstaltungId+"Veranstaltung")){
    						v.add(vm.getVeranstaltung().get(Integer.valueOf(veranstaltungId)).getVBeschreibung());
    					}
    				}
    			}
    	//	}
    	//	System.out.println(subscription);
    	//	v.add(subscription);
    	}
    	subscriptions.setListData(v);
    	labelSubList.setVisible(true);
    	subscriptions.setVisible(true);    	
    }

    /***************Areas zur Darstellung der Mitteilung***********
     * 
     * Die Areas (unten in der GUI) werden erstellt.
     * Für jeden Tab eine also SG, VS und O
     * 
     * Eventuell noch setEditable(false) setzen? 
     * 
     * TODO: Mitteilungen
     *
     * ****************************************************************/
    public static void showAreaAllgemeinSG(){
    		
    		labelAreaAllgSGV = new JLabel("Meldungen:");
    		labelAreaAllgSGV.setBounds(10, 300, 300, 100);
            panelSG.add(labelAreaAllgSGV);
        	panelSG.validate();
        	panelSG.repaint();
    		
    		AreaAllgemeinSG = new JTextArea(7, 20);
    		AreaAllgemeinSG.setText("");
    		AreaAllgemeinSG.setLineWrap(true);
    		AreaAllgemeinSG.setWrapStyleWord(true);
            scrollpaneAreaAllgSG = new JScrollPane(AreaAllgemeinSG); 
         	scrollpaneAreaAllgSG.setBounds(10, 370, 300, 150);
            panelSG.add(scrollpaneAreaAllgSG);

    		
    	}
    	
    public static void showAreaAllgemeinVS(){
    		
    		labelAreaAllgVS = new JLabel("Meldungen:");
    		labelAreaAllgVS.setBounds(10, 300, 300, 100);
            panelVS.add(labelAreaAllgVS);
        	panelVS.validate();
        	panelVS.repaint();
    		
    		AreaAllgemeinVS = new JTextArea(7, 20);
    		AreaAllgemeinVS.setText("");
    		AreaAllgemeinVS.setLineWrap(true);
    		AreaAllgemeinVS.setWrapStyleWord(true);
            scrollpaneAreaAllgVS = new JScrollPane(AreaAllgemeinVS); 
         	scrollpaneAreaAllgVS.setBounds(10, 370, 400, 150);
            panelVS.add(scrollpaneAreaAllgVS);

    		
    	}
    	
    public static void showAreaAllgemeinO(){
    		
    		labelAreaAllgO = new JLabel("Meldungen:");
    		labelAreaAllgO.setBounds(10, 300, 300, 100);
            panelO.add(labelAreaAllgO);
        	panelO.validate();
        	panelO.repaint();
    		
    		AreaAllgemeinO = new JTextArea(7, 20);
    		AreaAllgemeinO.setText("");
    		AreaAllgemeinO.setLineWrap(true);
    		AreaAllgemeinO.setWrapStyleWord(true);
            scrollpaneAreaAllgO = new JScrollPane(AreaAllgemeinO); 
         	scrollpaneAreaAllgO.setBounds(10, 370, 400, 150);
            panelO.add(scrollpaneAreaAllgO);

    		
    	}
    
    
    
    /********************************************/
	/**********Methoden Veranstalter*************/
    /********************************************/
    
    
    
    public static void showBtnLogoutVV(){
    		
    	btnZurueckVV = new JButton ("Logout");
       	btnZurueckVV.setBounds(800, 500, 150, 25);
       	panelVV.add(btnZurueckVV);
       	panelVV.validate();
       	
       	btnZurueckVV.addActionListener(new ActionListener() {
				
		public void actionPerformed(ActionEvent btnZurueckVVe) {
				
			Object[] options = {"Interessent", "Veranstalter"};
                    
			int selected = JOptionPane.showOptionDialog(null,
                      "Welche Rolle haben Sie?",
                      "Alternativen",
                      JOptionPane.DEFAULT_OPTION, 
                      JOptionPane.INFORMATION_MESSAGE, 
                      null, options, options[0]);
    					
                    if(selected == 0)
                    {
        	            showPanelsI();
        	            showDropdownSG();
        	            showDropdownVS();
        	            showDropdownO();
        	            showAreaO();
        	            showAreaVS();
        	            showButtonZurueckO();
        	            showLogoutSG();
        	            showButtonZurueckVS();
        	            showAreaAllgemeinSG();	
        	            showAreaAllgemeinVS();
        	            showAreaAllgemeinO();

        	             
        	            fenster1.setVisible(true);
        	            fenster2.setVisible(false);
                     } 

                     if(selected == 1){
                       	showPanelsV();
                       	showDropdownVSG();
                       	showBtnLogoutVV();
        	            showAreaAllgPanelV();

                        	
                        fenster2.setVisible(true);
                        fenster1.setVisible(false);
                     }    	
            	}
			});
    	}
    	
   
    
    /***************Button Bestätigen neue Veranstaltung***********
     * 
     * Der Button wird erstellt und positioniert.
     *
     * ****************************************************************/	
    public static void showButtonNewOK(){
    		
    		btnNewOK.setVisible(true);
    		
        	btnNewOK.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent BtnOKe) {
					
					hideFieldsNewV();
					hideLabelsFields();
					showAreaVSG();
					btnAbortNew.setVisible(false);
					XMLGregorianCalendar time = null, date = null;
					
					
					try {
					time = csv.buildXMLTime(dropdownHourE.getSelectedIndex(), 
								dropdownMinuteE.getSelectedIndex());

					
					date = csv.buildXMLDate(Integer.parseInt(dropdownYearE.getSelectedItem().toString()), 
							Integer.parseInt(dropdownMonthE.getSelectedItem().toString()),
							Integer.parseInt(dropdownDayE.getSelectedItem().toString()));
					
					} catch (DatatypeConfigurationException e) {
						System.out.println("Falsche Datum/Uhrzeit Daten!");
					}				
					
					String beschreibung = fieldBeschrE.getText();
					String info = AreaInfoE.getText();
					String niveau = fieldNiveauE.getText();
					String voraussetzungen = fieldVorraussetzungenE.getText();
					String gebaeudeIdRef = "0";
					String veranstalterIdRef = "0";
					
					Veranstaltung newVeranst = csv.buildVeranstaltung(beschreibung,
							info,
							date,
							time,
							niveau,
							voraussetzungen,
							gebaeudeIdRef,
							veranstalterIdRef);
					
					if(csv.postVeranstaltung(String.valueOf(sportgruppenIndex),
							String.valueOf(sportartenIndex), newVeranst))
						try {
							AreaAllgemeinPanelV.getDocument().insertString(0, "Veranstaltung wurde hinzugefügt!\n", null);
						} catch (BadLocationException e) {
							e.printStackTrace();
						}
					hideDropDownVV();

					resetInsertsFieldsE();
				}
			});
    	}

    public static void resetInsertsFieldsE(){
    	fieldBeschrE.setText("");
    	AreaInfoE.setText("");
    	fieldNiveauE.setText("");
    	fieldVorraussetzungenE.setText("");
    	dropdownDayE.setSelectedIndex(0);
    	dropdownMonthE.setSelectedIndex(0);
    	dropdownYearE.setSelectedIndex(0);
    	dropdownHourE.setSelectedIndex(0);
    	dropdownMinuteE.setSelectedIndex(0);
    	dropdownGebäudeE.setSelectedIndex(0);
    }
    /***************Button neue V abbrechen***********
     * Das Erstellen der vVeranstaltung wird abgebrochen
     *
     * ****************************************************************/
    public static void showButtonNewAbort(){
    		
    		btnAbortNew.setVisible(true);
        	
        	
        	btnAbortNew.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent BtnNewAborte) {
					
					hideFieldsNewV();
					hideLabelsFields();
					showAreaVSG();
					btnAbortNew.setVisible(false);
					resetInsertsFieldsE();
					hideDropDownVV();
				}
			});
    	}
    	
    
    /***************Button neue Veranstaltung***********
     * 
     * Der Button wird erstellt und positioniert.
     *Sobald er gedrpckt wird werden die Fields geladen
     * ****************************************************************/
    public static void showButtonNewV(){
    		
        	btnNewV.setVisible(true);
        	
        	btnNewV.addActionListener(new ActionListener() {
				
				
				public void actionPerformed(ActionEvent NewVe) {
					
					countNew++;
					showTextfieldsVErstellen();
					hideDropDownVS();
					showButtonNewOK();
					showButtonNewAbort();
					hideAreaVSG();
					btnEditV.setVisible(false);
					btnDeleteV.setVisible(false);
					hideDropDownVV();

					
				}
			});

        	
    	}

    
    /***************Area zur Darstellung der Werte in der XML***********
     * 
     * Die Areas (rechts in der GUI) werden erstellt.
     * AreaVSG
     * 
     * Eventuell noch setEditable(false) setzen? 
     * 
     * TODO: Areas mit Inhalt befüllen. 
     * Das macht man mit AreaXYZ.setText(t);
     *
     * ****************************************************************/
    public static void showAreaVSG(){
    	
    	labelAreaVSG.setVisible(true);
    	AreaVSG.setVisible(true);

            
    	}
    	
    /**************************DropdownVSG********************
     *Das Dropdown mit den Sportgruppen wird angezeigt.
     *Hierzu wird gleichzeitig das Label erstellt.
     *Sobald eine VSG gewählt wurde, erscheint das DropDownVSA sowie eine Area.
     *TODO: In den String DropdownVSG müssen die richtigen Sportgruppen rein
     *TODO: AreaVSG muss befüllt werden / eventuell aktualisiert? 
     */
    	
    public static void showDropdownVSG(){
    		
    		List<String> sportgruppenListe = csv.getSportgruppen(); 
    	
    		labelVSG = new JLabel("Bitte wählen Sie eine Sportgruppe");
            labelVSG.setBounds(10, 50, 300, 25);
            panelVV.add(labelVSG);
            labelVSG.setVisible(true);
    		
    		final String[] DropDownVSG = sportgruppenListe.toArray(new String[sportgruppenListe.size()]);
        	dropdownVSG = new JComboBox(DropDownVSG);
        	dropdownVSG.setBounds(10, 70, 200, 25);
        	panelVV.add(dropdownVSG);
        	
        	
        	dropdownVSG.addActionListener(new ActionListener()
        	{
        		public void actionPerformed(ActionEvent dropdowVVe) 
        		{ 			
//        				JComboBox item = (JComboBox) dropdownSGe.getSource();
//        				
//        				if (item.getSelectedIndex() != 0 && item.getSelectedIndex() < DropDownSG.length){
        				
        				String info, labelInfo;
        				sportgruppenIndex = dropdownVSG.getSelectedIndex();
        				
        				labelInfo = csv.getSportgruppeElement(String.valueOf(sportgruppenIndex)).getSGName();
        				info = csv.getSportgruppe(String.valueOf(sportgruppenIndex));
        				
        				AreaVSG.setText(info);
        				labelAreaVSG.setText("Sportgruppe: " + labelInfo);
        				
        				dropdownVSA.removeActionListener(DropDownVSAListen);
        				dropdownVSA.setVisible(false);
        				dropdownVSA.removeAllItems();
        				showDropdownVSA();
        				if(btnNewV.isVisible()){
        					
            				hideBtnNewV();

        					
        				}
        				
        				if (countNew > 0){
        					hideFieldsNewV();
        					hideLabelsFields();
        				}
        				
        				hideBtnAbortEdit();
        				hideBtnDelete();
        				hideBtnEdit();
        				
        				
        		}

        	});
    	}
    
    
    /**************************DropdownVSA********************
     *Das Dropdown mit den Sportarten wird angezeigt.
     *Hierzu wird gleichzeitig das Label erstellt.
     *Sobald eine VSA gewählt wurde, erscheint das DropDownVV sowie eine Area.
     *TODO: In den String DropdownVSA müssen die richtigen Sportarten rein
     *TODO: AreaVSG muss befüllt werden / eventuell aktualisiert? 
     */
   public static void createDropdownVSA(){
	   
	   	labelVSA = new JLabel("Bitte wählen Sie eine Sportart!");
   		labelVSA.setBounds(10, 100, 300, 25);
   		labelVSA.setVisible(false);
   		panelVV.add(labelVSA);
   		panelVV.validate();
   		panelVV.repaint();
       
       final String[] DropDownVSA = new String[] {"Sportarten", "Kampfsport", "Rückschlag", "Schnee", "Soooonenschein"};
     	dropdownVSA = new JComboBox(DropDownVSA);
      	dropdownVSA.setBounds(10, 120, 200, 25);
      	dropdownVSA.setVisible(false);
      	panelVV.add(dropdownVSA);
      	panelVV.validate();
   }
    
    public static void showDropdownVSA(){
        	
    		List<String> sportartenListe = csv.getSportarten(String.valueOf(sportgruppenIndex));
    		labelVSA.setVisible(true);
    		
    		for(String sportarten:sportartenListe){
    			dropdownVSA.addItem(sportarten);
    		}
    		dropdownVSA.setVisible(true);
           	dropdownVSA.addActionListener(DropDownVSAListen);
        }
    
    
    /**************************DropdownVV********************
     *Das Dropdown mit den Veranstaltungen wird angezeigt.
     *Hierzu wird gleichzeitig das Label erstellt.
     *Sobald eine VSG gewählt wurde, erscheint das DropDownVSA sowie eine Area.
     *TODO: In den String DropdownVV müssen die richtigen Veranstaltungen rein
     *TODO: AreaVSG muss befüllt werden / eventuell aktualisiert? 
     */
  
    public static void createDropdownVV(){
    	labelVV = new JLabel("Bitte wählen Sie eine Veranstaltung!");
		labelVV.setBounds(10, 150, 300, 25);
		labelVV.setVisible(false);
        panelVV.add(labelVV);
        panelVV.validate();

		
        final String[] DropDownVV = {""};
    	
        dropdownVV = new JComboBox(DropDownVV);
    	dropdownVV.setBounds(10, 170, 200, 25);
    	dropdownVV.setVisible(false);
    	panelVV.add(dropdownVV);
    	panelVV.validate();
    }
    
    public static void showDropdownVV(){
    		
    		List<String> veranstaltungenListe = csv.getVeranstaltungen(String.valueOf(sportgruppenIndex), 
    				String.valueOf(sportartenIndex));

    		labelVV.setVisible(true);
    		
    		for(String veranstaltungen:veranstaltungenListe){
    			dropdownVV.addItem(veranstaltungen);
    		}    		
    		dropdownVV.setVisible(true);        	
        	dropdownVV.addActionListener(DropDownVVListen);
        	
    	}
    	
    
    /*******************Button Ändern einer Veranstaltung*************
     *Der Button erscheint.
     *Bei Klick wird die aktuelle Veranstaltung geladen
     */
    
    
    
    public static void showBtnEdit(){
    		
    		btnEditV.setVisible(true);
        	
        	
        	btnEditV.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent BtnEdite) {
					String beschreibung, info, niveau, voraussetzungen;
					XMLGregorianCalendar date, time;
					
					hideAreaVSG();
					showTextfieldsVAendern();
					
					Veranstaltung v = csi.getVeranstaltungElement(String.valueOf(sportgruppenIndex),
							String.valueOf(sportartenIndex),
							String.valueOf(veranstaltungenIndex));
					
					beschreibung = v.getVBeschreibung();
					info = v.getVInfo();
					niveau = v.getVNiveau();
					voraussetzungen = v.getVVorraussetzungen();
					
					date = v.getVDatum();
					time = v.getVUhrzeit();
					
					fieldBeschrAE.setText(beschreibung);
					AreInfoAE.setText(info);
					fieldNiveauAE.setText(niveau);
					fieldVorraussetzungenAE.setText(voraussetzungen);
					
					dropdownDayAE.setSelectedItem(date.getDay());
					dropdownMonthAE.setSelectedItem(date.getMonth());
					dropdownYearAE.setSelectedItem(date.getYear());
					
					dropdownMinuteAE.setSelectedIndex(time.getMinute());
					dropdownHourAE.setSelectedIndex(time.getHour());
					
					btnEditV.setVisible(false);
					btnDeleteV.setVisible(false);
//					hideBtnAbortEdit();
					showBtnEditOK();
					showBtnAbortEdit();
					
				}
			});
    	}

    /************Button Ändern einer Veranstaltung Abbrechen*************
     *Der Button erscheint.
     */
    public static void showBtnAbortEdit(){
		
		btnAbortEdit.setVisible(true);
    	
    	btnAbortEdit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent BtnAbortEdite) {
				hideFieldsEditV();
				hideLabelsFields();
				hideBtnEditOK();
				hideBtnAbortEdit();
				showAreaVSG();
				showBtnEdit();
				showBtnDelete();
			}
		});
	}
    	
    /*******************Button Löschen einer Veranstaltung*************
     *Der Button erscheint.
     *Bei Klick wird die aktuelle Veranstaltung geladen
     */
    public static void showBtnDelete(){
    		
    		btnDeleteV.setVisible(true);

    		
        	btnDeleteV.addActionListener(new ActionListener() {
    			
    			public void actionPerformed(ActionEvent BtnAbortEdite) {
    				
    				showAreaVSG();
    				if(csv.deleteVeranstaltung(String.valueOf(sportgruppenIndex),
    						String.valueOf(sportartenIndex),
    						String.valueOf(veranstaltungenIndex))){
    					
							try {
								//Neuste Meldungen immer ganz oben.
								AreaAllgemeinPanelV.getDocument().insertString(0, "Veranstaltung wurde gelöscht!\n", null);
							} catch (BadLocationException e) {
								System.out.println("Fehler beim Löschen der Veranstaltung");
								e.printStackTrace();
							}
    				}
    				AreaVSG.setText(csv.getSportart(String.valueOf(sportgruppenIndex), String.valueOf(sportartenIndex)));
    				labelAreaVSG.setText("Sportart: " + csv.getSportartElement(String.valueOf(sportgruppenIndex),
    						String.valueOf(sportartenIndex)).getSName());
    				veranstaltungenIndex = -1;
    				btnEditV.setVisible(false);
					btnDeleteV.setVisible(false);
					hideDropDownVV();
					//TODO: Dropdown-Feld "Veranstaltung" sowie zugehöriges Label ausblenden.
    				
    			}
    		});	
    	} 
    	
    
    /***********Button Ändern einer Veranstaltung Bestätigen*************
     *Der Button erscheint.
     *
     */
    public static void showBtnEditOK(){
    		
    		btnEditOK.setVisible(true);
        	
        	
        	btnEditOK.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent BtnEditOKe) {
				
					hideFieldsEditV();
					hideLabelsFields();
					hideBtnEditOK();
					hideBtnAbortEdit();
					showAreaVSG();				
					showBtnEdit();
					showBtnDelete();
					/*TODO: Veranstaltung aktualisieren*/

					
				}
			});
    	}  
    	
    
    /*******************Labels der Fields einblenden**************/
    public static void showLabelsFields(){
    		
    	
		labelVEBeschr.setVisible(true);
    	labelVEInfo.setVisible(true);
		labelVEDatum.setVisible(true);
		labelVEUhrzeit.setVisible(true);
		labelVENiveau.setVisible(true);
		labelVEVorraussetungen.setVisible(true);
		labelVEGebäude.setVisible(true);
		
    		
    	}
   
    
    /*******************TextFields zur Erstellung einblenden**************/
    public static void showTextfieldsVErstellen(){
    		
        	showLabelsFields();

    		showLabelsFields();
    		fieldBeschrE.setVisible(true);
            scrollpaneAreaInfoE.setVisible(true);
        	dropdownDayE.setVisible(true);
        	dropdownMonthE.setVisible(true);
        	dropdownYearE.setVisible(true);
        	dropdownHourE.setVisible(true);
        	dropdownMinuteE.setVisible(true);
        	fieldNiveauE.setVisible(true);
        	fieldVorraussetzungenE.setVisible(true);
        	dropdownGebäudeE.setVisible(true);
        	
        
    	}
    	
    	
    /**************Meldungen bezüglich Publishen/Subscriben**********
     *Unten links Area
     *TODO: Inhalt füllen
     */
    public static void showAreaAllgPanelV(){

    	labelAreaAllgVV = new JLabel("Meldungen:");
		labelAreaAllgVV.setBounds(10, 300, 300, 100);
		labelAreaAllgVV.setVisible(true);
        panelVV.add(labelAreaAllgVV);
    	panelVV.validate();
    	panelVV.repaint();
		
		AreaAllgemeinPanelV = new JTextArea(7, 20);
		AreaAllgemeinPanelV.setText("");
		AreaAllgemeinPanelV.setVisible(true);
		AreaAllgemeinPanelV.setLineWrap(true);
		AreaAllgemeinPanelV.setWrapStyleWord(true);
		AreaAllgemeinPanelV.setEditable(false);
        scrollpaneAreaAllg = new JScrollPane(AreaAllgemeinPanelV); 
     	scrollpaneAreaAllg.setBounds(10, 370, 400, 150);
        panelVV.add(scrollpaneAreaAllg);
   
    	}
    	
    
    /*******************TextFields zur Änderung einblenden*
     * 
     * TODO: Die entsprechenden Daten müssen eingefpgt werden
     * 
     */
    public static void showTextfieldsVAendern(){
    		
    		showLabelsFields();
    		fieldBeschrAE.setVisible(true);
        	scrollpaneAreaInfoAE.setVisible(true);
            dropdownDayAE.setVisible(true);
        	dropdownMonthAE.setVisible(true);
        	dropdownYearAE.setVisible(true);
        	dropdownHourAE.setVisible(true);
        	dropdownMinuteAE.setVisible(true);
        	fieldNiveauAE.setVisible(true);
        	fieldVorraussetzungenAE.setVisible(true);
        	dropdownGebäudeAE.setVisible(true);
        	
    	}
    	
    public static void hideBtnNewV(){
    		btnNewV.setVisible(false);
    		
    	}
    
    public static void hideDropDownVV(){
		
		dropdownVV.setVisible(false);
		labelVV.setVisible(false);
	}
    
    public static void hideDropDownVS(){
    		
    		dropdownVS.setVisible(false);
    		labelVS.setVisible(false);
    	}
    public static void hideAreaVSG(){
    		
    		AreaVSG.setVisible(false);
    		labelAreaVSG.setVisible(false);
    	}
    public static void hideBtnDelete(){
    		
    		btnDeleteV.setVisible(false);
    		
    	}
    public static void hideBtnEdit(){
    		
    		btnEditV.setVisible(false);
    	}
    public static void hideBtnAbortEdit(){
    		
    		btnAbortEdit.setVisible(false);
    		
    	}
    public static void hideFieldsEditV(){
    		fieldBeschrAE.setVisible(false);
    		AreInfoAE.setVisible(false);
    		dropdownDayAE.setVisible(false);
    		scrollpaneAreaInfoAE.setVisible(false);
    		dropdownMonthAE.setVisible(false);
    		dropdownYearAE.setVisible(false);
    		dropdownHourAE.setVisible(false);
    		dropdownMinuteAE.setVisible(false);
    		dropdownGebäudeAE.setVisible(false);
    		fieldNiveauAE.setVisible(false);
    		fieldVorraussetzungenAE.setVisible(false);
    		
    	}
    public static void hideFieldsNewV(){
    		labelVEBeschr.setVisible(false);
    		fieldBeschrE.setVisible(false);
    		labelVEInfo.setVisible(false);
    		scrollpaneAreaInfoE.setVisible(false);
    		labelVEDatum.setVisible(false);
    		dropdownDayE.setVisible(false);
    		dropdownMonthE.setVisible(false);
    		dropdownYearE.setVisible(false);
    		labelVEUhrzeit.setVisible(false);
    		dropdownHourE.setVisible(false);
    		dropdownMinuteE.setVisible(false);
    		labelVEGebäude.setVisible(false);
    		dropdownGebäudeE.setVisible(false);
    		labelVENiveau.setVisible(false);
    		fieldNiveauE.setVisible(false);
    		labelVEVorraussetungen.setVisible(false);
    		fieldVorraussetzungenE.setVisible(false);
    		btnNewOK.setVisible(false);
    		
    	}
    public static void hideBtnEditOK(){
    		btnEditOK.setVisible(false);
    	}
    public static void hideLabelsFields(){
    		
    		labelVEBeschr.setVisible(false);
    		labelVEInfo.setVisible(false);
        	labelVEDatum.setVisible(false);
    		labelVEUhrzeit.setVisible(false);
        	labelVENiveau.setVisible(false);
        	labelVEVorraussetungen.setVisible(false);
        	labelVEGebäude.setVisible(false);
    		
    	}

    /***************(Un-)Subscribe Equipment und Gebäude***********
    * 
    * Sind nicht augerufen.
    * Eventuell später einfach noch machen? 
    *
    ****************************************************************/
        public static void showButtonSubsribeE(){
        		
            	btnSubscribeE = new JButton("Subscribe");
            	btnSubscribeE.setBounds(600, 400, 150, 25);
            	panelE.add(btnSubscribeSA);
            	panelE.validate();
            	panelE.repaint();
            	
            	btnSubscribeE.addActionListener(new ActionListener() {
    				
    				
    				public void actionPerformed(ActionEvent btnSubEe) {

    					
    				}
    			});

            	
        	}

        public static void showButtonSubsribeG(){
        		
            	btnSubscribeG = new JButton("Subscribe");
            	btnSubscribeG.setBounds(600, 400, 150, 25);
            	panelVV.add(btnSubscribeG);
            	panelVV.validate();
            	panelVV.repaint();
            	
            	btnSubscribeG.addActionListener(new ActionListener() {
    				
    				
    				public void actionPerformed(ActionEvent btnSubGe) {

    					
    				}
    			});

            	
        	}

        public static void showButtonUnsubsribeE(){
        		
            	btnUnsubscribeE = new JButton("Subscribe");
            	btnUnsubscribeE.setBounds(600, 400, 150, 25);
            	panelE.add(btnUnsubscribeE);
            	panelE.validate();
            	panelE.repaint();
            	
            	btnUnsubscribeE.addActionListener(new ActionListener() {
    				
    				
    				public void actionPerformed(ActionEvent btnUnsubEe) {

    					
    				}
    			});

            	
        	}

        public static void showButtonUnsubsribeG(){
        		
            	btnUnsubscribeG = new JButton("Unsubscribe");
            	btnUnsubscribeG.setBounds(600, 400, 150, 25);
            	panelVV.add(btnUnsubscribeG);
            	panelVV.validate();
            	panelVV.repaint();
            	
            	btnUnsubscribeG.addActionListener(new ActionListener() {
    				
    				
    				public void actionPerformed(ActionEvent btnUnsubGe) {

    					
    				}
    			});

            	
        	}
        
        public static void getSubscriptions(){
        	
        }



}