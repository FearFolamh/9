import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Main extends JPanel {
    private final String[] strings = {"Привет", "Мир", "Java", "Swing", "AWT"};
    private final int[] xPositions;
    private final int[] yPositions;
    private final int[] xVelocities;
    private final int[] yVelocities;

    public Main() {
        Random random = new Random();
        int stringCount = strings.length;
        xPositions = new int[stringCount];
        yPositions = new int[stringCount];
        xVelocities = new int[stringCount];
        yVelocities = new int[stringCount];

        for (int i = 0; i < stringCount; i++) {
            xPositions[i] = random.nextInt(300);
            yPositions[i] = random.nextInt(300);
            xVelocities[i] = random.nextInt(5) + 1;
            yVelocities[i] = random.nextInt(5) + 1;
        }

        Timer timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < stringCount; i++) {
                    xPositions[i] += xVelocities[i];
                    yPositions[i] += yVelocities[i];

                    if (xPositions[i] < 0 ||  xPositions[i] > getWidth() - 50) {
                        xVelocities[i] *= -1;
                    }
                    if (yPositions[i] < 0  || yPositions[i] > getHeight() - 10) {
                        yVelocities[i] *= -1;
                    }
                }
                repaint();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        for (int i = 0; i < strings.length; i++) {
            g.drawString(strings[i], xPositions[i], yPositions[i]);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Движение строк");
        Main applet = new Main();
        frame.add(applet);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}