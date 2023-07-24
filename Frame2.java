import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

public class Frame2{
            private JFrame creationFrame;
            private JButton regularButton, specialButton, returnButton;

            public Frame2(){
                this.creationFrame = new JFrame("Create Vending Machine");

                this.creationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.creationFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
                this.creationFrame.setSize(250, 400);

                this.regularButton = new JButton("Regular Vending Machine");
                this.regularButton.setPreferredSize(new Dimension(220,30));
                this.specialButton = new JButton("Special Vending Machine");
                this.specialButton.setPreferredSize(new Dimension(220,30));
                this.returnButton = new JButton("Return");
                this.returnButton.setPreferredSize(new Dimension(220,30));

                creationFrame.add(regularButton);
                creationFrame.add(specialButton);
                creationFrame.add(returnButton);

                creationFrame.setVisible(false);
            }

           public JFrame getCreationFrame(){
                       return this.creationFrame;
           }

           public void setRegularButtonListener(ActionListener actionListener){
           this.regularButton.addActionListener(actionListener);
           }

                public void setSpecialButtonListener(ActionListener actionListener){
                    this.specialButton.addActionListener(actionListener);
                }

                public void setReturnButtonListener(ActionListener actionListener){
                    this.returnButton.addActionListener(actionListener);
                }
}