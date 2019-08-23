package net.csirmazbendeguz.memory_game.swing.panels;

import net.csirmazbendeguz.memory_game.swing.GameFrame;
import net.csirmazbendeguz.memory_game.ImageLoader;
import net.csirmazbendeguz.memory_game.MemoryGame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

public class GamePanel extends JPanel {
    
    private ArrayList<String> cardArrayList = new ArrayList<String>(); // jatekban levo kartyak
    private ArrayList<String> cards = new ArrayList<String>(); // jatekban levo kartyak (2x) veletlenszeruen elrendezve
    private ArrayList<CardPanel> cardPanels = new ArrayList<CardPanel>();
    private int size = MemoryGame.START_SIZE; // 2-4-6
    private BufferedImage image;

    public GamePanel() {
        this.setBackground(Color.yellow);
        this.setBounds(25, 100, 650, 650);

        image = ImageLoader.getInstance().loadBackogroundImage("GamePanelBackground.png");
        
        initGame();
    }

    public int get_size() {
        return size;
    }

    private void initGame() {
        this.removeAll();
        cardPanels.clear();
        cardArrayList.clear();
        cards.clear();
        fillCardArrayList(size);
        initPanel(size);
    }
    
    public void newGame(int size) {
        this.size = size;
        initGame();
        GameFrame f = (GameFrame) SwingUtilities.getWindowAncestor(this);
        f.time.reset();
        f.tries.reset();
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
                CardPanel ip = getRandomCard();
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
            f.tries.add();
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
            f.time.end();
            System.out.println("WIN!"+" Size: "+size+"x"+size+", Time: "+f.time.getText()+", "+f.tries.getText());
            f.win.show(size, f.time.getText(), f.tries.getText());
        }
    }

    private void fillCardArrayList(int size) {
        List<String> cardList;

        try {
            cardList = ImageLoader.getInstance().getCardList();
        } catch (Exception exception) {
            throw new RuntimeException("Failed to load card list.");
        }

        Random rand = new Random();
        do {
            String card = cardList.get(rand.nextInt(cardList.size()));
            if(!cardArrayList.contains(card)) {
                cardArrayList.add(card);
            }
        } while(cardArrayList.size() < size*size/2);
    }
    
    
    private CardPanel getRandomCard() {
        Random rand = new Random();
        do {
            String card = cardArrayList.get(rand.nextInt(cardArrayList.size()));
            if(elementsContained(cards, card) < 2) {
                cards.add(card);
                return new CardPanel(card);
            }
        } while(true);
    }
    
    // visszaadja hanyszor tartalmaz egy lista egy objektumot
    private int elementsContained(ArrayList array, Object obj) {
        int c = 0;
        for(int i = 0; i < array.size(); i++) {
            if(array.get(i) == obj) {
                c++;
            }
        }
        return c;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }
    
    
}
