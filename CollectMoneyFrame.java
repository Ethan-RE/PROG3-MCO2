import java.awt.*;
import java.awt.event.ActionListener;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.*;
public class CollectMoneyFrame extends JFrame {
    JButton backButton;
    JButton collectMoney;
    public CollectMoneyFrame() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        this.collectMoney = new JButton("Collect Money");
        JPanel collectMoneyPanel = new JPanel();
        collectMoneyPanel.add(this.collectMoney);
        mainPanel.add(collectMoneyPanel);

        this.backButton = new JButton("Back");
        JPanel back = new JPanel();
        back.add(this.backButton, BorderLayout.SOUTH);
        mainPanel.add(back);

        add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setSize(250, 400);
        setVisible(true);
    }

    public static void main(String[] args) {
        CollectMoneyFrame cmf = new CollectMoneyFrame();
    }

    public void setBackButtonListener(ActionListener actionListener){
        this.backButton.addActionListener(actionListener);
    }
}
