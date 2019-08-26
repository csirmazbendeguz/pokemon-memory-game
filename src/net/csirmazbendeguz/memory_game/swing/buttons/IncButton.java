package net.csirmazbendeguz.memory_game.swing.buttons;

import net.csirmazbendeguz.memory_game.MemoryGame;
import net.csirmazbendeguz.memory_game.game_state.event.listeners.NewGameListener;
import net.csirmazbendeguz.memory_game.game_state.event.objects.NewGameEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IncButton extends PokemonButton implements ActionListener, NewGameListener {

    private static final String LABEL = "%1$sx%1$x >>";

    public IncButton() {
        super("");
        this.setBounds(50, 775, WIDTH, HEIGHT);
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int dimension = MemoryGame.gameState.getBoard().getDimension();
        dimension += 2;
        if (dimension == 8) {
            dimension = 2;
        }
        MemoryGame.gameState.newGame(dimension);
    }

    @Override
    public void newGameStarted(NewGameEvent event) {
        setText(String.format(LABEL, event.getBoard().getDimension()));
    }

}
