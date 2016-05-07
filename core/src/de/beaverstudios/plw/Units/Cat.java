package de.beaverstudios.plw.Units;

import de.beaverstudios.plw.Player.Player;
import de.beaverstudios.plw.Techs.Types.ArmorType;
import de.beaverstudios.plw.Techs.Types.DamageType;
import de.beaverstudios.plw.TextureManager;

/**
 * Created by Grass on 3/21/2016.
 */
public class Cat extends Unit{

    public Cat(Player p, int slot) {
        name = "Cat";
        player = p;
        maxLife = 200;
        w = 20;
        h = 20;
        damage = 20;
        damageType = DamageType.PHYSICAL;
        armorType = ArmorType.PHYSICAL;
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
