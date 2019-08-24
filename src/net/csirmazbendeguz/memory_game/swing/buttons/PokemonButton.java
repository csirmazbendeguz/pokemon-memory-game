package net.csirmazbendeguz.memory_game.swing.buttons;

import net.csirmazbendeguz.memory_game.utils.ResourceLoader;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;

/**
 * Base class for buttons.
 */
public class PokemonButton extends JButton implements MouseListener {

    /**
     * The button images.
     */
    private static final BufferedImage NORMAL, HOVER, CLICK;

    static {
        ResourceLoader resourceLoader = ResourceLoader.getInstance();
        NORMAL = resourceLoader.loadBackogroundImage("Button.png");
        HOVER = resourceLoader.loadBackogroundImage("ButtonHover.png");
        CLICK = resourceLoader.loadBackogroundImage("ButtonClick.png");
    }

    /**
     * The button width.
     */
    static final int WIDTH = 170;

    /**
     * The button height.
     */
    static final int HEIGHT = 50;

    /**
     * The image to render.
     */
    private BufferedImage image = NORMAL;

    public PokemonButton(String text) {
        super(text);
        this.setOpaque(false);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
        // Horizontally center the text.
        int x = this.getWidth() / 2 - g2d.getFontMetrics().stringWidth(getText()) / 2;
        g2d.drawString(getText(), x, 28);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        image = HOVER;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        image = CLICK;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        image = NORMAL;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        image = HOVER;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        image = NORMAL;
    }

}
