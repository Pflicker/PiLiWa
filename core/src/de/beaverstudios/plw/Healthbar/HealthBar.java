package de.beaverstudios.plw.Healthbar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

/**
 * Created by Grass on 3/3/2016.
 */
public class HealthBar{

    private Texture healthBar;

    public HealthBar(float x, float y, float width, float height, int life) {
        createTexture((int)width, (int)height, getColor(life));
    }

    public Color getColor(int life){
        Color color;
        color = new Color();
            if (life >= 50){
               color.set(Color.GREEN);
            } else {
                color.set(Color.RED);
            }
        return color;
    }

    private void createTexture(int width, int height, Color color) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillRectangle(0, 0, width, height);
        healthBar = new Texture(pixmap);
        pixmap.dispose();
    }

    public void draw(Batch batch, float parentAlpha, float x, float y, float w, float h, int life) {
        Color ret = batch.getColor();
        Color color = getColor(life);
        batch.setColor(color);
        batch.draw(healthBar, x, y, w, h);
        batch.setColor(ret);
    }

    public void dispose(){
        healthBar.dispose();
    }
}
