package de.beaverstudios.plw.Hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.beaverstudios.plw.TextureManager;
import de.beaverstudios.plw.Units.Building;
import de.beaverstudios.plw.Units.BuildingManager;
import de.beaverstudios.plw.Units.Marine;
import de.beaverstudios.plw.Units.UnitManager;

/**
 * Created by Grass on 3/5/2016.
 */
public class DialogPlacement {

    private int gridSize;
    private Rectangle rect;
    public Table table;
    private TextButton btnBuildhere;
    private Menu menu;
    private BuildingManager bm;
    private UnitManager um;



    public DialogPlacement(BuildingManager bm, UnitManager um,Menu menu) {
        this.menu = menu;
        this.um = um;
        this.bm = bm;

        btnBuildhere = new TextButton("Build here", menu.hud.skin);


        this.menu = menu;
        rect = new Rectangle();

        table = new Table();
        rect.set(50, 50, 50, 50);
        Texture texture = TextureManager.MARINE;
        table.setColor(Color.GREEN);
        table.setFillParent(true);
        table.setSkin(menu.hud.skin);
        table.pad(Gdx.graphics.getHeight() * 0.2f, Gdx.graphics.getWidth() * 0.2f, Gdx.graphics.getHeight() * 0.2f, Gdx.graphics.getWidth() * 0.2f);

        for (int i = 0; i < 3; i++) {
            table.add(btnBuildhere).expandX().expandY();
            table.add("2").expandX().expandY();
            table.add(btnBuildhere).expandX().expandY();
            table.row();
        }
        btnBuildhere.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickAction();

            }
        });
    }

        private void clickAction(){
        menu.hud.gameInfo.setMoneyPlayer(GameInfo.getMoneyPlayer() - 10);
        menu.hud.gameInfo.setIncomePlayer(GameInfo.getIncomePlayer() + 2);
        bm.playerBuilding.add(new Building(um, new Marine(1)));
            menu.setDialogPlacement(false);

    }
    public void update(float dt){

    }
}
