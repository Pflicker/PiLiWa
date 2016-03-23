package de.beaverstudios.plw.Techs;

import java.util.ArrayList;

import de.beaverstudios.plw.Buildings.BuildingManager;
import de.beaverstudios.plw.Buildings.BuildingTypes;
import de.beaverstudios.plw.Player.Game;

/**
 * Created by Grass on 3/20/2016.
 */
public class Techs {


    public ArrayList<SpecificTechs> checkSpecificTechsAvailability(){
        ArrayList<SpecificTechs> availableTechs = new ArrayList<SpecificTechs>();
        BuildingTypes req;

        for (SpecificTechs s : SpecificTechs.values()){
            if(!Game.player2.hasSpecificTech(s)){

                    req = s.getRequirements();
                    // check if Player has Building

                    if (BuildingManager.hasBuilding(req, 1)) {
                        availableTechs.add(s);
                    }


            }
        }

        return availableTechs;
    }
    // for each player
    public ArrayList<GeneralTechs> checkGeneralTechsAvailability(){

        ArrayList<GeneralTechs> availableTechs = new ArrayList<GeneralTechs>();
        GeneralTechs[] req;

        for (GeneralTechs g : GeneralTechs.values()){
            Integer hasNumberOfRequirements = 0;
            if(!Game.player2.hasGeneralTech(g)){

                if(g.getRequirements() == null){
                    availableTechs.add(g);
                } else {
                    req = g.getRequirements();
                    // check if Player has requirements
                    for (GeneralTechs t : req) {
                        if (Game.player2.hasGeneralTech(t)){
                            hasNumberOfRequirements++;
                        }
                    }
                    if (hasNumberOfRequirements == req.length) {
                        availableTechs.add(g);
                    }
                }
            } else {
                System.out.println("has tech" + g);
            }
        }
        return availableTechs;
    }
}

