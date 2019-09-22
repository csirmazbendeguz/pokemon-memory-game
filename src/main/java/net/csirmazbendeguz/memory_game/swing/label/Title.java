package net.csirmazbendeguz.memory_game.swing.label;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Title extends BaseLabel {

    @Inject
    public Title(@Named("title") BufferedImage title) {
        super(title);
        setPreferredSize(new Dimension(title.getWidth(), title.getHeight()));
    }

}
