package net.csirmazbendeguz.memory_game.swing.labels;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * Label to show the win screen title.
 */
@Singleton
public class YouWinLabel extends JLabel {

    @Inject
    public YouWinLabel(@Named("youWin") BufferedImage youWin) {
        super(new ImageIcon(youWin));
    }

}
