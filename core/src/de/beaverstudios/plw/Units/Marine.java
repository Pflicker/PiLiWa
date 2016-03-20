package de.beaverstudios.plw.Units;

import de.beaverstudios.plw.Units.DamageType.Physical;
import de.beaverstudios.plw.Units.Healthbar.HealthBar;
import de.beaverstudios.plw.TextureManager;

/**
 * Created by Grass on 3/2/2016.
 */
public class Marine extends Unit implements Physical {


    public Marine(int p, int slot) {
        player = p;
        maxLife = 100;
        w = 10;
        h = 10;
        x = getSpawnPointX(p, slot);
        y = getSpawnPointY(p, slot);
        damage = 5;
        skin = TextureManager.MARINE;
        life = maxLife;
        healthBar = new HealthBar(x, y + h + 1, w, 1, life, maxLife);
        buildung = false;
        name = "Marine";
        armor = 1;
        range = 50;
        attackspeed = 1;
        flying = false;
        attackFlying = true;
        attackGround = true;
        stealthDetect = false;
        invisible = false;
        movementspeed = 100;




    }

}
