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

    public Marine(Player p,int slot){
        player = p;
        name = "marine" ;
        w = 10 ;
        h = 10 ;
        maxLife = 140 ;
        armorType = ArmorType.PHYSICAL ;
        armor = 5 ;
        movementspeed = 10 ;
        damage = 25 ;
        damageType = DamageType.PHYSICAL ;
        attackspeed = 1 ;
        range = 50 ;
        skin = TextureManager.MARINE ;

        value = 1 ;
        FRAME_COLS = 1 ;
        FRAME_ROWS = 1 ;

        create();
    }


}
