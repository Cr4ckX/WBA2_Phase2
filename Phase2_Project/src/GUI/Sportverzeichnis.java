package GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.sun.org.apache.xerces.internal.impl.RevalidationHandler;
 
public class neu 
{
	
		private static JLabel labelAnweisung, labelSG, labelSA, labelV, labelO, labelAreaSG, labelAreaV, labelAreaO, labelVS;
		private static JComboBox dropdownSG, dropdownSA, dropdownV, dropdownVS, dropdownO;
		private static JTextArea AreaSG, AreaV, AreaO;
		
		//Button Interessenten
		private static JButton btnSubscribeSG, btnUnsubscribeSG, btnUnsubscribeO, btnUnsubscribeV, btnSubscribeV, btnSubscribeO, btnzurueckSG, btnzurueckV, btnzurueckO;  
		//Button Veranstalter
		private static JButton btnPublishE, btnPublishV, btnPublishG, btnSubscribeE, btnSubscribeG;
		
		
		final static JFrame fenster = new JFrame("Herzlich Willkommen");
        final static JTabbedPane tabLeiste = new JTabbedPane();
        final static JTabbedPane tabLeiste2 = new JTabbedPane();
        static JPanel PanelMain = new JPanel ();
		static JPanel panelSG = new JPanel();
		static JPanel panelV = new JPanel();
		static JPanel panelG = new JPanel();
		static JPanel panelE = new JPanel();
		static JPanel panelVV = new JPanel();
		
		
        public static void main(String[] args) {
                
                fenster.setSize(1000, 600);
                fenster.setLocationRelativeTo(null);
                fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                
                
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
             
                    fenster.add(tabLeiste);
                    fenster.setVisible(true);
                	
                } 

