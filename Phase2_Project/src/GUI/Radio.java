package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.awt.event.*;


/*Von javax.swing abgeleitet*/
public class Radio extends JFrame{
	
	private JButton btnAuswahl, btnZurueck;
	private JRadioButton rbtnI, rbtnV;
	private JLabel labelAnweisung, loginI, loginV, labelOK, labelUKSG, labelUKSA, labelUUK; 
	private ButtonGroup rbtngroup;
	private JComboBox dropdownOK, dropdownUK, dropdownUUK;
	private TextArea TextArea;
	
	
	
	
	Radio(){
		//Konstruktor der Elternklasse aufrufen
		super();
		
	
		//Titel setzen (Vom Fenster)
		this.setTitle("Herzlich Willkommen");
		
		/***************************************************************/
		/*****************************Layout****************************/
		/***************************************************************/
		
		this.setLayout(null);
		
		
		/***************************************************************/
		/*****************************Beschriftung**********************/
		/***************************************************************/
		labelAnweisung = new JLabel ("Bitte wählen Sie eine Rolle aus!", JLabel.CENTER);
		loginI = new JLabel("Eingeloggt als: Interessent");
		loginV = new JLabel ("Eingeloggt als: Veranstalter");
		labelOK = new JLabel("Bitte wählen Sie eine Kategorie!");
		labelUKSG = new JLabel("Bitte wählen Sie einen Sportgruppe!");
		labelUKSA = new JLabel("Bitte wählen Sie eine Sportart");
		labelUUK = new JLabel("Bitte wählen Sie einen Ort!");
		
		
		
		//Erstmal nicht anzeigen lassen!
		loginV.setVisible(false);
		loginI.setVisible(false);
		labelOK.setVisible(false);
		labelUKSG.setVisible(false);
		labelUUK.setVisible(false);
		
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
		
		//Auswahl/ZurückButton
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
		
		String[] oberkategorie = new String[] {"DEFAULT", "Sportgruppen", "Veranstalter", "Orte"};
		dropdownOK = new JComboBox(oberkategorie);
		
		String[] Veranstalter = new String[] {"Kampfsport", "Rückschlag", "Schnee", "Soooonenschein"};
		dropdownUK = new JComboBox(Veranstalter);
		
		String[] Orte = new String[] {"Hemmerden", "Niebüll", "Leck", "Grevenbroich", "Hamburg(meinePerle)"};
		dropdownUUK = new JComboBox(Orte);
		
		//Erstmal unsichtbar
		dropdownOK.setVisible(false);
		dropdownUK.setVisible(false);
		dropdownUUK.setVisible(false);
		
		
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
		
		
		
		/***************************************************************/
		/*******************Einfuegen in die Pane***********************/
		/***************************************************************/	
		/*Einfuegen in die Pane*/
		this.getContentPane().add(labelAnweisung);
		this.getContentPane().add(rbtnI);
		this.getContentPane().add(rbtnV);
		this.getContentPane().add(btnAuswahl);
		this.getContentPane().add(btnZurueck);
		this.getContentPane().add(loginI);
		this.getContentPane().add(loginV);
		this.getContentPane().add(dropdownOK);
		this.getContentPane().add(dropdownUK);
		this.getContentPane().add(dropdownUUK);
		this.getContentPane().add(labelOK);
		this.getContentPane().add(labelUKSG);
		this.getContentPane().add(labelUUK);
		
	
	
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
	            
				
			}
			
			
		});
		
		
		dropdownOK.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent dropdownOK) {
				
				
				JComboBox item = (JComboBox) dropdownOK.getSource();
				//0. DEFAULT 1.SG 2.V 3. O
				
				//Ich waehle Sportgruppen aus!
				if(item.getSelectedIndex() == 1){
					System.out.println("Klappt :)");
					labelUKSG.setVisible(true);
					dropdownUK.setVisible(true);
				}
				else if (item.getSelectedIndex() == 2){
					labelUKSG.setVisible(false);
					dropdownUK.setVisible(false);
					
					
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
