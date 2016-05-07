package de.beaverstudios.plw.Buildings;

import de.beaverstudios.plw.Player.Player;
import de.beaverstudios.plw.Units.DarkTemplar;
import de.beaverstudios.plw.Units.Unit;
import de.beaverstudios.plw.Units.UnitManager;

/**
 * Created by root on 26.03.16.
 */
public class Building5 extends Building {

    public Building5(int slot, Player player){
        this.slot =slot;
        this.player = player;
        this.thisType = BuildingTypes.BUILDING5;
        Unit unitPtr = UnitManager.getGhostDarkTemplar();
        create();
    }

    public void spawnUnit(){
        player.getUnits().add(new DarkTemplar(player,slot));
    }

}
