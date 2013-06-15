package GUI;
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
		private static JButton btnSubscribeSG, btnUnsubscribeSG, btnUnsubscribeO, btnUnsubscribeV, btnSubscribeV, btnSubscribeO, btnzurueckSG, btnzurueckV, btnzurueckO;  
		//Button Veranstalter
		private static JButton btnPublishV, btnDeleteV, btnEditV, btnPublishG, btnSubscribeE, btnSubscribeG, btnUnsubscribeE, btnUnsubscribeG, btnZurueckVV;
		private static JLabel labelVV, labelAreaVV;
		private static JComboBox dropdownVV;
		private static JTextArea AreaVV;
		
		
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
                
                Interessent I = new Interessent();
                
                Object[] options = {"Interessent", "Veranstalter"};
                
                int selected = JOptionPane.showOptionDialog(null,
                  "Welche Rolle haben Sie?",
                  "Alternativen",
                  JOptionPane.DEFAULT_OPTION, 
                  JOptionPane.INFORMATION_MESSAGE, 
                  null, options, options[0]);
                
                
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
                	I.interessentErzeugen(interessent);
             
                    
                    fenster1.setVisible(true);
                	fenster2.setVisible(false);
                } 

                if(selected == 1)
                {
                	showPanelsV();
                	showDropdownVV();
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
	        
	        panelE.setLayout(null);	
	        tabLeiste2.addTab("Equiment", panelE);
	            
	        
	        panelGB.setLayout(null);
	        tabLeiste2.addTab("Gebäude", panelGB);
	
	        panelVV.setLayout(null);
	        tabLeiste2.addTab("Meine Veranstaltungen", panelVV);
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
	                	showDropdownVV();
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
	                	showDropdownVV();
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
	                	showDropdownVV();
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
    	
    	
    	public static void showButtonPublishV(){
    		
    		btnPublishV = new JButton("Publish");
    		btnPublishV.setBounds(800, 400, 150, 25);
        	panelVV.add(btnPublishV);
        	panelVV.validate();
        	panelVV.repaint();

    	}
    	
    	public static void showButtonDeleteV(){
    		
    		btnDeleteV = new JButton("Löschen");
    		btnDeleteV.setBounds(600, 400, 150, 25);
        	panelVV.add(btnDeleteV);
        	panelVV.validate();
        	panelVV.repaint();

    	}
    	
    	public static void showButtonEditV(){
    		
    		btnEditV = new JButton("Ändern");
    		btnEditV.setBounds(600, 450, 150, 25);
        	panelVV.add(btnEditV);
        	panelVV.validate();
        	panelVV.repaint();

    	}

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
	                	fenster1.repaint();
	                	fenster1.validate();
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
	                	showDropdownVV();
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

    	public static void showDropdownVV(){
    		
    		
            
    		labelVV = new JLabel("Bitte wählen Sie eine Veranstaltung");
            labelVV.setBounds(10, 10, 300, 100);
            panelVV.add(labelVV);
            labelVV.setVisible(true);
            labelVV.validate();
    		
    		final String[] DropDownVV = new String[] {"V1", "V2", "V3", "V4", "V5"};
        	dropdownVV = new JComboBox(DropDownVV);
        	dropdownVV.setBounds(10, 40, 200, 100);
        	panelVV.add(dropdownVV);
        	panelVV.validate();
        	
        	
        	dropdownVV.addActionListener(new ActionListener()
        	{
        		public void actionPerformed(ActionEvent dropdowVVe) 
        		{ 			
//        				JComboBox item = (JComboBox) dropdownSGe.getSource();
//        				
//        				if (item.getSelectedIndex() != 0 && item.getSelectedIndex() < DropDownSG.length){
        				
        				showButtonDeleteV();
        				showButtonEditV();
        				showButtonPublishV();
        				showAreaVV();
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

    	
    	public static void showAreaVV(){
    	
            
            labelAreaVV = new JLabel("Informationen bezüglich: ");
            labelAreaVV.setBounds(600, 10, 300, 100);
            panelVV.add(labelAreaVV);
        	panelVV.validate();

            

            AreaVV = new JTextArea();
            AreaVV.setLineWrap(true);
    		AreaVV.setBounds(600, 90, 350, 300);
    		panelVV.add(AreaVV);
        	panelVV.validate();

            
    		
    	}
}