package de.beaverstudios.plw.Units;

/**
 * Created by Grass on 3/2/2016.
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Pools;

import de.beaverstudios.plw.Healthbar.HealthBar;
import de.beaverstudios.plw.PlwGame;
import de.beaverstudios.plw.Screens.GameScreen;

public class Unit {

    String name;
    float x;
    float y;
    Integer w;
    Integer h;
    Integer dx;
    Integer dy;
    Integer life;
    Integer armor;
    Integer movementspeed;
    Integer range;
    Integer damage;
    Integer damageType;
    float timeSinceShot;
    float attackspeed;
    Texture skin;
    Boolean invisible;
    Boolean flying;
    Boolean attackFlying;
    Boolean attackGround;
    Boolean stealthDetect;
    Boolean direction;
    float rotate;
    HealthBar healthBar;

    public Unit() {

    }

    public Integer getW() {
        return this.w;
    }

    public Integer getH() {
        return this.h;
    }

    public float getTimeSinceShot() {
        return this.timeSinceShot;
    }

    public void setTimeSinceShot(float timeSinceShot) {
        this.timeSinceShot = timeSinceShot;
    }

    public float getRotate() {
        return this.rotate;
    }

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Integer getDx() {
        return this.dx;
    }

    public void setDx(Integer dx) {
        this.dx = dx;
    }

    public Integer getDy() {
        return this.dy;
    }

    public void setDy(Integer dy) {
        this.dy = dy;
    }

    public Integer getLife() {
        return this.life;
    }

    public void setLife(Integer life) {
        this.life = life;
    }

    public Integer getArmor() {
        return this.armor;
    }

    public Integer getMovementspeed() {
        return this.movementspeed;
    }

    public Float getAttackspeed() {
        return this.attackspeed;
    }

    public String getName() {
        return name;
    }

    public Integer getRange() {
        return this.range;
    }

    public Integer getDamage() {
        return this.damage;
    }

    public Integer getDamageType() {
        return this.damageType;
    }

    public Texture getSkin() {
        return this.skin;
    }

    public Boolean getInvisible() {
        return this.invisible;
    }

    public Boolean getFlying() {
        return this.flying;
    }

    public Boolean getAttackFlying() {
        return this.attackFlying;
    }

    public Boolean getAttackGround() {
        return this.attackGround;
    }

    public Boolean getStealthDetect() {
        return this.stealthDetect;
    }

    public Boolean getDirection() {
        return this.direction;
    }

    public Boolean rangeCheck(float x, float range) {

        return false;
    }

    public void stop() {
        movementspeed = 0;
    }

    public void update(float dt) {

        if (rangeCheck(this.x,this.range)) {
            stop();
        }else{
            x = (x - movementspeed*dt);
            }
        }

    public float getSpawnPointX(int p){
        if(p == 1){
            x = (800) ;
        } else {
            x = 0;
        }
        return x;
    }

    public boolean inRange(int p){
        if (p == 0){

        }
        return true;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(this.skin,this.x,this.y,this.w, this.h);
        healthBar.draw(batch,1,this.x, this.y+this.h+1,this.w,1,this.life);

    }

    public void dispose() {
        this.healthBar.dispose();
    }
}
