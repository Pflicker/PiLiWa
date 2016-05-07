package de.beaverstudios.plw.Units;

import de.beaverstudios.plw.Player.Player;
import de.beaverstudios.plw.Techs.Types.ArmorType;
import de.beaverstudios.plw.Techs.Types.DamageType;
import de.beaverstudios.plw.TextureManager;

/**
 * Created by root on 26.03.16.
 */
public class MeleeTank extends Unit {
    public MeleeTank(Player p,int slot){
        player = p;
        name =  "meleetank";
        w = 10 ;
        h = 10 ;
        maxLife = 250 ;
        armorType = ArmorType.PHYSICAL ;
        armor = 10 ;
        movementspeed = 10 ;
        damage = 5 ;
        damageType = DamageType.ENERGY ;
        attackspeed = 1 ;
        range = 0 ;
        skin = TextureManager.MELEETANK ;






        value = 1 ;
        FRAME_COLS = 1 ;
        FRAME_ROWS = 1 ;

        create();
    }

}
