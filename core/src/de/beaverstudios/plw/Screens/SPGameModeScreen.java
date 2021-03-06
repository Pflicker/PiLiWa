package de.beaverstudios.plw.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import de.beaverstudios.plw.PlwGame;
import de.beaverstudios.plw.TextureManager;

/**
 * Created by Grass on 3/21/2016.
 */
public class SPGameModeScreen implements Screen, InputProcessor {

    private Skin skin;
    private Stage stage;

    private Table table;

    private TextButton btnReturn;
    private Button btnCampaign;
    private Button btnCustomGame;

    private SpriteBatch batch;
    private Sprite sprite;
    private Viewport menuPort;

    final PlwGame game;

    public SPGameModeScreen(final PlwGame gam) {

        skin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        this.game = gam;

        menuPort = new FitViewport(PlwGame.V_WIDTH,PlwGame.V_HEIGHT);
        stage = new Stage(menuPort);

        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top);
        table.setPosition(0, Gdx.graphics.getHeight());

        btnReturn = new TextButton("Return",skin);
        btnCampaign = new Button(TextureManager.IMGBTNCAMPAIGN.getDrawable());
        btnCustomGame = new Button(TextureManager.IMGBTNRESUME.getDrawable());

        btnReturn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Clicked button", "Return");
                game.setScreen(new de.beaverstudios.plw.Screens.MainMenuScreen(game));
                dispose();
            }
        });

        btnCampaign.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Clicked button", "Campaign");
                game.setScreen(new de.beaverstudios.plw.Screens.CampaignOverview(game));
                dispose();

            }
        });

        btnCustomGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Clicked button", "Custom Game");
                game.setScreen(new de.beaverstudios.plw.Screens.GameSettingScreen(game));
                dispose();
            }
        });




        table.padTop(30);
        table.add(btnReturn).padBottom(30);
        table.row();
        table.add(btnCampaign).width(PlwGame.V_WIDTH*0.3f).height(PlwGame.V_HEIGHT*0.4f).left();
        table.add(btnCustomGame).width(PlwGame.V_WIDTH*0.3f).height(PlwGame.V_HEIGHT*0.4f).right();
        stage.addActor(table);

        batch = new SpriteBatch();
        sprite = new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")));
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        InputMultiplexer im = new InputMultiplexer(stage,this);
        Gdx.input.setInputProcessor(im);
    }
    @Override
    public void show() {
    }
    @Override
    public void dispose(){
        skin.dispose();
        stage.dispose();
        game.dispose();
    }
    @Override
    public void hide(){

    }

    @Override
    public void resume(){

    }

    @Override
    public void resize(int width, int height){
        menuPort.update(width, height);


    }

    @Override
    public void pause(){

    }


    @Override
    public void render (float delta){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        sprite.draw(batch);
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
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

