package net.csirmazbendeguz.memory_game.swing.labels;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import net.csirmazbendeguz.memory_game.game_state.Stopwatch;

import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

/**
 * Label showing the time spent.
 */
public class TimeLabel extends BaseLabel implements Observer {

    @Inject
    public TimeLabel(@Named("labelBackground") BufferedImage background, Stopwatch stopwatch) {
        super(background);
        this.setBounds(940, 18, WIDTH, HEIGHT);
        stopwatch.addObserver(this);
    }

    /**
     * Update the label's text.
     */
    @Override
    public void update(Observable observable, Object o) {
        int totalSeconds = (int) o;

        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;

        setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }

}
