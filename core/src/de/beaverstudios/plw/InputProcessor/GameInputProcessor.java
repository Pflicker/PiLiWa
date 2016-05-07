package de.beaverstudios.plw.InputProcessor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

import de.beaverstudios.plw.Hud.Hud;
import de.beaverstudios.plw.Player.Game;
import de.beaverstudios.plw.Player.Player;
import de.beaverstudios.plw.Screens.GameScreen;
import de.beaverstudios.plw.Units.Unit;

/**
 * Created by root on 08.04.16.
 */
public class GameInputProcessor implements InputProcessor {
    @Override
    public boolean keyDown(int keycode) {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            GameScreen.gameCamX -= 1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            GameScreen.gameCamX += 1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            GameScreen.gameCamY += 1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            GameScreen.gameCamY -= 1;
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        Vector2 vector;
        vector = new Vector2();

        vector.set(screenX, screenY);
        GameScreen.gameStage.screenToStageCoordinates(vector);

        System.out.println("ScreenCoords: " + screenX + " " + screenY);
        System.out.println("StageCoords: " + vector.x + " " + vector.y);

        for(Player p : Game.players){
            for(Unit u : p.getUnits()){
                if (u.getBounds().contains(vector.x,vector.y)) {
                    GameScreen.hud.setUnit(u);
                    System.out.println("HIT");
                    return true;
                }
                }
            }
        GameScreen.hud.clearInfo();

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {

        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        GameScreen.gamecam.zoom += 0.1*amount;
        return true;
    }
}
