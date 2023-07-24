import VendingMachine.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

public class FactoryController {
    private VendingMachine vendingMachine;
    private FactoryView factoryView;
    protected FactoryModel factoryModel;
    private Frame2 frame2;
    private Frame3 frame3;
    private VendView vendView;
    private MaintView maintView;
    private StockItemFrame stockItemView;
    private SetItemPriceFrame setItemPriceView;
    private TransactionHistoryFrame transactionHistoryView;
    private ArrayList<String> items;
    private ArrayList<Double> prices, calories,values;
    private ArrayList<Integer> itemStocks,moneyStocks;
    private ArrayList<String> transactions;

    public FactoryController (FactoryModel model, FactoryView view) {
        this.vendingMachine = new VendingMachine();
        this.factoryView = view;
        this.factoryModel = model;
        this.frame2 = new Frame2();
        this.frame3 = new Frame3();

        this.items = this.vendingMachine.getItemNames();
        this.prices = this.vendingMachine.getItemPrice();
        this.calories = this.vendingMachine.getItemCalories();
        this.values = this.vendingMachine.getMoneyValue();
        this.itemStocks = this.vendingMachine.getItemStock();
        this.moneyStocks = this.vendingMachine.getMoneyStock();
        this.transactions = this.vendingMachine.getTransactionHistory();

        this.vendView = new VendView(items,prices,calories,itemStocks);
        this.maintView = new MaintView();
        this.stockItemView = new StockItemFrame(items,itemStocks);
        this.setItemPriceView = new SetItemPriceFrame(items, prices);

        this.factoryView.setCreateButtonListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JFrame frame = factoryView.getMainFrame();
                frame.setVisible(false);
                System.out.println("frame set invisible");
                JFrame newFrame = frame2.getCreationFrame();
                newFrame.setVisible(true);
                System.out.println("frame set visible");
            }
        });

        this.factoryView.setTestButtonListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JFrame frame = factoryView.getMainFrame();
                frame.setVisible(false);
                System.out.println("frame set invisible");
                JFrame newFrame = frame3.getTestingFrame();
                newFrame.setVisible(true);
                System.out.println("frame set visible");
            }
        });

        this.factoryView.setExitButtonListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
                //exit program
            }
        });

        this.frame2.setReturnButtonListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JFrame frame = frame2.getCreationFrame();
                frame.setVisible(false);
                System.out.println("frame set invisible");
                JFrame newFrame = factoryView.getMainFrame();
                newFrame.setVisible(true);
                System.out.println("frame set visible");
            }
        });

        this.frame2.setRegularButtonListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Creating Machine");
                factoryModel.createVM();
                vendingMachine = factoryModel.getRVM();
            }
        });

        this.frame2.setSpecialButtonListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //create special vending machine code from model
            }
        });

        this.frame3.setReturnButtonListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JFrame frame = frame3.getTestingFrame();
                frame.setVisible(false);
                System.out.println("frame set invisible");
                JFrame newFrame = factoryView.getMainFrame();
                newFrame.setVisible(true);
                System.out.println("frame set visible");
            }
        });

        this.frame3.setTvendButtonListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JFrame frame = frame3.getTestingFrame();
                frame.setVisible(false);
                System.out.println("frame set invisible");
                JFrame newFrame = vendView.getRVendFrame();
                newFrame.setVisible(true);
                System.out.println("frame set visible");
            }
        });

        this.frame3.setTmaintButtonListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JFrame frame = factoryView.getMainFrame();
                frame.setVisible(false);
                maintView.setVisible(true);
            }
        });

        this.vendView.setBackButtonListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JFrame frame = vendView.getRVendFrame();
                frame.setVisible(false);
                System.out.println("frame set invisible");
                JFrame newFrame = factoryView.getMainFrame();
                newFrame.setVisible(true);
                System.out.println("frame set visible");
            }
        });

        for(int i = 0;i < items.size();i++){
            JTextArea temp = vendView.getStockTextArea(i);
            this.vendView.setItemButtonListener(i,new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    String holder = temp.getText();
                    holder = updateStock(holder,-1);
                    temp.setText(holder);
                }
            });
        }

        this.maintView.setBackButtonListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                 maintView.setVisible(false);
                 System.out.println("frame set invisible");
                 JFrame newFrame = factoryView.getMainFrame();
                 newFrame.setVisible(true);
                 System.out.println("frame set visible");
            }
        });

        this.maintView.setStockItemButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maintView.setVisible(false);
                System.out.println("frame set invisible");
                stockItemView.setVisible(true);
                System.out.println("frame set invisible");
            }
        });

        this.maintView.setSetPriceButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maintView.setVisible(false);
                System.out.println("frame set invisible");
                setItemPriceView.setVisible(true);
                System.out.println("frame set invisible");
            }
        });

        this.maintView.setTransHistoryButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maintView.setVisible(false);
                System.out.println("frame set invisible");
                transactionHistoryView.setVisible(true);
                System.out.println("frame set invisible");
            }
        });
    }

    private String updateStock(String string,int j){
        int i = Integer.parseInt(string);
        i = i + j;
        String holder = String.valueOf(i);
        return holder;
    }

    //Maintenance Features

}