package net.csirmazbendeguz.memory_game.game_state;

import net.csirmazbendeguz.memory_game.util.ResourceLoader;

import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class Card extends Observable {

    /**
     * The card back image.
     */
    private static final BufferedImage CARD_BACK;

    static {
        CARD_BACK = ResourceLoader.getInstance().loadBackogroundImage("CardBack.png");
    }

    /**
     * The card front image.
     */
    private BufferedImage cardFront;

    /**
     * The card image name.
     */
    private String imageName;

    /**
     * Whether the card is facing up or down.
     */
    private boolean faceUp = false;

    /**
     * True while a card animation is running.
     */
    private boolean inAnimation = false;

    /**
     * True until the card is removed from the board.
     */
    private boolean visible = true;

    public Card(String imageName) {
        this.imageName = imageName;
        cardFront = ResourceLoader.getInstance().loadCardImage(imageName);
    }

    public BufferedImage getImage() {
        return isFaceUp() ? cardFront : CARD_BACK;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public boolean isVisible() {
        return visible;
    }

    public boolean isInAnimation() {
        return inAnimation;
    }

    public boolean isPairOf(Card other) {
        return imageName.equals(other.imageName);
    }

    public void hide() {
        animate(new TimerTask() {
            @Override
            public void run() {
                visible = false;
                inAnimation = false;
                setChanged();
                notifyObservers();
                GameState.getInstance().checkWin();
            }
        });
    }

    public void flipDown() {
        animate(new TimerTask() {
            @Override
            public void run() {
                faceUp = false;
                inAnimation = false;
                setChanged();
                notifyObservers();
            }
        });
    }

    private void animate(TimerTask timerTask) {
        inAnimation = true;
        setChanged();
        notifyObservers();
        new Timer().schedule(timerTask, 750);
    }

    public void flipUp() {
        faceUp = true;
        setChanged();
        notifyObservers();
    }

}
