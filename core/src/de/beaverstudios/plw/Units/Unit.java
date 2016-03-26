package de.beaverstudios.plw.Units;

/**
 * Created by Grass on 3/2/2016.
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import de.beaverstudios.plw.Player.Game;
import de.beaverstudios.plw.Player.Player;
import de.beaverstudios.plw.Techs.Types.ArmorType;
import de.beaverstudios.plw.Techs.Types.DamageType;
import de.beaverstudios.plw.Units.Healthbar.HealthBar;
import de.beaverstudios.plw.PlwGame;
import de.beaverstudios.plw.Units.Healthbar.ShieldBar;

public abstract class Unit {

    String name;
    Player player;

    float x;
    float y;
    Float dx;
    Float dy;
    int gridX;
    int gridY;
    Integer w;
    Integer h;

    Integer maxLife;
    Integer life;
    Integer armor;
    ArmorType armorType;
    HealthBar healthBar;


    ShieldBar shieldBar;
    Integer maxShieldValue = 1;
    Integer shieldValue;
    Integer shieldReloadValue = 0;
    float timeSinceDamageTaken;
    float shieldReloadTimer = 2f;
    float shieldReloadStep;

    Integer damage;
    DamageType damageType;
    float timeSinceAttack;
    float attackspeed;
    float movementspeed;
    float range;
    Unit target;
    float distTarget;
    boolean fight;
    static ArrayList<Unit> NNtable = new ArrayList<Unit>();
    static float[] vec =new float[2];

    Integer value;
    Texture skin;
    Boolean invisible = false;
    Boolean flying = false;
    Boolean attackFlying = true;
    Boolean attackGround = true;
    Boolean stealthDetect = false;
    Boolean direction;
    Boolean buildung = false;
    float rotate;

    int slot;

    //Animation vars
    int        FRAME_COLS;
    int        FRAME_ROWS;
    Animation walkAnimation;
    Texture walkSheet;
    TextureRegion[] walkFrames;
    TextureRegion currentFrame;
    float stateTime;

    public void create() {
        life = maxLife;
        healthBar = new HealthBar(x, y + h + 1, w, 2, life, maxLife);

        if(!buildung){
            x = getSpawnPointX(player, slot);
            y = getSpawnPointY(player, slot);
        }

        if(armorType == ArmorType.SHIELD){
            shieldValue = maxShieldValue;
            shieldBar = new ShieldBar(x,y+h+2,w,2,shieldValue,maxShieldValue);
        }

        walkSheet = skin;
        TextureRegion[][] tmp = TextureRegion.split(this.walkSheet, this.walkSheet.getWidth()/this.FRAME_COLS, this.walkSheet.getHeight()/this.FRAME_ROWS);
        walkFrames = new TextureRegion[this.FRAME_COLS * this.FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < this.FRAME_ROWS; i++) {
            for (int j = 0; j < this.FRAME_COLS; j++) {
                this.walkFrames[index++] = tmp[i][j];
            }
        }
        this.walkAnimation = new Animation(0.125f, this.walkFrames);
        this.stateTime = 0f;
    }

    public void update(float dt) {
        //if (rangeCheck(this)) {
        if (armorType == ArmorType.SHIELD){
            checkShield(dt);
        }
        if (!buildung) {
            Path.findPath(this);
            x += dx * movementspeed * dt;
            y += dy * movementspeed * dt;
        }
        if (fight){
            fight();
        }timeSinceDamageTaken +=dt;
    }

    public void checkShield(float dt){
        if (timeSinceDamageTaken >= shieldReloadTimer) {
            shieldReloadStep += dt;
            if (shieldValue + shieldReloadValue >= maxShieldValue) {
                shieldValue = maxShieldValue;
                shieldReloadStep = 0;
            } else {
                shieldValue += shieldReloadValue;
                System.out.println("Shield reloaded by" + shieldReloadValue);
                shieldReloadStep = 0;
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
        Boolean trigger1 = false;
        Boolean trigger2 = false;

        Unit unit_ptr;

        row = u.getGridX() - 1;
        col = u.getGridY() - 1;
        rowMax = u.getGridX() + 1;
        colMax = u.getGridY() + 1;

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
            colMax = u.getGridY();
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
            if (u.getPlayer() == Game.player2) {
                u.setTarget(Game.opponent(player).getUnits().get(0));
                u.setDistTarget((float) java.lang.Math.sqrt(java.lang.Math.pow(u.getX() - u.getTarget().getX(), 2) + java.lang.Math.pow(u.getY() - u.getTarget().getY(), 2)));

            }
            if (u.getPlayer() == Game.player1) {
                u.setTarget(Game.opponent(player).getUnits().get(0));
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
                target.takeDamage(damage,damageType,target.armorType,player);
                timeSinceAttack = 0;
                //System.out.println("Live: " + target.getLife() + " " + this + " " + this.target);
            }
        }
    }

    public float getSpawnPointX(Player player, int slot) {
        float x = 300f;
        if (player == Game.player1) {
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

        if (player == Game.player2) {
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

    public float getSpawnPointY(Player player, int slot){
        float y = 0f;
        if (player == Game.player1) {
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
        if (player == Game.player2) {
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

    public void takeDamage(int baseDamage,DamageType damageType,ArmorType armorType,Player attackingPlayer){
        timeSinceDamageTaken = 0;
        int damageTaken = 0;
        int damage = Math.round(baseDamage * DamageType.calculateDamage(damageType,armorType,attackingPlayer));

        if(armorType == armorType.SHIELD){
            if (shieldValue < damage){
                damageTaken = shieldValue;
                shieldValue = 0;
                life = life - (damage - damageTaken);
            } else {
                shieldValue = shieldValue - damage;
            }
        } else {
            life = life - damage;
        }
    }

    public void dispose() {
        this.healthBar.dispose();
        if(armorType == ArmorType.SHIELD){ this.shieldBar.dispose();}

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Integer getW() {
        return w;
    }

    public Integer getH() {
        return h;
    }

    public Float getDx() {
        return dx;
    }

    public void setDx(Float dx) {
        this.dx = dx;
    }

    public Float getDy() {
        return dy;
    }

    public void setDy(Float dy) {
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

    public int getGridX() {
        return gridX;
    }

    public void setGridX(int gridX) {
        this.gridX = gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public void setGridY(int gridY) {
        this.gridY = gridY;
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

    public float getTimeSinceAttack() {
        return timeSinceAttack;
    }

    public void setTimeSinceAttack(float timeSinceAttack) {
        this.timeSinceAttack = timeSinceAttack;
    }

    public float getAttackspeed() {
        return attackspeed;
    }

    public void setAttackspeed(float attackspeed) {
        this.attackspeed = attackspeed;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
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

    public Boolean getBuildung() {
        return buildung;
    }

    public void setBuildung(Boolean buildung) {
        this.buildung = buildung;
    }

    public float getRotate() {
        return rotate;
    }

    public void setRotate(float rotate) {
        this.rotate = rotate;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public Unit getTarget() {
        return target;
    }

    public void setTarget(Unit target) {
        this.target = target;
    }

    public float getDistTarget() {
        return distTarget;
    }

    public void setDistTarget(float distTarget) {
        this.distTarget = distTarget;
    }

    public boolean isFight() {
        return fight;
    }

    public void setFight(boolean fight) {
        this.fight = fight;
    }

    public int getFRAME_COLS() {
        return FRAME_COLS;
    }

    public void setFRAME_COLS(int FRAME_COLS) {
        this.FRAME_COLS = FRAME_COLS;
    }

    public int getFRAME_ROWS() {
        return FRAME_ROWS;
    }

    public void setFRAME_ROWS(int FRAME_ROWS) {
        this.FRAME_ROWS = FRAME_ROWS;
    }

    public Animation getWalkAnimation() {
        return walkAnimation;
    }

    public void setWalkAnimation(Animation walkAnimation) {
        this.walkAnimation = walkAnimation;
    }

    public Texture getWalkSheet() {
        return walkSheet;
    }

    public void setWalkSheet(Texture walkSheet) {
        this.walkSheet = walkSheet;
    }

    public TextureRegion[] getWalkFrames() {
        return walkFrames;
    }

    public void setWalkFrames(TextureRegion[] walkFrames) {
        this.walkFrames = walkFrames;
    }

    public TextureRegion getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(TextureRegion currentFrame) {
        this.currentFrame = currentFrame;
    }

    public float getStateTime() {
        return stateTime;
    }

    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }

    public static ArrayList<Unit> getNNtable() {
        return NNtable;
    }

    public static void setNNtable(ArrayList<Unit> NNtable) {
        Unit.NNtable = NNtable;
    }

    public static float[] getVec() {
        return vec;
    }

    public static void setVec(float[] vec) {
        Unit.vec = vec;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }

    public ArmorType getArmorType() {
        return armorType;
    }

    public void setArmorType(ArmorType armorType) {
        this.armorType = armorType;
    }

    public ShieldBar getShieldBar() {
        return shieldBar;
    }

    public void setShieldBar(ShieldBar shieldBar) {
        this.shieldBar = shieldBar;
    }

    public Integer getMaxShieldValue() {
        return maxShieldValue;
    }

    public void setMaxShieldValue(Integer maxShieldValue) {
        this.maxShieldValue = maxShieldValue;
    }

    public Integer getShieldValue() {
        return shieldValue;
    }

    public void setShieldValue(Integer shieldValue) {
        this.shieldValue = shieldValue;
    }

    public Integer getShieldReloadValue() {
        return shieldReloadValue;
    }

    public float getTimeSinceDamageTaken() {
        return timeSinceDamageTaken;
    }

    public void setShieldReloadValue(Integer shieldReloadValue) {
        this.shieldReloadValue = shieldReloadValue;
    }

    public void setTimeSinceDamageTaken(float timeSinceDamageTaken) {
        this.timeSinceDamageTaken = timeSinceDamageTaken;
    }

    public void setShieldReloadTimer(Integer shieldReloadTimer) {
        this.shieldReloadTimer = shieldReloadTimer;
    }
}
