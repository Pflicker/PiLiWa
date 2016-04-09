package de.beaverstudios.plw.Buildings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

import de.beaverstudios.plw.Hud.DialogPlacement;
import de.beaverstudios.plw.Hud.GameInfo;
import de.beaverstudios.plw.Hud.Notice;
import de.beaverstudios.plw.Player.Game;
import de.beaverstudios.plw.Player.Player;
import de.beaverstudios.plw.Screens.GameScreen;

public class BuildingManager {

    private float timeSinceSpawn;

    public BuildingManager() {
    }

    public void update(float dt) {

        timeSinceSpawn += dt;
        if (timeSinceSpawn > 10) {
            for (Player p : Game.players) {
                for (de.beaverstudios.plw.Buildings.Building b : p.getBuildings()) {
                    b.spawnUnit();

                }
                //Gdx.app.log("Unit spawned", String.format("%03f", timeSinceSpawn));
                timeSinceSpawn = 0;
            }
        }
    }

    public static Boolean buyBuilding(BuildingTypes buildingType, Integer slot, Player p){

        if (p.getMoney() >= buildingType.getPrice()){
            createBuilding(buildingType, slot, p);
            return true;
        } else {
            if(!p.isCom()) {
                Notice.noticeWindow.setVisible(true);
            }
            return false;
        }

    }

    public static void createBuilding(BuildingTypes buildingType, Integer slot, Player p){
        for (int i = 0; i < p.getBuildings().size(); i++){
            if(p.getBuildings().get(i).getSlot() == slot){
                p.getBuildings().remove(i);
                Gdx.app.log("BuildingManager: ", "Building on Slot " + String.format("%01d",slot) + " removed @ Player" + p);
            }
        }
        switch(buildingType){
            case BARRACKS:
                p.getBuildings().add(new Barracks(slot, p));
                Gdx.app.log("BuildingManager: ", "Barracks added");
                break;
            case BUILDING2:
                p.getBuildings().add(new Building2(slot, p));
                Gdx.app.log("BuildingManager: ", "Building 2 added");
                break;
            case BUILDING3:
                p.getBuildings().add(new Building3(slot, p));
                Gdx.app.log("BuildingManager: ", "Building 3 added");
                break;
            case BUILDING4:
                p.getBuildings().add(new Building4(slot, p));
                Gdx.app.log("BuildingManager: ", "Building 4 added");
                break;
            case BUILDING5:
                p.getBuildings().add(new Building5(slot, p));
                Gdx.app.log("BuildingManager: ", "Building 5 added");
                break;
            default:
                Gdx.app.log("BuildingManager: ", "No Building found");
        }

        p.addMoney(-buildingType.getPrice());
        //Gdx.app.log("BuildingManager: ", "Slot " + String.format("%01d", slot));
        //Gdx.app.log("BuildingManager: ", "playerBuildingsSize " + String.format("%01d", p.getBuildings().size()));
    }

    public float getTimeSinceSpawn() {
        return timeSinceSpawn;
    }

    public void setTimeSinceSpawn(float timeSinceSpawn) {
        this.timeSinceSpawn = timeSinceSpawn;
    }
}