package de.beaverstudios.plw;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.beaverstudios.plw.Screens.LoadingScreen;
import de.beaverstudios.plw.Screens.MainMenuScreen;


public class PlwGame extends Game {


	public static final int V_WIDTH = 800;
	public static final int V_HEIGHT = 480;
	public static final int GRID_RES = 10;
	public static final int MELE_RANGE = 15;
	public static final int DET_RANGE = 80;
	public static final int MOVEMENTSPEED = 20;
	public static TextureManager textureManager;

	public void create() {
		textureManager = new TextureManager();
        setScreen(new MainMenuScreen(this));
	}

	public void render() {
		super.render();
	}

	public void dispose() {
	}

}