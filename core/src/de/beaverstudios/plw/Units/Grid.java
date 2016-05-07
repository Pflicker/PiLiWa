package de.beaverstudios.plw.Units;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

import de.beaverstudios.plw.Player.Game;
import de.beaverstudios.plw.Player.Player;
import de.beaverstudios.plw.PlwGame;

/**
 * Created by paul on 05.03.16.
 *///
public class Grid {

    static ArrayList<ArrayList<ArrayList<Unit>>> gridTable = new ArrayList<ArrayList<ArrayList<Unit>>>();

    public Grid() {


        create_new_grid();
    }

    public void create_new_grid() {

        this.gridTable.clear();
        for (int i = 0; i < PlwGame.GRID_RES; i++) {

            this.gridTable.add(new ArrayList<ArrayList<Unit>>());
            for (int j = 0; j < PlwGame.GRID_RES; j++) {
                this.gridTable.get(i).add(new ArrayList<Unit>());

            }
        }
    }

    public void update() {

        float x;
        float y;

        create_new_grid();

        for (Player p : Game.players) {
            for (int i = 0; i < p.getUnits().size(); i++) {

                x = p.getUnits().get(i).getX();
                y = p.getUnits().get(i).getY();
                if (x == 800) {
                    x = 799;
                }
                p.getUnits().get(i).setGridX((int) ((x) / PlwGame.V_WIDTH * PlwGame.GRID_RES));
                p.getUnits().get(i).setGridY((int) ((y) / PlwGame.V_HEIGHT * PlwGame.GRID_RES));
                //System.out.println("plyer: " + UnitManager.playerUnits.get(i).getPlayer() + " " + UnitManager.comUnits.get(i) + " " + x + " " + y + " " + (int) (x / (PlwGame.V_WIDTH + 1) * (PlwGame.GRID_RES + 1)) + " " + ((int) (y / (PlwGame.V_HEIGHT + 1) * PlwGame.GRID_RES)));
                this.gridTable.get((int) (x / (PlwGame.V_WIDTH) * PlwGame.GRID_RES)).get((int) (y / (PlwGame.V_HEIGHT) * PlwGame.GRID_RES)).add(p.getUnits().get(i));
            }
        }

    }
}
