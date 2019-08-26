package net.csirmazbendeguz.memory_game.game_state.event;

import java.awt.*;
import java.util.*;

/**
 * Iterator for component trees.
 */
class ComponentIterator implements Iterator<Component> {

    /**
     * The components to iterate.
     */
    private Queue<Component> queue = new ArrayDeque<>();

    /**
     * Construct a new iterator for iterating over a component tree.
     *
     * @param root The component tree's root.
     */
    ComponentIterator(Component root) {
        queue.offer(root);
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public Component next() {
        Component next = queue.poll();

        if (next instanceof Container) {
            Component[] children = ((Container) next).getComponents();
            Collections.addAll(queue, children);
        }

        return next;
    }

}
