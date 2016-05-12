package de.beaverstudios.plw.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;

import java.io.IOException;
import java.util.ArrayList;

import de.beaverstudios.plw.Buildings.Building;
import de.beaverstudios.plw.Buildings.BuildingTypes;
import de.beaverstudios.plw.Screens.GameScreen;
import de.beaverstudios.plw.Techs.GeneralTechs;
import de.beaverstudios.plw.Techs.Types.ArmorType;
import de.beaverstudios.plw.Techs.Types.DamageType;
import de.beaverstudios.plw.Units.Unit;
import de.beaverstudios.plw.Units.UnitTypes;

/**
 * Created by Grass on 3/20/2016.
 */
public class Player {

    private int income;
    private boolean com;
    private int money;
    private ArrayList<GeneralTechs> generalTechs;
    private ArrayList<Unit> units;
    private ArrayList<Building> buildings;

    //Race
    private String raceName;
    public Array<UnitTypes> unitIDs;
    public Array<BuildingTypes> buildingIDs;
    private String pathtoXML;

    private Integer totalToughness;
    private Integer totalPower;

    private boolean flip;

    private int physicalDamage;
    private int elementalDamage;
    private int energyDamage;

    private int physicalArmor;
    private int angstromArmor;
    private int shieldArmor;

    public Player(boolean computer, boolean flip,String pathtoXML) {

            this.pathtoXML = pathtoXML;
            this.pathtoXML = "XML/Units.xml";
            income = 5;
            money = 100;
            generalTechs = new ArrayList<GeneralTechs>();
            units = new ArrayList<Unit>();
            buildings = new ArrayList<Building>();
            this.flip = flip;
            this.com = computer;

        this.raceName = "Human";
        XmlReader xmlReader = new XmlReader();
        unitIDs = new Array<UnitTypes>();
        buildingIDs = new Array<BuildingTypes>();
        XmlReader.Element root = null;

        try {
            root = xmlReader.parse(Gdx.files.internal(this.pathtoXML));
        } catch (IOException e) {
            e.printStackTrace();
        }
        XmlReader.Element base = root.getChildByName("base");
        addUnit(0,base);
        int numberOfBuildings = root.getChildCount();
        System.out.println("Buildings: " + numberOfBuildings);

        for(int i = 0; i < numberOfBuildings-1; i++) {

            XmlReader.Element building = root.getChild(i+1);
            int numberofUnits = building.getChildCount();
            buildingIDs.add(new BuildingTypes(building.getInt("buildingid"),building.get("name"),building.getInt("price"),this));
            buildingIDs.get(i).setTexture(new Texture(Gdx.files.internal(building.get("texture"))));
            buildingIDs.get(i).setImage(new Image(buildingIDs.get(i).getTexture()));
            buildingIDs.get(i).getImage().setDrawable(new SpriteDrawable(new Sprite(buildingIDs.get(i).getTexture())));

            for (int j = 0; j < numberofUnits; j++) {
                XmlReader.Element unit = building.getChild(j);
                System.out.println("add Child" + (j+1));
                addUnit(j+1, unit);
                buildingIDs.get(i).addUnit(unitIDs.size);
            }
        }
        for(int j = 0; unitIDs.size > j ; j++){
            System.out.println(unitIDs.get(j).getName());
        }
        for(int k = 0;buildingIDs.size > k ; k++){
            System.out.println(buildingIDs.get(k).getBuildingName());
        }
    }

    public void addUnit(Integer j, XmlReader.Element unit){

        unitIDs.add(new UnitTypes(unit.getInt("unitid"), unit.get("name"),unit.get("texture")));
        unitIDs.get(j).setW(unit.getInt("w"));
        unitIDs.get(j).setH(unit.getInt("h"));
        unitIDs.get(j).setMaxLife(unit.getInt("maxlife"));
        unitIDs.get(j).setArmor(unit.getInt("armor"));
        unitIDs.get(j).setArmorType(ArmorType.valueOf(unit.get("armortype")));

        if (unitIDs.get(j).getArmorType() == ArmorType.SHIELD) {
            unitIDs.get(j).setMaxShieldValue(unit.getInt("maxshieldvalue"));
            unitIDs.get(j).setShieldReloadValue(unit.getInt("shieldreloadvalue"));
        }
        unitIDs.get(j).setDamage(unit.getInt("damage"));
        unitIDs.get(j).setDamageType(DamageType.valueOf(unit.get("damagetype")));
        unitIDs.get(j).setAttackspeed(unit.getFloat("attackspeed"));
        unitIDs.get(j).setRange(unit.getFloat("range"));
        unitIDs.get(j).setReq(unit.getInt("req"));
        unitIDs.get(j).setTier(unit.getInt("tier"));
    }


