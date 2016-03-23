package de.beaverstudios.plw.Buildings;

import de.beaverstudios.plw.Units.Cat;
import de.beaverstudios.plw.Units.Marine;
import de.beaverstudios.plw.Units.UnitManager;

/**
 * Created by Grass on 3/21/2016.
 */
public class Factory extends Building {

    public Factory(int slot, int player) {
        this.slot = slot;
        this.player = player;
        thisType =BuildingTypes.FACTORY;
        create();
    }

    public void spawnUnit() {
        if (player == 0) {
            UnitManager.comUnits.add(new Cat(0,slot));
            System.out.println(buildingName);
            System.out.println(unitName);
        }
        if (player == 1) {
            UnitManager.playerUnits.add(new Cat(1,slot));
        }

    }
}
