import java.awt.Point;

public class Balls {
    private int n;
    private Point[] balls = new Point[3];
    private int[] default_x = new int[3];
    private int[] default_y = new int[3];

    public Balls(int n){
        this.n = n;
        balls[0] = new Point(10, 20);
        default_x[0] = 1;
        default_y[0] = 2;
        balls[1] = new Point(100, 100);
        default_x[1] = 2;
        default_y[1] = 2;
        balls[2] = new Point(200, 20);
        default_x[2] = 3;
        default_y[2] = 2;
    }

    public void translate(int dx, int dy){
        for(int i = 0; i < balls.length; i++){
            balls[i].translate(dx, dy);
        }
    }

    public void reInit(){
        for(int i = 0; i < balls.length; i++){
            balls[i].setLocation(default_x[i], default_y[i]);
        }
    }

    public Point[] getBalls(){
        return balls;
    }

    @Override
    public String toString(){
        String result = "";
        for(int i = 0; i < balls.length; i++){
            result += "Point " + i + " : x=" + balls[i].getX() + ", y=" + balls[i].getY() + "\n";
        }
        return result;
    }
}
