package net.csirmazbendeguz.memory_game.swing;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.csirmazbendeguz.memory_game.swing.panels.GamePanel;
import net.csirmazbendeguz.memory_game.swing.panels.WinGlassPane;

import java.awt.*;
import javax.swing.*;

@Singleton
public class GameFrame extends JFrame {

    /**
     * The frame dimension.
     */
    private static final Dimension SIZE = new Dimension(1100, 900);

    @Inject
    public GameFrame(WinGlassPane winGlassPane, GamePanel gamePanel) {
        super();
        setMinimumSize(SIZE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Pokémon Memory Game");

        // Disable keyboard input on focused components.
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> true);

        setGlassPane(winGlassPane);
        setContentPane(gamePanel);

        validate();
        setVisible(true);
    }

}
