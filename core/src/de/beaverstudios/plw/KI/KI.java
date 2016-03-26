package de.beaverstudios.plw.KI;

import de.beaverstudios.plw.Buildings.Building5;
import de.beaverstudios.plw.Buildings.BuildingManager;
import de.beaverstudios.plw.Buildings.Barracks;
import de.beaverstudios.plw.Player.Game;
import de.beaverstudios.plw.Player.Player;

/**
 * Created by paul on 20.03.16.
 */
public class KI {

    public KI(Player com){
        com.getBuildings().add(new Barracks(0,com));
        // add com Income
    }
}
