import java.io.IOException;
import java.rmi.UnknownHostException;
import java.util.Scanner;

public class User {
    public static void main(String args[]) {
        TCPClient clientSide = new TCPClient();
        Scanner sc = new Scanner(System.in);

        try {
            String c;
            while(true) {
                System.out.print("Digite curiosidade: ");
                c = sc.nextLine();
                if(c.equals("curiosidade") || c.equals("Curiosidade")) {
                    break;
                } else {
                    System.out.println(":(");
                }
            }

            clientSide.sendRequest(c);
            String resp = clientSide.getResponse();

            System.out.println(resp);

            clientSide.close();
            sc.close();
        } catch (UnknownHostException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            try {
                clientSide.close();
            } catch (IOException e) {
                System.out.println("IO: " + e.getMessage());
            }
        }
    }
}
