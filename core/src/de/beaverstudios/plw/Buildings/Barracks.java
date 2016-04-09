package de.beaverstudios.plw.Buildings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.beaverstudios.plw.Buildings.Building;
import de.beaverstudios.plw.Buildings.BuildingManager;
import de.beaverstudios.plw.Hud.Menu;
import de.beaverstudios.plw.Hud.UnitInfo;
import de.beaverstudios.plw.Interfaces.Buyable;
import de.beaverstudios.plw.Player.Game;
import de.beaverstudios.plw.Player.Player;
import de.beaverstudios.plw.Screens.GameScreen;
import de.beaverstudios.plw.Units.Marine;
import de.beaverstudios.plw.Units.Unit;
import de.beaverstudios.plw.Units.UnitManager;

/**
 * Created by Grass on 3/19/2016.
 */
public class Barracks extends Building {

    public Barracks(int slot, Player player) {
        this.slot = slot;
        this.player = player;
        Unit unitPtr = UnitManager.getGhostMarine();
        thisType = BuildingTypes.BARRACKS;
        create();
    }

    public void spawnUnit() {
        player.getUnits().add(new Marine(player, slot));
    }


}
