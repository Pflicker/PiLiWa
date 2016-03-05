package de.beaverstudios.plw.Screens;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import de.beaverstudios.plw.PlwGame;

import de.beaverstudios.plw.Units.BuildingManager;
import de.beaverstudios.plw.Units.UnitManager;

public class GameScreen implements Screen,InputProcessor {
    public long startTime;
    public long gameTime;
    public long gameTimeInt;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private SpriteBatch batch;

    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private float gameCamX;
    private float gameCamY;
    public Integer p;

    private de.beaverstudios.plw.Hud.Hud hud;
    public UnitManager um;
    public BuildingManager bm;

    public GameScreen() {

        p = 1;
        startTime = System.nanoTime();
        gameTimeInt = 0;

        // create the camera and the SpriteBatch
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(PlwGame.V_WIDTH, PlwGame.V_HEIGHT, gamecam);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("map1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1);

        batch = new SpriteBatch();

        um = new UnitManager();
        bm = new BuildingManager();
        hud = new de.beaverstudios.plw.Hud.Hud(batch,um,bm);


        gameCamX = gamePort.getWorldWidth() / 2;
        gameCamY = gamePort.getWorldHeight() / 2;
        gamecam.position.set(gameCamX, gameCamY, 0);

        InputMultiplexer im = new InputMultiplexer(hud.hudStage,hud.menuStage, this);
        Gdx.input.setInputProcessor(im);
    }


    public void update(float dt){
        gameTime = System.nanoTime() - startTime;
        gameTimeInt = TimeUtils.timeSinceNanos(startTime)/1000000;

        gamecam.update();
        gamecam.position.set(gameCamX, gameCamY, 0);
        renderer.setView(gamecam);
        hud.update(dt);
        bm.update(dt);
        um.update(dt);

    }

    @Override
    public void render(float delta) {
        update(delta);

        gamePort.apply();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        batch.setProjectionMatrix(gamecam.combined);
        batch.begin();
        um.render(batch);
        batch.end();

        batch.setProjectionMatrix(hud.hudStage.getCamera().combined);
        hud.hudStage.draw();
        hud.menuStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        hud.hudPort.update(width,height);
        gamePort.update(width, height);
        gamecam.update();
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        hud.dispose();
        batch.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            gameCamX -= 1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            gameCamX += 1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            gameCamY += 1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            gameCamY -= 1;
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
            gamecam.zoom += 0.1*amount;
        return true;
    }
}
