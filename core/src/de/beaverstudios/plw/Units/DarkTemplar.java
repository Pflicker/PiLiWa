package de.beaverstudios.plw.Units;

import de.beaverstudios.plw.Player.Player;
import de.beaverstudios.plw.Techs.Types.ArmorType;
import de.beaverstudios.plw.Techs.Types.DamageType;
import de.beaverstudios.plw.TextureManager;

/**
 * Created by root on 26.03.16.
 */
public class DarkTemplar extends Unit {
    public DarkTemplar(Player p,int slot){
        player = p;
        name =  "darktemplar";
        w = 10 ;
        h = 10 ;
        maxLife = 100;
        armorType = ArmorType.SHIELD ;
        armor = 20 ;
        movementspeed = 10 ;
        damage = 70 ;
        damageType = DamageType.ENERGY ;
        attackspeed = 2 ;
        range = 10 ;
        skin = TextureManager.DARKTEMPLAR;






        value = 1 ;
        FRAME_COLS = 1 ;
        FRAME_ROWS = 1 ;

        create();
    }

}
