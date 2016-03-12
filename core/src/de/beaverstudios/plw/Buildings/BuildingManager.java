package de.beaverstudios.plw.Buildings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

import de.beaverstudios.plw.Hud.GameInfo;

/**
 * Created by Grass on 3/3/2016.
 */
public class BuildingManager {

    public static ArrayList<de.beaverstudios.plw.Buildings.Building> comBuilding = new ArrayList<de.beaverstudios.plw.Buildings.Building>();
    public static Array<Building> playerBuildings = new Array<Building>(true,9);
    private static boolean buildNew;
    private static int buildNewB;
    private static int buildNewSlot;

    public BuildingManager() {
    }

    public void update(float dt) {
        if (buildNew){
            createBuilding();
        }
        for (de.beaverstudios.plw.Buildings.Building b : comBuilding){
            b.update(dt);
        }
        for (de.beaverstudios.plw.Buildings.Building b : playerBuildings){
            b.update(dt);
        }
    }

    public void createBuilding(){
        for (int i = 0; i < playerBuildings.size; i++){
            if(playerBuildings.get(i).getSlot() == buildNewSlot){
                playerBuildings.removeIndex(i);
                Gdx.app.log("BuildingManager: ", "Building on Slot " + String.format("%01d",buildNewSlot) + " removed");
            }
        }
        playerBuildings.add(new Building(buildNewSlot));
        Gdx.app.log("BuildingManager: ", "Building added");
        Gdx.app.log("BuildingManager: ", "Slot " +String.format("%01d",buildNewSlot));
        Gdx.app.log("BuildingManager: ", "playerBuildingsSize " +String.format("%01d",playerBuildings.size));
        GameInfo.setIncomePlayer(GameInfo.getIncomePlayer() + 2);
        GameInfo.setMoneyPlayer(GameInfo.getMoneyPlayer() - 10);
        setBuildNew(false);
    }

    public static ArrayList<Building> getComBuilding() {
        return comBuilding;
    }

    public static void setComBuilding(ArrayList<Building> comBuilding) {
        BuildingManager.comBuilding = comBuilding;
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

    public static Array<Building> getPlayerBuildings() {
        return playerBuildings;
    }

    public static void setPlayerBuildings(Array<Building> playerBuildings) {
        BuildingManager.playerBuildings = playerBuildings;
    }

}
