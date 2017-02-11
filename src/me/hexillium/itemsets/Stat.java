package me.hexillium.itemsets;

public class Stat {

    private String attribute;
    private double modifier;

    public String getAttribute() {
        return attribute;
    }

    public double getModifier() {
        return modifier;
    }

    public Stat(String attribute, double modifier) {

        this.attribute = attribute;
        this.modifier = modifier;
    }
}
