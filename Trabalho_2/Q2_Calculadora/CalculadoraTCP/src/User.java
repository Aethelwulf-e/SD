import java.io.IOException;
import java.util.Scanner;

public class User {
    public static void main(String args[]) {
        Proxy proxy = new Proxy();
        Scanner sc = new Scanner(System.in);

        try {
            double op1, op2;
            String r;

            System.out.print("Entre com a operacao: ");
            r = sc.nextLine();

            System.out.print("Digite o primeiro valor: ");
            op1 = Double.parseDouble(sc.nextLine());

            System.out.print("Digite o segundo valor: ");
            op2 = Double.parseDouble(sc.nextLine());

            double result = 0;
            String sendError = "Error";

            if(r.equals("+")) {
                result = proxy.add(op1, op2);
            } else if(r.equals("-")) {
                result = proxy.sub(op1, op2);
            } else if(r.equals("*")) {
                result = proxy.mult(op1, op2);
            } else if(r.equals("/") && op2 != 0.0) {
                result = proxy.div(op1, op2);
            } else {
                System.out.println(sendError);
                sc.close();
                System.exit(0);
            }

            System.out.println(result);

            proxy.close();
            sc.close();
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
            proxy.close();
        }
    }
}
