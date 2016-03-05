package de.beaverstudios.plw.Units;

import com.badlogic.gdx.Gdx;

/**
 * Created by Grass on 3/3/2016.
 */
public class Building {
    private float timeSinceSpawn;
    private Unit unit;
    private UnitManager unitManager;

    public Building(UnitManager um,Unit u) {
        unit = u;
        unitManager = um;
        timeSinceSpawn = 0;
    }

    public void update(float dt){
        timeSinceSpawn += dt;
        if(timeSinceSpawn > 5){
            unitManager.playerUnits.add(new Marine(1));
            unitManager.unitsSpawned += 1;
            Gdx.app.log("Unit spawned", String.format("%03f", timeSinceSpawn));
            timeSinceSpawn =0;
        }
    }

}
