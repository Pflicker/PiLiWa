package de.beaverstudios.plw.Hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import de.beaverstudios.plw.Buildings.BuildingManager;
import de.beaverstudios.plw.Hud.MenuStates.BaseInfo;
import de.beaverstudios.plw.Hud.MenuStates.GeneralTechsMenu;
import de.beaverstudios.plw.Hud.MenuStates.SpecificTechsMenu;
import de.beaverstudios.plw.Hud.MenuStates.TechMenu;
import de.beaverstudios.plw.PlwGame;
import de.beaverstudios.plw.Techs.SpecificTechs;

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

    private static String name;
    private static boolean dialogPlacement;
    private static boolean ret;
    public enum MENUSTATES{
        GAME,BUILD,BUILDINFO,TECH,GENERALTECH,SPECIFICTECH,BASEINFO
    }
    public static MENUSTATES menuState;

    public Menu() {

        table = new Table();
        table.setBounds(PlwGame.V_WIDTH * 0.8f, 0, PlwGame.V_WIDTH * 0.2f, PlwGame.V_HEIGHT);
        table.setBackground(Hud.getSkin().getDrawable("default-scroll"));
        table.top();

        menuStateChanged = true;
        menuState = MENUSTATES.GAME;
        dialogPlacement = false;

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
        if (BuildingManager.isBuildingTypeChanged()) {
            buildingInfoMenu.update(dt);
            BuildingManager.setBuildingTypeChanged(false);
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
}



