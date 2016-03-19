package de.beaverstudios.plw.Hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


import de.beaverstudios.plw.Buildings.BuildingManager;
import de.beaverstudios.plw.Screens.GameScreen;
import de.beaverstudios.plw.Units.Unit;
import de.beaverstudios.plw.Units.UnitManager;

/**
 * Created by Grass on 3/5/2016.
 */
public class BuildingInfoMenu {

    private static Skin skin;
    private static TextButton btnBuild;
    private static TextButton btnReturn;
    private static Label buildingNameLabel;
    private static Label lbDamage;
    private String buildingName;
    private String unitName;
    private Unit u;

    public BuildingInfoMenu(){

        skin = Hud.getSkin();
        switch(BuildingManager.newBuildingType){

            case BARRACKS:
            u = UnitManager.ghostMarine;
                break;
        }



        this.buildingName = BuildingManager.newBuildingType.getBuildingName();
        this.unitName = BuildingManager.newBuildingType.getUnitName();


        btnBuild = new TextButton("Build...", Hud.getSkin());
        btnReturn = new TextButton("Return", Hud.getSkin());
        buildingNameLabel = new Label(buildingName, Hud.getSkin());
        buildingNameLabel.setText(Menu.getName());
        lbDamage = new Label(String.format("%03d", u.getDamage()), skin);

        btnBuild.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                    switch(BuildingManager.newBuildingType){
                        case BARRACKS:
                            if (GameInfo.getMoneyPlayer() >= BuildingManager.newBuildingType.getPrice()) {
                                Gdx.app.log("Clicked Button", "Build...");
                                Menu.setDialogPlacement(true);
                            } else {
                                Gdx.app.log("Warning:", "Not enough Money");
                            }
                            break;
                    }
            }
        });

        btnReturn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Menu.setMenuStateChanged(true);
                Menu.setMenuState(1);
                Gdx.app.log("Button:", "Return");
                Menu.setRet(true);
            }
        });
    }

    public void create(Table table) {
        table.clear();
        table.row();
        table.add(btnReturn);
        table.row();
        table.add(buildingNameLabel);
        table.row();
        table.add(lbDamage);
        table.add(btnBuild);
    }
}
