import VendingMachine.*;
import VendingMachine.SpecialVendingMachine;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;
/**
 * A class representing the vending machine factory model
 */
public class FactoryModel {
    private VendingMachine vm;
    private SpecialVendingMachine svm;

    //VM creation

    /**
     * Creates a vending machine for the factory
     */
    public void createVM() {
        this.vm = new VendingMachine();
    }

    public void createSVM() { this.svm = new SpecialVendingMachine(); }
    //VM testing

    /**
     * Tests current vending machine in factory
     */
    public void vendingItemButton(double addPrice,JTextArea toUpdate,ArrayList<String> items,int selectedItemIndex,int currentItemIndex,ArrayList<String> currentItems){
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

    public void vendingMoneyButton(JTextArea moneyText,int money){
        JTextArea temp = moneyText;
        String holder = temp.getText();

        holder = updateStock(holder,money);

        temp.setText(holder);
    }

    public void buyItemButton(boolean isRegular,boolean isSpecial, SpecialVendView specialVendView, VendView vendView,ArrayList<String> currentItems,ArrayList<String> items,ArrayList<Integer> itemStocks,VendingMachine vendingMachine,SpecialVendingMachine specialVendingMachine){
        // Iterate through the currentItems list and update the stock for each item
        for (int i = 0; i < currentItems.size(); i++) {
            JTextArea temp = new JTextArea();
            if(isRegular)
                temp = vendView.getStockTextArea(i);
            else if(isSpecial)
                temp = specialVendView.getStockTextArea(i);
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
        JTextArea priceTA = new JTextArea();
        JTextArea moneyTA = new JTextArea();
        if(isRegular) {
            priceTA = vendView.getPriceTextArea();
            moneyTA = vendView.getMoneyTextArea();
        }
        else if(isSpecial){
            priceTA = specialVendView.getPriceTextArea();
            moneyTA = specialVendView.getMoneyTextArea();
        }
        String priceS = priceTA.getText();
        String moneyS = moneyTA.getText();
        int price = Integer.parseInt(priceS);
        double dPrice = price;
        double dMoney = Double.parseDouble(moneyS);

        CashRegister cashRegister = new CashRegister();
        ArrayList<Transaction> transHistory = new ArrayList<>();

        if(isRegular){
            cashRegister = vendingMachine.getCashHander();
            transHistory = vendingMachine.getTransHistory();
        }
        else if (isSpecial){
            cashRegister = specialVendingMachine.getCashHander();
            transHistory = specialVendingMachine.getTransHistory();
        }

        ArrayList<MoneyStack> held = cashRegister.calculateChange(dPrice,dMoney);

        for(int i = 0;i < currentItems.size();i++){
            ArrayList<ItemStack> hold = new ArrayList<>();
            Double totalChange = 0.0;
            if(isRegular){
                hold = vendingMachine.getItemTypes();
            }
            else if (isSpecial){
                hold = specialVendingMachine.getItemTypes();
            }
            Item item = hold.get(i).getItem();

            for(int j = 0;j < held.size();j++){
                Double change = held.get(i).getValue();
                totalChange = totalChange + change;
            }

            Transaction temporary = new Transaction(dPrice,totalChange,item);
            transHistory.add(temporary);
        }

        ArrayList<Integer> hold = cashRegister.getMoneyStock();
        vendingMachine.stockMoney(hold);

        price = price * -1;
        moneyS = updateStock(moneyS, price);
        priceS = updateStock(priceS, price);
        priceTA.setText(priceS);
        moneyTA.setText(moneyS);
        // Clear the currentItems list after the purchase is completed
        currentItems.clear();
    }

    public void specialVendBeefButton(ArrayList<String> optionItems,JTextArea basket){
        optionItems.add("Beef");
        basket.append("Beef");
    }

    public void specialVendSauceButton(ArrayList<String> optionItems,SpecialVendView specialVendView,boolean a,boolean b,String sauce){
        if(!a && !b){
            System.out.println("add sauce");
            JTextArea basket = specialVendView.getBasket();
            optionItems.add(sauce);
            basket.append(sauce);
            a = true;
        }
        else if(a){
            System.out.println("remove sauce");
            JTextArea basket = specialVendView.getBasket();
            int j = -1;
            for(int i = 0;i < optionItems.size();i++){
                String temp = optionItems.get(i);
                if(temp.equals(sauce)){
                    j = i;
                    break;
                }
            }
            optionItems.remove(j);
            String temp = basket.getText();
            temp = temp.replace(sauce,"");
            basket.setText(temp);
            a = false;
        }
    }

    public void specialVendExtraButton(ArrayList<String> optionItems,JTextArea basket,String item){
        optionItems.add(item);
        basket.append(item);
    }

    public void specialVendConfirmButton(SpecialVendView specialVendView,SpecialVendingMachine specialVendingMachine,ArrayList<String> optionItems,ArrayList<String> items,JTextArea clear){
        JPanel specialPanel = specialVendView.getSpecialPanel();
        specialPanel.setVisible(false);
        JTextArea cooking = specialVendView.getCooking();
        cooking.setVisible(true);
        int delay = 5000;
        ArrayList<ItemStack> inventory = specialVendingMachine.getItemTypes();
        ArrayList<Item> ingredients = new ArrayList<>();

        for(int i = 0;i < optionItems.size();i++){
            String temp = optionItems.get(i);
            for(int j = 0; j < items.size();j++){
                String holder = items.get(j);
                if(holder.equals(temp)){
                    Item held = inventory.get(j).getItem();
                    ingredients.add(held);
                }
            }
        }

        clear.setText("");

        Timer timer = new Timer(delay, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                cooking.setVisible(false);
                JButton specialItem = specialVendView.getSpecialItem();
                specialItem.setVisible(true);
                specialVendingMachine.cook(ingredients);
                JTextArea hold = specialVendView.getPriceTextArea();
                String temp = hold.getText();
                for(int i = 0;i < ingredients.size();i++){
                    Item held = ingredients.get(i);
                    double j = held.getPrice();
                    int x = (int) Math.round(j);
                    temp = updateStock(temp,x);
                    hold.setText(temp);
                }
            }
        });

        timer.setRepeats(false);
        timer.start();
    }

