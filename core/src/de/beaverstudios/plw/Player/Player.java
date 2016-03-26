package de.beaverstudios.plw.Player;

import java.util.ArrayList;

import de.beaverstudios.plw.Buildings.Building;
import de.beaverstudios.plw.Buildings.BuildingTypes;
import de.beaverstudios.plw.KI.KI;
import de.beaverstudios.plw.Techs.GeneralTechs;
import de.beaverstudios.plw.Techs.SpecificTechs;
import de.beaverstudios.plw.Techs.Types.ArmorType;
import de.beaverstudios.plw.Units.Base;
import de.beaverstudios.plw.Units.Unit;

/**
 * Created by Grass on 3/20/2016.
 */
public class Player {

    private KI ki;
    private int income;
    private int money;
    private ArrayList<GeneralTechs> generalTechs;
    private ArrayList<SpecificTechs> specificTechs;
    private ArrayList<Unit> units;
    private ArrayList<Building> buildings;

    private Integer totalToughness;
    private Integer totalPower;

    private boolean flip;

    private int physicalDamage;
    private int elementalDamage;
    private int energyDamage;

    private int physicalArmor;
    private int angstromArmor;
    private int shieldArmor;

    public Player(boolean computer, boolean flip, boolean ghostPlayer) {
            income = 5;
            money = 100;
            generalTechs = new ArrayList<GeneralTechs>();
            specificTechs = new ArrayList<SpecificTechs>();
            units = new ArrayList<Unit>();
            buildings = new ArrayList<Building>();
            this.flip = flip;
         if (computer) {
             ki = new KI(Player .this);

         }
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

    public void addSpecificTech(SpecificTechs upgrade){
        specificTechs.add(upgrade);
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

    public boolean hasSpecificTech(SpecificTechs upgrade) {
        boolean hasUpgrade;
        hasUpgrade = false;
        if(specificTechs.contains(upgrade)){
                hasUpgrade = true;
            }

        return hasUpgrade;
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

    public KI getKi() {
        return ki;
    }

    public void setKi(KI ki) {
        this.ki = ki;
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

    public ArrayList<SpecificTechs> getSpecificTechs() {
        return specificTechs;
    }

    public void setSpecificTechs(ArrayList<SpecificTechs> specificTechs) {
        this.specificTechs = specificTechs;
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



}
