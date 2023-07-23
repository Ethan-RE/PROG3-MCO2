package VendingMachine;

import java.util.ArrayList;
import java.lang.Cloneable;

/**
 * A class representing a stack of items of the same type.
 * Used within the vending machine to dispense items.
 */
public class ItemStack{
    private ArrayList<Item> items;
    private Item itemType;

    /**
     * Constructor for ItemStack
     * @param item - type of Item stack holds
     */
    public ItemStack(Item item) {
        this.items = new ArrayList<Item>();
        this.itemType = item;
    }

    // Get the total number of items in the ItemStack
    public int getNumItems() {
        return this.items.size();
    }

    /**
     * Pops an item from the stack
     * @return an item of whichever type the stack holds
     */
    public Item popItem() {
        Item temp = this.items.get(this.items.size()-1);
        this.items.remove(this.items.size()-1);
        return temp;
    }

    /**
     * Pushes a new item onto the stack.
     */
    public void pushItem() {
        this.items.add(new Item(this.itemType));
    }

    // Getters

    // Get the name of the item in the stack
    public String getItemName() {
        return this.itemType.getName();
    }

    // Get the price of the item in the stack
    public double getItemPrice() {
        return this.itemType.getPrice();
    }

    // Get the calories of the item in the stack
    public double getItemCalories() {
        return this.itemType.getCalories();
    }

    // Set the price of the item in the stack
    public void setItemPrice(double price) {
        this.itemType.setPrice(price);
    }
}