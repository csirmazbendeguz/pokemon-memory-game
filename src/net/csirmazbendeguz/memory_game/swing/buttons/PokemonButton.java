package net.csirmazbendeguz.memory_game.swing.buttons;

import net.csirmazbendeguz.memory_game.services.ResourceLoader;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;

public class PokemonButton extends JButton implements MouseListener {
    
    private String str;
    private BufferedImage image, normal, hover, click;
    public Dimension size = new Dimension(170, 50);
    
    
    public PokemonButton(String str) {
        super(str);
        this.setOpaque(false);
        this.str = str;

        ResourceLoader resourceLoader = ResourceLoader.getInstance();
        normal = resourceLoader.loadBackogroundImage("Button.png");
        hover = resourceLoader.loadBackogroundImage("ButtonHover.png");
        click = resourceLoader.loadBackogroundImage("ButtonClick.png");
        image = normal;

        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.addMouseListener(this);
        
    }
    
    public void setString(String str) {
        this.str = str;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
        g2d.drawString(str, this.getWidth()/2-g2d.getFontMetrics().stringWidth(str)/2, 28);
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
