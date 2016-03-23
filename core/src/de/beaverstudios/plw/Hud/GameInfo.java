package de.beaverstudios.plw.Hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.beaverstudios.plw.Player.Game;
import de.beaverstudios.plw.Screens.GameScreen;
import de.beaverstudios.plw.TextureManager;

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
    private Button btnPause;
    private Button btnResume;
    private TextButton btnSpeed;

    public Table infoTable;


    public GameInfo(Skin skin) {

        btnState = new TextButton("Pause",skin);
        btnSpeed = new TextButton("Double",skin);

        btnPause = new Button(TextureManager.IMGBTNPAUSE.getDrawable());

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
        incomeComValue =new Label(String.format("%03d", Game.player1.getIncome()), skin);
        incomePlayerLabel = new Label("Income Player: ",skin);
        incomePlayerValue = new Label(String.format("%03d", Game.player2.getIncome()),skin);
        moneyComLabel = new Label("Money Com: ", skin);
        moneyComValue = new Label(String.format("$04d", Game.player1.getMoney()), skin);
        moneyPlayerLabel = new Label ("Money Player: ",skin);
        moneyPlayerValue = new Label(String.format("%04d", Game.player1.getMoney()),skin);
        gameTimeLabel = new Label("Time: ", skin);
        gameTimeValue = new Label(String.format("%03d", Game.getGameTimeInt()),skin);

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

    public void update(float dt){


        incomeComValue.setText(String.format("%03d", Game.player1.getIncome()));
        incomePlayerValue.setText(String.format("%03d", Game.player2.getIncome()));

        gameTimeValue.setText(String.format("%03d", Game.getGameTimeInt()));
        moneyPlayerValue.setText(String.format("%04d", Game.player2.getMoney()));
        moneyComValue.setText(String.format("%04d", Game.player1.getMoney()));

    }
}
