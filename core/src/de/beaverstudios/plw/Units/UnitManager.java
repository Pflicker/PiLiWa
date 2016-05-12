package de.beaverstudios.plw.Units;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

import de.beaverstudios.plw.Animations.UnitValue;
import de.beaverstudios.plw.Player.Game;
import de.beaverstudios.plw.Player.Player;
import de.beaverstudios.plw.PlwGame;
import de.beaverstudios.plw.Screens.GameScreen;
import de.beaverstudios.plw.Techs.Types.ArmorType;

/**
 * Created by Grass on 3/3/2016.
 */
public class UnitManager {

    private static Grid grid;
    public static ArrayList<UnitValue> unitValues = new ArrayList<UnitValue>();
    public static Path path;

    public UnitManager() {

        grid = new Grid();
        path = new Path();
    }

    public void update(float dt) {
        grid.update();
        for (Player p : Game.players){
            for (Unit u : p.getUnits()) {
                u.update(dt);
                u.walkStateTime += dt;
            }
            killUnits(p);
        }

        for (int i = 0;unitValues.size() > i;i++){
            unitValues.get(i).update(dt);
            if (unitValues.get(i).getTimer() > 2f){
                unitValues.remove(i);
            }
        }
    }

    public void killUnits(Player p) {

        if(p.getUnits().get(0).getLife() < 1){
            GameScreen.gameOver = true;
            System.out.println("Game Over");
        }

        for (int i = 1; i < p.getUnits().size(); i++) {
            if (p.getUnits().get(i).getLife() < 1){
                unitValues.add(new UnitValue(p.getUnits().get(i).x + p.getUnits().get(i).getW() / 2, p.getUnits().get(i).y + p.getUnits().get(i).getH(), p.getUnits().get(i).value));
                Game.opponent(p).addMoney(p.getUnits().get(i).getValue());
                //p.getUnits().get(i).remove();
                p.getUnits().remove(i);
                System.out.println(p + " Unit killed");
            }
            else if (p.getUnits().get(i).getX() < 0 || p.getUnits().get(i).getX() > PlwGame.V_WIDTH){
                p.getUnits().remove(i);
                System.out.println(p + " Unit disposed");
            }
            else if (p.getUnits().get(i).getY() < 0 || p.getUnits().get(i).getY() > PlwGame.V_HEIGHT){
                p.getUnits().remove(i);
                System.out.println(p + " Unit disposed");
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (Player p : Game.players) {
            for (Unit u : p.getUnits()) {
                u.draw(batch);
            }
        }
        for (int i = 0;unitValues.size() > i;i++){
            unitValues.get(i).render(batch, new BitmapFont());
        }
    }
}
