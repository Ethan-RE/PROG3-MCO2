package VendingMachine;
import java.util.ArrayList;

public class VendingMachine {
    protected CashRegister cashHandler; //CashRegister object to handle cash transactions
    protected ArrayList<ItemStack> itemTypes; //List of available items in the vending machine
    protected ArrayList<ItemStack> lastItemStock; //Keeps track of the previous item stock for transactions
    protected static int MAX_ITEMTYPES = 16; //Maximum number of different item types the vending machine can hold
    protected static int MAX_ITEMS = 16; //Maximum number of items of a specific type the vending machine can hold
    protected ArrayList<Transaction> transactions; //List to keep track of the transaction made

    //Constructor
    public VendingMachine() {
        //Initialize the cashHandler as an empty CashRegister object
        this.cashHandler = new CashRegister();
        //Initialize the list of transactions
        this.transactions = new ArrayList<Transaction>();
        //Initialize the itemTypes list with a capacity of 8 item
        this.itemTypes = new ArrayList<ItemStack>(8);
        //Add default item to the vending machine
        this.itemTypes.add(new ItemStack(new Item(
        "Egg", 10, 100
        )));
        this.itemTypes.add(new ItemStack(new Item(
        "Beef",150,500
        )));
        this.itemTypes.add(new ItemStack(new Item(
        "Carrot",20,25
        )));
        this.itemTypes.add(new ItemStack(new Item(
        "Rice",50,130
        )));
        this.itemTypes.add(new ItemStack(new Item(
        "Peas",75,80
        )));
        this.itemTypes.add(new ItemStack(new Item(
        "Spring Onions",30,25
        )));
        this.itemTypes.add(new ItemStack(new Item(
        "Sweet sauce",15,5
        )));
        this.itemTypes.add(new ItemStack(new Item(
        "Spicy sauce",15,10
        )));
        System.out.printf("\nBasic Items Set!\n"); //Print message to indicate successful default item addition
    }

    //Testing Features
    public Item dispenseItem(int index) {
        return this.itemTypes.get(index - 1).popItem();
    }

    public ArrayList<MoneyStack> dispenseChange(ArrayList<Integer> bills, double price, double value) {
        this.cashHandler.stockInternal(bills);
        return this.cashHandler.calculateChange(price, value);
    }

    //Maintenance Features
    public void stockItem(int index, int stock) {
        //loop through 'stock' number of times to add items to the specified index in itemTypes list
        for(int i=0 ; i < stock ; i++) {
            this.itemTypes.get(index).pushItem();
        }

        //Update the lastItemStock to reflect the changes in itemTypes list
        this.lastItemStock = new ArrayList<ItemStack>();
        for (int i = 0 ; i<this.itemTypes.size() ; i++) {
            ItemStack temp = this.itemTypes.get(i);
            temp.pushItem();
            ItemStack temp2 = new ItemStack(temp.popItem());
            for(int j = 0 ; j<temp.getNumItems() ; j++) {
                temp2.pushItem();
            }
            this.lastItemStock.add(temp2);
        }

        //Clear the transactions list since the stock has changed
        this.transactions.clear();
    }

    /**
     * Creates a new ItemStack to add to itemTypes
     * @param item type of item of which the new stack will be
     * @return False if at max itemtypes, true otherwise
     */
    public boolean addNewItemStack(Item item) {
        // Check if there is space to add a new item stack
        if (this.itemTypes.size()<MAX_ITEMTYPES) this.itemTypes.add(new ItemStack(item)); // Create a new item stack and add it to the itemTypes list
        else return false;
        return true;
    }

    public void removeItemStack(int index) {
        this.itemTypes.remove(index-1);
    }

    public void stockMoney(ArrayList<Integer> restock) {
        this.cashHandler.stockInternal(restock);
    }

    public ArrayList<Money> collectMoney() {
        return this.cashHandler.collectMoney();
    }
    public void setItemPrice(int index, double price) {
        this.itemTypes.get(index).setItemPrice(price);
    }

    //Getters
    public ArrayList<String> getTransactionHistory() {
        ArrayList<String> transactions = new ArrayList<String>();
        for(int i = 0 ; i<this.transactions.size() ; i++) {
            Transaction transaction = this.transactions.get(i);
            String temp = String.format("Transaction#%d: %s--%.2f--%.2f", i+1, transaction.getItemBought(), transaction.getCashReceived(), transaction.getChangeGiven());
        }
        return transactions;
    }

    public ArrayList<ItemStack> getItemTypes(){
        return this.itemTypes;
    }

    public ArrayList<String> getItemNames() {
        ArrayList<String> items = new ArrayList<String>();
        // Loop through itemTypes array to return each item's name
        for(int i=0 ; i < this.itemTypes.size() ; i++) {
            ItemStack tempItem = this.itemTypes.get(i);
            items.add(tempItem.getItemName());
        }
        return items;
    }

    public ArrayList<Integer> getItemStock() {
        ArrayList<Integer> items = new ArrayList<Integer>();
        // Loop through itemTypes array to return how many of each item exists
        for(int i=0 ; i < this.itemTypes.size() ; i++) {
            ItemStack tempItem = this.itemTypes.get(i);
            items.add(tempItem.getNumItems());
        }
        return items;
    }

    public ArrayList<Double> getItemCalories() {
        ArrayList<Double> items = new ArrayList<Double>();
        //Loop through itemTypes array to return each item's calories
        for(int i=0 ; i < this.itemTypes.size() ; i++) {
            ItemStack tempItem = this.itemTypes.get(i);
            items.add(tempItem.getItemCalories());
        }
        return items;
    }

    public ArrayList<Double> getItemPrice() {
        ArrayList<Double> items = new ArrayList<Double>();
        //Loop through itemTypes array to return each item's price
        for(int i=0 ; i < this.itemTypes.size() ; i++) {
            ItemStack tempItem = this.itemTypes.get(i);
            items.add(tempItem.getItemPrice());
        }
        return items;
    }

    public ArrayList<Integer> getMoneyStock() {
        return this.cashHandler.getMoneyStock();
    }
    
    public ArrayList<Double> getMoneyValue() {
        return this.cashHandler.getMoneyValue();
    }
}