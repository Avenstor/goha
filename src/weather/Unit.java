package weather;

public enum Unit {
    METRIC("metric"),
    IMPERIAL("imperial");

    private final String unitValue;

    Unit(String unitValue){
        this.unitValue = unitValue;
    }

    public String getUnitValue() {
        return unitValue;
    }
}