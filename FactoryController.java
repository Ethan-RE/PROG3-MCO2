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
    private CollectMoneyFrame collectMoneyView;
    private String newName;
    private double newPrice,newCalories;
    private int selectedItemIndex;
    private boolean hasSweet,hasSpicy,isEnough,isRegular,isSpecial;
    private List<Integer> denominations;
    private List<Observer> observers;
    private ArrayList<String> items,displayItems,optionItems,currentItems,currentItems2;
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
        this.optionItems = new ArrayList<>();
        this.selectedItemIndex = -1;
        this.observers = new ArrayList<>();
        this.denominations = List.of(1,5,10,20,50,100,200,500,1000);
        this.hasSweet = false;
        this.hasSpicy = false;
        this.isRegular = false;
        this.isSpecial = false;

        this.displayItems = this.specialVendingMachine.getOptionNames();
        this.items = this.specialVendingMachine.getItemNames();
        this.prices = this.specialVendingMachine.getItemPrice();
        this.calories = this.specialVendingMachine.getItemCalories();
        this.values = this.vendingMachine.getMoneyValue();
        this.itemStocks = this.specialVendingMachine.getItemStock();
        this.moneyStocks = this.specialVendingMachine.getMoneyStock();
        this.transactions = this.vendingMachine.getTransactionHistory();

        this.vendView = new VendView(displayItems,prices,calories,itemStocks);
        this.specialVendView = new SpecialVendView(items,prices,calories,itemStocks);
        this.maintView = new MaintView();
        this.stockItemView = new StockItemFrame(items,itemStocks);
        this.setItemPriceView = new SetItemPriceFrame(items, prices);
        this.stockItemView = new StockItemFrame(items,itemStocks);
        this.transactionHistoryView = new TransactionHistoryFrame(transactions);
        this.stockMoneyView = new StockMoneyFrame(values,moneyStocks);
        this.collectMoneyView = new CollectMoneyFrame();

        addObserver(vendView);
        addObserver(specialVendView);
        addObserver(stockItemView);
        addObserver(setItemPriceView);
        addObserver(stockItemView);
        addObserver(stockMoneyView);
        addObserver(transactionHistoryView);

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
                vendingMachine = factoryModel.getRVM(FactoryController.this.items,FactoryController.this.prices,FactoryController.this.calories,FactoryController.this.values,FactoryController.this.itemStocks,FactoryController.this.moneyStocks,FactoryController.this.transactions,
                        FactoryController.this.vendView,FactoryController.this.specialVendView,FactoryController.this.maintView,FactoryController.this.stockItemView,FactoryController.this.setItemPriceView,FactoryController.this.transactionHistoryView,FactoryController.this.stockMoneyView);
                FactoryController.this.isRegular = true;
                FactoryController.this.isSpecial = false;
                notifyObservers();
            }
        });

        this.frame2.setSpecialButtonListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Creating Special Machine");
                factoryModel.createSVM();
                specialVendingMachine = factoryModel.getSVM(FactoryController.this.items,FactoryController.this.prices,FactoryController.this.calories,FactoryController.this.values,FactoryController.this.itemStocks,FactoryController.this.moneyStocks,FactoryController.this.transactions,
                        FactoryController.this.vendView,FactoryController.this.specialVendView,FactoryController.this.maintView,FactoryController.this.stockItemView,FactoryController.this.setItemPriceView,FactoryController.this.transactionHistoryView,FactoryController.this.stockMoneyView);
                FactoryController.this.isSpecial = true;
                FactoryController.this.isRegular = false;
                notifyObservers();
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
                JFrame newFrame = new JFrame();
                if(isRegular) {
                    newFrame = vendView.getRVendFrame();
                }
                else if (isSpecial){
                    newFrame = specialVendView.getRVendFrame();
                }
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

        for (int i = 0; i < displayItems.size(); i++) {
            JTextArea temp = vendView.getDisplayPriceTextArea(i);
            JTextArea toUpdate = vendView.getPriceTextArea();
            String priceText = temp.getText();
            double addPrice = Double.parseDouble(priceText);

            int currentItemIndex = i;

            this.vendView.setItemButtonListener(i, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    factoryModel.vendingItemButton(addPrice,toUpdate,items,selectedItemIndex,currentItemIndex,currentItems);
                }
            });
        }

        for (int i = 0; i < denominations.size(); i++) {
            int money = denominations.get(i);
            JTextArea temp = vendView.getMoneyTextArea();
            this.vendView.setMoneyButtonListener(i, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    factoryModel.vendingMoneyButton(temp,money);
                }
            });
        }

        this.vendView.setBuyButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                factoryModel.buyItemButton(isRegular,isSpecial,specialVendView,vendView,currentItems,items,itemStocks,vendingMachine,specialVendingMachine);
                notifyObservers();
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
                    factoryModel.vendingItemButton(addPrice,toUpdate,items,selectedItemIndex,currentItemIndex,currentItems);
                }
            });
        }

        for (int i = 0; i < denominations.size(); i++) {
            int money = denominations.get(i);
            JTextArea temp = specialVendView.getMoneyTextArea();
            this.specialVendView.setMoneyButtonListener(i, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    factoryModel.vendingMoneyButton(temp,money);
                }
            });
        }

        this.specialVendView.setBuyButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                factoryModel.buyItemButton(isRegular,isSpecial,specialVendView,vendView,currentItems,items,itemStocks,vendingMachine,specialVendingMachine);
                notifyObservers();
            }
        });

        this.specialVendView.setSpecialItemButtonListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JButton temp = specialVendView.getSpecialItem();
                temp.setVisible(false);
                JPanel holder = specialVendView.getSpecialPanel();
                holder.setVisible(true);
                JTextArea basket = specialVendView.getBasket();
                basket.setVisible(true);
            }
        });

        this.specialVendView.setBeefButtonListener(new ActionListener(){
            JTextArea basket = specialVendView.getBasket();
            public void actionPerformed(ActionEvent e){
                factoryModel.specialVendBeefButton(optionItems,basket);
            }
        });

        this.specialVendView.setSpicySButtonListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                factoryModel.specialVendSauceButton(optionItems,specialVendView,hasSpicy,hasSweet,"Spicy sauce");
            }
        });

        this.specialVendView.setSweetSButtonListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                factoryModel.specialVendSauceButton(optionItems,specialVendView,hasSweet,hasSpicy,"Sweet sauce");
            }
        });

        this.specialVendView.setPeasButtonListener(new ActionListener(){
            JTextArea basket = specialVendView.getBasket();
            public void actionPerformed(ActionEvent e){
                factoryModel.specialVendExtraButton(optionItems,basket,"Peas");
            }
        });

        this.specialVendView.setEggsButtonListener(new ActionListener(){
            JTextArea basket = specialVendView.getBasket();
            public void actionPerformed(ActionEvent e){
                factoryModel.specialVendExtraButton(optionItems,basket,"Eggs");
            }
        });

        this.specialVendView.setConfirmBasketButtonListener(new ActionListener(){
            JTextArea clear = specialVendView.getBasket();
            public void actionPerformed(ActionEvent e){
                factoryModel.specialVendConfirmButton(specialVendView,specialVendingMachine,optionItems,items,clear);
                notifyObservers();
            }
        });

        this.specialVendView.setCloseSpecialButtonListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JTextArea clear = specialVendView.getBasket();
                clear.setText("");
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
                factoryModel.inputTextListener(setItemPriceView,selectedItemIndex,items,prices);
            }
        });

        for(int i = 0;i < items.size();i++) {
            int currentItemIndex = i;
            this.setItemPriceView.setItemButtonListener(i, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    factoryModel.setItemPriceButton(setItemPriceView,selectedItemIndex,currentItemIndex,items,currentItems2);
                    notifyObservers();
                }
            });
        }

        this.setItemPriceView.setBackButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setItemPriceView.setVisible(false);
                maintView.setVisible(true);
            }
        });

        this.stockMoneyView.setRestockButtonListener(new ActionListener() {
            ArrayList<JTextArea> hold = stockMoneyView.getStock();

            public void actionPerformed(ActionEvent e) {
                ArrayList<Integer> money = new ArrayList<>();
                for(int i = 0;i < hold.size();i++){
                    money.add(1);
                }
                factoryModel.setStockMoneyButton(isRegular,isSpecial,vendingMachine,specialVendingMachine,money,hold);
                money.clear();
            }
        });

        this.stockItemView.setBackButtonListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                stockItemView.setVisible(false);
                maintView.setVisible(true);
            }
        });

        for(int i = 0; i < items.size(); i++) {
            JTextArea temp = stockItemView.getStock(i);
            ArrayList<ItemStack> itemTypes = specialVendingMachine.getItemTypes();
            if(isRegular)
                itemTypes = vendingMachine.getItemTypes();
            else if (isSpecial)
                itemTypes = specialVendingMachine.getItemTypes();
            int currentItem = i;
            ItemStack currentStack = itemTypes.get(i);
            this.stockItemView.setItemButtonListener(i, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    factoryModel.setStockItemButton(temp,itemStocks,currentItem,currentStack,isRegular,isSpecial,vendingMachine,specialVendingMachine);
                    notifyObservers();
                }
            });
        }

        this.stockItemView.setAddNewItemButtonListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JButton temp = stockItemView.getAddNewItemButton();
                temp.setVisible(false);
                JPanel holder = stockItemView.getInputPanel();
                holder.setVisible(true);
            }
        });

        this.stockItemView.setNameTextListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get user input for new item name
                JTextField name = stockItemView.getNewName();
                newName = name.getText();
            }
        });

        this.stockItemView.setPriceTextListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get user input for new item price
                JTextField price = stockItemView.getNewPrice();
                String priceString = price.getText();
                try {
                    newPrice = Double.parseDouble(priceString);
                } catch (NumberFormatException ex) {
                    // Handle invalid input if needed
                    // For example, show an error message to the user
                }
            }
        });

        this.stockItemView.setCaloriesTextListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get user input for new item calories
                JTextField calories = stockItemView.getNewCalories();
                String caloriesString = calories.getText();
                try {
                    newCalories = Double.parseDouble(caloriesString);
                } catch (NumberFormatException ex) {
                    // Handle invalid input if needed
                    // For example, show an error message to the user
                }
            }
        });

        this.stockItemView.setFinishButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add the new item to the vending machine
                Item item = new Item(newName, newPrice, newCalories);
                vendingMachine.addNewItemStack(item);

                // Notify the observers about the changes
                notifyObservers();

                // Hide the input panel and show the "Add New Item" button again
                JPanel holder = stockItemView.getInputPanel();
                holder.setVisible(false);
                JButton temp = stockItemView.getAddNewItemButton();
                temp.setVisible(true);
            }
        });



        this.stockMoneyView.setBackButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stockMoneyView.setVisible(false);
                System.out.println("frame set invisible");
                maintView.setVisible(true);
                System.out.println("frame set invisible");
            }
        });

        this.collectMoneyView.setCollectMoneyButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                factoryModel.setCollectMoneyButton(isRegular,isSpecial,vendingMachine,specialVendingMachine,collectMoneyView);
            }
        });

        this.collectMoneyView.setBackButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextArea temp = collectMoneyView.getCollected();
                temp.setText("");
                collectMoneyView.setVisible(false);
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

        this.maintView.setCollectMoneyButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maintView.setVisible(false);
                System.out.println("frame set invisible");
                collectMoneyView.setVisible(true);
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

    // Method to add observers to the list
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // Method to remove observers from the list
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    // Method to notify all observers of changes
    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}