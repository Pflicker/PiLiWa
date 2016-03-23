package de.beaverstudios.plw.Techs;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;
import java.util.EnumMap;

import de.beaverstudios.plw.TextureManager;
import de.beaverstudios.plw.Units.Unit;

/**
 * Created by Grass on 3/21/2016.
 */
public enum GeneralTechs {
    PHYSICALDAMAGEUPGRADE1(1,"Physical Damage 1",100,DamageType.PHYSICAL, TextureManager.IMGBARRACKS,null),
    PHYSICALDAMAGEUPGRADE2(2,"Physical Damage 2",150,DamageType.PHYSICAL,TextureManager.IMGFACTORY,GeneralTechs.PHYSICALDAMAGEUPGRADE1),
    PHYSICALDAMAGEUPGRADE3(3,"Physical Damage 3",200,DamageType.PHYSICAL,TextureManager.IMGBTNRESUME,GeneralTechs.PHYSICALDAMAGEUPGRADE2),
    ELEMENTALDAMAGEUPGRADE1(4,"Elemental Damage 1",100,DamageType.ELEMENTAL, TextureManager.IMGBARRACKS,null),
    ELEMENTALDAMAGEUPGRADE2(5,"Elemental Damage 2",150,DamageType.ELEMENTAL, TextureManager.IMGBARRACKS,ELEMENTALDAMAGEUPGRADE1),
    ELEMENTALDAMAGEUPGRADE3(6,"Elemental Damage 3",200,DamageType.ELEMENTAL, TextureManager.IMGBARRACKS,ELEMENTALDAMAGEUPGRADE2),
    ENERGYDAMAGEUPGRADE1(7,"Energy Damage 1",100,DamageType.ENERGY, TextureManager.IMGBARRACKS,null),
    ENERGYDAMAGEUPGRADE2(8,"Energy Damage 2",150,DamageType.ENERGY, TextureManager.IMGBARRACKS,ENERGYDAMAGEUPGRADE1),
    ENERGYDAMAGEUPGRADE3(9,"Energy Damage 3",200,DamageType.ENERGY, TextureManager.IMGBARRACKS,ENERGYDAMAGEUPGRADE2);

    private final Integer index;
    private final String techName;
    private final Integer price;
    private final Image image;
    private final DamageType damageType;
    private GeneralTechs[] requirements;

    private GeneralTechs(Integer index, String techName, Integer price,DamageType damageType,Image image,GeneralTechs... requirements){
        this.index = index;
        this.techName = techName;
        this.price = price;
        this.image = image;
        this.damageType = damageType;
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
}
