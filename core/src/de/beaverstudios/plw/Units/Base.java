package de.beaverstudios.plw.Units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import de.beaverstudios.plw.Healthbar.HealthBar;
import de.beaverstudios.plw.TextureManager;

/**
 * Created by Grass on 3/3/2016.
 */
public class Base extends Unit {

    public Base(int p) {


        if (p == 0) {
            getSpawnPointX(p);
            direction = false;
        } else {
            getSpawnPointX(p);
            direction = true;
        }


        y = 16;
        w = 20;
        h = 20;
        life = 100;
        armor = 50;
        damage = 50;
        range = 80;
        player = p;
        stealthDetect = true;
        attackGround = true;
        attackFlying = true;
        movementspeed = 0;
        attackspeed = 1;
        healthBar = new HealthBar(x,y+h+1,w,1,life);
        buildung = true;

        skin = TextureManager.BASE;

    }




}
