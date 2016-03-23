package de.beaverstudios.plw.Units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

import de.beaverstudios.plw.Animations.UnitValue;
import de.beaverstudios.plw.Player.Game;
import de.beaverstudios.plw.PlwGame;
import de.beaverstudios.plw.Screens.GameScreen;

/**
 * Created by Grass on 3/3/2016.
 */
public class UnitManager {

    public static ArrayList<Unit> comUnits = new ArrayList<Unit>();
    public static ArrayList<Unit> playerUnits = new ArrayList<Unit>();
    private static Grid grid;
    public static int unitsSpawned;
    public static Marine ghostMarine;
    public static Cat ghostCat;
    public static Unit target;
    public static ArrayList<UnitValue> unitValues = new ArrayList<UnitValue>();

    public UnitManager() {

        ghostMarine = new Marine(2,9);
        ghostCat = new Cat(2,9);

        grid = new Grid();

        playerUnits.add(new Base(1));
        comUnits.add(new Base(0));
    }

    public void update(float dt) {
        grid.update();
        for (Unit u : comUnits) {

                if(rangeCheck(u)) {
                    u.setX((u.getX() + u.getMovementspeed() * dt));
                    u.stateTime += dt;
                }
                else{
                    fight(u);
                }
        }
        for (Unit u : playerUnits) {

                if(rangeCheck(u)) {
                    u.setX((u.getX() - u.getMovementspeed() * dt));
                    u.stateTime += dt;
                }
                else{
                    fight(u);
                }
        }
        killUnits();
        for (int i = 0;unitValues.size() > i;i++){
            unitValues.get(i).update(dt);
            if (unitValues.get(i).getTimer() > 2f){
                unitValues.remove(i);
            }
        }
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
                unitValues.add(new UnitValue(playerUnits.get(i).x+playerUnits.get(i).getW()/2,playerUnits.get(i).y+playerUnits.get(i).getH(),playerUnits.get(i).value));
                Game.player1.addMoney(playerUnits.get(i).getValue());
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
                unitValues.add(new UnitValue(comUnits.get(i).x,comUnits.get(i).y+comUnits.get(i).getH()+10,comUnits.get(i).value));
                Game.player2.addMoney(comUnits.get(i).getValue());
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
            u.currentFrame = u.walkAnimation.getKeyFrame(u.stateTime, true);
            batch.draw(u.getCurrentFrame(),u.getX(),u.getY(),u.getW(),u.getH());
            u.healthBar.draw(batch, 1, u.getX(), u.getY() + u.getH() + 1, u.getW(), 1, u.getLife(),u.getMaxLife());
        }

        for (Unit u : playerUnits) {
            u.currentFrame = u.walkAnimation.getKeyFrame(u.stateTime, true);
            if(!u.currentFrame.isFlipX()){u.currentFrame.flip(true,false);}
            batch.draw(u.getCurrentFrame(),u.getX(),u.getY(), u.getW() / 2, u.getH() / 2, u.getW(), u.getH(), 1, 1, u.getRotate());
            u.healthBar.draw(batch, 1, u.getX(), u.getY() + u.getH() + 1, u.getW(), 1, u.getLife(),u.getMaxLife());
        }
        for (int i = 0;unitValues.size() > i;i++){
            unitValues.get(i).render(batch, new BitmapFont());
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
