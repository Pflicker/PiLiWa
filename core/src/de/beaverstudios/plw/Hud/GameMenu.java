package de.beaverstudios.plw.Hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.beaverstudios.plw.Screens.GameScreen;


/**
 * Created by Grass on 3/5/2016.
 */
public class GameMenu {

    private TextButton btnBuildMenu;

    public GameMenu() {

        btnBuildMenu = new TextButton("Build", Hud.getSkin());

        btnBuildMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Clicked Button", "Build");
                Menu.setMenuState(1);
                Menu.setMenuStateChanged(true);
            }

        });

    }

    public void create(Table table) {
        table.add(btnBuildMenu);
    }
}