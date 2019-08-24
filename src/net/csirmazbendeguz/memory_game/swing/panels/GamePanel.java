package net.csirmazbendeguz.memory_game.swing.panels;

import net.csirmazbendeguz.memory_game.game_state.TriesCounter;
import net.csirmazbendeguz.memory_game.utils.RandomCardGenerator;
import net.csirmazbendeguz.memory_game.utils.ResourceLoader;
import net.csirmazbendeguz.memory_game.game_state.Stopwatch;
import net.csirmazbendeguz.memory_game.swing.GameFrame;
import net.csirmazbendeguz.memory_game.MemoryGame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;

public class GamePanel extends JPanel {

    private String[][] board;
    private ArrayList<CardPanel> cardPanels = new ArrayList<CardPanel>();
    private int size = MemoryGame.DEFAULT_BOARD_DIMENSION; // 2-4-6
    private BufferedImage image;

    public GamePanel() {
        this.setBackground(Color.yellow);
        this.setBounds(25, 100, 650, 650);

        image = ResourceLoader.getInstance().loadBackogroundImage("GamePanelBackground.png");
        
        initGame();
    }

    public int get_size() {
        return size;
    }

    private void initGame() {
        this.removeAll();
        cardPanels.clear();
        board = new RandomCardGenerator().generateBoard(size);
        initPanel(size);
    }
    
    public void newGame(int size) {
        this.size = size;
        initGame();
        GameFrame f = (GameFrame) SwingUtilities.getWindowAncestor(this);
        Stopwatch stopwatch = Stopwatch.getInstance();
        stopwatch.stopTimer();
        stopwatch.resetSeconds();
        TriesCounter.getInstance().reset();
        f.validate();
        f.repaint();
    }
    
    private void initPanel(int size) {
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(layout);
        
//        int insetHorizontal = (this.getWidth()-size*CardPanel.size.width)/(size+2);
//        int insetVertical = (this.getHeight()-size*CardPanel.size.height)/(size+2);
//        gbc.insets = new Insets(insetVertical, insetHorizontal, insetVertical, insetHorizontal);
        for(int x = 0; x < size; x++) {
            for(int y = 0; y < size; y++) {
                gbc.fill = GridBagConstraints.CENTER;
                gbc.gridx = x;
                gbc.gridy = y;
                CardPanel ip = new CardPanel(board[x][y]);
                cardPanels.add(ip);
                this.add(ip, gbc);
            }
        }
    }

    public void checkPairs() {
        ArrayList<Integer> tempA = new ArrayList<Integer>();
        for(int i = 0; i < cardPanels.size(); i++) {
            CardPanel ip = cardPanels.get(i);
            if(ip.getShowFront()) {
                tempA.add(i);
            }
        }
        if(tempA.size() == 2) {
            GameFrame f = (GameFrame) SwingUtilities.getWindowAncestor(this);
            TriesCounter.getInstance().increase();
            CardPanel imgp0 = cardPanels.get(tempA.get(0));
            CardPanel imgp1 = cardPanels.get(tempA.get(1));
            if(imgp0.getImgName().equals(imgp1.getImgName())) {
                imgp0.clearCardAnim();
                imgp1.clearCardAnim();
                cardPanels.remove(imgp0);
                cardPanels.remove(imgp1);
                checkWin();
            } else {
                imgp0.changeBackAnim();
                imgp1.changeBackAnim();
            }
        }
    }

    private void checkWin() {
        if(cardPanels.isEmpty()) {
            GameFrame f = (GameFrame) SwingUtilities.getWindowAncestor(this);
            Stopwatch stopwatch = Stopwatch.getInstance();
            stopwatch.stopTimer();
            f.win.show(
                size,
                "Time: " + stopwatch.getSeconds(),
                "Tries: " + TriesCounter.getInstance().getTries()
            );
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }

}
