package de.beaverstudios.plw.Player;

import de.beaverstudios.plw.Techs.Techs;

/**
 * Created by Grass on 3/21/2016.
 */
public class Game {



    private static float gameTime;
    private static int gameTimeInt;

    public static Player player1;
    public static Player player2;
    private static float timeSinceInc;



    public Game() {
        player1 = new Player(true);
        player2 = new Player(false);



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

}

