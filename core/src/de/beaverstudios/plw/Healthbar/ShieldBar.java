package de.beaverstudios.plw.Healthbar;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;


/**
 * Created by Grass on 3/20/2016.
 */
public class ShieldBar {
    private Texture shieldBar;

    public ShieldBar(float x, float y, float width, float height, int shield) {
        createTexture((int)width, (int)height, getColor(shield));
    }

    public Color getColor(int shield){
        Color color;
        color = new Color();
        if (shield >= 50){
            color.set(Color.BLUE);
        } else {
            color.set(Color.NAVY);
        }
        return color;
    }

    private void createTexture(int width, int height, Color color) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillRectangle(0, 0, width, height);
        shieldBar = new Texture(pixmap);
        pixmap.dispose();
    }

    public void draw(Batch batch, float parentAlpha, float x, float y, float w, float h, int life) {
        Color ret = batch.getColor();
        Color color = getColor(life);
        batch.setColor(color);
        batch.draw(shieldBar, x, y, w, h);
        batch.setColor(ret);
    }

    public void dispose(){
        shieldBar.dispose();
    }
}
