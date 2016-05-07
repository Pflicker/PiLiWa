package de.beaverstudios.plw.Hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

import de.beaverstudios.plw.Buildings.BuildingTypes;
import de.beaverstudios.plw.Player.Game;
import de.beaverstudios.plw.Player.Player;
import de.beaverstudios.plw.PlwGame;
import de.beaverstudios.plw.Screens.GameScreen;
import de.beaverstudios.plw.Techs.GeneralTechs;
import de.beaverstudios.plw.Techs.Types.ArmorType;
import de.beaverstudios.plw.TextureManager;
import de.beaverstudios.plw.Units.Healthbar.HealthBar;
import de.beaverstudios.plw.Units.Healthbar.ShieldBar;
import de.beaverstudios.plw.Units.Unit;

/**
 * Created by Grass on 3/2/2016.
 */
public class Hud {

    //General Vars
    public Stage hudStage;
    public Viewport hudPort;
    private OrthographicCamera hudCam;
    private Skin skin;
    //private TechTree techTree;
    //private static boolean help;

    //GameInfo Vars
    private Label lbGameTime;
    private Label lbPlayer1Income;
    private Label lbPlayer1Money;
    private Label lbPlayer2Income;
    private Label lbPlayer2Money;
    private TextButton btnHelp;
    public Table infoTable;

    // Menu
    private WidgetGroup menu;
    private Table menuTable;
    private BuildingTypes menuBuildingType;
    private Integer menuBuildingSlot;

    // Notice
    private String notice;
    public Table noticeWindow;
    private float visibleTimer;

    //Build Menu
    private ArrayList<Button> btnBuildings;
    private ArrayList<Label> lbBuildings;
    private ArrayList<BuildingTypes> buildingTypes;
    private DragAndDrop dnd;

    //UnitInfo
    private Unit unit;
    private Table tableOverlay;
    private Table unitInfoTable;
    private HealthBar healthBar;
    private ShieldBar shieldBar;
    private WidgetGroup unitInfoGroup;
    private Table advancedTable;
    private Button btnAdvance;
    private Label lbUIShield;
    private Label lbUILife;
    private Label lbUIDamage;
    private Label lbUIAttackSpeed;
    private Label lbUIArmor;
    private Label lbUIArmoryType;
    private Label lbUIDamageType;
    private Label lbUIMovementspeed;
    private Label lbUIRange;
    private Label lbUISpecial;

    //BuildingDialog
    private Table buildintDialogTable;
    private Array<Table> slotTable = new Array<Table>(true, 9);

    //TechDialog
    private Table techDialogTable;
    private ArrayList<Button> btnTechs;
    private ArrayList<Label> lbTechs;
    private ArrayList<GeneralTechs> generalTechs;
    private GeneralTechs generalTech;
    private Label lbPhysical;
    private Label lbElemental;
    private Label lbEnergy;
    private Label lbDamage;
    private Label lbArmor;

    //Bottom Bar
    private Table bottomTable;
    private Table buttonTable;
    private WidgetGroup bottomGroup;

    private TextButton btnBase;
    private TextButton btnTech;
    private TextButton btnState;
    private TextButton btnSpeed;

    //Building Info
    private Table buildingInfoTable;
    private Label lbBuilding;
    private Label lbBuildingPrice;
    private Label lbUnitName;
    private Label lbmaxLife;
    private Label lbarmor;
    private Label lbspeed;
    private Label lbdamage;
    private Label lbdamageType;
    private Label lbattackSpeed;
    private Label special;
    private BuildingTypes buildingType;
    private Unit u;
    private TextButton btnBuild;

    //BuildingOverview

    private Table unitOverview;
    private Array<Table> buildingOverviewSlotTable = new Array<Table>(true, 9);
    private WidgetGroup uo;

    //UpgradeBuildingMenu
    private ArrayList<Button> btnUpgrades;
    private ArrayList<Label> lbUpgrades;
    private ArrayList<BuildingTypes> upgrades;
    private BuildingTypes building;

