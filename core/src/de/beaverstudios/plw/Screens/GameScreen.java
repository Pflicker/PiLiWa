package de.beaverstudios.plw.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import de.beaverstudios.plw.Hud.Hud;
import de.beaverstudios.plw.InputProcessor.GameInputProcessor;
import de.beaverstudios.plw.Player.Game;
import de.beaverstudios.plw.PlwGame;
import de.beaverstudios.plw.Buildings.BuildingManager;
import de.beaverstudios.plw.Techs.Techs;
import de.beaverstudios.plw.TextureManager;
import de.beaverstudios.plw.Units.UnitManager;

public class GameScreen implements Screen {


    private static TmxMapLoader mapLoader;
    private static TiledMap map;
    private static OrthogonalTiledMapRenderer renderer;

    private static SpriteBatch batch;
    public static TextureManager textureManager;
    public static BitmapFont font;

    public static OrthographicCamera gamecam;
    public static Stage gameStage;
    public static Viewport gamePort;
    public static float gameCamX;
    public static float gameCamY;
    public Integer p;

    public enum STATE{
        RUN,PAUSE
    }
    public static STATE state;

    public static int speed;

    public static Game world;

    public static Hud hud;
    public static UnitManager um;
    public static BuildingManager bm;
    public static Techs techs;

    final PlwGame gam;

    boolean keyPressed;
    public static boolean gameOver;

    public GameScreen(PlwGame game) {
        this.gam = game;
        state = STATE.RUN;
        speed = 0;

        // create Batch
        batch = new SpriteBatch();
        textureManager = new TextureManager();
        font = new BitmapFont();

        // create the camera and the SpriteBatch
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(PlwGame.V_WIDTH, PlwGame.V_HEIGHT, gamecam);
        gameStage = new Stage(gamePort,batch);

        // create Map and Maprenderer
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("map2.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1);

        world = new Game();

        um = new UnitManager();
        bm = new BuildingManager();
        hud = new Hud();
        techs = new Techs();

        gameCamX = gamePort.getWorldWidth() / 2;
        gameCamY = gamePort.getWorldHeight() / 2;
        gamecam.position.set(gameCamX, gameCamY, 0);

        InputMultiplexer im = new InputMultiplexer();
        im.addProcessor(Hud.hudStage);
        im.addProcessor(gameStage);
        im.addProcessor(new GameInputProcessor());
        Gdx.input.setInputProcessor(im);
    }


    public void update(float dt) {

            world.update(dt);
            float deltaTime = state == STATE.PAUSE ? 0 : Gdx.graphics.getDeltaTime();
            if (speed == 1) dt = dt * 2;
            gamecam.update();
            gamecam.position.set(gameCamX, gameCamY, 0);
            renderer.setView(gamecam);
            hud.update(dt);
            bm.update(dt);
            um.update(dt);

        if (gameOver) {
            gameOver = false;
            gam.setScreen(new de.beaverstudios.plw.Screens.GameOverScreen(gam));
           // dispose(); Fehler
        }

    }
    @Override
    public void render(float dt) {

        switch(state) {
            case RUN:
                update(dt);
                break;
            case PAUSE:
                break;
        }

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gamePort.apply();
        renderer.render();
        batch.setProjectionMatrix(gamecam.combined);
        batch.begin();
        um.render(batch);
        batch.end();
        batch.setProjectionMatrix(hud.hudStage.getCamera().combined);
        hud.hudStage.draw();
        batch.begin();
        hud.draw(batch);
        batch.end();

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
        state = STATE.PAUSE;
    }

    @Override
    public void resume() {
        state = STATE.RUN;
    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        batch.dispose();
        font.dispose();
    }

    public static SpriteBatch getBatch() {
        return batch;
    }


}
