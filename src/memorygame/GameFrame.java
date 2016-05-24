package memorygame;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class GameFrame extends JFrame {
    
    private Container cp = getContentPane();
    private Background backGround; // fohatter
    private GamePanel gp; // kartyak
    private IncButton ib; // meret novelese
    private RestartButton rb;
    Time t;
    Tries tr;
    Win w;
    
    public GameFrame() {
        super();
        
        this.setSize(1100, 900);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Pokemon Memory Game");
        this.setBackground(null);
        this.setResizable(false);
        
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher(){
            @Override
            public boolean dispatchKeyEvent(KeyEvent e){
              return true;
            }
        });
        
    ///////////////////////
        
        backGround = new Background("img/bg/Background.jpg");
        backGround.setLayout(null);
        cp.add(backGround);
        
        w = new Win(this.getGlassPane());
        backGround.add(w);
        
        gp = new GamePanel();
        backGround.add(gp);
        
        ib = new IncButton(gp);
        backGround.add(ib);
        
        rb = new RestartButton(gp);
        backGround.add(rb);
        
        t = new Time();
        backGround.add(t);
        
        tr = new Tries();
        backGround.add(tr);
        
        this.validate();
        this.setVisible(true);
    }
    
}
