import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPServer {
    public static void main (String args[]) {
        try{
            int serverPort = 7896;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while(true) {
                Socket clientSocket = listenSocket.accept();
                Connection c = new Connection(clientSocket);
            }
        } catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());}
    }
}
class Connection extends Thread {
    DataInputStream dis;
    DataOutputStream dos;
    Socket clientSocket;

    public Connection (Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            dis = new DataInputStream (clientSocket.getInputStream());
            dos = new DataOutputStream(clientSocket.getOutputStream());
            this.start();
        } catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
    }

    public void run(){
        Scanner scn = new Scanner(System.in);
        String enviar;
        String receber;

        try {
            while(true) {
                receber = dis.readUTF();

                if(receber.equals("exit")) {
                    System.out.println("Fechando conexao...");
                    clientSocket.close();
                    System.out.println("Conexao fechada");
                    break;
                }

                System.out.println(receber);

                System.out.print(">> ");
                enviar = scn.nextLine();

                if(enviar.equals("exit")) {
                    System.out.println("Fechando conexao...");
                    clientSocket.close();
                    System.out.println("Conexao fechada");
                    break;
                }

                dos.writeUTF("[S]: " + enviar);
            }
        }catch (EOFException e){System.out.println("EOF:"+e.getMessage());
        } catch(IOException e) {System.out.println("readline:"+e.getMessage());
        } finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}


    }
}