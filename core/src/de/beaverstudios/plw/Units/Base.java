package de.beaverstudios.plw.Units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import de.beaverstudios.plw.Healthbar.HealthBar;
import de.beaverstudios.plw.PlwGame;
import de.beaverstudios.plw.TextureManager;

/**
 * Created by Grass on 3/3/2016.
 */
public class Base extends Unit {

    public Base(int p) {

        w = 20;
        h = 20;

        if (p == 0) {
            x = 0;
            direction = false;
        } else {
            x = PlwGame.V_WIDTH*0.8f - w;
            direction = true;
        }


        y = PlwGame.V_HEIGHT/2;

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
