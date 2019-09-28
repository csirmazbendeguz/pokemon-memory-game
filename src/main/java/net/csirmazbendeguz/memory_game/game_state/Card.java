package net.csirmazbendeguz.memory_game.game_state;

import net.csirmazbendeguz.memory_game.event.EventDispatcher;
import net.csirmazbendeguz.memory_game.event.objects.CardFlipDownEvent;
import net.csirmazbendeguz.memory_game.event.objects.CardFlipUpEvent;
import net.csirmazbendeguz.memory_game.event.objects.CardHideEvent;

import java.util.EventObject;
import java.util.Timer;
import java.util.TimerTask;

public class Card {

    private EventDispatcher eventDispatcher;

    private boolean faceUp = false;

    private boolean visible = true;

    private boolean inAnimation = false;

    private String imageName;

    public Card(String imageName, EventDispatcher eventDispatcher) {
        this.imageName = imageName;
        this.eventDispatcher = eventDispatcher;
    }

    public String getImageName() {
        return imageName;
    }

    public boolean isVisible() {
        return visible;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public boolean isInAnimation() {
        return inAnimation;
    }

    public void hide() {
        EventObject event = new CardHideEvent(this, this);
        animate(new TimerTask() {
            @Override
            public void run() {
                visible = false;
                inAnimation = false;
                eventDispatcher.dispatch(event);
            }
        });
    }

    public void flipDown() {
        EventObject event = new CardFlipDownEvent(this, this);
        animate(new TimerTask() {
            @Override
            public void run() {
                faceUp = false;
                inAnimation = false;
                eventDispatcher.dispatch(event);
            }
        });
    }

    private void animate(TimerTask timerTask) {
        inAnimation = true;
        new Timer().schedule(timerTask, 750);
    }

    public void flipUp() {
        faceUp = true;
        eventDispatcher.dispatch(new CardFlipUpEvent(this, this));
    }

    public boolean canFlipUp() {
        return isVisible() && !isFaceUp() && !isInAnimation();
    }

    public boolean isPairOf(Card other) {
        return imageName.equals(other.imageName);
    }

}
