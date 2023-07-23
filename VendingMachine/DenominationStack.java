package VendingMachine;

public class MoneyStack {
    private int value;
    private ArrayList<Denomination> denominations;

    public MoneyStack(int value) {
        this.denominations = new ArrayList<Denomination>();
        this.value = value;
    }

    public void pushMoney() {
        this.denominations.add(new Denomination(value));
    }

    public Denomination popMoney() {
        Denomination temp = this.denominations.get(this.denominations.size()-1);
        this.denominations.remove(this.denominations.size()-1);
        return temp;
    }
}