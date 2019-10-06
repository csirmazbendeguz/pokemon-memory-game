package net.csirmazbendeguz.memory_game.swing;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.csirmazbendeguz.memory_game.swing.panels.GamePanel;
import net.csirmazbendeguz.memory_game.swing.panels.WinScreenGlassPane;

import java.awt.*;
import javax.swing.*;

@Singleton
public class GameFrame extends JFrame {

    private static final Dimension DEFAULT_WINDOW_SIZE = new Dimension(1100, 900);

    @Inject
    public GameFrame(WinScreenGlassPane winScreenGlassPane, GamePanel gamePanel) {
        setMinimumSize(DEFAULT_WINDOW_SIZE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Pokémon Memory Game");

        // Disable keyboard input on focused components.
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> true);

        setGlassPane(winScreenGlassPane);
        setContentPane(gamePanel);

        validate();
        setVisible(true);
    }

}
