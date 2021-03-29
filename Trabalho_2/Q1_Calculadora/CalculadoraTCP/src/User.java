import java.io.IOException;
import java.util.Scanner;

public class User {
    public static void main(String args[]) {
        try {
            TCPClient clientSide = new TCPClient();

            Scanner sc = new Scanner(System.in);
            System.out.println("Entre com a expressao:");
            String exp = sc.nextLine();

            clientSide.sendRequest(exp);
            String resp = clientSide.getResponse();
            clientSide.close();

            System.out.println("Valor: " + resp);
            sc.close();
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }
}
