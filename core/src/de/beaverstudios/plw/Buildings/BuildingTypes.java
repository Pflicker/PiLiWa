package de.beaverstudios.plw.Buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import de.beaverstudios.plw.Player.Game;
import de.beaverstudios.plw.TextureManager;
import de.beaverstudios.plw.Units.Marine;
import de.beaverstudios.plw.Units.Melee;
import de.beaverstudios.plw.Units.Unit;
import de.beaverstudios.plw.Units.UnitManager;

/**
 * Created by Grass on 3/3/2016.
 */
public enum BuildingTypes{
    BARRACKS(0,"Barracks","Marine",UnitManager.ghostMarine,50, TextureManager.IMGBARRACKS,162,138),
    BUILDING2(1,"Building2","Melee",UnitManager.ghostMelee,50, TextureManager.IMGBUILDING2,176,125),
    BUILDING3(2,"Building3","Sniper",UnitManager.ghostSniper,50, TextureManager.IMGBUILDING3,84,210),
    BUILDING4(3,"Building4","MeleeTank",UnitManager.ghostMeleeTank,50, TextureManager.IMGBUILDING4,275,25),
    BUILDING5(4,"Building5","DarkTemplar",UnitManager.ghostDarkTemplar,50, TextureManager.IMGBUILDING5,120,175);

    private final String buildingName;
    private final String unitName;
    private Unit unit;
    private final Integer price;
    private final Image image;
    private final Integer index;
    private final Integer toughness;
    private final Integer power;

    BuildingTypes(Integer index, String buildingName, String unitName,Unit unit, Integer price, Image image,Integer toughness, Integer power){
        this.buildingName = buildingName;
        this.unitName = unitName;
        this.unit = unit;
        this.price = price;
        this.image = image;
        this.index = index;
        this.toughness = toughness;
        this.power = power;
    }

    public static BuildingTypes getNameByIndex(int i){
        BuildingTypes t;
        t = BARRACKS;
        switch (i){
            case 0:
            {
                t = BARRACKS;
                break;
            }
            case 1: {
                t = BUILDING2;
                break;
            }
            case 2:{
                t = BUILDING3;
                break;
            }
            case 3:{
                t = BUILDING4;
                break;
            }
            case 4:{
                t =BUILDING5;
                break;
            }
        }
        return t;

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

    public Unit getUnitByIndex(int i) {
        Unit u;
        u = UnitManager.ghostMarine;
        switch (i) {
            case 0:{
                u = UnitManager.ghostMarine;
                break;
            }
            case 1:{
                u = UnitManager.ghostMelee;
                break;
            }
            case 2:{
                u = UnitManager.ghostSniper;
                break;
            }
            case 3:{
                u = UnitManager.ghostMeleeTank;
                break;
            }
            case 4:{
                u = UnitManager.ghostSniper;
                break;
            }
        }
        return u;
    }

    public Unit getUnit(){
        return unit;
    }

    public Integer getToughness() {
        return toughness;
    }

    public Integer getPower() {
        return power;
    }

    public Image getImage() {
        return image;
    }

}


