package de.beaverstudios.plw.Techs.Types;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

import de.beaverstudios.plw.Units.Unit;

/**
 * Created by root on 24.03.16.
 */
public enum ArmorType {
    PHYSICAL("Physical","Phy"),
    SHIELD("Shield","Shi"),
    ANGSTROM("Angstrom","Ang");

    private final String typeName;
    private final String typeNameShort;

    ArmorType(String typeName, String typeNameShort){
        this.typeName = typeName;
        this.typeNameShort = typeNameShort;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getTypeNameShort() {
        return typeNameShort;
    }
}
