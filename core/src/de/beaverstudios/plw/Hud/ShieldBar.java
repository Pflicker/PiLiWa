package de.beaverstudios.plw.Hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import de.beaverstudios.plw.Techs.Types.ArmorType;
import de.beaverstudios.plw.Units.Unit;

/**
 * Created by Grass on 5/11/2016.
 */
public class ShieldBar extends Actor {
    private Texture shieldBar;
    private Color color;
    private Texture shieldBarBackground;
    private Unit unit;

    public ShieldBar(float x, float y, float width, float height, Unit unit) {
        color = new Color(Color.BLUE);
        setWidth(width);
        setHeight(height);
        setX(x);
        setY(y);
        this.unit = unit;
        createTexture((int) width, (int) height, color);
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

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(unit.getArmorType() == ArmorType.SHIELD) {
            batch.draw(shieldBarBackground, getX(), getY(), getWidth(), getHeight());
            batch.draw(shieldBar, getX(), getY(), getWidth() * (float) unit.getShieldValue() / (float) unit.getMaxShieldValue(), getHeight());
        }
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public void dispose() {
        shieldBar.dispose();
    }
}
