package de.beaverstudios.plw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * Created by Grass on 3/3/2016.
 */
public class TextureManager {
    public static TextureAtlas hudAtlas;

    public static Texture BTNPAUSE = new Texture(Gdx.files.internal("Buttons/Pause.png"));
    public static Texture BTNRESUME = new Texture(Gdx.files.internal("Buttons/Resume.png"));

    public static Texture BASE = new Texture(Gdx.files.internal("base.png"));
    public static Texture MARINE = new Texture(Gdx.files.internal("base.png"));
    public static Texture CAT = new Texture(Gdx.files.internal("cat.png"));
    public static Texture HUDBACK = new Texture(Gdx.files.internal("hudback.png"));
    public static Texture BARRACKS = new Texture(Gdx.files.internal("barracks.png"));
    public static Texture FACTORY = new Texture(Gdx.files.internal("factory.jpg"));

    public static Image IMGBTNPAUSE;
    public static Image IMGBTNRESUME;
    public static Image IMGBARRACKS;
    public static Image IMGFACTORY;


    public TextureManager() {
        hudAtlas = new TextureAtlas(Gdx.files.internal("hudtest.atlas"));

        IMGBARRACKS = new Image(BARRACKS);
        IMGBARRACKS.setDrawable(new SpriteDrawable(new Sprite(BARRACKS)));

        IMGFACTORY = new Image(FACTORY);
        IMGFACTORY.setDrawable(new SpriteDrawable(new Sprite(FACTORY)));

        IMGBTNPAUSE = new Image(BTNPAUSE);
        IMGBTNPAUSE.setDrawable(new SpriteDrawable(new Sprite(BTNPAUSE)));

        IMGBTNRESUME = new Image(BTNRESUME);
        IMGBTNRESUME.setDrawable(new SpriteDrawable(new Sprite(BTNRESUME)));

        //  white = new Image(this.hudAtlas.findRegion("white"));

    }
}
