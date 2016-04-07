package de.beaverstudios.plw.Hud;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import de.beaverstudios.plw.Player.Game;
import de.beaverstudios.plw.PlwGame;
import de.beaverstudios.plw.Screens.GameScreen;
import de.beaverstudios.plw.TextureManager;
import de.beaverstudios.plw.Units.Healthbar.HealthBar;
import de.beaverstudios.plw.Units.Unit;

/**
 * Created by root on 07.04.16.
 */
public class UnitInfo {

    public static Unit unit;
    public Table table;
    private HealthBar healthBar;

    public UnitInfo(){

        unit = Game.player1.getUnits().get(0);
        table = new Table(Hud.getSkin());
        table.setX(PlwGame.V_WIDTH);
        table.setY(PlwGame.V_HEIGHT * 0.8f);
        table.setBackground(TextureManager.IMGFACTORY.getDrawable());
        table.add(new TextButton("Test",Hud.getSkin()));
        table.setSize(200,100);

    }

    public void update(float dt){
        this.healthBar = unit.getHealthBar();


    }

    public void draw(){
        unit.getHealthBar().draw(GameScreen.getBatch(),0,table.getX(),table.getY(),table.getWidth(),table.getHeight(),unit.getLife(),unit.getMaxLife());
    }
}
