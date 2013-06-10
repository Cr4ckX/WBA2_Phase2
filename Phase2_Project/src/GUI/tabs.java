package GUI;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
 
public class tabs {

	private static JButton btnAuswahl, btnZurueck;
	private static JLabel labelAnweisung;
	private static JRadioButton rbtnI, rbtnV;
	private static ButtonGroup rbtngroup;
        public static void main(String[] args) {
                JFrame fenster = new JFrame("Herzlich Willkommen");
                fenster.setSize(1000, 600);
                fenster.setLocationRelativeTo(null);
                fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
 
                final JTabbedPane tabLeisteI = new JTabbedPane();
                final JTabbedPane rolle = new JTabbedPane();
                final JTabbedPane tabLeisteV = new JTabbedPane();

                
                /***************************************************************/
        		/*****************************TableisteI************************/
        		/***************************************************************/
                
                JPanel PanelSG = new JPanel();
                PanelSG.add(new JButton("Button des ersten Tabs"));
                tabLeisteI.addTab("Sportgruppen", PanelSG);
 
                JPanel PanelV = new JPanel();
                PanelV.add(new JButton("Button des zweiten Tabs"));
                tabLeisteI.addTab("Veranstalter", PanelV);
 
                JPanel PanelO = new JPanel();
                PanelO.add(new JButton("Button des zweiten Tabs"));
                tabLeisteI.addTab("Orte", PanelO);
                
                tabLeisteI.setVisible(true);
                
                
                /***************************************************************/
        		/*****************************TableisteV************************/
        		/***************************************************************/
                
                JPanel PanelE = new JPanel();
                PanelE.add(new JButton("Button des ersten Tabs"));
                tabLeisteV.addTab("Equipment", PanelE);

                tabLeisteV.setVisible(false);
                                
                //Erste Seite!
                
                JPanel PanelFirst = new JPanel();
                PanelFirst.setLayout(null);
                PanelE.setLayout(null);
                PanelO.setLayout(null);
                PanelSG.setLayout(null);
                PanelV.setLayout(null);
                
                labelAnweisung = new JLabel ("Bitte wählen Sie eine Rolle aus!", JLabel.CENTER);
                labelAnweisung.setBounds(60, 20, 300, 25);
                PanelFirst.add(labelAnweisung);
                
                btnAuswahl = new JButton ("Auswahl");
        		btnAuswahl.setBounds(150, 90, 100, 25);
                PanelFirst.add(btnAuswahl);
                
        		
                rolle.addTab("Rolle", PanelFirst);

                rolle.setVisible(true);
                /***************************************************************/
        		/*****************************Button****************************/
        		/***************************************************************/
        		//Erstellen der RadioButtons
        		rbtnI = new JRadioButton("Interessent");
        		rbtnV = new JRadioButton("Veranstalter");
        		rbtnI.setBounds(50, 60, 300, 25);
        		rbtnV.setBounds(350, 60, 300, 25);
        		PanelFirst.add(rbtnI);
        		PanelFirst.add(rbtnV);
        		
        		//RadioButtonGroup
        		rbtngroup = new ButtonGroup();
        		rbtngroup.add(rbtnI);
        		rbtngroup.add(rbtnV);
        	
               
        		//AuswahlButton pressed
        		btnAuswahl.addActionListener(new ActionListener() 
        		{
        	        public void actionPerformed(ActionEvent eBtnAuswahl)
        	        {
        	            
        	            System.out.println("Test");

//        	        	/***************************************************************/
//        	    		/********************Unterscheidung der Rolle*******************/
//        	    		/***************************************************************/
//        	        	if (rbtnI.isSelected())
//        	        	{
//        	        		rolle.setVisible(false);
//        	        		tabLeisteI.setVisible(true);
//        	        	}
//        	        	
//        	        	
//        	        	if (rbtnV.isSelected())
//        	        	{
//        	        		rolle.setVisible(false);
//        	        		tabLeisteV.setVisible(true);
//        	        	}
        	            
        	        }
        	    });
                
                
                
             fenster.add(rolle);
//             fenster.add(tabLeisteV);
//             fenster.add(tabLeisteI);
                fenster.setVisible(true);
        }
 
}