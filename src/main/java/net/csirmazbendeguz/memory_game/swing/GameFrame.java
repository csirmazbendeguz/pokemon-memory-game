package net.csirmazbendeguz.memory_game.swing;

import com.google.inject.Inject;
import net.csirmazbendeguz.memory_game.swing.panels.BackgroundPanel;
import net.csirmazbendeguz.memory_game.swing.panels.BoardPanel;
import net.csirmazbendeguz.memory_game.swing.label.Labels;
import net.csirmazbendeguz.memory_game.swing.panels.WinGlassPane;
import net.csirmazbendeguz.memory_game.swing.buttons.ChangeDimensionButton;
import net.csirmazbendeguz.memory_game.swing.buttons.RestartButton;

import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {

    @Inject
    public GameFrame(BoardPanel boardPanel, WinGlassPane winGlassPane, BackgroundPanel bg, ChangeDimensionButton changeDimensionButton, RestartButton restartButton, Labels labels) {
        super();
        setSize(1100, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Pokémon Memory Game");
        setBackground(null);
        setResizable(false);

        // Disable keyboard input on focused components.
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> true);

        setGlassPane(winGlassPane);
        getContentPane().add(bg);
        bg.setLayout(null);
        bg.add(winGlassPane);
        bg.add(boardPanel);
        bg.add(changeDimensionButton);
        bg.add(restartButton);
        bg.add(labels);

        validate();
        setVisible(true);
    }

}
