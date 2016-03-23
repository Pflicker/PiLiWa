package de.beaverstudios.plw.Units;

import de.beaverstudios.plw.Units.Healthbar.HealthBar;
import de.beaverstudios.plw.PlwGame;
import de.beaverstudios.plw.TextureManager;

/**
 * Created by Grass on 3/3/2016.
 */
public class Base extends Unit {


    public Base(int p) {

        w = 20;
        h = 20;

        maxLife = 1000;
        armor = 50;
        damage = 200;
        range = 80;
        player = p;
        stealthDetect = true;
        attackGround = true;
        attackFlying = true;
        movementspeed = 0;
        attackspeed = 1;
        buildung = true;
        value = 20;

        skin = TextureManager.BASE;

        FRAME_COLS =1;
        FRAME_ROWS =1;
        create();

        if (p == 0) {
            x = 0;
            direction = false;
        } else {
            x = PlwGame.V_WIDTH * 0.8f - w;
            direction = true;
        }


        y = PlwGame.V_HEIGHT / 2;

    }
}


