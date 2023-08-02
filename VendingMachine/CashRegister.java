package VendingMachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * A class representing a cash register
 */
public class CashRegister{

    private double price;
    private double value;
    private double change;

    private MoneyStack thousands;
    private MoneyStack fiveHundreds;
    private MoneyStack twoHundreds;
    private MoneyStack hundreds;
    private MoneyStack fifties;
    private MoneyStack twenties;
    private MoneyStack tens;
    private MoneyStack fives;
    private MoneyStack ones;

    private ArrayList<MoneyStack> internalBank;

    //CashRegister constructor
    public CashRegister() {
        this.thousands = new MoneyStack(1000);
        this.fiveHundreds = new MoneyStack(500);
        this.twoHundreds = new MoneyStack(200);
        this.hundreds = new MoneyStack(100);
        this.fifties = new MoneyStack(50);
        this.twenties = new MoneyStack(20);
        this.tens = new MoneyStack(10);
        this.fives = new MoneyStack(5);
        this.ones = new MoneyStack(1);

        this.internalBank = new ArrayList<MoneyStack>();
        this.internalBank.add(ones);
        this.internalBank.add(fives);
        this.internalBank.add(tens);
        this.internalBank.add(twenties);
        this.internalBank.add(fifties);
        this.internalBank.add(hundreds);
        this.internalBank.add(twoHundreds);
        this.internalBank.add(fiveHundreds);
        this.internalBank.add(thousands);
    }

    /**
     * Calculates change to be given based on given price and value
     * @param price - price of item bought
     * @param value - value given
     * @return array of MoneyStack given as change
     */
    public ArrayList<MoneyStack> calculateChange(double price, double value) {
        System.out.println("cahnge calc");
        this.change = price-value;
        this.price = price;
        this.value = value;
        ArrayList<MoneyStack> change = new ArrayList<>();
        for(int i = 0 ; i<this.internalBank.size() ; i++) {
            change.add(new MoneyStack(this.internalBank.get(i).getNumMoney()));
            while(value>this.internalBank.get(i).getValue()&&this.internalBank.get(i).getNumMoney()>0) {
                value=value-this.internalBank.get(i).getValue();
                this.internalBank.get(i).popMoney();
                change.get(i).pushMoney();
            }
        }

        if (value>0) {
            for (int i = 0 ; i<change.size() ; i++) {
                for (int j = 0 ; j<change.get(i).getNumMoney() ; j++) {
                    this.internalBank.get(i).pushMoney();
                }
            }
            return change;
        }
        
        return change;
    }

    //Restock the internal bank with given denominations

    /**
     * Restock the internal bank with the given denominations
     * @param restock - array of amount of each bill there is, from 1-1000
     */
    public void stockInternal(ArrayList<Integer> restock) {
        for (int i = 0 ; i<this.internalBank.size() ; i++) {
            for (int j = 0 ; j<restock.get(i) ; j++) {
                this.internalBank.get(i).pushMoney();
            }
        }
    }

    public ArrayList<Money> collectMoney() {
        ArrayList<Money> monies = new ArrayList<>();
        int i = 0;
        for(MoneyStack moneystack : this.internalBank) {
            for(int j = 0 ; j<moneystack.getNumMoney() ; j++) {
                monies.add(moneystack.popMoney());
            }
            i++;
        }
        return monies;
    }

    //Getter for change
    public double getChange() {return this.change;}

    public ArrayList<Integer> getMoneyStock() {
        ArrayList<Integer> moneyStock = new ArrayList<>();
        for(MoneyStack ms : this.internalBank) {
            moneyStock.add(ms.getNumMoney());
        }
        return moneyStock;
    }
    
    public ArrayList<Double> getMoneyValue() {
        ArrayList<Double> moneyVal = new ArrayList<>();
        for(MoneyStack ms : this.internalBank) {
            moneyVal.add(ms.getValue());
        }
        return moneyVal;
    }
}

