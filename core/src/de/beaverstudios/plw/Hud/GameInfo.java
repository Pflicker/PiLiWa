package de.beaverstudios.plw.Hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;

import de.beaverstudios.plw.Screens.GameScreen;

/**
 * Created by Grass on 3/5/2016.
 */
public class GameInfo {
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

    private TextButton btnState;
    private TextButton btnSpeed;


    public static Integer incomeCom;
    public static Integer incomePlayer;
    public static Integer moneyCom;
    public static Integer moneyPlayer;
    public static float timeSinceInc;

    public Table infoTable;

    public GameInfo(Skin skin) {
        timeSinceInc = System.nanoTime();
        incomeCom = 5;
        incomePlayer = 5;
        moneyCom = 100;
        moneyPlayer = 100;

        btnState = new TextButton("Pause",skin);
        btnSpeed = new TextButton("Double",skin);

        btnState.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                switch (GameScreen.state) {
                    case RUN: {
                        GameScreen.state = GameScreen.STATE.PAUSE;
                        btnState.setText("Resume");
                        Gdx.app.log("Button:", "Pause");
                        break;
                    }
                    case PAUSE: {
                        GameScreen.state = GameScreen.STATE.RUN;
                        btnState.setText("Pause");
                        Gdx.app.log("Button:", "Run");
                        break;

                    }
                }
            }
        });

        btnSpeed.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                switch (GameScreen.speed) {
                    case 0: {
                        GameScreen.speed = 1;
                        btnSpeed.setText("Normal Speed");
                        Gdx.app.log("Button:", "Double Speed");
                        break;
                    }
                    case 1: {
                        GameScreen.speed = 0;
                        btnSpeed.setText("Double Speed");
                        Gdx.app.log("Button:", "Normal Speed");
                        break;

                    }
                }
            }
        });

        incomeComLabel= new Label("Income Com: ", skin);
        incomeComValue =new Label(String.format("%03d", incomeCom), skin);
        incomePlayerLabel = new Label("Income Player: ",skin);
        incomePlayerValue = new Label(String.format("%03d", incomePlayer),skin);
        moneyComLabel = new Label("Money Com: ", skin);
        moneyComValue = new Label(String.format("$04d", moneyCom), skin);
        moneyPlayerLabel = new Label ("Money Player: ",skin);
        moneyPlayerValue = new Label(String.format("%04d", moneyPlayer),skin);
        gameTimeLabel = new Label("Time: ", skin);
        gameTimeValue = new Label(String.format("%03d", GameScreen.gameTimeInt),skin);

        infoTable = new Table();
        infoTable.setBounds(0, 0, Gdx.graphics.getWidth() * 0.8f, Gdx.graphics.getHeight());
        infoTable.top();
        infoTable.setSkin(skin);
        infoTable.row();
        infoTable.add(btnState).left();
        infoTable.add(btnSpeed);
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

    }

    public static Integer getIncomePlayer() {
        return incomePlayer;
    }

    public static void setIncomePlayer(Integer incomePlayer) {
        GameInfo.incomePlayer = incomePlayer;
    }

    public static Integer getIncomeCom() {
        return incomeCom;
    }

    public static void setIncomeCom(Integer incomeCom) {
        GameInfo.incomeCom = incomeCom;
    }

    public static Integer getMoneyCom() {
        return moneyCom;
    }

    public static void setMoneyCom(Integer moneyCom) {
        GameInfo.moneyCom = moneyCom;
    }

    public static Integer getMoneyPlayer() {
        return moneyPlayer;
    }

    public static void setMoneyPlayer(Integer moneyPlayer) {
        GameInfo.moneyPlayer = moneyPlayer;
    }

    public void update(float dt){

        timeSinceInc += dt;
        if(timeSinceInc >=  + 5) {
            moneyCom = moneyCom + incomeCom;
            moneyPlayer = moneyPlayer+incomePlayer;
            timeSinceInc = 0;
        }
        incomeComValue.setText(String.format("%03d", incomeCom));
        incomePlayerValue.setText(String.format("%03d", incomePlayer));

        gameTimeValue.setText(String.format("%03d", GameScreen.gameTimeInt));
        moneyPlayerValue.setText(String.format("%04d", moneyPlayer));
        moneyComValue.setText(String.format("%04d", moneyCom));

    }
}
