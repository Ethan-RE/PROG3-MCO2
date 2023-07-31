import VendingMachine.VendingMachine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SetItemPriceFrame extends JFrame{
    private ArrayList<String> itemNames;
    private ArrayList<Double> itemPrice;
    private ArrayList<JTextArea> price;
    private ArrayList<JButton> buttons;
    private JTextField input;
    private JPanel inputPanel;
    private JButton backButton;

    public SetItemPriceFrame (ArrayList<String> itemNames, ArrayList<Double> itemPrice) {
        super("Set Item Price");
        this.buttons = new ArrayList<JButton>();
        this.price = new ArrayList<>();
        this.itemNames = itemNames;
        this.input = new JTextField(8);
        this.itemPrice = itemPrice;

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        ArrayList<JPanel> items = new ArrayList<>();

        for(int i=0 ; i<this.itemNames.size() ; i++) {
            String temp = this.itemNames.get(i);
            JButton holder = new JButton(temp);
            String temp2 = String.valueOf(itemPrice.get(i));
            JTextArea holder2 = new JTextArea(temp2);

            holder2.setEditable(false);

            this.buttons.add(holder);
            this.price.add(holder2);

            items.add(new JPanel());
            items.get(i).setLayout(new BoxLayout(items.get(i), BoxLayout.X_AXIS));
            items.get(i).add(this.buttons.get(i));
            items.get(i).add(this.price.get(i));
        }

        for(JPanel panel : items) {
            mainPanel.add(panel);
        }

        inputPanel = new JPanel();
        inputPanel.add(this.input, BorderLayout.SOUTH);
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
    }

    public JTextArea getPrice(int index){
        return this.price.get(index);
    }
    public JPanel getInputPanel(){
        return this.inputPanel;
    }

    public JTextField getInput(){
        return this.input;
    }

    public void setInputTextListener(ActionListener actionListener){
        this.input.addActionListener(actionListener);
    }

    public void setItemButtonListener(int index, ActionListener actionListener){
        JButton temp = this.buttons.get(index);
        temp.addActionListener(actionListener);
    }

    public void setBackButtonListener(ActionListener actionListener){
        this.backButton.addActionListener(actionListener);
    }
}
