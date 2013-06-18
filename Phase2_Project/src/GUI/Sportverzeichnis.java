package GUI;
import java.awt.Color;
import java.awt.Component;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import xmppService.*;
 
public class Sportverzeichnis 
{
	
	/********************************************/
	/**************Interessent*******************/
	/********************************************/
	private static JLabel labelSG, labelSA, labelV, labelO, labelAreaSG, 
		labelAreaV, labelAreaO, labelVS, labelSubList;
	
	private static JComboBox dropdownSG, dropdownSA, dropdownV, dropdownVS, 
		dropdownO;		
	
	private static JTextArea AreaSG, AreaV, AreaO;
	
	private static JButton btnSubscribeSA, btnUnsubscribeSA, btnUnsubscribeO, 
		btnUnsubscribeV, btnSubscribeV, btnSubscribeVS, btnUnsubscribeVS, 
		btnSubscribeO, btnzurueckSG, btnzurueckVS, btnzurueckO, btnEditOK;  
	
	
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
                
    	/********************************************/
    	/*****************Windows********************/
    	/********************************************/
    	fenster1.setSize(1000, 600);
        fenster1.setLocationRelativeTo(null);
        fenster1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenster1.add(tabLeiste);
                
        fenster2.setSize(1000, 600);
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
	            showSubscriptionsList();
	            showAreaAllgemeinVS();
	            showAreaAllgemeinO(); 
	            fenster1.setVisible(true);
	            fenster2.setVisible(false);
             } 

        	
        	//Veranstalter wurde gewählt
             if(selected == 1){
               	showPanelsV();
               	showDropdownVSG();
               	showBtnLogoutVV();
	            showAreaAllgPanelV();
                fenster2.setVisible(true);
                fenster1.setVisible(false);
             }
            
            //Zwecks Debbuging ist das jetzt hier! 
             labelAreaVSG = new JLabel("Informationen bezüglich: ");
     		labelAreaVSG.setBounds(600, 10, 300, 100);
     		labelAreaVSG.setVisible(false);
             panelVV.add(labelAreaVSG);
         	panelVV.validate();
         	



            AreaVSG = new JTextArea();
            AreaVSG.setLineWrap(true);
            AreaVSG.setBounds(600, 90, 350, 300);
      		AreaVSG.setVisible(false);

     		panelVV.add(AreaVSG);
         	panelVV.validate();
      
			showAreaSG();
			showAreaVSG();

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
     *TODO: In den String DropdownSG müssen die richtigen Sportgruppen rein
     *TODO: AreaSG muss befüllt werden
     ****************************************************************/
    public static void showDropdownSG()
    	{
	    		  
	            labelSG = new JLabel("Bitte wählen Sie eine Sportgruppe!");
	            labelSG.setBounds(10, 50, 300, 25);
	            panelSG.add(labelSG);
	            
	            
	            final String[] DropDownSG = new String[] {"Sportgruppen", "Kampf", "Ball", "Example", "Ejemplo"};
	        	dropdownSG = new JComboBox(DropDownSG);
	        	dropdownSG.setBounds(10, 70, 200, 25);
	        	panelSG.add(dropdownSG);
	        	
	        	
	        	dropdownSG.addActionListener(new ActionListener()
	        	{
	        		public void actionPerformed(ActionEvent dropdownSGe) 
	        		{ 			
//	        				
	        				showDropdownSA();	
	        				showLogoutSG();
	        	            
	        		}
	        	});
            
    	}

    /**************************DropdownSA********************
     *Das Dropdown mit den Sportarten wird angezeigt.
     *Hierzu wird gleichzeitig das Label erstellt.
     *Sobald eine SA gewählt wurde, erscheint das DropDownV sowie eine Area.
     *TODO: In den String DropdownSA müssen die richtigen Sportarten rein
     *TODO: AreaSA muss befüllt werden
     *****************************************************************/	
    public static void showDropdownSA(){
    		
    		labelSA = new JLabel("Bitte wählen Sie eine Sportart!");
    		labelSA.setBounds(10, 100, 300, 25);
            panelSG.add(labelSA);
            panelSG.validate();
            panelSG.repaint();
    		
            final String[] DropDownSA = new String[] {"Sportarten", "Kampfsport", "Rückschlag", "Schnee", "Soooonenschein"};
        	dropdownSA = new JComboBox(DropDownSA);
        	dropdownSA.setBounds(10, 120, 200, 25);
        	panelSG.add(dropdownSA);
        	panelSG.validate();
        	
        	dropdownSA.addActionListener(new ActionListener()
        	{
        		public void actionPerformed(ActionEvent dropdownSAe) 
        		{ 			
    				showDropdownV();
    				showButtonSubsribeSA();
    				/*If (person.isSubscribed){
    				 * 
    				 * 	btnSubscribeSA.setEnabled(false);
    				 * 	btnSubscribeSA.setToolTipText("Node wurde bereits subscribed");
    				 * }
    				 * 
    				 * else {
    				 * 
    				 * btnUnsubscribeSA.setEnable(false);
    				 * 
    				 * }
    				 * 
    				 * 
    				 * */
    				
    				showButtonUnsubsribeSA();
    				scrollpaneAreaAllgSG.setVisible(true);
    				}
   			});
    	}
    

    /**************************DropdownV********************
     * Das Dropdown mit den Veranstaltungen wird angezeigt.
     * Hierzu wird gleichzeitig das Label erstellt.
     * Sobald eine V gewählt wurde, erscheinen die Subscribe und Unsubrice Button sowie eine Area.
     * TODO: In den String DropdownV müssen die richtigen Veranstaltungen rein
     * TODO: AreaV muss befüllt werden
     * ****************************************************************/   	
    public static void showDropdownV(){
    		
    		labelV = new JLabel("Bitte wählen Sie eine Veranstaltung");
    		labelV.setBounds(10, 150, 300, 25);
    		panelSG.add(labelV);
    		panelSG.validate();
    		
            final String[] DropDownV = new String[] {"Veranstaltungen", "Bla", "Beispiel1", "Example", "Ejemplo"};
        	
            dropdownV = new JComboBox(DropDownV);
        	dropdownV.setBounds(10, 170, 200, 25);
        	panelSG.add(dropdownV);
        	panelSG.validate();
        	
        	dropdownV.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent dropdownVe) {
					
					btnSubscribeSA.setVisible(false);
					btnUnsubscribeSA.setVisible(false);
					showButtonSubsribeV();
					btnSubscribeV.setToolTipText("Abonnieren der Veranstaltung");
    				showButtonUnsubsribeV();
    				btnUnsubscribeV.setToolTipText("Entabonnieren der Veranstaltung");
    				
    				/*TODO: Wenn bereits Subsrcibed ist Enabled(false) setzen
    				 * If (person.isSubscribed){
    				 * 
    				 * 	btnSubscribeV.setEnabled(false);
    				 * 	btnSubscribeV.setToolTipText("Node wurde bereits subscribed");
    				 * }
    				 * 
    				 * else {
    				 * 
    				 * btnUnsubscribeV.setEnable(false);
    				 * btnUnsubscribeV.setToolTipText("Nur ein abonnierter Node kann entabonniert werden");
    				 * 
    				 * }
    				 * 
    				 * 
    				 * */
					
				}
			});
    	}

    	
    /**************************DropdownVS********************
     * Das Dropdown mit den Veranstaltern wird angezeigt.
     * Hierzu wird gleichzeitig das Label erstellt.
     * Sobald ein VS gewählt wurde, erscheinen die Subscribe und Unsubrice Button sowie eine Area.
     * TODO: In den String DropdownVS müssen die richtigen Veranstalter rein
     * TODO: AreaVS muss befüllt werden
     * ****************************************************************/ 
    public static void showDropdownVS(){
        	
    		
    		labelVS = new JLabel("Bitte wählen Sie eine(n) Veranstalter/in!");
            labelVS.setBounds(10, 10, 300, 100);
            panelVS.add(labelVS);
            panelVS.validate();
            
        	final String[] DropDownVS = new String[] {"Veranstalter", "David", "Laura", "Super", "Mentor"};
        	dropdownVS = new JComboBox(DropDownVS);
        	dropdownVS.setBounds(10, 70, 200, 25);
        	panelVS.add(dropdownVS);
        	panelVS.validate();
        	
        	dropdownVS.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent Ve) {
					showButtonZurueckVS();
					showButtonSubsribeVS();
					showButtonUnsubsribeVS();
					
					/*TODO: Button ausgrauen
					 * If (person.isSubscribed){
    				 * 
    				 * 	btnSubscribeV.setEnabled(false);
    				 * 	btnSubscribeV.setToolTipText("Node wurde bereits subscribed");
    				 * }
    				 * 
    				 * else {
    				 * 
    				 * btnUnsubscribeV.setEnable(false);
    				 * btnUnsubscribeV.setToolTipText("Node wurde noch nicht abonniert");
    				 * }
    				 * 
    				 * 
    				 * */
					
					
				}
			});
        	
        	
    	}

    /**************************DropdownO********************
     * Das Dropdown mit den Orten wird angezeigt.
     * Hierzu wird gleichzeitig das Label erstellt.
     * Sobald ein O gewählt wurde, erscheinen die Subscribe und Unsubrice Button sowie eine Area.
     * TODO: In den String DropdownO müssen die richtigen Ort rein
     * TODO: AreaO muss befüllt werden
     * ****************************************************************/ 
    public static void showDropdownO(){
    		
    		
            
    		labelO = new JLabel("Bitte wählen Sie einen Ort");
            labelO.setBounds(10, 10, 300, 100);
            panelO.add(labelO);
            labelO.setVisible(true);
            labelO.validate();
    		
    		final String[] DropDownO = new String[] {"Orte", "Bernberg", "Hesselbach", "Niederseßmar", "Wiehl"};
        	dropdownO = new JComboBox(DropDownO);
        	dropdownO.setBounds(10, 70, 200, 25);
        	panelO.add(dropdownO);
        	panelO.validate();
        	
        	
        	dropdownO.addActionListener(new ActionListener()
        	{
        		public void actionPerformed(ActionEvent dropdownOe) 
        		{ 			
        			showButtonZurueckO();
        			showButtonSubsribeO();
        			showButtonUnsubsribeO();
        			
        			/*TODO: Button ausgrauen
					 * If (person.isSubscribed){
    				 * 
    				 * 	btnSubscribeO.setEnabled(false);
    				 * 	btnSubscribeO.setToolTipText("Node wurde bereits subscribed");
    				 * }
    				 * 
    				 * else {
    				 * 
    				 * btnUnsubscribeO.setEnable(false);
    				 * btnUnsubscribeO.setToolTipText("Node wurde noch nicht abonniert");
    				 * }
    				 * 
    				 * 
    				 * */
        		}
        	});
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

    /*********************(Un-)Subscribe Sportart********************
     * 
     * Die Button zum Subscriben und Unsubriben einer 
     * Sportart werden erstellt.
     *
     * ****************************************************************/ 	
    public static void showButtonSubsribeSA(){
    		
        	btnSubscribeSA = new JButton("Subscribe");
        	btnSubscribeSA.setBounds(600, 400, 150, 25);
        	panelSG.add(btnSubscribeSA);
        	panelSG.validate();
        	panelSG.repaint();
        	
        	btnSubscribeSA.addActionListener(new ActionListener() {
				
				
				public void actionPerformed(ActionEvent btnSubSGe) {

					/*TODO: Hier muss was passieren*/
				}
			});

        	
    	}
    	
    public static void showButtonUnsubsribeSA(){
		
    	btnUnsubscribeSA = new JButton("Unsubscribe");
    	btnUnsubscribeSA.setBounds(800, 400, 150, 25);
    	panelSG.add(btnUnsubscribeSA);
    	panelSG.validate();
    	panelSG.repaint();
    	
    	
    	btnUnsubscribeSA.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent btnUnSGe) {

				/*TODO: Hier musss auch was passieren*/
			}
		});

	}	

    /*********************(Un-)Subscribe Veranstaltung********************
     * 
     * Die Button zum Subscriben und Unsubriben einer 
     * Veranstaltung werden erstellt.
     *
     * ****************************************************************/ 
    public static void showButtonSubsribeV(){
    		
        	btnSubscribeV = new JButton("Subscribe");
        	btnSubscribeV.setBounds(600, 400, 150, 25);
        	panelSG.add(btnSubscribeV);
        	panelSG.validate();
        	panelSG.repaint();
        	
        	btnSubscribeV.addActionListener(new ActionListener() {
				
				
				public void actionPerformed(ActionEvent btnSubVe) {

					
				}
			});

        	
    	}
    	
    public static void showButtonUnsubsribeV(){
    		
        	btnUnsubscribeV = new JButton("Unsubscribe");
        	btnUnsubscribeV.setBounds(800, 400, 150, 25);
        	panelSG.add(btnUnsubscribeV);
        	panelSG.validate();
        	panelSG.repaint();

        	
        	btnUnsubscribeV.addActionListener(new ActionListener() {
				
				
				public void actionPerformed(ActionEvent btnUnVe) {

					
				}
			});
    	}
    	
    
    /*********************(Un-)Subscribe Veranstalter********************
     * 
     * Die Button zum Subscriben und Unsubriben eines 
     * Veranstalters werden erstellt.
     *
     * ****************************************************************/ 
    public static void showButtonSubsribeVS(){
		
        btnSubscribeVS = new JButton("Subscribe");
       	btnSubscribeVS.setBounds(600, 400, 150, 25);
       	panelVS.add(btnSubscribeVS);
       	panelVS.validate();
       	panelVS.repaint();
       	
       	btnSubscribeVS.addActionListener(new ActionListener() {
				
				
       	public void actionPerformed(ActionEvent btnSubVSe) {
       		
       			/*TODO: Veranstalter subsriben*/
					
				}
			});

        	
    	}
    
    public static void showButtonUnsubsribeVS(){
		
		btnUnsubscribeVS = new JButton("Unsubscribe");
		btnUnsubscribeVS.setBounds(800, 400, 150, 25);
		btnUnsubscribeVS.setToolTipText("Entabonnieren");
		panelVS.add(btnUnsubscribeVS);
		panelVS.validate();
		panelVS.repaint();
		
		btnUnsubscribeVS.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent btnUnVSe) {

				/*TODO: Unsubscribe Veranstalter*/
				
			}
		});
	}
    
    
    /*********************(Un-)Subscribe Ort****************************
     * 
     * Die Button zum Subscriben und Unsubriben eines 
     * Ortes werden erstellt.
     *
     * ****************************************************************/
    public static void showButtonSubsribeO(){
    		
        	btnSubscribeO = new JButton("Subscribe");
        	btnSubscribeO.setBounds(600, 400, 150, 25);
        	panelO.add(btnSubscribeO);
        	panelO.validate();
        	panelO.repaint();
        	
        	btnSubscribeO.addActionListener(new ActionListener() {
				
				
				public void actionPerformed(ActionEvent btnSubOe) {
					/*TODO: Subscriben Ort*/
					
				}
			});

        	
    	}
       	
    public static void showButtonUnsubsribeO(){
	
    		btnUnsubscribeO = new JButton("Unsubscribe");
    		btnUnsubscribeO.setBounds(800, 400, 150, 25);
    		panelO.add(btnUnsubscribeO);
    		panelO.validate();
    		panelO.repaint();
    		
    		btnUnsubscribeO.addActionListener(new ActionListener() {
				
				
				public void actionPerformed(ActionEvent btnUnOe) {
					/*TODO: Unsubscribe Ort!*/
					
				}
			});
    	}
    	
    
    /***************Areas zur Darstellung der Werte in der XML***********
     * 
     * Die Areas (rechts in der GUI) werden erstellt.
     * AreaSG, AreaVS und AreaO
     * 
     * Eventuell noch setEditable(false) setzen? 
     * 
     * TODO: Areas mit Inhalt befüllen. 
     * Das macht man mit AreaXYZ.setText(t);
     *
     * ****************************************************************/
    public static void showAreaSG(){
    		
    		labelAreaSG = new JLabel("Informationen bezüglich: ");
            labelAreaSG.setBounds(600, 10, 300, 100);
            panelSG.add(labelAreaSG);
        	panelSG.validate();
        	panelSG.repaint();



            AreaSG = new JTextArea();
            AreaSG.setLineWrap(true);
    		AreaSG.setBounds(600, 90, 350, 300);
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
    	
    
    public static void showSubscriptionsList(){
    	
    	labelSubList = new JLabel("Your Subscriptions:");
    	labelSubList.setBounds(350, 300, 300, 100);
        panelSG.add(labelSubList);
    	panelSG.validate();
    	panelSG.repaint();
    	
        String SubsribedItems[] = {"Politik", "Autos", "Mode", 
            "Film- und Fernsehen", "Computer", "Tiere", "Sport"};
        JList Subscriptions = new JList(SubsribedItems);
        Subscriptions.setBounds(350, 370, 200, 150);
        panelSG.add(Subscriptions);
    	
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
    		
    		btnNewOK = new JButton("OK");
    		btnNewOK.setBounds(800, 450, 150, 25);
    		panelVV.add(btnNewOK);
    		panelVV.validate();
        	panelVV.repaint();
        	
        	
        	btnNewOK.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent BtnOKe) {
					
					hideFieldsNewV();
					hideLabelsFields();
					showAreaVSG();
					btnAbortNew.setVisible(false);
					/*TODO: Publishen - Ok Button übernimmt eingegebenen Werte und macht nen Post*/
					
				}
			});
    	}

    
    /***************Button neue V abbrechen***********
     * Das Erstellen der vVeranstaltung wird abgebrochen
     *
     * ****************************************************************/
    public static void showButtonNewAbort(){
    		
    		btnAbortNew = new JButton("Abbrechen");
    		btnAbortNew.setBounds(650, 450, 150, 25);
    		panelVV.add(btnAbortNew);
    		panelVV.validate();
        	panelVV.repaint();
        	
        	
        	btnAbortNew.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent BtnNewAborte) {
					
					hideFieldsNewV();
					hideLabelsFields();
					showAreaVSG();
					btnAbortNew.setVisible(false);
					
					
				}
			});
    	}
    	
    
    /***************Button neue Veranstaltung***********
     * 
     * Der Button wird erstellt und positioniert.
     *Sobald er gedrpckt wird werden die Fields geladen
     * ****************************************************************/
    public static void showButtonNewV(){
    		
        	btnNewV = new JButton("Neue Veranstaltung");
        	btnNewV.setBounds(220, 120, 150, 25);
        	panelVV.add(btnNewV);
        	panelVV.validate();
        	panelVV.repaint();
        	
        	btnNewV.addActionListener(new ActionListener() {
				
				
				public void actionPerformed(ActionEvent NewVe) {
					
					countNew++;
					showTextfieldsVErstellen();
					hideDropDownVS();
					showButtonNewOK();
					showButtonNewAbort();
					hideAreaVSG();
					
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
    		
    	
    		labelVSG = new JLabel("Bitte wählen Sie eine Sportgruppe");
            labelVSG.setBounds(10, 50, 300, 25);
            panelVV.add(labelVSG);
            labelVSG.setVisible(true);
            labelVSG.validate();
    		
    		final String[] DropDownVSG = new String[] {"Judo", "Kampf", "Pi", "Pa", "Po"};
        	dropdownVSG = new JComboBox(DropDownVSG);
        	dropdownVSG.setBounds(10, 70, 200, 25);
        	panelVV.add(dropdownVSG);
        	panelVV.validate();
        	
        	
        	dropdownVSG.addActionListener(new ActionListener()
        	{
        		public void actionPerformed(ActionEvent dropdowVVe) 
        		{ 			
//        				JComboBox item = (JComboBox) dropdownSGe.getSource();
//        				
//        				if (item.getSelectedIndex() != 0 && item.getSelectedIndex() < DropDownSG.length){
        				
        			
        				showDropdownVSA();
        				
        				if (countNew > 0){
        					hideFieldsNewV();
        					hideLabelsFields();
        				}
        				
        				if(countSA > 0){
        					hideAreaVSG();
        				}
        				
        				if(countVV >0){
        					hideAreaVSG();
        				}
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
    public static void showDropdownVSA(){
        	
    		labelVSA = new JLabel("Bitte wählen Sie eine Sportart!");
        	labelVSA.setBounds(10, 100, 300, 25);
            panelVV.add(labelVSA);
            panelVV.validate();
            panelVV.repaint();
            
            final String[] DropDownVSA = new String[] {"Sportarten", "Kampfsport", "Rückschlag", "Schnee", "Soooonenschein"};
          	dropdownVSA = new JComboBox(DropDownVSA);
           	dropdownVSA.setBounds(10, 120, 200, 25);
           	panelVV.add(dropdownVSA);
           	panelVV.validate();
           	
           	dropdownVSA.addActionListener(new ActionListener(){
           		
           		public void actionPerformed(ActionEvent dropdownSAe) { 			
           			
           			
           			showButtonNewV();
           			showDropdownVV();
           			if (countVV >0){
           				hideAreaVSG();
           				btnDeleteV.setVisible(false);
           				btnEditV.setVisible(false);
           			}
           			
           			if (countNew > 0){
    					hideFieldsNewV();
    					hideLabelsFields();
    				}
           			
           			for (int i = 0; i < countSA; i++) {
           				hideAreaVSG();

					}
           			}
           			
           		
       		});
        }
    
    
    /**************************DropdownVV********************
     *Das Dropdown mit den Veranstaltungen wird angezeigt.
     *Hierzu wird gleichzeitig das Label erstellt.
     *Sobald eine VSG gewählt wurde, erscheint das DropDownVSA sowie eine Area.
     *TODO: In den String DropdownVV müssen die richtigen Veranstaltungen rein
     *TODO: AreaVSG muss befüllt werden / eventuell aktualisiert? 
     */
    public static void showDropdownVV(){
    		
    		labelVV = new JLabel("Bitte wählen Sie eine Veranstaltung!");
    		labelVV.setBounds(10, 150, 300, 25);
            panelVV.add(labelVV);
            panelVV.validate();

    		
            String[] DropDownVV = new String[] {"Veranstaltungen", "Bla", "Beispiel1", "Example", "Ejemplo"};
        	
            dropdownVV = new JComboBox(DropDownVV);
        	dropdownVV.setBounds(10, 170, 200, 25);
        	panelVV.add(dropdownVV);
        	panelVV.validate();
        	
        	dropdownVV.addActionListener(new ActionListener(){
           		
           		public void actionPerformed(ActionEvent dropdownVVe) { 			
           			
           			showBtnDelete();
           			showBtnEdit();
           			
           			countVV++;
           			
           			if (countNew > 0){
           				hideFieldsNewV();
           				hideLabelsFields();
           			}
           		}
       		});
        	
    	}
    	
    
    /*******************Button Ändern einer Veranstaltung*************
     *Der Button erscheint.
     *Bei Klick wird die aktuelle Veranstaltung geladen
     */
    public static void showBtnEdit(){
    		
    		
        	btnEditV = new JButton("Ändern");
    		btnEditV.setBounds( 600, 450, 150, 25);
        	panelVV.add(btnEditV);
        	panelVV.validate();
        	panelVV.repaint();
        	
        	btnEditV.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent BtnEdite) {
					hideAreaVSG();
					showTextfieldsVAendern();
					/*TODO: Hier muss dann die gewählte Veranstaltung rein*/
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
		
		btnAbortEdit = new JButton("Abbrechen");
		btnAbortEdit.setBounds( 650, 450, 150, 25);
    	panelVV.add(btnAbortEdit);
    	panelVV.validate();
    	panelVV.repaint();
    	
    	btnAbortEdit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent BtnAbortEdite) {
				hideFieldsEditV();
				hideLabelsFields();
				hideBtnEditOK();
				hideBtnAbortEdit();
				showAreaVSG();
			}
		});
	}
    	
    /*******************Button Löschen einer Veranstaltung*************
     *Der Button erscheint.
     *Bei Klick wird die aktuelle Veranstaltung geladen
     */
    public static void showBtnDelete(){
    		
    		btnDeleteV = new JButton("Löschen");
    		btnDeleteV.setBounds(800, 450, 150, 25);
        	panelVV.add(btnDeleteV);
        	panelVV.validate();
        	panelVV.repaint();
    	} 
    	
    
    /***********Button Ändern einer Veranstaltung Bestätigen*************
     *Der Button erscheint.
     *
     */
    public static void showBtnEditOK(){
    		
    		btnEditOK = new JButton("Übernehmen");
    		btnEditOK.setBounds(800, 450, 150, 25);
        	panelVV.add(btnEditOK);
        	panelVV.validate();
        	panelVV.repaint();
        	
        	
        	btnEditOK.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent BtnEditOKe) {
					hideFieldsEditV();
					hideLabelsFields();
					hideBtnEditOK();
					hideBtnAbortEdit();
					showAreaVSG();
					/*TODO: Veranstaltung aktualisieren*/
					
				}
			});
    	}  
    	
    
    /*******************Labels der Fields einblenden**************/
    public static void showLabelsFields(){
    		
    		labelVEBeschr = new JLabel("Name/Beschreibung:");
    		labelVEBeschr.setBounds(650, 60, 200, 25);
    		labelVEBeschr.setVisible(true);
    		panelVV.add(labelVEBeschr);
    		panelVV.validate();
    		
    		labelVEInfo = new JLabel("Informationen:");
        	labelVEInfo.setBounds(650, 110, 200, 25);
        	labelVEInfo.setVisible(true);
    		panelVV.add(labelVEInfo);
    		panelVV.validate();
    		
        	labelVEDatum = new JLabel("Datum:");
        	labelVEDatum.setBounds(650, 185, 200, 25);
        	labelVEDatum.setVisible(true);
    		panelVV.add(labelVEDatum);
    		panelVV.validate();
    		
    		labelVEUhrzeit = new JLabel("Uhrzeit:");
        	labelVEUhrzeit.setBounds(650, 225, 200, 25);
        	labelVEUhrzeit.setVisible(true);
    		panelVV.add(labelVEUhrzeit);
    		panelVV.validate();

        	labelVENiveau = new JLabel("Niveau:");
        	labelVENiveau.setBounds(650, 270, 200, 25);
        	labelVENiveau.setVisible(true);
    		panelVV.add(labelVENiveau);
    		panelVV.validate();

        	labelVEVorraussetungen = new JLabel("Vorraussetzungen:");
        	labelVEVorraussetungen.setBounds(650, 320, 200, 25);
        	labelVEVorraussetungen.setVisible(true);
    		panelVV.add(labelVEVorraussetungen);
    		panelVV.validate();

        	labelVEGebäude = new JLabel("Gebäude:");
        	labelVEGebäude.setBounds(650, 370, 200, 25);
        	labelVEGebäude.setVisible(true);
    		panelVV.add(labelVEGebäude);
    		panelVV.validate();
    		
    	}
   
    
    /*******************TextFields zur Erstellung einblenden**************/
    public static void showTextfieldsVErstellen(){
    		
//    		Color colorGrey = new Color(152,153,155);
        	showLabelsFields();
    		fieldBeschrE = new JTextField("");
    		fieldBeschrE.setToolTipText("Bitte tragen Sie hier einen Namen und eine Beschreibung der Veranstaltung ein");
    		fieldBeschrE.setBounds(650, 80, 300, 25);
        	panelVV.add(fieldBeschrE);
        	panelVV.validate();
    		
        	AreaInfoE = new JTextArea(5, 20);
        	AreaInfoE.setText("Lorem ipsum dolor sit amet, " +
            		"consetetur sadipscing elitr, sed diam nonumy " +
            		"eirmod tempor invidunt ut labore et " +
            		"dolore magna aliquyam erat, sed diam voluptua. " +
            		"At vero eos et accusam et justo duo dolores et " +
                            "ea rebum.");
        	 AreaInfoE.setLineWrap(true);
        	 AreaInfoE.setWrapStyleWord(true);
             scrollpaneAreaInfoE = new JScrollPane(AreaInfoE); 
          	 scrollpaneAreaInfoE.setBounds(650, 130, 300, 50);
             panelVV.add(scrollpaneAreaInfoE);
             scrollpaneAreaInfoE.setVisible(true);
             
        	final String[] DropDownDay = new String[] {"1", "2","3","4","5","6","7","8", "9","10","11","12","13", "14","15","16","17","18","19","20","21","22","23", "24","25","26","27","28","29","30","31"};
        	dropdownDayE = new JComboBox(DropDownDay);
        	dropdownDayE.setBounds(650, 200, 60, 30);
        	panelVV.add(dropdownDayE);
        	panelVV.validate();
        	
        	final String[] DropDownMonth = new String[] {"Jan", "Feb", "März", "April", "Mai", "Juni","Juli","Aug","Sep","Okt","Nov","Dez"};
        	dropdownMonthE = new JComboBox(DropDownMonth);
        	dropdownMonthE.setBounds(720, 200, 80, 30);
        	panelVV.add(dropdownMonthE);
        	panelVV.validate();
        	
        	
        	final String[] DropDownYear = new String[] {"2013", "2014", "2015", "2016"};
        	dropdownYearE = new JComboBox(DropDownYear);
        	dropdownYearE.setBounds(820, 200, 100, 30);
        	panelVV.add(dropdownYearE);
        	panelVV.validate();

        	final String[] DropDownHour = new String[] {"08", "09", "10", "11", "12", "13","14","15","16","17","18","19","20","21", "22"};
        	dropdownHourE = new JComboBox(DropDownHour);
        	dropdownHourE.setBounds(650, 240, 80, 30);
        	panelVV.add(dropdownHourE);
        	panelVV.validate();
        	
        	
        	final String[] DropDownMinute = new String[] {"00","01", "02","03","04","05","06","07","08", "09","10","11","12","13", "14","15","16","17","18","19","20","21","22","23", "24","25","26","27","28","29","30","31", "32","33","34","35","36","37","38", "39","40","41","42","43", "44","45","46","47","48","49","50","51","52","53", "54","55","56","57","58","59"};
        	dropdownMinuteE = new JComboBox(DropDownMinute);
        	dropdownMinuteE.setBounds(740, 240, 80, 30);
        	panelVV.add(dropdownMinuteE);
        	panelVV.validate();
        	
        	
        	fieldNiveauE = new JTextField("");
        	fieldNiveauE.setBounds(650, 290, 300, 25);
        	fieldNiveauE.setToolTipText("Bitte geben Sie hier das Niveau der Veranstaltung ein!");
        	panelVV.add(fieldNiveauE);
        	panelVV.validate();
        	
        	
        	fieldVorraussetzungenE = new JTextField("");
        	fieldVorraussetzungenE.setBounds(650, 340, 300, 25);
        	fieldVorraussetzungenE.setToolTipText("Bitte geben Sie hier das Niveau der Veranstaltung ein!");
        	panelVV.add(fieldVorraussetzungenE);
        	panelVV.validate();
        	
        	final String[] DropDownGebäude = new String[] {"G1", "G2", "10", "11", "12", "13"};
        	dropdownGebäudeE = new JComboBox(DropDownGebäude);
        	dropdownGebäudeE.setBounds(650, 390, 300, 30);
        	panelVV.add(dropdownGebäudeE);
        	panelVV.validate();
        
    	}
    	
    	
    /**************Meldungen bezüglich Publishen/Subscriben**********
     *Unten links Area
     *TODO: Inhalt füllen
     */
    public static void showAreaAllgPanelV(){
    		
    		labelAreaAllgVV = new JLabel("Meldungen:");
    		labelAreaAllgVV.setBounds(10, 300, 300, 100);
            panelVV.add(labelAreaAllgVV);
        	panelVV.validate();
        	panelVV.repaint();
    		
    		AreaAllgemeinPanelV = new JTextArea(7, 20);
    		AreaAllgemeinPanelV.setText("");
    		AreaAllgemeinPanelV.setLineWrap(true);
    		AreaAllgemeinPanelV.setWrapStyleWord(true);
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
    		
    		fieldBeschrAE = new JTextField("");
    		fieldBeschrAE.setBounds(650, 80, 300, 25);
        	panelVV.add(fieldBeschrAE);
        	panelVV.validate();
        	
    		
        	AreInfoAE = new JTextArea(5, 20);
         	AreInfoAE.setText("Lorem ipsum dolor sit amet, " +
           		"consetetur sadipscing elitr, sed diam nonumy " +
           		"eirmod tempor invidunt ut labore et " +
           		"dolore magna aliquyam erat, sed diam voluptua. " +
           		"At vero eos et accusam et justo duo dolores et " +
                           "ea rebum.");
         	AreInfoAE.setLineWrap(true);
         	AreInfoAE.setWrapStyleWord(true);
         	scrollpaneAreaInfoAE = new JScrollPane(AreInfoAE); 
         	scrollpaneAreaInfoAE.setBounds(650, 130, 300, 50);
            panelVV.add(scrollpaneAreaInfoAE);
        	
    		
        	final String[] DropDownDayAE = new String[] {"0","1", "2","3","4","5","6","7","8", "9","10","11","12","13", "14","15","16","17","18","19","20","21","22","23", "24","25","26","27","28","29","30","31"};
        	dropdownDayAE = new JComboBox(DropDownDayAE);
        	dropdownDayAE.setBounds(650, 200, 60, 30);
        	panelVV.add(dropdownDayAE);
        	panelVV.validate();
        	
        	final String[] DropDownMonthAE = new String[] {"Jan", "Feb", "März", "April", "Mai", "Juni","Juli","Aug","Sep","Okt","Nov","Dez"};
        	dropdownMonthAE = new JComboBox(DropDownMonthAE);
        	dropdownMonthAE.setBounds(720, 200, 80, 30);
        	panelVV.add(dropdownMonthAE);
        	panelVV.validate();
        	
        	
        	final String[] DropDownYearAE = new String[] {"2013", "2014", "2015", "2016"};
        	dropdownYearAE = new JComboBox(DropDownYearAE);
        	dropdownYearAE.setBounds(820, 200, 100, 30);
        	panelVV.add(dropdownYearAE);
        	panelVV.validate();

        	
        	final String[] DropDownHourAE = new String[] {"08", "09", "10", "11", "12", "13","14","15","16","17","18","19","20","21", "22"};
        	dropdownHourAE = new JComboBox(DropDownHourAE);
        	dropdownHourAE.setBounds(650, 240, 80, 30);
        	panelVV.add(dropdownHourAE);
        	panelVV.validate();
        	
        	
        	final String[] DropDownMinuteAE = new String[] {"00","01", "02","03","04","05","06","07","08", "09","10","11","12","13", "14","15","16","17","18","19","20","21","22","23", "24","25","26","27","28","29","30","31", "32","33","34","35","36","37","38", "39","40","41","42","43", "44","45","46","47","48","49","50","51","52","53", "54","55","56","57","58","59"};
        	dropdownMinuteAE = new JComboBox(DropDownMinuteAE);
        	dropdownMinuteAE.setBounds(740, 240, 80, 30);
        	panelVV.add(dropdownMinuteAE);
        	panelVV.validate();
        	
        	
        	fieldNiveauAE = new JTextField("");
        	fieldNiveauAE.setBounds(650, 290, 300, 25);
        	panelVV.add(fieldNiveauAE);
        	panelVV.validate();
        	
        	
        	fieldVorraussetzungenAE = new JTextField("");
        	fieldVorraussetzungenAE.setBounds(650, 340, 300, 25);
        	panelVV.add(fieldVorraussetzungenAE);
        	panelVV.validate();
        	
        	
        	
        	final String[] DropDownGebäudeAE = new String[] {"G1", "G2", "10", "11", "12", "13"};
        	dropdownGebäudeAE = new JComboBox(DropDownGebäudeAE);
        	dropdownGebäudeAE.setBounds(650, 390, 300, 30);
        	panelVV.add(dropdownGebäudeAE);
        	panelVV.validate();
    	}
    	
    public static void hideBtnNewV(){
    		btnNewV.setVisible(false);
    		
    	}
    public static void hideDropDownVS(){
    		
    		dropdownVV.setVisible(false);
    		labelVV.setVisible(false);
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



}