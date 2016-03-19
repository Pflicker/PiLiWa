package de.beaverstudios.plw.Screens;


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

import de.beaverstudios.plw.Hud.Hud;
import de.beaverstudios.plw.PlwGame;

import de.beaverstudios.plw.Buildings.BuildingManager;
import de.beaverstudios.plw.Units.Grid;
import de.beaverstudios.plw.Units.UnitManager;

public class GameScreen implements Screen,InputProcessor {
    public static long startTime;
    public static long gameTime;
    public static long gameTimeInt;

    private static TmxMapLoader mapLoader;
    private static TiledMap map;
    private static OrthogonalTiledMapRenderer renderer;

    private static SpriteBatch batch;

    private static OrthographicCamera gamecam;
    private static Viewport gamePort;
    private static float gameCamX;
    private static float gameCamY;
    public Integer p;

    public static Hud hud;
    public static UnitManager um;
    public static BuildingManager bm;

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
        hud = new Hud();


        gameCamX = gamePort.getWorldWidth() / 2;
        gameCamY = gamePort.getWorldHeight() / 2;
        gamecam.position.set(gameCamX, gameCamY, 0);

        InputMultiplexer im = new InputMultiplexer(hud.hudStage, this);
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
        batch.dispose();
    }

    public static SpriteBatch getBatch() {
        return batch;
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
