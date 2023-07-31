import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

public class Frame3{
            private JFrame testingFrame;
            private JButton tvendButton, tmaintButton, returnButton;

            public Frame3(){
                this.testingFrame = new JFrame("Test Vending Machine");

                this.testingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.testingFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
                this.testingFrame.setSize(250, 400);

                this.tvendButton = new JButton("Test Vending Features");
                this.tvendButton.setPreferredSize(new Dimension(220,30));
                this.tmaintButton = new JButton("Test Maintenance Features");
                this.tmaintButton.setPreferredSize(new Dimension(220,30));
                this.returnButton = new JButton("Return");
                this.returnButton.setPreferredSize(new Dimension(220,30));

                testingFrame.add(tvendButton);
                testingFrame.add(tmaintButton);
                testingFrame.add(returnButton);

                testingFrame.setVisible(false);
            }

           public JFrame getTestingFrame(){
                       return this.testingFrame;
           }

           public void setTvendButtonListener(ActionListener actionListener){
                this.tvendButton.addActionListener(actionListener);
            }

            public void setTmaintButtonListener(ActionListener actionListener){
                this.tmaintButton.addActionListener(actionListener);
            }

            public void setReturnButtonListener(ActionListener actionListener){
                this.returnButton.addActionListener(actionListener);
            }
}