package de.beaverstudios.plw.Units;

import de.beaverstudios.plw.TextureManager;
import de.beaverstudios.plw.Units.Healthbar.HealthBar;

/**
 * Created by Grass on 3/21/2016.
 */
public class Cat extends Unit{

    public Cat(int p, int slot) {
        name = "Cat";
        player = p;
        maxLife = 200;
        w = 20;
        h = 20;
        damage = 20;
        skin = TextureManager.CAT;
        buildung = false;
        value = 2;
        armor = 1;
        range = 100;
        attackspeed = 2f;
        flying = false;
        attackFlying = true;
        attackGround = true;
        stealthDetect = false;
        invisible = false;
        movementspeed = 100;

        FRAME_COLS = 2;
        FRAME_ROWS = 4;

        create();
    }


}