                if(selected == 1)
                {
                	showPanelsV();
                	
                	fenster.add(tabLeiste2);
                    fenster.setVisible(true);
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
	
	        panelG.setLayout(null);
	        tabLeiste.addTab("Orte", panelG);
    	}
    	
    	
    	public static void showPanelsV(){
    		
	    	/***************************************************************/
	    	/**************************PANEL********************************/
	    	/***************************************************************/	
	        
	        panelE.setLayout(null);	
	        tabLeiste2.addTab("Equiment", panelE);
	            
	        
	        panelVV.setLayout(null);
	        tabLeiste2.addTab("Gebäude", panelVV);
	
	        panelG.setLayout(null);
	        tabLeiste2.addTab("meine Veranstaltungen", panelG);
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
	        				showArea();
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
            panelG.add(labelO);
            labelO.setVisible(true);
            labelO.validate();
    		
    		final String[] DropDownO = new String[] {"Orte", "Bernberg", "Hesselbach", "Niederseßmar", "Wiehl"};
        	dropdownO = new JComboBox(DropDownO);
        	dropdownO.setBounds(10, 40, 200, 100);
        	panelG.add(dropdownO);
        	panelG.validate();
        	
        	
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
				
            	fenster.dispose();
            	
            	Object[] options = {"Interessent", "Veranstalter"};
                
                int selected = JOptionPane.showOptionDialog(null,
                  "Welche Rolle haben Sie?",
                  "Alternativen",
                  JOptionPane.DEFAULT_OPTION, 
                  JOptionPane.INFORMATION_MESSAGE, 
                  null, options, options[0]);
					
	                if(selected == 0)
	                {
	                	fenster.repaint();
	                	fenster.validate();
	                	showPanelsI();
	                	showDropdownSG();
	                	showDropdownVS();
	                	showDropdownO();
	             
	                    fenster.add(tabLeiste);
	                    fenster.setVisible(true);
	                	
	                }
	                
//	                if(selected == 1)
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
				
            	fenster.dispose();
            	
            	Object[] options = {"Interessent", "Veranstalter"};
                
                int selected = JOptionPane.showOptionDialog(null,
                  "Welche Rolle haben Sie?",
                  "Alternativen",
                  JOptionPane.DEFAULT_OPTION, 
                  JOptionPane.INFORMATION_MESSAGE, 
                  null, options, options[0]);
					
	                if(selected == 0)
	                {
	                	fenster.repaint();
	                	fenster.validate();
	                	showPanelsI();
	                	showDropdownSG();
	                	showDropdownVS();
	                	showDropdownO();
	             
	                    fenster.add(tabLeiste);
	                    fenster.setVisible(true);
	                	
	                }
	                
//	                if(selected == 1)
				}
			});
    	}

    	public static void showButtonZurueckO(){
    		
        	btnzurueckO = new JButton ("Zurück");
           	btnzurueckO.setBounds(800, 500, 150, 25);
           	panelG.add(btnzurueckSG);
           	panelG.validate();
           	
           	
           	btnzurueckO.addActionListener(new ActionListener() {
    				
           	public void actionPerformed(ActionEvent btnzurueckOe) {
    				
                	fenster.dispose();
                	
                	Object[] options = {"Interessent", "Veranstalter"};
                    
                    int selected = JOptionPane.showOptionDialog(null,
                      "Welche Rolle haben Sie?",
                      "Alternativen",
                      JOptionPane.DEFAULT_OPTION, 
                      JOptionPane.INFORMATION_MESSAGE, 
                      null, options, options[0]);
    					
    	                if(selected == 0)
    	                {
    	                	fenster.repaint();
    	                	fenster.validate();
    	                	showPanelsI();
    	                	showDropdownSG();
    	                	showDropdownVS();
    	                	showDropdownO();
    	             
    	                    fenster.add(tabLeiste);
    	                    fenster.setVisible(true);
    	                	
    	                }
    	                
//    	                if(selected == 1)
    				}
    			});
        	}
    	

    	public static void showButtonSubsribeSG(){
    		
        	btnSubscribeSG = new JButton("Abonnieren");
        	btnSubscribeSG.setBounds(600, 400, 150, 25);
        	panelSG.add(btnSubscribeSG);
        	panelSG.validate();
        	panelSG.repaint();

        	
    	}
    	
    	public static void showButtonSubsribeV(){
    		
        	btnSubscribeV = new JButton("Abonnieren");
        	btnSubscribeV.setBounds(600, 400, 150, 25);
        	panelV.add(btnSubscribeV);
        	panelV.validate();
        	panelV.repaint();

        	
    	}
    	
    	public static void showButtonSubsribeO(){
    		
        	btnSubscribeO = new JButton("Abonnieren");
        	btnSubscribeO.setBounds(600, 400, 150, 25);
        	panelG.add(btnSubscribeO);
        	panelG.validate();
        	panelG.repaint();

        	
    	}
    	
    	public static void showButtonUnsubsribeSG(){
    		
        	btnUnsubscribeSG = new JButton("Unsubscribe");
        	btnUnsubscribeSG.setBounds(800, 400, 150, 25);
        	panelSG.add(btnUnsubscribeSG);
        	panelSG.validate();
        	panelSG.repaint();

    	}
    	
    	public static void showButtonUnsubsribeV(){
    		
        	btnUnsubscribeV = new JButton("Unsubscribe");
        	btnUnsubscribeV.setBounds(800, 400, 150, 25);
        	panelV.add(btnUnsubscribeV);
        	panelV.validate();
        	panelV.repaint();

    	}

    	public static void showButtonUnsubsribeO(){
	
    		btnUnsubscribeO = new JButton("Unsubscribe");
    		btnUnsubscribeO.setBounds(800, 400, 150, 25);
    		panelG.add(btnUnsubscribeO);
    		panelG.validate();
    		panelG.repaint();
    	}

    	public static void showArea(){
    		
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
            panelG.add(labelAreaO);
        	panelG.validate();

            

            AreaO = new JTextArea();
            AreaO.setLineWrap(true);
    		AreaO.setBounds(600, 90, 350, 300);
    		panelG.add(AreaO);
        	panelG.validate();

            
    		
    	}
    	
    	
    	
    	/********************************************/
    	/**********Methoden Veranstalter*************/
    	/********************************************/
    	
    	public static void showButtonPublishE(){
    		
        	btnPublishE = new JButton("Publish");
        	btnPublishE.setBounds(800, 400, 150, 25);
        	panelE.add(btnUnsubscribeSG);
        	panelE.validate();
        	panelE.repaint();

    	}
    	
    	public static void showButtonPublishG(){
    		
        	btnSubscribeG = new JButton("Unsubscribe");
        	btnSubscribeG.setBounds(800, 400, 150, 25);
        	panelG.add(btnSubscribeG);
        	panelG.validate();
        	panelG.repaint();

    	}

}