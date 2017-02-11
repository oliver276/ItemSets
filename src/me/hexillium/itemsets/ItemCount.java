package me.hexillium.itemsets;

public class ItemCount {

    private Item item;
    private int count;
    private ItemBlock parent;

    public Item getItem() {
        return item;
    }

    public int getCount() {
        return count;
    }

    public ItemBlock getParent() { return parent; }

    public ItemCount(Item item, int count,ItemBlock parent) {
        this.parent = parent;
        this.item = item;
        this.count = count;
    }
}
