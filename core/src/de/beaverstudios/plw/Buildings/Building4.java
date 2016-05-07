package de.beaverstudios.plw.Buildings;

import de.beaverstudios.plw.Player.Player;
import de.beaverstudios.plw.Units.MeleeTank;
import de.beaverstudios.plw.Units.Unit;
import de.beaverstudios.plw.Units.UnitManager;

/**
 * Created by root on 26.03.16.
 */
public class Building4 extends Building {
    public Building4(int slot, Player player){
        this.slot =slot;
        this.player = player;
        this.thisType = BuildingTypes.BUILDING4;
        Unit unitPtr = UnitManager.getGhostMeleeTank();
        create();
    }

    public void spawnUnit(){
        player.getUnits().add(new MeleeTank(player,slot));
    }

}
