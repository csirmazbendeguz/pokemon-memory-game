package net.csirmazbendeguz.memory_game.game_state;

import net.csirmazbendeguz.memory_game.MemoryGame;

import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The card state.
 */
public class Card extends Observable {

    /**
     * The card back image.
     */
    private BufferedImage cardBack;

    /**
     * The card front image.
     */
    private BufferedImage cardFront;

    /**
     * The card front image's name.
     */
    private String imageName;

    /**
     * Whether the card is facing up or down.
     */
    private boolean faceUp = false;

    /**
     * Whether a card animation is running.
     */
    private boolean inAnimation = false;

    /**
     * Whether the card should be displayed.
     */
    private boolean visible = true;

    /**
     * Construct a new card state object.
     *
     * @param imageName The card front image's name.
     */
    public Card(String imageName, BufferedImage cardFront, BufferedImage cardBack) {
        this.imageName = imageName;
        this.cardFront = cardFront;
        this.cardBack = cardBack;
    }

    public BufferedImage getImage() {
        return isFaceUp() ? cardFront : cardBack;
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
                MemoryGame.gameState.checkWin();
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
