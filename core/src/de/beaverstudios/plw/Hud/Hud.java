package de.beaverstudios.plw.Hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


import de.beaverstudios.plw.PlwGame;
import de.beaverstudios.plw.Screens.GameScreen;

/**
 * Created by Grass on 3/2/2016.
 */
public class Hud implements InputProcessor {

    public Stage hudStage;
    public Viewport hudPort;
    private OrthographicCamera hudCam;
    private TextureAtlas hudAtlas;
    public static GameInfo gameInfo;
    public Menu menu;
    private static Skin skin;

    public Hud() {

        skin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        hudCam = new OrthographicCamera();
        hudPort = new FitViewport(PlwGame.V_WIDTH, PlwGame.V_HEIGHT, hudCam);
        hudStage = new Stage(hudPort, GameScreen.getBatch());

        gameInfo = new GameInfo(skin);
        menu = new Menu();

        hudStage.addActor(gameInfo.infoTable);
        hudStage.addActor(menu.table);
        hudStage.addActor(menu.dialog.table);
    }

    public void update(float dt) {
        gameInfo.update(dt);
        menu.update(dt);
    }

    public static Skin getSkin() {
        return skin;
    }


    public void dispose() {
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
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
        return false;
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
        return false;
    }
}