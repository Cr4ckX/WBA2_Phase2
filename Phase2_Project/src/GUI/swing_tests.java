package GUI;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;


/*Von javax.swing abgeleitet*/
public class swing_tests extends JFrame{
	
	private JButton button1, button2;
	private JRadioButton radio1, radio2;
	
	
	
	
	swing_tests(){
		
		//Konstruktor der Elternklasse aufrufen
		super();
		
		
		//Titel setzen (Vom Fenster)
		this.setTitle("Sportverzeichnis WBA");
		
		//Layouter setzen
		this.getContentPane().setLayout( new GridLayout(2,1));
		//this.getContentPane().setLayout( null );
		
		//Erstellen eines Labels und Platizierung in der Mitte
		JLabel label = new JLabel("Willkommen", JLabel.CENTER);
		JButton button = new JButton ("Klick mich");
		
		//Button1
		JButton button1 = new JButton ("Test-Klick :)");
		button1.setSize(150, 70);
		button1.setLocation(150, 10);
		
		JButton button2 = new JButton ("Klick lieber mich");
		button2.setSize(130, 40);
		button2.setLocation(10, 10);
		
		//Zufügen zur COntenPane
		this.getContentPane().add ( button1 );
		this.getContentPane().add (button2);
		
		
		
		//Kontainer hinzufügen
		this.getContentPane().add( label);
		this.getContentPane().add( button );
		
		//Fenster schließen
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		button1.addActionListener( new ButtonAction());
		button2.addActionListener(new ButtonAction());
	}

	
	class ButtonAction implements ActionListener{
		
		public void actionPerformed ( ActionEvent e){
			
			if (e.getActionCommand() ==button1.getText()){
				button1.setText("Wow. Du hast dich für mich entschieden");
			}
			
//			if (e.getActionCommand() == button2.getText()){
//				button2.setText("Prima. Richtige Wahl!");
//			}
			
			
			
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		swing_tests fenster = new swing_tests();
		
		
		fenster.setSize(1300,1000);
		fenster.setLocation(200, 100);
		//Fenster sichtbar machen
		fenster.setVisible(true);
	}

}
