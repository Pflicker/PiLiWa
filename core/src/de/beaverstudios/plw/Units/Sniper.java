package de.beaverstudios.plw.Units;

import de.beaverstudios.plw.Player.Player;
import de.beaverstudios.plw.Techs.Types.ArmorType;
import de.beaverstudios.plw.Techs.Types.DamageType;
import de.beaverstudios.plw.TextureManager;

/**
 * Created by root on 26.03.16.
 */
public class Sniper extends Unit{
    public Sniper(Player p,int slot){
        player = p;
        name =  "sniper";
        w = 10 ;
        h = 10 ;
        maxLife = 70 ;
        armorType = ArmorType.ANGSTROM ;
        armor = 0 ;
        movementspeed = 10 ;
        damage = 70 ;
        damageType = DamageType.PHYSICAL ;
        attackspeed = 2 ;
        range = 100 ;
        skin = TextureManager.SNIPER ;






        value = 1 ;
        FRAME_COLS = 1 ;
        FRAME_ROWS = 1 ;

        create();
    }

}
