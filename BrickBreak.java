import javax.swing.*;


public class BrickBreak {
    public static void main(String[] args) {
        String filepath = "bensound-thejazzpiano.wav";
        Music musicObject = new Music();
        musicObject.playSound(filepath);
        JFrame obj = new JFrame();
        Game game = new Game();
        obj.setBounds(25, 25, 1000, 800);
        obj.setTitle("Brick Breaker Fun Time");
        obj.setResizable(true);
        obj.setVisible(true);
        obj.add(game);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}