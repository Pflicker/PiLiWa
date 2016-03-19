package de.beaverstudios.plw.Units;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

/**
 * Created by paul on 05.03.16.
 *///
public class Grid {

    ArrayList<ArrayList<ArrayList<Unit>>> grid = new ArrayList<ArrayList<ArrayList<Unit>>>();
    UnitManager um;
    int res = 100;
    public Grid(UnitManager um_) {

    this.um = um_;

        create_new_grid();
    }

    public void create_new_grid(){

        this.grid.clear();
        for (int i = 0; i < res; i++) {

            this.grid.add(new ArrayList<ArrayList<Unit>>());
            for (int j = 0; j < res; j++) {
                this.grid.get(i).add(new ArrayList<Unit>());

            }
        }
        return;
    }

    public void update() {

        float x;
        float y;

        create_new_grid();

        for (int i = 0; i < um.comUnits.size(); i++) {

            x = um.comUnits.get(i).getX();
            y = um.comUnits.get(i).getY();

            this.grid.get((int)(x/801*res)).get((int)(y/50*res)).add(um.comUnits.get(i));
        }

        for (int j = 0; j < um.playerUnits.size(); j++) {

            x = um.playerUnits.get(j).getX();
            y = um.playerUnits.get(j).getY();

            this.grid.get((int)(x/801*res)).get((int)(y/50*res)).add(um.playerUnits.get(j));
        }
    return;
    }


}
