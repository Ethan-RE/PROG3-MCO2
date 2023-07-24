package VendingMachine;
import java.util.ArrayList;

public class SpecialItem extends Item {
    private ArrayList<Item> ingredients;

    public SpecialItem(ArrayList<Item> ingredients) {
        super();
        this.name = "Mongolian Rice Bowl";
        this.ingredients = ingredients;
        for(int i = 0 ; i<ingredients.size() ; i++) {
            this.calories = ingredients.get(i).getCalories();
            this.price = ingredients.get(i).getPrice();
        }
        this.step = step;
    }
}
