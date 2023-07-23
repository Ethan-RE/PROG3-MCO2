package VendingMachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
     * @return array of bills given as change
     */
    public ArrayList<Integer> calculateChange(double price, double value)

    //Restock the internal bank with given denominations

    /**
     * Restock the internal bank with the given denominations
     * @param restock - array of amount of each bill there is, from 1-1000
     */
    public void stockInternal(ArrayList<Integer> restock) 

    //Getter for change
    public double getChange() {return this.change;}
}

