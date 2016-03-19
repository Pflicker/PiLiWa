package de.beaverstudios.plw.Buildings;

/**
 * Created by Grass on 3/3/2016.
 */
public enum BuildingTypes{
    BARRACKS("Barracks","Marine",20,2),
    BUILDING2("Building2","Unit2",20,2);

    private final String buildingName;
    private final String unitName;
    private final Integer price;
    private final Integer incomeRaise;

    private BuildingTypes(String buildingName, String unitName, Integer price, Integer incomeRaise){
        this.buildingName = buildingName;
        this.unitName = unitName;
        this.price = price;
        this.incomeRaise =incomeRaise;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public String getUnitName() {
        return unitName;
    }

    public Integer getPrice(){
        return price;
    }

    public Integer getIncomeRaise(){
        return incomeRaise;
    }
}

