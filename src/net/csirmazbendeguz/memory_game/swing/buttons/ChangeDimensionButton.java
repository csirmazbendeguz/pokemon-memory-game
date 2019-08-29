package net.csirmazbendeguz.memory_game.swing.buttons;

import net.csirmazbendeguz.memory_game.MemoryGame;
import net.csirmazbendeguz.memory_game.game_state.event.listeners.GameStartListener;
import net.csirmazbendeguz.memory_game.game_state.event.objects.GameStartEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Button to change the dimension.
 */
public class ChangeDimensionButton extends PokemonButton implements ActionListener, GameStartListener {

    /**
     * Template for the button's label.
     */
    private static final String LABEL = "%1$sx%1$x >>";

    /**
     * Construct a button for changing the dimension.
     */
    public ChangeDimensionButton() {
        super("");
        this.setBounds(50, 775, WIDTH, HEIGHT);
        this.addActionListener(this);
    }

    /**
     * Change the dimension.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int dimension = MemoryGame.gameState.getBoard().getDimension();
        dimension += 2;
        if (dimension == 8) {
            dimension = 2;
        }
        MemoryGame.gameState.newGame(dimension);
    }

    /**
     * Update the button's label.
     */
    @Override
    public void gameStarted(GameStartEvent event) {
        setText(String.format(LABEL, event.getBoard().getDimension()));
    }

}
