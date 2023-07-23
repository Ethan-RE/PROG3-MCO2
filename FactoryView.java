import javax.swing.*;
import java.awt.*;

public class FactoryView {
    JFrame frame;
    public FactoryView() {
        frame = new JFrame();
        frame.setSize(480,854); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.green);

        Button button = new Button();
    }
}