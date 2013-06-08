package GUI;

import javax.swing.*;
 
public class kp {
 
        public static void main(String[] args) {
                JFrame fenster = new JFrame("Herzlich Willkommen");
                fenster.setSize(700, 700);
                fenster.setLocationRelativeTo(null);
                fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                
                
 
                JTabbedPane tabLeiste = new JTabbedPane();
                JPanel panel = new JPanel();
                panel.add(new JButton("Button des ersten Tabs"));
                tabLeiste.addTab("Tab1", panel);
 
                JPanel panel2 = new JPanel();
                panel2.add(new JButton("Button des zweiten Tabs"));
                tabLeiste.addTab("Tab2", panel2);
 
                fenster.add(tabLeiste);
                fenster.setVisible(true);
        }
 
}