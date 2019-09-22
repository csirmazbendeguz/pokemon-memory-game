package net.csirmazbendeguz.memory_game.swing.panels;

import com.google.inject.Inject;
import net.csirmazbendeguz.memory_game.swing.labels.TimeLabel;
import net.csirmazbendeguz.memory_game.swing.labels.TriesLabel;

import javax.swing.*;
import java.awt.*;

/**
 * Head-up display to show the current game's statistics.
 */
public class HUD extends JPanel {

    @Inject
    public HUD(TimeLabel timeLabel, TriesLabel triesLabel) {
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(timeLabel);
        add(Box.createRigidArea(new Dimension(0, 3)));
        add(triesLabel);
    }

}
