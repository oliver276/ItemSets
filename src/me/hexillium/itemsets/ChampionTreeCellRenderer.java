package me.hexillium.itemsets;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;

public class ChampionTreeCellRenderer implements TreeCellRenderer{

    private JLabel label;

    ChampionTreeCellRenderer(){
        label = new JLabel();
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        Object o = ((DefaultMutableTreeNode) value).getUserObject();
        if (selected){
            label.setForeground(Color.BLUE);
        } else {
            label.setForeground(null);
        }
        label.setIcon(null);
        if (o instanceof ItemCount) {
            try {
                ItemCount itemc = (ItemCount) o;
                Item item = itemc.getItem();
                label.setIcon(item.getIcon());
                label.setText(itemc.getCount() + " x " + item.getName());
            } catch (Exception ex) {
                ex.printStackTrace();
                label.setIcon(null);
                label.setText("" + value);
            }
        } else if (o instanceof Champion) {
            Champion c = (Champion) o;
            label.setText(c.getFriendlyName());
            label.setIcon(null);
        } else if (o instanceof ItemPage) {
            ItemPage itemPage = (ItemPage) o;
            label.setText(itemPage.getName());
            label.setIcon(null);
        } else if (o instanceof ItemBlock){
            ItemBlock itemBlock = (ItemBlock) o;
            label.setText(itemBlock.getName());
            label.setIcon(null);
        } else {
            label.setIcon(null);
            label.setText("" + value);
        }
        return label;
    }
}
