package de.beaverstudios.plw.Buildings;

import de.beaverstudios.plw.Player.Player;
import de.beaverstudios.plw.PlwGame;
import de.beaverstudios.plw.Units.Unit;
import de.beaverstudios.plw.Units.UnitManager;

/**
 * Created by Grass on 3/3/2016.
 */
public class Building {
    public Integer price;
    public String buildingName;
    public Player player;
    public Integer slot;
    public Integer unitID;
    public BuildingTypes thisType;
    Unit unitPtr;

    public Building(BuildingTypes buildingTypes,Integer slot,Player player, Integer unitID) {
        thisType = buildingTypes;
        this.slot=slot;
        this.player=player;
        this.unitID = unitID;
        create();
    }

    public void create(){
        buildingName = thisType.getBuildingName();
        price = thisType.getPrice();
    }

    public void spawnUnit(){
        player.getUnits().add(new Unit(player.getUnitIDs().get(unitID),slot,player, PlwGame.MOVEMENTSPEED));
    }

    public int getSlot() {
        return slot;
    }

    public BuildingTypes getThisType(){ return thisType; }

    public Unit getUnitPtr() {
        return unitPtr;
    }

    public void setUnitPtr(Unit unitPtr) {
        this.unitPtr = unitPtr;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public Integer getUnitID() {
        return unitID;
    }

    public void setUnitID(Integer unitID) {
        this.unitID = unitID;
    }

    public void setThisType(BuildingTypes thisType) {
        this.thisType = thisType;
    }

}
