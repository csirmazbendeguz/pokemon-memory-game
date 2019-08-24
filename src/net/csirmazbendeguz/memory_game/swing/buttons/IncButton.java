package net.csirmazbendeguz.memory_game.swing.buttons;

import net.csirmazbendeguz.memory_game.game_state.Board;
import net.csirmazbendeguz.memory_game.swing.panels.GamePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IncButton extends PokemonButton implements ActionListener {

    private GamePanel gp;
    private static final String LABEL = "%1$sx%1$x >>";

    public IncButton(GamePanel gp) {
        super(String.format(LABEL, Board.getInstance().getDimension()));
        this.gp = gp;
        this.setBounds(50, 775, WIDTH, HEIGHT);
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int dimension = Board.getInstance().getDimension();
        dimension += 2;
        if (dimension == 8) {
            dimension = 2;
        }
        gp.newGame(dimension);
        setText(String.format(LABEL, dimension));
    }

}
