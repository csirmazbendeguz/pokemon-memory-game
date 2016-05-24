package memorygame;

import java.awt.Color;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;

public class Time extends JLabel {
    
    private Timer timer;
    private long totalSecs = 0;
    
    public Time() {
        this.setText();
        this.setForeground(Color.BLACK);
        this.setFont(new Font("Serif", Font.PLAIN, 25));
        this.setBounds(950, 16, 100, 50);
        
        start();
    }
    
    private void start() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                totalSecs++;
                setText();
            }
        }, 0, 1000);
    }
    
    private void setText() {
        long hours = totalSecs / 3600;
        long minutes = (totalSecs % 3600) / 60;
        long seconds = totalSecs % 60;

        this.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
        this.repaint();
    }
    
    public void end() {
        if(timer!=null) timer.cancel();
        timer = null;
    }
    
    public void reset() {
        end();
        totalSecs = 0;
        setText();
        start();
    }
    
    
}
