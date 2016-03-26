package de.beaverstudios.plw.Units;

import de.beaverstudios.plw.Player.Player;
import de.beaverstudios.plw.Techs.Types.ArmorType;
import de.beaverstudios.plw.Techs.Types.DamageType;
import de.beaverstudios.plw.TextureManager;

/**
 * Created by root on 26.03.16.
 */
public class Melee extends Unit{

    public Melee(Player p,int slot){
        player = p;
        name =  "melee";
        w = 10 ;
        h = 10 ;
        maxLife = 160 ;
        armorType = ArmorType.SHIELD ;
        armor = 10 ;
        movementspeed = 10 ;
        damage = 25 ;
        damageType = DamageType.PHYSICAL ;
        attackspeed = 1 ;
        range = 0 ;
        skin = TextureManager.MELEE ;






        value = 1 ;
        FRAME_COLS = 1 ;
        FRAME_ROWS = 1 ;

        create();
    }

}
