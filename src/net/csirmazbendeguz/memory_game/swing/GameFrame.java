package net.csirmazbendeguz.memory_game.swing;

import net.csirmazbendeguz.memory_game.game_state.Stopwatch;
import net.csirmazbendeguz.memory_game.game_state.TriesCounter;
import net.csirmazbendeguz.memory_game.swing.labels.TimeLabel;
import net.csirmazbendeguz.memory_game.swing.labels.TriesLabel;
import net.csirmazbendeguz.memory_game.swing.panels.BackgroundPanel;
import net.csirmazbendeguz.memory_game.swing.panels.Win;
import net.csirmazbendeguz.memory_game.swing.buttons.IncButton;
import net.csirmazbendeguz.memory_game.swing.buttons.RestartButton;
import net.csirmazbendeguz.memory_game.swing.panels.GamePanel;

import java.awt.Container;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class GameFrame extends JFrame {

    public Win win;

    public GameFrame() {
        super();

        this.setSize(1100, 900);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Pokémon Memory Game");
        this.setBackground(null);
        this.setResizable(false);

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher(){
            @Override
            public boolean dispatchKeyEvent(KeyEvent e){
              return true;
            }
        });

        Container container = getContentPane();
        BackgroundPanel bg = new BackgroundPanel();
        bg.setLayout(null);
        container.add(bg);

        win = new Win(this.getGlassPane());
        bg.add(win);

        GamePanel gamePanel = new GamePanel();
        bg.add(gamePanel);

        IncButton incButton = new IncButton(gamePanel);
        bg.add(incButton);

        RestartButton restartButton = new RestartButton(gamePanel);
        bg.add(restartButton);

        TimeLabel timeLabel = new TimeLabel();
        bg.add(timeLabel);

        TriesLabel triesLabel = new TriesLabel();
        bg.add(triesLabel);

        this.validate();
        this.setVisible(true);
        Stopwatch.getInstance().resetSeconds();
        TriesCounter.getInstance().reset();
    }

}
