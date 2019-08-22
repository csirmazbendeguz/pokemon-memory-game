package net.csirmazbendeguz.memory_game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IncButton extends PokemonButton implements ActionListener {
    
    private int size = MemoryGame.START_SIZE;
    private GamePanel gp;
    
    public IncButton(GamePanel gp) {
        super("4x4 >>");
        this.gp = gp;
        changeText();
        this.setBounds(50, 775, super.size.width, super.size.height);
        this.addActionListener(this);
    }
    
    private void changeText() {
//        this.setText(size+"x"+size+" >>");
        super.setString(size+"x"+size+" >>");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        size += 2;
        if(size==8) size = 2;
        gp.newGame(size);
        changeText();
    }
    
}
