package de.beaverstudios.plw.Units.Healthbar;

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

    public HealthBar(float x, float y, float width, float height, int life, int maxlife) {
        createTexture((int)width, (int)height, getColor(life,maxlife));
    }

    public Color getColor(int life, int maxLife){
        Color color;
        color = new Color();
        if (life >= (maxLife * 2 / 3)){
            color.set(Color.GREEN);
        } else if(life >= (maxLife *1 / 3)){
            color.set(Color.YELLOW);
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

    public void draw(Batch batch, float parentAlpha, float x, float y, float w, float h, int life, int maxLife) {
        Color ret = batch.getColor();
        Color color = new Color();
        color.set(getColor(life, maxLife));
        batch.setColor(color);
        batch.draw(healthBar, x, y, w*life/maxLife, h);
        batch.setColor(ret);
    }

    public void dispose(){
        healthBar.dispose();
    }
}
