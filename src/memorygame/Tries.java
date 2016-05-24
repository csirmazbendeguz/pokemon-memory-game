package memorygame;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class Tries extends JLabel {
    
    private int count = 0;
    
    public Tries() {
        this.setBounds(950, 72, 100, 50);
        this.setText();
        this.setForeground(Color.BLACK);
        this.setFont(new Font("Serif", Font.PLAIN, 25));
    }
    
    public void reset() {
        count = 0;
        this.setText();
        this.repaint();
    }
    
    private void setText() {
        this.setText("Tries: "+count);
    }
    
    public void add() {
        count++;
        this.setText();
        this.repaint();
    }
    
}
