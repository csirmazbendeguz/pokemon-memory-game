package net.csirmazbendeguz.memory_game.swing.button;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import net.csirmazbendeguz.memory_game.game_state.GameState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class RestartButton extends BaseButton implements ActionListener {

    private GameState gameState;

    @Inject
    public RestartButton(@Named("buttonBackground") BufferedImage normal, @Named("buttonBackgroundHover") BufferedImage hover, @Named("buttonBackgroundClick") BufferedImage click, GameState gameState) {
        super(normal, hover, click);
        setText("Restart");
        addActionListener(this);
        this.gameState = gameState;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameState.restartGame();
    }

}
