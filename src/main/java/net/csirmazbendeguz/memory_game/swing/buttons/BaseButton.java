package net.csirmazbendeguz.memory_game.swing.buttons;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;

/**
 * Base class for the game's buttons.
 */
abstract class BaseButton extends JButton implements MouseListener {

    /**
     * The button dimension.
     */
    private static final Dimension SIZE = new Dimension(170, 50);

    /**
     * The button images.
     */
    private BufferedImage normal, hover, click;

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
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        // Center the text.
        FontMetrics metrics = graphics2D.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(getText())) / 2;
        int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
        graphics2D.drawString(getText(), x, y);
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

    @Override
    public Dimension getMinimumSize() {
        return SIZE;
    }

    @Override
    public Dimension getPreferredSize() {
        return SIZE;
    }

    @Override
    public Dimension getMaximumSize() {
        return SIZE;
    }

}
