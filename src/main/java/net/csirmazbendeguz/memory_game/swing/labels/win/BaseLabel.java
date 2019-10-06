package net.csirmazbendeguz.memory_game.swing.labels.win;

import javax.swing.*;
import java.awt.*;

/**
 * Base class for the win screen's labels.
 */
abstract class BaseLabel extends JLabel {

    BaseLabel() {
        setForeground(Color.WHITE);
        setFont(new Font("Serif", Font.BOLD, 45));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        super.paintComponent(graphics2D);
    }

}