    public void inputTextListener(SetItemPriceFrame setItemPriceView,int selectedItemIndex,ArrayList<String> items,ArrayList<Double> prices){
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

    public void setItemPriceButton(SetItemPriceFrame setItemPriceView,int selectedItemIndex,int currentItemIndex,ArrayList<String> items,ArrayList<String> currentItems2){
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

    public void setStockItemButton(JTextArea temp,ArrayList<Integer> itemStocks,int currentItem,ItemStack currentStack,boolean isRegular,boolean isSpecial,VendingMachine vendingMachine,SpecialVendingMachine sVendingMachine){
        String holder = temp.getText();
        holder = updateStock(holder,1);
        temp.setText(holder);
        int k = Integer.parseInt(holder);
        itemStocks.set(currentItem,k);
        currentStack.pushItem();
        if(isRegular)
            vendingMachine.stockItem(currentItem,1);
        else if(isSpecial)
            sVendingMachine.stockItem(currentItem,1);
    }

    public void setStockMoneyButton(boolean isRegular,boolean isSpecial,VendingMachine vendingMachine,SpecialVendingMachine specialVendingMachine,ArrayList<Integer> money,ArrayList<JTextArea> temp){
        CashRegister cashRegister = new CashRegister();

        if(isRegular){
            cashRegister = vendingMachine.getCashHander();
        }
        else if (isSpecial){
            cashRegister = specialVendingMachine.getCashHander();
        }

        cashRegister.stockInternal(money);

        for(int i = 0;i < temp.size();i++) {
            JTextArea hold = temp.get(i);
            String holder = hold.getText();
            int j = 1;
            holder = updateStock(holder, j);
            hold.setText(holder);
        }
    }

    public void setCollectMoneyButton(boolean isRegular,boolean isSpecial,VendingMachine vendingMachine,SpecialVendingMachine specialVendingMachine,CollectMoneyFrame collectMoneyView){
        ArrayList<Money> temp = new ArrayList<>();

        System.out.println("dispensing money");

        if(isRegular){
            System.out.println("Regukar");
            temp = vendingMachine.collectMoney();
        }
        else if(isSpecial){
            System.out.println("speecial");
            temp = specialVendingMachine.collectMoney();
        }

        ArrayList<Double> holder = new ArrayList<>();

        for(int i = 0;i < temp.size();i++){
            holder.add(temp.get(i).getValue());
        }

        JTextArea hold = collectMoneyView.getCollected();
        for(int i = 0;i < holder.size();i++) {
            String held = String.valueOf(holder.get(i));
            System.out.println("Collected " + held);
            hold.append(held);
        }
    }

    public VendingMachine getRVM(ArrayList<String> items,ArrayList<Double> prices,ArrayList<Double> calories,ArrayList<Double> values,ArrayList<Integer> itemStocks, ArrayList<Integer> moneyStocks,ArrayList<String> transactions,
                                 VendView vendView,SpecialVendView specialVendView,MaintView maintView,StockItemFrame stockItemView,SetItemPriceFrame setItemPriceView,TransactionHistoryFrame transactionHistoryView,StockMoneyFrame stockMoneyView){
        items = this.vm.getItemNames();
        prices = this.vm.getItemPrice();
        calories = this.vm.getItemCalories();
        values = this.vm.getMoneyValue();
        itemStocks = this.vm.getItemStock();
        moneyStocks = this.vm.getMoneyStock();
        transactions = this.vm.getTransactionHistory();
        vendView = new VendView(items,prices,calories,itemStocks);
        specialVendView = new SpecialVendView(items,prices,calories,itemStocks);
        maintView = new MaintView();
        stockItemView = new StockItemFrame(items,itemStocks);
        setItemPriceView = new SetItemPriceFrame(items, prices);
        transactionHistoryView = new TransactionHistoryFrame(transactions);
        stockMoneyView = new StockMoneyFrame(values,moneyStocks);
        return this.vm;
    }
    public SpecialVendingMachine getSVM(ArrayList<String> items,ArrayList<Double> prices,ArrayList<Double> calories,ArrayList<Double> values,ArrayList<Integer> itemStocks, ArrayList<Integer> moneyStocks,ArrayList<String> transactions,
                                        VendView vendView,SpecialVendView specialVendView,MaintView maintView,StockItemFrame stockItemView,SetItemPriceFrame setItemPriceView,TransactionHistoryFrame transactionHistoryView,StockMoneyFrame stockMoneyView) {
        items = this.svm.getItemNames();
        prices = this.svm.getItemPrice();
        calories = this.svm.getItemCalories();
        values = this.svm.getMoneyValue();
        itemStocks = this.svm.getItemStock();
        moneyStocks = this.svm.getMoneyStock();
        transactions = this.svm.getTransactionHistory();
        vendView = new VendView(items,prices,calories,itemStocks);
        specialVendView = new SpecialVendView(items,prices,calories,itemStocks);
        maintView = new MaintView();
        stockItemView = new StockItemFrame(items,itemStocks);
        setItemPriceView = new SetItemPriceFrame(items, prices);
        transactionHistoryView = new TransactionHistoryFrame(transactions);
        stockMoneyView = new StockMoneyFrame(values,moneyStocks);
        return this.svm; }

    private String updateStock(String string,int j){
        int i = Integer.parseInt(string);
        i = i + j;
        String holder = String.valueOf(i);
        return holder;
    }
}