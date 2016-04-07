package de.beaverstudios.plw.Hud;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import de.beaverstudios.plw.Player.Game;
import de.beaverstudios.plw.PlwGame;
import de.beaverstudios.plw.Screens.GameScreen;
import de.beaverstudios.plw.Techs.Types.ArmorType;
import de.beaverstudios.plw.TextureManager;
import de.beaverstudios.plw.Units.Healthbar.HealthBar;
import de.beaverstudios.plw.Units.Healthbar.ShieldBar;
import de.beaverstudios.plw.Units.Unit;

/**
 * Created by root on 07.04.16.
 */
public class UnitInfo {

    public static Unit unit;
    public Table table;
    private HealthBar healthBar;
    private Integer life;
    private Integer maxLife;
    private ShieldBar shieldBar;

    public UnitInfo(){

        unit = Game.player1.getUnits().get(0);
        life = unit.getLife();
        maxLife = unit.getMaxLife();
        table = new Table(Hud.getSkin());
        table.setBounds(PlwGame.V_WIDTH * 0.8f, 0, PlwGame.V_WIDTH * 0.2f, PlwGame.V_HEIGHT * 0.2f);
        table.setBackground(TextureManager.IMGFACTORY.getDrawable());
        healthBar = new HealthBar(table.getX(),table.getY(),table.getWidth(),table.getHeight(),life,maxLife);
        table.add(healthBar);
    }

    public void update(float dt){
        life = unit.getLife();
        maxLife = unit.getMaxLife();
        //healthBar.draw(GameScreen.getBatch(),0,table.getX(),table.getY(),table.getWidth(),table.getHeight(),unit.getLife(),unit.getMaxLife());
    }

}
