import VendingMachine.VendingMachine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StockItemFrame extends JFrame{

    private ArrayList<String> itemNames;
    private ArrayList<Integer> itemStock;

    private ArrayList<JButton> buttons;
    private ArrayList<JTextArea> stock;
    private JButton backButton;
    private JButton addNewItemButton;

    public StockItemFrame (ArrayList<String> itemNames, ArrayList<Integer> itemStock) {
        super("Stock Items");
        this.buttons = new ArrayList<JButton>();
        this.itemNames = itemNames;
        this.itemStock = itemStock;

        stock = new ArrayList<>();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        ArrayList<JPanel> items = new ArrayList<>();

        for(int i=0 ; i<this.itemNames.size() ; i++) {
            String temp = this.itemNames.get(i);
            JButton holder = new JButton(temp);
            String temp2 = String.valueOf(this.itemStock.get(i));
            JTextArea holder2 = new JTextArea(temp2);

            this.buttons.add(holder);
            stock.add(holder2);

            items.add(new JPanel());
            items.get(i).setLayout(new BoxLayout(items.get(i), BoxLayout.X_AXIS));
            items.get(i).add(this.buttons.get(i));
            items.get(i).add(stock.get(i));
        }

        for(JPanel panel : items) {
            mainPanel.add(panel);
        }
        this.addNewItemButton = new JButton("Add New Item");
        JPanel addNewItem = new JPanel();
        addNewItem.add(addNewItemButton);
        mainPanel.add(addNewItem);

        this.backButton = new JButton("Back");
        JPanel back = new JPanel();
        back.add(this.backButton, BorderLayout.SOUTH);
        mainPanel.add(back);

        add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setSize(250, 400);
        setVisible(false);
    }

    public JTextArea getStock(int index){
        return this.stock.get(index);
    }

    public void setItemButtonListener(int index, ActionListener actionListener){
        JButton temp = this.buttons.get(index);
        temp.addActionListener(actionListener);
    }

    public void setBackButtonListener(ActionListener actionListener){
        this.backButton.addActionListener(actionListener);
    }
}
