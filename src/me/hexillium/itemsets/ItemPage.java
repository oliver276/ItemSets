package me.hexillium.itemsets;

import java.util.ArrayList;

public class ItemPage {

    private String filename;

    private String name;
    private ArrayList<ItemBlock> itemBlocks;
    private String maps;
    private Champion champ;
    private boolean needsSaving;
    private boolean needsDeleting;

    public ItemPage(String name,String filename, Champion champ){
        this.champ = champ;
        this.name = name;
        maps = "any";
        itemBlocks = new ArrayList<>();
        this.filename = filename;
    }

    public void swap(int block1, int block2) {

        ItemBlock temp = itemBlocks.get(block1);
        itemBlocks.set(block1, itemBlocks.get(block2));
        itemBlocks.set(block2, temp);
    }

    public ItemPage(String name,String filename, Champion champ, boolean isNew){
        this.champ = champ;
        this.name = name;
        maps = "any";
        itemBlocks = new ArrayList<>();
        this.filename = filename;
        needsSaving = isNew;
    }

    public void setNeedsSaving(boolean needsSaving){
        this.needsSaving = needsSaving;
    }

    public boolean getNeedsSaving(){
        return needsSaving;
    }

    public Champion getChamp() {
        return champ;
    }

    public void addItemBlock(ItemBlock itemBlock){
        itemBlocks.add(itemBlock);
    }

    public String getName() {
        return name;
    }

    public ArrayList<ItemBlock> getItemBlocks() {
        return itemBlocks;
    }

    public void setNeedsDeleting(boolean needsDeleting){
        this.needsDeleting = needsDeleting;
    }

    public boolean getNeedsDeleting(){
        return this.needsDeleting;
    }

    public String getMaps() {
        return maps;
    }

    public void removeBlock(ItemBlock block){
        itemBlocks.remove(block);
    }

    public String serialise(){
        StringBuilder strbld = new StringBuilder();
        strbld.append("{");
        strbld.append("\"title\": \"").append(name).append("\",");
        strbld.append("\"type\": \"custom\",");
        strbld.append("\"map\": \"").append(maps).append("\",");
        strbld.append("\"mode\": \"any\",");
        strbld.append("\"blocks\": [");
        for (ItemBlock b : itemBlocks){
            strbld.append(b.serialise()).append(",");
        }
        strbld.deleteCharAt(strbld.length() - 1);
        strbld.append("]}");
        return strbld.toString();
    }

    public String getFilename() {
        return filename;
    }
}
