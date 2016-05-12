package de.beaverstudios.plw.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import de.beaverstudios.plw.PlwGame;
import de.beaverstudios.plw.Units.UnitManager;

/**
 * Created by Grass on 5/11/2016.
 */
public class UnitDetailScreen implements Screen {

    private Stage stage;
    private Viewport menuPort;
    final PlwGame game;

    private Skin skin;
    private Label header;
    private Table table;
    private SelectBox sbRace;
    private UnitManager unitManager;

    private Sprite sprite;
    private SpriteBatch batch;

    public UnitDetailScreen(final PlwGame game) {
        this.game=game;

        menuPort=new FitViewport(PlwGame.V_WIDTH,PlwGame.V_HEIGHT);
        stage= new Stage(menuPort);

        skin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        table= new Table(skin);

        unitManager = new UnitManager();

        header = new Label("Units", skin);
        sbRace = new SelectBox(skin);

        table.add(header);
        table.row();
        table.add(sbRace);

        stage.addActor(table);

        batch = new SpriteBatch();
        sprite = new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")));
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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

    }
}
