package net.csirmazbendeguz.memory_game.swing.labels;

import net.csirmazbendeguz.memory_game.game_state.TriesCounter;

import java.awt.Color;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;

public class TriesLabel extends JLabel implements Observer {

    public TriesLabel() {
        this.setBounds(950, 72, 100, 50);
        this.setForeground(Color.BLACK);
        this.setFont(new Font("Serif", Font.PLAIN, 25));
        TriesCounter.getInstance().addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        int tries = (int) o;
        this.setText("Tries: " + tries);
        this.repaint();
    }

}
