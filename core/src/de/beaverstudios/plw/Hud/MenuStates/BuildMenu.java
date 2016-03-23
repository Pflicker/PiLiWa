package de.beaverstudios.plw.Hud.MenuStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Iterator;

import de.beaverstudios.plw.Buildings.BuildingManager;
import de.beaverstudios.plw.Buildings.BuildingTypes;
import de.beaverstudios.plw.Hud.Hud;
import de.beaverstudios.plw.Hud.Menu;
import de.beaverstudios.plw.Screens.GameScreen;
import de.beaverstudios.plw.TextureManager;

/**
 * Created by Grass on 3/5/2016.
 */
public class BuildMenu {

    private TextButton btnReturn;

    private ArrayList<Button> btnBuildings;
    private ArrayList<Label> lbBuildings;

    private Skin skin;


    public BuildMenu() {

        skin = Hud.getSkin();

        btnReturn = new TextButton("Return", skin);

        btnReturn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Menu.setMenuStateChanged(true);
                Menu.menuState = Menu.MENUSTATES.GAME;
                Gdx.app.log("Button:", "Return");
            }

        });


        btnBuildings = new ArrayList<Button>();
        lbBuildings = new ArrayList<Label>();

        for (BuildingTypes b : BuildingTypes.values()){
            btnBuildings.add(new Button(b.getImage().getDrawable()));
            lbBuildings.add(new Label(b.getBuildingName(), skin));
        }

        for (int i = 0;btnBuildings.size() > i; i++){
            generateClickListener(i);
        }
    }

    public void generateClickListener(final int i){
        btnBuildings.get(i).setSkin(skin);
        btnBuildings.get(i).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.log("Clicked Button", lbBuildings.get(i).getName());
                BuildingManager.newBuildingType = BuildingTypes.getNameByIndex(i);
                BuildingManager.buildingTypeChanged = true;
                Menu.setMenuStateChanged(true);
                Menu.menuState = Menu.MENUSTATES.BUILDINFO;
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
        for(i = 0;btnBuildings.size() > i + 1;i += 2){
            table.add(btnBuildings.get(i)).left().expandX().padLeft(10).width(50).height(50);
            table.add(btnBuildings.get(i+1)).right().expandX().padRight(10).width(50).height(50);
            table.row();
            table.add(lbBuildings.get(i)).left().expandX().padLeft(10);
            table.add(lbBuildings.get(i+1)).right().expandX().padRight(10);
            table.row();
            }

        if(btnBuildings.size() > i){
            table.add(btnBuildings.get(i)).left().expandX().width(50).height(50).padLeft(10);
            table.row();
            table.add(lbBuildings.get(i)).left().expandX().padLeft(10);
        }
        table.row();

    }
}
