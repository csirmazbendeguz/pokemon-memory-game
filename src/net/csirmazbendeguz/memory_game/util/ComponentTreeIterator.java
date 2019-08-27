package net.csirmazbendeguz.memory_game.util;

import java.awt.*;
import java.util.*;

/**
 * Iterator for component trees.
 *
 * Traverses a component tree BFS style.
 */
public class ComponentTreeIterator implements Iterator<Component> {

    /**
     * The components to iterate.
     */
    private Queue<Component> queue = new ArrayDeque<>();

    /**
     * Construct a new iterator for iterating over a component tree.
     *
     * @param root The component tree's root.
     */
    public ComponentTreeIterator(Component root) {
        queue.offer(root);
    }

    /**
     * Decide whether there are any components available for iteration.
     */
    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    /**
     * Compute the next component of the tree.
     */
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
