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
public class BuildMenu {
    private TextButton btnGetMarine;
    private TextButton btnReturn;
    private static Label marineLabel;
    public static int marineCount;

    public BuildMenu() {

        btnGetMarine = new TextButton("Marine", Hud.getSkin());
        btnReturn = new TextButton("Return",Hud.getSkin());
        marineLabel = new Label(String.format("%03d", marineCount), Hud.getSkin());
        marineLabel.setText(String.format("%03d", marineCount));

        btnGetMarine.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Clicked Button", "Marine");
                Menu.setMenuStateChanged(true);
                Menu.setMenuState(2);
                Menu.setUnitCode(0);
            }
        });

        btnReturn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Menu.setMenuStateChanged(true);
                Menu.setMenuState(0);
                Gdx.app.log("Button:", "Return");
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
            table.add(btnGetMarine);
            table.add(marineLabel);
    }
}
