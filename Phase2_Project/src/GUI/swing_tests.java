package GUI;
import javax.swing.*;
import java.awt.*;


/*Von javax.swing abgeleitet*/
public class swing_tests extends JFrame{
	
	swing_tests(){
		
		//Konstruktor der Elternklasse aufrufen
		super();
		//Titel setzen (Vom Fenster)
		
		this.setTitle("Sportverzeichnis WBA");
		
		//Layouter setzen
		this.getContentPane().setLayout( new GridLayout(2,1));
		
		//Erstellen eines Labels und Platizierung in der Mitte
		JLabel label = new JLabel("Willkommen", JLabel.CENTER);
		JButton button = new JButton ("Klick mich");
		
		//Kontainer hinzufügen
		this.getContentPane().add( label);
		this.getContentPane().add( button );
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
