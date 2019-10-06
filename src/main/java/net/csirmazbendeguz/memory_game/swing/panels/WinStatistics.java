package net.csirmazbendeguz.memory_game.swing.panels;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.csirmazbendeguz.memory_game.swing.labels.win.SizeLabel;
import net.csirmazbendeguz.memory_game.swing.labels.win.TimeLabel;
import net.csirmazbendeguz.memory_game.swing.labels.win.TriesLabel;

import javax.swing.*;
import java.awt.*;

@Singleton
public class WinStatistics extends JLabel {

    private static final Dimension SIZE = new Dimension(580, 300);

    private static final Color BACKGROUND = new Color(64, 64, 64, 128);

    @Inject
    public WinStatistics(SizeLabel sizeStatLabel, TimeLabel timeStatLabel, TriesLabel triesStatLabel) {
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalGlue());
        sizeStatLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(sizeStatLabel);
        add(Box.createRigidArea(new Dimension(0, 30)));
        timeStatLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(timeStatLabel);
        add(Box.createRigidArea(new Dimension(0, 30)));
        triesStatLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(triesStatLabel);
        add(Box.createVerticalGlue());
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(BACKGROUND);
        graphics2D.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(graphics2D);
    }

    @Override
    public Dimension getPreferredSize() {
        return SIZE;
    }

    @Override
    public Dimension getMaximumSize() {
        return SIZE;
    }

    @Override
    public Dimension getMinimumSize() {
        return SIZE;
    }

}
