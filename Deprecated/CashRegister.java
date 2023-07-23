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

    private int thousands;
    private int fiveHundreds;
    private int twoHundreds;
    private int hundreds;
    private int fifties;
    private int twenties;
    private int tens;
    private int fives;
    private int ones;

    private ArrayList<Integer> internalBank;

    //CashRegister constructor
    public CashRegister(double price, double value, int thousands, int fiveHundreds, int twoHundreds, int hundreds, int fifties, int twenties, int tens, int fives, int ones){
        this.price = price;
        this.value = value;
        this.change = 0.0;

        this.thousands = thousands;
        this.fiveHundreds = fiveHundreds;
        this.twoHundreds = twoHundreds;
        this.hundreds = hundreds;
        this.fifties = fifties;
        this.twenties = twenties;
        this.tens = tens;
        this.fives = fives;
        this.ones = ones;

        this.internalBank = new ArrayList<Integer>();

        //Initialize internal bank with available denominations
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
    public ArrayList<Integer> calculateChange(double price, double value){
        int thousand = 0;
        int fiveH = 0;
        int twoH = 0;
        int hundred = 0;
        int fifty = 0;
        int twenty = 0;
        int ten = 0;
        int five = 0;
        int one = 0;

        ArrayList<Integer> changes = new ArrayList<>();
            
        this.change = value - price;
        value = value - price;

        if (this.change<=0) return null;

        if(value>0) {
            //calculate the change using the available denominations
            while(value>=1000&&this.thousands>0) {
                value = value - 1000;
                this.thousands--;
                this.internalBank.set(8,this.internalBank.get(8)-1);
                thousand++;
            }

            while(value>=500&&this.fiveHundreds>0) {
                value = value - 500;
                this.fiveHundreds--;
                this.internalBank.set(7,this.internalBank.get(7)-1);
                fiveH++;
            }

            while(value>=200&&this.twoHundreds>0) {
                value = value - 200;
                this.twoHundreds--;
                this.internalBank.set(6,this.internalBank.get(6)-1);
                twoH++;
            }

            while(value>=100&&this.hundreds>0) {
                value = value - 100;
                this.hundreds--;
                this.internalBank.set(5,this.internalBank.get(5)-1);
                hundred++;
            }

            while(value>=50&&this.fifties>0) {
                value = value - 50;
                this.fifties--;
                this.internalBank.set(4,this.internalBank.get(4)-1);
                fifty++;
            }

            while(value>=20&&this.twenties>0) {
                value = value - 20;
                this.twenties--;
                this.internalBank.set(3,this.internalBank.get(3)-1);
                twenty++;
            }

            while(value>=10&&this.tens>0) {
                value = value - 10;
                this.tens--;
                this.internalBank.set(2,this.internalBank.get(2)-1);
                ten++;
            }

            while(value>=5&&this.fives>0) {
                value = value - 5;
                this.fives--;
                this.internalBank.set(1,this.internalBank.get(1)-1);
                five++;
            }

            while(value>=1&&this.ones>0) {
                value = value - 1;
                this.ones--;
                this.internalBank.set(0,this.internalBank.get(0)-1);
                one++;
            }

            //Add calculated values to changes list
            changes.add(thousand);
            changes.add(fiveH);
            changes.add(twoH);
            changes.add(hundred);
            changes.add(fifty);
            changes.add(twenty);
            changes.add(ten);
            changes.add(five);
            changes.add(one);

            //If there is still remaining change, add -1 to indicate insufficient denominations
            if(value > 0) {
                changes.add(-1);
            }
                    

        }

        return changes;
    }

    //Restock the internal bank with given denominations

    /**
     * Restock the internal bank with the given denominations
     * @param restock - array of amount of each bill there is, from 1-1000
     */
    public void stockInternal(ArrayList<Integer> restock) {
        int oldValue;
        int newValue;

        //Update each denomination in the internal bank
        for(int x = 0;x<9;x++) {
            oldValue = this.internalBank.get(x);
            newValue = oldValue + restock.get(x);

            this.internalBank.set(x,newValue);
        }

        this.thousands = this.internalBank.get(8);
        this.fiveHundreds = this.internalBank.get(7);
        this.twoHundreds = this.internalBank.get(6);
        this.hundreds = this.internalBank.get(5);
        this.fifties = this.internalBank.get(4);
        this.twenties = this.internalBank.get(3);
        this.tens = this.internalBank.get(2);
        this.fives = this.internalBank.get(1);
        this.ones = this.internalBank.get(0);
    }

    //Getter for change
    public double getChange() {return this.change;}

    /**
     * Displays all available bills
     */
    public void displayBills() {
        ArrayList<String> denominations = new ArrayList<String>();
        List<String> stringsToAdd = Arrays.asList("Ones","Fives","Tens","Twenties","Fifties","One Hundreds","Two Hundreds","Five Hundreds","One Thousands");
        denominations.addAll(stringsToAdd);
        for (int i=0 ; i<9 ; i++) {
            System.out.printf("\n%s - %d", denominations.get(i), this.internalBank.get(i));
        }
        System.out.printf("\n");
    }
}

