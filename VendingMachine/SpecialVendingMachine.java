package VendingMachine;

import java.util.ArrayList;

public class SpecialVendingMachine extends VendingMachine {
    SpecialItem specialOrder;
    ArrayList<ItemStack> options;

    public SpecialVendingMachine() {
        super();
        this.options = new ArrayList<>();

        options.addAll(itemTypes);

        this.options.add(new ItemStack(new Item(
                "Brown Sugar",5,10
        )));

        this.options.add(new ItemStack(new Item(
                "Garlic",10,6
        )));

        this.options.add(new ItemStack (new Item(
            "Ginger",10,2
        )));
    }

    public void cook(ArrayList<Item> ingredients) {
        ArrayList<String> holder = new ArrayList<>();

        for(int i = 0;i < ingredients.size();i++){
            Item temp  = ingredients.get(i);
            holder.add(temp.getName());
        }

        for(int i = 0;i < itemTypes.size();i++){
            ItemStack temp = itemTypes.get(i);
            Item held = temp.popItem();
            String name = held.getName();
            if(name != holder.get(i))
                temp.pushItem();
        }

        this.specialOrder = new SpecialItem(ingredients);
    }

    public ArrayList<ItemStack> getOptions(){
        return this.options;
    }

    public ArrayList<String> getOptionNames(){
        ArrayList<String> holder = new ArrayList<>();
        for(int i = 0;i < options.size();i++){
            ItemStack temp  = options.get(i);
            String held = temp.getItemName();
            holder.add(held);
        }
        return holder;
    }

    public ArrayList<Double> getOptionPrices(){
        ArrayList<Double> holder = new ArrayList<>();
        for(int i = 0;i < options.size();i++){
            ItemStack temp = options.get(i);
            Double held = temp.getItemPrice();
            holder.add(held);
        }
        return holder;
    }

    public ArrayList<Double> getOptionCalories(){
        ArrayList<Double> holder = new ArrayList<>();
        for(int i = 0;i < options.size();i++){
            ItemStack temp = options.get(i);
            Double held = temp.getItemCalories();
            holder.add(held);
        }
        return holder;
    }

    public SpecialItem getSpecialItem(){
        return this.specialOrder;
    }
}
