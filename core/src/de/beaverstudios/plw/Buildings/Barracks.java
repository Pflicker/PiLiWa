package de.beaverstudios.plw.Buildings;

import de.beaverstudios.plw.Buildings.Building;
import de.beaverstudios.plw.Buildings.BuildingManager;
import de.beaverstudios.plw.Interfaces.Buyable;
import de.beaverstudios.plw.Units.Marine;
import de.beaverstudios.plw.Units.UnitManager;

/**
 * Created by Grass on 3/19/2016.
 */
public class Barracks extends Building {

    public Barracks(int slot, int player) {
        this.slot = slot;
        this.player = player;
        thisType = BuildingTypes.BARRACKS;
        create();

    }

    public void spawnUnit() {
        if (player == 0) {
            UnitManager.comUnits.add(new Marine(0,slot));
        }
        if (player == 1) {
            UnitManager.playerUnits.add(new Marine(1,slot));
        }

    }
}
