package net.csirmazbendeguz.memory_game.swing;

import com.google.inject.Inject;
import net.csirmazbendeguz.memory_game.swing.button.Buttons;
import net.csirmazbendeguz.memory_game.swing.label.Title;
import net.csirmazbendeguz.memory_game.swing.panels.BackgroundPanel;
import net.csirmazbendeguz.memory_game.swing.panels.BoardPanel;
import net.csirmazbendeguz.memory_game.swing.label.Labels;
import net.csirmazbendeguz.memory_game.swing.panels.WinGlassPane;

import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {

    private static final Dimension MINIMUM_SIZE = new Dimension(1100, 900);

    @Inject
    public GameFrame(BoardPanel boardPanel, WinGlassPane winGlassPane, BackgroundPanel bg, Buttons buttons, Title title, Labels labels) {
        super();
        setMinimumSize(MINIMUM_SIZE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Pokémon Memory Game");
        setBackground(null);

        // Disable keyboard input on focused components.
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> true);

        setGlassPane(winGlassPane);
        getContentPane().add(bg);

        SpringLayout layout = new SpringLayout();
        bg.setLayout(layout);

        layout.putConstraint(SpringLayout.NORTH, winGlassPane, 0, SpringLayout.NORTH, bg);
        layout.putConstraint(SpringLayout.SOUTH, winGlassPane, 0, SpringLayout.SOUTH, bg);
        layout.putConstraint(SpringLayout.WEST, winGlassPane, 0, SpringLayout.WEST, bg);
        layout.putConstraint(SpringLayout.EAST, winGlassPane, 0, SpringLayout.EAST, bg);
        bg.add(winGlassPane);

        layout.putConstraint(SpringLayout.NORTH, title, 20, SpringLayout.NORTH, bg);
        layout.putConstraint(SpringLayout.WEST, title, 25, SpringLayout.WEST, bg);
        bg.add(title);

        layout.putConstraint(SpringLayout.VERTICAL_CENTER, boardPanel, 0, SpringLayout.VERTICAL_CENTER, bg);
        layout.putConstraint(SpringLayout.WEST, boardPanel, 25, SpringLayout.WEST, bg);
        boardPanel.setPreferredSize(new Dimension(650, 650));
        boardPanel.setMaximumSize(new Dimension(650, 650));
        boardPanel.setMinimumSize(new Dimension(650, 650));
        bg.add(boardPanel);

        layout.putConstraint(SpringLayout.SOUTH, buttons, -50, SpringLayout.SOUTH, bg);
        layout.putConstraint(SpringLayout.WEST, buttons, 50, SpringLayout.WEST, bg);
        bg.add(buttons);

        layout.putConstraint(SpringLayout.NORTH, labels, 10, SpringLayout.NORTH, bg);
        layout.putConstraint(SpringLayout.EAST, labels, -10, SpringLayout.EAST, bg);
        bg.add(labels);

        validate();
        setVisible(true);
    }

}
