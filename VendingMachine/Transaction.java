package VendingMachine;

/**
 * A class representing a transaction within the vending machine.
 */
public class Transaction {
    private double cashReceived;
    private double changeGiven;
    private Item itemBought;

    //Constructor to initialize a transaction object

    /**
     * Constructor for a Transaction
     * @param cashReceived amount of cash received from a transaction
     * @param changeGiven amount of change given
     * @param itemBought what type of item was bought
     */
    public Transaction(double cashReceived, double changeGiven, Item itemBought) {this.cashReceived = cashReceived; this.changeGiven = changeGiven; this.itemBought=itemBought;}

    //Getters
    //Getter for cashReceived
    public double getCashReceived() {return this.cashReceived;}
    //Getter for changeGiven
    public double getChangeGiven() {return this.changeGiven;}
    //Getter for name of item bought
    public String getItemBought() {return this.itemBought.getName();}
    
}