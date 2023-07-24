import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VendView extends JFrame{
    private JFrame rVendFrame;
    private JButton buyButton,backButton;
    private ArrayList<JTextArea> displayPrices,displayCalories,displayStock;
    private ArrayList<JButton> buttons;

    public VendView(ArrayList<String> items, ArrayList<Double> prices, ArrayList<Double> calories, ArrayList<Integer> stocks){
        super("Vending Machine");

        this.rVendFrame = new JFrame();
        this.rVendFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.rVendFrame.setSize(250, 40);

        this.displayPrices = new ArrayList<>();
        this.displayCalories = new ArrayList<>();
        this.displayStock = new ArrayList<>();
        this.buttons = new ArrayList<>();

        JPanel mainPanel = new JPanel();
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

        for(int i = 0;i < prices.size();i++){
            Double temp = prices.get(i);
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

        for(int i = 0;i < stocks.size();i++){
            Integer temp = stocks.get(i);
            String s = String.valueOf(temp);
            JTextArea holder = new JTextArea(s);
            displayStock.add(holder);
            displayStock.get(i).setEditable(false);
        }
        System.out.println("stocks labled");

        this.buyButton = new JButton("Buy item");
        this.backButton = new JButton("Return to menu");

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

    public void setItemButtonListener(int index, ActionListener actionListener){
        JButton temp = buttons.get(index);
        temp.addActionListener(actionListener);
    }
    public JTextArea getStockTextArea(int index){
        return this.displayStock.get(index);
    }

    public void setBuyButtonListener(ActionListener actionListener){
        this.buyButton.addActionListener(actionListener);
    }

    public void setBackButtonListener(ActionListener actionListener){
        this.backButton.addActionListener(actionListener);
    }
}
