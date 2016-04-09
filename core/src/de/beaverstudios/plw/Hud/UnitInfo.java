package de.beaverstudios.plw.Hud;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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
    private Integer shield;
    private Integer maxShield;
    private static Label lbShield;
    private static Label lbLife;

    public UnitInfo(){


        table = new Table(Hud.getSkin());
        table.setBounds(PlwGame.V_WIDTH * 0.8f, 0, PlwGame.V_WIDTH * 0.2f, PlwGame.V_HEIGHT * 0.2f);
        table.setBackground(TextureManager.IMGFACTORY.getDrawable());
        table.padTop(table.getHeight() * 0.4f);

        unit = Game.player2.getUnits().get(0);
        life = unit.getLife();
        maxLife = unit.getMaxLife();
        lbLife = new Label("Life: " + life + " / " + maxLife,Hud.getSkin());
        lbLife.setSize(table.getWidth()*0.5f, table.getHeight()*0.05f);
        healthBar = new HealthBar(table.getX(),table.getY(),table.getWidth(),table.getHeight(),life,maxLife);


        if (unit.getArmorType() == ArmorType.SHIELD){
            shield = unit.getShieldValue();
            maxShield = unit.getMaxShieldValue();
            lbShield= new Label("Shield: " + shield + " / " + maxShield,Hud.getSkin());
            shieldBar = new ShieldBar(table.getX(),table.getY(),table.getWidth(),table.getHeight(),shield,maxShield);
            table.add(lbShield).center().top().setActorHeight(table.getHeight()*0.2f);
            table.row();
        }
        table.add(lbLife);
    }

    public void update(float dt){
        if(unit != null) {
            if (unit.getArmorType() == ArmorType.SHIELD) {
                shield = unit.getShieldValue();
                maxShield = unit.getMaxShieldValue();
                lbShield.setText("Shield: " + shield + " / " + maxShield);
            }

            life = unit.getLife();
            maxLife = unit.getMaxLife();
            lbLife.setText("Life: " + life + " / " + maxLife);
        }
    }

    public void draw(SpriteBatch batch) {
        if (unit != null) {
            if (unit.getArmorType() == ArmorType.SHIELD) {
                shieldBar.draw(batch, 0, table.getX(), table.getY() + table.getHeight() * 0.8f, table.getWidth(), table.getHeight() * 0.2f, shield, maxShield);
            }
            healthBar.draw(batch, 0, table.getX(), table.getY() + table.getHeight() * 0.6f, table.getWidth(), table.getHeight() * 0.2f, life, maxLife);
        }
    }

    public static void setUnit(Unit unit) {
        lbShield.setText("");
        lbLife.setText("");
        UnitInfo.unit = unit;
    }

    public static void clearInfo() {
        unit = null;
        lbShield.setText("");
        lbLife.setText("");
    }
}
