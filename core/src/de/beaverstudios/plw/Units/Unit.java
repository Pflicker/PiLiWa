package de.beaverstudios.plw.Units;

/**
 * Created by Grass on 3/2/2016.
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

import de.beaverstudios.plw.Screens.GameScreen;
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
    static ArrayList<Unit> NNtable = new ArrayList<Unit>();
    static float[] vec =new float[2];


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

    public boolean isFight() {
        return fight;
    }

    public void setFight(boolean fight) {
        this.fight = fight;
    }

    int slot;
    Unit target;
    float distTarget;
    boolean fight;


    public void update(float dt) {


        //if (rangeCheck(this)) {
            if (!buildung) {

                Path.findPath(this);
                x += dx * movementspeed * dt;
                y += dy * movementspeed * dt;

            }
            if (fight){
                fight();
            }

        //} else {
        //    fight();
        //}
    }

    public boolean rangeCheck(Unit u){

        int row;
        int col;
        int size;
        int rowMax;
        int colMax;
        double dist;
        Boolean trigger1 = false;
        Boolean trigger2 = false;

        Unit unit_ptr;

        row = u.getgridX() - 1;
        col = u.getgridY() - 1;
        rowMax = u.getgridX() + 1;
        colMax = u.getgridY() + 1;

        if (gridX == 0){
            row = gridX;
        }

        if (gridX == PlwGame.GRID_RES){
            rowMax = u.gridX;
        }

        if (gridY == 0){
            col = gridY;
        }

        if (gridY == PlwGame.GRID_RES){
            colMax = u.getgridY();
        }
        for (int i = row; i < rowMax; i++) {
            for (int j = col; j < colMax; j++) {

                size = Grid.gridTable.get(i).get(j).size();

                for (int k = 0; k < size; k++) {
                    unit_ptr = Grid.gridTable.get(i).get(j).get(k);
                    if (unit_ptr.getPlayer() != player) {
                        dist = java.lang.Math.sqrt(java.lang.Math.pow(u.getX() - unit_ptr.getX(), 2) + java.lang.Math.pow(u.getY() - unit_ptr.getY(), 2));
                        if (dist < PlwGame.DET_RANGE) {
                            u.setTarget(unit_ptr);
                            trigger1 = true;
                            if (dist < range) {
                                trigger2 = true;
                                if (dist < u.distTarget) {
                                    distTarget = (float) dist;
                                    target = unit_ptr;
                                }
                            }
                        }
                    }
                }
            }

            if (trigger2) {
                return false;

            }

        }
        if (!trigger1) {
            if (u.getPlayer() == 1) {
                u.setTarget(UnitManager.comUnits.get(0));
                u.setDistTarget((float) java.lang.Math.sqrt(java.lang.Math.pow(u.getX() - u.getTarget().getX(), 2) + java.lang.Math.pow(u.getY() - u.getTarget().getY(), 2)));

            }
            if (u.getPlayer() == 0) {
                u.setTarget(UnitManager.playerUnits.get(0));
                u.setDistTarget((float) java.lang.Math.sqrt(java.lang.Math.pow(u.getX() - u.getTarget().getX(), 2) + java.lang.Math.pow(u.getY() - u.getTarget().getY(), 2)));
            }
        }
        return true;
    }

    public void fight() {
        //vllt rausnehmen für performence
        double dist = java.lang.Math.sqrt(java.lang.Math.pow(x - target.x, 2 ) + java.lang.Math.pow(y - target.y, 2 ));
        if (dist < range){
            timeSinceAttack += Gdx.graphics.getDeltaTime();
            if (timeSinceAttack > attackspeed) {
                target.setLife(target.getLife() - damage);
                timeSinceAttack = 0;
                //System.out.println("Live: " + target.getLife() + " " + this + " " + this.target);
            }
        }
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
