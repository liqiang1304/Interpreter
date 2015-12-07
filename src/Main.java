import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String result = "";
        while (true){
            System.out.println("calc> ");
            String text = sc.nextLine();
            Interpreter interpreter = new Interpreter(text);
            try {
                result = interpreter.expr();
                System.out.println(result);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
