package de.beaverstudios.plw.Player;

import java.util.ArrayList;

import de.beaverstudios.plw.Techs.GeneralTechs;
import de.beaverstudios.plw.Techs.SpecificTechs;

/**
 * Created by Grass on 3/20/2016.
 */
public class Player {

    public int income;
    public int money;
    public ArrayList<GeneralTechs> generalTechs;
    public ArrayList<SpecificTechs> specificTechs;

    public Player(boolean computer) {
        income = 5;
        money = 100;
        generalTechs = new ArrayList<GeneralTechs>();
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

    public boolean hasSpecificTech(SpecificTechs upgrade) {
        boolean hasUpgrade;
        hasUpgrade = false;
        for (SpecificTechs g : specificTechs) {
            if (g == upgrade) {
                hasUpgrade = true;
            }
        }
        return hasUpgrade;
    }
}
