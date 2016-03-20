package de.beaverstudios.plw.Units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

import de.beaverstudios.plw.PlwGame;

/**
 * Created by Grass on 3/3/2016.
 */
public class UnitManager {

    public static ArrayList<Unit> comUnits = new ArrayList<Unit>();
    public static ArrayList<Unit> playerUnits = new ArrayList<Unit>();
    private static Grid grid;
    public static int unitsSpawned;
    public static Marine ghostMarine;
    public static Path path;

    public UnitManager() {

        ghostMarine = new Marine(2,9);

        grid = new Grid();
        path = new Path();
        playerUnits.add(new Base(1));
        comUnits.add(new Base(0));
    }


}
