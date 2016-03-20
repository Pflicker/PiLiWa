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
    public static Unit target;

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
                    u.setX((u.getX() + u.getMovementspeed() * dt));

                }
                else{
                    fight(u);
                }
        }

        for (Unit u : playerUnits) {

                if(rangeCheck(u)) {
                    u.setX((u.getX() - u.getMovementspeed() * dt));
                }
                else{
                    fight(u);
                }
        }

        killUnits();

    }

    public boolean rangeCheck(Unit u){

        int row;
        int col;
        int size;
        int rowMax;
        int colMax;
        double dist;
        double distTarget = 10000;
        Boolean trigger = false;

        Unit unit_ptr;

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
        for (int i = row; i < rowMax; i++) {
            for (int j = col; j < colMax; j++) {

                size = grid.gridTable.get(i).get(j).size();
                if (u.getPlayer() == 0) {
                    for (int k = 0; k < size; k++) {
                        if (size > 0){
                            unit_ptr = grid.gridTable.get(i).get(j).get(k);
                            if (unit_ptr.getPlayer() == 1) {

                                dist = java.lang.Math.sqrt(java.lang.Math.pow(u.getX() - unit_ptr.getX(), 2 ) + java.lang.Math.pow(u.getY() - unit_ptr.getY(), 2 ));
                                if (dist < u.getRange()) {
                                    target = unit_ptr;
                                    trigger = true;
                                    if (dist < distTarget) {
                                        distTarget = dist;

                                    }
                                }
                            }
                        }
                    }
                }
                if (trigger){
                    return false;
                }
                if (u.getPlayer() == 1){
                    for (int k = 0; k < size; k++) {
                        if (size > 0){
                            unit_ptr = grid.gridTable.get(i).get(j).get(k);
                           if (unit_ptr.getPlayer() == 0) {
                                dist = java.lang.Math.sqrt(java.lang.Math.pow(u.getX() - unit_ptr.getX(), 2 ) + java.lang.Math.pow(u.getY() - unit_ptr.getY(), 2 ));
                                if (dist < u.getRange()) {
                                    target = unit_ptr;
                                    trigger = true;
                                    if (dist < distTarget) {
                                        distTarget = dist;
                                        target = unit_ptr;
                                    }
                                }
                            }

                        }
                    }
                }
                if (trigger) {
                    return false;
                }/*
                for (int k = 0; k < size; k++) {
                    unit_ptr = grid.gridTable.get(row).get(col).get(k);
                    dist = java.lang.Math.sqrt(java.lang.Math.pow(u.getX() - unit_ptr.getX(), 2 ) + java.lang.Math.pow(u.getY() - unit_ptr.getY(), 2 ));
                    if (!unit_ptr.buildung) {
                        if (u.getX() != unit_ptr.getX()){
                            if (dist < PlwGame.MELE_RANGE) {
                                trigger = true;
                            }
                        }
                    }
                }*/

            }

        }

        return true;
    }

    public void fight(Unit u) {
        //vllt rausnehmen für performence
        double dist = java.lang.Math.sqrt(java.lang.Math.pow(u.getX() - target.getX(), 2 ) + java.lang.Math.pow(u.getY() - target.getY(), 2 ));
        if (dist < u.getRange()){
            u.timeSinceAttack += Gdx.graphics.getDeltaTime();
            if (u.timeSinceAttack > u.getAttackspeed()) {
                target.setLife(target.getLife() - u.getDamage());
                u.timeSinceAttack = 0;
                System.out.println("Live: " + target.getLife() + " " + u + " " + target);
            }
        }
    }
    public void killUnits() {
        for (int i = 0; i < playerUnits.size(); i++) {
            if (playerUnits.get(i).getLife() < 1){
                playerUnits.remove(i);
                System.out.println("PlayerUnit killed");
            }
            else if (playerUnits.get(i).getX() < 0 || playerUnits.get(i).getX() > PlwGame.V_WIDTH){
                playerUnits.remove(i);
                System.out.println("PlayerUnit disposed");
            }
            else if (playerUnits.get(i).getY() < 0 || playerUnits.get(i).getY() > PlwGame.V_HEIGHT){
                playerUnits.remove(i);
                System.out.println("PlayerUnit disposed");
            }
        }
        for (int i = 0; i < comUnits.size(); i++) {
            if (comUnits.get(i).getLife() < 1) {
                comUnits.remove(i);
                System.out.println("ComUnit killed");
            }
            else if (comUnits.get(i).getX() < 0 || comUnits.get(i).getX() > PlwGame.V_WIDTH){
                comUnits.remove(i);
                System.out.println("ComUnit disposed");
            }
            else if (comUnits.get(i).getY() < 0 || comUnits.get(i).getY() > PlwGame.V_HEIGHT){
                comUnits.remove(i);
                System.out.println("ComUnit disposed");
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (Unit u : comUnits) {
            batch.draw(u.getSkin(),u.getX(),u.getY(),u.getW(),u.getH());
            u.healthBar.draw(batch, 1, u.getX(), u.getY() + u.getH() + 1, u.getW(), 1, u.getLife(),u.getMaxLife());
        }

        for (Unit u : playerUnits) {
            batch.draw(u.getSkin(),u.getX(),u.getY(),u.getW(),u.getH());
            u.healthBar.draw(batch, 1, u.getX(), u.getY() + u.getH() + 1, u.getW(), 1, u.getLife(),u.getMaxLife());
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
