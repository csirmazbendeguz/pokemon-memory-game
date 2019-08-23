package net.csirmazbendeguz.memory_game.swing.panels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Background extends JPanel {
    private BufferedImage image;
    
    public Background(BufferedImage image) {
        this.image = image;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, getParent().getWidth(), getParent().getHeight(), null);
    }
}
