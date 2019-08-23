package net.csirmazbendeguz.memory_game.swing.labels;

import net.csirmazbendeguz.memory_game.services.TimerService;

import java.awt.Color;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;

public class TimeLabel extends JLabel implements Observer {

    public TimeLabel() {
        this.setForeground(Color.BLACK);
        this.setFont(new Font("Serif", Font.PLAIN, 25));
        this.setBounds(940, 18, 150, 50);
        TimerService.getInstance().addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        long totalSeconds = (long) o;

        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;

        this.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
        this.repaint();
    }

}
