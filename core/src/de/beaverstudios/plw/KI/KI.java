package de.beaverstudios.plw.KI;

import de.beaverstudios.plw.Buildings.Building;
import de.beaverstudios.plw.Buildings.BuildingManager;
import de.beaverstudios.plw.Buildings.Types.Barracks;

/**
 * Created by paul on 20.03.16.
 */
public class KI {

    public KI(){
        BuildingManager.comBuilding.add(new Barracks(0, 0));
    }
}
