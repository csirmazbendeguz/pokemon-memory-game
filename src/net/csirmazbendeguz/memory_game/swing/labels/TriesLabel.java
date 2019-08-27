package net.csirmazbendeguz.memory_game.swing.labels;

import net.csirmazbendeguz.memory_game.game_state.TriesCounter;

import java.awt.Color;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;

public class TriesLabel extends JLabel implements Observer {

    public TriesLabel() {
        setBounds(950, 72, 100, 50);
        setForeground(Color.BLACK);
        setFont(new Font("Serif", Font.PLAIN, 25));
        TriesCounter.getInstance().addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        int tries = (int) o;
        setText("Tries: " + tries);
        repaint();
    }

}
