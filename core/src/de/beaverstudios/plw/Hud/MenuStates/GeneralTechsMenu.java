package de.beaverstudios.plw.Hud.MenuStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;

import de.beaverstudios.plw.Buildings.BuildingManager;
import de.beaverstudios.plw.Buildings.BuildingTypes;
import de.beaverstudios.plw.Hud.Hud;
import de.beaverstudios.plw.Hud.Menu;
import de.beaverstudios.plw.Player.Game;
import de.beaverstudios.plw.Screens.GameScreen;
import de.beaverstudios.plw.Techs.*;
import de.beaverstudios.plw.Techs.GeneralTechs;

/**
 * Created by Grass on 3/22/2016.
 */
public class GeneralTechsMenu {

    private TextButton btnReturn;

    private ArrayList<Button> btnTechs;
    private ArrayList<Label> lbTechs;
    private ArrayList<GeneralTechs> generalTechs;
    private GeneralTechs generalTech;

    private Skin skin;

    public GeneralTechsMenu() {

        skin = Hud.getSkin();

        btnTechs = new ArrayList<Button>();
        lbTechs = new ArrayList<Label>();
        generalTechs = new ArrayList<GeneralTechs>();

        btnReturn = new TextButton("Return", Hud.getSkin());

        btnReturn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Menu.setMenuStateChanged(true);
                Menu.menuState = Menu.MENUSTATES.TECH;
                Gdx.app.log("Button:", "Return");
            }
        });
    }

    public void update(Table table){
        btnTechs.clear();
        lbTechs.clear();
        generalTechs.clear();

        generalTechs = GameScreen.techs.checkGeneralTechsAvailability();
        for (GeneralTechs g : generalTechs){
            btnTechs.add(new Button(g.getImage().getDrawable()));
            lbTechs.add(new Label(g.getTechName(), skin));
        }
        for (int i = 0; generalTechs.size() > i; i++ ) {
            generateClickListener(i);
        }
        create(table);
    }

    public void generateClickListener(final int i){
        btnTechs.get(i).setSkin(skin);
        btnTechs.get(i).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                generalTech = generalTechs.get(i);
                Gdx.app.log("Clicked Button", lbTechs.get(i).getName());
                if (Game.player2.getMoney() >= generalTech.getPrice()) {
                    Game.player2.addGeneralTech(generalTech);
                    Game.player2.addMoney(-generalTech.getPrice());
                    Hud.menu.generalTechsMenu.update(Menu.table);
                } else {
                    Gdx.app.log("Clicked Button", "Insufficient Funds");
                }
            }
        });
    }

    public void create(Table table) {
        int i;
        table.clear();
        table.row();
        table.add(btnReturn).left();
        table.row();
        table.row();
        for(i = 0;btnTechs.size() > i + 1;i += 2){
            table.add(btnTechs.get(i)).left().expandX().padLeft(10).width(50).height(50);
            table.add(btnTechs.get(i+1)).right().expandX().padRight(10).width(50).height(50);
            table.row();
            table.add(lbTechs.get(i)).left().expandX().padLeft(10);
            table.add(lbTechs.get(i+1)).right().expandX().padRight(10);
            table.row();
        }

        if(btnTechs.size() > i){
            table.add(btnTechs.get(i)).left().expandX().width(50).height(50).padLeft(10);
            table.row();
            table.add(lbTechs.get(i)).left().expandX().padLeft(10);
        }
        table.row();
    }
}


