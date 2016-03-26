package de.beaverstudios.plw.Buildings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

import de.beaverstudios.plw.Hud.DialogPlacement;
import de.beaverstudios.plw.Hud.GameInfo;
import de.beaverstudios.plw.Player.Game;
import de.beaverstudios.plw.Player.Player;

public class BuildingManager {

    private static boolean buildNew;
    private static int buildNewB;
    private static int buildNewSlot;
    private static Player buildPlayer;
    private static BuildingTypes newBuildingType;
    private static boolean buildingTypeChanged;
    private float timeSinceSpawn;


    public BuildingManager() {
        newBuildingType = BuildingTypes.BARRACKS;
        buildingTypeChanged = false;
        buildNew = false;
    }

    public void update(float dt) {
        if (buildNew) {
            createBuilding();
        }
        timeSinceSpawn += dt;
        if (timeSinceSpawn > 5) {
            for (Player p : Game.players) {
                for (de.beaverstudios.plw.Buildings.Building b : p.getBuildings()) {
                    b.spawnUnit();
                }
                //Gdx.app.log("Unit spawned", String.format("%03f", timeSinceSpawn));
                timeSinceSpawn = 0;
            }
        }
    }

    public void createBuilding(){
        for (int i = 0; i < buildPlayer.getBuildings().size(); i++){
            if(buildPlayer.getBuildings().get(i).getSlot() == buildNewSlot){
                buildPlayer.getBuildings().remove(i);
                Gdx.app.log("BuildingManager: ", "Building on Slot " + String.format("%01d",buildNewSlot) + " removed @ Player" + buildPlayer);
            }
        }
        switch(newBuildingType){
            case BARRACKS:
                buildPlayer.getBuildings().add(new Barracks(buildNewSlot, Game.player2));
                Gdx.app.log("BuildingManager: ", "Barracks added");
                setBuildNew(false);
                break;
            case FACTORY:
                buildPlayer.getBuildings().add(new Factory(buildNewSlot, Game.player2));
                Gdx.app.log("BuildingManager: ", "Factory added");
                setBuildNew(false);
                break;
            default:
                Gdx.app.log("BuildingManager: ", "No Building found");
        }

        Game.player2.addIncome(newBuildingType.getIncomeRaise());
        Game.player2.addMoney(-newBuildingType.getPrice());
        Gdx.app.log("BuildingManager: ", "Slot " + String.format("%01d", buildNewSlot));
        Gdx.app.log("BuildingManager: ", "playerBuildingsSize " + String.format("%01d", buildPlayer.getBuildings().size()));
    }

    public static int getBuildNewSlot() {
        return buildNewSlot;
    }

    public static void setBuildNewSlot(int buildNewSlot) {
        BuildingManager.buildNewSlot = buildNewSlot;
    }

    public static boolean isBuildNew() {
        return buildNew;
    }

    public static void setBuildNew(boolean buildNew) {
        BuildingManager.buildNew = buildNew;
    }

    public static int getBuildNewB() {
        return buildNewB;
    }

    public static void setBuildNewB(int buildNewB) {
        BuildingManager.buildNewB = buildNewB;
    }

    public static Player getBuildPlayer() {
        return buildPlayer;
    }

    public static void setBuildPlayer(Player buildPlayer) {
        BuildingManager.buildPlayer = buildPlayer;
    }

    public static BuildingTypes getNewBuildingType() {
        return newBuildingType;
    }

    public static void setNewBuildingType(BuildingTypes newBuildingType) {
        BuildingManager.newBuildingType = newBuildingType;
    }

    public static boolean isBuildingTypeChanged() {
        return buildingTypeChanged;
    }

    public static void setBuildingTypeChanged(boolean buildingTypeChanged) {
        BuildingManager.buildingTypeChanged = buildingTypeChanged;
    }

    public float getTimeSinceSpawn() {
        return timeSinceSpawn;
    }

    public void setTimeSinceSpawn(float timeSinceSpawn) {
        this.timeSinceSpawn = timeSinceSpawn;
    }
}
