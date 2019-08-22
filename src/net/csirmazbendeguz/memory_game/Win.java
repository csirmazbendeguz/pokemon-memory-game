package net.csirmazbendeguz.memory_game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Win extends JPanel {
    
    private boolean isVisible = false;
    private int size;
    private String time, tries;
    private BufferedImage img;
    private Component gp;
    
    
    public Win(Component glassPane) {
        gp = glassPane;
        gp.setBounds(0, 0, 1100, 900);
        gp.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {e.consume();}

            @Override
            public void mousePressed(MouseEvent e) {hideWin();}

            @Override
            public void mouseReleased(MouseEvent e) {e.consume();}

            @Override
            public void mouseEntered(MouseEvent e) {e.consume();}

            @Override
            public void mouseExited(MouseEvent e) {e.consume();}
        });
        
        this.setBackground(null);
        this.setOpaque(false);
        this.setBounds(0, 0, 1100, 900);

        img = ImageLoader.getInstance().loadBackogroundImage("Win.png");
    }
    
    public void show(int size, String time, String tries) {
        gp.setVisible(true);
        this.size = size;
        this.time = time;
        this.tries = tries;
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                isVisible = true;
                refresh();
//                timer.schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        hideWin();
//                    }
//                }, 3000);
            }
        }, 1000);
    }
    
    private void hideWin() {
        if(isVisible) {
            isVisible = false;
            gp.setVisible(false);
            refresh();
        }
    }
    
    private void refresh() {
        this.repaint();
    }
    
    private void drawStrToWinCenter(Graphics2D g2d, String str, int y) {
        g2d.drawString(str, this.getWidth()/2-g2d.getFontMetrics().stringWidth(str)/2-10, y);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(isVisible) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2d.setColor(Color.WHITE);
            
            g2d.setFont(new Font("Serif", Font.BOLD, 45));
            g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
            
            drawStrToWinCenter(g2d, "Size: "+size+"x"+size, 400);
            drawStrToWinCenter(g2d, "Time: "+time, 500);
            drawStrToWinCenter(g2d, tries, 600);
        }
    }
    
}
