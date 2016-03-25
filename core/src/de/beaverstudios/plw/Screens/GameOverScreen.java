package de.beaverstudios.plw.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import de.beaverstudios.plw.PlwGame;

/**
 * Created by Grass on 3/20/2016.
 */
public class GameOverScreen implements Screen {


    private Skin skin;
    private Stage stage;

    private Table table;
    private TextButton btnMainMenu;

    private SpriteBatch batch;
    private Sprite sprite;
    private Viewport gameOverPort;
    private PlwGame gam;


    public GameOverScreen(final PlwGame game) {
        this.gam = game;
        skin = new Skin(Gdx.files.internal("skins/uiskin.json"));

        gameOverPort = new FitViewport(PlwGame.V_WIDTH,PlwGame.V_HEIGHT);
        stage = new Stage(gameOverPort);

        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top);
        table.setPosition(0, Gdx.graphics.getHeight());


        btnMainMenu = new TextButton("Return to Main Menu",skin);

        btnMainMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Clicked button", "Yep, you did");
                game.setScreen(new de.beaverstudios.plw.Screens.MainMenuScreen(game));
                dispose();
            }
        });


        table.padTop(30);
        table.add(btnMainMenu).padBottom(30);

        stage.addActor(table);

        batch = new SpriteBatch();
        sprite = new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")));
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        InputMultiplexer im = new InputMultiplexer(stage);
        Gdx.input.setInputProcessor(im);

    }
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        sprite.draw(batch);
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }

    @Override
    public void dispose(){
        skin.dispose();
        stage.dispose();
        batch.dispose();
    }
    @Override
    public void hide(){

    }

    @Override
    public void resume(){

    }

    @Override
    public void resize(int width, int height){
        gameOverPort.update(width, height);


    }

    @Override
    public void pause() {

    }

}
