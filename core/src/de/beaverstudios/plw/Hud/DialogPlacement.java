package de.beaverstudios.plw.Hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import de.beaverstudios.plw.Buildings.BuildingManager;
import de.beaverstudios.plw.Player.Game;

/**
 * Created by Grass on 3/5/2016.
 */

public class DialogPlacement {

    public Table table;
    private static Array<TextButton> build = new Array<TextButton>(true,9);
    public static Array<Table> slotTable = new Array<Table>(true, 9);
    private static TextButton btnBuildSlot0;
    private static TextButton btnBuildSlot1;
    private static TextButton btnBuildSlot2;
    private static TextButton btnBuildSlot3;
    private static TextButton btnBuildSlot4;
    private static TextButton btnBuildSlot5;
    private static TextButton btnBuildSlot6;
    private static TextButton btnBuildSlot7;
    private static TextButton btnBuildSlot8;

    public DialogPlacement() {

        table = new Table();

        for (int i=0; i <9;i++) {
            slotTable.add(new Table());
            slotTable.get(i).setSkin(Hud.getSkin());
            slotTable.get(i).add("Empty").expandX().expandY();
            slotTable.get(i).row();
        }

        btnBuildSlot0 = new TextButton("Build Slot 0",Hud.getSkin());
        btnBuildSlot1 = new TextButton("Build Slot 1",Hud.getSkin());
        btnBuildSlot2 = new TextButton("Build Slot 2",Hud.getSkin());
        btnBuildSlot3 = new TextButton("Build Slot 3",Hud.getSkin());
        btnBuildSlot4 = new TextButton("Build Slot 4",Hud.getSkin());
        btnBuildSlot5 = new TextButton("Build Slot 5",Hud.getSkin());
        btnBuildSlot6 = new TextButton("Build Slot 6",Hud.getSkin());
        btnBuildSlot7 = new TextButton("Build Slot 7",Hud.getSkin());
        btnBuildSlot8 = new TextButton("Build Slot 8",Hud.getSkin());

        table.setVisible(false);
        table.setColor(Color.GREEN);
        table.setBackground(Hud.getSkin().getDrawable("default-scroll"));
        table.setBounds(Gdx.graphics.getWidth() * 0.2f, Gdx.graphics.getHeight() * 0.2f, Gdx.graphics.getWidth() * 0.6f, Gdx.graphics.getHeight() * 0.6f);
        table.setSkin(Hud.getSkin());

        btnBuildSlot0.addListener(new ClickListener() {
                                      @Override
                                      public void clicked(InputEvent event, float x, float y) {
                                          Gdx.app.log("Dialog Table", "Slot 0");
                                          Menu.setMenuBuildingSlot(0);
                                          buildGeneral();
                                      }
                                  }
        );
        btnBuildSlot1.addListener(new ClickListener() {
                                      @Override
                                      public void clicked(InputEvent event, float x, float y) {
                                          Gdx.app.log("Dialog Table", "Slot 1");
                                          Menu.setMenuBuildingSlot(1);
                                          buildGeneral();
                                      }
                                  }
        );
        btnBuildSlot2.addListener(new ClickListener() {
                                      @Override
                                      public void clicked(InputEvent event, float x, float y) {
                                          Gdx.app.log("Dialog Table", "Slot 2");
                                          Menu.setMenuBuildingSlot(2);
                                          buildGeneral();
                                      }
                                  }
        );

        btnBuildSlot3.addListener(new ClickListener() {
                                      @Override
                                      public void clicked(InputEvent event, float x, float y) {
                                          Gdx.app.log("Dialog Table", "Slot 3");
                                          Menu.setMenuBuildingSlot(3);
                                          buildGeneral();
                                      }
                                  }
        );
        btnBuildSlot4.addListener(new ClickListener() {
                                      @Override
                                      public void clicked(InputEvent event, float x, float y) {
                                          Gdx.app.log("Dialog Table", "Slot 4");
                                          Menu.setMenuBuildingSlot(4);
                                          buildGeneral();
                                      }
                                  }
        );
        btnBuildSlot5.addListener(new ClickListener() {
                                      @Override
                                      public void clicked(InputEvent event, float x, float y) {
                                          Gdx.app.log("Dialog Table", "Slot 5");
                                          Menu.setMenuBuildingSlot(5);
                                          buildGeneral();
                                      }
                                  }
        );
        btnBuildSlot6.addListener(new ClickListener() {
                                      @Override
                                      public void clicked(InputEvent event, float x, float y) {
                                          Gdx.app.log("Dialog Table", "Slot 6");
                                          Menu.setMenuBuildingSlot(6);
                                          buildGeneral();
                                      }
                                  }
        );
        btnBuildSlot7.addListener(new ClickListener() {
                                      @Override
                                      public void clicked(InputEvent event, float x, float y) {
                                          Gdx.app.log("Dialog Table", "Slot 7");
                                          Menu.setMenuBuildingSlot(7);
                                          buildGeneral();
                                      }
                                  }
        );
        btnBuildSlot8.addListener(new ClickListener() {
                                      @Override
                                      public void clicked(InputEvent event, float x, float y) {
                                          Gdx.app.log("Dialog Table", "Slot 8");
                                          Menu.setMenuBuildingSlot(8);
                                          buildGeneral();
                                      }
                                  }
        );

        build.add(btnBuildSlot0);
        build.add(btnBuildSlot1);
        build.add(btnBuildSlot2);
        build.add(btnBuildSlot3);
        build.add(btnBuildSlot4);
        build.add(btnBuildSlot5);
        build.add(btnBuildSlot6);
        build.add(btnBuildSlot7);
        build.add(btnBuildSlot8);

        create();

    }
    public void buildGeneral(){
        slotTable.get(Menu.getMenuBuildingSlot()).clearChildren();
        slotTable.get(Menu.getMenuBuildingSlot()).setSkin(Hud.getSkin());
        slotTable.get(Menu.getMenuBuildingSlot()).add(Menu.getMenuBuildingType().getBuildingName()).expandX().expandY();
        slotTable.get(Menu.getMenuBuildingSlot()).row();
        slotTable.get(Menu.getMenuBuildingSlot()).add(build.get(Menu.getMenuBuildingSlot())).expandX().expandY();
        System.out.println(Menu.getMenuBuildingSlot());
        BuildingManager.buyBuilding(Menu.getMenuBuildingType(),Menu.getMenuBuildingSlot(), Game.player2);
        Menu.setRet(true);
    }

    public void create(){

     for(int i = 0; i < 9;i++) {
            slotTable.get(i).add(build.get(i)).expandX().expandY();
            table.add(slotTable.get(i)).expandX().expandY();
            if (i == 2) table.row();
            if (i == 5) table.row();
        }
    }

}