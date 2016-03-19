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
    public static Marine ghostMarine;

    public UnitManager() {

        ghostMarine = new Marine(2);

        playerBase = new Base(1);
        comBase = new Base(0);

        playerUnits.add(playerBase);
        comUnits.add(comBase);
    }

    public void update(float dt) {

        for (int i = 0; i > comUnits.size(); i++) {
            comUnits.get(i).update(dt);
            if (comUnits.get(i).getX() < 0) {
                comUnits.remove(i);
            }
        }
        for (Unit u : comUnits) {
            u.update(dt);
        }

        for (int i = 0; i > playerUnits.size(); i++) {
            playerUnits.get(i).update(dt);
            if (playerUnits.get(i).getX() < 0) {
                playerUnits.remove(i);
            }
        }
        for (Unit u : playerUnits) {
            u.update(dt);
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
