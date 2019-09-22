package net.csirmazbendeguz.memory_game.swing.label;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * Label to show the game's title.
 */
public class TitleLabel extends JLabel {

    @Inject
    public TitleLabel(@Named("title") BufferedImage title) {
        super(new ImageIcon(title));
    }

}
