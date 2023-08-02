import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class VendView extends JFrame implements Observer{
    protected JFrame rVendFrame;
    protected JButton buyButton,backButton;
    protected JTextArea money,price,change;
    protected JPanel mainPanel;
    protected ArrayList<JTextArea> displayPrices,displayCalories,displayStock;
    protected ArrayList<JButton> buttons,moneyButtons;
    protected List<String> denominations,items;
    private ArrayList<Double> prices;
    private ArrayList<Integer> itemStocks;

    public VendView(ArrayList<String> items, ArrayList<Double> prices, ArrayList<Double> calories, ArrayList<Integer> stocks){
        super("Vending Machine");

        this.prices = prices;
        this.itemStocks = stocks;

        this.rVendFrame = new JFrame();
        this.rVendFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.rVendFrame.setSize(250, 40);

        this.displayPrices = new ArrayList<>();
        this.displayCalories = new ArrayList<>();
        this.displayStock = new ArrayList<>();
        this.buttons = new ArrayList<>();
        this.moneyButtons = new ArrayList<>();

        this.items = items;
        this.denominations = List.of("One","Five","Ten","Twenty","Fifty","One Hundred","Two Hundred","Five Hundred","One Thousand");

        this.mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setSize(250, 400);
        ArrayList<JPanel> displayItems = new ArrayList<>();

        for(int i = 0;i < items.size();i++){
            String temp = items.get(i);
            JButton holder = new JButton(temp);
            buttons.add(holder);
        }
        System.out.println("buttons labled");

        for(int i = 0;i < this.prices.size();i++){
            Double temp = this.prices.get(i);
            String s = String.valueOf(temp);
            JTextArea holder = new JTextArea(s);
            displayPrices.add(holder);
            displayPrices.get(i).setEditable(false);
        }
        System.out.println("prices labled");

        for(int i = 0;i < calories.size();i++){
            Double temp = calories.get(i);
            String s = String.valueOf(temp);
            JTextArea holder = new JTextArea(s);
            displayCalories.add(holder);
            displayCalories.get(i).setEditable(false);
        }
        System.out.println("calories labled");

        for(int i = 0;i < this.itemStocks.size();i++){
            Integer temp = this.itemStocks.get(i);
            String s = String.valueOf(temp);
            JTextArea holder = new JTextArea(s);
            displayStock.add(holder);
            displayStock.get(i).setEditable(false);
        }
        System.out.println("stocks labled");

        this.money = new JTextArea("0");
        this.buyButton = new JButton("Buy item");
        this.price = new JTextArea("0");
        this.change = new JTextArea("0");
        this.backButton = new JButton("Return to menu");

        for(int i = 0;i < denominations.size();i++){
            String holder = denominations.get(i);
            JButton temp = new JButton(holder);
            moneyButtons.add(temp);
        }

        System.out.println("for loop to add buttons to panels");
        for(int i = 0;i < items.size();i++){
            displayItems.add(new JPanel());
            displayItems.get(i).setLayout(new BoxLayout(displayItems.get(i), BoxLayout.X_AXIS));
            System.out.println("Panel and layout set " + i);
            displayItems.get(i).add(buttons.get(i));
            System.out.println("Button set " + i);
            displayItems.get(i).add(displayPrices.get(i));
            System.out.println("Price set " + i);
            displayItems.get(i).add(displayStock.get(i));
            System.out.println("stock set " + i);
            displayItems.get(i).add(displayCalories.get(i));
            System.out.println("calories set " + i);
        }
        System.out.println("panels added");

        for(JPanel panel : displayItems){
            mainPanel.add(panel);
            System.out.println("panel added to main");
        }

        JPanel moneyPanel = new JPanel();
        moneyPanel.setLayout(new BoxLayout(moneyPanel, BoxLayout.Y_AXIS));

        moneyPanel.add(Box.createVerticalGlue());

        for (JButton moneyButton : moneyButtons) {
            moneyPanel.add(moneyButton);
        }

        moneyPanel.add(money);

        moneyPanel.add(Box.createVerticalGlue());

        mainPanel.add(moneyPanel,BorderLayout.EAST);

        JPanel price = new JPanel();
        this.price.setEditable(false);
        price.add(this.price,BorderLayout.SOUTH);
        mainPanel.add(price);

        JPanel change = new JPanel();
        this.change.setEditable(false);
        change.add(this.change,BorderLayout.SOUTH);
        mainPanel.add(price);

        JPanel buy = new JPanel();
        buy.add(this.buyButton, BorderLayout.SOUTH);
        mainPanel.add(buy);
        System.out.println("added buy panel");

        JPanel back = new JPanel();
        back.add(this.backButton, BorderLayout.SOUTH);
        mainPanel.add(back);
        System.out.println("added back panel");

        rVendFrame.add(mainPanel);

        rVendFrame.setVisible(false);
    }

    public JFrame getRVendFrame(){
        return this.rVendFrame;
    }

    public void setMoneyButtonListener(int index,ActionListener actionListener){
        JButton temp = moneyButtons.get(index);
        temp.addActionListener(actionListener);
    }

    public void setItemButtonListener(int index, ActionListener actionListener){
        JButton temp = buttons.get(index);
        temp.addActionListener(actionListener);
    }

    public String getItems(int index){return this.items.get(index);}
    public JTextArea getStockTextArea(int index){
        return this.displayStock.get(index);
    }

    public JTextArea getMoneyTextArea() { return this.money; }

    public JTextArea getDisplayPriceTextArea(int index) { return this.displayPrices.get(index); }
    public JTextArea getPriceTextArea(){ return this.price; }

    public JTextArea getChangeTextArea() {return this.change; }

    public void setBuyButtonListener(ActionListener actionListener){
        this.buyButton.addActionListener(actionListener);
    }

    public void setBackButtonListener(ActionListener actionListener){
        this.backButton.addActionListener(actionListener);
    }

    public void update() {
        // Update the stock quantity display for each item
        for (int i = 0; i < itemStocks.size(); i++) {
            JTextArea stockTextArea = displayStock.get(i);
            int updatedStock = itemStocks.get(i);
            stockTextArea.setText(String.valueOf(updatedStock));
        }

        // Update the price display for each item
        for (int i = 0; i < prices.size(); i++) {
            JTextArea priceTextArea = displayPrices.get(i);
            double updatedPrice = prices.get(i);
            priceTextArea.setText(String.valueOf(updatedPrice));
        }
    }
}
