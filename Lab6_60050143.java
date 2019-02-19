import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.util.concurrent.TimeUnit;

class Lab6_60050143 extends JPanel implements Runnable {
    public static void main(String[] args) {
        Lab6_60050143 m = new Lab6_60050143();
        JFrame f = new JFrame();
        f.add(m);
        f.setTitle("Lab6");
        f.setSize(600, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        (new Thread(m)).start();
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, 600, 600);
        g2.setColor(Color.BLACK);
        g2.translate(circleMove, 0);
        g2.drawOval(0, 0, 100, 100);
        g2.translate(-circleMove, 0);
        // g2.rotate(squareRotate, 300, 300);
        g2.translate(0, squareMove);
        g2.drawRect(50, 450, 100, 100);
    }

    double circleMove = 0;

    double squareMove = 0;
    double temp;
    double temp2;
    double startTime = System.currentTimeMillis();

    public void run() {
        double lastTime = System.currentTimeMillis();
        double currentTime, elapsedTime;

        while (true) {
            currentTime = System.currentTimeMillis();
            elapsedTime = currentTime - lastTime;

            // TODO: update logics
            // Displayx
            int seconds = (int) ((currentTime - startTime) / 1000);
            if (circleMove >= 480) {
                // System.out.println("on 470");
                temp = (50.0 * elapsedTime / 1000.0) * -1;
            } else if (circleMove <= 50)
                temp = 50.0 * elapsedTime / 1000.0;

            circleMove += temp;
            if (seconds >= 3) {
                if (squareMove <= -450) {
                    // System.out.println("on 470");
                    temp2 = 0;

                } else if (squareMove >= 0)

                    temp2 = (100.0 * elapsedTime / 1000.0) * -1;

                squareMove += temp2;
            }
            // squareRotate += 0.5 * elapsedTime / 1000.0;

            repaint();
            lastTime = currentTime;
            // take a little nap: 60fps
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
    }
}