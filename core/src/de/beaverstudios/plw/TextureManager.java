package de.beaverstudios.plw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Grass on 3/3/2016.
 */
public class TextureManager {
    public static TextureAtlas hudAtlas;
    public static Texture BASE = new Texture(Gdx.files.internal("base.png"));
    public static Texture MARINE = new Texture(Gdx.files.internal("base.png"));

    public TextureManager() {
        hudAtlas = new TextureAtlas(Gdx.files.internal("hudtest.atlas"));

      //  white = new Image(this.hudAtlas.findRegion("white"));

    }
}
