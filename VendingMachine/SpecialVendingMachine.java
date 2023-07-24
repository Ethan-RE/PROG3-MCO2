package VendingMachine;

import java.util.ArrayList;

public class SpecialVendingMachine extends VendingMachine {
    SpecialItem specialOrder;

    public SpecialVendingMachine() {
        super();
    }

    public void cook(ArrayList<Item> ingredients) {
        specialOrder = new SpecialItem(ingredients);
    }
}
