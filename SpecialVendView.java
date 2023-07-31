import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SpecialVendView extends VendView {

    private JButton specialItem,closeSpecial;
    private ArrayList<JButton> specialItems;
    private  JPanel specialPanel;

    public SpecialVendView(ArrayList<String> items, ArrayList<Double> prices, ArrayList<Double> calories, ArrayList<Integer> stocks, ArrayList<String> options) {
        super(items, prices, calories, stocks);

        this.specialItem = new JButton("Mongolian Rice Bowl");
        this.closeSpecial = new JButton ("return");
        this.specialItems = new ArrayList<>();
        this.specialPanel = new JPanel();
        specialPanel.setLayout(new BoxLayout(specialPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < options.size(); i++) {
            String temp = options.get(i);
            JButton holder = new JButton(temp);
            this.specialItems.add(holder);
            specialPanel.add(holder);
        }

        specialPanel.add(closeSpecial);

        JPanel mainPanel = (JPanel) rVendFrame.getContentPane();
        mainPanel.add(specialPanel);
        specialPanel.setVisible(false);
        mainPanel.add(specialItem);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public JButton getSpecialItem(){
        return this.specialItem;
    }

    public JPanel getSpecialPanel(){
        return this.specialPanel;
    }

    public void setSpecialItemButtonListener(ActionListener actionListener){
        this.specialItem.addActionListener(actionListener);
    }

    public void setCloseSpecialButtonListener(ActionListener actionListener){
        this.closeSpecial.addActionListener(actionListener);
    }

    public void setSpecialItemsButtonListener(int index, ActionListener actionListener){
        JButton temp = specialItems.get(index);
        temp.addActionListener(actionListener);
    }
}
