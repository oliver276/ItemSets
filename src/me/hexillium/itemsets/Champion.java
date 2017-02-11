package me.hexillium.itemsets;


import java.io.File;

public class Champion implements Comparable{

    private String friendlyName;
    private String referenceName;
    private File champDirectory;
    private String title;
    private int ID;
    public Champion(String friendlyName, String referenceName, String title, int ID){
        this.friendlyName = friendlyName;
        this.referenceName = referenceName;
        this.ID = ID;
        this.title = title;
        champDirectory = new File(Main.installDirectory + "/Config/Champions/" + referenceName + "/Recommended/");

    }

    public String getTitle(){
        return title;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public File getChampDirectory() {
        return champDirectory;
    }

    public int getID() {
        return ID;
    }

    @Override
    public int compareTo(Object o) {
        return this.getFriendlyName().toLowerCase().compareTo(((Champion) o).getFriendlyName().toLowerCase());
    }
}
