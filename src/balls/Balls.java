package Balls;

public class Balls {
    private int n;
    private Ball[] balls = new Ball[3];
    private int[] default_x = new int[3];
    private int[] default_y = new int[3];

    public Balls(int n){
        this.n = n;
        balls[0] = new Ball(10, 20);
        default_x[0] = 10;
        default_y[0] = 20;
        balls[1] = new Ball(100, 100);
        default_x[1] = 100;
        default_y[1] = 100;
        balls[2] = new Ball(200, 20);
        default_x[2] = 200;
        default_y[2] = 20;
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

    public Ball[] getBalls(){
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
