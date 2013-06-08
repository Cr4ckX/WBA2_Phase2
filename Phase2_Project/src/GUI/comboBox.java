package GUI;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.awt.event.*;


/*Von javax.swing abgeleitet*/
public class comboBox extends JFrame{
	
	private JButton btnAuswahl;
	private JRadioButton rbInteressent, rbVeranstalter;
	private JLabel labelAnweisung; 
	private ButtonGroup rbtngroup;
	private JTabbedPane tabLeiste;
	
	
	
	comboBox(){
		
		//Konstruktor der Elternklasse aufrufen
		super();
	
		//Titel setzen (Vom Fenster)
		this.setTitle("Herzlich Willkommen");
		
		/***************************************************************/
		/*****************************Layout****************************/
		/***************************************************************/
										//7 Zeilen, 0 Spalten
		getContentPane().setLayout(new GridLayout(7,0,10,10));

		/***************************************************************/
		/*****************************Beschriftung**********************/
		/***************************************************************/
		JLabel labelAnweisung = new JLabel ("Bitte wählen Sie eine Rolle aus!", JLabel.CENTER);
		
		
		
		/***************************************************************/
		/***********************Radio-Button****************************/
		/***************************************************************/
		JTabbedPane tabLeiste = new JTabbedPane();
		
        JPanel panel = new JPanel();
        tabLeiste.addTab("Tab1", panel);

        JPanel panel2 = new JPanel();
        tabLeiste.addTab("Tab2", panel2);


	
		/***************************************************************/
		/*******************Einfuegen in die Pane***********************/
		/***************************************************************/	
		/*Einfuegen in die Pane*/
		this.getContentPane().add(labelAnweisung);
		this.getContentPane().add(rbInteressent);
		this.getContentPane().add(rbVeranstalter);
		this.getContentPane().add(btnAuswahl);
		this.getContentPane().add(tabLeiste);
		
//		btnAuswahl.addActionListener( new meinListener());
	
		
		//Fenster schließen
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	}
	
	/***************************************************************/
	/**************************Actions******************************/
	/***************************************************************/
	
//	class meinListener implements ActionListener{
//		
//		public void actionPerformed ( ActionEvent e){
//			
//			
//			
//		}
//		
//		
//		
//	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		comboBox fenster = new comboBox();
		
		/**/
		fenster.setSize(700,300);
		fenster.setLocation(200, 100);
		//Fenster sichtbar machen
		fenster.setVisible(true);
		
		
	}

}
