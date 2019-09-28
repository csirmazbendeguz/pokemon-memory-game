package net.csirmazbendeguz.memory_game.swing;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public interface DefaultMouseListener extends MouseListener {

    @Override
    default void mouseClicked(MouseEvent mouseEvent) {}

    @Override
    default void mousePressed(MouseEvent mouseEvent) {}

    @Override
    default void mouseReleased(MouseEvent mouseEvent) {}

    @Override
    default void mouseEntered(MouseEvent mouseEvent) {}

    @Override
    default void mouseExited(MouseEvent mouseEvent) {}

}
