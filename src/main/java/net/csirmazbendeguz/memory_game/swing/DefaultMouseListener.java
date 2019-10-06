package net.csirmazbendeguz.memory_game.swing;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public interface DefaultMouseListener extends MouseListener {

    @Override
    default void mouseClicked(MouseEvent mouseEvent) {
        mouseEvent.consume();
    }

    @Override
    default void mousePressed(MouseEvent mouseEvent) {
        mouseEvent.consume();
    }

    @Override
    default void mouseReleased(MouseEvent mouseEvent) {
        mouseEvent.consume();
    }

    @Override
    default void mouseEntered(MouseEvent mouseEvent) {
        mouseEvent.consume();
    }

    @Override
    default void mouseExited(MouseEvent mouseEvent) {
        mouseEvent.consume();
    }

}
