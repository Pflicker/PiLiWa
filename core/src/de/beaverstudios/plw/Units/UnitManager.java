package de.beaverstudios.plw.Units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

import de.beaverstudios.plw.PlwGame;

/**
 * Created by Grass on 3/3/2016.
 */
public class UnitManager {

    public static ArrayList<Unit> comUnits = new ArrayList<Unit>();
    public static ArrayList<Unit> playerUnits = new ArrayList<Unit>();
    private static Grid grid;
    public static int unitsSpawned;
    public static Marine ghostMarine;
    public static Path path;
    public static Unit u;

    public UnitManager() {

        ghostMarine = new Marine(2,9);

        grid = new Grid();
        path = new Path();
        playerUnits.add(new Base(1));
        comUnits.add(new Base(0));
    }




    public void update(float dt) {

        grid.update();

        for (Unit u : comUnits) {
            u.update(dt);
        }

        for (Unit u : playerUnits) {
            u.update(dt);
        }

        killUnits();
    }

    public void killUnits() {
        for (int i = 0; i < playerUnits.size(); i++) {
            if (playerUnits.get(i).getLife() < 1){
                playerUnits.remove(i);
                System.out.println("PlayerUnit killed");
            }
            else if (playerUnits.get(i).getX() < 0 || playerUnits.get(i).getX() > PlwGame.V_WIDTH){
                playerUnits.remove(i);
                System.out.println("PlayerUnit disposed0");
            }
            else if (playerUnits.get(i).getY() < 0 || playerUnits.get(i).getY() > PlwGame.V_HEIGHT){
                playerUnits.remove(i);
                System.out.println("PlayerUnit disposed1");
            }
        }
        for (int i = 0; i < comUnits.size(); i++) {
            if (comUnits.get(i).getLife() < 1) {
                comUnits.remove(i);
                System.out.println("ComUnit killed");
            }
            else if (comUnits.get(i).getX() < 0 || comUnits.get(i).getX() > PlwGame.V_WIDTH){
                comUnits.remove(i);
                System.out.println("ComUnit disposed0");
            }
            else if (comUnits.get(i).getY() < 0 || comUnits.get(i).getY() > PlwGame.V_HEIGHT){
                comUnits.remove(i);
                System.out.println("ComUnit disposed1");
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (Unit u : playerUnits){
            batch.draw(u.getSkin(), u.getX(), u.getY(), u.getW(), u.getH());
            u.healthBar.draw(batch, 1, u.getX(), u.getY() + u.getH()+ 1, u.getW(), 1,u.getLife(), u.getMaxLife());
        }
        for (Unit u : comUnits){
            batch.draw(u.getSkin(), u.getX(), u.getY(), u.getW(), u.getH());
            u.healthBar.draw(batch, 1, u.getX(), u.getY() + u.getH()+ 1, u.getW(), 1,u.getLife(), u.getMaxLife());
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
