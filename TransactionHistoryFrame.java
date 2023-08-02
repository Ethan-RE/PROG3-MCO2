import VendingMachine.Transaction;

import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;


public class TransactionHistoryFrame extends JFrame implements Observer{
    JButton backButton;
    ArrayList<String> transactions;
    public TransactionHistoryFrame(ArrayList<String> transactions) {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));

        JTextArea fields = new JTextArea("Transaction#--Item Bought--Cash Received--Change Given");
        mainPanel.add(fields);
        ArrayList<JTextArea> transactionHistory = new ArrayList<>();
        int i = 0;

        this.transactions = transactions;

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
        setVisible(false);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void addTransaction(String transactionDetails) {
        // Create a new JTextArea with the provided transaction details
        JTextArea transactionTextArea = new JTextArea(transactionDetails);
        transactionTextArea.setEditable(false);

        // Add the new transaction to the display
        JPanel mainPanel = (JPanel) getContentPane().getComponent(0);
        mainPanel.add(transactionTextArea);

        // Repaint the TransactionHistoryFrame to update the display
        revalidate();
        repaint();
    }

    public void setBackButtonListener(ActionListener actionListener){
        this.backButton.addActionListener(actionListener);
    }

    public void update() {
        // Clear the existing transactions to avoid duplicates
        JPanel mainPanel = (JPanel) getContentPane().getComponent(0);
        mainPanel.removeAll();

        // Add each transaction to the display
        for (String transaction : transactions) {
            JTextArea transactionTextArea = new JTextArea(transaction);
            transactionTextArea.setEditable(false);
            mainPanel.add(transactionTextArea);
        }

        // Repaint the TransactionHistoryFrame to update the display
        revalidate();
        repaint();
    }
}
