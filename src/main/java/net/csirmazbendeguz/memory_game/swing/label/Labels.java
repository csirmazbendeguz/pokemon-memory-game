package net.csirmazbendeguz.memory_game.swing.label;

import com.google.inject.Inject;

import javax.swing.*;
import java.awt.*;

/**
 * Container for the labels.
 */
public class Labels extends JPanel {

    private static final Dimension LABEL_SIZE = new Dimension(150, 50);

    private static final Component FILLER = Box.createRigidArea(new Dimension(0, 3));

    @Inject
    public Labels(TimeLabel timeLabel, TriesLabel triesLabel) {
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(timeLabel);
        add(FILLER);
        add(triesLabel);

        timeLabel.setMaximumSize(LABEL_SIZE);
        triesLabel.setMaximumSize(LABEL_SIZE);
        setBounds(940, 10, LABEL_SIZE.width, LABEL_SIZE.height * 2 + FILLER.getHeight());
    }

}
