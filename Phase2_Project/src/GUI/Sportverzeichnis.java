package GUI;
import java.awt.Color;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import xmppService.*;
 
public class Sportverzeichnis 
{
	
		
	
		private static JLabel labelSG, labelSA, labelV, labelO, labelAreaSG, labelAreaV, labelAreaO, labelVS;
		private static JComboBox dropdownSG, dropdownSA, dropdownV, dropdownVS, dropdownO;
		private static JTextArea AreaSG, AreaV, AreaO;
		
		//Button Interessenten
		private static JButton btnSubscribeSG, btnUnsubscribeSG, btnUnsubscribeO, btnUnsubscribeV, btnSubscribeV, btnSubscribeO, btnzurueckSG, btnzurueckV, btnzurueckO, btnEditOK;  
		//Button Veranstalter
		private static JButton btnDeleteV, btnEditV, btnSubscribeE, btnSubscribeG, btnUnsubscribeE, btnUnsubscribeG, btnZurueckVV, btnNewV, btnOK, btnAbortEdit, btnAbortNew;
		private static JLabel labelVSG, labelAreaVV, labelAreaVSG, labelAreaVSA, labelVSA, labelVV, labelVEBeschr, labelVEInfo, labelVEDatum, labelVEUhrzeit, labelVENiveau, labelVEVorraussetungen, labelVEGebäude;
		private static JComboBox dropdownVSG, dropdownVSA, dropdownVV, dropdownDayE, dropdownMonthE, dropdownYearE, dropdownHourE, dropdownMinuteE, dropdownGebäudeE, 
			dropdownDayAE, dropdownMonthAE, dropdownYearAE, dropdownHourAE, dropdownMinuteAE, dropdownGebäudeAE;
		private static JTextArea AreaVV, AreaVSG, AreaVSA;
		private static JTextField fieldBeschrE,fieldInfoE, fieldDatumE, fieldUhrzeitE, fieldNiveauE, fieldVorraussetzungenE;
		private static JTextField fieldBeschrAE,fieldInfoAE, fieldDatumAE, fieldUhrzeitAE, fieldNiveauAE, fieldVorraussetzungenAE;
		static int countVV = 0;
		static int countNew = 0;
		static int countSA = 0;
		
		final static JFrame fenster1 = new JFrame("Herzlich Willkommen");
		final static JFrame fenster2 = new JFrame("Herzlich Willkommen");
        final static JTabbedPane tabLeiste = new JTabbedPane();
        final static JTabbedPane tabLeiste2 = new JTabbedPane();
        static JPanel PanelMain = new JPanel ();
		static JPanel panelSG = new JPanel();
		static JPanel panelV = new JPanel();
		static JPanel panelO = new JPanel();
		static JPanel panelE = new JPanel();
		static JPanel panelGB = new JPanel();
		static JPanel panelVV = new JPanel();
		
        public static void main(String[] args) {
                
                fenster1.setSize(1000, 600);
                fenster1.setLocationRelativeTo(null);
                fenster1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                fenster1.add(tabLeiste);
            	fenster2.add(tabLeiste2);

                
                fenster2.setSize(1000, 600);
                fenster2.setLocationRelativeTo(null);
                fenster2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                Icon icon = new ImageIcon( "/Ausarbeitungen/icon.png" );
                Object[] options = {"Interessent", "Veranstalter"};
                
//                int selected = JOptionPane.showOptionDialog(null,
//                  "Welche Rolle haben Sie?",
//                  "Alternativen",
//                  JOptionPane.DEFAULT_OPTION, 
//                  JOptionPane.INFORMATION_MESSAGE, 
//                  null, icon, options, options[0]);
                
                int selected = JOptionPane.showOptionDialog(
                		null, "Welche Rolle haben Sie?", "Alternativen", JOptionPane.DEFAULT_OPTION, 
                		JOptionPane.INFORMATION_MESSAGE, icon, options, options[0]);
//                int selected = JOptionPane.showOptionDialog(null,
//                        "Welche Rolle haben Sie?",
//                        "Alternativen",
//                        JOptionPane.DEFAULT_OPTION, 
//                        JOptionPane.INFORMATION_MESSAGE, 
//                        null, options, options[0], icon);
                
                //Also wurde Interessent gewählt!
                if(selected == 0)
                {
                	showPanelsI();
                	showDropdownSG();
                	showDropdownVS();
                	showDropdownO();
                	showAreaO();
                	showAreaV();
                	showButtonZurueckO();
                	showButtonZurueckSG();
                	showButtonZurueckV();
//                	I.interessentErzeugen(interessent);
             
                    
                    fenster1.setVisible(true);
                	fenster2.setVisible(false);
                } 

                if(selected == 1)
                {
                	showPanelsV();
                	showDropdownVSG();
                	showButtonZurueckVV();
                	
                	
                    fenster2.setVisible(true);
                    fenster1.setVisible(false);
                }
                
                             
                
                
        }
 
    
    	
