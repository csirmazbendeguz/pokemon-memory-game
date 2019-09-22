package net.csirmazbendeguz.memory_game.swing.panels;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import net.csirmazbendeguz.memory_game.swing.labels.BackgroundFigureLabel;
import net.csirmazbendeguz.memory_game.swing.labels.TitleLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Container to display the game's components.
 */
@Singleton
public class GamePanel extends JPanel {

    /**
     * The background image.
     */
    private BufferedImage background;

    @Inject
    public GamePanel(@Named("background") BufferedImage background, WinGlassPane winGlassPane, TitleLabel title, BoardPanel boardPanel, Buttons buttons, HUD hud, BackgroundFigureLabel backgroundFigure) {
        this.background = background;

        SpringLayout layout = new SpringLayout();
        setLayout(layout);

        layout.putConstraint(SpringLayout.NORTH, winGlassPane, 0, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.SOUTH, winGlassPane, 0, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.WEST, winGlassPane, 0, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, winGlassPane, 0, SpringLayout.EAST, this);
        add(winGlassPane);

        layout.putConstraint(SpringLayout.NORTH, title, 20, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, title, 25, SpringLayout.WEST, this);
        add(title);

        layout.putConstraint(SpringLayout.VERTICAL_CENTER, boardPanel, 0, SpringLayout.VERTICAL_CENTER, this);
        layout.putConstraint(SpringLayout.WEST, boardPanel, 25, SpringLayout.WEST, this);
        add(boardPanel);

        layout.putConstraint(SpringLayout.SOUTH, buttons, -50, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.WEST, buttons, 50, SpringLayout.WEST, this);
        add(buttons);

        layout.putConstraint(SpringLayout.NORTH, hud, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.EAST, hud, -10, SpringLayout.EAST, this);
        add(hud);

        layout.putConstraint(SpringLayout.SOUTH, backgroundFigure, -50, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.EAST, backgroundFigure, -20, SpringLayout.EAST, this);
        add(backgroundFigure);
    }

    /**
     * Draw the background image.
     */
    @Override
    public void paintComponent(Graphics graphics) {
        graphics.drawImage(background, 0, 0, getWidth(), getHeight(), null);
    }

}
