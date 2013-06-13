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
		private static JButton btnsubscribe, btnunsubscribe ,btnpublish, btnzurueck, btnzurueckV, btnzurueckO;  
		
		final static JFrame fenster = new JFrame("Herzlich Willkommen");
        final static JTabbedPane tabLeiste = new JTabbedPane();
        final static JTabbedPane tabLeiste2 = new JTabbedPane();
        static JPanel PanelMain = new JPanel ();
		static JPanel panelSG = new JPanel();
		static JPanel panelV = new JPanel();
		static JPanel panelO = new JPanel();
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
                
                
                //Also wurde Interessent gew�hlt!
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
 
     
    	public static void showPanelMain(){
    		
    		PanelMain.add(btnsubscribe);
    		PanelMain.add(btnunsubscribe);
    		PanelMain.add(btnzurueck);
    		showButtonSubsribe();
    		showButtonUnsubsribe();
    		showButtonZurueck();
    		showArea();
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
	            
	        
	        panelVV.setLayout(null);
	        tabLeiste2.addTab("Veranstaltungen", panelVV);
	
	        panelO.setLayout(null);
	        tabLeiste2.addTab("Orte", panelO);
    	}
    	
    	public static void showDropdownSG()
    	{
	    		  
	            labelSG = new JLabel("Bitte w�hlen Sie eine Sportgruppe!");
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
	        				
	        				
	        					
	        		}
	        	});
            
    	}

    	public static void showDropdownSA(){
    		
    		labelSA = new JLabel("Bitte w�hlen Sie eine Sportart!");
    		labelSA.setBounds(10, 70, 300, 100);
            panelSG.add(labelSA);
            panelSG.validate();
    		
            final String[] DropDownSA = new String[] {"Sportarten", "Kampfsport", "R�ckschlag", "Schnee", "Soooonenschein"};
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
    		
    		labelV = new JLabel("Bitte w�hlen Sie eine Veranstaltung!");
    		labelV.setBounds(10, 120, 300, 100);
            panelV.add(labelV);
            panelV.validate();

    		
            String[] DropDownV = new String[] {"Veranstaltungen", "Bla", "Beispiel1", "Example", "Ejemplo"};
        	
            dropdownV = new JComboBox(DropDownV);
        	dropdownV.setBounds(10, 140, 200, 100);
        	panelV.add(dropdownV);
        	panelV.validate();

    	
        	
        	dropdownV.addActionListener(new ActionListener()
        	{
        		public void actionPerformed(ActionEvent dropdownVe) 
        		{ 			
//        				JComboBox item = (JComboBox) dropdownSGe.getSource();
//        				
//        				if (item.getSelectedIndex() != 0 && item.getSelectedIndex() < DropDownSG.length){
        				showButtonZurueckV();
        				showButtonSubsribe();
        				showButtonUnsubsribe();
        				
        					
        		}
        	});
    	
    	
    	}

    	public static void showDropdownVS(){
        	
    		labelVS = new JLabel("Bitte w�hlen Sie eine(n) Veranstalter/in!");
            labelVS.setBounds(10, 10, 300, 100);
            panelV.add(labelVS);
            panelV.validate();
            
        	final String[] DropDownVS = new String[] {"Veranstalter", "David", "Laura", "Super", "Mentor"};
        	dropdownVS = new JComboBox(DropDownVS);
        	dropdownVS.setBounds(10, 40, 200, 100);
        	panelV.add(dropdownVS);
        	panelV.validate();
        	
    	}

    	public static void showDropdownO(){
    		
    		labelO = new JLabel("Bitte w�hlen Sie einen Ort");
            labelO.setBounds(10, 10, 300, 100);
            panelO.add(labelO);
            labelO.setVisible(true);
            labelO.validate();
    		
    		final String[] DropDownO = new String[] {"Orte", "Bernberg", "Hesselbach", "Niederse�mar", "Wiehl"};
        	dropdownO = new JComboBox(DropDownO);
        	dropdownO.setBounds(10, 40, 200, 100);
        	panelO.add(dropdownO);
        	panelO.validate();
    	}

    	public static void showButtonZurueck(){
    		
    		btnzurueck = new JButton ("Zur�ck");
        	btnzurueck.setBounds(800, 500, 150, 25);
        	PanelMain.add(btnzurueck);
        	PanelMain.validate();

        	
        	
        	btnzurueck.addActionListener(new ActionListener() {
				
				
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
    		
    		btnzurueckV = new JButton ("Zur�ck");
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
    		
    		btnzurueckO = new JButton ("Zur�ck");
        	btnzurueckO.setBounds(800, 500, 150, 25);
        	panelO.add(btnzurueckV);
        	panelO.validate();

        	
        	
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
	                
//	                if(selected == 1)
				}
			});
    	}

    	public static void showButtonSubsribe(){
    		
        	btnsubscribe = new JButton("Abonnieren");
        	btnsubscribe.setBounds(600, 400, 150, 25);
        	panelSG.add(btnsubscribe);
        	panelSG.validate();
        	panelSG.repaint();

        	
    	}
    	
    	public static void showButtonUnsubsribe(){
    		
        	btnunsubscribe = new JButton("EntAbonnieren");
        	btnunsubscribe.setBounds(800, 400, 150, 25);
        	panelSG.add(btnunsubscribe);
        	panelSG.validate();
        	panelSG.repaint();

    	}

    	public static void showArea(){
    		
    		labelAreaSG = new JLabel("Informationen bez�glich: ");
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
    	
            
            labelAreaV = new JLabel("Informationen bez�glich: ");
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
    	
            
            labelAreaO = new JLabel("Informationen bez�glich: ");
            labelAreaO.setBounds(600, 10, 300, 100);
            panelO.add(labelAreaO);
        	panelO.validate();

            

            AreaO = new JTextArea();
            AreaO.setLineWrap(true);
    		AreaO.setBounds(600, 90, 350, 300);
    		panelO.add(AreaO);
        	panelO.validate();

            
    		
    	}
    	
    	

}