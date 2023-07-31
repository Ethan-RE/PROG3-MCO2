import VendingMachine.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;

public class FactoryController {
    private VendingMachine vendingMachine;
    private SpecialVendingMachine specialVendingMachine;
    private FactoryView factoryView;
    protected FactoryModel factoryModel;
    private Frame2 frame2;
    private Frame3 frame3;
    private VendView vendView;
    private SpecialVendView specialVendView;
    private MaintView maintView;
    private SetItemPriceFrame setItemPriceFrame;
    private StockItemFrame stockItemView;
    private StockMoneyFrame stockMoneyView;
    private SetItemPriceFrame setItemPriceView;
    private TransactionHistoryFrame transactionHistoryView;
    private int selectedItemIndex;
    private List<Integer> denominations;
    private ArrayList<String> items,optionItems,currentItems,currentItems2;
    private ArrayList<Double> prices, calories,values;
    private ArrayList<Integer> itemStocks,moneyStocks;
    private ArrayList<String> transactions;

    public FactoryController (FactoryModel model, FactoryView view) {
        this.vendingMachine = new VendingMachine();
        this.specialVendingMachine = new SpecialVendingMachine();
        this.factoryView = view;
        this.factoryModel = model;
        this.frame2 = new Frame2();
        this.frame3 = new Frame3();

        this.currentItems = new ArrayList<>();
        this.currentItems2 = new ArrayList<>();
        this.selectedItemIndex = -1;
        this.denominations = List.of(1,5,10,20,50,100,200,500,1000);

        this.items = this.vendingMachine.getItemNames();
        this.prices = this.vendingMachine.getItemPrice();
        this.calories = this.vendingMachine.getItemCalories();
        this.values = this.vendingMachine.getMoneyValue();
        this.itemStocks = this.vendingMachine.getItemStock();
        this.moneyStocks = this.vendingMachine.getMoneyStock();
        this.transactions = this.vendingMachine.getTransactionHistory();
        this.optionItems = this.specialVendingMachine.getOptionNames();

        this.vendView = new VendView(items,prices,calories,itemStocks);
        this.specialVendView = new SpecialVendView(items,prices,calories,itemStocks,optionItems);
        this.maintView = new MaintView();
        this.stockItemView = new StockItemFrame(items,itemStocks);
        this.setItemPriceView = new SetItemPriceFrame(items, prices);
        this.stockItemView = new StockItemFrame(items,itemStocks);
        this.transactionHistoryView = new TransactionHistoryFrame(transactions);
        this.stockMoneyView = new StockMoneyFrame(values,moneyStocks);

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
                System.out.println("Creating Special Machine");
                factoryModel.createSVM();
                specialVendingMachine = factoryModel.getSVM();
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

        for (int i = 0; i < items.size(); i++) {
            JTextArea temp = vendView.getDisplayPriceTextArea(i);
            JTextArea toUpdate = vendView.getPriceTextArea();
            String priceText = temp.getText();
            double addPrice = Double.parseDouble(priceText);

            int currentItemIndex = i;

            this.vendView.setItemButtonListener(i, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int newPrice = (int) Math.round(addPrice);
                    String updatedPrice = toUpdate.getText();
                    updatedPrice = updateStock(updatedPrice, newPrice);
                    toUpdate.setText(updatedPrice);

                    // Store the index of the selected item
                    selectedItemIndex = currentItemIndex;

                    // Add the selected item to the currentItems list
                    if (selectedItemIndex >= 0 && selectedItemIndex < items.size()) {
                        String selectedItem = items.get(selectedItemIndex);
                        currentItems.add(selectedItem);
                        System.out.println(selectedItem);
                    }
                }
            });
        }

        for (int i = 0; i < denominations.size(); i++) {
            int money = denominations.get(i);
            this.vendView.setMoneyButtonListener(i, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JTextArea temp = vendView.getMoneyTextArea();
                    String holder = temp.getText();

                    holder = updateStock(holder,money);

                    temp.setText(holder);
                }
            });
        }

        this.vendView.setBuyButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Iterate through the currentItems list and update the stock for each item
                for (int i = 0; i < currentItems.size(); i++) {
                    JTextArea temp = vendView.getStockTextArea(i);
                    for(int j = 0;j < items.size();j++){
                        String currentItem = currentItems.get(i);
                        String possibleItem = items.get(j);
                        if (currentItem.equals(possibleItem)) {
                            // Update the stock for the current item
                            int currentStock = itemStocks.get(j);
                            currentStock -= 1;
                            itemStocks.set(j, currentStock);
                            temp.setText(Integer.toString(currentStock));
                            System.out.println(currentItem + " bought");
                        }
                    }
                }

                // Update the money
                JTextArea priceTA = vendView.getPriceTextArea();
                JTextArea moneyTA = vendView.getMoneyTextArea();
                String priceS = priceTA.getText();
                String moneyS = moneyTA.getText();
                int price = Integer.parseInt(priceS);
                price = price * -1;
                moneyS = updateStock(moneyS, price);
                priceS = updateStock(priceS, price);
                priceTA.setText(priceS);
                moneyTA.setText(moneyS);

                // Clear the currentItems list after the purchase is completed
                currentItems.clear();
            }
        });

        this.specialVendView.setBackButtonListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JFrame frame = specialVendView.getRVendFrame();
                frame.setVisible(false);
                System.out.println("frame set invisible");
                JFrame newFrame = factoryView.getMainFrame();
                newFrame.setVisible(true);
                System.out.println("frame set visible");
            }
        });

        for (int i = 0; i < items.size(); i++) {
            JTextArea temp = specialVendView.getDisplayPriceTextArea(i);
            JTextArea toUpdate = specialVendView.getPriceTextArea();
            String priceText = temp.getText();
            double addPrice = Double.parseDouble(priceText);

            int currentItemIndex = i;

            this.specialVendView.setItemButtonListener(i, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int newPrice = (int) Math.round(addPrice);
                    String updatedPrice = toUpdate.getText();
                    updatedPrice = updateStock(updatedPrice, newPrice);
                    toUpdate.setText(updatedPrice);

                    // Store the index of the selected item
                    selectedItemIndex = currentItemIndex;

                    // Add the selected item to the currentItems list
                    if (selectedItemIndex >= 0 && selectedItemIndex < items.size()) {
                        String selectedItem = items.get(selectedItemIndex);
                        currentItems.add(selectedItem);
                    }
                }
            });
        }

        for (int i = 0; i < denominations.size(); i++) {
            int money = denominations.get(i);
            this.specialVendView.setMoneyButtonListener(i, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JTextArea temp = specialVendView.getMoneyTextArea();
                    String holder = temp.getText();

                    holder = updateStock(holder,money);

                    temp.setText(holder);
                }
            });
        }

        this.specialVendView.setBuyButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Iterate through the currentItems list and update the stock for each item
                for (int i = 0; i < currentItems.size(); i++) {
                    JTextArea temp = specialVendView.getStockTextArea(i);
                    String currentItem = currentItems.get(i);

                    // Find the index of the current item in the items list
                    int itemIndex = items.indexOf(currentItem);
                    if (itemIndex != -1) {
                        // Update the stock for the current item
                        int currentStock = itemStocks.get(itemIndex);
                        currentStock -= 1;
                        itemStocks.set(itemIndex, currentStock);
                        temp.setText(Integer.toString(currentStock));
                    }
                }

                // Update the money
                JTextArea priceTA = specialVendView.getPriceTextArea();
                JTextArea moneyTA = specialVendView.getMoneyTextArea();
                String priceS = priceTA.getText();
                String moneyS = moneyTA.getText();
                int price = Integer.parseInt(priceS);
                price = price * -1;
                moneyS = updateStock(moneyS, price);
                priceS = updateStock(priceS, price);
                priceTA.setText(priceS);
                moneyTA.setText(moneyS);

                // Clear the currentItems list after the purchase is completed
                currentItems.clear();
            }
        });

        this.specialVendView.setSpecialItemButtonListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JButton temp = specialVendView.getSpecialItem();
                temp.setVisible(false);
                JPanel holder = specialVendView.getSpecialPanel();
                holder.setVisible(true);
            }
        });

        this.specialVendView.setCloseSpecialButtonListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JPanel temp = specialVendView.getSpecialPanel();
                temp.setVisible(false);
                JButton holder = specialVendView.getSpecialItem();
                holder.setVisible(true);
            }
        });

        this.maintView.setBackButtonListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                 maintView.setVisible(false);
                 System.out.println("frame set invisible");
                 JFrame newFrame = factoryView.getMainFrame();
                 newFrame.setVisible(true);
                 System.out.println("frame set visible");
            }
        });

        this.setItemPriceView.setInputTextListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("entering input");
                JTextField temp = setItemPriceView.getInput();
                String holder = temp.getText();
                double updatePrice = Double.parseDouble(holder);


                // Update the price for the selected item
                if (selectedItemIndex >= 0 && selectedItemIndex < items.size()) {
                    prices.set(selectedItemIndex, updatePrice);
                    String newPrice = String.valueOf(updatePrice);
                    JTextArea updater = setItemPriceView.getPrice(selectedItemIndex);
                    updater.setText(newPrice);
                    temp.setVisible(false);
                    System.out.println("price updated");
                }
            }
        });

        for(int i = 0;i < items.size();i++) {
            int currentItemIndex = i;
            this.setItemPriceView.setItemButtonListener(i, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JPanel temp = setItemPriceView.getInputPanel();
                    temp.setVisible(true);
                    selectedItemIndex = currentItemIndex;

                    // Add the selected item to the currentItems list
                    if (selectedItemIndex >= 0 && selectedItemIndex < items.size()) {
                        String selectedItem = items.get(selectedItemIndex);
                        currentItems2.add(selectedItem);
                    }
                    System.out.println("textbox opened");
                }
            });
        }

        this.setItemPriceView.setBackButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setItemPriceView.setVisible(false);
                maintView.setVisible(true);
            }
        });

        for(int i = 0; i < items.size(); i++) {
            JTextArea temp = stockItemView.getStock(i);
            int currentItem = i;
            this.stockItemView.setItemButtonListener(i, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String holder = temp.getText();
                    int j = 1;
                    holder = updateStock(holder,j);
                    temp.setText(holder);
                    int k = Integer.parseInt(holder);
                    itemStocks.set(currentItem,k);
                }
            });
        }

        this.stockItemView.setBackButtonListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                stockItemView.setVisible(false);
                maintView.setVisible(true);
            }
        });

        for(int i = 0; i < denominations.size(); i++) {
            JTextArea temp = stockMoneyView.getStock(i);
            this.stockMoneyView.setItemButtonListener(i, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String holder = temp.getText();
                    int j = 1;
                    holder = updateStock(holder,j);
                    temp.setText(holder);
                }
            });
        }

        this.stockMoneyView.setBackButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stockMoneyView.setVisible(false);
                System.out.println("frame set invisible");
                maintView.setVisible(true);
                System.out.println("frame set invisible");
            }
        });

        this.transactionHistoryView.setBackButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                transactionHistoryView.setVisible(false);
                System.out.println("frame set invisible");
                maintView.setVisible(true);
                System.out.println("frame set invisible");
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

        this.maintView.setStockMoneyButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maintView.setVisible(false);
                System.out.println("frame set invisible");
                stockMoneyView.setVisible(true);
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
}