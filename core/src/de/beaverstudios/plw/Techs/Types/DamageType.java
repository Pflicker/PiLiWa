package de.beaverstudios.plw.Techs.Types;

import de.beaverstudios.plw.Player.Game;
import de.beaverstudios.plw.Player.Player;

/**
 * Created by Grass on 3/22/2016.
 */
public enum DamageType {
    ENERGY("Energy","Eng"),
    PHYSICAL("Physical","Phy"),
    ELEMENTAL("Elemental", "Ele");

    private final String typeName;
    private final String typeNameShort;

    DamageType (String typeName,String typeNameShort){
        this.typeName = typeName;
        this.typeNameShort = typeNameShort;
    }

    public static Float calculateDamage(DamageType damageType, ArmorType armorType, Player attackingPlayer){
        float multiplicator = 1;

        Player opponent;
        opponent = Game.opponent(attackingPlayer);


        switch (damageType){
            case ENERGY:
                multiplicator = multiplicator + 0.1f*attackingPlayer.getEnergyDamage();
                switch (armorType) {
                    case ANGSTROM:
                        multiplicator = multiplicator*0.8f;
                        break;
                    case PHYSICAL:
                        multiplicator = multiplicator*1.2f;
                        break;
                }
                break;
            case ELEMENTAL:
                multiplicator = multiplicator + 0.1f*attackingPlayer.getElementalDamage();
                switch (armorType) {
                    case SHIELD:
                        multiplicator = multiplicator*1.2f;
                        break;
                    case PHYSICAL:
                        multiplicator = multiplicator*0.8f;
                        break;
                }
                break;
            case PHYSICAL:
                multiplicator = multiplicator + 0.1f*attackingPlayer.getPhysicalDamage();
                switch (armorType) {
                    case SHIELD:
                        multiplicator = multiplicator*0.8f;
                        break;
                    case ANGSTROM:
                        multiplicator = multiplicator*1.2f;
                        break;
                }
                break;
        }

        switch (armorType){
            case SHIELD:
                multiplicator = multiplicator *(1 - 0.1f*opponent.getShieldArmor());
                break;
            case ANGSTROM:
                multiplicator = multiplicator *(1 - 0.1f*opponent.getAngstromArmor());
                break;
            case PHYSICAL:
                multiplicator = multiplicator *(1 - 0.1f*opponent.getPhysicalArmor());
        }
        //System.out.println("Multiplicator" + multiplicator);
        return multiplicator;
    }

    public void getIndexByName(){

    }

    public String getTypeName() {
        return typeName;
    }

    public String getTypeNameShort() {
        return typeNameShort;
    }
}
