import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPClient {
    public static void main(String args[]) {
        Socket s = null;
        Scanner read = new Scanner(System.in);
        String nome;

        System.out.println("Digite seu nome: ");
        nome = read.nextLine();

        try {
            int serverPort = 8080;
            s = new Socket("localhost", serverPort);

            DataInputStream  dis = new DataInputStream (s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

//            String data = dis.readUTF();
//            System.out.println("Received: " + data);

            while (true) {
                System.out.print(">> ");
                String enviar = read.nextLine();

                if (enviar.equals("exit")) {
                    dos.writeUTF(enviar);
                    System.out.println("Fechando conexao...");
                    s.close();
                    System.out.println("Conexao fechada.");
                    break;
                }

                if (!enviar.equals("")) {
                    enviar = "[ " + nome + " ]: " + enviar + "\n";
                }

                dos.writeUTF(enviar);
                String receber = dis.readUTF();
                System.out.println(receber);
            }
            s.close();
        } catch (UnknownHostException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("readline: " + e.getMessage());
        } finally {
            if (s != null) try {
                s.close();
            } catch (IOException e) {
                System.out.println("close: " + e.getMessage());
            }
        }
    }
}