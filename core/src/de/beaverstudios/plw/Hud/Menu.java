package de.beaverstudios.plw.Hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.badlogic.gdx.math.Interpolation.*;

import de.beaverstudios.plw.Buildings.BuildingTypes;
import de.beaverstudios.plw.Hud.MenuStates.BaseInfo;
import de.beaverstudios.plw.Hud.MenuStates.GeneralTechsMenu;
import de.beaverstudios.plw.Hud.MenuStates.SpecificTechsMenu;
import de.beaverstudios.plw.Hud.MenuStates.TechMenu;
import de.beaverstudios.plw.PlwGame;
import de.beaverstudios.plw.Screens.GameScreen;
import de.beaverstudios.plw.TextureManager;

/**
 * Created by Grass on 3/5/2016.
 */
public class Menu implements InputProcessor {
    public static Table table;
    private static boolean menuStateChanged;
    private static de.beaverstudios.plw.Hud.MenuStates.GameMenu gameMenu;
    private static de.beaverstudios.plw.Hud.MenuStates.BuildMenu buildMenu;
    private static de.beaverstudios.plw.Hud.MenuStates.BuildingInfoMenu buildingInfoMenu;
    private static TechMenu techMenu;
    public static GeneralTechsMenu generalTechsMenu;
    public static SpecificTechsMenu specificTechsMenu;
    private static BaseInfo baseInfo;
    public static DialogPlacement dialog;

    private static BuildingTypes menuBuildingType;
    private static boolean menuBuildingTypeChanged;
    private static Integer menuBuildingSlot;

    private static String name;
    private static boolean dialogPlacement;
    private static boolean ret;
    public enum MENUSTATES{
        GAME,BUILD,BUILDINFO,TECH,GENERALTECH,SPECIFICTECH,BASEINFO
    }
    public static MENUSTATES menuState;
    private static Rectangle btnExpand;
    private static boolean isExpanded;

    public Menu() {

        table = new Table(Hud.getSkin());
        table.setBounds(PlwGame.V_WIDTH * 0.8f, 0, PlwGame.V_WIDTH * 0.2f, PlwGame.V_HEIGHT);
        table.setBackground(TextureManager.IMGHUDBACK.getDrawable());
        table.top();

        menuStateChanged = true;
        menuState = MENUSTATES.GAME;
        dialogPlacement = false;

        menuBuildingType = BuildingTypes.BARRACKS;
        menuBuildingSlot = 0;

        btnExpand = new Rectangle(PlwGame.V_WIDTH*0.8f-20,PlwGame.V_HEIGHT*0.8f-20,50,50);

        gameMenu = new de.beaverstudios.plw.Hud.MenuStates.GameMenu();
        buildMenu = new de.beaverstudios.plw.Hud.MenuStates.BuildMenu();
        buildingInfoMenu = new de.beaverstudios.plw.Hud.MenuStates.BuildingInfoMenu();
        techMenu = new TechMenu();
        generalTechsMenu = new GeneralTechsMenu();
        specificTechsMenu = new SpecificTechsMenu();
        baseInfo = new BaseInfo();
        dialog = new DialogPlacement();

        gameMenu.create(table);

    }

