import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame implements KeyListener, ActionListener {
    private int birdY = 300;
    private int velocity = 0;
    private int gravity = 2;
    private int score = 0;
    private int pipeX = 800;
    private int pipeY = 0;
    private int pipeGap = 200;
    private Timer timer;
    private boolean gameover = false;

    public Main() {
        setTitle("Flappy Bird");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        addKeyListener(this);
        timer = new Timer(20, this);
        timer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.cyan);
        g.fillRect(0, 0, 800, 600);
        g.setColor(Color.orange);
        g.fillRect(pipeX, pipeY, 80, 300);
        g.fillRect(pipeX, pipeY + 400 + pipeGap, 80, 300);
        g.setColor(Color.green);
        g.fillRect(150, birdY, 50, 50);
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.drawString("Score: " + score, 20, 50);
        if (gameover) {
            g.drawString("Game Over", 300, 300);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (!gameover) {
            pipeX -= 5;
            if (pipeX < -80) {
                pipeX = 800;
                pipeY = (int) (Math.random() * 250) - 200;
            }

            velocity += gravity;
            birdY += velocity;

            if (birdY > 550 || birdY < 0) {
                gameover = true;
            } else if (pipeX < 200 && pipeX > 100 && (birdY < pipeY + 300 || birdY > pipeY + 400 + pipeGap)) {
                gameover = true;
            } else {
                score++;
            }
        }
        repaint();
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!gameover) {
                velocity = -20;
            } else {
                // Reset the game if space bar is pressed after game over
                birdY = 300;
                velocity = 0;
                pipeX = 800;
                pipeY = 0;
                score = 0;
                gameover = false;
            }
        }
    }

    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        Main game = new Main();
        game.setVisible(true);
    }
}
