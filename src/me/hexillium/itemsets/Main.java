package me.hexillium.itemsets;

import com.google.gson.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static ArrayList<Item> allItems;
    public static ArrayList<Champion> allChamps;
    public static Map<Champion,ArrayList<ItemPage>> allItemsSets;
    public static Map<Champion,ArrayList<ItemPage>> allUnsortedItemsSets;

    static String installDirectory = "C:/Riot Games/League of Legends/";// = "C:/Riot Games/League of Legends/";

    public static ArrayList<String> tags;

    public static void init(){
        tags = new ArrayList<>();
        allItemsSets = new TreeMap<>(new ChampionSetsComparator());
        allUnsortedItemsSets = new HashMap<>();
    }

    public static Item[] getItems() throws Exception{
        File file;
        //file = new File("C:/Users/olive/Desktop/resources/items.json");
        //BufferedReader reader = new BufferedReader(new FileReader(file));
        InputStream in = Main.class.getResourceAsStream("/resources/items.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = reader.readLine();

        //items
        JsonObject items = parse(line).getAsJsonObject("data");

        ArrayList<Item> itemList = new ArrayList<>();


        for (Map.Entry<String,JsonElement> element : items.entrySet()) {
            int id = Integer.valueOf(element.getKey());
            JsonObject jso = element.getValue().getAsJsonObject();
            String name = jso.get("name").getAsString();
            String description = jso.get("description").getAsString();
            ArrayList<Integer> builtFrom = new ArrayList<>();
            try {
                for (JsonElement elementEntry : jso.get("from").getAsJsonArray()) {
                    builtFrom.add(elementEntry.getAsInt());
                }
            } catch (Exception ignored){}
            ArrayList<Integer> builtInto = new ArrayList<>();
            try {
                for (JsonElement elementEntry : jso.get("into").getAsJsonArray()) {
                    builtInto.add(elementEntry.getAsInt());
                }
            } catch (Exception ignored){}

            ArrayList<Integer> maps = new ArrayList<>();
            for (Map.Entry<String, JsonElement> map : jso.get("maps").getAsJsonObject().entrySet()){
                if (map.getValue().getAsString().equalsIgnoreCase("true")){
                    maps.add(Integer.valueOf(map.getKey()));
                }
            }

            ArrayList<Stat> stats = new ArrayList<>();
            for (Map.Entry<String, JsonElement> map : jso.get("stats").getAsJsonObject().entrySet()){
                stats.add(new Stat(map.getKey(),map.getValue().getAsDouble()));
            }

            ArrayList<ItemStat> tags = new ArrayList<>();
            ArrayList<String> stringTags = new ArrayList<>();
            for (JsonElement elementEntry : jso.get("tags").getAsJsonArray()){
                stringTags.add(elementEntry.getAsString());
                tags.add(ItemStat.valueOf(elementEntry.getAsString()));
                if (!Main.tags.contains(elementEntry.getAsString())) Main.tags.add(elementEntry.getAsString());
            }
            ItemStat[] itemStats = tags.toArray(new ItemStat[0]);

            JsonObject goldValues = jso.get("gold").getAsJsonObject();
            int base = goldValues.get("base").getAsInt();
            int total = goldValues.get("total").getAsInt();
            int sell = goldValues.get("sell").getAsInt();

            Item newItem = new Item(id,name,description,builtFrom.toArray(new Integer[0]),builtInto.toArray(new Integer[0]),
                    maps.toArray(new Integer[0]),stats.toArray(new Stat[0]),stringTags.toArray(new String[0]),base,total,sell,itemStats);

            itemList.add(newItem);

            URL imgURL = Main.class.getResource("/resources/items/" + id + " (32x32).jpg");
            ImageIcon image = new ImageIcon(imgURL);

            newItem.setIcon(image);


            System.out.println("Processing item " + element.getKey() + ", " + name);
        }
        Collections.sort(itemList);
        allItems = itemList;
        return itemList.toArray(new Item[0]);

    }

    static ItemPage[] getPagesForChampion(Champion champion){

        ArrayList<ItemPage> itemPages = new ArrayList<>();

        if (champion.getChampDirectory() == null) return null;

        if (!champion.getChampDirectory().isDirectory()) return null;
        try {
            boolean b = champion.getChampDirectory().listFiles().length > 0;
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
        System.out.print("Processing champion: " + champion.getFriendlyName());
        //noinspection ConstantConditions
        for (File itemSet : champion.getChampDirectory().listFiles()){
            //Gson gson = new Gson();
            System.out.print(", set: " + itemSet.getName());
            try {
                //BufferedReader reader = new BufferedReader(new FileReader(itemSet));
                JsonObject stuff = parse(new String(Files.readAllBytes(Paths.get(itemSet.getPath()))));

                String pageName = stuff.get("title").getAsString();
                ItemPage page = new ItemPage(pageName,itemSet.getName(),champion);
                for (JsonElement jse : stuff.get("blocks").getAsJsonArray()){
                    JsonObject currentBlock = jse.getAsJsonObject();
                    ItemBlock current = new ItemBlock(currentBlock.get("type").getAsString(),page);
                    for (JsonElement item : currentBlock.get("items").getAsJsonArray()){
                        JsonObject item_o = item.getAsJsonObject();
                        item_o.get("test");
                        //Item item1 = itemByID(Integer.valueOf(item_o.get("id").getAsString()));
                        if (item != null)
                        current.addItem(itemByID(Integer.valueOf(item_o.get("id").getAsString())),item_o.get("count").getAsInt());
                    }
                    page.addItemBlock(current);
                }
                itemPages.add(page);
            } catch (Exception ex){
                ex.printStackTrace();
                System.out.println("Error reading file. Trying next one.");
                continue;
            }
        }
        System.out.println();
        //System.out.println("-=yo=-");
        allUnsortedItemsSets.put(champion,itemPages);
        allItemsSets.put(champion,itemPages);
        return itemPages.toArray(new ItemPage[0]);

    }

    public static void updateItemSetsAllChamps(){
        for (Champion c : allChamps){
            try {
                ItemPage[] item_A = Main.getPagesForChampion(c);

            } catch (Exception ex) {
                System.out.println("No sets found for " + c.getFriendlyName());
                ex.printStackTrace();
            }
        }
        //Collections.sort(allItemsSets,new ChampionSetsComparator());
    }

    public static Item itemByID(int ID){
        for (Item item : allItems){
            if (item.getID() == ID) return item;
        }
        return null;
    }

    public static Item itemByName(String name){
        for (Item item : allItems){
            if (Objects.equals(item.getName(), name)) return item;
        }
        return null;
    }

    public static Champion[] getChampions() throws Exception{
        File file;
        //file = new File("C:/Users/olive/Desktop/resources/names.json");
        //BufferedReader reader = new BufferedReader(new FileReader(file));
        InputStream in = Main.class.getResourceAsStream("/resources/names.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = reader.readLine();

        //items
        JsonObject champs = parse(line).getAsJsonObject("data");

        ArrayList<Champion> champList = new ArrayList<>();


        for (Map.Entry<String,JsonElement> element : champs.entrySet()) {
            //int id = Integer.valueOf(element.getKey());
            JsonObject jso = element.getValue().getAsJsonObject();
            String friendlyName = jso.get("name").getAsString();
            String referenceName = jso.get("key").getAsString();
            String title = jso.get("title").getAsString();
            int ID = jso.get("id").getAsInt();

            champList.add(new Champion(friendlyName,referenceName,title,ID));
            System.out.println("Processing champion " + ID + ": " + referenceName);
        }
        Collections.sort(champList);
        allChamps = champList;
        return champList.toArray(new Champion[0]);
    }



    /*public void init() throws Exception{

        File file;
        //file = new File("C:/Users/olive/Desktop/resources/items.json");
        //BufferedReader reader = new BufferedReader(new FileReader(file));
        InputStream in = getClass().getResourceAsStream("items.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = reader.readLine();

        //items
        JsonObject items = parse(line).getAsJsonObject("data");

        for (Map.Entry<String,JsonElement> element : items.entrySet()) {
            //mainGui[0].tree1.addSelectionPath(new TreePath(element.getKey() + " , " + element.getValue()));
            //System.out.println(element.getKey() + " , " + element.getValue());
        }
        //champs
    } */

    public static JsonObject parse(String jsonLine) {
        JsonElement jelement = new JsonParser().parse(jsonLine);
        return jelement.getAsJsonObject();
    }

    public String parse_Old(String jsonLine) {
        JsonElement jelement = new JsonParser().parse(jsonLine);
        JsonObject jobject = jelement.getAsJsonObject();
        jobject = jobject.getAsJsonObject("data");
        JsonArray jarray = jobject.getAsJsonArray("translations");
        jobject = jarray.get(0).getAsJsonObject();
        String result = jobject.get("translatedText").toString();
        return result;
    }




}
