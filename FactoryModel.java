import VendingMachine.*;
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

    public void buyItemButton(VendView vendView,ArrayList<String> currentItems,ArrayList<String> items,ArrayList<Integer> itemStocks){
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

    public void specialVendConfirmButton(SpecialVendView specialVendView,SpecialVendingMachine specialVendingMachine,ArrayList<String> optionItems,ArrayList<String> items){
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

    public void setStockItemButton(JTextArea temp,ArrayList<Integer> itemStocks,int currentItem,ItemStack currentStack){
        String holder = temp.getText();
        int j = 1;
        holder = updateStock(holder,j);
        temp.setText(holder);
        int k = Integer.parseInt(holder);
        itemStocks.set(currentItem,k);
        currentStack.pushItem();
    }

    public void setStockMoneyButton(JTextArea temp){
        String holder = temp.getText();
        int j = 1;
        holder = updateStock(holder,j);
        temp.setText(holder);
    }

    public VendingMachine getRVM(){
        return this.vm;
    }
    public SpecialVendingMachine getSVM() {return this.svm; }

    private String updateStock(String string,int j){
        int i = Integer.parseInt(string);
        i = i + j;
        String holder = String.valueOf(i);
        return holder;
    }
}