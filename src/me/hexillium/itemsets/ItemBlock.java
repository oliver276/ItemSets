package me.hexillium.itemsets;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ItemBlock {

    private String name;
    private ArrayList<ItemCount> items;
    private ItemPage parent;

    public String getName() {
        return name;
    }

    public ArrayList<ItemCount> getItems() {
        return items;
    }

    public ItemBlock(String name, ItemPage parentPage){
        this.name = name;
        items = new ArrayList<>();
        parent = parentPage;
    }

    public void removeItem(Item item){
        items.remove(item);
    }

    public ItemPage getParentPage(){
        return parent;
    }

    public void addItem(Item item,int count){
        items.add(new ItemCount(item,count,this));
    }

    public void swap(int item1, int item2){

        ItemCount temp = items.get(item1);
        items.set(item1,items.get(item2));
        items.set(item2,temp);


        /*if (item1 > item2){
            int temp = item2;
            item2 = item1;
            item1 = temp;
        }

        Map<Item, Integer> newMap = new HashMap<>();
        ArrayList<Map.Entry<Item, Integer>> tempPositions = new ArrayList<>();
        for (ItemCount i : items){
            tempPositions.add(new AbstractMap.SimpleEntry<Item, Integer>(i,items.get(i)));
        }
        for (int it = 0; it < tempPositions.size(); it++ ){
            if (it == item1){
                newMap.put(tempPositions.get(item2).getKey(),tempPositions.get(item2).getValue());
            } else if (it == item2){
                newMap.put(tempPositions.get(item1).getKey(),tempPositions.get(item1).getValue());
            } else {
                newMap.put(tempPositions.get(it).getKey(),tempPositions.get(it).getValue());
            }
        }
        items = newMap;*/
    }

    public boolean containsItem(Item item){
        return items.contains(item);
    }

    public String serialise(){
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");
        jsonBuilder.append("\"type\": \"").append(name).append("\",");
        jsonBuilder.append("\"items\": [");
        for (ItemCount i : items){
            try {
                String internalItemBuilder = "{" +
                        "\"id\": \"" + i.getItem().getID() + "\"," +
                        "\"count\": " + i.getCount() + "},";
                jsonBuilder.append(internalItemBuilder);
            } catch (Exception ignored) {
                //ignored.printStackTrace();
            }
        }
        jsonBuilder.deleteCharAt(jsonBuilder.length() - 1);
        jsonBuilder.append("]}");
        return jsonBuilder.toString();
    }

}
