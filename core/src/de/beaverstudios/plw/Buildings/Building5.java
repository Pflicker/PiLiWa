package de.beaverstudios.plw.Buildings;

import de.beaverstudios.plw.Player.Player;
import de.beaverstudios.plw.Units.DarkTemplar;

/**
 * Created by root on 26.03.16.
 */
public class Building5 extends Building {

    public Building5(int slot, Player player){
        this.slot =slot;
        this.player = player;
        this.thisType = BuildingTypes.BUILDING5;
        create();
    }

    public void spawnUnit(){
        player.getUnits().add(new DarkTemplar(player,slot));
    }

}
