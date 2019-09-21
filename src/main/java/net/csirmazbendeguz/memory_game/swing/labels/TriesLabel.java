package net.csirmazbendeguz.memory_game.swing.labels;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import net.csirmazbendeguz.memory_game.game_state.TriesCounter;

import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

public class TriesLabel extends PokemonLabel implements Observer {

    @Inject
    public TriesLabel(@Named("labelBackground") BufferedImage background, TriesCounter triesCounter) {
        super(background);
        setBounds(940, 72, WIDTH, HEIGHT);
        triesCounter.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object tries) {
        setText("Tries: " + tries);
        repaint();
    }

}
