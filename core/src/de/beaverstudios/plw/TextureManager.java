package de.beaverstudios.plw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
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
    public static Texture MARINE = new Texture(Gdx.files.internal("cat.png"));
    public static Texture MELEE = new Texture(Gdx.files.internal("base.png"));
    public static Texture SNIPER = new Texture(Gdx.files.internal("base.png"));
    public static Texture MELEETANK = new Texture(Gdx.files.internal("base.png"));
    public static Texture DARKTEMPLAR = new Texture(Gdx.files.internal("base.png"));

    public static Texture CAT = new Texture(Gdx.files.internal("cat.png"));
    public static Texture HUDBACK = new Texture(Gdx.files.internal("hudback.png"));

    public static Texture BARRACKS  = new Texture(Gdx.files.internal("barracks.png"));
    public static Texture BUILDING2 = new Texture(Gdx.files.internal("factory.jpg"));
    public static Texture BUILDING3 = new Texture(Gdx.files.internal("barracks.png"));
    public static Texture BUILDING4 = new Texture(Gdx.files.internal("factory.jpg"));
    public static Texture BUILDING5 = new Texture(Gdx.files.internal("barracks.png"));

    public static Texture FACTORY = new Texture(Gdx.files.internal("factory.jpg"));

    public static Texture DAMAGE = new Texture(Gdx.files.internal("UnitInfo/damage.png"));
    public static Texture ARMOR = new Texture(Gdx.files.internal("UnitInfo/armor.png"));
    public static Texture SPEED = new Texture(Gdx.files.internal("UnitInfo/speed.png"));
    public static Texture SHIELD = new Texture(Gdx.files.internal("UnitInfo/shield.png"));
    public static Texture HEALTH = new Texture(Gdx.files.internal("UnitInfo/life.png"));

    public static Texture BOTTOMBARHUD = new Texture(Gdx.files.internal("bottombarback.png"));

    public static Image IMGBTNPAUSE;
    public static Image IMGBTNRESUME;
    public static Image IMGBARRACKS;
    public static Image IMGFACTORY;
    public static Image IMGBUILDING2;
    public static Image IMGBUILDING3;
    public static Image IMGBUILDING4;
    public static Image IMGBUILDING5;
    public static Image IMGHUDBACK;

    public static Image IMGDAMAGE;
    public static Image IMGARMOR;
    public static Image IMGSPEED;
    public static Image IMGSHIELD;
    public static Image IMGHEALTH;

    public static Image IMGBOTTOMBARHUD;



    public TextureManager() {
//        hudAtlas = new TextureAtlas(Gdx.files.internal("hudtest.atlas"));

        IMGHUDBACK = new Image(HUDBACK);
        IMGHUDBACK.setDrawable(new SpriteDrawable(new Sprite(HUDBACK)));

        IMGBARRACKS = new Image(BARRACKS);
        IMGBARRACKS.setDrawable(new SpriteDrawable(new Sprite(BARRACKS)));

        IMGFACTORY = new Image(FACTORY);
        IMGFACTORY.setDrawable(new SpriteDrawable(new Sprite(FACTORY)));

        IMGBTNPAUSE = new Image(BTNPAUSE);
        IMGBTNPAUSE.setDrawable(new SpriteDrawable(new Sprite(BTNPAUSE)));

        IMGBTNRESUME = new Image(BTNRESUME);
        IMGBTNRESUME.setDrawable(new SpriteDrawable(new Sprite(BTNRESUME)));

        IMGBUILDING2 = new Image(BUILDING2);
        IMGBUILDING2.setDrawable(new SpriteDrawable(new Sprite(BUILDING2)));

        IMGBUILDING3 = new Image(BUILDING3);
        IMGBUILDING3.setDrawable(new SpriteDrawable(new Sprite(BUILDING3)));

        IMGBUILDING4 = new Image(BUILDING4);
        IMGBUILDING4.setDrawable(new SpriteDrawable(new Sprite(BUILDING4)));

        IMGBUILDING5 = new Image(BUILDING5);
        IMGBUILDING5.setDrawable(new SpriteDrawable(new Sprite(BUILDING5)));

        IMGDAMAGE = new Image(DAMAGE);
        IMGARMOR = new Image(ARMOR);
        IMGSPEED = new Image(SPEED);
        IMGSHIELD = new Image(SHIELD);
        IMGHEALTH = new Image(HEALTH);

        IMGBOTTOMBARHUD = new Image(BOTTOMBARHUD);
        IMGBOTTOMBARHUD.setDrawable(new SpriteDrawable(new Sprite(BOTTOMBARHUD)));
        //  white = new Image(this.hudAtlas.findRegion("white"));

    }
}
