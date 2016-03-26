package de.beaverstudios.plw.Buildings;

import de.beaverstudios.plw.Player.Player;
import de.beaverstudios.plw.Units.Sniper;

/**
 * Created by root on 26.03.16.
 */
public class Building3 extends Building {
    public Building3(int slot, Player player){
        this.slot =slot;
        this.player = player;
        this.thisType = BuildingTypes.BUILDING3;
        create();
    }

    public void spawnUnit(){
        player.getUnits().add(new Sniper(player,slot));
    }

}
