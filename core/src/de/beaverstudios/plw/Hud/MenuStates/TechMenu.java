package de.beaverstudios.plw.Hud.MenuStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.beaverstudios.plw.Buildings.BuildingManager;
import de.beaverstudios.plw.Buildings.BuildingTypes;
import de.beaverstudios.plw.Hud.Hud;
import de.beaverstudios.plw.Hud.Menu;
import de.beaverstudios.plw.Player.Game;

/**
 * Created by Grass on 3/20/2016.
 */
public class TechMenu {

    private TextButton btnReturn;

    private TextButton btnGetGeneralTechs;
    private TextButton btnGetSpecificTechs;

    public TechMenu() {

        btnReturn = new TextButton("Return", Hud.getSkin());

        btnGetGeneralTechs = new TextButton("General Techs", Hud.getSkin());
        btnGetSpecificTechs = new TextButton("Specific Techs", Hud.getSkin());

        btnReturn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Menu.setMenuStateChanged(true);
                Menu.menuState = Menu.MENUSTATES.GAME;
                Gdx.app.log("Button:", "Return");
            }

        });

        btnGetGeneralTechs.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Clicked Button", "General Techs Menu");
                Menu.setMenuStateChanged(true);
                Menu.generalTechsMenu.update(Menu.table);
                Menu.menuState = Menu.MENUSTATES.GENERALTECH;

            }
        });

        btnGetSpecificTechs.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Clicked Button", "Specific Techs");
                Menu.setMenuStateChanged(true);
                Menu.specificTechsMenu.update(Menu.table);
                Menu.menuState = Menu.MENUSTATES.SPECIFICTECH;
            }
        });
    }

    public void create(Table table) {
        table.clear();
        table.row();
        table.add(btnReturn).left();
        table.row();
        table.row();
        table.row();
        table.add(btnGetGeneralTechs);
        table.row();
        table.add(btnGetSpecificTechs);
    }

}

