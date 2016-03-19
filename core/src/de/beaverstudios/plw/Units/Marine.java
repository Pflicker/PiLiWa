package de.beaverstudios.plw.Units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import de.beaverstudios.plw.Healthbar.HealthBar;
import de.beaverstudios.plw.TextureManager;

/**
 * Created by Grass on 3/2/2016.
 */
public class Marine extends Unit {

    private static String name = "Marine";
    private static Integer armor = 1;
    private static Integer range = 5;
    private static Integer damage = 5;
    private static Integer damageType = 5;
    private static Integer attackspeed = 1;
    private static Integer maxMovementSpeed = 100;
    private static boolean flying = false;
    private static boolean attackFlying = true;
    private static boolean attackGround = true;
    private static boolean stealthDetect = false;
    private static boolean invisible = false;

    public Marine(int p) {
        player = p;
        movementspeed = maxMovementSpeed;
        maxLife = 100;
        w = 10;
        h = 10;
        x = getSpawnPointX(p);
        y = 16;
        skin = TextureManager.MARINE;
        timeSinceShot = 0;
        life = maxLife;
        healthBar = new HealthBar(x,y+h+1,w,1,life);
        buildung = true;
    }
}
