import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.*;
import java.awt.Rectangle;

public class Game extends JPanel implements KeyListener, ActionListener{
    private boolean play = false;
    private boolean gameOver = false;
    private int score = 0;
    private int totalOfBricks = 36;
    private Timer time;
    private int delay = 8;
    private int playX = 400;
    private int ballPositionX = 150;
    private int ballPositionY = 350;
    private int ballXDirection = -1;
    private int ballYDirection = -2;
    private Maps map;

    public Game() {
        map = new Maps(6, 6);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        time = new Timer(delay, this);
        time.start();
    }

    public void paint(Graphics graphics) {
        boolean prevGameOver = gameOver;
        graphics.setColor(Color.pink);
        graphics.fillRect(1, 1, 998, 792);

        map.draw((Graphics2D)graphics);

        graphics.setColor(Color.red);
        graphics.fillRect(0, 0, 4, 792);
        graphics.fillRect(0, 0, 998, 4);
        graphics.fillRect(997, 0, 4, 792);

        graphics.setColor(Color.black);
        graphics.fillRect(playX, 750, 100, 8);

        graphics.setColor(Color.white);
        graphics.fillOval(ballPositionX, ballPositionY, 30, 30);

        graphics.setColor(Color.black);
        graphics.setFont(new Font("serif", Font.BOLD, 30));
        graphics.drawString("" + score, 870, 25);

        if(totalOfBricks <= 0) {
            gameOver = true;
            play = false;
            ballXDirection = 0;
            ballYDirection = 0;
            graphics.setColor(Color.black);
            graphics.setFont(new Font("serif", Font.BOLD, 50));
            graphics.drawString("Victory!!! Your Current Score is: " + score, 130, 300);
            graphics.setFont(new Font("serif", Font.BOLD, 50));
            graphics.drawString("To play again, please press enter", 190, 350);
        }

        if (ballPositionY > 990) {
            gameOver = true;
            play = false;
            ballXDirection = 0;
            ballYDirection = 0;
            graphics.setColor(Color.black);
            graphics.setFont(new Font("serif", Font.BOLD, 50));
            graphics.drawString("Game Over!!! Your Score is: " + score, 150, 300);
            graphics.setFont(new Font("serif", Font.BOLD, 50));
            graphics.drawString("To replay, please press enter", 190, 350);
        }

        if(prevGameOver == false && gameOver == true){
            this.effect();
        }

        graphics.dispose();
    }

    public void effect(){
        if (totalOfBricks <= 0){
            String filepath = "samus-victory-theme.wav";
            SoundEffects sound = new SoundEffects();
            sound.playNewSound(filepath);
        }
        if (ballPositionY > 990){
            String filepath = "supermariodead.wav";
            SoundEffects sound = new SoundEffects();
            sound.playNewSound(filepath);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) { 
        time.start();
        if(play) {
            if(new Rectangle(ballPositionX, ballPositionY, 30, 30).intersects(new Rectangle(playX, 750, 100, 8))) {
                ballYDirection = -ballYDirection;
            }

            A: for(int i = 0; i < map.map.length; i++) {
                for(int j = 0; j < map.map.length; j++) {
                    if(map.map[i][j] > 0) {
                        int brickX = j* map.brickWid + 50;
                        int brickY = i* map.brickHei + 30;
                        int brickWid = map.brickWid;
                        int brickHei = map.brickHei;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWid, brickHei);
                        Rectangle ballRect = new Rectangle(ballPositionX, ballPositionY, 30, 30);
                        Rectangle brickRect = rect;

                        if(ballRect.intersects(brickRect)) {
                            map.setBrickCollision(0, i, j);
                            totalOfBricks--;
                            score += 10;

                            if(ballPositionX + 19 <= brickRect.x || ballPositionX + 1 >= brickRect.x + brickRect.width) {
                                ballXDirection = -ballXDirection;
                            } else {
                                ballYDirection = -ballYDirection;
                            }

                            break A;
                        }
                    }
                }
            }
            ballPositionX += ballXDirection;
            ballPositionY += ballYDirection;
            if(ballPositionX < 0) {
                ballXDirection = -ballXDirection;
            }
            if(ballPositionY < 0) {
                ballYDirection = -ballYDirection;
            }
            if(ballPositionX > 970) {
                ballXDirection = -ballXDirection;
            }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(playX > 900){
                playX = 900;
            } else{
                movePlayRight();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playX < 25){
                playX = 25;
            } else{
                movePlayLeft();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!play) {
                gameOver = false;
                play = false;
                ballPositionX = 150;
                ballPositionY = 350;
                ballXDirection = -1;
                ballYDirection = -2;
                playX = 0;
                score = 0;
                totalOfBricks = 36;
                map = new Maps(6, 6);

                repaint();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_C){
            totalOfBricks = 0;
            for(int i = 0; i < map.map.length; i++){
                for(int j = 0; j < map.map[i].length; j++){
                    map.setBrickCollision(0, i, j);
                }
            }
        }
    }
    public void movePlayRight() {
        play = true;
        playX += 30;
    }

    public void movePlayLeft() {
        play = true;
        playX -= 30;
    }
    
}