    public void update(float dt) {
        if (isMenuBuildingTypeChanged()) {
            buildingInfoMenu.update(dt);
            setMenuBuildingTypeChanged(false);
        }
            baseInfo.update(dt);




        if (menuStateChanged) {
            table.clear();
            Gdx.app.log("Menu", "State Changed");
            switch (menuState) {
                case GAME:
                    Gdx.app.log("Table created", "GameMenu");
                    gameMenu.create(table);
                    break;
                case BUILD:
                    Gdx.app.log("Table created", "BuildMenu");
                    buildMenu.create(table);
                    break;
                case BUILDINFO:
                    Gdx.app.log("Table created", "BuildingInfo");
                    buildingInfoMenu.create(table);
                    break;
                case TECH:
                    Gdx.app.log("Table created", "TechMenu");
                    techMenu.create(table);
                    break;
                case GENERALTECH:
                    Gdx.app.log("Table created", "GeneralTechs");
                    generalTechsMenu.create(table);
                    break;
                case SPECIFICTECH:
                    Gdx.app.log("Table created", "Specific Techs");
                    specificTechsMenu.create(table);
                    break;
                case BASEINFO:
                    Gdx.app.log("Table created", "Base Info");
                    baseInfo.create(table);
                    break;


            }
            setMenuStateChanged(false);
        }
        if (dialogPlacement){
            dialog.table.setVisible(true);
            Gdx.app.log("Table created", "Dialog");
            dialogPlacement = false;
        }
        if (ret){
            dialog.table.setVisible(false);
            ret = false;
        }

    }

    public static void setExpand(){
        if(isExpanded){
            MoveToAction action = new MoveToAction();
            action.setPosition(table.getX() + (table.getWidth() / 2), table.getY());
            action.setDuration(1f);
            table.addAction(action);
            table.addAction(sequence(fadeIn(1), run(new Runnable() {
                public void run() {
                 //   System.out.println("Action complete");
                    btnExpand.setX(table.getX());
                    btnExpand.setY(btnExpand.getY());
                    isExpanded = false;
                    table.setSize(PlwGame.V_WIDTH*0.2f,PlwGame.V_HEIGHT);
                    //GameScreen.gameCamX = PlwGame.V_WIDTH*0.2f;
                }
            })));
        } else {
            MoveToAction action = new MoveToAction();
            action.setPosition(table.getX() - (table.getWidth()), table.getY());
            action.setDuration(1f);
            table.addAction(action);
            table.addAction(sequence(fadeIn(1), run(new Runnable() {
                public void run() {
                 //   System.out.println("Action complete");
                    btnExpand.setX(table.getX());
                    btnExpand.setY(btnExpand.getY());
                 //   System.out.println("Table X:" + btnExpand.getX() + " Table Y:" + btnExpand.getY());
                    isExpanded = true;
                    table.setSize(PlwGame.V_WIDTH*0.4f,PlwGame.V_HEIGHT);
                    //GameScreen.gamecam.zoom += 0.75;
                    //GameScreen.gameCamX -= Gdx.graphics.getWidth()*0.2f;
                }
            })));
        }
    }

    public boolean isDialogPlacement() {
        return dialogPlacement;
    }

    public static void setDialogPlacement(boolean dialogPlacement) {
        Menu.dialogPlacement = dialogPlacement;
    }

    public static boolean isMenuStateChanged() {
        return menuStateChanged;
    }

    public static void setMenuStateChanged(boolean changed) {
        menuStateChanged = changed;
    }

    public static String getName() {

        return name;
    }

    public static BuildingTypes getMenuBuildingType() {
        return menuBuildingType;
    }

    public static void setMenuBuildingType(BuildingTypes menuBuildingType) {
        Menu.menuBuildingType = menuBuildingType;
    }

    public static boolean isMenuBuildingTypeChanged() {
        return menuBuildingTypeChanged;
    }

    public static void setMenuBuildingTypeChanged(boolean menuBuildingTypeChanged) {
        Menu.menuBuildingTypeChanged = menuBuildingTypeChanged;
    }

    public static Integer getMenuBuildingSlot() {
        return menuBuildingSlot;
    }

    public static void setMenuBuildingSlot(Integer menuBuildingSlot) {
        Menu.menuBuildingSlot = menuBuildingSlot;
    }

    public static boolean isRet() {
        return ret;
    }

    public static void setRet(boolean ret) {
        Menu.ret = ret;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public static Rectangle getBtnExpand() {
        return btnExpand;
    }

    public static void setBtnExpand(Rectangle expandMenu) {
        Menu.btnExpand = expandMenu;
    }
}



