package de.beaverstudios.plw.Buildings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import de.beaverstudios.plw.TextureManager;
import de.beaverstudios.plw.Units.Marine;
import de.beaverstudios.plw.Units.Unit;
import de.beaverstudios.plw.Units.UnitManager;

/**
 * Created by Grass on 3/3/2016.
 */
public abstract class Building {
    public float timeSinceSpawn;
    public Integer slot;
    public Image buildingImage;
    public Integer price;
    public String name;
    public Integer incomeRaise;

    public Building(int slot) {
        this.slot = slot;
        timeSinceSpawn = 0;
        buildingImage = new Image(TextureManager.BASE);
    }

    public void update(float dt){
        timeSinceSpawn += dt;
        if(timeSinceSpawn > 5){
            spawnUnit();
            UnitManager.unitsSpawned += 1;
            Gdx.app.log("Unit spawned", String.format("%03f", timeSinceSpawn));
            timeSinceSpawn =0;
        }
    }

    public void spawnUnit(){
    }

    public int getSlot() {
        return slot;
    }
    public Image getBuildingImage() {
        return buildingImage;
    }
}
