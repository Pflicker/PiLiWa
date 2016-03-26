package de.beaverstudios.plw.Buildings;

import de.beaverstudios.plw.Player.Player;
import de.beaverstudios.plw.Units.Melee;
import de.beaverstudios.plw.Units.UnitManager;

/**
 * Created by root on 26.03.16.
 */
public class Building2 extends Building {

    public Building2(int slot, Player player){
        this.slot =slot;
        this.player = player;
        this.thisType = BuildingTypes.BUILDING2;
        create();
    }

    public void spawnUnit(){
        player.getUnits().add(new Melee(player,slot));
    }


}
