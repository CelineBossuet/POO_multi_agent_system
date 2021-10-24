package balls;

public class TestBalls {
    public static void main(String[] args) {
        Balls balls = new Balls();
        System.out.println(balls);

        balls.translate(1, 3);
        System.out.println(balls);

        balls.reInit();
        System.out.println(balls);
    }

}
