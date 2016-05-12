package de.beaverstudios.plw.Player;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

import de.beaverstudios.plw.Buildings.Building;
import de.beaverstudios.plw.Buildings.BuildingTypes;
import de.beaverstudios.plw.Screens.GameScreen;
import de.beaverstudios.plw.Units.Unit;

/**
 * Created by Grass on 3/21/2016.
 */
public class Game {

    public static ArrayList<Player> players;

    private static float gameTime;
    private static int gameTimeInt;
    private float timeSinceSpawn;

    public static Player player1;
    public static Player player2;
    private static float timeSinceInc;

    public Game() {
        players = new ArrayList<Player>();

        players.add(player1 = new Player(true,false,"placeholder"));
        players.add(player2 = new Player(false,true,"placeholder"));

        player1.getUnits().add(new Unit(player1.getUnitIDs().get(0),-1,player1,0));
        player1.getUnits().get(0).setDx(0f);
        player1.getUnits().get(0).setDy(0f);
        player2.getUnits().add(new Unit(player2.getUnitIDs().get(0),-1,player2,0));
        player2.getUnits().get(0).setDx(0f);
        player2.getUnits().get(0).setDy(0f);
        System.out.println("Player1 Units:" + player1.getUnits().get(0).getName());
        System.out.println("Player2 Units:" + player2.getUnits().get(0).getName());

        gameTimeInt = 0;
        timeSinceInc = 0;
    }

    public void update(float dt) {
        gameTime += dt;
        gameTimeInt = (int)gameTime;
        timeSinceInc += dt;
        if (timeSinceInc >= 10) {
            player1.addMoney(player1.getIncome());
            player2.addMoney(player2.getIncome());
            timeSinceInc = 0;
            /*if(player1.isCom()){
                KI.comTurn(player1);
            }
            if(player2.isCom()){
                KI.comTurn(player2);
            }*/
        }
        timeSinceSpawn += dt;
        if (timeSinceSpawn > 10) {
            for (Player p : Game.players) {
                for (Building b : p.getBuildings()) {
                    b.spawnUnit();
                }
                timeSinceSpawn = 0;
            }
        }
    }

    public static int getGameTimeInt() {
        return gameTimeInt;
    }

    public static Player opponent(Player player){
        Player opponent;
        opponent = player1;
        if(player == player1){
            opponent = player2;
        }
        if (player ==player2){
            opponent = player1;
        }
        return opponent;
    }

    public static Player getThisPlayer(){
        return player2;
    }


}

