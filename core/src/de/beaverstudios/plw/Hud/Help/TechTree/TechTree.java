package de.beaverstudios.plw.Hud.Help.TechTree;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.lang.reflect.Field;

import de.beaverstudios.plw.Hud.Hud;
import de.beaverstudios.plw.Screens.GameScreen;
import de.beaverstudios.plw.TextureManager;

/**
 * Created by root on 28.03.16.
 */
public class TechTree {

    public Table treeTable;
    public static Tree tree;
    private static Tree.Node node1;
    private static Tree.Node node5;
    private static Tree.Node node6;
    private static Tree.Node node7;
    private static Tree.Node node2;
    private static Tree.Node node3;
    private static Tree.Node node4;

    Button btn1;
    WidgetGroup layer1;
    private static TextButton btnReturn;

    private static Skin skin;

    public TechTree(){

        skin = new Skin(Gdx.files.internal("skins/uiskin.json"));

        btnReturn = new TextButton("Return",skin);

        btnReturn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameScreen.state = GameScreen.STATE.RUN;
                treeTable.setVisible(false);
                Gdx.app.log("Button:", "Return");
            }

        });

        treeTable = new Table(skin);
        treeTable.setVisible(false);
        treeTable.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        treeTable.setBackground(TextureManager.IMGBTNPAUSE.getDrawable());

        // First create an instance of Tree where the setStyle method is overridden
        tree = new Tree(skin) {
            @Override
            public void setStyle(TreeStyle style) {
                super.setStyle(style);

                // After having called the base class's setStyle,
                // use horrible reflection to find and alter the indentSpacing field.
                try {
                    Field field = Tree.class.getDeclaredField("indentSpacing");
                    field.setAccessible(true);
                    field.set(this, 8); // This is how much you want each plus and minus indented.
                    // Normally this is set to something like
                    //     Math.max(style.plus.getMinWidth(), style.minus.getMinWidth())
                    System.out.println(field);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        };

    // You will likely want to play around with this to make sure the node
    // content is not rendered too close to the plus/minus
        tree.setIconSpacing(4, 4);

    // Add tree nodes here!

        btn1 = new Button(TextureManager.IMGBARRACKS,skin);

        layer1 = new WidgetGroup();
        layer1.addActor(btn1);
        layer1.setColor(Color.RED);

        node1 = new Tree.Node(btn1);
        node2 = new Tree.Node(new TextButton("Node2",skin));
        node5 = new Tree.Node(new TextButton("Node5",skin));
        node6 = new Tree.Node(new TextButton("Node6",skin));
        node7 = new Tree.Node(new TextButton("Node7",skin));
        node3 = new Tree.Node(new TextButton("Node3",skin));
        node4 = new Tree.Node(new TextButton("Node4", skin));

        node2.add(node3);
        node2.add(node4);

        tree.add(node1);
        tree.add(node5);
        tree.add(node6);
        tree.add(node7);

        tree.add(node2);
        tree.setX(300);
        tree.setY(300);

        treeTable.add(btnReturn).left().top();
        treeTable.add(tree);
    }


}
