package net.csirmazbendeguz.memory_game.game_state;

import net.csirmazbendeguz.memory_game.event.EventDispatcher;
import net.csirmazbendeguz.memory_game.event.objects.CardFlipUpEvent;
import net.csirmazbendeguz.memory_game.event.objects.CardHideEvent;
import net.csirmazbendeguz.memory_game.util.ResourceLoader;

import java.awt.image.BufferedImage;
import java.util.EventObject;
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
     * Whether the card is facing up or down.
     */
    private boolean faceUp = false;

    /**
     * Whether the card should be displayed.
     */
    private boolean visible = true;

    /**
     * Whether a card animation is running.
     */
    private boolean inAnimation = false;

    /**
     * The card front image's name.
     */
    private String imageName;

    /**
     * The event dispatcher.
     */
    private EventDispatcher eventDispatcher;

    /**
     * Construct a new card state object.
     *
     * @param imageName The card front image's name.
     */
    public Card(String imageName, ResourceLoader resourceLoader, BufferedImage cardBack, EventDispatcher eventDispatcher) {
        this.imageName = imageName;
        cardFront = resourceLoader.loadCardImage(imageName);
        this.cardBack = cardBack;
        this.eventDispatcher = eventDispatcher;
    }

    /**
     * Get the card image to render.
     */
    public BufferedImage getImage() {
        if (!isVisible()) {
            return null;
        }
        return isFaceUp() ? cardFront : cardBack;
    }

    /**
     * Return whether the card should be displayed.
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Return whether the card is facing up or down.
     */
    public boolean isFaceUp() {
        return faceUp;
    }

    /**
     * Return whether a card animation is in progress.
     */
    public boolean isInAnimation() {
        return inAnimation;
    }

    /**
     * Return whether the given memory card is a matching pair.
     */
    public boolean isPairOf(Card other) {
        return imageName.equals(other.imageName);
    }

    /**
     * Make the card invisible.
     */
    public void hide() {
        EventObject event = new CardHideEvent(this);
        animate(new TimerTask() {
            @Override
            public void run() {
                visible = false;
                inAnimation = false;
                setChanged();
                notifyObservers();
                eventDispatcher.dispatch(event);
            }
        });
    }

    /**
     * Flip the card down.
     */
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

    /**
     * Execute the given task after a small period of time.
     */
    private void animate(TimerTask timerTask) {
        inAnimation = true;
        new Timer().schedule(timerTask, 750);
    }

    /**
     * Flip the card up.
     */
    public void flipUp() {
        faceUp = true;
        setChanged();
        notifyObservers();
        eventDispatcher.dispatch(new CardFlipUpEvent(this, this));
    }

    public boolean canFlipUp() {
        return isVisible() && !isFaceUp() && !isInAnimation();
    }

}
