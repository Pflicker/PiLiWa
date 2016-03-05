package de.beaverstudios.plw.Units;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by Grass on 3/3/2016.
 */
public class BuildingManager {

    public final ArrayList<Building> comBuilding = new ArrayList<Building>();
    public final ArrayList<Building> playerBuilding = new ArrayList<Building>();

    public BuildingManager() {
    }

    public void update(float dt) {
        for (Building b : comBuilding){
            b.update(dt);
        }
        for (Building b : playerBuilding){
            b.update(dt);
        }
    }
}
