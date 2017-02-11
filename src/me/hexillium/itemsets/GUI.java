package me.hexillium.itemsets;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class GUI {
    protected JTree tree1;
    protected JTree tree2;
    protected JPanel panel1;
    private JButton AddItemButton;
    private JTextField SelectedItem;
    private JButton CreateNewItemset;
    private JTextField newItemSetName;
    private JTextField ItemSetItemText;
    private JButton removeButton;
    private JButton saveButton;
    private JButton upButton;
    private JButton downButton;
    private JTextField ItemMovementText;
    private JTextField selectedItemBlockText2;
    private JButton ItemBlockMoveUp;
    private JButton ItemBlockMoveDown;
    private JButton createButton;
    private JTextField NewItemBlockField;
    private JCheckBox bootsCheckBox;
    private JCheckBox laneCheckBox;
    private JCheckBox spellDamageCheckBox;
    private JCheckBox manaRegenCheckBox;
    private JCheckBox manaCheckBox1;
    private JCheckBox jungleCheckBox;
    private JCheckBox criticalStrikeCheckBox;
    private JCheckBox damageCheckBox;
    private JCheckBox attackSpeedCheckBox;
    private JCheckBox consumableCheckBox;
    private JCheckBox activeCheckBox;
    private JCheckBox healthCheckBox1;
    private JCheckBox magicResistanceCheckBox1;
    private JCheckBox onHitCheckBox;
    private JCheckBox stealthCheckBox;
    private JCheckBox nonBootsMovementCheckBox;
    private JCheckBox armourCheckBox1;
    private JCheckBox tenacityCheckBox;
    private JCheckBox lifestealCheckBox;
    private JCheckBox spellVampCheckBox;
    private JCheckBox goldRegenerationCheckBox;
    private JCheckBox auraCheckBox;
    private JCheckBox visionCheckBox;
    private JCheckBox cooldownReductionCheckBox;
    private JCheckBox magicPenetrationCheckBox;
    private JCheckBox healthRegenCheckBox;
    private JCheckBox slowCheckBox;
    private JCheckBox armourPenetrationCheckBox;
    private JCheckBox trinketCheckBox;
    private JCheckBox bilgewaterCheckBox;
    private JTextField selectedItemBlockText;
    private JTextField selectedItemPageText;
    private JTextField selectedChampionText;
    private JTextField selectedItemBlock2;
    private JButton DeleteItemBlockButton;
    private JButton resetAllChangesButton;
    private JTextField selectedItemSet2;
    private JButton deleteItemsetButton;
    private JTextField itemSearchBox;
    //private JButton updateButton;
    private DefaultMutableTreeNode ChampRootNode;
    private DefaultTreeModel champModel;
    private DefaultMutableTreeNode ItemRootNode;
    private DefaultTreeModel itemModel;

    private ItemCount selectedItemLeft;
    private Item selectedItemRight;

    private DefaultMutableTreeNode selectedChampNode;
    private DefaultMutableTreeNode selectedItemBlockNode;
    private DefaultMutableTreeNode selectedItemPageNode;
    private DefaultMutableTreeNode selectedItemLeftNode;

    private ItemPage selectedItemPage;
    private ItemBlock selectedItemBlock;
    private Champion selectedChampion;

    private char mostRecentSearch;
    private boolean wasLastChar;


    private GUI() {
        $$$setupUI$$$();
        tree1.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                if (e.getPath().getPathCount() == 5) {  //itemLeft
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getPathComponent(4);
                    selectedItemLeftNode = node;
                    selectedItemLeft = (ItemCount) node.getUserObject();
                    ItemSetItemText.setText(selectedItemLeft.getItem().getName());
                    ItemMovementText.setText(selectedItemLeft.getItem().getName());
                } else if (e.getPath().getPathCount() == 4) {    //itemblock
                    selectedItemBlockNode = (DefaultMutableTreeNode) e.getPath().getPathComponent(3);
                    selectedItemBlock = ((ItemBlock) selectedItemBlockNode.getUserObject());
                    selectedItemBlockText2.setText(selectedItemBlock.getName());
                    selectedItemBlockText.setText(selectedItemBlock.getName());
                    selectedItemBlock2.setText(selectedItemBlock.getName());
                } else if (e.getPath().getPathCount() == 3) {   //itempage
                    selectedItemPageNode = (DefaultMutableTreeNode) e.getPath().getPathComponent(2);
                    selectedItemPage = (ItemPage) selectedItemPageNode.getUserObject();
                    selectedItemPageText.setText(selectedItemPage.getName());
                    selectedItemSet2.setText(selectedItemPage.getName());
                } else if (e.getPath().getPathCount() == 2) {  //champion
                    selectedChampNode = (DefaultMutableTreeNode) e.getPath().getPathComponent(1);
                    selectedChampion = (Champion) selectedChampNode.getUserObject();
                    selectedChampionText.setText(selectedChampion.getFriendlyName());
                }
            }
        });
        tree2.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                if (e.getPath().getPathCount() == 2 && (((DefaultMutableTreeNode) e.getPath().getPathComponent(1)).getUserObject()) instanceof Item) {

                    selectedItemRight = (Item) ((DefaultMutableTreeNode) e.getPath().getPathComponent(1)).getUserObject();
                    SelectedItem.setText(selectedItemRight.getName());

                }
            }
        });
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateItemList();
            }
        };
        bootsCheckBox.addActionListener(listener);
        spellDamageCheckBox.addActionListener(listener);
        laneCheckBox.addActionListener(listener);
        manaRegenCheckBox.addActionListener(listener);
        manaCheckBox1.addActionListener(listener);
        jungleCheckBox.addActionListener(listener);
        criticalStrikeCheckBox.addActionListener(listener);
        damageCheckBox.addActionListener(listener);
        attackSpeedCheckBox.addActionListener(listener);
        consumableCheckBox.addActionListener(listener);
        onHitCheckBox.addActionListener(listener);
        healthCheckBox1.addActionListener(listener);
        magicResistanceCheckBox1.addActionListener(listener);
        stealthCheckBox.addActionListener(listener);
        activeCheckBox.addActionListener(listener);
        nonBootsMovementCheckBox.addActionListener(listener);
        tenacityCheckBox.addActionListener(listener);
        spellVampCheckBox.addActionListener(listener);
        goldRegenerationCheckBox.addActionListener(listener);
        auraCheckBox.addActionListener(listener);
        healthRegenCheckBox.addActionListener(listener);
        armourCheckBox1.addActionListener(listener);
        lifestealCheckBox.addActionListener(listener);
        visionCheckBox.addActionListener(listener);
        cooldownReductionCheckBox.addActionListener(listener);
        magicPenetrationCheckBox.addActionListener(listener);
        slowCheckBox.addActionListener(listener);
        armourPenetrationCheckBox.addActionListener(listener);
        trinketCheckBox.addActionListener(listener);
        bilgewaterCheckBox.addActionListener(listener);

        AddItemButton.addActionListener(new ActionListener() {  //addItem
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedItemBlock == null || selectedItemRight == null) return;
                selectedItemBlock.addItem(selectedItemRight, 1);
                DefaultMutableTreeNode itemN = new DefaultMutableTreeNode(new ItemCount(selectedItemRight, 1, selectedItemBlock));
                selectedItemBlockNode.add(itemN);
                champModel.insertNodeInto(itemN, selectedItemBlockNode, selectedItemBlockNode.getChildCount() - 1);
                selectedItemBlock.getParentPage().setNeedsSaving(true);
            }
        });
        saveButton.addActionListener(new ActionListener() { // /save
            @Override
            public void actionPerformed(ActionEvent e) {
                Charset charset = StandardCharsets.UTF_8;
                //save the stuffs.
                Map<Champion, ArrayList<ItemPage>> allsets = Main.allItemsSets;
                for (ArrayList<ItemPage> pages : allsets.values()) {
                    for (ItemPage iPs : pages) {
                        if (!iPs.getNeedsSaving()) continue;
                        if (iPs.getNeedsDeleting()) {
                            //usually use path from the code
                            try {
                                Files.delete(Paths.get(iPs.getChamp().getChampDirectory().getPath() + "/" + iPs.getFilename()));
                            } catch (Exception ex) {
                                System.out.println("Error whilst deleting " + iPs.getName());
                            }
                        } else {
                            System.out.println("Saving " + iPs.getName());
                            String file = iPs.serialise();
                            try {
                                BufferedWriter writer = Files.newBufferedWriter(Paths.get(iPs.getChamp().getChampDirectory().getPath() + "/" + iPs.getFilename()), charset);
                                writer.write(file, 0, file.length());
                                writer.flush();
                                writer.close();
                                iPs.setNeedsSaving(false);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        CreateNewItemset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //create itemset for champ
                if (selectedChampion == null || newItemSetName.getText().isEmpty()) return;

                ArrayList<ItemPage> currentPages = Main.allItemsSets.get(selectedChampion);
                ItemPage newItemPage = new ItemPage(newItemSetName.getText(), "HEX_Java_" + newItemSetName.getText() + ".json", selectedChampion);
                currentPages.add(newItemPage);
                Main.allItemsSets.replace(selectedChampion, currentPages);
                DefaultMutableTreeNode itemN = new DefaultMutableTreeNode(newItemPage);
                selectedChampNode.add(itemN);
                champModel.insertNodeInto(itemN, selectedChampNode, selectedChampNode.getChildCount() - 1);
                newItemPage.setNeedsSaving(true);
                //System.out.println(selectedItemBlock.getParentPage().getName());

            }
        });
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //todo: add code here to add item blocks to the itemsets
                if (selectedItemPage == null || NewItemBlockField.getText().isEmpty()) return;
                ItemBlock nBlock = new ItemBlock(NewItemBlockField.getText(), selectedItemPage);
                selectedItemPage.addItemBlock(nBlock);
                DefaultMutableTreeNode newItemBlock = new DefaultMutableTreeNode(nBlock);
                selectedItemPageNode.add(newItemBlock);
                selectedItemPage.setNeedsSaving(true);
                champModel.insertNodeInto(newItemBlock, selectedItemPageNode, selectedItemPageNode.getChildCount() - 1);
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //remove item from itemblock
                if (selectedItemLeft == null) return;
                ItemBlock pBlock = selectedItemLeft.getParent();
                pBlock.removeItem(selectedItemLeft.getItem());
                champModel.removeNodeFromParent(selectedItemLeftNode);
                pBlock.getParentPage().setNeedsSaving(true);
                tree1.clearSelection();
                //todo: deleting;
                //todo: code for removing items --might also have to sync stuff uo
                //todo: add more stuff for deleting and maybe even renaming stuff like sets and blocks.
            }
        });
        DeleteItemBlockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //remove ItemBlock from an ItemSet.
                if (selectedItemBlock == null) return;
                selectedItemBlock.getParentPage().removeBlock(selectedItemBlock);
                champModel.removeNodeFromParent(selectedItemBlockNode);
                selectedItemBlock.getParentPage().setNeedsSaving(true);
                tree1.clearSelection();
            }
        });
        deleteItemsetButton.addActionListener(new ActionListener() { //remove itemset from a champion
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedItemPage == null) return;
                selectedItemPage.setNeedsDeleting(true);
                champModel.removeNodeFromParent(selectedItemPageNode);
                selectedItemPageNode.removeFromParent();
                selectedItemPage = null;
                tree1.clearSelection();
            }
        });
        upButton.addActionListener(new ActionListener() { //move an item up
            @Override
            public void actionPerformed(ActionEvent e) {
                int currentIndex = selectedItemLeftNode.getParent().getIndex(selectedItemLeftNode);
                if (currentIndex == 0) return;
                selectedItemLeft.getParent().swap(currentIndex, currentIndex - 1);
                selectedItemLeft.getParent().getParentPage().setNeedsSaving(true);

                //((DefaultMutableTreeNode) selectedItemLeftNode.getParent()).insert(selectedItemLeftNode, currentIndex - 1);
                DefaultMutableTreeNode parent = (DefaultMutableTreeNode) selectedItemLeftNode.getParent();
                champModel.removeNodeFromParent(selectedItemLeftNode);
                champModel.insertNodeInto(selectedItemLeftNode, parent, currentIndex - 1);

            }
        });

        downButton.addActionListener(new ActionListener() {  //move an item down
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultMutableTreeNode parent = (DefaultMutableTreeNode) selectedItemLeftNode.getParent();

                int currentIndex = parent.getIndex(selectedItemLeftNode);
                if (currentIndex == parent.getChildCount() - 1) return;
                selectedItemLeft.getParent().swap(currentIndex, currentIndex + 1);
                selectedItemLeft.getParent().getParentPage().setNeedsSaving(true);

                //((DefaultMutableTreeNode) selectedItemLeftNode.getParent()).insert(selectedItemLeftNode, currentIndex - 1);
                champModel.removeNodeFromParent(selectedItemLeftNode);
                champModel.insertNodeInto(selectedItemLeftNode, parent, currentIndex + 1);

            }
        });
        ItemBlockMoveUp.addActionListener(new ActionListener() { //move block up
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultMutableTreeNode parent = (DefaultMutableTreeNode) selectedItemBlockNode.getParent();

                int currentIndex = selectedItemBlockNode.getParent().getIndex(selectedItemBlockNode);
                if (currentIndex == 0) return;
                selectedItemBlock.getParentPage().swap(currentIndex, currentIndex - 1);
                selectedItemBlock.getParentPage().setNeedsSaving(true);

                //((DefaultMutableTreeNode) selectedItemLeftNode.getParent()).insert(selectedItemLeftNode, currentIndex - 1);
                champModel.removeNodeFromParent(selectedItemBlockNode);
                champModel.insertNodeInto(selectedItemBlockNode, parent, currentIndex - 1);
            }
        });
        ItemBlockMoveDown.addActionListener(new ActionListener() {  //move block down
            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultMutableTreeNode parent = (DefaultMutableTreeNode) selectedItemBlockNode.getParent();

                int currentIndex = selectedItemBlockNode.getParent().getIndex(selectedItemBlockNode);
                if (currentIndex == parent.getChildCount() - 1) return;
                selectedItemBlock.getParentPage().swap(currentIndex, currentIndex + 1);
                selectedItemBlock.getParentPage().setNeedsSaving(true);

                //((DefaultMutableTreeNode) selectedItemLeftNode.getParent()).insert(selectedItemLeftNode, currentIndex - 1);
                champModel.removeNodeFromParent(selectedItemBlockNode);
                champModel.insertNodeInto(selectedItemBlockNode, parent, currentIndex + 1);

            }
        });
        itemSearchBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char keyPressed = e.getKeyChar();
                if (Character.isLetter(keyPressed) || Character.isSpaceChar(keyPressed) || keyPressed == '\'') {
                    mostRecentSearch = e.getKeyChar();
                    wasLastChar = true;
                } else {
                    wasLastChar = false;
                    e.consume();
                }
                updateItemList();
                //System.out.println(keyPressed);
            }
        });

    }

    private void updateItemList() {
        ArrayList<Item> items = Main.allItems;
        ArrayList<ItemStat> allowedStats = new ArrayList<ItemStat>();
        Collections.addAll(allowedStats, getAllowedCategories());
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Items");
        for (Item i : items) {

            ArrayList<ItemStat> iStats = new ArrayList<ItemStat>();
            Collections.addAll(iStats, i.getTags());
            boolean doIt = true;
            for (ItemStat st : allowedStats) {
                if (!iStats.contains(st)) {
                    doIt = false;
                }
            }

            String searchQ = wasLastChar ? itemSearchBox.getText() + mostRecentSearch : itemSearchBox.getText();
            //System.out.println(searchQ);
            if (!i.getName().toLowerCase().replaceAll("'", "").contains(searchQ.toLowerCase().replaceAll("'", "")))
                doIt = false;

            if (doIt) {
                DefaultMutableTreeNode item = new DefaultMutableTreeNode(i);


                DefaultMutableTreeNode desc = new DefaultMutableTreeNode("Description");
                String descr = i.getDescription();
                for (String b_desc : descr.split("<br>")) {
                    desc.add(new DefaultMutableTreeNode(b_desc));
                }
                //maybe make a new node for each <br> that exists within this.

                DefaultMutableTreeNode stats = new DefaultMutableTreeNode("Stats");
                for (Stat s : i.getItemStats()) {
                    DefaultMutableTreeNode node = new DefaultMutableTreeNode(s.getAttribute() + ": " + s.getModifier());
                    stats.add(node);
                }
                DefaultMutableTreeNode allowedMaps = new DefaultMutableTreeNode("Allowed Maps");
                for (String s : i.getAllowedMaps()) {
                    allowedMaps.add(new DefaultMutableTreeNode(s));
                }

                DefaultMutableTreeNode gold = new DefaultMutableTreeNode("Gold");
                DefaultMutableTreeNode total = new DefaultMutableTreeNode("Total cost: " + i.getTotalCost());
                DefaultMutableTreeNode base = new DefaultMutableTreeNode("Base cost: " + i.getBaseCost());
                DefaultMutableTreeNode sellValue = new DefaultMutableTreeNode("Sell value: " + i.getSellValue());

                gold.add(total);
                gold.add(base);
                gold.add(sellValue);

                DefaultMutableTreeNode id = new DefaultMutableTreeNode("Item ID: " + i.getID());
                item.add(id);
                item.add(desc);
                if (stats.children().hasMoreElements()) {
                    item.add(stats);
                }
                item.add(allowedMaps);
                item.add(gold);
                top.add(item);
            }
        }
        itemModel.setRoot(top);
    }

    private ItemStat[] getAllowedCategories() {
        ArrayList<ItemStat> allowedStats = new ArrayList<>();

        if (bootsCheckBox.isSelected()) allowedStats.add(ItemStat.Boots);
        if (spellDamageCheckBox.isSelected()) allowedStats.add(ItemStat.SpellDamage);
        if (laneCheckBox.isSelected()) allowedStats.add(ItemStat.Lane);
        if (manaRegenCheckBox.isSelected()) allowedStats.add(ItemStat.ManaRegen);
        if (manaCheckBox1.isSelected()) allowedStats.add(ItemStat.Mana);
        if (jungleCheckBox.isSelected()) allowedStats.add(ItemStat.Jungle);
        if (criticalStrikeCheckBox.isSelected()) allowedStats.add(ItemStat.CriticalStrike);
        if (damageCheckBox.isSelected()) allowedStats.add(ItemStat.Damage);
        if (attackSpeedCheckBox.isSelected()) allowedStats.add(ItemStat.AttackSpeed);
        if (consumableCheckBox.isSelected()) allowedStats.add(ItemStat.Consumable);
        if (onHitCheckBox.isSelected()) allowedStats.add(ItemStat.OnHit);
        if (healthCheckBox1.isSelected()) allowedStats.add(ItemStat.Health);
        if (magicResistanceCheckBox1.isSelected()) allowedStats.add(ItemStat.SpellBlock);
        if (stealthCheckBox.isSelected()) allowedStats.add(ItemStat.Stealth);
        if (activeCheckBox.isSelected()) allowedStats.add(ItemStat.Active);
        if (nonBootsMovementCheckBox.isSelected()) allowedStats.add(ItemStat.NonbootsMovement);
        if (tenacityCheckBox.isSelected()) allowedStats.add(ItemStat.Tenacity);
        if (spellVampCheckBox.isSelected()) allowedStats.add(ItemStat.SpellVamp);
        if (goldRegenerationCheckBox.isSelected()) allowedStats.add(ItemStat.GoldPer);
        if (auraCheckBox.isSelected()) allowedStats.add(ItemStat.Aura);
        if (healthRegenCheckBox.isSelected()) allowedStats.add(ItemStat.HealthRegen);
        if (armourCheckBox1.isSelected()) allowedStats.add(ItemStat.Armor);
        if (lifestealCheckBox.isSelected()) allowedStats.add(ItemStat.LifeSteal);
        if (visionCheckBox.isSelected()) allowedStats.add(ItemStat.Vision);
        if (cooldownReductionCheckBox.isSelected()) allowedStats.add(ItemStat.CooldownReduction);
        if (magicPenetrationCheckBox.isSelected()) allowedStats.add(ItemStat.MagicPenetration);
        if (slowCheckBox.isSelected()) allowedStats.add(ItemStat.Slow);
        if (armourPenetrationCheckBox.isSelected()) allowedStats.add(ItemStat.ArmorPenetration);
        if (trinketCheckBox.isSelected()) allowedStats.add(ItemStat.Trinket);
        if (bilgewaterCheckBox.isSelected()) allowedStats.add(ItemStat.Bilgewater);

        return allowedStats.toArray(new ItemStat[0]);
    }

    private void createChampNodes(DefaultMutableTreeNode top) {

        try {
            Main.getChampions();
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
        Main.updateItemSetsAllChamps();

        Map<Champion, ArrayList<ItemPage>> itemPages = Main.allItemsSets;
        for (Champion c : itemPages.keySet()) {

            DefaultMutableTreeNode champ = new DefaultMutableTreeNode(c);
            boolean setsExist = false;

            for (ItemPage page : itemPages.get(c)) {
                setsExist = true;
                DefaultMutableTreeNode pageNode = new DefaultMutableTreeNode(page);
                for (ItemBlock block : page.getItemBlocks()) {
                    DefaultMutableTreeNode blockNode = new DefaultMutableTreeNode(block);
                    ArrayList<ItemCount> items = block.getItems();
                    for (ItemCount item : items) {
                        try {

                            DefaultMutableTreeNode itemNode = new DefaultMutableTreeNode(item);
                            blockNode.add(itemNode);
                        } catch (Exception ex) {
                        }
                    }
                    pageNode.add(blockNode);
                }
                if (setsExist) champ.add(pageNode);
            }
            top.add(champ);
        }
    }

    private void createItemNodes(DefaultMutableTreeNode top) {
        Item[] items;
        try {
            items = Main.getItems();
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
        for (Item i : items) {

            DefaultMutableTreeNode item = new DefaultMutableTreeNode(i);


            DefaultMutableTreeNode desc = new DefaultMutableTreeNode(i.getDescription());

            DefaultMutableTreeNode stats = new DefaultMutableTreeNode("Stats");
            for (Stat s : i.getItemStats()) {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(s.getAttribute() + ": " + s.getModifier());
                stats.add(node);
            }
            DefaultMutableTreeNode allowedMaps = new DefaultMutableTreeNode("Allowed Maps");
            for (String s : i.getAllowedMaps()) {
                allowedMaps.add(new DefaultMutableTreeNode(s));
            }

            DefaultMutableTreeNode gold = new DefaultMutableTreeNode("Gold");
            DefaultMutableTreeNode total = new DefaultMutableTreeNode("Total cost: " + i.getTotalCost());
            DefaultMutableTreeNode base = new DefaultMutableTreeNode("Base cost: " + i.getBaseCost());
            DefaultMutableTreeNode sellValue = new DefaultMutableTreeNode("Sell value: " + i.getSellValue());

            gold.add(total);
            gold.add(base);
            gold.add(sellValue);

            DefaultMutableTreeNode id = new DefaultMutableTreeNode("Item ID: " + i.getID());
            item.add(id);
            item.add(desc);
            if (stats.children().hasMoreElements()) {
                item.add(stats);
            }
            item.add(allowedMaps);
            item.add(gold);
            top.add(item);
        }
    }

    public static void main(String[] args) {
        Main.init();
        //System.loadLibrary("gson-2.6.2");
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new GUI().panel1);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        if (args.length != 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String str : args) stringBuilder.append(str);
            Main.installDirectory = stringBuilder.toString();
        }
    }

    private void createUIComponents() {
        ItemRootNode = new DefaultMutableTreeNode("Items");
        createItemNodes(ItemRootNode);
        itemModel = new DefaultTreeModel(ItemRootNode);
        tree2 = new JTree(itemModel);
        tree2.setCellRenderer(new ItemTreeCellRenderer());
        //tree1.putClientProperty("html.disable", Boolean.FALSE);

        ChampRootNode = new DefaultMutableTreeNode("Champions");
        createChampNodes(ChampRootNode);
        champModel = new DefaultTreeModel(ChampRootNode);
        tree1 = new JTree(champModel);
        tree1.setCellRenderer(new ChampionTreeCellRenderer());
        //tree1.putClientProperty("html.disable", Boolean.FALSE);


    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        final JScrollPane scrollPane1 = new JScrollPane();
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 26;
        gbc.weightx = 5.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(scrollPane1, gbc);
        tree1.putClientProperty("html.disable", Boolean.FALSE);
        scrollPane1.setViewportView(tree1);
        final JScrollPane scrollPane2 = new JScrollPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 1;
        gbc.gridheight = 25;
        gbc.weightx = 3.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(scrollPane2, gbc);
        tree2.putClientProperty("html.disable", Boolean.FALSE);
        scrollPane2.setViewportView(tree2);
        final JLabel label1 = new JLabel();
        label1.setText("Create new itemset with specified name into a champion");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label1, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Add selected item to itemblock");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label2, gbc);
        SelectedItem = new JTextField();
        SelectedItem.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(SelectedItem, gbc);
        AddItemButton = new JButton();
        AddItemButton.setText("Add Item");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(AddItemButton, gbc);
        CreateNewItemset = new JButton();
        CreateNewItemset.setText("Create");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(CreateNewItemset, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("Remove selected item from itemblock");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label3, gbc);
        newItemSetName = new JTextField();
        newItemSetName.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(newItemSetName, gbc);
        ItemSetItemText = new JTextField();
        ItemSetItemText.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(ItemSetItemText, gbc);
        removeButton = new JButton();
        removeButton.setText("Remove");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(removeButton, gbc);
        final JLabel label4 = new JLabel();
        label4.setText("Move selected Item up or down");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label4, gbc);
        upButton = new JButton();
        upButton.setText("Up");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(upButton, gbc);
        downButton = new JButton();
        downButton.setText("Down");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(downButton, gbc);
        ItemMovementText = new JTextField();
        ItemMovementText.setEditable(false);
        ItemMovementText.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.gridwidth = 3;
        gbc.weightx = 10.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(ItemMovementText, gbc);
        final JLabel label5 = new JLabel();
        label5.setText("Move the selected Item Block up or down");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label5, gbc);
        selectedItemBlockText2 = new JTextField();
        selectedItemBlockText2.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 9;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(selectedItemBlockText2, gbc);
        ItemBlockMoveUp = new JButton();
        ItemBlockMoveUp.setText("Up");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(ItemBlockMoveUp, gbc);
        ItemBlockMoveDown = new JButton();
        ItemBlockMoveDown.setText("Down");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(ItemBlockMoveDown, gbc);
        final JLabel label6 = new JLabel();
        label6.setText("Create an Item block with the speficied name in a set");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label6, gbc);
        createButton = new JButton();
        createButton.setText("Create");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 11;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(createButton, gbc);
        NewItemBlockField = new JTextField();
        NewItemBlockField.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(NewItemBlockField, gbc);
        bootsCheckBox = new JCheckBox();
        bootsCheckBox.setText("Boots");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 17;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(bootsCheckBox, gbc);
        spellDamageCheckBox = new JCheckBox();
        spellDamageCheckBox.setText("Spell Damage");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 18;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(spellDamageCheckBox, gbc);
        laneCheckBox = new JCheckBox();
        laneCheckBox.setText("Lane");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 19;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(laneCheckBox, gbc);
        manaRegenCheckBox = new JCheckBox();
        manaRegenCheckBox.setText("Mana Regen");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 17;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(manaRegenCheckBox, gbc);
        manaCheckBox1 = new JCheckBox();
        manaCheckBox1.setText("Mana");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 18;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(manaCheckBox1, gbc);
        jungleCheckBox = new JCheckBox();
        jungleCheckBox.setText("Jungle");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 19;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(jungleCheckBox, gbc);
        criticalStrikeCheckBox = new JCheckBox();
        criticalStrikeCheckBox.setText("Critical Strike");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 17;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(criticalStrikeCheckBox, gbc);
        damageCheckBox = new JCheckBox();
        damageCheckBox.setText("Damage");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 18;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(damageCheckBox, gbc);
        attackSpeedCheckBox = new JCheckBox();
        attackSpeedCheckBox.setText("Attack Speed");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 19;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(attackSpeedCheckBox, gbc);
        consumableCheckBox = new JCheckBox();
        consumableCheckBox.setText("Consumable");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 20;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(consumableCheckBox, gbc);
        onHitCheckBox = new JCheckBox();
        onHitCheckBox.setText("On Hit");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 19;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(onHitCheckBox, gbc);
        healthCheckBox1 = new JCheckBox();
        healthCheckBox1.setText("Health");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 17;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(healthCheckBox1, gbc);
        magicResistanceCheckBox1 = new JCheckBox();
        magicResistanceCheckBox1.setText("Magic Resistance");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 18;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(magicResistanceCheckBox1, gbc);
        stealthCheckBox = new JCheckBox();
        stealthCheckBox.setText("Stealth");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 20;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(stealthCheckBox, gbc);
        activeCheckBox = new JCheckBox();
        activeCheckBox.setText("Active");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 20;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(activeCheckBox, gbc);
        nonBootsMovementCheckBox = new JCheckBox();
        nonBootsMovementCheckBox.setText("Non-boots movement");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 20;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(nonBootsMovementCheckBox, gbc);
        tenacityCheckBox = new JCheckBox();
        tenacityCheckBox.setText("Tenacity");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 21;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(tenacityCheckBox, gbc);
        spellVampCheckBox = new JCheckBox();
        spellVampCheckBox.setText("SpellVamp");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 21;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(spellVampCheckBox, gbc);
        goldRegenerationCheckBox = new JCheckBox();
        goldRegenerationCheckBox.setText("Gold Regeneration");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 21;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(goldRegenerationCheckBox, gbc);
        auraCheckBox = new JCheckBox();
        auraCheckBox.setText("Aura");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 21;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(auraCheckBox, gbc);
        healthRegenCheckBox = new JCheckBox();
        healthRegenCheckBox.setText("Health Regen");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 17;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(healthRegenCheckBox, gbc);
        armourCheckBox1 = new JCheckBox();
        armourCheckBox1.setText("Armour");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 18;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(armourCheckBox1, gbc);
        lifestealCheckBox = new JCheckBox();
        lifestealCheckBox.setText("Lifesteal");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 19;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(lifestealCheckBox, gbc);
        visionCheckBox = new JCheckBox();
        visionCheckBox.setText("Vision");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 20;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(visionCheckBox, gbc);
        cooldownReductionCheckBox = new JCheckBox();
        cooldownReductionCheckBox.setText("Cooldown Reduction");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 21;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(cooldownReductionCheckBox, gbc);
        magicPenetrationCheckBox = new JCheckBox();
        magicPenetrationCheckBox.setText("Magic Penetration");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 22;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(magicPenetrationCheckBox, gbc);
        slowCheckBox = new JCheckBox();
        slowCheckBox.setText("Slow");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 22;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(slowCheckBox, gbc);
        armourPenetrationCheckBox = new JCheckBox();
        armourPenetrationCheckBox.setText("Armour Penetration");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 22;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(armourPenetrationCheckBox, gbc);
        trinketCheckBox = new JCheckBox();
        trinketCheckBox.setText("Trinket");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 22;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(trinketCheckBox, gbc);
        bilgewaterCheckBox = new JCheckBox();
        bilgewaterCheckBox.setText("Bilgewater");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 22;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(bilgewaterCheckBox, gbc);
        selectedItemBlockText = new JTextField();
        selectedItemBlockText.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(selectedItemBlockText, gbc);
        selectedItemPageText = new JTextField();
        selectedItemPageText.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(selectedItemPageText, gbc);
        selectedChampionText = new JTextField();
        selectedChampionText.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(selectedChampionText, gbc);
        final JLabel label7 = new JLabel();
        label7.setText("Delete selected Item block");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 12;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label7, gbc);
        selectedItemBlock2 = new JTextField();
        selectedItemBlock2.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 13;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(selectedItemBlock2, gbc);
        DeleteItemBlockButton = new JButton();
        DeleteItemBlockButton.setText("Delete");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 13;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(DeleteItemBlockButton, gbc);
        selectedItemSet2 = new JTextField();
        selectedItemSet2.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 15;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(selectedItemSet2, gbc);
        deleteItemsetButton = new JButton();
        deleteItemsetButton.setText("Delete Itemset");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 15;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(deleteItemsetButton, gbc);
        final JLabel label8 = new JLabel();
        label8.setText("Delete selected itemset     !Warning");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 14;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label8, gbc);
        saveButton = new JButton();
        saveButton.setText("Save");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 25;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(saveButton, gbc);
        final JLabel label9 = new JLabel();
        label9.setText("Filter Items");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 16;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label9, gbc);
        itemSearchBox = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(itemSearchBox, gbc);
        final JLabel label10 = new JLabel();
        label10.setText("Search for an item:");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label10, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
