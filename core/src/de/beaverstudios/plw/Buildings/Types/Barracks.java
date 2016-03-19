package de.beaverstudios.plw.Buildings.Types;

import de.beaverstudios.plw.Buildings.Building;
import de.beaverstudios.plw.Buildings.BuildingManager;
import de.beaverstudios.plw.Interfaces.Buyable;
import de.beaverstudios.plw.Units.Marine;
import de.beaverstudios.plw.Units.UnitManager;

/**
 * Created by Grass on 3/19/2016.
 */
public class Barracks extends Building{

    public Barracks(int slot) {
        super(slot);
        name = "Barracks";

    }

    public void spawnUnit(){
        UnitManager.playerUnits.add(new Marine(1));
    }

}
