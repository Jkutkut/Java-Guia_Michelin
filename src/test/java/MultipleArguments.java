public class MultipleArguments {

    private static void call(int... args) {
        for (int arg : args) {
            System.out.println(arg);
        }
    }

    public static void main(String[] args) {
        call(1, 2, 3);
        call(new int[]{1, 2, 3});
    }
}
