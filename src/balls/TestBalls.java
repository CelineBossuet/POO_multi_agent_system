package Balls;

public class TestBalls {
    public static void main(String[] args) {

        Balls balls = new Balls(5);

        System.out.println(balls);

        balls.translate(2, 0);

        System.out.println(balls);

        balls.reInit();

        System.out.println(balls);

    }
}
