package net.csirmazbendeguz.memory_game.swing.labels;

import com.google.inject.Inject;
import net.csirmazbendeguz.memory_game.game_state.Stopwatch;
import net.csirmazbendeguz.memory_game.util.ResourceLoader;

import java.awt.Color;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

public class TimeLabel extends PokemonLabel implements Observer {

    @Inject
    public TimeLabel(ResourceLoader resourceLoader, Stopwatch stopwatch) {
        super(resourceLoader);
        this.setForeground(Color.BLACK);
        this.setFont(new Font("Serif", Font.PLAIN, 25));
        this.setBounds(940, 18, 150, 50);
        stopwatch.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        int totalSeconds = (int) o;

        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;

        this.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
        this.repaint();
    }

}
