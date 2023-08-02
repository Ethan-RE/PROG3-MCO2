import VendingMachine.VendingMachine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StockItemFrame extends JFrame implements Observer {

    private ArrayList<String> itemNames;
    private ArrayList<Integer> itemStock;
    private ArrayList<JButton> buttons;
    private ArrayList<JTextArea> stock;
    private JTextField name, price, calories;
    private JPanel inputPanel, mainPanel;
    private JButton backButton, addNewItemButton, finishButton;

    public StockItemFrame(ArrayList<String> itemNames, ArrayList<Integer> itemStock) {
        super("Stock Items");

        this.buttons = new ArrayList<JButton>();
        this.buttons.clear();
        this.itemNames = itemNames;
        this.itemStock = itemStock;

        this.name = new JTextField(50);
        this.price = new JTextField(10);
        this.calories = new JTextField(10);

        this.stock = new ArrayList<>();
        this.stock.clear();
        this.mainPanel = new JPanel();
        this.mainPanel.removeAll();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        ArrayList<JPanel> items = new ArrayList<>();

        for (int i = 0; i < this.itemNames.size(); i++) {
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
            System.out.println(temp + " button added");
        }

        for (JPanel panel : items) {
            mainPanel.add(panel);
        }
        this.addNewItemButton = new JButton("Add New Item");
        JPanel addNewItem = new JPanel();
        addNewItem.add(addNewItemButton);
        mainPanel.add(addNewItem);

        this.finishButton = new JButton("Finish adding");
        inputPanel = new JPanel();
        inputPanel.add(name, BorderLayout.SOUTH);
        inputPanel.add(price, BorderLayout.SOUTH);
        inputPanel.add(calories, BorderLayout.SOUTH);
        inputPanel.add(finishButton, BorderLayout.SOUTH);
        mainPanel.add(inputPanel);
        inputPanel.setVisible(false);

        this.backButton = new JButton("Back");
        JPanel back = new JPanel();
        back.add(this.backButton, BorderLayout.SOUTH);
        mainPanel.add(back);

        add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setSize(250, 400);
        setVisible(false);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public JTextArea getStock(int index) {
        return this.stock.get(index);
    }

    public JButton getAddNewItemButton() {
        return this.addNewItemButton;
    }

    public JPanel getInputPanel() {
        return this.inputPanel;
    }

    public JTextField getNewName() {
        return this.name;
    }

    public JTextField getNewPrice() {
        return this.price;
    }

    public JTextField getNewCalories() {
        return this.calories;
    }

    public void setNameTextListener(ActionListener actionListener) {
        this.name.addActionListener(actionListener);
    }

    public void setPriceTextListener(ActionListener actionListener) {
        this.price.addActionListener(actionListener);
    }

    public void setCaloriesTextListener(ActionListener actionListener) {
        this.calories.addActionListener(actionListener);
    }

    public void setFinishButtonListener(ActionListener actionListener) {
        this.finishButton.addActionListener(actionListener);
    }

    public void setAddNewItemButtonListener(ActionListener actionListener) {
        this.addNewItemButton.addActionListener(actionListener);
    }

    public void setItemButtonListener(int index, ActionListener actionListener) {
        JButton temp = this.buttons.get(index);
        temp.addActionListener(actionListener);
    }

    public void setBackButtonListener(ActionListener actionListener) {
        this.backButton.addActionListener(actionListener);
    }

    public void update() {
        // Update the stock quantity display for each item
        for (int i = 0; i < itemStock.size(); i++) {
            stock.get(i).setText(String.valueOf(itemStock.get(i)));
        }
    }

}