    public Hud() {

        skin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        skin.getFont("default-font").getData().setScale(0.8f);
        hudCam = new OrthographicCamera();
        hudPort = new FitViewport(PlwGame.V_WIDTH, PlwGame.V_HEIGHT, hudCam);
        hudStage = new Stage(hudPort, GameScreen.getBatch());

        //<editor-fold desc="GameInfo">
        //GameInfo

        lbPlayer1Income = new Label("Player 1 Income: " + Game.player1.getIncome(),skin);
        lbPlayer1Money = new Label("Player 1 Money: " + Game.player1.getMoney(),skin);
        lbPlayer2Income = new Label("Player 2 Income: " + Game.player2.getIncome(),skin);
        lbPlayer2Money = new Label("Player 2 Money: " + Game.player2.getMoney(),skin);
        lbGameTime = new Label("Time: " + Game.getGameTimeInt(),skin);

        btnHelp = new TextButton("Help",skin);
        btnHelp.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //setHelp(true);
                System.out.println("Help clicked");
            }
        });

        infoTable = new Table(skin);
        infoTable.setBounds(0, 0, Gdx.graphics.getWidth() * 0.8f, Gdx.graphics.getHeight());
        infoTable.top();
        infoTable.add(btnHelp);
        infoTable.add(lbGameTime);
        infoTable.row();
        infoTable.add(lbPlayer1Income).expandX();
        infoTable.add(lbPlayer2Income).expandX();
        infoTable.row();
        infoTable.add(lbPlayer1Money).expandX();
        infoTable.add(lbPlayer2Money).expandX();
        //</editor-fold>

        //<editor-fold desc="Menu">
        //Menu

        menuTable = new Table(skin);
        menuTable.setBounds(PlwGame.V_WIDTH * 0.8f, PlwGame.V_HEIGHT*0.2f, PlwGame.V_WIDTH * 0.2f, PlwGame.V_HEIGHT*0.8f);
        menuTable.setBackground(TextureManager.IMGHUDBACK.getDrawable());
        menuTable.top();
        menuTable.setTouchable(Touchable.enabled);
        menuTable.setVisible(false);
        menuBuildingType=BuildingTypes.BARRACKS;
        menuBuildingSlot = 0;
        //</editor-fold>

        //<editor-fold desc="Building Info">
        //Building Info

        buildingInfoTable = new Table(skin);
        buildingInfoTable.setBounds(PlwGame.V_WIDTH*0.6f,PlwGame.V_HEIGHT*0.2f,PlwGame.V_WIDTH*0.2f,PlwGame.V_HEIGHT*0.6f);
        buildingInfoTable.setBackground(TextureManager.IMGBARRACKS.getDrawable());
        buildingInfoTable.setVisible(false);
        buildingInfoTable.columnDefaults(0);
        buildingInfoTable.columnDefaults(1);
        buildingInfoTable.columnDefaults(2);
        buildingInfoTable.columnDefaults(3);
        //buildingInfoTable.debug();

        btnBuild = new TextButton("Build",skin);
        btnBuild.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                GameScreen.bm.buyBuilding(buildingType,menuBuildingSlot, Game.player2);
                updateBuildingOverview();
                updateBuildingDialog();
                building = buildingType;
                createUpgradeMenu(menuTable);
                buildingInfoTable.setVisible(false);
            }
        });

        this.buildingType = getMenuBuildingType();
        u = this.buildingType.getUnitByIndex(this.buildingType.getIndex());

        lbBuilding = new Label(" " + buildingType.getBuildingName(), skin);
        lbBuildingPrice = new Label("Price: " + buildingType.getPrice(),skin);

        lbUnitName = new Label(u.getName(),skin);
        lbmaxLife = new Label("Life: " + u.getMaxLife(),skin);
        lbarmor= new Label(" " + u.getArmor(),skin);
        lbspeed= new Label(" " + u.getMovementspeed(),skin);
        lbdamage= new Label(" " +  u.getDamage(),skin);
        lbdamageType= new Label(" " + u.getDamageType().getTypeName(),skin);
        lbattackSpeed= new Label("AS: " + u.getAttackspeed(),skin);

        Integer height = (int)buildingInfoTable.getHeight()/10;
        buildingInfoTable.row().height(height).colspan(3);
        buildingInfoTable.add(lbBuilding);
        buildingInfoTable.row().height(height).colspan(3);
        buildingInfoTable.add(lbBuildingPrice);
        buildingInfoTable.row().height(height);
        buildingInfoTable.add(lbUnitName).colspan(3);
        buildingInfoTable.row().height(height);
        buildingInfoTable.add(TextureManager.IMGHEALTH).height(20).width(20);
        buildingInfoTable.add(lbmaxLife);
        buildingInfoTable.row().height(height);
        buildingInfoTable.add(TextureManager.IMGSHIELD).height(20).width(20);
        buildingInfoTable.add(lbarmor);
        buildingInfoTable.row().height(height);
        buildingInfoTable.add(TextureManager.IMGSPEED).height(20).width(20);
        buildingInfoTable.add(lbspeed);
        buildingInfoTable.row().height(height);
        buildingInfoTable.add(TextureManager.IMGDAMAGE).height(20).width(20);
        buildingInfoTable.add(lbdamage);
        buildingInfoTable.row().height(height);
        buildingInfoTable.add(lbdamageType);
        buildingInfoTable.row().height(height);
        buildingInfoTable.add(lbattackSpeed);
        buildingInfoTable.row().height(height).colspan(4);
        buildingInfoTable.add(btnBuild);
        //</editor-fold>

        //<editor-fold desc="Notice">
        // Notice

        notice = "Not enough Money";
        noticeWindow = new Table();
        noticeWindow.setSkin(skin);
        noticeWindow.center();
        noticeWindow.padTop(20);
        noticeWindow.setVisible(false);
        noticeWindow.add(notice);
        noticeWindow.setX(PlwGame.V_WIDTH / 2 - 30);
        noticeWindow.setY(PlwGame.V_HEIGHT / 4);
        //</editor-fold>

        //<editor-fold desc="Build Menu">
        // Build Menu

        btnBuildings = new ArrayList<Button>();
        lbBuildings = new ArrayList<Label>();
        buildingTypes = new ArrayList<BuildingTypes>();
        dnd = new DragAndDrop();

        for (BuildingTypes b : BuildingTypes.values()){
            if(b.getReq() == null) {
                buildingTypes.add(b);
                btnBuildings.add(new Button(b.getImage().getDrawable()));
                lbBuildings.add(new Label(b.getBuildingName(), skin));
            }
        }

        for (int i = 0;btnBuildings.size() > i; i++){
            final int finalI = i;
            btnBuildings.get(i).setSkin(skin);
            btnBuildings.get(i).addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y){
                    //System.out.println("Clicked Button" +  lbBuildings.get(finalI).getText());
                    buildingInfoTable.setVisible(true);
                    setBuildingType(buildingTypes.get(finalI));
                    updateBuildingDialog();
                }
            });
            dnd.addSource(new DragAndDrop.Source(btnBuildings.get(finalI)) {
                final DragAndDrop.Payload payload = new DragAndDrop.Payload();
                @Override
                public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
                    setBuildingType(buildingTypes.get(finalI));
                    payload.setObject(buildingTypes.get(finalI));
                    payload.setDragActor(new Image(btnBuildings.get(finalI).getBackground()));
                    return payload;
                }
            });
        }


        //</editor-fold>

        //<editor-fold desc="UnitInfo">
        //Unit Info

        unitInfoTable= new Table(skin);
        unitInfoTable.setBounds(PlwGame.V_WIDTH * 0.6f, 0, PlwGame.V_WIDTH * 0.2f, PlwGame.V_HEIGHT * 0.2f);
        //table.debug();

        tableOverlay = new Table(skin);
        tableOverlay.setBounds(PlwGame.V_WIDTH * 0.6f, 0, PlwGame.V_WIDTH * 0.2f, PlwGame.V_HEIGHT * 0.2f);

        unit = Game.player2.getUnits().get(0);

        lbUILife = new Label("Life: " + unit.getLife() + " / " + unit.getMaxLife(),skin);
        lbUILife.setSize(unitInfoTable.getWidth()*0.5f, unitInfoTable.getHeight()*0.05f);
        healthBar = new HealthBar(unitInfoTable.getX(),unitInfoTable.getY(),unitInfoTable.getWidth(),unitInfoTable.getHeight(),unit.getLife(),unit.getMaxLife());

        if (unit.getArmorType() == ArmorType.SHIELD){
            lbUIShield= new Label("Shield: " + unit.getShieldValue() + " / " + unit.getMaxShieldValue(),skin);
            shieldBar = new ShieldBar(unitInfoTable.getX(),unitInfoTable.getY(),unitInfoTable.getWidth(),unitInfoTable.getHeight(),unit.getShieldValue(),unit.getMaxShieldValue());
            tableOverlay.add(lbUIShield).center().top().setActorHeight(unitInfoTable.getHeight()*0.2f);
            tableOverlay.row();
            unitInfoTable.add(shieldBar);
        }

        tableOverlay.add(lbUILife);
        unitInfoTable.add(healthBar);

        btnAdvance = new Button(TextureManager.IMGBTNRESUME.getDrawable());
        btnAdvance.setPosition(unitInfoTable.getX(),unitInfoTable.getY());
        btnAdvance.setSize(30,30);

        btnAdvance.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Expand CLicked");
                if (advancedTable.isVisible()){
                    advancedTable.setVisible(false);
                } else {
                    advancedTable.setVisible(true);
                }
            }
        });

        advancedTable = new Table(skin);
        //advancedTable.debug();
        advancedTable.setBounds(PlwGame.V_WIDTH * 0.4f, 0, PlwGame.V_WIDTH * 0.2f, PlwGame.V_HEIGHT * 0.2f);
        advancedTable.setVisible(false);
        advancedTable.columnDefaults(0);
        advancedTable.columnDefaults(1);
        advancedTable.columnDefaults(2);
        advancedTable.columnDefaults(3);
        advancedTable.defaults();

        lbUIDamage = new Label(" " + unit.getDamage(),skin);
        lbUIAttackSpeed = new Label("AS:" + unit.getAttackspeed(),skin);
        lbUIArmor = new Label(" " + unit.getArmor(),skin);
        lbUIArmoryType = new Label(" " + unit.getArmorType().getTypeName(),skin);
        lbUIDamageType = new Label(" " + unit.getDamageType().getTypeName(),skin);
        lbUIMovementspeed = new Label(" " + unit.getMovementspeed(),skin);
        lbUIRange = new Label("R:" + unit.getRange(),skin);
        lbUISpecial = new Label("Special: ",skin);

        advancedTable.row().height(advancedTable.getHeight()/5);
        advancedTable.add(TextureManager.IMGDAMAGE).height(20).width(20);
        advancedTable.add(lbUIDamage);
        advancedTable.add(TextureManager.IMGARMOR).height(20).width(20);
        advancedTable.add(lbUIArmor);

        advancedTable.row().height(advancedTable.getHeight()/5);
        advancedTable.add();
        advancedTable.add(lbUIDamageType);
        advancedTable.add();
        advancedTable.add(lbUIArmoryType);

        advancedTable.row().height(advancedTable.getHeight()/5);
        advancedTable.add(TextureManager.IMGBUILDING4).height(20).width(20);
        advancedTable.add(lbUIAttackSpeed);
        advancedTable.add(TextureManager.IMGSPEED).height(20).width(20);
        advancedTable.add(lbUIMovementspeed);

        advancedTable.row().height(advancedTable.getHeight()/5);
        advancedTable.add(TextureManager.IMGBTNPAUSE).height(20).width(20);
        advancedTable.add(lbUIRange);
        advancedTable.add(TextureManager.IMGBTNRESUME).height(20).width(20);
        advancedTable.add();

        unitInfoGroup = new WidgetGroup();
        unitInfoGroup.addActor(unitInfoTable);
        unitInfoGroup.addActor(advancedTable);
        unitInfoGroup.addActor(tableOverlay);
        unitInfoGroup.addActor(btnAdvance);
        //</editor-fold>

        //<editor-fold desc="Building Overview">
        //Building Overview

        unitOverview = new Table(skin);
        unitOverview.setBounds(PlwGame.V_WIDTH*0.845f,0,PlwGame.V_HEIGHT*0.2f,PlwGame.V_HEIGHT*0.2f);
        unitOverview.columnDefaults(0);
        unitOverview.columnDefaults(1);
        unitOverview.columnDefaults(2);
        unitOverview.debug();

        for (int i=0; i < 9;i++) {
            buildingOverviewSlotTable.add(new Table(skin));
            buildingOverviewSlotTable.get(i).columnDefaults(0);
            buildingOverviewSlotTable.get(i).row().height(unitOverview.getHeight()/6);
            buildingOverviewSlotTable.get(i).add("Empty").center().width(unitOverview.getWidth()/3).expandY();
        }

        unitOverview.row().height(unitOverview.getHeight()/3-3);
        unitOverview.add(buildingOverviewSlotTable.get(0)).padTop(3);
        unitOverview.add(buildingOverviewSlotTable.get(3)).padTop(3);
        unitOverview.add(buildingOverviewSlotTable.get(6)).padTop(3);

        unitOverview.row().height(unitOverview.getHeight()/3-3);
        unitOverview.add(buildingOverviewSlotTable.get(1));
        unitOverview.add(buildingOverviewSlotTable.get(4));
        unitOverview.add(buildingOverviewSlotTable.get(7));

        unitOverview.row().height(unitOverview.getHeight()/3-3);
        unitOverview.add(buildingOverviewSlotTable.get(2)).padBottom(3);
        unitOverview.add(buildingOverviewSlotTable.get(5)).padBottom(3);
        unitOverview.add(buildingOverviewSlotTable.get(8)).padBottom(3);
        //</editor-fold>

        //<editor-fold desc="Building Dialog">
        // Building Dialog

        buildintDialogTable = new Table(skin);
        buildintDialogTable.setVisible(false);
        buildintDialogTable.setColor(Color.GREEN);
        buildintDialogTable.setBackground(skin.getDrawable("default-scroll"));
        buildintDialogTable.setBounds(PlwGame.V_WIDTH * 0.2f, PlwGame.V_HEIGHT* 0.3f, PlwGame.V_WIDTH *0.4f, PlwGame.V_WIDTH *0.4f);
        buildintDialogTable.setTouchable(Touchable.enabled);

        buildintDialogTable.columnDefaults(0);
        buildintDialogTable.columnDefaults(1);
        buildintDialogTable.columnDefaults(2);
        buildintDialogTable.debug();

        for (int i=0; i <9;i++) {
            final int finalI = i;
            slotTable.add(new Table(skin));
            slotTable.get(i).columnDefaults(0);
            slotTable.get(i).setTouchable(Touchable.enabled);
            slotTable.get(i).row().height(buildintDialogTable.getHeight()/3);
            slotTable.get(i).add("Empty").center().width(buildintDialogTable.getWidth()/3).expandY();
            slotTable.get(i).addListener(new ClickListener() {
                                             @Override
                                             public void clicked(InputEvent event, float x, float y) {
                                                 slotTable.get(menuBuildingSlot).setBackground(TextureManager.IMGBTNPAUSE.getDrawable());
                                                 setMenuBuildingSlot(finalI);
                                                 System.out.println("Dialog Table" + finalI);
                                                 slotTable.get(finalI).setBackground(TextureManager.IMGBTNRESUME.getDrawable());
                                                 menuTable.setVisible(true);
                                                 createBuildMenu(menuTable);
                                             }

                                             @Override
                                             public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                                                 if(dnd.isDragging()) {
                                                     slotTable.get(menuBuildingSlot).setBackground(TextureManager.IMGBTNPAUSE.getDrawable());
                                                     setMenuBuildingSlot(finalI);
                                                     slotTable.get(finalI).setBackground(TextureManager.IMGBTNRESUME.getDrawable());

                                                 }
                                             }

                                             @Override
                                             public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                                                 if(dnd.isDragging()) {
                                                    slotTable.get(menuBuildingSlot).setBackground(TextureManager.IMGBTNPAUSE.getDrawable());
                                                }
                                             }
                                         }
            );

            dnd.addTarget(new DragAndDrop.Target(slotTable.get(i)) {
                @Override
                public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                    System.out.println("Dragged");
                    return true;
                }

                @Override
                public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                    System.out.println("Dropped");
                    setMenuBuildingSlot(finalI);
                    GameScreen.bm.buyBuilding(buildingType,menuBuildingSlot, Game.player2);
                    createUpgradeMenu(menuTable);
                    updateBuildingDialog();
                    updateBuildingOverview();
                }
            });
        }

        buildintDialogTable.row().height(buildintDialogTable.getHeight()/3);
        buildintDialogTable.add(slotTable.get(0)).expandX().expandY();
        buildintDialogTable.add(slotTable.get(3)).expandX().expandY();
        buildintDialogTable.add(slotTable.get(6)).expandX().expandY();

        buildintDialogTable.row().height(buildintDialogTable.getHeight()/3);
        buildintDialogTable.add(slotTable.get(1)).expandX().expandY();
        buildintDialogTable.add(slotTable.get(4)).expandX().expandY();
        buildintDialogTable.add(slotTable.get(7)).expandX().expandY();

        buildintDialogTable.row().height(buildintDialogTable.getHeight()/3);
        buildintDialogTable.add(slotTable.get(2)).expandX().expandY();
        buildintDialogTable.add(slotTable.get(5)).expandX().expandY();
        buildintDialogTable.add(slotTable.get(8)).expandX().expandY();
        //</editor-fold>

        //<editor-fold desc="Tech Dialog">
        //TechDialog

        techDialogTable = new Table(skin);
        techDialogTable.setBackground(TextureManager.IMGHUDBACK.getDrawable());
        techDialogTable.setBounds(PlwGame.V_WIDTH * 0.2f, PlwGame.V_HEIGHT * 0.3f, PlwGame.V_WIDTH * 0.4f, PlwGame.V_WIDTH * 0.4f);
        techDialogTable.columnDefaults(0);
        techDialogTable.columnDefaults(1);
        techDialogTable.columnDefaults(2);
        techDialogTable.columnDefaults(3);
        techDialogTable.setVisible(false);
        techDialogTable.debug();

        lbPhysical = new Label("Physical",skin);
        lbElemental = new Label("Elemental",skin);
        lbEnergy = new Label("Energy",skin);
        lbDamage = new Label("Damage",skin);
        lbArmor = new Label("Armor",skin);

        techDialogTable.row().height(techDialogTable.getHeight()*0.2f).width(PlwGame.V_WIDTH*0.1f);
        techDialogTable.add();
        techDialogTable.add(lbPhysical).center();
        techDialogTable.add(lbEnergy).center();
        techDialogTable.add(lbElemental).center();

        btnTechs = new ArrayList<Button>();
        lbTechs = new ArrayList<Label>();
        generalTechs = new ArrayList<GeneralTechs>();

        for (GeneralTechs g : GeneralTechs.values()){
            if(g.getRequirements() == null){
                generalTechs.add(g);
                btnTechs.add(new Button(g.getImage().getDrawable()));
                lbTechs.add(new Label(g.getTechName(), skin));
            }
        }
        for (int i = 0; generalTechs.size() > i; i++ ) {
            generateClickListener(i);
        }

        techDialogTable.row();
        techDialogTable.add(lbDamage);
        techDialogTable.add(btnTechs.get(0)).height(50).width(50).expandX();
        techDialogTable.add(btnTechs.get(1)).height(50).width(50).expandX();
        techDialogTable.add(btnTechs.get(2)).height(50).width(50).expandX();
        techDialogTable.row();
        techDialogTable.add();
        techDialogTable.add(lbTechs.get(0)).expandX();
        techDialogTable.add(lbTechs.get(1)).expandX();
        techDialogTable.add(lbTechs.get(2)).expandX();
        techDialogTable.row().height(PlwGame.V_HEIGHT*0.05f);
        techDialogTable.row();
        techDialogTable.add(lbArmor);
        techDialogTable.add(btnTechs.get(3)).height(50).width(50).expandX();
        techDialogTable.add();
        techDialogTable.add();
        techDialogTable.row();
        techDialogTable.add();
        techDialogTable.add(lbTechs.get(3)).expandX();
        //</editor-fold>

        //<editor-fold desc="Bottom Bar">
        //Bottom Bar

        btnBase = new TextButton("Base",skin);
        btnTech = new TextButton("Techs", skin);
        btnState = new TextButton("Pause", skin);
        btnSpeed = new TextButton("Double", skin);

        btnBase.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(buildintDialogTable.isVisible()){
                    buildintDialogTable.setVisible(false);
                    menu.setVisible(false);
                   } else {
                    buildintDialogTable.setVisible(true);
                    menu.setVisible(true);
                    menuTable.setVisible(true);
                    createBuildMenu(menuTable);
                    slotTable.get(menuBuildingSlot).setBackground(TextureManager.IMGBTNPAUSE.getDrawable());
                    menuBuildingSlot = 0;
                    slotTable.get(menuBuildingSlot).setBackground(TextureManager.IMGBTNRESUME.getDrawable());
                    techDialogTable.setVisible(false);
                }
            }
        });

        btnTech.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(techDialogTable.isVisible()){
                    buildintDialogTable.setVisible(false);
                    menu.setVisible(false);
                    techDialogTable.setVisible(false);
                } else {
                    techDialogTable.setVisible(true);
                    menu.setVisible(true);
                    buildintDialogTable.setVisible(false);
                }
            }
        });

        btnState.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                switch (GameScreen.state) {
                    case RUN: {
                        GameScreen.state = GameScreen.STATE.PAUSE;
                        btnState.setText("Resume");
                        Gdx.app.log("Button:", "Pause");
                        break;
                    }
                    case PAUSE: {
                        GameScreen.state = GameScreen.STATE.RUN;
                        btnState.setText("Pause");
                        Gdx.app.log("Button:", "Run");
                        break;
                    }
                }
            }
        });

        btnSpeed.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                switch (GameScreen.speed) {
                    case 0: {
                        GameScreen.speed = 1;
                        btnSpeed.setText("Normal Speed");
                        Gdx.app.log("Button:", "Double Speed");
                        break;
                    }
                    case 1: {
                        GameScreen.speed = 0;
                        btnSpeed.setText("Double Speed");
                        Gdx.app.log("Button:", "Normal Speed");
                        break;
                    }
                }
            }
        });

        buttonTable = new Table(skin);
        buttonTable.setBounds(0,0,PlwGame.V_WIDTH*0.4f,PlwGame.V_HEIGHT*0.2f);
        buttonTable.row().height(50).pad(0,50,0,50);
        buttonTable.add(btnState).expandX();
        buttonTable.add(btnSpeed).expandX();
        buttonTable.add(btnBase).expandX();
        buttonTable.add(btnTech).expandX();

        bottomTable = new Table(skin);
        bottomTable.setBounds(0,0,PlwGame.V_WIDTH,PlwGame.V_HEIGHT*0.2f);
        bottomTable.setBackground(TextureManager.IMGBOTTOMBARHUD.getDrawable());
        //</editor-fold>

        //<editor-fold desc="Upgrade Building Menu">
        //UpgradeBuildingMenu

        btnUpgrades = new ArrayList<Button>();
        lbUpgrades = new ArrayList<Label>();
        upgrades = new ArrayList<BuildingTypes>();

        getUpgrades();
        //</editor-fold>

        //---Widget Groups---

        menu = new WidgetGroup();
        menu.addActor(menuTable);
        menu.addActor(buildingInfoTable);

        bottomGroup = new WidgetGroup();
        bottomGroup.addActor(bottomTable);
        bottomGroup.addActor(unitInfoGroup);
        bottomGroup.addActor(buttonTable);


        //----------------------HudStage------------------

        hudStage.addActor(infoTable);
        hudStage.addActor(bottomGroup);
        hudStage.addActor(noticeWindow);
        hudStage.addActor(buildintDialogTable); //muss allein stehen
        hudStage.addActor(techDialogTable);
        hudStage.addActor(menu);
        hudStage.addActor(unitOverview);
        //hudStage.addActor(techTree.treeTable);
    }

    public void update(float dt) {

        updateGameInfo(dt);
        /*
        if (help){
            //techTree.treeTable.setVisible(true);
            help = false;
            GameScreen.state = GameScreen.STATE.PAUSE;
        }*/

        //<editor-fold desc="Notice update">
        //Notice
        if(noticeWindow.isVisible()){
            visibleTimer += dt;
            System.out.println(visibleTimer);
            if (visibleTimer >= 5){
                noticeWindow.setVisible(false);
                visibleTimer = 0;
            }
        }
        //</editor-fold>

        //<editor-fold desc="Unit Info Update">
        // Unit Info
        if(unit != null && unit.getLife() < 1){
            clearInfo();
        }
        if(unit != null) {
            unitInfoGroup.setVisible(true);
            if (unit.getArmorType() == ArmorType.SHIELD) {
                lbUIShield.setText("Shield: " + unit.getShieldValue() + " / " + unit.getMaxShieldValue());
            }
            lbUILife.setText("Life: " + unit.getLife() + " / " + unit.getMaxLife());
            lbUIDamage.setText(" " + unit.getDamage());
            lbUIAttackSpeed.setText("AS:" + unit.getAttackspeed());
            lbUIArmor.setText(" " + unit.getArmor());
            lbUIArmoryType.setText(" " + unit.getArmorType().getTypeName());
            lbUIDamageType.setText(" " + unit.getDamageType().getTypeName());
            lbUIMovementspeed.setText(" " + unit.getMovementspeed());
            lbUIRange.setText("R:" + unit.getRange());
            lbUISpecial.setText("Special: ");
        }
        //</editor-fold>

        hudStage.act(dt);
    }

    public void updateGameInfo(float dt){
        //GameInfo
        lbGameTime.setText("Time: " + Game.getGameTimeInt());
        lbPlayer1Income.setText("Player 1 Income: " + Game.player1.getIncome());
        lbPlayer1Money.setText("Player 1 Money: " + Game.player1.getMoney());
        lbPlayer2Income.setText("Player 2 Income: " + Game.player2.getIncome());
        lbPlayer2Money.setText("Player 2 Money: " + Game.player2.getMoney());
    }

    public void updateBuildingDialog(){
        //Building Dialog

        for(int i = 0;Game.player2.getBuildings().size() > i; i++){
            slotTable.get(Game.player2.getBuildings().get(i).getSlot()).clearChildren();
            slotTable.get(Game.player2.getBuildings().get(i).getSlot()).add(Game.player2.getBuildings().get(i).getUnitName()).width(buildintDialogTable.getWidth()/3).expandY();
            final int finalI = i;
            slotTable.get(Game.player2.getBuildings().get(i).getSlot()).addListener(new ClickListener() {
                                                                                        @Override
                                                                                        public void clicked(InputEvent event, float x, float y) {
                                                                                            building = Game.player2.getBuildings().get(finalI).getThisType();
                                                                                            menuTable.setVisible(true);
                                                                                            createUpgradeMenu(menuTable);
                                                                                        }
                                                                                    }
            );slotTable.get(Game.player2.getBuildings().get(i).getSlot()).row().height(buildintDialogTable.getHeight()/6);;
        }

        //System.out.println(buildingType);
        u = buildingType.getUnitByIndex(buildingType.getIndex());

        lbBuilding.setText(" " + buildingType.getBuildingName());
        lbBuildingPrice.setText("Price: " + buildingType.getPrice());
        lbUnitName.setText(u.getName());
        lbmaxLife.setText("Life: " + u.getMaxLife());
        lbarmor.setText(" " + u.getArmor());
        lbspeed.setText(" " + u.getMovementspeed());
        lbdamage.setText(" " +  u.getDamage());
        lbdamageType.setText(" " + u.getDamageType().getTypeName());
        lbattackSpeed.setText("AS: " + u.getAttackspeed());
    }

    public void updateBuildingOverview(){
        for (int i=0; i <9;i++) {
            buildingOverviewSlotTable.get(i).clear();
            if (Game.player2.hasSlot(i) != null) {
                buildingOverviewSlotTable.get(i).setBackground(Game.player2.getBuildings().get(Game.player2.hasSlot(i)).getBuildingImage().getDrawable());
            } else {
                buildingOverviewSlotTable.get(i).add("Empty").expandX().expandY();
            }
        }
    }

    public void createUpgradeMenu(Table table) {
        getUpgrades();
        int i;
        table.clear();
        table.row();
        table.add("Slot: " + menuBuildingSlot);
        table.row();
        table.add("Available Upgrades:");
        table.row();
        for(i = 0;btnUpgrades.size() > i + 1;i += 2){
            table.add(btnUpgrades.get(i)).left().expandX().padLeft(10).width(50).height(50);
            table.add(btnUpgrades.get(i+1)).right().expandX().padRight(10).width(50).height(50);
            table.row();
            table.add(lbUpgrades.get(i)).left().expandX().padLeft(10);
            table.add(lbUpgrades.get(i+1)).right().expandX().padRight(10);
            table.row();
        }
        if(btnUpgrades.size() > i){
            table.add(btnUpgrades.get(i)).left().expandX().width(50).height(50).padLeft(10);
            table.row();
            table.add(lbUpgrades.get(i)).left().expandX().padLeft(10);
        }

    }

    public void getUpgrades(){
        btnUpgrades.clear();
        lbUpgrades.clear();
        upgrades.clear();

        for(BuildingTypes b : BuildingTypes.values()){
            if(b.getReq() == building){
                upgrades.add(b);
            }
        }
        System.out.println("Upgrades" + upgrades);

        for (BuildingTypes b : upgrades){
            btnUpgrades.add(new Button(b.getImage().getDrawable()));
            lbUpgrades.add(new Label(b.getBuildingName(), skin));
        }

        for (int i = 0;btnUpgrades.size() > i; i++){
            final int finalI = i;
            btnUpgrades.get(i).setSkin(skin);
            btnUpgrades.get(i).addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y){
                }
            });
        }
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
                    if(generalTech.getDamageType() != null){
                        switch (generalTech.getDamageType()) {
                            case PHYSICAL:
                                Game.player2.addPhysicalDamage();
                                break;
                            case ELEMENTAL:
                                Game.player2.addElementalDamage();
                                break;
                            case ENERGY:
                                Game.player2.addEnergyDamage();
                                break;
                        }
                    }
                    if(generalTech.getArmorType() != null){
                        switch (generalTech.getArmorType()){
                            case PHYSICAL:
                                Game.player2.addPhysicalArmor();
                                break;
                            case SHIELD:
                                Game.player2.addShieldArmor();
                                break;
                            case ANGSTROM:
                                Game.player2.addAngstromArmor();
                                break;
                        }
                    }
                    Game.player2.addMoney(-generalTech.getPrice());
                } else {
                    Gdx.app.log("Clicked Button", "Insufficient Funds");
                }
            }
        });
    }

    public void createBuildMenu(Table table) {
        int i;
        table.clear();
        table.row();
        table.add("Slot: " + menuBuildingSlot);
        table.row();
        table.add("Slot is Empty!");
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

    public void setUnit(Unit unit) {
        lbUIShield.setText("");
        lbUILife.setText("");
        this.unit = unit;
    }

    public void clearInfo() {
        this.unit = null;
        lbUIShield.setText("");
        lbUILife.setText("");
        unitInfoGroup.setVisible(false);
    }

    public void draw(SpriteBatch batch) {
        if(unit != null && unit.getLife() < 1){
            clearInfo();
        }
        if (unit != null) {
            if (unit.getArmorType() == ArmorType.SHIELD) {
                shieldBar.draw(batch, 0, unitInfoTable.getX(), unitInfoTable.getY() + unitInfoTable.getHeight() * 0.8f, unitInfoTable.getWidth(), unitInfoTable.getHeight() * 0.2f, unit.getShieldValue(), unit.getMaxShieldValue());
            }
            healthBar.draw(batch, 0, unitInfoTable.getX(), unitInfoTable.getY() + unitInfoTable.getHeight() * 0.6f,unitInfoTable.getWidth(), unitInfoTable.getHeight() * 0.2f, unit.getLife(), unit.getMaxLife());
        }
    }

    public void setBuildingType(BuildingTypes buildingType) {
        this.buildingType = buildingType;
    }

    public BuildingTypes getMenuBuildingType() {
        return menuBuildingType;
    }

    public void setMenuBuildingType(BuildingTypes menuBuildingType) {
        this.menuBuildingType = menuBuildingType;
    }

    public Integer getMenuBuildingSlot() {
        return menuBuildingSlot;
    }

    public void setMenuBuildingSlot(Integer menuBuildingSlot) {
        this.menuBuildingSlot = menuBuildingSlot;
    }


    public void dispose() {

    }
    /*
    public boolean isHelp() {
        return help;
    }

    public static void setHelp(boolean help) {
        Hud.help = help;
    }
       */
}