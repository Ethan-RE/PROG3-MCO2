package VendingMachine;

public class VendingMachine {
    private CashRegister cashHandler; //CashRegister object to handle cash transactions
    private ArrayList<ItemStack> itemTypes; //List of available items in the vending machine
    private ArrayList<ItemStack> lastItemStock; //Keeps track of the previous item stock for transactions
    private static int MAX_ITEMTYPES = 16; //Maximum number of different item types the vending machine can hold
    private static int MAX_ITEMS = 16; //Maximum number of items of a specific type the vending machine can hold
    private ArrayList<Transaction> transactions; //List to keep track of the transaction made

    //Constructor
    public NewRegVendMachine() {
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

    //Maintenance Features
}