package de.beaverstudios.plw.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import de.beaverstudios.plw.PlwGame;
import de.beaverstudios.plw.TextureManager;
import javafx.scene.shape.Line;

/**
 * Created by Grass on 5/9/2016.
 */
public class CampaignOverview implements Screen,InputProcessor {

    private Skin skin;
    private Stage stage;

    private Table table;

    private TextButton btnReturn;
    private Array<TextButton> btnLevel = new Array<TextButton>();
    private TextButton btnLevel1;

    private SpriteBatch batch;
    private Array<Line> lines;
    private EndNote endNote;

    private ShapeRenderer shapeRenderer;
    private Texture texture;
    private Viewport menuPort;
    private Camera cam;
    int srcX;

    final PlwGame game;

    public CampaignOverview(final PlwGame game) {
        this.game=game;
        skin = new Skin(Gdx.files.internal("skins/uiskin.json"));

        menuPort = new FitViewport(PlwGame.V_WIDTH,PlwGame.V_HEIGHT);
        stage = new Stage(menuPort);
        cam = stage.getCamera();

        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top);
        table.setPosition(0, Gdx.graphics.getHeight());

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

        srcX = 0;

        btnReturn = new TextButton("Return",skin);
        btnLevel1 = new TextButton("Level 1", skin);
        btnLevel1.setPosition(100,100);
        lines = new Array<Line>();
        lines.add(new Line(btnLevel1.getX(),btnLevel1.getY(),200,200));
        endNote = new EndNote(shapeRenderer,200,200,20);
        endNote.debug();

        System.out.println(endNote.getWidth() + " " + endNote.getHeight());
        btnReturn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Clicked button", "Return");
                game.setScreen(new de.beaverstudios.plw.Screens.SPGameModeScreen(game));
                dispose();
            }
        });

        endNote.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                endNote.addAction(Actions.fadeIn(1));
                System.out.println("Action");
            }
        });

        table.padTop(30);
        table.add(btnReturn).padBottom(30);
        table.row();

        stage.addActor(endNote);
        stage.addActor(btnLevel1);
        stage.addActor(table);

        batch = new SpriteBatch();
        texture = (TextureManager.CAMPAIGNMAP);

        InputMultiplexer im = new InputMultiplexer(stage,this);
        Gdx.input.setInputProcessor(im);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(texture, 0, 0, srcX, 0, PlwGame.V_WIDTH, PlwGame.V_HEIGHT);
        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.line((float)lines.get(0).getStartX()-srcX,(float)lines.get(0).getStartY(),(float)lines.get(0).getEndX()-srcX,(float)lines.get(0).getEndY());
        shapeRenderer.end();
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

        if((amount > 0 && srcX+50*amount <= (TextureManager.CAMPAIGNMAP.getWidth()-PlwGame.V_WIDTH)) || (amount < 0 && srcX+amount*50 >= 0)){
            srcX+=amount*50;
            cam.translate(amount*50,0,0);
        }
        return true;
    }
}
