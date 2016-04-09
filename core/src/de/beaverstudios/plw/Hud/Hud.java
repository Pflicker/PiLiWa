package de.beaverstudios.plw.Hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TooltipManager;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import de.beaverstudios.plw.Hud.Help.TechTree.TechTree;
import de.beaverstudios.plw.PlwGame;
import de.beaverstudios.plw.Screens.GameScreen;

/**
 * Created by Grass on 3/2/2016.
 */
public class Hud implements InputProcessor {

    public static Stage hudStage;
    public Viewport hudPort;
    private OrthographicCamera hudCam;
    private TextureAtlas hudAtlas;
    public static GameInfo gameInfo;
    public static Menu menu;
    private static Skin skin;
    private static TooltipManager tooltipManager;
    private static UnitInfo unitInfo;
    private static Notice notice;
    private static de.beaverstudios.plw.Hud.Help.TechTree.TechTree techTree;
    private static boolean help;


    public Hud() {

        skin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        skin.getFont("default-font").getData().setScale(0.8f);
        hudCam = new OrthographicCamera();
        hudPort = new FitViewport(PlwGame.V_WIDTH, PlwGame.V_HEIGHT, hudCam);
        hudStage = new Stage(hudPort, GameScreen.getBatch());

        tooltipManager = new TooltipManager();

        gameInfo = new GameInfo(skin);
        menu = new Menu();
        unitInfo = new UnitInfo();
        notice = new Notice();
        techTree = new TechTree();

        hudStage.addActor(gameInfo.infoTable);
        hudStage.addActor(menu.table);
        hudStage.addActor(menu.dialog.table); //muss allein stehen
        hudStage.addActor(unitInfo.table);
        hudStage.addActor(Notice.noticeWindow);
        hudStage.addActor(techTree.treeTable);
    }

    public void update(float dt) {

        if (help){
            techTree.treeTable.setVisible(true);
            help = false;
            GameScreen.state = GameScreen.STATE.PAUSE;
        }

        gameInfo.update(dt);
        menu.update(dt);
        notice.update(dt);
        hudStage.act(dt);
        unitInfo.update(dt);
    }

    public void draw(SpriteBatch batch){
        unitInfo.draw(batch);
    }

    public static Skin getSkin() {
        return skin;
    }


    public void dispose() {
    }

    public static boolean isHelp() {
        return help;
    }

    public static void setHelp(boolean help) {
        Hud.help = help;
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
        hudCam.zoom += 0.1*amount;
        return true;
    }

}