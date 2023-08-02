import VendingMachine.VendingMachine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StockMoneyFrame extends JFrame implements Observer{
    private ArrayList<Double> moneyValue;
    private ArrayList<Integer> moneyStock;

    private ArrayList<JButton> buttons;
    private ArrayList<JTextArea> stock,denominations;
    private JButton backButton,restock;

    public StockMoneyFrame(ArrayList<Double> money, ArrayList<Integer> moneyStock) {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.moneyValue = money;
        this.moneyStock = moneyStock;
        this.buttons = new ArrayList<>();
        this.stock = new ArrayList<>();
        this.denominations = new ArrayList<>();

        this.restock = new JButton("Restock");
        JPanel stockPanel = new JPanel();
        stockPanel.add(restock);
        mainPanel.add(stockPanel);

        ArrayList<JPanel> moneyPanels = new ArrayList<>();

        for(int i = 0 ; i<this.moneyValue.size() ; i++) {
            String temp = "Bill: " + this.moneyValue.get(i);
            String temp2 = String.valueOf(this.moneyStock.get(i));

            this.denominations.add(new JTextArea(temp));
            this.stock.add(new JTextArea(temp2));
            this.stock.get(i).setEditable(false);

            moneyPanels.add(new JPanel());
            moneyPanels.get(i).setLayout(new BoxLayout(moneyPanels.get(i), BoxLayout.X_AXIS));
            moneyPanels.get(i).add(this.denominations.get(i));
            moneyPanels.get(i).add(this.stock.get(i));
            mainPanel.add(moneyPanels.get(i));
        }

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

    public ArrayList<JTextArea> getStock(){
        return this.stock;
    }

    public void setRestockButtonListener(ActionListener actionListener){
        this.restock.addActionListener(actionListener);
    }

    public void setItemButtonListener(int index, ActionListener actionListener){
        JButton temp = this.buttons.get(index);
        temp.addActionListener(actionListener);
    }

    public void setBackButtonListener(ActionListener actionListener){
        this.backButton.addActionListener(actionListener);
    }

    public void update() {
        // Update the money stock display for each denomination
        for (int i = 0; i < moneyStock.size(); i++) {
            JTextArea stockTextArea = stock.get(i);
            int updatedStock = moneyStock.get(i);
            stockTextArea.setText(String.valueOf(updatedStock));
        }
    }
}
