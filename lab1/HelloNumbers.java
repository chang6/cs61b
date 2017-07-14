public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
        int out = x;
        while (x < 9) {
        	out = out + x;
            System.out.print(out + " ");
            x = x + 1;
        }
        out = out + x;
        System.out.println(out);
    }
}