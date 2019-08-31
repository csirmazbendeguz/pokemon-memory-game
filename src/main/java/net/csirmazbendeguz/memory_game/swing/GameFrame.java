package net.csirmazbendeguz.memory_game.swing;

import net.csirmazbendeguz.memory_game.swing.labels.TimeLabel;
import net.csirmazbendeguz.memory_game.swing.labels.TriesLabel;
import net.csirmazbendeguz.memory_game.swing.panels.BackgroundPanel;
import net.csirmazbendeguz.memory_game.swing.panels.BoardPanel;
import net.csirmazbendeguz.memory_game.swing.panels.WinGlassPane;
import net.csirmazbendeguz.memory_game.swing.buttons.ChangeDimensionButton;
import net.csirmazbendeguz.memory_game.swing.buttons.RestartButton;
import net.csirmazbendeguz.memory_game.util.ResourceLoader;

import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {

    public GameFrame(ResourceLoader resourceLoader) {
        super();
        setSize(1100, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Pokémon Memory Game");
        setBackground(null);
        setResizable(false);

        // Disable keyboard input on focused components.
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> true);

        BackgroundPanel bg = new BackgroundPanel(resourceLoader);
        WinGlassPane winGlassPane = new WinGlassPane(resourceLoader);
        setGlassPane(winGlassPane);
        BoardPanel boardPanel = new BoardPanel(resourceLoader);
        ChangeDimensionButton changeDimensionButton = new ChangeDimensionButton(resourceLoader);
        RestartButton restartButton = new RestartButton(resourceLoader);
        TimeLabel timeLabel = new TimeLabel();
        TriesLabel triesLabel = new TriesLabel();

        getContentPane().add(bg);
        bg.setLayout(null);
        bg.add(winGlassPane);
        bg.add(boardPanel);
        bg.add(changeDimensionButton);
        bg.add(restartButton);
        bg.add(timeLabel);
        bg.add(triesLabel);

        this.validate();
        this.setVisible(true);
    }

}
