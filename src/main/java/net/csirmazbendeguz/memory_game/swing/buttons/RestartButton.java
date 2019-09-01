package net.csirmazbendeguz.memory_game.swing.buttons;

import net.csirmazbendeguz.memory_game.game_state.GameState;
import net.csirmazbendeguz.memory_game.util.ResourceLoader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestartButton extends PokemonButton implements ActionListener {

    private GameState gameState;

    public RestartButton(ResourceLoader resourceLoader, GameState gameState) {
        super(resourceLoader);
        setText("Restart");
        setBounds(250, 775, WIDTH, HEIGHT);
        addActionListener(this);
        this.gameState = gameState;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameState.restartGame();
    }

}
