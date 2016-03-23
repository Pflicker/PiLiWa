package de.beaverstudios.plw.Units;

/**
 * Created by Grass on 3/2/2016.
 */
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import de.beaverstudios.plw.TextureManager;
import de.beaverstudios.plw.Units.Healthbar.HealthBar;
import de.beaverstudios.plw.PlwGame;

public abstract class Unit {

    String name;
    float x;
    float y;
    Integer player;
    Integer w;
    Integer h;
    Integer dx;
    Integer dy;
    Integer maxLife;
    Integer life;
    Integer armor;
    int gridX;
    int gridY;
    float movementspeed;
    float range;
    Integer damage;
    float timeSinceAttack;
    float attackspeed;
    Integer value;
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
    int        FRAME_COLS;
    int        FRAME_ROWS;
    Animation walkAnimation;
    Texture walkSheet;
    TextureRegion[] walkFrames;
    TextureRegion currentFrame;
    float stateTime;

    public void create() {
        life = maxLife;
        healthBar = new HealthBar(x, y + h + 1, w, 1, life, maxLife);
        x = getSpawnPointX(player, slot);
        y = getSpawnPointY(player, slot);

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
        if (player == 0){
            direction =false;
        }
        if (player == 1){
            direction = true;
        }
    }

    public static Object createObject(Constructor constructor,
                                      Object[] arguments) {
        System.out.println ("Constructor: " + constructor.toString());
        Object object = null;

        try {
            object = constructor.newInstance(arguments);
            System.out.println ("Object: " + object.toString());
            return object;
        } catch (InstantiationException e) {
            System.out.println(e);
        } catch (IllegalAccessException e) {
            System.out.println(e);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        } catch (InvocationTargetException e) {
            System.out.println(e);
        }
        return object;
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

    public void dispose() {
        this.healthBar.dispose();
    }

    public TextureRegion getCurrentFrame() {
        return currentFrame;
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

    public Integer getDx() {
        return dx;
    }

    public void setDx(Integer dx) {
        this.dx = dx;
    }

    public Integer getDy() {
        return dy;
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

    public Integer getValue() {
        return value;
    }
}