    	public static void showPanelsI(){
    		
	    	/***************************************************************/
	    	/**************************PANEL********************************/
	    	/***************************************************************/	
	        
    		PanelMain.setLayout(null);
    		
	        panelSG.setLayout(null);	
	        tabLeiste.addTab("Sportgruppen", panelSG);
	            
	        
	        panelV.setLayout(null);
	        tabLeiste.addTab("Veranstalter", panelV);
	
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
    	
    	public static void showDropdownSG()
    	{
	    		  
	            labelSG = new JLabel("Bitte wählen Sie eine Sportgruppe!");
	            labelSG.setBounds(10, 10, 300, 100);
	            panelSG.add(labelSG);
	            
	            
	            final String[] DropDownSG = new String[] {"Sportgruppen", "Kampf", "Ball", "Example", "Ejemplo"};
	        	dropdownSG = new JComboBox(DropDownSG);
	        	dropdownSG.setBounds(10, 40, 200, 100);
	        	panelSG.add(dropdownSG);
	        	
	        	
	        	dropdownSG.addActionListener(new ActionListener()
	        	{
	        		public void actionPerformed(ActionEvent dropdownSGe) 
	        		{ 			
//	        				JComboBox item = (JComboBox) dropdownSGe.getSource();
//	        				
//	        				if (item.getSelectedIndex() != 0 && item.getSelectedIndex() < DropDownSG.length){
	        				showDropdownSA();	
	        				showAreaSG();
	        				showButtonZurueckSG();
	        				showButtonSubsribeSG();
	        				showButtonUnsubsribeSG();
	        				
	        				
	        					
	        		}
	        	});
            
    	}

    	public static void showDropdownSA(){
    		
    		labelSA = new JLabel("Bitte wählen Sie eine Sportart!");
    		labelSA.setBounds(10, 70, 300, 100);
            panelSG.add(labelSA);
            panelSG.validate();
    		
            final String[] DropDownSA = new String[] {"Sportarten", "Kampfsport", "Rückschlag", "Schnee", "Soooonenschein"};
        	dropdownSA = new JComboBox(DropDownSA);
        	dropdownSA.setBounds(10, 90, 200, 100);
        	panelSG.add(dropdownSA);
        	panelSG.validate();
        	
        	dropdownSA.addActionListener(new ActionListener()
        	{
        		public void actionPerformed(ActionEvent dropdownSAe) 
        		{ 			
    				showDropdownV();
        		}
   			});
    	}
    	
    	public static void showDropdownV(){
    		
    		labelV = new JLabel("Bitte wählen Sie eine Veranstaltung!");
    		labelV.setBounds(10, 120, 300, 100);
            panelSG.add(labelV);
            panelSG.validate();

    		
            String[] DropDownV = new String[] {"Veranstaltungen", "Bla", "Beispiel1", "Example", "Ejemplo"};
        	
            dropdownV = new JComboBox(DropDownV);
        	dropdownV.setBounds(10, 140, 200, 100);
        	panelSG.add(dropdownV);
        	panelSG.validate();
    	}

    	public static void showDropdownVS(){
        	
    		labelVS = new JLabel("Bitte wählen Sie eine(n) Veranstalter/in!");
            labelVS.setBounds(10, 10, 300, 100);
            panelV.add(labelVS);
            panelV.validate();
            
        	final String[] DropDownVS = new String[] {"Veranstalter", "David", "Laura", "Super", "Mentor"};
        	dropdownVS = new JComboBox(DropDownVS);
        	dropdownVS.setBounds(10, 40, 200, 100);
        	panelV.add(dropdownVS);
        	panelV.validate();
        	
        	dropdownVS.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent Ve) {
					showButtonZurueckV();
					showButtonSubsribeV();
					showButtonUnsubsribeV();
				}
			});
        	
        	
    	}

    	public static void showDropdownO(){
    		
    		
            
    		labelO = new JLabel("Bitte wählen Sie einen Ort");
            labelO.setBounds(10, 10, 300, 100);
            panelO.add(labelO);
            labelO.setVisible(true);
            labelO.validate();
    		
    		final String[] DropDownO = new String[] {"Orte", "Bernberg", "Hesselbach", "Niederseßmar", "Wiehl"};
        	dropdownO = new JComboBox(DropDownO);
        	dropdownO.setBounds(10, 40, 200, 100);
        	panelO.add(dropdownO);
        	panelO.validate();
        	
        	
        	dropdownO.addActionListener(new ActionListener()
        	{
        		public void actionPerformed(ActionEvent dropdownOe) 
        		{ 			
//        				JComboBox item = (JComboBox) dropdownSGe.getSource();
//        				
//        				if (item.getSelectedIndex() != 0 && item.getSelectedIndex() < DropDownSG.length){
        				showButtonZurueckO();
        				showButtonSubsribeO();
        				showButtonUnsubsribeO();
        		}
        	});
    	}

    	public static void showButtonZurueckSG(){
    		
    		btnzurueckSG = new JButton ("Zurück");
        	btnzurueckSG.setBounds(800, 500, 150, 25);
        	panelSG.add(btnzurueckSG);
        	panelSG.validate();

        	
        	
        	btnzurueckSG.addActionListener(new ActionListener() {
				
				
            	public void actionPerformed(ActionEvent btnzuruecke) {
				            	
            	Object[] options = {"Interessent", "Veranstalter"};
                
                int selected = JOptionPane.showOptionDialog(null,
                  "Welche Rolle haben Sie?",
                  "Alternativen",
                  JOptionPane.DEFAULT_OPTION, 
                  JOptionPane.INFORMATION_MESSAGE, 
                  null, options, options[0]);
					
	                if(selected == 0)
	                {
	                	fenster1.repaint();
	                	fenster1.validate();
	                	showPanelsI();
	                	showDropdownSG();
	                	showDropdownVS();
	                	showDropdownO();
	                	showAreaO();
	                	showAreaV();
	                	showButtonZurueckO();
	                	showButtonZurueckSG();
	                	showButtonZurueckV();
	             
	                	fenster1.setVisible(true);
	                	fenster2.setVisible(false);
	                } 

	                if(selected == 1)
	                {
	                	showPanelsV();
	                	showDropdownVSG();
	                	showButtonZurueckVV();
	                	
	                	
	                    fenster2.setVisible(true);
	                    fenster1.setVisible(false);
	                }
	                	
	                }

				
			});
    	}
    	
    	public static void showButtonZurueckV(){
    		
    		btnzurueckV = new JButton ("Zurück");
        	btnzurueckV.setBounds(800, 500, 150, 25);
        	panelV.add(btnzurueckV);
        	panelV.validate();

        	
        	
        	btnzurueckV.addActionListener(new ActionListener() {
				
				
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
	                	fenster1.repaint();
	                	fenster1.validate();
	                	showPanelsI();
	                	showDropdownSG();
	                	showDropdownVS();
	                	showDropdownO();
	                	showAreaO();
	                	showAreaV();
	                	showButtonZurueckO();
	                	showButtonZurueckSG();
	                	showButtonZurueckV();
	             
	                	fenster1.setVisible(true);
	                	fenster2.setVisible(false);
	                } 

	                if(selected == 1)
	                {
	                	showPanelsV();
	                	showDropdownVSG();
	                	showButtonZurueckVV();
	                	
	                	
	                    fenster2.setVisible(true);
	                    fenster1.setVisible(false);
	                }
				}
			});
    	}

    	public static void showButtonZurueckO(){
    		
    		btnzurueckO = new JButton ("Zurück");
        	btnzurueckO.setBounds(800, 500, 150, 25);
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
	                	fenster1.repaint();
	                	fenster1.validate();
	                	showPanelsI();
	                	showDropdownSG();
	                	showDropdownVS();
	                	showDropdownO();
	                	showAreaO();
	                	showAreaV();
	                	showButtonZurueckO();
	                	showButtonZurueckSG();
	                	showButtonZurueckV();
	             
	                	fenster1.setVisible(true);
	                	fenster2.setVisible(false);
	                } 

	                if(selected == 1)
	                {
	                	showPanelsV();
	                	showDropdownVSG();
	                	showButtonZurueckVV();
	                	
	                	
	                    fenster2.setVisible(true);
	                    fenster1.setVisible(false);
	                }
				}
			});
        	}

    	public static void showButtonSubsribeSG(){
    		
        	btnSubscribeSG = new JButton("Abonnieren");
        	btnSubscribeSG.setBounds(600, 400, 150, 25);
        	panelSG.add(btnSubscribeSG);
        	panelSG.validate();
        	panelSG.repaint();
        	
        	btnSubscribeSG.addActionListener(new ActionListener() {
				
				
				public void actionPerformed(ActionEvent btnSubSGe) {

					
				}
			});

        	
    	}
    	
    	public static void showButtonSubsribeV(){
    		
        	btnSubscribeV = new JButton("Abonnieren");
        	btnSubscribeV.setBounds(600, 400, 150, 25);
        	panelV.add(btnSubscribeV);
        	panelV.validate();
        	panelV.repaint();
        	
        	btnSubscribeV.addActionListener(new ActionListener() {
				
				
				public void actionPerformed(ActionEvent btnSubVe) {

					
				}
			});

        	
    	}
    	
    	public static void showButtonSubsribeO(){
    		
        	btnSubscribeO = new JButton("Abonnieren");
        	btnSubscribeO.setBounds(600, 400, 150, 25);
        	panelO.add(btnSubscribeO);
        	panelO.validate();
        	panelO.repaint();
        	
        	btnSubscribeO.addActionListener(new ActionListener() {
				
				
				public void actionPerformed(ActionEvent btnSubVe) {

					
				}
			});

        	
    	}
    	
    	public static void showButtonUnsubsribeSG(){
    		
        	btnUnsubscribeSG = new JButton("Unsubscribe");
        	btnUnsubscribeSG.setBounds(800, 400, 150, 25);
        	panelSG.add(btnUnsubscribeSG);
        	panelSG.validate();
        	panelSG.repaint();
        	
        	
        	btnUnsubscribeSG.addActionListener(new ActionListener() {
				
				
				public void actionPerformed(ActionEvent btnUnSGe) {

					
				}
			});

    	}
    	
    	public static void showButtonUnsubsribeV(){
    		
        	btnUnsubscribeV = new JButton("Unsubscribe");
        	btnUnsubscribeV.setBounds(800, 400, 150, 25);
        	panelV.add(btnUnsubscribeV);
        	panelV.validate();
        	panelV.repaint();

        	
        	btnUnsubscribeV.addActionListener(new ActionListener() {
				
				
				public void actionPerformed(ActionEvent btnUnVe) {

					
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

					
				}
			});
    	}

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
    	
    	public static void showAreaV(){
    	
            
            labelAreaV = new JLabel("Informationen bezüglich: ");
            labelAreaV.setBounds(600, 10, 300, 100);
            panelV.add(labelAreaV);
            panelV.validate();
            

            AreaV = new JTextArea();
            AreaV.setLineWrap(true);
    		AreaV.setBounds(600, 90, 350, 300);
    		panelV.add(AreaV);
    		panelV.validate();
    		
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
    	
    	
    	
    	/********************************************/
    	/**********Methoden Veranstalter*************/
    	/********************************************/
    	
    	
    	
    	

    	public static void showButtonZurueckVV(){
    		
    		btnZurueckVV = new JButton ("Zurück");
        	btnZurueckVV.setBounds(800, 500, 150, 25);
        	panelVV.add(btnZurueckVV);
        	panelVV.validate();

        	
        	
        	btnZurueckVV.addActionListener(new ActionListener() {
				
				
            	public void actionPerformed(ActionEvent btnZurueckVVe) {
				
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
                	showAreaV();
                	showButtonZurueckO();
                	showButtonZurueckSG();
                	showButtonZurueckV();
//                	I.interessentErzeugen(interessent);
             
                    
                    fenster1.setVisible(true);
                	fenster2.setVisible(false);
                } 

                if(selected == 1)
                {
                	showPanelsV();
                	showDropdownVSG();
                	showButtonZurueckVV();
                	
                	
                    fenster2.setVisible(true);
                    fenster1.setVisible(false);
                }

				}
			});
    	}
    	
    	public static void showButtonSubsribeE(){
    		
        	btnSubscribeE = new JButton("Subscribe");
        	btnSubscribeE.setBounds(600, 400, 150, 25);
        	panelE.add(btnSubscribeSG);
        	panelE.validate();
        	panelE.repaint();
        	
        	btnSubscribeE.addActionListener(new ActionListener() {
				
				
				public void actionPerformed(ActionEvent btnSubEe) {

					
				}
			});

        	
    	}

    	public static void showButtonSubsribeG(){
    		
        	btnSubscribeG = new JButton("Abonnieren");
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
    		
        	btnUnsubscribeE = new JButton("Abonnieren");
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
    	
    	public static void showButtonOK(){
    		
    		btnOK = new JButton("OK");
    		btnOK.setBounds(850, 450, 100, 25);
    		panelVV.add(btnOK);
    		panelVV.validate();
        	panelVV.repaint();
        	
        	
        	btnOK.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent BtnOKe) {
					
					hideFieldsNewV();
					hideLabelsFields();
					showDropdownVV();
					
					
				}
			});
    	}
    	
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
					showButtonOK();
					hideAreaVSA();
					hideAreaVSG();
					if (countVV >0){
						hideAreaVV();
						btnDeleteV.setVisible(false);
						btnEditV.setVisible(false);

					}


					
				}
			});

        	
    	}

    	public static void showAreaVSG(){
    	
            
    		labelAreaVSG = new JLabel("Informationen bezüglichSG: ");
    		labelAreaVSG.setBounds(600, 10, 300, 100);
            panelVV.add(labelAreaVSG);
        	panelVV.validate();
        	panelVV.repaint();



            AreaVSG = new JTextArea();
            AreaVSG.setLineWrap(true);
            AreaVSG.setBounds(600, 90, 350, 300);
    		panelVV.add(AreaVSG);
        	panelVV.validate();

            
    	}
    	
    	public static void showAreaVSA(){
    	
            
    		labelAreaVSA = new JLabel("Informationen bezüglichSA: ");
    		labelAreaVSA.setBounds(600, 10, 300, 100);
            panelVV.add(labelAreaVSA);
        	panelVV.validate();
        	panelVV.repaint();



            AreaVSA = new JTextArea();
            AreaVSA.setLineWrap(true);
            AreaVSA.setBounds(600, 90, 350, 300);
    		panelVV.add(AreaVSA);
        	panelVV.validate();

            
    	}

    	public static void showAreaVV(){
    	
            
    		labelAreaVV = new JLabel("Informationen bezüglichVV: ");
            labelAreaVV.setBounds(600, 10, 300, 100);
            panelVV.add(labelAreaVV);
        	panelVV.validate();
        	panelVV.repaint();



            AreaVV = new JTextArea();
            AreaVV.setLineWrap(true);
    		AreaVV.setBounds(600, 90, 350, 300);
    		panelVV.add(AreaVV);
        	panelVV.validate();

            
    	}
    	
    	public static void showDropdownVSG(){
    		
    		
            
    		labelVSG = new JLabel("Bitte wählen Sie eine Sportgruppe");
            labelVSG.setBounds(10, 50, 300, 25);
            panelVV.add(labelVSG);
            labelVSG.setVisible(true);
            labelVSG.validate();
    		
    		final String[] DropDownVV = new String[] {"Judo", "Kampf", "Pi", "Pa", "Po"};
        	dropdownVSG = new JComboBox(DropDownVV);
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
        				showAreaVSG();
        				
        				if (countNew > 0){
        					hideFieldsNewV();
        					hideLabelsFields();
        				}
        				
        				if(countSA > 0){
        					hideAreaVSA();
        				}
        				
        				if(countVV >0){
        					hideAreaVV();
        				}
        		}

        	});
    	}
    	
    	public static void showDropdownVSA(){
        	
    		labelVSA = new JLabel("Bitte wählen Sie eine Sportart!");
        	labelVSA.setBounds(10, 100, 300, 25);
            panelVV.add(labelVSA);
            panelVV.validate();
            
            final String[] DropDownVSA = new String[] {"Sportarten", "Kampfsport", "Rückschlag", "Schnee", "Soooonenschein"};
          	dropdownVSA = new JComboBox(DropDownVSA);
           	dropdownVSA.setBounds(10, 120, 200, 25);
           	panelVV.add(dropdownVSA);
           	panelVV.validate();
           	
           	dropdownVSA.addActionListener(new ActionListener(){
           		
           		public void actionPerformed(ActionEvent dropdownSAe) { 			
           			
           			
           			showButtonNewV();
           			showDropdownVV();
           			hideAreaVSG();
           			showAreaVSA();
           			if (countVV >0){
           				hideAreaVV();
           				btnDeleteV.setVisible(false);
           				btnEditV.setVisible(false);
           			}
           			
           			if (countNew > 0){
    					hideFieldsNewV();
    					hideLabelsFields();
    				}
           			
           		}
       		});
        }
    	
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
           			
           			showAreaVV();
           			hideAreaVSA();
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
    	
    	public static void showBtnAbortEdit(){
    		
    		btnAbortEdit = new JButton("Abbrechen");
    		btnAbortEdit.setBounds( 600, 450, 150, 25);
        	panelVV.add(btnAbortEdit);
        	panelVV.validate();
        	panelVV.repaint();
        	
        	btnAbortEdit.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent BtnAbortEdite) {
					
				}
			});
    	}
    	
    	
    	public static void showBtnEdit(){
    		
    		
        	btnEditV = new JButton("Ändern");
    		btnEditV.setBounds( 600, 450, 150, 25);
        	panelVV.add(btnEditV);
        	panelVV.validate();
        	panelVV.repaint();
        	
        	btnEditV.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent BtnEdite) {
					hideAreaVV();
					showTextfieldsVAendern();
					hideBtnDelete();
					hideBtnEdit();
					hideBtnAbortEdit();
					showBtnEditOK();
					showBtnAbortEdit();
				}
			});
    	}
    	
    	public static void showBtnDelete(){
    		
    		btnDeleteV = new JButton("Löschen");
    		btnDeleteV.setBounds(800, 450, 150, 25);
        	panelVV.add(btnDeleteV);
        	panelVV.validate();
        	panelVV.repaint();
    	} 
    	
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
					showAreaVV();
					
					
				}
			});
    	}  
    	
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
    	
    	public static void showTextfieldsVErstellen(){
    		
//    		Color colorGrey = new Color(152,153,155);
        	showLabelsFields();
    		fieldBeschrE = new JTextField("");
    		fieldBeschrE.setToolTipText("Bitte tragen Sie hier einen Namen und eine Beschreibung der Veranstaltung ein");
    		fieldBeschrE.setBounds(650, 80, 300, 25);
        	panelVV.add(fieldBeschrE);
        	panelVV.validate();
    		
        	fieldInfoE = new JTextField("");
        	fieldInfoE.setBounds(650, 130, 300, 50);
        	fieldInfoE.setToolTipText("Bitte geben Sie hier Informatiionen bezüglich der veranstaltung ein");
        	panelVV.add(fieldInfoE);
        	panelVV.validate();
        	
        	final String[] DropDownDay = new String[] {"0","1", "2","3","4","5","6","7","8", "9","10","11","12","13", "14","15","16","17","18","19","20","21","22","23", "24","25","26","27","28","29","30","31"};
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
    	
    	public static void showTextfieldsVAendern(){
    		
    		showLabelsFields();
    		
    		fieldBeschrAE = new JTextField("");
    		fieldBeschrAE.setBounds(650, 80, 300, 25);
        	panelVV.add(fieldBeschrAE);
        	panelVV.validate();
        	
    		
        	fieldInfoAE = new JTextField("");
        	fieldInfoAE.setBounds(650, 130, 300, 50);
        	panelVV.add(fieldInfoAE);
        	panelVV.validate();
        	
    		
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
    	
    	public static void hideAreaVSA(){
    		
    		AreaVSA.setVisible(false);
    		labelAreaVSA.setVisible(false);
    	}
    	
    	public static void hideAreaVV(){
    		
    		AreaVV.setVisible(false);
    		labelAreaVV.setVisible(false);
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
    		fieldInfoAE.setVisible(false);
    		dropdownDayAE.setVisible(false);
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
    		fieldInfoE.setVisible(false);
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
    		btnOK.setVisible(false);
    		
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
}