package VendingMachine;
import java.util.ArrayList;

public class MoneyStack {
    private double value;
    private ArrayList<Money> Moneys;

    public MoneyStack(double value) {
        this.Moneys = new ArrayList<Money>();
        this.value = value;
    }

    public void pushMoney() {
        this.Moneys.add(new Money(value));
    }

    public Money popMoney() {
        Money temp = this.Moneys.get(this.Moneys.size()-1);
        this.Moneys.remove(this.Moneys.size()-1);
        return temp;
    }

    public double getValue() {
        return this.value;
    }

    public int getNumMoney() {
        return this.Moneys.size();
    }
}