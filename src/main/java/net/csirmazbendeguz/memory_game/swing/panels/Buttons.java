package net.csirmazbendeguz.memory_game.swing.panels;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.csirmazbendeguz.memory_game.swing.buttons.ChangeDimensionButton;
import net.csirmazbendeguz.memory_game.swing.buttons.RestartButton;

import javax.swing.*;
import java.awt.*;

/**
 * Container for the game's buttons.
 */
@Singleton
class Buttons extends JPanel {

    @Inject
    Buttons(RestartButton restartButton, ChangeDimensionButton changeDimensionButton) {
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(changeDimensionButton);
        add(Box.createRigidArea(new Dimension(30, 0)));
        add(restartButton);
    }

}
