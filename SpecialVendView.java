import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SpecialVendView extends VendView {

    private JButton specialItem,confirmBasket,closeSpecial;
    private JTextArea basket,cooking;
    private JButton beef,sweetS,spicyS,peas,eggs;
    private  JPanel specialPanel;

    public SpecialVendView(ArrayList<String> items, ArrayList<Double> prices, ArrayList<Double> calories, ArrayList<Integer> stocks) {
        super(items, prices, calories, stocks);

        this.specialItem = new JButton("Mongolian Rice Bowl");
        this.confirmBasket = new JButton("Confirm Basket");
        this.closeSpecial = new JButton ("return");
        this.basket = new JTextArea();
        this.cooking = new JTextArea("Cooking your rice bowl");
        this.specialPanel = new JPanel();
        specialPanel.setLayout(new BoxLayout(specialPanel, BoxLayout.Y_AXIS));

        this.beef = new JButton("Beef");
        this.sweetS = new JButton("Sweet Sauce");
        this.spicyS = new JButton("Spicy sauce");
        this.peas = new JButton("Peas");
        this.eggs = new JButton("Egg");

        specialPanel.add(beef);
        specialPanel.add(sweetS);
        specialPanel.add(spicyS);
        specialPanel.add(peas);
        specialPanel.add(eggs);

        specialPanel.add(basket);
        specialPanel.add(confirmBasket);

        specialPanel.add(closeSpecial);

        JPanel mainPanel = (JPanel) rVendFrame.getContentPane();
        mainPanel.add(specialPanel);
        specialPanel.setVisible(false);
        mainPanel.add(specialItem);
        mainPanel.add(cooking);
        cooking.setVisible(false);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public JButton getSpecialItem(){
        return this.specialItem;
    }

    public JTextArea getBasket(){
        return this.basket;
    }

    public JTextArea getCooking(){
        return this.cooking;
    }

    public JPanel getSpecialPanel(){
        return this.specialPanel;
    }
    public void setConfirmBasketButtonListener(ActionListener actionListener){
        this.confirmBasket.addActionListener(actionListener);
    }

    public void setSpecialItemButtonListener(ActionListener actionListener){
        this.specialItem.addActionListener(actionListener);
    }

    public void setCloseSpecialButtonListener(ActionListener actionListener){
        this.closeSpecial.addActionListener(actionListener);
    }

    public void setBeefButtonListener(ActionListener actionListener){
        this.beef.addActionListener(actionListener);
    }

    public void setSweetSButtonListener(ActionListener actionListener){
        this.sweetS.addActionListener(actionListener);
    }

    public void setSpicySButtonListener(ActionListener actionListener){
        this.spicyS.addActionListener(actionListener);
    }

    public void setPeasButtonListener(ActionListener actionListener){
        this.peas.addActionListener(actionListener);
    }

    public void setEggsButtonListener(ActionListener actionListener){
        this.eggs.addActionListener(actionListener);
    }

}
