package de.beaverstudios.plw.Hud;

import com.badlogic.gdx.scenes.scene2d.ui.Window;

import de.beaverstudios.plw.PlwGame;

/**
 * Created by Grass on 3/25/2016.
 */
public class Notice{

    public static boolean noticeVisible;
    private static String notice;
    public static Window noticeWindow;
    private static float visibleTimer;

    public Notice(){

        notice = "Not enough Money";
        noticeWindow = new Window("Notice",Hud.getSkin());
        noticeWindow.center();
        noticeWindow.padTop(20);
        noticeWindow.setVisible(false);
        noticeWindow.add(notice);
        noticeWindow.setX(PlwGame.V_WIDTH / 2 - 30);
        noticeWindow.setY(PlwGame.V_HEIGHT / 4);

    }

    public void update(float dt){
        if(noticeVisible){
            visibleTimer += dt;
            if (visibleTimer >= 5){
                noticeVisible = false;
                visibleTimer = 0;
            }
        }
    }

}
