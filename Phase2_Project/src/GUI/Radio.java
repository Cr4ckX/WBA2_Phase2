package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.awt.event.*;


/*Von javax.swing abgeleitet*/
public class Radio extends JFrame{
	
	private JButton btnAuswahl;
	private JRadioButton rbInteressent, rbVeranstalter;
	private JLabel labelAnweisung; 
	private ButtonGroup rbtngroup;
	
	
	
	
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
		final JLabel labelAnweisung = new JLabel ("Bitte wählen Sie eine Rolle aus!", JLabel.CENTER);
		final JLabel loginI = new JLabel("Sie haben sich erfolgreich als Interessent eingeloggt!");
		final JLabel loginV = new JLabel ("Sie haben sich ersolgreich als Veranstalter eingeloggt!");
		labelAnweisung.setBounds(60, 20, 300, 25);
		
		//Erstmal nicht anzeigen lassen!
		loginV.setVisible(false);
		loginI.setVisible(false);
		
		
		/***************************************************************/
		/*****************************Button****************************/
		/***************************************************************/
		//Erstellen der RadioButtons
		final JRadioButton rbtnI = new JRadioButton( "Interessent" );
		final JRadioButton rbtnV = new JRadioButton( "Veranstalter" );
		
		
		
		rbtngroup = new ButtonGroup ();
		rbtngroup.add(rbtnV);
		rbtngroup.add(rbtnI);
		rbtnI.setSelected( true );
		
		//AuswahlBtn - Definieren, Größe festlegen
		final JButton btnAuswahl = new JButton("Auswahl");
		
	
		
		
		
		/***************************************************************/
		/*************************Button-Position***********************/
		/***************************************************************/
	
		rbtnI.setBounds(50, 60, 300, 25);
		rbtnV.setBounds(250, 60, 300, 25);
		btnAuswahl.setBounds(150, 90, 100, 25);
		
		
		/***************************************************************/
		/*******************Einfuegen in die Pane***********************/
		/***************************************************************/	
		/*Einfuegen in die Pane*/
		this.getContentPane().add(labelAnweisung);
		this.getContentPane().add(rbtnI);
		this.getContentPane().add(rbtnV);
		this.getContentPane().add(btnAuswahl);
		this.getContentPane().add(loginI);
		this.getContentPane().add(loginV);
		
	
	
		/***************************************************************/
		/**************************Actions******************************/
		/***************************************************************/
	
		btnAuswahl.addActionListener(new ActionListener() 
		{
	        public void actionPerformed(ActionEvent e)
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
		            loginI.setVisible(true);
		            loginI.setBounds(150, 120, 300, 300);
		            
	        		
	        	}
	        	
	        	
	        	if (rbtnV.isSelected())
	        	{
	        		labelAnweisung.setVisible(false);
		            rbtnI.setVisible(false);
		            rbtnV.setVisible(false);
		            btnAuswahl.setVisible(false);	
		            loginV.setVisible(true);
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
