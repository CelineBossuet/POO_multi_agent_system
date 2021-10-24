package Balls;

public class TestBalls {
    public static void main(String[] args) {
        Balls instance = new Balls();
        System.out.println(instance);
        instance.translate(12, -6);
        System.out.println(instance);
        instance.reInit();
        System.out.println(instance);
    }
}
