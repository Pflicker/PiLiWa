package de.beaverstudios.plw.Units;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

import de.beaverstudios.plw.PlwGame;

/**
 * Created by paul on 05.03.16.
 *///
public class Grid {

    ArrayList<ArrayList<ArrayList<Unit>>> gridTable = new ArrayList<ArrayList<ArrayList<Unit>>>();

    public Grid() {


        create_new_grid();
    }

    public void create_new_grid(){

        this.gridTable.clear();
        for (int i = 0; i < PlwGame.GRID_RES; i++) {

            this.gridTable.add(new ArrayList<ArrayList<Unit>>());
            for (int j = 0; j < PlwGame.GRID_RES; j++) {
                this.gridTable.get(i).add(new ArrayList<Unit>());

            }
        }
        return;
    }

    public void update() {

        float x;
        float y;

        create_new_grid();

        for (int i = 0; i < UnitManager.playerUnits.size(); i++) {

            x = UnitManager.playerUnits.get(i).getX();
            y = UnitManager.playerUnits.get(i).getY();
            if (x==800){
                x = 799;
            }
            UnitManager.playerUnits.get(i).setgridX((int) ((x) / PlwGame.V_WIDTH*PlwGame.GRID_RES));
            UnitManager.playerUnits.get(i).setgridY((int) ((y) / PlwGame.V_HEIGHT * PlwGame.GRID_RES));
            //System.out.println("plyer: " + UnitManager.playerUnits.get(i).getPlayer() + " " + UnitManager.comUnits.get(i) + " " + x + " " + y + " " + (int) (x / (PlwGame.V_WIDTH + 1) * (PlwGame.GRID_RES + 1)) + " " + ((int) (y / (PlwGame.V_HEIGHT + 1) * PlwGame.GRID_RES)));
            this.gridTable.get((int)(x/ (PlwGame.V_WIDTH)*PlwGame.GRID_RES)).get((int) (y / (PlwGame.V_HEIGHT) * PlwGame.GRID_RES)).add(UnitManager.playerUnits.get(i));
        }
        for (int i = 0; i < UnitManager.comUnits.size(); i++) {

            x = UnitManager.comUnits.get(i).getX();
            y = UnitManager.comUnits.get(i).getY();
            if (x==800){
                x = 799;
            }
            UnitManager.comUnits.get(i).setgridX((int) ((x) / PlwGame.V_WIDTH * PlwGame.GRID_RES));
            UnitManager.comUnits.get(i).setgridY((int) ((y) / PlwGame.V_HEIGHT * PlwGame.GRID_RES));
            //System.out.println("plyer: " + UnitManager.comUnits.get(i).getPlayer() + " " + UnitManager.comUnits.get(i) +" " + x + " " + y + " " + (int)(x/ (PlwGame.V_WIDTH+1)*(PlwGame.GRID_RES+1)) + " " + ((int) (y / (PlwGame.V_HEIGHT + 1) * PlwGame.GRID_RES)));
            this.gridTable.get((int) (x / (PlwGame.V_WIDTH) * (PlwGame.GRID_RES))).get((int) (y / (PlwGame.V_HEIGHT) * PlwGame.GRID_RES)).add(UnitManager.comUnits.get(i));
            //System.out.println(UnitManager.comUnits.get(i) + " " + this.gridTable.get((int) (x / (PlwGame.V_WIDTH) * (PlwGame.GRID_RES))).get((int) (y / (PlwGame.V_HEIGHT) * PlwGame.GRID_RES)).size());
        }


    return;
    }


}
