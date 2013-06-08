package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.awt.event.*;


/*Von javax.swing abgeleitet*/
public class radio extends JFrame{
	
	private JButton btnAuswahl;
	private JRadioButton rbInteressent, rbVeranstalter;
	private JLabel labelAnweisung; 
	private ButtonGroup rbtngroup;
	
	
	
	radio(){
		//Konstruktor der Elternklasse aufrufen
		super();
	
		//Titel setzen (Vom Fenster)
		this.setTitle("Herzlich Willkommen");
		
		/***************************************************************/
		/*****************************Layout****************************/
		/***************************************************************/
										//7 Zeilen, 0 Spalten
		getContentPane().setLayout(new GridLayout(7,0,10,10));
//		this.setLayout(null);
//		labelAnweisung.setBounds(20, 20, 100, 20);
		
		
		
		/***************************************************************/
		/*****************************Beschriftung**********************/
		/***************************************************************/
		final JLabel labelAnweisung = new JLabel ("Bitte wählen Sie eine Rolle aus!", JLabel.CENTER);
		
		
		
		/***************************************************************/
		/*****************************Button****************************/
		/***************************************************************/
		//Erstellen der RadioButtons
		final JRadioButton rbInteressent = new JRadioButton( "Interessent" );
		final JRadioButton rbVeranstalter = new JRadioButton( "Veranstalter" );
		
		rbtngroup = new ButtonGroup ();
		rbtngroup.add(rbVeranstalter);
		rbtngroup.add(rbInteressent);
		
		//AuswahlBtn - Definieren, Größe festlegen
		JButton btnAuswahl = new JButton("Auswahl");
		btnAuswahl.setSize(100, 25);
	
		rbInteressent.setSelected( true );
	
		/***************************************************************/
		/*******************Einfuegen in die Pane***********************/
		/***************************************************************/	
		/*Einfuegen in die Pane*/
		this.getContentPane().add(labelAnweisung);
		this.getContentPane().add(rbInteressent);
		this.getContentPane().add(rbVeranstalter);
		this.getContentPane().add(btnAuswahl);
		
		
	
	
		/***************************************************************/
		/**************************Actions******************************/
		/***************************************************************/
	
		btnAuswahl.addActionListener(new ActionListener() 
		{
	        public void actionPerformed(ActionEvent e)
	        {
	            labelAnweisung.setVisible(false);
	            rbInteressent.setVisible(false);
	            rbVeranstalter.setVisible(false);
	        }
	    });
	
	}
	
	

	public static void main(String[] args) {
		radio fenster = new radio();
		fenster.setSize(1200,700);
		fenster.setLocation(200, 100);
		//Fenster sichtbar machen
		fenster.setVisible(true);
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
