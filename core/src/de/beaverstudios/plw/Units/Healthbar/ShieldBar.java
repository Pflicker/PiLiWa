package de.beaverstudios.plw.Units.Healthbar;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;


/**
 * Created by Grass on 3/20/2016.
 */
public class ShieldBar {
    private Texture shieldBar;
    private Color color;
    private Texture shieldBarBackground;

    public ShieldBar(float x, float y, float width, float height, int shieldValue, int maxShieldValue) {
        color = new Color(Color.BLUE);
        createTexture((int)width, (int)height, color);
        createBackgroundTexture((int) width, (int) height);

    }

    private void createBackgroundTexture(int width, int height) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.fillRectangle(0, 0, width, height);
        shieldBarBackground = new Texture(pixmap);
        pixmap.dispose();
    }

    private void createTexture(int width, int height, Color color) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillRectangle(0, 0, width, height);
        shieldBar = new Texture(pixmap);
        pixmap.dispose();
    }

    public void draw(Batch batch, float parentAlpha, float x, float y, float w, float h, int shieldValue, int maxShieldValue) {
        batch.draw(shieldBarBackground, x, y, w, h);
        batch.draw(shieldBar, x, y, (float) w * (float) shieldValue/ (float) maxShieldValue, h);
    }

    public void dispose(){
        shieldBar.dispose();
    }
}
