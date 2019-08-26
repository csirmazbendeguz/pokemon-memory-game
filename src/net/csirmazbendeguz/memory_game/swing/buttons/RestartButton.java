package net.csirmazbendeguz.memory_game.swing.buttons;

import net.csirmazbendeguz.memory_game.game_state.Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestartButton extends PokemonButton implements ActionListener {

    public RestartButton() {
        super("Restart");
        this.setBounds(250, 775, WIDTH, HEIGHT);
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Board.getInstance().restartGame();
    }

}
