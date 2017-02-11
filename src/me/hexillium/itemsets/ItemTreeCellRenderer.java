package me.hexillium.itemsets;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;

public class ItemTreeCellRenderer implements TreeCellRenderer{

    private JLabel label;

    ItemTreeCellRenderer(){
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

        if (o instanceof Item){
            Item item = (Item) o;
            label.setIcon(item.getIcon());
            label.setText(item.getName());

        } else {
            label.setIcon(null);
            label.setText("" + value);
        }
        return label;
    }
}
