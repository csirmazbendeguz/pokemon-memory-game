package net.csirmazbendeguz.memory_game.swing.button;

import com.google.inject.Inject;

import javax.swing.*;
import java.awt.*;

public class Buttons extends JPanel {

    private static final Dimension BUTTON_SIZE = new Dimension(170, 50);

    @Inject
    Buttons(RestartButton restartButton, ChangeDimensionButton changeDimensionButton) {
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        add(changeDimensionButton);
        add(Box.createRigidArea(new Dimension(30, 0)));
        add(restartButton);

        changeDimensionButton.setMaximumSize(BUTTON_SIZE);
        restartButton.setMaximumSize(BUTTON_SIZE);
        setBounds(50, 775, BUTTON_SIZE.width * 2 + 10, BUTTON_SIZE.height);
    }

}
