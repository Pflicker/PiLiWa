package de.beaverstudios.plw.KI;

import de.beaverstudios.plw.Buildings.BuildingManager;
import de.beaverstudios.plw.Buildings.Barracks;
import de.beaverstudios.plw.Buildings.BuildingTypes;
import de.beaverstudios.plw.Player.Game;
import de.beaverstudios.plw.Player.Player;

/**
 * Created by paul on 20.03.16.
 */
public class KI {

    public KI(Player p){
    }

    public static void comTurn(Player p){
        calcPowerValue(p);
        calcToughnessValue(p);
        calcPowerValue(Game.opponent(p));
        calcToughnessValue(Game.opponent(p));
        Metropolis.runMetropolis(p);
    }

     static void calcPowerValue(Player p) {

         Integer power = 100;
         for (int i = 0; i < p.getBuildings().size(); i++) {
             power += p.getBuildings().get(i).getPower();
         }
         p.setTotalPower(power);

     }

    static void calcToughnessValue(Player p){
        Integer toughness = 100;
        for (int i = 0; i < p.getBuildings().size(); i++) {
            toughness += p.getBuildings().get(i).getPower();
        }
        p.setTotalToughness(toughness);

    }

    static void calcPowerDamageType(Player p) {

        Integer power = 100;
        for (int i = 0; i < p.getBuildings().size(); i++) {
            //if(p.getBuildings().get(i).getUnitPtr().getArmorType().;
            //power += p.getBuildings().get(i).getPower();
        }
        p.setTotalPower(power);

    }

 }