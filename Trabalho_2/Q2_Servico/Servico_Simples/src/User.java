import java.io.IOException;
import java.rmi.UnknownHostException;
import java.util.Scanner;

public class User {
    public static void main(String args[]) {
        Proxy proxy = new Proxy();
        try {
            String c;
            Scanner sc = new Scanner(System.in);

            while(true) {
                System.out.print("Digite curiosidade: ");
                c = sc.nextLine();
                if(c.equals("curiosidade") || c.equals("Curiosidade")) {
                    break;
                } else {
                    System.out.println(":(");
                }
            }

            System.out.println(proxy.sendMsg(c));
            proxy.close();
            sc.close();

        } catch (UnknownHostException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            try {
                proxy.close();
            } catch (IOException e) {
                System.out.println("IO: " + e.getMessage());
            }
        }
    }
}