    public int getIncome() {
        return income;
    }

    public void addIncome(int income) {
        this.income += income;
    }

    public int getMoney() {
        return money;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public void addGeneralTech(GeneralTechs upgrade){
        generalTechs.add(upgrade);
    }

    public boolean hasGeneralTech(GeneralTechs upgrade) {
        boolean hasUpgrade;
        hasUpgrade = false;
        if(generalTechs.contains(upgrade)){
            hasUpgrade= true;
            System.out.println("General Playertechs" + generalTechs);
        }
        return hasUpgrade;
    }

    public Integer hasSlot(int slot){
        for (int i = 0; i < buildings.size();i++){
            if (slot == buildings.get(i).getSlot()){
                return i;
            }
        }
        return null;
    }
    public Boolean buyBuilding(BuildingTypes buildingType, Integer slot){

        if (getMoney() >= buildingType.getPrice()){
            createBuilding(buildingType, slot);
            return true;
        } else {
            if(!com) {
                GameScreen.hud.noticeWindow.setVisible(true);
            }
            return false;
        }
    }

    public void createBuilding(BuildingTypes buildingType, Integer slot){
        for (int i = 0; i < getBuildings().size(); i++){
            sellBuilding(i);
        }
        addMoney(-buildingType.getPrice());
        buildings.add(new Building(buildingType,slot,this,buildingType.getTier1Unit()));

    }

    public void sellBuilding(Integer slot){
        if(getBuildings().get(slot).getSlot() == slot){
            getBuildings().remove(slot);
            addMoney((int)(getBuildings().get(slot).getThisType().getPrice() * 0.5));
            Gdx.app.log("BuildingManager: ", "Building on Slot " + String.format("%01d",slot) + " removed @ Player");
        }
    }



    public ArrayList<GeneralTechs> getGeneralTechs() {
        return generalTechs;
    }

    public void addPhysicalDamage() {
        physicalDamage++;
    }
    public void addElementalDamage() {
        elementalDamage++;
    }
    public void addEnergyDamage() {
        energyDamage++;
    }
    public void addPhysicalArmor() {
        physicalArmor++;
    }
    public void addShieldArmor() {
        shieldArmor++;
    }
    public void addAngstromArmor() {
        angstromArmor++;
    }

    public int getPhysicalDamage() {
        return physicalDamage;
    }

    public int getElementalDamage() {
        return elementalDamage;
    }

    public int getEnergyDamage() {
        return energyDamage;
    }

    public int getPhysicalArmor() {
        return physicalArmor;
    }

    public int getAngstromArmor() {
        return angstromArmor;
    }

    public int getShieldArmor() {
        return shieldArmor;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setGeneralTechs(ArrayList<GeneralTechs> generalTechs) {
        this.generalTechs = generalTechs;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    public void setPhysicalDamage(int physicalDamage) {
        this.physicalDamage = physicalDamage;
    }

    public void setElementalDamage(int elementalDamage) {
        this.elementalDamage = elementalDamage;
    }

    public void setEnergyDamage(int energyDamage) {
        this.energyDamage = energyDamage;
    }

    public void setPhysicalArmor(int physicalArmor) {
        this.physicalArmor = physicalArmor;
    }

    public void setAngstromArmor(int angstromArmor) {
        this.angstromArmor = angstromArmor;
    }

    public void setShieldArmor(int shieldArmor) {
        this.shieldArmor = shieldArmor;
    }

    public boolean isFlip() {
        return flip;
    }

    public boolean hasBuilding(BuildingTypes buildingTypes){
        boolean ret;
        if(buildings.contains(buildingTypes)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isCom() {
        return com;
    }

    public Integer getTotalToughness() {
        return totalToughness;
    }

    public void setTotalToughness(Integer totalToughness) {
        this.totalToughness = totalToughness;
    }

    public Integer getTotalPower() {
        return totalPower;
    }

    public void setTotalPower(Integer totalPower) {
        this.totalPower = totalPower;
    }

    public Array<UnitTypes> getUnitIDs() {
        return unitIDs;
    }

    public void setUnitIDs(Array<UnitTypes> unitIDs) {
        this.unitIDs = unitIDs;
    }

    public Array<BuildingTypes> getBuildingIDs() {
        return buildingIDs;
    }

    public void setBuildingIDs(Array<BuildingTypes> buildingIDs) {
        this.buildingIDs = buildingIDs;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }
}
