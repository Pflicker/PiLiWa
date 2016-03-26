package de.beaverstudios.plw.Hud.MenuStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


import de.beaverstudios.plw.Buildings.BuildingManager;
import de.beaverstudios.plw.Buildings.BuildingTypes;
import de.beaverstudios.plw.Hud.GameInfo;
import de.beaverstudios.plw.Hud.Hud;
import de.beaverstudios.plw.Hud.Menu;
import de.beaverstudios.plw.Player.Game;
import de.beaverstudios.plw.Units.Unit;
import de.beaverstudios.plw.Units.UnitManager;

/**
 * Created by Grass on 3/5/2016.
 */
public class BuildingInfoMenu {

    private static Skin skin;
    private static TextButton btnBuild;
    private static TextButton btnReturn;

    private static Label lbBuilding;
    private static Label lbBuildingPrice;
    private static Label lbUnitName;

    private static Label lbmaxLife;
    private static Label lbarmor;
    private static Label lbspeed;
    private static Label lbdamage;
    private static Label lbdamageType;
    private static Label lbattackSpeed;
    private static Label special;

    private static Label textBuilding;
    private static Label textBuildingprice;



    private static Label textmaxLife;
    private static Label textarmor;
    private static Label textspeed;
    private static Label textdamage;
    private static Label textdamageType;
    private static Label textattackSpeed;
    private static Label textspecial;

    private String buildingName;
    private String unitName;
    private Unit u;
    private Image img;

    public BuildingInfoMenu(){

        skin = Hud.getSkin();
        u = UnitManager.ghostMarine;

        this.buildingName = Menu.getMenuBuildingType().getBuildingName();
        System.out.println(this.buildingName);
        this.unitName = Menu.getMenuBuildingType().getUnitName();

        btnBuild = new TextButton("Build...", Hud.getSkin());
        btnReturn = new TextButton("Return", Hud.getSkin());
        lbBuilding = new Label(this.buildingName, Hud.getSkin());
        lbBuildingPrice = new Label(String.format("%03d", Menu.getMenuBuildingType().getPrice()),skin);
        lbUnitName = new Label(Menu.getMenuBuildingType().getUnitName(),skin);

        textBuilding = new Label("Building: ",skin);
        textBuildingprice = new Label("Price: ",skin);
        textmaxLife = new Label("Life: ",skin);
        textarmor= new Label("Armor: ",skin);
        textspeed= new Label("Speed: ",skin);
        textdamage= new Label("Damage: ",skin);
        textdamageType= new Label("Damage Type:",skin);
        textattackSpeed= new Label("Attackspeed: ",skin);
        textspecial= new Label("Special Abilities: ",skin);
        img = new Image(u.getSkin());

        lbmaxLife = new Label(String.format("%03d", u.getMaxLife()),skin);
        lbarmor= new Label(String.format("%03d", u.getArmor()),skin);
        lbspeed= new Label(Float.toString(u.getMovementspeed()),skin);
        lbdamage= new Label(String.format("%03d", u.getDamage()),skin);
        //lbdamageType= new Label(String.format("%03d",u.getDamageType()),skin);
        lbattackSpeed= new Label(Float.toString(u.getAttackspeed()),skin);

        btnBuild.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                if (Game.player2.getMoney() >= Menu.getMenuBuildingType().getPrice()) {
                    Gdx.app.log("Clicked Button", "Build...");
                    Menu.setDialogPlacement(true);
                 } else {
                    Gdx.app.log("Warning:", "Not enough Money");
                }

            }
        });

        btnReturn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Menu.setMenuStateChanged(true);
                Menu.menuState = Menu.MENUSTATES.BUILD;
                Gdx.app.log("Button:", "Return");
                Menu.setRet(true);
            }
        });
    }

    public void update(float dt){
        BuildingTypes b;
        b = Menu.getMenuBuildingType();
        u = b.getUnitByIndex(b.getIndex());

        lbBuilding.setText(Menu.getMenuBuildingType().getBuildingName());
        lbBuildingPrice.setText(String.format("%03d", Menu.getMenuBuildingType().getPrice()));
        lbUnitName.setText(u.getName());
        lbmaxLife.setText(String.format("%03d", u.getLife()));
        lbspeed.setText(Float.toString(u.getMovementspeed()));
        lbdamage.setText(String.format("%03d", u.getDamage()));
        lbattackSpeed.setText(Float.toString(u.getAttackspeed()));
        lbarmor.setText(String.format("%03d", u.getArmor()));

    }

    public void create(Table table) {
        table.setSkin(skin);
        table.clear();
        table.row();
        table.add(btnReturn).left();
        table.row();
        table.add(lbBuilding);
        table.row();
        table.add(textBuildingprice).expandX().left();
        table.add(lbBuildingPrice).expandX();
        table.row();
        table.add(lbUnitName).center();
        table.row();
        table.add(textmaxLife).expandX().left();
        table.add(lbmaxLife).expandX();
        table.row();
        table.add(textspeed).expandX().left();
        table.add(lbspeed).expandX();
        table.row();
        table.add(textdamage).expandX().left();
        table.add(lbdamage).expandX();
        table.row();
        table.add(textdamageType).expandX().left();
        table.add(lbdamageType).expandX();
        table.row();
        table.add(textattackSpeed).expandX().left();
        table.add(lbattackSpeed).expandX();
        table.row();
        table.add(textarmor).expandX().left();
        table.add(lbarmor).expandX();
        table.row();
        table.add(img).center();
        table.row();
        table.add(btnBuild).center();
    }
}
