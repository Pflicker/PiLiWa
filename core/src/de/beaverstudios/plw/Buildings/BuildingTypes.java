package de.beaverstudios.plw.Buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import de.beaverstudios.plw.Player.Game;
import de.beaverstudios.plw.TextureManager;
import de.beaverstudios.plw.Units.Marine;
import de.beaverstudios.plw.Units.Unit;
import de.beaverstudios.plw.Units.UnitManager;

/**
 * Created by Grass on 3/3/2016.
 */
public enum BuildingTypes{
    BARRACKS(0,"Barracks","Marine",20,2, TextureManager.IMGBARRACKS),
    FACTORY(1,"Factory","Cat",20,2,  TextureManager.IMGFACTORY);

    private final String buildingName;
    private final String unitName;
    private final Integer price;
    private final Integer incomeRaise;
    private final Image image;
    private final Integer index;
    //private final Unit unit;

    BuildingTypes(Integer index, String buildingName, String unitName, Integer price, Integer incomeRaise, Image image){
        this.buildingName = buildingName;
        this.unitName = unitName;
        this.price = price;
        this.incomeRaise =incomeRaise;
        this.image = image;
        this.index = index;
        //this.unit = unit;
    }

    public static BuildingTypes getNameByIndex(int i){
        if(i == 0) return BARRACKS;
        if(i == 1) return FACTORY;
        return BARRACKS;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public String getUnitName() {
        return unitName;
    }

    public Integer getPrice(){
        return price;
    }

    public Integer getIndex() {
        return index;
    }

    public Integer getIncomeRaise(){
        return incomeRaise;
    }

    public Image getImage() {
        return image;
    }
/*
    public Unit getUnit() {
        return unit;
    }*/
}


