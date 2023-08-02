import java.awt.*;
import java.awt.event.ActionListener;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.*;
public class CollectMoneyFrame extends JFrame {
    JTextArea collected;
    JButton collectMoney,backButton;
    public CollectMoneyFrame() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        this.collectMoney = new JButton("Collect Money");
        JPanel collectMoneyPanel = new JPanel();
        collectMoneyPanel.add(this.collectMoney);
        mainPanel.add(collectMoneyPanel);

        this.collected = new JTextArea(" ");
        JPanel collectedPanel = new JPanel();
        collectedPanel.add(this.collected);
        mainPanel.add(collectedPanel);

        this.backButton = new JButton("Back");
        JPanel back = new JPanel();
        back.add(this.backButton, BorderLayout.SOUTH);
        mainPanel.add(back);

        add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setSize(250, 400);
        setVisible(false);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public JTextArea getCollected(){
        return this.collected;
    }

    public void setCollectMoneyButtonListener(ActionListener actionListener){
        this.collectMoney.addActionListener(actionListener);
    }

    public void setBackButtonListener(ActionListener actionListener){
        this.backButton.addActionListener(actionListener);
    }
}
