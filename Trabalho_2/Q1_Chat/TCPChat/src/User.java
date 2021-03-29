import java.io.IOException;
import java.rmi.UnknownHostException;
import java.util.Scanner;

public class User {
    public static void main(String args[]) {
        TCPClient clientSide = new TCPClient();
        try {
            String nome;
            Scanner sc = new Scanner(System.in);
            System.out.print("Digite seu nome: ");
            nome = sc.nextLine();

            while(true) {
                System.out.print(">> ");
                String msg = sc.nextLine();
                if(msg.equals("exit")) {
                    clientSide.close();
                    System.exit(0);
                } else {
                    if(!msg.equals("")) {
                        String toSend = "[" + nome + "]: " + msg;
                        clientSide.sendRequest(toSend);
                        String response = clientSide.getResponse();
                        System.out.println(response);
                    }
                }
            }
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
