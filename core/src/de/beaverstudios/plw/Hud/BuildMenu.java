package de.beaverstudios.plw.Hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.beaverstudios.plw.Buildings.BuildingManager;
import de.beaverstudios.plw.Buildings.BuildingTypes;
import de.beaverstudios.plw.Screens.GameScreen;

/**
 * Created by Grass on 3/5/2016.
 */
public class BuildMenu {

    private TextButton btnReturn;

    private TextButton btnGetBarracks;
    private TextButton btnGetBuilding2;



    public BuildMenu() {

        btnReturn = new TextButton("Return",Hud.getSkin());

        btnGetBarracks = new TextButton("Barracks", Hud.getSkin());
        btnGetBuilding2 = new TextButton("Building2", Hud.getSkin());

        btnReturn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Menu.setMenuStateChanged(true);
                Menu.setMenuState(0);
                Gdx.app.log("Button:", "Return");
            }

        });

        btnGetBarracks.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Clicked Button", "Barracks");
                BuildingManager.newBuildingType = BuildingTypes.BARRACKS;
                Menu.setMenuStateChanged(true);
                Menu.setMenuState(2);
            }
        });

        btnGetBuilding2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Clicked Button", "Building 2");
                BuildingManager.newBuildingType = BuildingTypes.BUILDING2;
                Menu.setMenuStateChanged(true);
                Menu.setMenuState(2);
            }
        });

    }

    public void create(Table table) {
            table.clear();
            table.row();
            table.add(btnReturn);
            table.row();
            table.row();
            table.row();
            table.add(btnGetBarracks);
            table.row();
            table.add(btnGetBuilding2);
    }
}
