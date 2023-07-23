package VendingMachine;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A class representing a vending machine.
 */
public class NewRegVendMachine {
    private CashRegister cashHandler; //CashRegister object to handle cash transactions
    private ArrayList<ItemStack> itemTypes; //List of available items in the vending machine
    private ArrayList<ItemStack> lastItemStock; //Keeps track of the previous item stock for transactions
    private static int MAX_ITEMTYPES = 16; //Maximum number of different item types the vending machine can hold
    private static int MAX_ITEMS = 16; //Maximum number of items of a specific type the vending machine can hold
    private ArrayList<Transaction> transactions; //List to keep track of the transaction made

    //Constructor for NewRegVendMachine class

    /**
     * Constructor for NewRegVendMachine, initializes all arrays and sets basic items
     */
    public NewRegVendMachine() {
        //Initialize the cashHandler as an empty CashRegister object
        this.cashHandler = new CashRegister(0,0,0,0,0,0,0,0,0,0,0);
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

    /**
     * Interface for interacting with vending features for vending machine
     */
    public void featuresVending() {
        Scanner input = new Scanner(System.in);
        int x = -1;
        do {
            System.out.printf("\nVENDING MACHINE MENU\n");
            System.out.printf("1. Back\n2. Display Items\n3. Buy Item");
            try {
                System.out.printf("\nINPUT: ");
                x = input.nextInt();       
            }
            catch (InputMismatchException e) {
                System.out.printf("\nINVALID INPUT\n");
                input.nextLine();
            }
            switch (x) {
                case 1: 
                    break;
                case 2:
                    this.displayItems(); //Display the available items in the vending machine
                    break;
                case 3:
                    if (this.itemsInStock()) this.buyItem(); //Check if items are in stock before allowing a purchase
                    else System.out.printf("\nNO ITEMS IN STOCK\n"); //Print a message if no items are available for purchase
                    break;
                default:
                    System.out.printf("\nINVALID INPUT\n");
            }
        } while (x!=1); //Continue the loop until the user chooses to return to menu

    }

    /**
     * Lets user buy an item.
     * @return an Item of the type they buy.
     */
    private Item buyItem() {
        Scanner input = new Scanner(System.in);
        int index=0;

        this.displayItems(); //Display available items to the user

        do {
            try {
                System.out.printf("\nEnter item you would like to buy (0 to exit): ");
                index = input.nextInt();
            }
            catch(InputMismatchException e) {
                System.out.println("\nINVALID INPUT\n");
                input.nextLine();
            }
            if (index==0) return null; //Exit if user cancels the purchase
            else if (index < 0 || index > this.itemTypes.size()) System.out.printf("\nINVALID INPUT\n"); //Invalid item index
            else if (this.itemTypes.get(index-1).getNumItems()==0) {
                System.out.printf("\nOUT OF STOCK\n"); //Item is out of stock
                index=-1;
            }
        } while (index < 1 || index > this.itemTypes.size());

        double price = this.itemTypes.get(index-1).getItemPrice(); //Get the price of the selected item

        int[] monies = {0,0,0,0,0,0,0,0,0,0}; //Array to hold the count of each bill/coin

        int choice=0;
        double sum=0;

        System.out.printf("\nINSERT BILLS\n");
        do {
            //Display the options for bills/coins and prompt for user input
            System.out.printf("1. Cancel\n2. Ones\n3. Fives\n4.Tens\n5. Twenties\n6. Fifties\n7. One Hundreds\n8. Two Hundreds\n9. Five Hundred\n10. One Thousands\nINPUT: ");
            try {
                choice = input.nextInt();
            }
            catch(InputMismatchException e) {
                System.out.println("\nINVALID INPUT\n");
                input.nextLine();
            }
            if (choice < 1 || choice > monies.length) System.out.printf("\nINVALID INPUT\n"); //Invalid bill/coin choice
            else {
                monies[choice-2]++; //Increment the count of the selected bill/coin
                switch (choice) {
                    case 1:
                        break;
                    case 2:
                        sum+=1;
                        break;
                    case 3:
                        sum+=5;
                        break;
                    case 4:
                        sum+=10;
                        break;
                    case 5:
                        sum+=20;
                        break;
                    case 6:
                        sum+=50;
                        break;
                    case 7:
                        sum+=100;
                        break;
                    case 8:
                        sum+=200;
                        break;
                    case 9:
                        sum+=500;
                        break;
                    case 10:
                        sum+=1000;
                        break;
                    default:
                        break;
                }
            }
        } while (choice != 1 && sum < price); //Continue until the user cancels or the sum reaches the item price

        if (choice==1) {
            System.out.printf("\nRETURNING INSERTED BILLS\n"); //Return the inserted bills and cancel the transaction
            return null;
        }

        ArrayList<Integer> change = this.cashHandler.calculateChange(price, sum); // Calculate the change required

        if (change!=null)
            if (change.get(change.size()-1)==-1) {
                this.cashHandler.stockInternal(change);
                System.out.printf("\nSORRY NOT ENOUGH CHANGE"); //Unable to provide sufficient change
                System.out.printf("\nRETURNING INSERTED BILLS\n");
                System.out.printf("\nCANCELLING TRANSACTION...\n");
                return null;
            }
        if(change!=null) System.out.printf("\nDISPENSING CHANGE\n");

        ArrayList<Integer> newBills = new ArrayList<Integer>();

        for (int i = 0; i < monies.length; i++)
            newBills.add(monies[i]); // Create a new array list to hold the counts of bill/coins

        this.cashHandler.stockInternal(newBills); // Update the cash handler with the new bill/coin

        System.out.printf("\nDISPENSING ITEM\n");     

        Item itemBought = this.itemTypes.get(index-1).popItem();

        this.transactions.add(new Transaction(sum, this.cashHandler.getChange(), itemBought)); // Add the transaction to the list

        return itemBought; // Return the purchased item
    }

    //Maintenance Features

    /**
     * Interface for interacting with maintenance features for vending machine
     */
    public void featuresMaintenance() {
        Scanner input = new Scanner(System.in);
        int x = -1;
        do {
            //Display maintenance options
            System.out.printf("\nVENDING MACHINE MAINTENANCE MENU\n");
            System.out.printf("1. Back\n2. Display Items\n3. Display Available Bills\n4. Restock Items\n5. Set Item Price\n6. Restock Money\n7. Add New Item\n8. Remove Item\n9. Review Transactions");
            try {
                System.out.printf("\nINPUT: ");
                x = input.nextInt();       
            }
            catch (InputMismatchException e) {
                System.out.printf("\nINVALID INPUT\n");
                input.nextLine();
            }
            switch (x) {
                case 1: 
                    break;
                case 2:
                    this.displayItems();
                    break;
                case 3:
                    this.displayBills();
                    break;
                case 4:
                    this.restockItem();
                    break;
                case 5:
                    this.setItemPrice();
                    break;
                case 6:
                    this.restockMoney();
                    break;
                case 7:
                    this.addNewItemStack();
                    break;
                case 8:
                    this.removeItemStack();
                    break;
                case 9:
                    this.reviewTransactions();
                    break;
                default:
                    System.out.printf("\nINVALID INPUT\n");
            }
        } while (x!=1);
    }

    /**
     * Stocks items based on how many items to add and at what index to add them to.
     * @param index Stack to add item to
     * @param stock How many of an item to add
     */
    private void stockItem(int index, int stock) {
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
     * Restocks Item
     */
    private void restockItem() {
        Scanner input = new Scanner(System.in);
        int index = 0;
        int stock = 0;

        // Display the items available in the vending machine
        this.displayItems();

        // Prompt the user to select an item to restock
        do {
            try {
                System.out.printf("\nWhich item would you like to restock: ");
                index = input.nextInt();       
            }
            catch (InputMismatchException e) {
                System.out.printf("\nINVALID INPUT\n");
                input.nextLine();
            }
        } while (index<1||index>this.itemTypes.size());

        //Prompt the user to enter the quantity of items to restock
        System.out.printf("\nHow much of this item would you like to restock? ");
        do {
            try {
                System.out.printf("\nINPUT: ");
                stock = input.nextInt();       
            }
            catch (InputMismatchException e) {
                System.out.printf("\nINVALID INPUT\n");
                input.nextLine();
            }
            if (stock<0||stock>MAX_ITEMS-(this.itemTypes.get(index-1).getNumItems())) System.out.printf("\nINVALID INPUT\n");
        } while (stock<0||stock>MAX_ITEMS-(this.itemTypes.get(index-1).getNumItems()));

        // Add the specified number of items to the select item stack
        for (int i=0 ; i<stock ; i++) {
            this.itemTypes.get((index-1)).pushItem();
        }

        // Update the lastItemStock to reflect the changes in itemTypes list
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

        // Clear the transactions list since the stock has changed
        this.transactions.clear();
    }

    /**
     * Creates a new ItemStack to add to itemTypes
     * @param item type of item of which the new stack will be
     */
    private void addNewItemStack(Item item) {
        // Check if there is space to add a new item stack
        if (this.itemTypes.size()<MAX_ITEMTYPES) this.itemTypes.add(new ItemStack(item)); // Create a new item stack and add it to the itemTypes list
        else System.out.printf("ALL SLOTS BEING USED"); // Display a message indicating that all slots are being used
    }

    /**
     * Creates a new ItemStack to add to itemTypes
     */
    private void addNewItemStack() {
        Scanner input = new Scanner(System.in);
        String tempName = "\n";
        double tempPrice = 0;
        double tempCal = 0;
        int stock = 0;
        System.out.printf("\nItem #%d\n", this.itemTypes.size()+1);

        // Prompt the user to enter the name of the new item
        do {
            System.out.printf("Enter item name: ");
            tempName = input.nextLine();
            if (tempName.equals("\n")) System.out.printf("\nNAME CANNOT BE EMPTY\n");
            else if (this.checkIfItemExists(tempName)) System.out.printf("\nITEM ALREADY EXISTS\n");
        } while (tempName.equals("\n")||this.checkIfItemExists(tempName));

        // Prompt the user to enter the price of the new item
        do {
            try {
                System.out.printf("Enter item price (php): ");
                tempPrice = input.nextDouble();       
            }
            catch (InputMismatchException e) {
                System.out.printf("\nINVALID INPUT\n");
                input.nextLine();
            }
            if (tempPrice<0) System.out.printf("\nPRICE CANNOT BE NEGATIVE\n");
        } while (tempPrice<0);
        
        input.nextLine();

        // Prompt the user to enter the calories of the new item
        do {
            try {
                System.out.printf("Enter item calories: ");
                tempCal = input.nextDouble();       
            }
            catch (InputMismatchException e) {
                System.out.printf("\nINVALID INPUT\n");
                input.nextLine();
            }
            if (tempCal<0) System.out.printf("\nCALORIES CANNOT BE NEGATIVE\n");
        } while (tempCal<0);

        // Prompt the user to enter the stock quantity of the new item
        do {
            try {
                System.out.printf("Enter item stock: ");
                stock = input.nextInt();       
            }
            catch (InputMismatchException e) {
                System.out.printf("\nINVALID INPUT\n");
                input.nextLine();
            }
            if (stock<0) System.out.printf("\nSTOCK CANNOT BE NEGATIVE\n");
            else if (stock>MAX_ITEMS) System.out.printf("\nSTOCK CANNOT BE GREATER THAN 16\n");
        } while (stock<0);

        input.nextLine();

        // Create a new item with the provided information and add it to the vending machine
        this.addNewItemStack(new Item(tempName, tempPrice, tempCal));
        // Stock the newly added item with the specified stock quantity
        this.stockItem(this.itemTypes.size()-1, stock);
    }

    /**
     * Removes an ItemStack from itemTypes
     */
    private void removeItemStack() {
        Scanner input = new Scanner(System.in);
        int x = 0;

        // Display the item in the vending machine
        do {
            this.displayItems();
            System.out.printf("\nWhich item would you like to remove? ");
            try {
                System.out.printf("\nINPUT: ");
                x = input.nextInt();       
            }
            catch (InputMismatchException e) {
                System.out.printf("\nINVALID INPUT\n");
                input.nextLine();
            }
            if (x<0||x>this.itemTypes.size()) System.out.printf("\nINVALID INPUT\n");
        } while (x<0||x>this.itemTypes.size());

        // Remove the selected item from the itemTypes list
        this.itemTypes.remove(x-1);
    }

    /**
     * Sets price of an item
     */
    private void setItemPrice() {
        Scanner input = new Scanner(System.in);
        int x = 0;
        double price = 0;

        // Prompt user to select item to change its price
        do {
            this.displayItems();
            System.out.printf("\nWhich item would you like to change price? ");
            try {
                System.out.printf("\nINPUT: ");
                x = input.nextInt();       
            }
            catch (InputMismatchException e) {
                System.out.printf("\nINVALID INPUT\n");
                input.nextLine();
            }
            if (x<0||x>this.itemTypes.size()) System.out.printf("\nINVALID INPUT\n");
        } while (x<0||x>this.itemTypes.size());

        // Prompt user to enter the new price for selected item
        System.out.printf("\nENTER NEW PRICE: ");
        do {
            try {
                System.out.printf("\nINPUT: ");
                price = input.nextDouble();
            } catch (InputMismatchException e) {
                System.out.printf("\nINVALID INPUT\n");
                input.nextLine();
            }
        } while (price<0);

        // Update the price of the selected item
        this.itemTypes.get(x-1).setItemPrice(price);
    }

    /**
     * Restocks money
     */
    private void restockMoney() {
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> restock = new ArrayList<Integer>();
        ArrayList<String> denominations = new ArrayList<String>();
        List<String> stringsToAdd = Arrays.asList("Ones","Fives","Tens","Twenties","Fifties","One Hundreds","Two Hundreds","Five Hundreds","One Thousands");
        denominations.addAll(stringsToAdd);
        
        int quantity;

        // Prompt user to enter the quantity of each denomination to restock
        for(int i=0 ; i < 9;i++) {
            try {
                System.out.println("How many " + denominations.get(i) + " To add ");
                quantity = input.nextInt();
                restock.add(quantity);
            }
            catch (InputMismatchException e) {
                System.out.printf("\nINVALID INPUT\n");
                input.nextLine();
                i--;
            }
        }

        // Restock the internal cash handler with the specified quantities of each denomination
        this.cashHandler.stockInternal(restock);
    }

    /**
     * Shows all transactions and inventory
     * Displays inventory from previous stocking and inventory currently
     * Displays change given and cash received from each transaction
     */
    private void reviewTransactions() {
        double total=0;
        double totalchange=0;
        double totalreceived=0;
        System.out.printf("\nSTARTING INVENTORY: ");
        System.out.printf("\nNAME--STOCK");

        for(int i=0; i < this.lastItemStock.size() ; i++) {
            System.out.printf("\n%s--%d", this.lastItemStock.get(i).getItemName(), this.lastItemStock.get(i).getNumItems());
        }


        System.out.printf("\nCURRENT INVENTORY: ");
        this.displayItems();
        System.out.printf("\nTRANSACTIONS: ITEM--CASH--CHANGE");
        if (this.transactions.size()==0) System.out.printf("\nNO TRANSACTIONS SINCE LAST RESTOCKING");
        for (int i=0 ; i<this.transactions.size() ; i++) {
            Transaction transaction = this.transactions.get(i);
            System.out.printf("\nTransaction#%d: %s--%.2f--%.2f", i+1, transaction.getItemBought(), transaction.getCashReceived(), transaction.getChangeGiven());
            totalchange+=transaction.getChangeGiven();
            totalreceived+=transaction.getCashReceived();
        }
        total = totalreceived-totalchange;
        System.out.printf("\nTOTAL REVENUE: %.2f", total);
    }

    /**
     * Displays all bills available
     */
    private void displayBills() {
        this.cashHandler.displayBills();
    }

    //Getters
    //Getter for itemNames
    public ArrayList<String> getItemNames() {
        ArrayList<String> items = new ArrayList<String>();
        // Loop through itemTypes array to return each item's name
        for(int i=0 ; i < this.itemTypes.size() ; i++) {
            ItemStack tempItem = this.itemTypes.get(i);
            items.add(tempItem.getItemName());
        }
        return items;
    }

    //Getter for itemStock
    public ArrayList<Integer> getItemStock() {
        ArrayList<Integer> items = new ArrayList<Integer>();
        // Loop through itemTypes array to return how many of each item exists
        for(int i=0 ; i < this.itemTypes.size() ; i++) {
            ItemStack tempItem = this.itemTypes.get(i);
            items.add(tempItem.getNumItems());
        }
        return items;
    }

    //Getter for itemCalories
    public ArrayList<Double> getItemCalories() {
        ArrayList<Double> items = new ArrayList<Double>();
        //Loop through itemTypes array to return each item's calories
        for(int i=0 ; i < this.itemTypes.size() ; i++) {
            ItemStack tempItem = this.itemTypes.get(i);
            items.add(tempItem.getItemCalories());
        }
        return items;
    }

    //Getter for itemPrice
    public ArrayList<Double> getItemPrice() {
        ArrayList<Double> items = new ArrayList<Double>();
        //Loop through itemTypes array to return each item's price
        for(int i=0 ; i < this.itemTypes.size() ; i++) {
            ItemStack tempItem = this.itemTypes.get(i);
            items.add(tempItem.getItemPrice());
        }
        return items;
    }

    /**
     * Checks if a given item exists based on its name
     * @param string
     * @return true if item exists
     */
    private boolean checkIfItemExists(String string) {
        //loop through itemTypes array to find an item with the given name
        for (int i=0 ; i < this.itemTypes.size() ; i++) {
            ItemStack tempItem = this.itemTypes.get(i);
            if (string.equals(tempItem.getItemName())) return true; //If found return true
        }
        //If an item matching the given name is not found return false
        return false;
    }

    /**
     * Displays all item's stock, calories, and prices
     */
    private void displayItems() {
        ArrayList<String> itemNames = this.getItemNames();
        ArrayList<Integer> itemStock = this.getItemStock();
        ArrayList<Double> itemCalories = this.getItemCalories();
        ArrayList<Double> itemPrice = this.getItemPrice();
        System.out.printf("\nNAME--STOCK--CAL--PRICE");
        //Loop through itemTypes to display each item's information
        for (int i=0 ; i < this.itemTypes.size() ; i++) {
            System.out.printf("\n%d. %s--%d--%.2f--%.2f", i+1, itemNames.get(i), itemStock.get(i), itemCalories.get(i), itemPrice.get(i));
        }
        System.out.printf("\n");
    }

    /**
     * Method to check if items are in stock or not
     * @return true if any item is in stock
     */
    private boolean itemsInStock() {
        int i=0;
        int itemsNotInStock=0;
        //Loop through itemTypes array to check stock of each item
        for (i=0 ; i<this.itemTypes.size() ; i++) {
            if (this.itemTypes.get(i).getNumItems()==0) itemsNotInStock++;
        }
        if (itemsNotInStock==this.itemTypes.size()) return false; //If machine contains no items return false
        return true; //Else return true
    }
}
