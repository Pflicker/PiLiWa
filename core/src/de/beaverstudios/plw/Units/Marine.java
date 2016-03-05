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
        name = "Marine";
        x = getSpawnPointX(p);
        y = 16;
        skin = TextureManager.MARINE;
        w = 10;
        h = 10;
        life = 100;
        armor = 1;
        movementspeed =10 ;
        range = 5;
        damage = 5;
        damageType = 5;
        timeSinceShot = 0;
        attackspeed = 1;
        invisible = false;
        flying = false;
        attackFlying = true;
        attackGround = true;
        stealthDetect = false;
        healthBar = new HealthBar(x,y+h+1,w,1,life);
    }
}
