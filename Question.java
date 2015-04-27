import java.awt.*;
import javax.swing.*;

public class Question {

    public Question() {
        JFrame f = new JFrame();
        f.setLayout(new BorderLayout());
        //f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, 1));
        for (int i = 0; i < 10; i++) {
            panel.add(new JButton("kjdh"));
        }
        JScrollPane scrollPane = new JScrollPane(panel);
        f.getContentPane().add(scrollPane);
        f.pack();
        //f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                new Question();
            }
        };
        SwingUtilities.invokeLater(r);
    }
}