package me.hexillium.itemsets;

import javax.swing.*;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Locale;

public class Item implements Comparable{

    private String name;
    private String description;
    private Integer[] builtInto;
    private Integer[] builtFrom;
    private Integer[] applicableMaps;
    private Stat[] itemStats;
    private String[] itemTags;

    private Icon icon;

    private int baseCost;
    private int totalCost;
    private int sellValue;
    private int count;

    private ItemStat[] tags;

    private int id;

    public Item(int id, String name, String description, Integer[] builtFrom, Integer[] builtInto,
                Integer[] applicableMaps, Stat[] itemStats, String[] itemTags,
                int baseCost, int totalCost, int sellValue, ItemStat[] tags) {
        this.name = name;
        this.description = description;
        this.builtInto = builtInto;
        this.builtFrom = builtFrom;
        this.applicableMaps = applicableMaps;
        this.itemStats = itemStats;
        this.itemTags = itemTags;
        this.baseCost = baseCost;
        this.totalCost = totalCost;
        this.sellValue = sellValue;
        this.id = id;
        //this.icon = icon;
        this.tags = tags;
    }

    public ItemStat[] getTags() {
        return tags;
    }

    public int getCount(){
        return this.count;
    }

    public void setCount(int count){
        this.count = count;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public int compareTo(Object o){
        Item item = (Item) o;
        Collator ukCollator = Collator.getInstance(Locale.UK);
        ukCollator.setStrength(Collator.PRIMARY);
        int diff = ukCollator.compare(name,item.getName());
        if(diff == 0 ) {
            return this.getID() - item.getID();
        } else {
            return diff;
        }
    }

    public int getID(){
        return this.id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer[] getBuiltInto() {
        return builtInto;
    }

    public Integer[] getBuiltFrom() {
        return builtFrom;
    }

    public Integer[] getApplicableMaps() {
        return applicableMaps;
    }

    public String[] getAllowedMaps(){
        ArrayList<String> AllowedMaps = new ArrayList<>();
        for (Integer i : applicableMaps){
            switch (i){
                case 1:
                    AllowedMaps.add("Summoner's Rift [Original Summer Variant]");
                    break;
                case 2:
                    AllowedMaps.add("Summoner's Rift [Original Autumn Variant]");
                    break;
                case 3:
                    AllowedMaps.add("The Proving Grounds");
                    break;
                case 4:
                    AllowedMaps.add("Twisted Treeling [Original Version]");
                    break;
                case 8:
                    AllowedMaps.add("The Crystal Scar");
                    break;
                case 10:
                    AllowedMaps.add("Twisted Treeline");
                    break;
                case 11:
                    AllowedMaps.add("Summoner's Rift");
                    break;
                case 12:
                    AllowedMaps.add("Howling Abyss");
                    break;
                case 14:
                    AllowedMaps.add("Butcher's Bridge");
            }
        }
        return AllowedMaps.toArray(new String[0]);
    }

    public Stat[] getItemStats() {
        return itemStats;
    }

    public String[] getItemTags() {
        return itemTags;
    }

    public int getBaseCost() {
        return baseCost;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public int getSellValue() {
        return sellValue;
    }
//"image":{"full":"1043.png","sprite":"item0.png","group":"item","x":336,"y":48,"w":48,"h":48}


}
