package net.csirmazbendeguz.memory_game.swing.panels;

import com.google.inject.Inject;
import net.csirmazbendeguz.memory_game.swing.labels.TimeLabel;
import net.csirmazbendeguz.memory_game.swing.labels.TriesLabel;

import javax.swing.*;
import java.awt.*;

/**
 * Head-up display to show the time spent and the number of tries.
 */
public class HUD extends JPanel {

    private static final Dimension LABEL_SIZE = new Dimension(150, 50);

    private static final Component FILLER = Box.createRigidArea(new Dimension(0, 3));

    @Inject
    public HUD(TimeLabel timeLabel, TriesLabel triesLabel) {
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(timeLabel);
        add(FILLER);
        add(triesLabel);

        timeLabel.setMaximumSize(LABEL_SIZE);
        triesLabel.setMaximumSize(LABEL_SIZE);

        setPreferredSize(new Dimension(LABEL_SIZE.width, LABEL_SIZE.height * 2 + FILLER.getHeight()));
    }

}
