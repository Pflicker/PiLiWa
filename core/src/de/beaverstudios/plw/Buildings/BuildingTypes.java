package de.beaverstudios.plw.Buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;

import de.beaverstudios.plw.Player.Player;
import de.beaverstudios.plw.Units.UnitManager;

/**
 * Created by Grass on 3/3/2016.
 */
public class BuildingTypes{

    private Integer buildingID;
    private String buildingName;
    private Integer price;
    private Array<Integer> units = new Array<Integer>();
    private Texture texture;
    private Image image;
    private Player player;

    public BuildingTypes(Integer buildingID, String buildingName, Integer price, Player player){
        this.player = player;
        this.buildingID = buildingID;
        this.buildingName = buildingName;
        this.price = price;
    }
    public Integer getTier1Unit(){
        Integer returnID = 0;
        for(int i = 1;units.size > i;i++){
           if (player.getUnitIDs().get(i).getTier() == 1){
               returnID = i;
           }
        }
        return returnID;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(Integer buildingID) {
        this.buildingID = buildingID;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public Array<Integer> getUnits() {
        return units;
    }

    public void addUnit(Integer unitID){
        this.units.add(unitID);
    }

    public void setUnits(Array<Integer> units) {
        this.units = units;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }

}


