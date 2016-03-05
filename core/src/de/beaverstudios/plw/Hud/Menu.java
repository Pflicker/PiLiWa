package de.beaverstudios.plw.Hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.beaverstudios.plw.Units.Building;
import de.beaverstudios.plw.Units.BuildingManager;
import de.beaverstudios.plw.Units.Marine;
import de.beaverstudios.plw.Units.UnitManager;

/**
 * Created by Grass on 3/5/2016.
 */
public class Menu implements InputProcessor {
    public Table table;
    private static boolean menuStateChanged;
    private static int menuState;
    private Rectangle bounds;
    private GameMenu gameMenu;
    private BuildMenu buildMenu;
    private BuildingInfoMenu buildingInfoMenu;
    private String name;
    public Hud hud;

    public Menu(Skin skin, final BuildingManager bm, final UnitManager um, Hud hud) {
        hud = this.hud;
        bounds = new Rectangle();
        bounds.set(Gdx.graphics.getWidth() * 0.8f, 0, Gdx.graphics.getWidth() * 0.2f, Gdx.graphics.getHeight());
        table = new Table();
        table.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        table.top();
        menuStateChanged = false;
        menuState = 0;

        gameMenu = new GameMenu(skin, Menu .this);
        buildMenu = new BuildMenu(skin,bm,um,Menu .this);
        buildingInfoMenu = new BuildingInfoMenu(skin,bm,um,Menu .this);

        gameMenu.create(table);

    }

    public void update() {
        if (menuStateChanged) {
            table.clear();
            int i = getMenuState();
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
        }
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

    public String getName() {
        return name;
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



