import java.io.IOException;
import java.rmi.UnknownHostException;
import java.util.Scanner;

public class User {
    public static void main(String args[]) {
        Proxy proxy = new Proxy();
        try {
            String nome;
            Scanner sc = new Scanner(System.in);
            System.out.print("Digite seu nome: ");
            nome = sc.nextLine();

            while(true) {
                System.out.print(">> ");
                String msg = sc.nextLine();
                if(msg.equals("exit")) {
                    proxy.close();
                    System.exit(0);
                } else {
                    if(!msg.equals("")) {
                        msg = "[" + nome + "]: " + msg;
                        System.out.println(proxy.sendMsg(msg));
                    }
                }
            }
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
