package de.beaverstudios.plw.Hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


import de.beaverstudios.plw.Screens.GameScreen;

/**
 * Created by Grass on 3/5/2016.
 */
public class BuildingInfoMenu {

    private TextButton btnBuild;
    private TextButton btnReturn;
    private static Label unitNameLabel;
    private String unitName;

    public BuildingInfoMenu(){

        unitName="Marine";
        btnBuild = new TextButton("Marine Info", Hud.getSkin());
        btnReturn = new TextButton("Return", Hud.getSkin());
        unitNameLabel = new Label(unitName, Hud.getSkin());
        unitNameLabel.setText(Menu.getName());

        btnBuild.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                    switch(Menu.getUnitCode()){
                        case 0:
                            if (GameInfo.getMoneyPlayer() >= 10) {
                                Gdx.app.log("Clicked Button", "Marine");
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
        table.add(unitNameLabel);
        table.row();
        table.add(btnBuild);
    }
}
