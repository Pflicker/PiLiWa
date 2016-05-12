package de.beaverstudios.plw.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import de.beaverstudios.plw.PlwGame;

/**
 * Created by Grass on 5/9/2016.
 */
public class GameSettingScreen implements Screen,InputProcessor {

    private Skin skin;
    private Stage stage;

    private Table table;

    private Label lbGameMode;
    private SelectBox player1race;
    private SelectBox player2race;
    private SelectBox player1color;
    private SelectBox player2color;
    private SelectBox player1com;
    private SelectBox player2com;

    private TextButton btnReturn;
    private TextButton btnStart;

    private SpriteBatch batch;
    private Sprite sprite;
    private Viewport menuPort;

    private final PlwGame game;

    public GameSettingScreen(final PlwGame game) {

        skin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        this.game=game;

        menuPort = new FitViewport(PlwGame.V_WIDTH,PlwGame.V_HEIGHT);
        stage = new Stage(menuPort);

        table = new Table(skin);
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top);
        table.setPosition(0, Gdx.graphics.getHeight());
        table.columnDefaults(0);
        table.columnDefaults(1);
        table.columnDefaults(2);
        table.debug();

        lbGameMode = new Label("Custom Match",skin);
        btnReturn = new TextButton("Return",skin);
        btnStart = new TextButton("Start",skin);

        Color red = new Color(Color.RED);
        Color blue = new Color(Color.BLUE);

        player1race = new SelectBox(skin);
        player2race = new SelectBox(skin);
        player1race.setMaxListCount(1);
        player2race.setMaxListCount(1);
        player1race.setItems("Human");
        player2race.setItems("Human");

        player1color = new SelectBox(skin);
        player2color = new SelectBox(skin);
        player1color.setMaxListCount(2);
        player2color.setMaxListCount(2);
        player1color.setColor(255,0,0,1);
        player1color.setItems("Red","Blue");
        player2color.setItems("Red","Blue");


        player1color.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(player1color.getSelectedIndex() == 0){
                    player1color.setColor(Color.RED);
                }
                if(player1color.getSelectedIndex() == 1){
                    player1color.setColor(Color.BLUE);
                }
                updateSelectBox2();
            }
        });

        player2color.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(player2color.getSelectedIndex() == 0){
                    player2color.setColor(Color.RED);
                }
                if(player2color.getSelectedIndex() == 1){
                    player2color.setColor(Color.BLUE);
                }
            }
        });

        player1com = new SelectBox(skin);
        player2com = new SelectBox(skin);
        player1com.setMaxListCount(2);
        player2com.setMaxListCount(2);
        player1com.setItems("COM","Player");
        player2com.setItems("COM","Player");

        btnReturn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Clicked button", "Return");
                game.setScreen(new de.beaverstudios.plw.Screens.SPGameModeScreen(game));
                dispose();
            }
        });

        btnStart.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Clicked button", "Return");
                game.setScreen(new GameScreen(game));
                dispose();
            }
        });

        table.padTop(30);
        table.add(btnReturn).left().padBottom(30);
        table.row();
        table.add(lbGameMode).center().expandX().colspan(3);
        table.row();
        table.add(player1com);
        table.add(player1race);
        table.add(player1color);
        table.row();
        table.add(player2com);
        table.add(player2race);
        table.add(player2color);
        table.row();
        table.add(btnStart).center().padTop(PlwGame.V_HEIGHT*0.2f).colspan(3);
        stage.addActor(table);

        batch = new SpriteBatch();
        sprite = new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")));
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        InputMultiplexer im = new InputMultiplexer(stage,this);
        Gdx.input.setInputProcessor(im);
        updateSelectBox1();
        updateSelectBox2();
    }

    @Override
    public void show() {

    }

    public void updateSelectBox1(){
        if(player1color.getSelectedIndex() == 0){
            player2color.setSelectedIndex(1);
        }
        if(player1color.getSelectedIndex() == 1){
            player2color.setSelectedIndex(0);
        }
    }

    public void updateSelectBox2(){
        if(player2color.getSelectedIndex() == 0){
            player1color.setSelectedIndex(1);
        }
        if(player2color.getSelectedIndex() == 1){
            player1color.setSelectedIndex(0);
        }
    }
    @Override
    public void render(float dt) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        sprite.draw(batch);
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        menuPort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        skin.dispose();
        stage.dispose();
        game.dispose();
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
