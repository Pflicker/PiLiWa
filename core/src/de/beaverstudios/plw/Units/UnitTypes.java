package de.beaverstudios.plw.Units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FileTextureData;

import de.beaverstudios.plw.Techs.Types.ArmorType;
import de.beaverstudios.plw.Techs.Types.DamageType;

/**
 * Created by Grass on 5/8/2016.
 */
public class UnitTypes {

    private Integer unitID;
    private String name;
    private Integer w;
    private Integer h;
    private Integer maxLife;
    private Integer armor;
    private ArmorType armorType;
    private Integer maxShieldValue;
    private Integer shieldReloadValue = 0;
    private Integer damage;
    private DamageType damageType;
    private float attackspeed;
    private float range;
    private Integer value;
    private String pathToSkin;
    private Integer tier;
    private Integer req;
    private Animation walkAnimation;
    private Animation loadingAnimation;
    private Animation shootAnimation;
    private TextureRegion[] walkFrames = new TextureRegion[4];
    private TextureRegion[] loadingFrames = new TextureRegion[4];
    private TextureRegion[] shootFrames = new TextureRegion[4];

    public UnitTypes(Integer unitID, String name,String pathToSkin) {
        this.unitID = unitID;
        this.name = name;
        this.pathToSkin = pathToSkin;
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(pathToSkin));
        walkFrames[0] = (atlas.findRegion("1"));
        walkFrames[1] = (atlas.findRegion("2"));
        walkFrames[2] = (atlas.findRegion("3"));
        walkFrames[3] = (atlas.findRegion("4"));
        walkAnimation = new Animation(1/4f, walkFrames);
    }

    public Integer getUnitID() {
        return unitID;
    }

    public void setUnitID(Integer unitID) {
        this.unitID = unitID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getMaxLife() {
        return maxLife;
    }

    public void setMaxLife(Integer maxLife) {
        this.maxLife = maxLife;
    }

    public Integer getArmor() {
        return armor;
    }

    public void setArmor(Integer armor) {
        this.armor = armor;
    }

    public ArmorType getArmorType() {
        return armorType;
    }

    public void setArmorType(ArmorType armorType) {
        this.armorType = armorType;
    }

    public Integer getMaxShieldValue() {
        return maxShieldValue;
    }

    public void setMaxShieldValue(Integer maxShieldValue) {
        this.maxShieldValue = maxShieldValue;
    }

    public Integer getShieldReloadValue() {
        return shieldReloadValue;
    }

    public void setShieldReloadValue(Integer shieldReloadValue) {
        this.shieldReloadValue = shieldReloadValue;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }

    public float getAttackspeed() {
        return attackspeed;
    }

    public void setAttackspeed(float attackspeed) {
        this.attackspeed = attackspeed;
    }

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getPathToSkin() {
        return pathToSkin;
    }

    public void setPathToSkin(String pathToSkin) {
        this.pathToSkin = pathToSkin;
    }

    public Integer getTier() {
        return tier;
    }

    public void setTier(Integer tier) {
        this.tier = tier;
    }

    public Integer getReq() {
        return req;
    }

    public void setReq(Integer req) {
        this.req = req;
    }

    public Animation getWalkAnimation() {
        return walkAnimation;
    }

    public void setWalkAnimation(Animation walkAnimation) {
        this.walkAnimation = walkAnimation;
    }

    public Animation getLoadingAnimation() {
        return loadingAnimation;
    }

    public void setLoadingAnimation(Animation loadingAnimation) {
        this.loadingAnimation = loadingAnimation;
    }

    public Animation getShootAnimation() {
        return shootAnimation;
    }

    public void setShootAnimation(Animation shootAnimation) {
        this.shootAnimation = shootAnimation;
    }

    public TextureRegion[] getWalkFrames() {
        return walkFrames;
    }

    public void setWalkFrames(TextureRegion[] walkFrames) {
        this.walkFrames = walkFrames;
    }

    public TextureRegion[] getLoadingFrames() {
        return loadingFrames;
    }

    public void setLoadingFrames(TextureRegion[] loadingFrames) {
        this.loadingFrames = loadingFrames;
    }

    public TextureRegion[] getShootFrames() {
        return shootFrames;
    }

    public void setShootFrames(TextureRegion[] shootFrames) {
        this.shootFrames = shootFrames;
    }
}
