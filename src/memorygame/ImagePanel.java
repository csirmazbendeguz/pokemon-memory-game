package memorygame;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ImagePanel extends JPanel implements MouseListener{

    private BufferedImage image;
    private String imgName;
    public static Dimension size = new Dimension(100, 100);
    private boolean showFront = false;
    public static BufferedImage back; // kartya hatulja
    private boolean inanim = false;
    private boolean isVisible = true;
    
    public ImagePanel(String imgName) {
        this.imgName = imgName;
        
        try {                
            image = ImageIO.read(new File("img/cards/"+imgName));
            back = ImageIO.read(new File("img/bg/CardBack.png"));
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        
        this.setPreferredSize(size);
        this.setMinimumSize(size);
        this.setMaximumSize(size);
        
        this.addMouseListener(this);
    }
    
    private void refresh() {
        JFrame f = (JFrame) SwingUtilities.getWindowAncestor(this);
        f.repaint();
        f.revalidate();
    }
    
    public boolean getShowFront() {
        return showFront;
    }
    
    public String getImgName() {
        return imgName;
    }
    
    // ha part talal
    public void clearCardAnim() {
        showFront = false;
        inanim = true;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                isVisible = false;
                inanim = false;
                refresh();
            }
        }, 750);
    }
    
    // ha nem talal
    public void changeBackAnim() {
        showFront = false;
        inanim = true;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                inanim = false;
                refresh();
            }
        }, 750);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        if(isVisible) g.drawImage((showFront||inanim) ? image : ImagePanel.back, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    @Override
    public void mouseClicked(MouseEvent me) {}
    
    @Override
    public void mousePressed(MouseEvent me) {
        if(isVisible && !showFront && !inanim) {
            showFront = !showFront;
            refresh();
            
            GamePanel gp = (GamePanel) getParent();
            gp.checkPairs();
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {}

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) {}

}