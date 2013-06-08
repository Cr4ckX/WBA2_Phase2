package GUI;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.awt.event.*;


/*Von javax.swing abgeleitet*/
public class firstChoice extends JFrame{
	
	private JButton btnSportgruppen, btnVeranstalter, btnOrte;
	private JLabel labelAnweisung; 
	
	
	
	
	firstChoice(){
		
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
		JLabel labelAnweisung = new JLabel ("Bitte wählen Sie eine Kategorie aus!!", JLabel.CENTER);
		
		
		
		/***************************************************************/
		/*****************************Button****************************/
		/***************************************************************/
		
		
		JButton btnVeranstalter = new JButton("Veranstalter");
		JButton btnSportgruppen = new JButton("Sportgruppen");
		JButton btnOrte = new JButton ("Orte");
		
		btnVeranstalter.setSize(100, 25);
	
		/***************************************************************/
		/*******************Einfuegen in die Pane***********************/
		/***************************************************************/	
		/*Einfuegen in die Pane*/
		this.getContentPane().add(labelAnweisung);
		this.getContentPane().add(btnVeranstalter);
		this.getContentPane().add(btnSportgruppen);
		this.getContentPane().add(btnOrte);
		
//		btnAuswahl.addActionListener( new meinListener());
	
		
		//Fenster schließen
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	}
	
	/***************************************************************/
	/**************************Actions******************************/
	/***************************************************************/
	
class meinListener implements ActionListener{
		
		public void actionPerformed ( ActionEvent e){
			
			
			
		}
		
		
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		firstChoice fenster = new firstChoice();
		
		/**/
		fenster.setSize(700,300);
		fenster.setLocation(200, 100);
		//Fenster sichtbar machen
		fenster.setVisible(true);
		
		
	}

}
