package GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
 
public class neu {
	
		private static JLabel labelAnweisung, labelSG, labelSA, labelV, labelArea;
		private static JRadioButton rbtnI;
		private static JRadioButton rbtnV;
		private static JComboBox dropdownSG, dropdownSA, dropdownV;
		private static JTextArea textPayload;
	
	
 
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
                	
                //TAB-Leiste!
                JTabbedPane tabLeiste = new JTabbedPane();
                	
                JPanel panelSG = new JPanel();
                panelSG.setLayout(null);
                
                
                /***************************************************************/
            	/**************************Labels*******************************/
            	/***************************************************************/
                
                labelSG = new JLabel("Bitte wählen Sie eine Sportgruppe!");
                labelSG.setBounds(10, 10, 300, 100);
                panelSG.add(labelSG);
                labelSG.setVisible(true);
                
        		labelSA = new JLabel("Bitte wählen Sie eine Sportart!");
        		labelSA.setBounds(10, 70, 300, 100);
                panelSG.add(labelSA);
                labelSA.setVisible(false);

        		labelV = new JLabel("Bitte wählen Sie eine Veranstaltung!");
        		labelV.setBounds(10, 130, 300, 100);
                panelSG.add(labelV);
                labelV.setVisible(false);


        		
                
                
                /***************************************************************/
            	/**************************Text-Area****************************/
            	/***************************************************************/	
            	
                labelArea = new JLabel("Informationen bezüglich: ");
                labelArea.setBounds(600, 10, 300, 100);
                panelSG.add(labelArea);
                labelArea.setVisible(false);

                textPayload = new JTextArea();
                textPayload.setLineWrap(true);
        		textPayload.setBounds(600, 60, 350, 300);
        		panelSG.add(textPayload);

                    
                //Erstmal unsichtbar
                textPayload.setVisible(false);
                    
                
                /***************************************************************/
            	/**************************DROP-DOWN****************************/
            	/***************************************************************/	
                
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
            		
            		
            		
            		
            	panelSG.add(new JButton("Button des ersten Tabs"));
                tabLeiste.addTab("Sportgruppen", panelSG);
                    
                    
                    
     
                JPanel panelV = new JPanel();
                panelV.add(new JButton("Button des zweiten Tabs"));
                tabLeiste.addTab("Veranstalter", panelV);
    
                    
                JPanel panelO = new JPanel();
                panelO.add(new JButton("Button des zweiten Tabs"));
                tabLeiste.addTab("Orte", panelO);
                   
                /*ACTIONS*/
                
                
                dropdownSG.addActionListener(new ActionListener(){

        			@Override
        		public void actionPerformed(ActionEvent dropdownSG) 
        		{ 			
        				
        		
        				JComboBox item = (JComboBox) dropdownSG.getSource();
        				
        				if (item.getSelectedIndex() != 0 && item.getSelectedIndex() < DropDownSG.length){
        					dropdownSA.setVisible(true);
        					labelSA.setVisible(true);
        					textPayload.setVisible(true);
        					labelArea.setVisible(true);
        				
        				}
        			}
        			
        		});
                
                dropdownSA.addActionListener(new ActionListener(){

        			@Override
        		public void actionPerformed(ActionEvent dropdownSA) 
        		{ 			
        				textPayload.setVisible(true);
        				JComboBox item = (JComboBox) dropdownSA.getSource();
        				
        				if (item.getSelectedIndex() != 0 && item.getSelectedIndex() < DropDownSA.length){
        					dropdownV.setVisible(true);
        					labelV.setVisible(true);
        					
        				}
        			}
        			
        		});
                    
                    
                    fenster.add(tabLeiste);
                    fenster.setVisible(true);
                	
                }
                
                
        }
 
}