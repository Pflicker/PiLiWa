package de.beaverstudios.plw.Units;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by Grass on 3/3/2016.
 */
public class UnitManager {

    public final ArrayList<Unit> comUnits = new ArrayList<Unit>();
    public final ArrayList<Unit> playerUnits = new ArrayList<Unit>();
    private final Base playerBase;
    private final Base comBase;
    public int unitsSpawned;

    public UnitManager() {

        playerBase = new Base(1);
        comBase = new Base(0);

        playerUnits.add(playerBase);
        comUnits.add(comBase);
    }

    public void update(float dt) {

        for (int i = 0; i > comUnits.size(); i++) {
           comUnits.get(i).update(dt);
            if(comUnits.get(i).getX() < 0){
                comUnits.remove(i);
            }
        }
        for (Unit u : comUnits){
            u.update(dt);
        }

        for (int i = 0; i > playerUnits.size(); i++) {
            playerUnits.get(i).update(dt);
            if(playerUnits.get(i).getX() < 0){
                playerUnits.remove(i);
            }
        }
        for (Unit u : playerUnits){
            u.update(dt);
        }

        //checkCollisions();
    }

    public void render(SpriteBatch batch) {
        for (Unit u : comUnits){
            u.draw(batch);
        }

        for (Unit u : playerUnits){
            u.draw(batch);
        }

    }

    /*private Array<Enemy> getEnemies() {
        Array<Enemy> ret = new Array<Enemy>();
        for (Entity e : entities)
            if (e instanceof Enemy)
                ret.add((Enemy)e);
        return ret;
    }

    private Array<Missile> getMissiles() {
        Array<Missile> ret = new Array<Missile>();
        for (Entity e : entities)
            if (e instanceof Missile)
                ret.add((Missile)e);
        return ret;
    }*/

}
