package net.csirmazbendeguz.memory_game.swing.buttons;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;

/**
 * Base class for the game's buttons.
 */
abstract class BaseButton extends JButton implements MouseListener {

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

    /**
     * Initialize the button.
     *
     * @param normal The default image.
     * @param hover The image to display on hover.
     * @param click The image to display on click.
     */
    BaseButton(BufferedImage normal, BufferedImage hover, BufferedImage click) {
        super();
        setOpaque(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        addMouseListener(this);
        this.normal = normal;
        this.hover = hover;
        this.click = click;
        image = normal;
    }

    /**
     * Draw the button.
     */
    @Override
    public void paintComponent(Graphics graphics) {
        graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        // Horizontally center the text.
        int x = this.getWidth() / 2 - graphics.getFontMetrics().stringWidth(getText()) / 2;
        graphics.drawString(getText(), x, 28);
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
