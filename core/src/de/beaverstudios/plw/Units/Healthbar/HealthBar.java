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

import de.beaverstudios.plw.Units.Unit;

/**
 * Created by Grass on 3/3/2016.
 */
public class HealthBar extends Actor{

    private Texture healthBar;
    private Color color;
    private Texture healthBarBackground;
    private Unit unit;

    public HealthBar(Unit unit) {
        this.unit = unit;
        color = new Color(Color.GREEN);
        createTexture(unit.getW(), 1, color);
        createBackgroundTexture(unit.getW(), 1);
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
        batch.draw(healthBarBackground, unit.getX(), unit.getY()+unit.getH()+1, unit.getW(), 1);
        if (unit.getLife()>= (unit.getMaxLife()* 2 / 3)) {
            healthBar.dispose();
            createTexture(unit.getW(),1, Color.GREEN);
        } else if (unit.getLife()>= (unit.getMaxLife() * 1 / 3)) {
            healthBar.dispose();
            createTexture((int) unit.getW(), (int) 1, Color.YELLOW);
        } else {
            healthBar.dispose();
            createTexture((int) unit.getW(), (int) 1, Color.RED);
        }
        batch.draw(healthBar, unit.getW(), unit.getY()+unit.getH() + 1, (float)unit.getW() * (float)unit.getLife()/(float)unit.getMaxLife(), 1);
    }

    public void draw(Batch batch, float parentAlpha, float x, float y, float w, float h, int life, int maxLife) {
        batch.draw(healthBarBackground, x, y, w, h);
        if (life >= (maxLife * 2 / 3)) {
            healthBar.dispose();
            createTexture((int)w,(int)h, Color.GREEN);
        } else if (life >= (maxLife * 1 / 3)) {
            healthBar.dispose();
            createTexture((int) w, (int) h, Color.YELLOW);
        } else {
            healthBar.dispose();
            createTexture((int) w, (int) h, Color.RED);
        }
        batch.draw(healthBar, x, y, (float)w * (float)life/(float)maxLife, h);
    }

    public void dispose() {
        healthBar.dispose();
        healthBarBackground.dispose();
    }
}
