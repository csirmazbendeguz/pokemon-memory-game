package net.csirmazbendeguz.memory_game.swing.labels;

import net.csirmazbendeguz.memory_game.game_state.TriesCounter;
import net.csirmazbendeguz.memory_game.util.ResourceLoader;

import java.awt.Color;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

public class TriesLabel extends PokemonLabel implements Observer {

    public TriesLabel(ResourceLoader resourceLoader, TriesCounter triesCounter) {
        super(resourceLoader);
        setBounds(940, 72, 150, 50);
        setForeground(Color.BLACK);
        setFont(new Font("Serif", Font.PLAIN, 25));
        triesCounter.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object tries) {
        setText("Tries: " + tries);
        repaint();
    }

}
