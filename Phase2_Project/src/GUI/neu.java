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
	
	
 
        public static void main(String[] args) {
                JFrame fenster = new JFrame("Herzlich Willkommen");
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
                if(selected == 0){
                	
                	
                	/***************************************************************/
                	/**************************PANEL********************************/
                	/***************************************************************/	
                	//TAB-Leiste!
                    JTabbedPane tabLeiste = new JTabbedPane();
                    	
                    JPanel panelSG = new JPanel();
                    panelSG.setLayout(null);	
                    tabLeiste.addTab("Sportgruppen", panelSG);
                        
                        
                        
         
                    JPanel panelV = new JPanel();
                    panelV.setLayout(null);
                    tabLeiste.addTab("Veranstalter", panelV);
        
                        
                    JPanel panelO = new JPanel();
                    panelO.setLayout(null);
                    panelO.add(new JButton("Button des zweiten Tabs"));
                    tabLeiste.addTab("Orte", panelO);
                	
                
                
                
                /***************************************************************/
            	/**************************Labels*******************************/
            	/***************************************************************/
                    
                    
                /*********************************/
              	/************LabelsSG*************/
               	/*********************************/ 
                    
                    
                labelSG = new JLabel("Bitte wählen Sie eine Sportgruppe!");
                labelSG.setBounds(10, 10, 300, 100);
                panelSG.add(labelSG);
                labelSG.setVisible(true);
                
        		labelSA = new JLabel("Bitte wählen Sie eine Sportart!");
        		labelSA.setBounds(10, 70, 300, 100);
                panelSG.add(labelSA);
                labelSA.setVisible(false);

        		labelV = new JLabel("Bitte wählen Sie eine Veranstaltung!");
        		labelV.setBounds(10, 120, 300, 100);
                panelSG.add(labelV);
                labelV.setVisible(false);
                

                /***************************************/
            	/***************LabelsV*****************/
            	/***************************************/
                
                labelVS = new JLabel("Bitte wählen Sie eine(n) Veranstalter/in!");
                labelVS.setBounds(10, 10, 300, 100);
                panelV.add(labelVS);
                labelVS.setVisible(true);
                
                
                /***************************************/
            	/***************LabelsO*****************/
            	/***************************************/
                
                labelO = new JLabel("Bitte wählen Sie einen Ort");
                labelO.setBounds(10, 10, 300, 100);
                panelO.add(labelO);
                labelO.setVisible(true);
        		
        	
                
                
                /***************************************************************/
            	/**************************Text-Area****************************/
            	/***************************************************************/	
            	
                /***************************************/
            	/***************AreaSG******************/
            	/***************************************/
                
                
                labelAreaSG = new JLabel("Informationen bezüglich: ");
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
                
                labelAreaV = new JLabel("Informationen bezüglich: ");
                labelAreaV.setBounds(600, 10, 300, 100);
                panelV.add(labelAreaV);
                

                AreaV = new JTextArea();
                AreaV.setLineWrap(true);
        		AreaV.setBounds(600, 90, 350, 300);
        		panelV.add(AreaV);
        		
        		/***************************************/
            	/***************AreaO*******************/
            	/***************************************/
                
                labelAreaO = new JLabel("Informationen bezüglich: ");
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
            	
                final String[] DropDownSA = new String[] {"Sportarten", "Kampfsport", "Rückschlag", "Schnee", "Soooonenschein"};
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
            	/***************DropDownVS**************/
            	/***************************************/
            	final String[] DropDownVS = new String[] {"Veranstalter", "David", "Laura", "Super", "Mentor"};
            	dropdownVS = new JComboBox(DropDownVS);
            	dropdownVS.setBounds(10, 40, 200, 100);
            	panelV.add(dropdownVS);
            	dropdownVS.setVisible(true);	
            		
            	
            	/***************************************/
            	/***************DropDownO**************/
            	/***************************************/
            	final String[] DropDownO = new String[] {"Orte", "Bernberg", "Hesselbach", "Niederseßmar", "Wiehl"};
            	dropdownO = new JComboBox(DropDownO);
            	dropdownO.setBounds(10, 40, 200, 100);
            	panelO.add(dropdownO);
            	dropdownO.setVisible(true);
            	
                   
                /*ACTIONS*/
                
                
                dropdownSG.addActionListener(new ActionListener(){

        			@Override
        		public void actionPerformed(ActionEvent dropdownSG) 
        		{ 			
        				
        		
        				JComboBox item = (JComboBox) dropdownSG.getSource();
        				
        				if (item.getSelectedIndex() != 0 && item.getSelectedIndex() < DropDownSG.length){
        					dropdownSA.setVisible(true);
        					labelSA.setVisible(true);
        					AreaSG.setVisible(true);
        					labelAreaSG.setVisible(true);
        				
        				}
        			}
        			
        		});
                
                dropdownSA.addActionListener(new ActionListener(){

        			@Override
        		public void actionPerformed(ActionEvent dropdownSA) 
        		{ 			
        				AreaSG.setVisible(true);
        				JComboBox item = (JComboBox) dropdownSA.getSource();
        				
        				if (item.getSelectedIndex() != 0 && item.getSelectedIndex() < DropDownSA.length){
        					dropdownV.setVisible(true);
        					labelV.setVisible(true);
        					
        				}
        			}
        			
        		});
                    
                    
                    fenster.add(tabLeiste);
                    fenster.setVisible(true);
                	
            } //Ende der If
                
                
        }
 
}