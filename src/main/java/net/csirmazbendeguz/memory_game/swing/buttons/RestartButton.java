package net.csirmazbendeguz.memory_game.swing.buttons;

import net.csirmazbendeguz.memory_game.MemoryGame;
import net.csirmazbendeguz.memory_game.util.ResourceLoader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestartButton extends PokemonButton implements ActionListener {

    public RestartButton(ResourceLoader resourceLoader) {
        super(resourceLoader);
        setText("Restart");
        setBounds(250, 775, WIDTH, HEIGHT);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MemoryGame.gameState.restartGame();
    }

}
