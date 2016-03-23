package de.beaverstudios.plw.Buildings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.lang.reflect.Constructor;

import de.beaverstudios.plw.TextureManager;
import de.beaverstudios.plw.Units.Marine;
import de.beaverstudios.plw.Units.Unit;
import de.beaverstudios.plw.Units.UnitManager;

import static de.beaverstudios.plw.Units.Unit.createObject;

/**
 * Created by Grass on 3/3/2016.
 */
public abstract class Building {
    public Image buildingImage;
    public Integer price;
    public String buildingName;
    public Integer incomeRaise;
    public Integer player;
    public Integer slot;
    public String unitName;
    public BuildingTypes thisType;
    public Unit ghostUnit;
    public Unit unit;



    public Building() {
    }

    public void create(){
        buildingName = thisType.getBuildingName();
        price = thisType.getPrice();
        incomeRaise = thisType.getIncomeRaise();
        unitName = thisType.getUnitName();
        buildingImage = thisType.getImage();
        unit = thisType.getUnit();

    }


    public void update(float dt){

    }
    public void spawnUnit(){

    }

    /*public void spawnUnit(){
        if (player == 0) {
            Class[] intArgsClass = new Class[] {int.class, int.class};
            Object[] intArgs = new Object[] {player, slot};
            Constructor intArgsConstructor = null;
            try {
                intArgsConstructor =
                        unitDefinition.getConstructor(intArgsClass);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            unitDefinition = (Class) createObject(intArgsConstructor, intArgs);
            //unit.getClass().getDeclaredConstructors(intArgs).newInstance(0, slot);
        }
    }*/

    public int getSlot() {
        return slot;
    }
    public Image getBuildingImage() {
        return buildingImage;
    }

    public String getUnitName() {
        return unitName;
    }
}
