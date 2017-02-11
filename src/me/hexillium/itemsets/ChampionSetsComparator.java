package me.hexillium.itemsets;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

public class ChampionSetsComparator implements Comparator<Champion> {
    @Override
    public int compare(Champion o1, Champion o2) {
        Map<Champion,ArrayList<ItemPage>> map = Main.allUnsortedItemsSets;
        if (map.get(o1).size() == 0 && map.get(o2).size() != 0){
            return 1;
        } else if (map.get(o1).size() != 0 && map.get(o2).size() == 0){
            return -1;
        }
        return o1.getFriendlyName().compareTo(o2.getFriendlyName());
    }
}
