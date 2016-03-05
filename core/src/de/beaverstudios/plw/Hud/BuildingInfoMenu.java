package de.beaverstudios.plw.Hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import de.beaverstudios.plw.Units.Building;
import de.beaverstudios.plw.Units.BuildingManager;
import de.beaverstudios.plw.Units.Marine;
import de.beaverstudios.plw.Units.Unit;
import de.beaverstudios.plw.Units.UnitManager;

/**
 * Created by Grass on 3/5/2016.
 */
public class BuildingInfoMenu {

    private TextButton btnBuild;
    private TextButton btnReturn;
    private static Label unitNameLabel;
    private static String unitName;
    private Menu menu;
    private Label movementspeedLabel;
    private Marine marine;


    public BuildingInfoMenu(Skin skin, final BuildingManager bm, final UnitManager um, final Menu menu) {
        unitName = menu.getName();
        this.menu = menu;
        btnBuild = new TextButton("Build Marine", skin);
        btnReturn = new TextButton("Return", skin);
        unitNameLabel = new Label(unitName, skin);
        unitNameLabel.setText(menu.getName());
        movementspeedLabel = new Label("5", skin);


        btnBuild.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (GameInfo.getMoneyPlayer() >= 10) {
                    Gdx.app.log("Clicked Button", "Marine");
                    menu.hud.gameInfo.setMoneyPlayer(GameInfo.getMoneyPlayer() - 10);
                    menu.hud.gameInfo.setIncomePlayer(GameInfo.getIncomePlayer() + 2);
                    bm.playerBuilding.add(new Building(um, new Marine(1)));
                } else {
                    Gdx.app.log("Warning:", "Not enough Money");
                }
            }
        });

        btnReturn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickAction();
                Gdx.app.log("Button:", "Return");
            }

        });


    }

    private void clickAction() {
        menu.setMenuStateChanged(true);
        menu.setMenuState(1);

    }

    public void create(Table table) {
        table.clear();
        table.row();
        table.add(btnReturn);
        table.row();
        table.row();
        table.row();
        table.add(btnReturn);
        table.add(unitNameLabel);
        table.row();
        table.add(movementspeedLabel);


    }
}
