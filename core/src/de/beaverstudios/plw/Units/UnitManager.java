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

    public UnitManager() {

        ghostMarine = new Marine(2,9);

        grid = new Grid();

        playerUnits.add(new Base(1));
        comUnits.add(new Base(0));
    }

    public void update(float dt) {



        grid.update();

        for (Unit u : comUnits) {

                if(rangeCheck(u)) {
                    u.setX((u.getX() - u.getMovementspeed() * dt));
                }
        }

        for (Unit u : playerUnits) {

                if(rangeCheck(u)) {
                    u.setX((u.getX() - u.getMovementspeed() * dt));
                }
                else{

                }
        }

    }

    public boolean rangeCheck(Unit u){

        int row = 0;
        int col = 0;
        int size = 0;
        int rowMax = 0;
        int colMax = 0;

        Unit unit_ptr;

        System.out.println(u.getPlayer());

        row = u.getgridX() - 1;
        col = u.getgridY() - 1;
        rowMax = u.getgridX() + 1;
        colMax = u.getgridY() + 1;

        if (u.getgridX() == 0){
            row = u.getgridX();
        }

        if (u.getgridX() == PlwGame.GRID_RES){
            rowMax = u.getgridX();
        }

        if (u.getgridY() == 0){
            col = u.getgridY();
        }

        if (u.getgridY() == PlwGame.GRID_RES){
            colMax = u.getgridY();
        }
        while (row <= rowMax) {

            while (col <= colMax) {

                if (u.getPlayer() == 0) {
                    size = grid.gridTable.get(row).get(col).size();
                    for (int k = 0; k < size; k++) {
                        unit_ptr = grid.gridTable.get(row).get(col).get(k);

                        if (unit_ptr.getPlayer() == 1) {
                            return false;
                        }
                    }
                }
                if (u.getPlayer() == 1){
                    size = grid.gridTable.get(row).get(col).size();
                    for (int k = 0; k < size; k++) {
                        unit_ptr = grid.gridTable.get(row).get(col).get(k);
                        if (unit_ptr.getPlayer() == 0) {
                            return false;
                        }
                    }

                }
                col++;
            }
            row++;
        }

        return true;
    }

    public void render(SpriteBatch batch) {
        for (Unit u : comUnits) {
            batch.draw(u.getSkin(),u.getX(),u.getY(),u.getW(),u.getH());
            u.healthBar.draw(batch, 1, u.getX(), u.getY() + u.getH() + 1, u.getW(), 1, u.getLife());
        }

        for (Unit u : playerUnits) {
            batch.draw(u.getSkin(),u.getX(),u.getY(),u.getW(),u.getH());
            u.healthBar.draw(batch, 1, u.getX(), u.getY() + u.getH() + 1, u.getW(), 1, u.getLife());
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
