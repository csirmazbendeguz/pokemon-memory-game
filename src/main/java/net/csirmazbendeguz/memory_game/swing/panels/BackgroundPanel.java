package net.csirmazbendeguz.memory_game.swing.panels;

import com.google.inject.Inject;
import net.csirmazbendeguz.memory_game.util.ResourceLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * Container for the game's background image.
 */
public class BackgroundPanel extends JPanel {

    /**
     * The background image.
     */
    private BufferedImage image;

    @Inject
    public BackgroundPanel(ResourceLoader resourceLoader) {
        image = resourceLoader.loadBackogroundImage("Background.jpg");
    }

    /**
     * Draw the background image.
     */
    @Override
    public void paintComponent(Graphics g) {
        Container parent = getParent();
        g.drawImage(image, 0, 0, parent.getWidth(), parent.getHeight(), null);
    }

}
