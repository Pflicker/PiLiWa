package de.beaverstudios.plw.Units;

import de.beaverstudios.plw.Player.Player;
import de.beaverstudios.plw.Techs.Types.ArmorType;
import de.beaverstudios.plw.Techs.Types.DamageType;
import de.beaverstudios.plw.Units.Healthbar.HealthBar;
import de.beaverstudios.plw.TextureManager;

/**
 * Created by Grass on 3/2/2016.
 */
public class Marine extends Unit {


    public Marine(Player p, int slot) {
        player = p;
        maxLife = 100;
        w = 10;
        h = 10;
        x = getSpawnPointX(p, slot);
        y = getSpawnPointY(p, slot);
        damage = 5;
        skin = TextureManager.MARINE;
        life = maxLife;
        value = 1;
        name = "Marine";
        armor = 1;
        range = 10;
        damageType = DamageType.PHYSICAL;
        armorType = ArmorType.ANGSTROM;
        attackspeed = 1;
        flying = false;
        attackFlying = true;
        attackGround = true;
        stealthDetect = false;
        invisible = false;
        movementspeed = 10;

        FRAME_COLS=1;
        FRAME_ROWS=1;

        create();




    }

}
