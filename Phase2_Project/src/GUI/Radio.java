package GUI;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.awt.event.*;
import javax.swing.JScrollPane;


/*Von javax.swing abgeleitet*/
public class Radio extends JFrame{
	
	private JButton btnAuswahl, btnZurueck;
	private JRadioButton rbtnI, rbtnV;
	private JLabel labelAnweisung, loginI, loginV, labelOK, labelUKSG, labelUUK, labelArea; 
	private ButtonGroup rbtngroup;
	private JComboBox dropdownOK, dropdownUK, dropdownUUK;
	private JTextArea textPayload;
	private JPanel contentPane;

	
	
	
	Radio(){
		//Konstruktor der Elternklasse aufrufen
		super();
		
		contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
		

		
		/***************************************************************/
		/*****************************Layout****************************/
		/***************************************************************/
		
		contentPane.setLayout(null);
		
		
		/***************************************************************/
		/*****************************Beschriftung**********************/
		/***************************************************************/
		labelAnweisung = new JLabel ("Bitte wählen Sie eine Rolle aus!", JLabel.CENTER);
		loginI = new JLabel("Eingeloggt als: TODO");
		loginV = new JLabel ("Eingeloggt als: TODO");
		labelOK = new JLabel("Bitte wählen Sie eine Kategorie!");
		labelUKSG = new JLabel("Bitte wählen Sie einen Sportart!");
		labelUUK = new JLabel("Bitte wählen Sie einen Ort!");
		labelArea = new JLabel("Informationen bezüglich:");
		
		
		
		//Erstmal nicht anzeigen lassen!
		loginV.setVisible(false);
		loginI.setVisible(false);
		labelOK.setVisible(false);
		labelUKSG.setVisible(false);
		labelUUK.setVisible(false);
		labelArea.setVisible(false);
		
		/***************************************************************/
		/*****************************Button****************************/
		/***************************************************************/
		//Erstellen der RadioButtons
		rbtnI = new JRadioButton("Interessent");
		rbtnV = new JRadioButton("Veranstalter");

		//RadioButtonGroup
		rbtngroup = new ButtonGroup();
		rbtngroup.add(rbtnI);
		rbtngroup.add(rbtnV);
		
		//Auswahl/ZurŸckButton
		btnAuswahl = new JButton("Auswahl");
		btnZurueck = new JButton("Zurück");
		
		//Erstmal unsichtbar
		btnZurueck.setVisible(false);
		
		/***************************************************************/
		/*************************Dropdown-Menues***********************
		 * Hierbei zu beachten: 
		 * Oberkategorien OK: Sportgruppe SG, Veranstalter V und Ort O
		 * Unterkategorien UK: Sportart SA
		 * UnterUnterkategorie UUK: Veranstaltung
		 * /
		/***************************************************************/
		
		String[] oberkategorie = new String[] {"Kategorien", "Sportgruppen", "Veranstalter", "Orte"};
		dropdownOK = new JComboBox(oberkategorie);
		
		final String[] unterkategorie = new String[] {"Sportarten", "Kampfsport", "Rückschlag", "Schnee", "Soooonenschein"};
		dropdownUK = new JComboBox(unterkategorie);
		
		String[] Orte = new String[] {"Hemmerden", "NiebŸll", "Leck", "Grevenbroich", "Hamburg(meinePerle)"};
		dropdownUUK = new JComboBox(Orte);
		
		//Erstmal unsichtbar
		dropdownOK.setVisible(false);
		dropdownUK.setVisible(false);
		dropdownUUK.setVisible(false);
		
		
		/***************************************************************/
		/**************************Text-Area****************************/
		/***************************************************************/	
		
		textPayload = new JTextArea();
        textPayload.setLineWrap(true);
        
        //Erstmal unsichtbar
        textPayload.setVisible(false);
       
		
		/***************************************************************/
		/*************************Positionierung************************/
		/***************************************************************/
		labelAnweisung.setBounds(60, 20, 300, 25);
		loginI.setBounds(800, -10, 700, 70);
		loginV.setBounds(800, -10, 700, 70);
		rbtnI.setBounds(50, 60, 300, 25);
		rbtnV.setBounds(350, 60, 300, 25);

		btnAuswahl.setBounds(150, 90, 100, 25);
		btnZurueck.setBounds(900, 550, 100, 25);
		
		labelOK.setBounds(10, 10, 300, 100);
		dropdownOK.setBounds(10, 30, 200, 100);
		
		labelUKSG.setBounds(10, 70, 300, 100);
		dropdownUK.setBounds(10, 90, 200, 100);
		
		labelUUK.setBounds(10, 130, 300, 100);
		dropdownUUK.setBounds(10, 150, 200, 100);
		
		
		textPayload.setBounds(600, 70, 350, 300);
		
		labelArea.setBounds(600, 10, 300, 100);
		
		
		
		/***************************************************************/
		/*******************Einfuegen in die Pane***********************/
		/***************************************************************/	
		/*Einfuegen in die Pane*/
		contentPane.add(labelAnweisung);
		contentPane.add(rbtnI);
		contentPane.add(rbtnV);
		contentPane.add(btnAuswahl);
		contentPane.add(btnZurueck);
		contentPane.add(loginI);
		contentPane.add(loginV);
		contentPane.add(dropdownOK);
		contentPane.add(dropdownUK);
		contentPane.add(dropdownUUK);
		contentPane.add(labelOK);
		contentPane.add(labelUKSG);
		contentPane.add(labelUUK);
		contentPane.add(textPayload);
		contentPane.add(labelArea);
		
	
	
		/***************************************************************/
		/**************************Actions******************************/
		/***************************************************************/
	
		
		
		//AuswahlButton pressed
		btnAuswahl.addActionListener(new ActionListener() 
		{
	        public void actionPerformed(ActionEvent eBtnAuswahl)
	        {
	        	
	        	/***************************************************************/
	    		/********************Unterscheidung der Rolle*******************/
	    		/***************************************************************/
	        	if (rbtnI.isSelected())
	        	{
	        		
	        		labelAnweisung.setVisible(false);
		            rbtnI.setVisible(false);
		            rbtnV.setVisible(false);
		            btnAuswahl.setVisible(false);	
		            
		            
		            /*Oberfläche setzen!*/
		            loginI.setVisible(true);
		            dropdownOK.setVisible(true); 
		            btnZurueck.setVisible(true);
		            labelOK.setVisible(true);
		            
	        		
	        	}
	        	
	        	
	        	if (rbtnV.isSelected())
	        	{
	        		labelAnweisung.setVisible(false);
		            rbtnI.setVisible(false);
		            rbtnV.setVisible(false);
		            btnAuswahl.setVisible(false);	
		            loginV.setVisible(true);
		            btnZurueck.setVisible(true);
	        	}
	            
	        }
	    });
		
		
		btnZurueck.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent eBtnZurueck) {
				
				labelAnweisung.setVisible(true);
	            rbtnI.setVisible(true);
	            rbtnV.setVisible(true);
	            btnAuswahl.setVisible(true);
	            
	            loginI.setVisible(false);
	            loginV.setVisible(false);
	            dropdownOK.setVisible(false); 
	            dropdownUK.setVisible(false);
	            dropdownUUK.setVisible(false);
	            btnZurueck.setVisible(false);
	            labelOK.setVisible(false);
	            labelArea.setVisible(true);
	            labelUKSG.setVisible(false);
	            
				
			}
			
			
		});
		
		
		dropdownOK.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent dropdownOK) {
				
				textPayload.setVisible(false);
				
				
				JComboBox item = (JComboBox) dropdownOK.getSource();
				//0. DEFAULT 1.SG 2.V 3. O
				//Sportgruppe wurde ausgewählt
				if(item.getSelectedIndex() == 1){
					System.out.println("Klappt :)");
					labelUKSG.setVisible(true);
					dropdownUK.setVisible(true);
					
				}
				//Wenn also der Default-Wert ausgewählt wurde.
				else if (item.getSelectedIndex() == 0){
					labelUKSG.setVisible(false);
					dropdownUK.setVisible(false);
				}
				
				//Wenn also Veranstalter ausgewählt wurde ausgewählt wurde.
				else if (item.getSelectedIndex() == 2){
					labelUKSG.setVisible(false);
					dropdownUK.setVisible(false);
				}
				
				//Wenn also Orte ausgewählt wurde ausgewählt wurde.
				else if (item.getSelectedIndex() == 3){
					labelUKSG.setVisible(false);
					dropdownUK.setVisible(false);
				}
					
			}
			
		});
		
		dropdownUK.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent dropdownUK) {
				
				JComboBox item2 = (JComboBox) dropdownUK.getSource();
				
				//Wenn ein richtiges gewählt wurde
				if (item2.getSelectedIndex() < unterkategorie.length && item2.getSelectedIndex() != 0){
					textPayload.setVisible(true);
					labelArea.setVisible(true);
					
				}
					
					
				for (int i = 0; i < unterkategorie.length; i++) {
					if(item2.getSelectedIndex() == i){
						//Aktualisieren LISTE mit Informationen bezüglich der Sportart
					}
					
				}
			}
		});
	
		
		
		
	}
	
	

	public static void main(String[] args) {
		Radio windowfirst = new Radio();
		windowfirst.setBounds(100, 100, 1000, 600);
        windowfirst.setLocationRelativeTo(null);
		windowfirst.setVisible(true);
		windowfirst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
