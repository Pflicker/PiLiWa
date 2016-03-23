package de.beaverstudios.plw.Techs;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

import de.beaverstudios.plw.Buildings.Barracks;
import de.beaverstudios.plw.Buildings.Building;
import de.beaverstudios.plw.Buildings.BuildingTypes;
import de.beaverstudios.plw.TextureManager;

/**
 * Created by Grass on 3/21/2016.
 */
public enum SpecificTechs {
    STIMPACK(1,"Stimpack",100,TextureManager.IMGBTNPAUSE, BuildingTypes.BARRACKS);

    private final Integer index;
    private final String techName;
    private final Integer price;
    private final Image image;
    private BuildingTypes requirements;

    private SpecificTechs(Integer index, String techName, Integer price,Image image,BuildingTypes requirements){
        this.index = index;
        this.techName = techName;
        this.price = price;
        this.image = image;
        this.requirements = requirements;
    }

    public int getPrice(){
        return price;
    }

    public Image getImage() {
        return image;
    }

    public String getTechName() {
        return techName;
    }

    public BuildingTypes getRequirements() {
        return requirements;
    }
}
