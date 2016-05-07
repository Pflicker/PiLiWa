package de.beaverstudios.plw.Buildings;

import com.badlogic.gdx.Gdx;

import de.beaverstudios.plw.Hud.Hud;
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
                timeSinceSpawn = 0;
            }
        }
    }

    public Boolean buyBuilding(BuildingTypes buildingType, Integer slot, Player p){

        if (p.getMoney() >= buildingType.getPrice()){
            createBuilding(buildingType, slot, p);
            return true;
        } else {
            if(!p.isCom()) {
                GameScreen.hud.noticeWindow.setVisible(true);
            }
            return false;
        }

    }

    public void createBuilding(BuildingTypes buildingType, Integer slot, Player p){
        for (int i = 0; i < p.getBuildings().size(); i++){
            sellBuilding(i,p);
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

    }

    public void sellBuilding(Integer slot, Player p){
        if(p.getBuildings().get(slot).getSlot() == slot){
            p.getBuildings().remove(slot);
            p.addMoney((int)(p.getBuildings().get(slot).getThisType().getPrice() * 0.5));
            Gdx.app.log("BuildingManager: ", "Building on Slot " + String.format("%01d",slot) + " removed @ Player" + p);
        }
    }

    public float getTimeSinceSpawn() {
        return timeSinceSpawn;
    }

    public void setTimeSinceSpawn(float timeSinceSpawn) {
        this.timeSinceSpawn = timeSinceSpawn;
    }
}