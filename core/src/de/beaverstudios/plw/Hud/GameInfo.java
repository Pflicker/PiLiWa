package de.beaverstudios.plw.Hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.TimeUtils;

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

    public static Long startTime;
    public static Long gameTime;
    public static Integer incomeCom;
    public static Integer incomePlayer;
    public static Integer moneyCom;
    public static Integer moneyPlayer;
    public static Long timeSinceInc;

    public Table infoTable;

    public GameInfo(Skin skin) {
        startTime = System.nanoTime();
        gameTime = 0L;
        timeSinceInc = System.nanoTime();
        incomeCom = 5;
        incomePlayer = 5;
        moneyCom = 100;
        moneyPlayer = 100;

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
    }

    public static Long getGameTime() {
        return gameTime;
    }

    public static void setGameTime(Long gameTime) {
        GameInfo.gameTime = gameTime;
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
        gameTime = (System.nanoTime() - startTime) /1000000000;

        if(TimeUtils.timeSinceNanos(this.timeSinceInc) / 1000L >= 5000000L) {
            moneyCom = moneyCom + incomeCom;
            moneyPlayer = moneyPlayer+incomePlayer;
            timeSinceInc = System.nanoTime();
        }
        incomeComValue.setText(String.format("%03d", incomeCom));
        incomePlayerValue.setText(String.format("%03d", incomePlayer));

        gameTimeValue.setText(String.format("%03d", gameTime));
        moneyPlayerValue.setText(String.format("%04d", moneyPlayer));
        moneyComValue.setText(String.format("%04d", moneyCom));

    }
}
