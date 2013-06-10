package GUI;

// Import-Anweisung f�r unseren JFrame
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
// Import-Anweisung f�r unser JLabel
import javax.swing.JLabel;
 
public class konstru
{
     public static void main(String[] args)
    {
        /* Erzeugung eines neuen Frames mit dem 
           Titel "Mein JFrame Beispiel" */             
        JFrame window = new JFrame("Mein JFrame Beispiel");
        /* Wir setzen die Breite und die H�he 
           unseres Fensters auf 200 Pixel */          
        window.setSize(1000,600);
        
        
        final JButton btnAuswahl, btnZurueck;
        final JRadioButton rbtnI, rbtnV;
        final JLabel labelAnweisung, loginI, loginV, labelOK, labelUKSG, labelUUK; 
        final ButtonGroup rbtngroup;
        final JComboBox dropdownOK, dropdownUK, dropdownUUK;
       
        
        /***************************************************************/
		/*****************************Beschriftung**********************/
		/***************************************************************/
		labelAnweisung = new JLabel ("Bitte w�hlen Sie eine Rolle aus!", JLabel.CENTER);
		loginI = new JLabel("Eingeloggt als: Interessent");
		loginV = new JLabel ("Eingeloggt als: Veranstalter");
		labelOK = new JLabel("Bitte w�hlen Sie eine Kategorie!");
		labelUKSG = new JLabel("Bitte w�hlen Sie einen Sportart!");
		labelUUK = new JLabel("Bitte w�hlen Sie einen Ort!");
        
     // Wir erstellen ein JLabel mit einem Text und unserem Icon
        // Die horizontale Ausrichtung setzen wir auf "CENTER"
        JLabel label = new JLabel ("My 127.0.0.1 is my castle", 
            JLabel.CENTER);
 
        // Die vertikale Ausrichtung des JLabels setzen wir auf "TOP"
        label.setVerticalAlignment(JLabel.TOP);
 
        // Die relative Ausrichtung des Textes zum Icon setzen wir auf "LEFT"
        label.setHorizontalTextPosition(JLabel.LEFT);
 
        // Wir f�gen das JLabel unserem Dialog hinzu
        window.add(label);
        window.add(loginI);
        window.add(loginV);
        window.getContentPane().add(label);
        window.getContentPane().add(labelAnweisung);

 
     // Wir lassen unseren Frame anzeigen
        window.setVisible(true);
    }
}