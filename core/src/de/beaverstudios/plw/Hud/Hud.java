package de.beaverstudios.plw.Hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sun.media.jfxmediaimpl.MediaDisposer;

import de.beaverstudios.plw.PlwGame;
import de.beaverstudios.plw.Units.BuildingManager;
import de.beaverstudios.plw.Units.UnitManager;

/**
 * Created by Grass on 3/2/2016.
 */
public class Hud implements MediaDisposer.Disposable, InputProcessor{

    public Stage hudStage;
    public Stage menuStage;
    public Viewport hudPort;
    private TextureAtlas hudAtlas;
    public static GameInfo gameInfo;
    private Menu menu;
    private Skin skin;

    public Hud (SpriteBatch batch, final UnitManager um, final BuildingManager bm){

        skin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        hudPort = new FitViewport(PlwGame.V_WIDTH, PlwGame.V_HEIGHT, new OrthographicCamera());
        menuStage = new Stage(hudPort, batch);
        hudStage = new Stage(hudPort, batch);

        gameInfo = new GameInfo(skin);
        menu = new Menu(skin,bm,um, Hud .this);

        hudStage.addActor(gameInfo.infoTable);
        menuStage.addActor(menu.table);
    }

    public void update(float dt) {
        gameInfo.update(dt);
        menu.update();
        if (menu.isMenuStateChanged()){
            Gdx.app.log("Menu","State Changed");
            menuStage.clear();
            menuStage.addActor(menu.table);
            menu.setMenuStateChanged(false);
        }


        if (menu.isMenuStateChanged()) {
            menuStage.clear();
            menuStage.addActor(menu.table);
            menu.setMenuStateChanged(false);

        }
    }

    @Override
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
