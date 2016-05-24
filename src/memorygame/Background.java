package memorygame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Background extends JPanel {
    private BufferedImage image;
    
    public Background(String imgPath) {
        setImage(imgPath);
    }
    
    public void setImage(String imgPath) {
        try {
            image = ImageIO.read(new File(imgPath));
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, getParent().getWidth(), getParent().getHeight(), null);
    }
}
