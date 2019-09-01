package net.csirmazbendeguz.memory_game.swing.buttons;

import net.csirmazbendeguz.memory_game.util.ResourceLoader;

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
    private BufferedImage normal, hover, click;

    /**
     * The button dimensions.
     */
    static final int WIDTH = 170, HEIGHT = 50;

    /**
     * The image to render.
     */
    private BufferedImage image;

    public PokemonButton(ResourceLoader resourceLoader) {
        super();
        setOpaque(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        addMouseListener(this);
        normal = resourceLoader.loadBackogroundImage("Button.png");
        hover = resourceLoader.loadBackogroundImage("ButtonHover.png");
        click = resourceLoader.loadBackogroundImage("ButtonClick.png");
        image = normal;
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
        image = hover;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        image = click;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        image = normal;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        image = hover;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        image = normal;
    }

}
