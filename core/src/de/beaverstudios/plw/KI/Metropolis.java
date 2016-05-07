package de.beaverstudios.plw.KI;

import de.beaverstudios.plw.Buildings.Barracks;
import de.beaverstudios.plw.Buildings.BuildingManager;
import de.beaverstudios.plw.Buildings.BuildingTypes;
import de.beaverstudios.plw.Player.Game;
import de.beaverstudios.plw.Player.Player;
import de.beaverstudios.plw.Screens.GameScreen;

import java.util.Random;
/**
 * Created by Grass on 3/26/2016.
 */
public class Metropolis {
    static boolean trigger;
    static int power;
    static int toughness;
    static double rnd;
    static BuildingTypes buildingType;
    static int building;
    static int slot;
    Metropolis(){

    }

    static void runMetropolis(Player p) {

        for(int i = 0; i<1; i++) {
            suggestStep(p);
            if(checkUpdate(p)){
                update(p);
                return;
            }
        }
        System.out.println("Metrolois failed at iteration: " + 100);
    }

    static void suggestStep(Player p){


        slot = randInt(0, 9);

        building = randInt(0, p.getBuildings().size());
        buildingType = BuildingTypes.getNameByIndex(building);
        //System.out.println(p.getTotalPower());
        power = p.getTotalPower() + BuildingTypes.getNameByIndex(building).getPower();
        toughness = p.getTotalToughness() + BuildingTypes.getNameByIndex(building).getToughness();
    }

    static int randInt(int min, int max) {

        Random rand;

        int randomNum =(int) Math.floor(Math.random()*((max - min) + 1) + min);
        System.out.println(randomNum);
        return randomNum;
    }

    static boolean checkUpdate(Player p){

        rnd = Math.random();

        if(calcProb(p) > rnd){
            //System.out.println("prob" + calcProb(p));
            return true;
        }
        else{
            return false;
        }
    }

    static double calcProb(Player p){
        double ppp;
        double tpp;
        double prob;
        double offset = 1.1;
        //System.out.println((Game.opponent(p).getTotalPower()*offset)+ " " +power);
        ppp = (Game.opponent(p).getTotalPower()*offset)/power;
        tpp = (Game.opponent(p).getTotalToughness()+offset)/toughness;
        prob = 0.5*(ppp+tpp);

        return prob;
    }

    static void update(Player p){

        if(GameScreen.bm.buyBuilding(buildingType,slot,p)){
            System.out.println("New ComBuilding");
        } else {
            System.out.println("Com No Money");
        }

    }

}

