package de.beaverstudios.plw.KI;

import de.beaverstudios.plw.Buildings.BuildingManager;
import de.beaverstudios.plw.Buildings.Barracks;
import de.beaverstudios.plw.Buildings.Factory;

/**
 * Created by paul on 20.03.16.
 */
public class KI {

    public KI(){
        BuildingManager.comBuilding.add(new Factory(0, 0));
        // add com Income
    }
}
