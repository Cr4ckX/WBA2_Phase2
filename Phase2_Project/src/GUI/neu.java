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
		
		//TAB-Leiste!
        final static JTabbedPane tabLeiste = new JTabbedPane();
		
		static JPanel panelSG = new JPanel();
		static JPanel panelV = new JPanel();
		static JPanel panelO = new JPanel();
		
		
		
        public static void main(String[] args) {
                final JFrame fenster = new JFrame("Herzlich Willkommen");
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
                if(selected == 0){
                	
                	
                	/***************************************************************/
                	/**************************PANEL********************************/
                	/***************************************************************/	
                    
                    panelSG.setLayout(null);	
                    tabLeiste.addTab("Sportgruppen", panelSG);
                        
                    
                    panelV.setLayout(null);
                    tabLeiste.addTab("Veranstalter", panelV);
        
                    panelO.setLayout(null);
                    tabLeiste.addTab("Orte", panelO);
                	
                
                
                
                /***************************************************************/
            	/**************************Labels*******************************/
            	/***************************************************************/
                    
                    
                /*********************************/
              	/************LabelsSG*************/
               	/*********************************/ 
                    
                    
                labelSG = new JLabel("Bitte w�hlen Sie eine Sportgruppe!");
                labelSG.setBounds(10, 10, 300, 100);
                panelSG.add(labelSG);
                labelSG.setVisible(true);
                
        		labelSA = new JLabel("Bitte w�hlen Sie eine Sportart!");
        		labelSA.setBounds(10, 70, 300, 100);
                panelSG.add(labelSA);
                labelSA.setVisible(false);

        		labelV = new JLabel("Bitte w�hlen Sie eine Veranstaltung!");
        		labelV.setBounds(10, 120, 300, 100);
                panelSG.add(labelV);
                labelV.setVisible(false);
                

                /***************************************/
            	/***************LabelsV*****************/
            	/***************************************/
                
                labelVS = new JLabel("Bitte w�hlen Sie eine(n) Veranstalter/in!");
                labelVS.setBounds(10, 10, 300, 100);
                panelV.add(labelVS);
                labelVS.setVisible(true);
                
                
                /***************************************/
            	/***************LabelsO*****************/
            	/***************************************/
                
                labelO = new JLabel("Bitte w�hlen Sie einen Ort");
                labelO.setBounds(10, 10, 300, 100);
                panelO.add(labelO);
                labelO.setVisible(true);
        		
        	
                
                
                /***************************************************************/
            	/**************************Text-Area****************************/
            	/***************************************************************/	
            	
                /***************************************/
            	/***************AreaSG******************/
            	/***************************************/
                
                
                labelAreaSG = new JLabel("Informationen bez�glich: ");
                labelAreaSG.setBounds(600, 10, 300, 100);
                panelSG.add(labelAreaSG);
                labelAreaSG.setVisible(false);

                AreaSG = new JTextArea();
                AreaSG.setLineWrap(true);
        		AreaSG.setBounds(600, 90, 350, 300);
        		panelSG.add(AreaSG);
                //Erstmal unsichtbar
                AreaSG.setVisible(false);
                    
                
                /***************************************/
            	/***************AreaV*******************/
            	/***************************************/
                
                labelAreaV = new JLabel("Informationen bez�glich: ");
                labelAreaV.setBounds(600, 10, 300, 100);
                panelV.add(labelAreaV);
                

                AreaV = new JTextArea();
                AreaV.setLineWrap(true);
        		AreaV.setBounds(600, 90, 350, 300);
        		panelV.add(AreaV);
        		
        		/***************************************/
            	/***************AreaO*******************/
            	/***************************************/
                
                labelAreaO = new JLabel("Informationen bez�glich: ");
                labelAreaO.setBounds(600, 10, 300, 100);
                panelO.add(labelAreaO);
                

                AreaO = new JTextArea();
                AreaO.setLineWrap(true);
        		AreaO.setBounds(600, 90, 350, 300);
        		panelO.add(AreaO);
                
                
                /***************************************************************/
            	/**************************DROP-DOWN****************************/
            	/***************************************************************/	
                
        		/***************************************/
            	/***************DropDownSG**************/
            	/***************************************/
        		
                final String[] DropDownSG = new String[] {"Sportgruppen", "Kampf", "Ball", "Example", "Ejemplo"};
            	dropdownSG = new JComboBox(DropDownSG);
            	dropdownSG.setBounds(10, 40, 200, 100);
            	panelSG.add(dropdownSG);
            	
                final String[] DropDownSA = new String[] {"Sportarten", "Kampfsport", "R�ckschlag", "Schnee", "Soooonenschein"};
            	dropdownSA = new JComboBox(DropDownSA);
            	dropdownSA.setBounds(10, 90, 200, 100);
            	panelSG.add(dropdownSA);
            	dropdownSA.setVisible(false);
            		
            	final String[] DropDownV = new String[] {"Veranstaltungen", "Bla", "Beispiel1", "Example", "Ejemplo"};
            	dropdownV = new JComboBox(DropDownV);
            	dropdownV.setBounds(10, 140, 200, 100);
            	panelSG.add(dropdownV);
            	dropdownV.setVisible(false);
            	
            	
            	
            	/***************************************/
            	/***************DropDownO**************/
            	/***************************************/
            	final String[] DropDownO = new String[] {"Orte", "Bernberg", "Hesselbach", "Niederse�mar", "Wiehl"};
            	dropdownO = new JComboBox(DropDownO);
            	dropdownO.setBounds(10, 40, 200, 100);
            	panelO.add(dropdownO);
            	dropdownO.setVisible(true);
            	
            	
            	/***************************************************************/
            	/**************************Button*******************************/
            	/***************************************************************/
                
            	btnzurueck = new JButton ("Zur�ck");
            	btnzurueck.setBounds(800, 500, 150, 25);
            	panelSG.add(btnzurueck);
            	
            	
//            	panelV.add(btnzurueck);
//            	panelO.add(btnzurueck);
                    
                /*********************************/
              	/************ButtonSG*************/
               	/*********************************/ 
            	
            	btnsubscribe = new JButton("Abonnieren");
            	btnsubscribe.setBounds(600, 400, 150, 25);
            	panelSG.add(btnsubscribe);
            	btnsubscribe.setVisible(false);
            	
            	
            	btnunsubscribe = new JButton("EntAbonnieren");
            	btnunsubscribe.setBounds(800, 400, 150, 25);
            	panelSG.add(btnunsubscribe);
            	btnunsubscribe.setVisible(false);
            	/***************************************************************/
            	/**************************Actions******************************/
            	/***************************************************************/
                    
                    
                /*********************************/
              	/************ActionsSG************/
               	/*********************************/ 
                   
                /*ACTIONS*/
                         	
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
                
                
                dropdownSG.addActionListener(new ActionListener(){

        			@Override
        		public void actionPerformed(ActionEvent dropdownSGe) 
        		{ 			
        				
        				
//        				JComboBox item = (JComboBox) dropdownSGe.getSource();
//        				
//        				if (item.getSelectedIndex() != 0 && item.getSelectedIndex() < DropDownSG.length){
        					dropdownSA.setVisible(true);
        					labelSA.setVisible(true);
        					AreaSG.setVisible(true);
        					labelAreaSG.setVisible(true);
        					btnsubscribe.setVisible(true);
        					btnunsubscribe.setVisible(true);
        				
        				}
        			}
        			
        		);
                
                dropdownSA.addActionListener(new ActionListener(){

        			@Override
        		public void actionPerformed(ActionEvent dropdownSAe) 
        		{ 			
        				AreaSG.setVisible(true);
//        				JComboBox item = (JComboBox) dropdownSAe.getSource();
//        				
//        				if (item.getSelectedIndex() != 0 && item.getSelectedIndex() < DropDownSA.length){
        					dropdownV.setVisible(true);
        					labelV.setVisible(true);
        					btnsubscribe.setVisible(true);
        					btnunsubscribe.setVisible(true);
        					
        					
        				}
        			}
        			
        		);
                    
                    
                    fenster.add(tabLeiste);
                    fenster.setVisible(true);
                	
            } //Ende der If
                
                             
                
                
        }
 
        /***************************************/
    	/***************DropDownVS**************/
    	/***************************************/
    	
    	public void dropdownVS(){
    	
        	final String[] DropDownVS = new String[] {"Veranstalter", "David", "Laura", "Super", "Mentor"};
        	dropdownVS = new JComboBox(DropDownVS);
        	dropdownVS.setBounds(10, 40, 200, 100);
        	panelV.add(dropdownVS);
        	dropdownVS.setVisible(true);	
    		
    	}
}