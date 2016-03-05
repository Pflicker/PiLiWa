package de.beaverstudios.plw.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sun.media.jfxmediaimpl.MediaDisposer;

import de.beaverstudios.plw.PlwGame;
import de.beaverstudios.plw.Units.Building;
import de.beaverstudios.plw.Units.BuildingManager;
import de.beaverstudios.plw.Units.Marine;
import de.beaverstudios.plw.Units.UnitManager;

/**
 * Created by Grass on 3/2/2016.
 */
public class Hud implements MediaDisposer.Disposable, InputProcessor{

    public Stage hudStage;
    public Viewport hudPort;

    BitmapFont font;

    public Long startTime;
    public Long gameTime;
    public Integer incomeCom;
    public Integer incomePlayer;
    public Integer moneyCom;
    public Integer moneyPlayer;
    public long timeSinceInc;
    public Table infoTable;
    private TextureAtlas hudAtlas;

    private Label incomePlayerLabel;
    private Label incomePlayerValue;
    private Label gameTimeLabel;
    private Label gameTimeValue;
    private Label incomeComLabel;
    private Label incomeComValue;
    private Label moneyPlayerLabel;
    private Label moneyPlayerValue;
    private Label moneyComLabel;
    private Label moneyComValue;

    Texture hudback;
    public Image white;
    public Image black;
    private Skin skin;
    private TextButton btnMarine;
    private Table buildTable;
    private Label marineLabel;
    public int marineCount;
    public boolean spawnMarine;
    public Marine marine;

    public Hud (SpriteBatch batch, final UnitManager um, final BuildingManager bm){

        startTime = System.nanoTime();
        gameTime = 0L;
        timeSinceInc = System.nanoTime();
        incomeCom = 5;
        incomePlayer = 5;
        moneyCom = 100;
        moneyPlayer = 100;

        skin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        hudPort = new FitViewport(PlwGame.V_WIDTH, PlwGame.V_HEIGHT, new OrthographicCamera());
        hudStage = new Stage(hudPort, batch);

        font = new BitmapFont();
        hudback = new Texture(Gdx.files.internal("hudback.png"));

        incomeComLabel= new Label("Income Com: ", skin);
        incomeComValue =new Label(String.format("%03d", incomeCom), skin);
        incomePlayerLabel = new Label("Income Player: ",skin);
        incomePlayerValue = new Label(String.format("%03d", incomePlayer),skin);
        moneyComLabel = new Label("Money Com: ", skin);
        moneyComValue = new Label(String.format("$04d", moneyCom), skin);
        moneyPlayerLabel = new Label ("Money Player: ",skin);
        moneyPlayerValue = new Label(String.format("%04d", moneyPlayer),skin);
        gameTimeLabel = new Label("Time: ", skin);
        gameTimeValue = new Label(String.format("%03d", gameTime),skin);

        btnMarine = new TextButton("Marine", skin);
        marineLabel = new Label(String.format("%03d", marineCount), skin);

        btnMarine.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (moneyPlayer >= 10) {
                    Gdx.app.log("Clicked Button", "Marine");
                    moneyPlayer = moneyPlayer - 10;
                    incomePlayer += 2;
                    marineCount++;
                    bm.playerBuilding.add(new Building(um, new Marine(1)));
                    Gdx.app.log("size", String.format("%03d", um.playerUnits.size()));
                    Gdx.app.log("size", String.format("%03d", bm.playerBuilding.size()));
                    Gdx.app.log("size", String.format("%03d", um.unitsSpawned));
                } else {
                    Gdx.app.log("Warning:", "Not enough Money");
                }
            }
        });


        WidgetGroup buildings = new WidgetGroup();
        buildings.addActor(btnMarine);

        infoTable = new Table();
        infoTable.setBounds(0, 0, Gdx.graphics.getWidth() * 0.8f, Gdx.graphics.getHeight());
        infoTable.top();
        infoTable.setSkin(skin);
        infoTable.row();
        infoTable.add();
        infoTable.add();
        infoTable.add(gameTimeLabel);
        infoTable.add(gameTimeValue);
        infoTable.add();
        infoTable.add();
        infoTable.row();
        infoTable.add(incomeComLabel).expandX();
        infoTable.add(incomeComValue).expandX();
        infoTable.add().expandX();
        infoTable.add(incomePlayerLabel).expandX();
        infoTable.add(incomePlayerValue).expandX();
        infoTable.row();
        infoTable.add(moneyComLabel).expandX();
        infoTable.add(moneyComValue).expandX();
        infoTable.add().expandX();
        infoTable.add(moneyPlayerLabel).expandX();
        infoTable.add(moneyPlayerValue).expandX();

        buildTable = new Table();
        buildTable.setBounds(Gdx.graphics.getWidth() * 0.8f, 0, Gdx.graphics.getWidth() * 0.2f, Gdx.graphics.getHeight());
        buildTable.top();
        buildTable.setSkin(skin);
        buildTable.row();
        buildTable.row();
        buildTable.row();
        buildTable.add(btnMarine);
        buildTable.add(marineLabel);

        hudStage.addActor(infoTable);
        hudStage.addActor(buildTable);


    }
    public void update(float dt) {

        gameTime = (System.nanoTime() - startTime) /1000000000;

        if(TimeUtils.timeSinceNanos(this.timeSinceInc) / 1000L >= 5000000L) {
            moneyCom = moneyCom + incomeCom;
            moneyPlayer = moneyPlayer+incomePlayer;
            timeSinceInc = System.nanoTime();
        }
        incomeComValue.setText(String.format("%03d", incomeCom));
        incomePlayerValue.setText(String.format("%03d", incomePlayer));
        marineLabel.setText(String.format("%03d", marineCount));
        gameTimeValue.setText(String.format("%03d", gameTime));
        moneyPlayerValue.setText(String.format("%04d", moneyPlayer));
        moneyComValue.setText(String.format("%04d", moneyCom));

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
