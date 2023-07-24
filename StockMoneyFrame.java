import VendingMachine.VendingMachine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StockMoneyFrame extends JFrame{
    private ArrayList<Double> moneyValue;
    private ArrayList<Integer> moneyStock;

    private ArrayList<JButton> buttons;
    private ArrayList<JTextArea> stock;
    private JButton backButton;

    public StockMoneyFrame(ArrayList<Double> money, ArrayList<Integer> moneyStock) {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.moneyValue = money;
        this.moneyStock = moneyStock;
        this.buttons = new ArrayList<>();
        this.stock = new ArrayList<>();

        ArrayList<JPanel> moneyPanels = new ArrayList<>();

        for(int i = 0 ; i<this.moneyValue.size() ; i++) {
            String temp = "Bill: " + this.moneyValue.get(i);
            String temp2 = "Stock: " + this.moneyStock.get(i);

            this.buttons.add(new JButton(temp));
            this.stock.add(new JTextArea(temp2));
            this.stock.get(i).setEditable(false);

            moneyPanels.add(new JPanel());
            moneyPanels.get(i).setLayout(new BoxLayout(moneyPanels.get(i), BoxLayout.X_AXIS));
            moneyPanels.get(i).add(this.buttons.get(i));
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
        setVisible(true);
    }

    public void setItemButtonListener(int index, ActionListener actionListener){
        JButton temp = this.buttons.get(index);
        temp.addActionListener(actionListener);
    }

    public void setBackButtonListener(ActionListener actionListener){
        this.backButton.addActionListener(actionListener);
    }

    public static void main(String[] args) {
        VendingMachine vm = new VendingMachine();
        StockMoneyFrame sim = new StockMoneyFrame(vm.getMoneyValue(), vm.getMoneyStock());
    }
}
