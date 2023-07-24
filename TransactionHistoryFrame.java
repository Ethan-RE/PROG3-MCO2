import VendingMachine.Transaction;

import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;


public class TransactionHistoryFrame extends JFrame{
    JButton backButton;
    public TransactionHistoryFrame(ArrayList<String> transactions) {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));

        JTextArea fields = new JTextArea("Transaction#--Item Bought--Cash Received--Change Given");
        mainPanel.add(fields);
        ArrayList<JTextArea> transactionHistory = new ArrayList<>();
        int i = 0;

        for(String transaction : transactions) {
            transactionHistory.add(new JTextArea(transaction));
            mainPanel.add(transactionHistory.get(i));
            i++;
        }

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

    public void setBackButtonListener(ActionListener actionListener){
        this.backButton.addActionListener(actionListener);
    }

}
