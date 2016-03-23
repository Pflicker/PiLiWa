package de.beaverstudios.plw.Animations;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Grass on 3/21/2016.
 */
public class UnitValue {
    private float timer;
    private float x;
    private float y;
    private String popUp;

    public UnitValue(float x, float y, int value){
        timer = 0f;
        this.x = x;
        this.y = y;
        popUp = "+ " + value;
    }

    public void update(float dt){
        timer += dt;
        y += dt*10;
    }

    public void render(SpriteBatch batch, BitmapFont font){
        font.draw(batch,popUp,x,y);
    }

    public float getTimer() {
        return timer;
    }
}
