package de.beaverstudios.plw.Units;

/**
 * Created by Grass on 3/2/2016.
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

import de.beaverstudios.plw.Units.Healthbar.HealthBar;
import de.beaverstudios.plw.PlwGame;

public abstract class Unit {

    String name;
    float x;
    float y;
    Integer player;
    Integer w;
    Integer h;
    float dx;
    float dy;
    Integer maxLife;
    Integer life;

    public void setDx(float dx) {
        this.dx = dx;
    }

    Integer armor;
    int gridX;
    int gridY;
    float movementspeed;
    float range;

    public void setDy(float dy) {
        this.dy = dy;
    }

    Integer damage;
    float timeSinceAttack;

    public Unit getTarget() {
        return target;
    }

    public void setTarget(Unit target) {
        this.target = target;
    }

    public float getDx() {
        return dx;
    }

    public float getDy() {
        return dy;
    }

    public float getDistTarget() {
        return distTarget;
    }

    public void setDistTarget(float distTarget) {
        this.distTarget = distTarget;
    }

    float attackspeed;
    Texture skin;
    Boolean invisible;
    Boolean flying;
    Boolean attackFlying;
    Boolean attackGround;
    Boolean stealthDetect;
    Boolean direction;
    Boolean buildung;
    float rotate;
    HealthBar healthBar;
    int slot;
    Unit target;
    float distTarget;



    public void update(float dt) {

        grid.update();

        for (Unit u : comUnits) {

            if(rangeCheck(u)) {

                if(!u.buildung) {
                    //System.out.println(u.getX() + " " + u.getY());
                    path.findPath(u);
                    //System.out.println(u.getX() + " " + u.getY());
                    u.setX((u.getX() + u.getDx() * dt));
                    u.setY((u.getY() + u.getDy() * dt));
                    //System.out.println(u.getX() + " " + u.getY());
                }
                u.setX((u.getX() + u.getMovementspeed() * dt));

            }
            else{
                fight(u);
            }
        }

        for (Unit u : playerUnits) {

            if(rangeCheck(u)) {
                if(!u.buildung) {
                    //System.out.println(u.getX() + " " + u.getY());
                    path.findPath(u);
                    //System.out.println(u.getX() + " " + u.getY());
                    u.setX((u.getX() + u.getDx() * dt));
                    u.setY((u.getY() + u.getDy() * dt));
                    //System.out.println(u.getX() + " " + u.getY());
                }
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
                                    u.setTarget(unit_ptr);
                                    trigger = true;
                                    if (dist < u.distTarget) {
                                        u.setDistTarget((float) dist);
                                        u.setTarget(unit_ptr);
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
                                    u.setTarget(unit_ptr);
                                    trigger = true;
                                    if (dist < u.distTarget) {
                                        u.setDistTarget((float) dist);
                                        u.setTarget(unit_ptr);
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
        if (u.getPlayer() == 1){
            u.setTarget(comUnits.get(0));
            u.setDistTarget((float) java.lang.Math.sqrt(java.lang.Math.pow(u.getX() - u.getTarget().getX(), 2) + java.lang.Math.pow(u.getY() - u.getTarget().getY(), 2)));

        }
        if (u.getPlayer() == 0){
            u.setTarget(playerUnits.get(0));
            u.setDistTarget((float)java.lang.Math.sqrt(java.lang.Math.pow(u.getX() - u.getTarget().getX(), 2) + java.lang.Math.pow(u.getY() - u.getTarget().getY(), 2)));
        }
        return true;
    }

    public void fight(Unit u) {
        //vllt rausnehmen für performence
        double dist = java.lang.Math.sqrt(java.lang.Math.pow(u.getX() - u.target.getX(), 2 ) + java.lang.Math.pow(u.getY() - u.target.getY(), 2 ));
        if (dist < u.getRange()){
            u.timeSinceAttack += Gdx.graphics.getDeltaTime();
            if (u.timeSinceAttack > u.getAttackspeed()) {
                u.target.setLife(u.target.getLife() - u.getDamage());
                u.timeSinceAttack = 0;
                System.out.println("Live: " + u.target.getLife() + " " + u + " " + u.target);
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


    public float getSpawnPointX(int player, int slot) {
        float x = 300f;
        if (player == 0) {
            if (slot == 0 || slot == 3 || slot == 6) {
                x = 30f;
            }
            if (slot == 1 || slot == 4 || slot == 7) {
                x = 50f;
            }
            if (slot == 2 || slot == 5 || slot == 8) {
                x = 70f;
            }
        }

        if (player == 1) {
            if (slot == 0 || slot == 3 || slot == 6) {
                x = 560f;
            }
            if (slot == 1 || slot == 4 || slot == 7) {
                x = 580f;
            }
            if (slot == 2 || slot == 5 || slot == 8) {
                x = 600f;
            }
        }
        gridX = (int)(x/ PlwGame.V_WIDTH*PlwGame.GRID_RES);
        return x;
    }

    public float getSpawnPointY(int player, int slot){
        float y = 0f;
        if (player == 0) {
            if (slot <= 2) {
                y = (float)((PlwGame.V_HEIGHT/2) + 20);
            }
            if (slot > 2 && slot <= 5) {
                y = (float)(PlwGame.V_HEIGHT/2);
            }
            if (slot > 5 && slot <= 8) {
                y = (float)((PlwGame.V_HEIGHT/2) - 20);
            }
        }
        if (player == 1) {
            if (slot <= 2) {
                y = (float)((PlwGame.V_HEIGHT/2) +20);
            }
            if (slot > 2 && slot <= 5) {
                y = (float)(PlwGame.V_HEIGHT/2);
            }
            if (slot > 5 && slot <= 8) {
                y = (float)((PlwGame.V_HEIGHT/2) -20);
            }
        }
        gridY = (int)(y/PlwGame.V_HEIGHT*PlwGame.GRID_RES);
        return y;
    }

    public void initShield(){

    }

    public void draw(SpriteBatch batch) {
        batch.draw(skin,x,y,w,h);
        healthBar.draw(batch,1,x, y+h+1,w,1,life,maxLife);
    }

    public void dispose() {
        this.healthBar.dispose();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayer(Integer player) {
        this.player = player;
    }

    public Integer getPlayer() {
        return player;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Integer getgridX() {
        return gridX;
    }

    public void setgridX(Integer gridX) {
        this.gridX = gridX;
    }

    public Integer getgridY() {
        return gridY;
    }

    public void setgridY(Integer gridY) {
        this.gridY = gridY;
    }

    public Integer getW() {
        return w;
    }

    public void setW(Integer w) {
        this.w = w;
    }

    public Integer getH() {
        return h;
    }

    public void setH(Integer h) {
        this.h = h;
    }


    public void setDx(Integer dx) {
        this.dx = dx;
    }


    public void setDy(Integer dy) {
        this.dy = dy;
    }

    public Integer getMaxLife() {
        return maxLife;
    }

    public void setMaxLife(Integer maxLife) {
        this.maxLife = maxLife;
    }

    public Integer getLife() {
        return life;
    }

    public void setLife(Integer life) {
        this.life = life;
    }

    public Integer getArmor() {
        return armor;
    }

    public void setArmor(Integer armor) {
        this.armor = armor;
    }

    public float getMovementspeed() {
        return movementspeed;
    }

    public void setMovementspeed(float movementspeed) {
        this.movementspeed = movementspeed;
    }

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public int getGridX() {
        return gridX;
    }

    public float getAttackspeed() {
        return attackspeed;
    }

    public float getTimeSinceAttack() {
        return timeSinceAttack;
    }

    public void setTimeSinceAttack(float timeSinceAttack) {
        this.timeSinceAttack = timeSinceAttack;
    }

    public void setAttackspeed(float attackspeed) {
        this.attackspeed = attackspeed;
    }

    public Texture getSkin() {
        return skin;
    }

    public void setSkin(Texture skin) {
        this.skin = skin;
    }

    public Boolean getInvisible() {
        return invisible;
    }

    public void setInvisible(Boolean invisible) {
        this.invisible = invisible;
    }

    public Boolean getFlying() {
        return flying;
    }

    public void setFlying(Boolean flying) {
        this.flying = flying;
    }

    public Boolean getAttackFlying() {
        return attackFlying;
    }

    public void setAttackFlying(Boolean attackFlying) {
        this.attackFlying = attackFlying;
    }

    public Boolean getAttackGround() {
        return attackGround;
    }

    public void setAttackGround(Boolean attackGround) {
        this.attackGround = attackGround;
    }

    public Boolean getStealthDetect() {
        return stealthDetect;
    }

    public void setStealthDetect(Boolean stealthDetect) {
        this.stealthDetect = stealthDetect;
    }

    public Boolean getDirection() {
        return direction;
    }

    public void setDirection(Boolean direction) {
        this.direction = direction;
    }

    public float getRotate() {
        return rotate;
    }

    public void setRotate(float rotate) {
        this.rotate = rotate;
    }

}
