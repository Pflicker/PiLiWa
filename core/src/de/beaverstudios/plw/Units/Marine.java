package de.beaverstudios.plw.Units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import de.beaverstudios.plw.Healthbar.HealthBar;
import de.beaverstudios.plw.TextureManager;

/**
 * Created by Grass on 3/2/2016.
 */
public class Marine extends Unit {

    public Marine(int p) {


        maxLife = 100;
        w = 10;
        h = 10;
        x = getSpawnPointX(p);
        damage = 5;
        y = 16;
        skin = TextureManager.MARINE;
        timeSinceShot = 0;
        life = maxLife;
        healthBar = new HealthBar(x,y+h+1,w,1,life);
        name = "Marine";
        armor = 1;
        range = 5;
        damageType = 5;
        attackspeed = 1;
        flying = false;
        attackFlying = true;
        attackGround = true;
        stealthDetect = false;
        invisible = false;


    }

}
