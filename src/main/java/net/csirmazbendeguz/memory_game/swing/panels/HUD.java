package net.csirmazbendeguz.memory_game.swing.panels;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.csirmazbendeguz.memory_game.swing.labels.hud.TimeLabel;
import net.csirmazbendeguz.memory_game.swing.labels.hud.TriesLabel;

import javax.swing.*;
import java.awt.*;

/**
 * Head-up display to show the current game's statistics.
 */
@Singleton
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
