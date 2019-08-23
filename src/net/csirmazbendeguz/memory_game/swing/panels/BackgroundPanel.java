package net.csirmazbendeguz.memory_game.swing.panels;

import net.csirmazbendeguz.memory_game.services.ResourceLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {

    /**
     * The background image.
     */
    private BufferedImage image;

    public BackgroundPanel() {
        this.image = ResourceLoader.getInstance().loadBackogroundImage("Background.jpg");
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
