package net.csirmazbendeguz.memory_game.swing.labels;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * Label to show the background figure.
 */
@Singleton
public class BackgroundFigureLabel extends JLabel {

    @Inject
    public BackgroundFigureLabel(@Named("backgroundFigure") BufferedImage backgroundFigure) {
        super(new ImageIcon(backgroundFigure));
    }

}
