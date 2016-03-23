package de.beaverstudios.plw.Hud.MenuStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.beaverstudios.plw.Hud.Hud;
import de.beaverstudios.plw.Hud.Menu;
import de.beaverstudios.plw.Screens.GameScreen;


/**
 * Created by Grass on 3/5/2016.
 */
public class GameMenu {

    private TextButton btnBuildMenu;
    private TextButton btnTechMenu;
    private TextButton btnBaseInfo;

    public GameMenu() {

        btnBuildMenu = new TextButton("Build", Hud.getSkin());
        btnTechMenu = new TextButton("TechMenu", Hud.getSkin());
        btnBaseInfo = new TextButton("BaseInfo", Hud.getSkin());

        btnBuildMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Clicked Button", "Build");
                Menu.menuState = Menu.MENUSTATES.BUILD;
                Menu.setMenuStateChanged(true);
            }

        });

        btnTechMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Clicked Button", "Build");
                Menu.menuState = Menu.MENUSTATES.TECH;
                Menu.setMenuStateChanged(true);
            }

        });

        btnBaseInfo.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Clicked Button", "B");
                Menu.menuState = Menu.MENUSTATES.BASEINFO;
                Menu.setMenuStateChanged(true);
            }
        });
    }

            public void create(Table table) {
                table.add(btnBuildMenu);
                table.row();
                table.add(btnTechMenu);
                table.row();
                table.add(btnBaseInfo);
            }
        }
