import VendingMachine.*;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

public class MaintView extends JFrame{
    private VendingMachine vendingMachine;
    private JButton setPriceButton,stockItemButton,stockMoneyButton,collectMoneyButton,transHistoryButton,backButton;

    public MaintView(){
        super("Vending Machine Maintenance");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setSize(250, 400);

        this.backButton = new JButton("Return to menu");
        this.setPriceButton = new JButton("Set Item Prices");
        this.stockItemButton = new JButton("Stock Items");
        this.stockMoneyButton = new JButton("Stock Money");
        this.collectMoneyButton = new JButton("Collect Money");
        this.transHistoryButton = new JButton("Transaction History");
        
        add(setPriceButton);
        add(stockItemButton);
        add(stockMoneyButton);
        add(collectMoneyButton);
        add(transHistoryButton);
        add(backButton);

        setVisible(false);
    }
    
    public void setSetPriceButton(ActionListener actionListener){
        this.setPriceButton.addActionListener(actionListener);
    }
    
    public void setStockItemButton(ActionListener actionListener){
        this.stockItemButton.addActionListener(actionListener);
    }
    
    public void setStockMoneyButton(ActionListener actionListener){
        this.stockMoneyButton.addActionListener(actionListener);
    }

    public void setCollectMoneyButton(ActionListener actionlistener){
        this.collectMoneyButton.addActionListener(actionlistener);
    }
    
    public void setTransHistoryButton(ActionListener actionListener) {
        this.transHistoryButton.addActionListener(actionListener);
    }

    public void setBackButtonListener(ActionListener actionListener){
        this.backButton.addActionListener(actionListener);
    }
}
