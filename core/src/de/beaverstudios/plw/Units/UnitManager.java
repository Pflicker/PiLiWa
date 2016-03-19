package de.beaverstudios.plw.Units;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by Grass on 3/3/2016.
 */
public class UnitManager {

    public static ArrayList<Unit> comUnits = new ArrayList<Unit>();
    public static ArrayList<Unit> playerUnits = new ArrayList<Unit>();
    private Base playerBase;
    private Base comBase;
    public static int unitsSpawned;
    public Marine marine;

    public UnitManager() {

        marine = new Marine(1);

        playerBase = new Base(1);
        comBase = new Base(0);

        playerUnits.add(playerBase);
        comUnits.add(comBase);
    }

    public void update(float dt) {


        for (Unit u : comUnits) {
            u.setX((u.getX() - u.getMovementspeed() * dt));
        }

        for (Unit u : playerUnits) {
            u.setX((u.getX() - u.getMovementspeed() * dt));
        }

    }

    public void render(SpriteBatch batch) {
        for (Unit u : comUnits) {
            batch.draw(u.getSkin(),u.getX(),u.getY(),u.getW(),u.getH());
            u.healthBar.draw(batch,1,u.getX(), u.getY()+u.getH()+1,u.getW(),1,u.getLife());
        }

        for (Unit u : playerUnits) {
            batch.draw(u.getSkin(),u.getX(),u.getY(),u.getW(),u.getH());
            u.healthBar.draw(batch,1,u.getX(), u.getY()+u.getH()+1,u.getW(),1,u.getLife());
        }

    }

    public static ArrayList<Unit> getComUnits() {
        return comUnits;
    }

    public static void setComUnits(ArrayList<Unit> comUnits) {
        UnitManager.comUnits = comUnits;
    }

    public static ArrayList<Unit> getPlayerUnits() {
        return playerUnits;
    }

    public static void setPlayerUnits(ArrayList<Unit> playerUnits) {
        UnitManager.playerUnits = playerUnits;
    }
}
