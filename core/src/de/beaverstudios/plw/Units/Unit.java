package de.beaverstudios.plw.Units;

/**
 * Created by Grass on 3/2/2016.
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

import de.beaverstudios.plw.Player.Game;
import de.beaverstudios.plw.Player.Player;
import de.beaverstudios.plw.Techs.GeneralTechs;
import de.beaverstudios.plw.Techs.Types.ArmorType;
import de.beaverstudios.plw.Techs.Types.DamageType;
import de.beaverstudios.plw.Units.Healthbar.HealthBar;
import de.beaverstudios.plw.PlwGame;
import de.beaverstudios.plw.Units.Healthbar.ShieldBar;

public class Unit {

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
    String skin;
    Boolean invisible = false;
    Boolean flying = false;
    Boolean attackFlying = true;
    Boolean attackGround = true;
    Boolean stealthDetect = false;
    Boolean flip;
    Boolean buildung = false;
    float rotate;
    UnitTypes unitType;

    int slot;

    //Animation vars
    float walkStateTime;
    float loadStateTime;
    float shootStateTime;

    Rectangle bounds;

    public Unit(UnitTypes unitTypes,Integer slot, Player player, Integer movementspeed) {
        this.name=unitTypes.getName();
        this.w = unitTypes.getW();
        this.h=unitTypes.getH();
        this.maxLife=unitTypes.getMaxLife();
        this.armor=unitTypes.getArmor();
        this.armorType=unitTypes.getArmorType();
        this.maxShieldValue=unitTypes.getMaxShieldValue();
        this.shieldReloadValue=unitTypes.getShieldReloadValue();
        this.damage=unitTypes.getDamage();
        this.damageType=unitTypes.getDamageType();
        this.attackspeed=unitTypes.getAttackspeed();
        this.range=unitTypes.getRange();
        this.value=unitTypes.getValue();
        this.skin=unitTypes.getPathToSkin();
        this.unitType=unitTypes;
        this.slot = slot;
        this.player=player;
        this.movementspeed=movementspeed;
        create();
    }

    public void create() {

        life = maxLife;
        healthBar = new HealthBar(this);

        x = getSpawnPointX(player, slot);
        y = getSpawnPointY(player, slot);

        dx=-1f;
        dy=0f;

        if (armorType == ArmorType.SHIELD) {
            shieldValue = maxShieldValue;
            shieldBar = new ShieldBar(x, y + h + 2, w, 2, shieldValue, maxShieldValue);
        }

        bounds = new Rectangle(x,y,unitType.getWalkAnimation().getKeyFrame(walkStateTime).getRegionWidth(),unitType.getWalkAnimation().getKeyFrame(walkStateTime).getRegionHeight());

        if(this.player == Game.player1){
            flip = true;
        } else {
            flip = false;
        }

        w = unitType.getWalkAnimation().getKeyFrame(walkStateTime).getRegionWidth();
        h = unitType.getWalkAnimation().getKeyFrame(walkStateTime).getRegionHeight();
    }

    public void update(float dt) {

        if (armorType == ArmorType.SHIELD){
            checkShield(dt);
        }
        if (!buildung) {
            //Path.findPath(this);
            x += dx * movementspeed * dt;
            y += dy * movementspeed * dt;

        }
        if (fight){
            fight();
        }
        bounds.setX(x);
        bounds.setY(y);

        timeSinceDamageTaken +=dt;
        walkStateTime += Gdx.graphics.getDeltaTime();
    }

    public void draw(SpriteBatch batch) {

        if (dx != 0 || dy != 0) {
            batch.draw(unitType.getWalkAnimation().getKeyFrame(walkStateTime, true), flip ? x+w : x, y,flip ? -w: w,h);
        } else {
            batch.draw(unitType.getWalkFrames()[0], x, y);
        }

        //healthBar.draw(batch, 1, getX(), getY() + unitType.getWalkAnimation().getKeyFrame(walkStateTime).getRegionHeight() + 1, getW(), 1, getLife(), getMaxLife());
        if (getArmorType() == ArmorType.SHIELD) {
            shieldBar.draw(batch, 1, getX(), getY()+unitType.getWalkAnimation().getKeyFrame(walkStateTime).getRegionHeight() + 2, getW(), 2, getShieldValue(), getMaxShieldValue());
        }
    }



    public void checkShield(float dt){
        if (timeSinceDamageTaken >= shieldReloadTimer) {
            shieldReloadStep += dt;
            if (shieldValue + shieldReloadValue >= maxShieldValue) {
                shieldValue = maxShieldValue;
                shieldReloadStep = 0;
            } else {
                shieldValue += shieldReloadValue;
                //System.out.println("Shield reloaded by" + shieldReloadValue);
                shieldReloadStep = 0;
            }
        }
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
            if(slot ==-1){
                x = 0f;
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
            if(slot ==-1){
                x = PlwGame.V_WIDTH*0.8f -w;
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
            if (slot == -1){
                y = PlwGame.V_HEIGHT/2;
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
            if (slot == -1){
                y = PlwGame.V_HEIGHT/2;
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

    public float getShieldReloadTimer() {
        return shieldReloadTimer;
    }

    public void setShieldReloadTimer(float shieldReloadTimer) {
        this.shieldReloadTimer = shieldReloadTimer;
    }

    public float getShieldReloadStep() {
        return shieldReloadStep;
    }

    public void setShieldReloadStep(float shieldReloadStep) {
        this.shieldReloadStep = shieldReloadStep;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public UnitTypes getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitTypes unitType) {
        this.unitType = unitType;
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

    public Boolean getFlip() {
        return flip;
    }

    public void setFlip(Boolean flip) {
        this.flip = flip;
    }

    public float getLoadStateTime() {
        return loadStateTime;
    }

    public void setLoadStateTime(float loadStateTime) {
        this.loadStateTime = loadStateTime;
    }

    public float getShootStateTime() {
        return shootStateTime;
    }

    public void setShootStateTime(float shootStateTime) {
        this.shootStateTime = shootStateTime;
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

    public float getWalkStateTime() {
        return walkStateTime;
    }

    public void setWalkStateTime(float WalkStateTime) {
        this.walkStateTime = WalkStateTime;
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

    public HealthBar getHealthBar() {
        return healthBar;
    }

    public void setHealthBar(HealthBar healthBar) {
        this.healthBar = healthBar;
    }

    public void setW(Integer w) {
        this.w = w;
    }

    public void setH(Integer h) {
        this.h = h;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }
}
