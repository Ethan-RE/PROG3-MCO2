import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;import javax.swing.JButton;
import javax.swing.JTextArea;import javax.swing.JButton;
                             import javax.swing.JTextArea;
import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FactoryView {
    private JFrame mainFrame;
    private JPanel mainPanel;
    private JButton createButton, testButton, exitButton;

    public FactoryView(){
        this.mainFrame = new JFrame("Vending Machine Factory");

        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.mainFrame.setSize(250, 400);

        this.createButton = new JButton("Create Vending Machine");
        this.createButton.setPreferredSize(new Dimension(220,30));
        this.testButton = new JButton("Test Vending Machine");
        this.testButton.setPreferredSize(new Dimension(220,30));
        this.exitButton = new JButton("Exit");
        this.exitButton.setPreferredSize(new Dimension(220,30));

        mainFrame.add(this.createButton);
        mainFrame.add(this.testButton);
        mainFrame.add(this.exitButton);

        mainFrame.setVisible(true);
    }

    public JFrame getMainFrame(){
        return this.mainFrame;
    }

    public void setCreateButtonListener(ActionListener actionListener){
        this.createButton.addActionListener(actionListener);
    }

    public void setTestButtonListener(ActionListener actionListener){
        this.testButton.addActionListener(actionListener);
    }

    public void setExitButtonListener(ActionListener actionListener){
        this.exitButton.addActionListener(actionListener);
    }
}