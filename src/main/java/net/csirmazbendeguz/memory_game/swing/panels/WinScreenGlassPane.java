package net.csirmazbendeguz.memory_game.swing.panels;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import net.csirmazbendeguz.memory_game.event.listeners.GameEndListener;
import net.csirmazbendeguz.memory_game.event.objects.GameEndEvent;
import net.csirmazbendeguz.memory_game.swing.DefaultMouseListener;
import net.csirmazbendeguz.memory_game.swing.labels.YouWinLabel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

@Singleton
public class WinScreenGlassPane extends JPanel implements GameEndListener, DefaultMouseListener {

    private BufferedImage background;

    /**
     * Whether to render the win screen.
     */
    private boolean shouldRender;

    @Inject
    public WinScreenGlassPane(@Named("winScreen") BufferedImage background, YouWinLabel youWinLabel, WinStatistics winStatistics) {
        this.background = background;
        setOpaque(false);
        addMouseListener(this);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalGlue());
        youWinLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(youWinLabel);
        add(Box.createRigidArea(new Dimension(0, 30)));
        winStatistics.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(winStatistics);
        add(Box.createVerticalGlue());
    }

    /**
     * Show the win screen.
     */
    @Override
    public void gameEnded(GameEndEvent event) {
        // Make the transparent glass pane immediately visible.
        // This will block all the other components from being clicked.
        setVisible(true);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                shouldRender = true;
                repaint();
            }
        }, 750);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        if (shouldRender) {
            graphics.drawImage(background, 0, 0, getWidth(), getHeight(), null);
            super.paintComponent(graphics);
        }
    }

    @Override
    protected void paintChildren(Graphics graphics) {
        if (shouldRender) {
            super.paintChildren(graphics);
        }
    }

    /**
     * Hide the win screen if it's visible.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (shouldRender) {
            shouldRender = false;
            setVisible(false);
        }
    }

}
