package GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
 
public class neu 
{
	
		private static JLabel labelAnweisung, labelSG, labelSA, labelV, labelO, labelAreaSG, labelAreaV, labelAreaO, labelVS;
		private static JRadioButton rbtnI;
		private static JRadioButton rbtnV;
		private static JComboBox dropdownSG, dropdownSA, dropdownV, dropdownVS, dropdownO;
		private static JTextArea AreaSG, AreaV, AreaO;
		private static JButton btnsubscribe, btnunsubscribe ,btnpublish, btnzurueck;  
		
		final static JFrame fenster = new JFrame("Herzlich Willkommen");
        final static JTabbedPane tabLeiste = new JTabbedPane();
        final static JTabbedPane tabLeiste2 = new JTabbedPane();
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
                
                
                //Also wurde Interessent gewählt!
                if(selected == 0)
                {
                	showPanelsI();
                	showDropdownSG();
                	showDropdownVS();
                	showDropdownO();
             
                    fenster.add(tabLeiste);
                    fenster.setVisible(true);
                	
                } //Ende der If
                
                             
                
                
        }
 
     
    	
    	
    	public static void showPanelsI(){
    		
	    	/***************************************************************/
	    	/**************************PANEL********************************/
	    	/***************************************************************/	
	        
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
	        					
	        		}
	        	});
            
    	}

    	public static void showDropdownSA(){
    		
    		labelSA = new JLabel("Bitte wählen Sie eine Sportart!");
    		labelSA.setBounds(10, 70, 300, 100);
            panelSG.add(labelSA);
    		
            final String[] DropDownSA = new String[] {"Sportarten", "Kampfsport", "Rückschlag", "Schnee", "Soooonenschein"};
        	dropdownSA = new JComboBox(DropDownSA);
        	dropdownSA.setBounds(10, 90, 200, 100);
        	panelSG.add(dropdownSA);

        	
        	dropdownSA.addActionListener(new ActionListener()
        	{
        		public void actionPerformed(ActionEvent dropdownSAe) 
        		{ 			
    				showDropdownV();
    				showAreaSG();
        		}
   			});
    	}
    	
    	public static void showDropdownV(){
    		
    		labelV = new JLabel("Bitte wählen Sie eine Veranstaltung!");
    		labelV.setBounds(10, 120, 300, 100);
            panelSG.add(labelV);

    		
            final String[] DropDownV = new String[] {"Veranstaltungen", "Bla", "Beispiel1", "Example", "Ejemplo"};
        	dropdownV = new JComboBox(DropDownV);
        	dropdownV.setBounds(10, 140, 200, 100);
        	panelSG.add(dropdownV);

    	}

    	public static void showDropdownVS(){
        	
    		labelVS = new JLabel("Bitte wählen Sie eine(n) Veranstalter/in!");
            labelVS.setBounds(10, 10, 300, 100);
            panelV.add(labelVS);
            labelVS.setVisible(true);
    		
        	final String[] DropDownVS = new String[] {"Veranstalter", "David", "Laura", "Super", "Mentor"};
        	dropdownVS = new JComboBox(DropDownVS);
        	dropdownVS.setBounds(10, 40, 200, 100);
        	panelV.add(dropdownVS);
        	dropdownVS.setVisible(true);	
    		
    	}

    	public static void showDropdownO(){
    		
    		labelO = new JLabel("Bitte wählen Sie einen Ort");
            labelO.setBounds(10, 10, 300, 100);
            panelO.add(labelO);
            labelO.setVisible(true);
    		
    		final String[] DropDownO = new String[] {"Orte", "Bernberg", "Hesselbach", "Niederseßmar", "Wiehl"};
        	dropdownO = new JComboBox(DropDownO);
        	dropdownO.setBounds(10, 40, 200, 100);
        	panelO.add(dropdownO);
        	dropdownO.setVisible(true);
    	}

    	public static void showButtonZurueck(){
    		
    		btnzurueck = new JButton ("Zurück");
        	btnzurueck.setBounds(800, 500, 150, 25);
        	panelSG.add(btnzurueck);
        	
        	
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
					
				}
			});
    	}

    	public static void showButtonSubsribe(){
    		
        	btnsubscribe = new JButton("Abonnieren");
        	btnsubscribe.setBounds(600, 400, 150, 25);
        	panelSG.add(btnsubscribe);
        	
    	}
    	
    	public static void showButtonUnsubsribe(){
    		
        	btnunsubscribe = new JButton("EntAbonnieren");
        	btnunsubscribe.setBounds(800, 400, 150, 25);
        	panelSG.add(btnunsubscribe);
    	}

    	public static void showAreaSG(){
    		
    		labelAreaSG = new JLabel("Informationen bezüglich: ");
            labelAreaSG.setBounds(600, 10, 300, 100);
            panelSG.add(labelAreaSG);


            AreaSG = new JTextArea();
            AreaSG.setLineWrap(true);
    		AreaSG.setBounds(600, 90, 350, 300);
    		panelSG.add(AreaSG);
            
    	}
    	
    	public static void showAreaV(){
    	
            
            labelAreaV = new JLabel("Informationen bezüglich: ");
            labelAreaV.setBounds(600, 10, 300, 100);
            panelV.add(labelAreaV);
            

            AreaV = new JTextArea();
            AreaV.setLineWrap(true);
    		AreaV.setBounds(600, 90, 350, 300);
    		panelV.add(AreaV);
    		
    	}
    	
    	public static void showAreaO(){
    	
            
            labelAreaO = new JLabel("Informationen bezüglich: ");
            labelAreaO.setBounds(600, 10, 300, 100);
            panelO.add(labelAreaO);
            

            AreaO = new JTextArea();
            AreaO.setLineWrap(true);
    		AreaO.setBounds(600, 90, 350, 300);
    		panelO.add(AreaO);
            
    		
    	}
    	
    	

}