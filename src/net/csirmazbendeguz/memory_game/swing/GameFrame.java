package net.csirmazbendeguz.memory_game.swing;

import net.csirmazbendeguz.memory_game.ImageLoader;
import net.csirmazbendeguz.memory_game.swing.labels.Time;
import net.csirmazbendeguz.memory_game.swing.labels.Tries;
import net.csirmazbendeguz.memory_game.swing.panels.Win;
import net.csirmazbendeguz.memory_game.swing.buttons.IncButton;
import net.csirmazbendeguz.memory_game.swing.buttons.RestartButton;
import net.csirmazbendeguz.memory_game.swing.panels.Background;
import net.csirmazbendeguz.memory_game.swing.panels.GamePanel;

import java.awt.Container;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class GameFrame extends JFrame {

    private Container container = getContentPane();
    private Background bg;
    private GamePanel gamePanel;
    private IncButton incButton;
    private RestartButton restartButton;
    public Time time;
    public Tries tries;
    public Win win;

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

        BufferedImage backgroundImage = ImageLoader.getInstance().loadBackogroundImage("Background.jpg");
        bg = new Background(backgroundImage);
        bg.setLayout(null);
        container.add(bg);

        win = new Win(this.getGlassPane());
        bg.add(win);

        gamePanel = new GamePanel();
        bg.add(gamePanel);

        incButton = new IncButton(gamePanel);
        bg.add(incButton);

        restartButton = new RestartButton(gamePanel);
        bg.add(restartButton);

        time = new Time();
        bg.add(time);

        tries = new Tries();
        bg.add(tries);

        this.validate();
        this.setVisible(true);
    }

}
