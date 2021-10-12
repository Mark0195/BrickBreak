import java.awt.*;

public class Maps {
    public int map[][];
    public int brickWid;
    public int brickHei;

    public Maps(int row, int col) {
        map = new int[row][col];
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                map[i][j] = 1;
            }
        }

        brickWid = 900/col;
        brickHei = 150/row;
    }
    public void draw(Graphics2D d) {
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                if(map[i][j] > 0) {
                    d.setColor(Color.green);
                    d.fillRect(j * brickWid + 50, i * brickHei + 30, brickWid, brickHei);
                    d.setStroke(new BasicStroke(5));
                    d.setColor(Color.black);
                    d.drawRect(j * brickWid + 50, i * brickHei + 30, brickWid, brickHei);
                }
            }
        }
    }
    public void setBrickCollision(int value, int row, int col){
        map[row][col] = value;
    }
}
