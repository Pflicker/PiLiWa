package de.beaverstudios.plw.Player;

import java.util.ArrayList;

import de.beaverstudios.plw.Techs.Techs;
import de.beaverstudios.plw.Units.Base;

/**
 * Created by Grass on 3/21/2016.
 */
public class Game {

    public static ArrayList<Player> players;

    private static float gameTime;
    private static int gameTimeInt;

    public static Player player1;
    public static Player player2;
    public static Player ghostPlayer;
    private static float timeSinceInc;



    public Game() {
        players = new ArrayList<Player>();

        players.add(player1 = new Player(true,false,false));
        players.add(player2 = new Player(false,true,false));

        player1.getUnits().add(new Base(player1));
        player2.getUnits().add(new Base(player2));

        ghostPlayer = new Player(false,false,true);

        gameTimeInt = 0;
        timeSinceInc = System.nanoTime();
    }

    public void update(float dt) {
        gameTime += dt;
        gameTimeInt = (int)gameTime;
        timeSinceInc += dt;
        if (timeSinceInc >= +5) {
            player1.addMoney(player1.getIncome());
            player2.addMoney(player2.getIncome());
            timeSinceInc = 0;
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

}

