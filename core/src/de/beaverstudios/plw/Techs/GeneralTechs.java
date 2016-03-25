package de.beaverstudios.plw.Techs;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

import de.beaverstudios.plw.Techs.Types.*;
import de.beaverstudios.plw.Techs.Types.ArmorType;
import de.beaverstudios.plw.TextureManager;

/**
 * Created by Grass on 3/21/2016.
 */
public enum GeneralTechs {
    PHYSICALDAMAGEUPGRADE1(1,"Physical Damage 1",100, de.beaverstudios.plw.Techs.Types.DamageType.PHYSICAL, TextureManager.IMGBARRACKS,null),
    PHYSICALDAMAGEUPGRADE2(2,"Physical Damage 2",150, de.beaverstudios.plw.Techs.Types.DamageType.PHYSICAL,TextureManager.IMGFACTORY,GeneralTechs.PHYSICALDAMAGEUPGRADE1),
    PHYSICALDAMAGEUPGRADE3(3,"Physical Damage 3",200, de.beaverstudios.plw.Techs.Types.DamageType.PHYSICAL,TextureManager.IMGBTNRESUME,GeneralTechs.PHYSICALDAMAGEUPGRADE2),
    ELEMENTALDAMAGEUPGRADE1(4,"Elemental Damage 1",100, de.beaverstudios.plw.Techs.Types.DamageType.ELEMENTAL, TextureManager.IMGBARRACKS,null),
    ELEMENTALDAMAGEUPGRADE2(5,"Elemental Damage 2",150, de.beaverstudios.plw.Techs.Types.DamageType.ELEMENTAL, TextureManager.IMGBARRACKS,ELEMENTALDAMAGEUPGRADE1),
    ELEMENTALDAMAGEUPGRADE3(6,"Elemental Damage 3",200, de.beaverstudios.plw.Techs.Types.DamageType.ELEMENTAL, TextureManager.IMGBARRACKS,ELEMENTALDAMAGEUPGRADE2),
    ENERGYDAMAGEUPGRADE1(7,"Energy Damage 1",100, de.beaverstudios.plw.Techs.Types.DamageType.ENERGY, TextureManager.IMGBARRACKS,null),
    ENERGYDAMAGEUPGRADE2(8,"Energy Damage 2",150, de.beaverstudios.plw.Techs.Types.DamageType.ENERGY, TextureManager.IMGBARRACKS,ENERGYDAMAGEUPGRADE1),
    ENERGYDAMAGEUPGRADE3(9,"Energy Damage 3",200, de.beaverstudios.plw.Techs.Types.DamageType.ENERGY, TextureManager.IMGBARRACKS,ENERGYDAMAGEUPGRADE2),
    PHYSICALARMORUPGRADE1(10,"Physical Armor 1",100,ArmorType.PHYSICAL,TextureManager.IMGBARRACKS,null),
    PHYSICALARMORUPGRADE2(11,"Physical Armor 2",150,ArmorType.PHYSICAL,TextureManager.IMGBARRACKS,PHYSICALARMORUPGRADE1),
    PHYSICALARMORUPGRADE3(12,"Physical Armor 3",200,ArmorType.PHYSICAL,TextureManager.IMGBARRACKS,PHYSICALARMORUPGRADE2);

    private final Integer index;
    private final String techName;
    private final Integer price;
    private final Image image;
    private final de.beaverstudios.plw.Techs.Types.DamageType damageType;
    private final de.beaverstudios.plw.Techs.Types.ArmorType armorType;
    private GeneralTechs[] requirements;

    private GeneralTechs(Integer index, String techName, Integer price, de.beaverstudios.plw.Techs.Types.DamageType damageType,Image image,GeneralTechs... requirements){
        this.index = index;
        this.techName = techName;
        this.price = price;
        this.image = image;
        this.damageType = damageType;
        armorType = null;
        this.requirements = requirements;
    }

    private GeneralTechs(Integer index, String techName, Integer price, de.beaverstudios.plw.Techs.Types.ArmorType armorType,Image image,GeneralTechs... requirements){
        this.index = index;
        this.techName = techName;
        this.price = price;
        this.image = image;
        this.armorType = armorType;
        damageType = null;
        this.requirements = requirements;
    }

    public int getPrice(){
        return price;
    }

    public GeneralTechs[] getRequirements() {
        return requirements;
    }

    public Image getImage() {
        return image;
    }

    public String getTechName() {
        return techName;
    }

    public de.beaverstudios.plw.Techs.Types.DamageType getDamageType() {
        return damageType;
    }

    public ArmorType getArmorType(){
        return armorType;
    }
}
