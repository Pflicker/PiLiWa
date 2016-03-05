package de.beaverstudios.plw.Hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.beaverstudios.plw.Units.Building;
import de.beaverstudios.plw.Units.BuildingManager;
import de.beaverstudios.plw.Units.Marine;
import de.beaverstudios.plw.Units.UnitManager;

/**
 * Created by Grass on 3/5/2016.
 */
public class BuildMenu {
    private TextButton btnMarine;
    private TextButton btnReturn;
    private static Label marineLabel;
    public static int marineCount;
    private Menu menu;

    public BuildMenu(Skin skin, final BuildingManager bm, final UnitManager um, Menu menu) {

        btnMarine = new TextButton("Marine", skin);
        btnReturn = new TextButton("Return",skin);
        marineLabel = new Label(String.format("%03d", marineCount), skin);
        marineLabel.setText(String.format("%03d", marineCount));
        this.menu = menu;

        btnMarine.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Clicked Button", "Marine");
                clickActionButton();


            }
        });


        btnReturn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                    clickActionReturn();
                    Gdx.app.log("Button:", "Return");
                }

        });



    }
    private void clickActionReturn(){
        menu.setMenuStateChanged(true);
        menu.setMenuState(0);

    }
    private void clickActionButton(){
        menu.setMenuStateChanged(true);
        menu.setMenuState(2);

    }

        public void create(Table table) {
            table.clear();
            table.row();
            table.add(btnReturn);
            table.row();
            table.row();
            table.row();
            table.add(btnMarine);
            table.add(marineLabel);

        }
    }
