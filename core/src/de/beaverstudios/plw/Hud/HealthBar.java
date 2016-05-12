package de.beaverstudios.plw.Hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import de.beaverstudios.plw.Units.Unit;

/**
 * Created by Grass on 5/11/2016.
 */
public class HealthBar extends Actor {
    private Texture healthBar;
    private Color color;
    private Texture healthBarBackground;
    private Unit unit;

    public HealthBar(float x, float y,float width, float height, Unit unit) {
        color = new Color(Color.GREEN);
        setWidth(width);
        setHeight(height);
        setX(x);
        setY(y);
        this.unit = unit;
        createTexture((int)getWidth(),(int) getHeight(), color);
        createBackgroundTexture((int)getWidth(),(int)getHeight());
    }

    private void createTexture(int width, int height, Color color) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillRectangle(0, 0, width, height);
        healthBar = new Texture(pixmap);
        pixmap.dispose();
    }

    private void createBackgroundTexture(int width, int height) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.fillRectangle(0, 0, width, height);
        healthBarBackground = new Texture(pixmap);
        pixmap.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(healthBarBackground, getX(), getY(), getWidth(), getHeight());
        if (unit.getLife()>= (unit.getMaxLife()* 2 / 3)) {
            healthBar.dispose();
            createTexture((int)getWidth(),(int)getHeight(), Color.GREEN);
        } else if (unit.getLife()>= (unit.getMaxLife() * 1 / 3)) {
            healthBar.dispose();
            createTexture((int) getWidth(), (int) getHeight(), Color.YELLOW);
        } else {
            healthBar.dispose();
            createTexture((int) getWidth(), (int) getHeight(), Color.RED);
        }
        batch.draw(healthBar, getX(), getY(), getWidth() * (float)unit.getLife()/(float)unit.getMaxLife(), getHeight());
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public void dispose() {
        healthBar.dispose();
        healthBarBackground.dispose();
    }

}
