package de.beaverstudios.plw.Hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.beaverstudios.plw.Screens.GameScreen;

/**
 * Created by Grass on 3/5/2016.
 */
public class Menu implements InputProcessor {
    public static Table table;
    private static boolean menuStateChanged;
    private static int menuState;
    private static GameMenu gameMenu;
    private static BuildMenu buildMenu;
    private static BuildingInfoMenu buildingInfoMenu;
    private static int unitCode;
    private static String name;
    public static DialogPlacement dialog;
    private static boolean dialogPlacement;
    private static boolean ret;

    public Menu() {
        dialogPlacement = false;
        unitCode = 0;
        table = new Table();
        table.setBounds(Gdx.graphics.getWidth() * 0.8f, 0, Gdx.graphics.getWidth() * 0.2f, Gdx.graphics.getHeight());
        table.setBackground(Hud.getSkin().getDrawable("default-scroll"));
        table.top();

        menuStateChanged = true;
        menuState = 0;
        dialogPlacement = false;

        gameMenu = new GameMenu();
        buildMenu = new BuildMenu();
        buildingInfoMenu = new BuildingInfoMenu();
        dialog = new DialogPlacement();

        gameMenu.create(table);

    }

    public void update(float dt) {

        if (menuStateChanged) {
            table.clear();
            int i = getMenuState();
            Gdx.app.log("Menu", "State Changed");
            switch (i) {
                case 0:
                    Gdx.app.log("Table created", "GameMenu");
                    gameMenu.create(table);
                    break;
                case 1:
                    Gdx.app.log("Table created", "BuildMenu");
                    buildMenu.create(table);
                    break;
                case 2:
                    Gdx.app.log("Table created", "MarineInfo");
                    buildingInfoMenu.create(table);
            }
            setMenuStateChanged(false);
        }
        if (dialogPlacement){
            dialog.table.setVisible(true);
            int i = getUnitCode();
            Gdx.app.log("Table created", "Dialog");
            dialog.update(dt);
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

    public int getMenuState() {
        return menuState;
    }

    public static void setMenuState(int state) {
        menuState = state;
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

    public static int getUnitCode() {
        return unitCode;
    }

    public static void setUnitCode(int code) {
       unitCode = code;
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



