package de.beaverstudios.plw.Buildings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.lang.reflect.Constructor;

import de.beaverstudios.plw.Player.Player;
import de.beaverstudios.plw.Screens.GameScreen;
import de.beaverstudios.plw.TextureManager;
import de.beaverstudios.plw.Units.Marine;
import de.beaverstudios.plw.Units.Unit;
import de.beaverstudios.plw.Units.UnitManager;

/**
 * Created by Grass on 3/3/2016.
 */
public abstract class Building {
    public Image buildingImage;
    public Integer price;
    public String buildingName;
    public Player player;
    public Integer slot;
    public String unitName;
    public BuildingTypes thisType;
    public Unit ghostUnit;
    public Unit unit;
    Unit unitPtr;
    Integer toughness;
    Integer power;


    public Building() {
    }

    public void create(){
        buildingName = thisType.getBuildingName();
        price = thisType.getPrice();
        unitName = thisType.getUnitName();
        buildingImage = thisType.getImage();
        power = thisType.getPower();
        power = thisType.getToughness();
        //unit = thisType.getUnit();


    }


    public void update(float dt){

    }
    public void spawnUnit(){

    }

    public int getSlot() {
        return slot;
    }
    public Image getBuildingImage() {
        return buildingImage;
    }

    public String getUnitName() {
        return unitName;
    }

    public BuildingTypes getThisType(){ return thisType; }


    public Integer getToughness() {
        return toughness;
    }

    public Integer getPower() {
        return power;
    }

    public Unit getUnitPtr() {
        return unitPtr;
    }

    public void setUnitPtr(Unit unitPtr) {
        this.unitPtr = unitPtr;
    }
}
