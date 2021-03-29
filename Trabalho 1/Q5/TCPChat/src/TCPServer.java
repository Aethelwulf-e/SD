import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class TCPServer {
    public static ArrayList < Connection > clients = new ArrayList < Connection > ();

    public static void main(String args[]) {
        try {
            int serverPort = 8080;
            ArrayList < String > historico = new ArrayList < String > ();
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while (true) {
                Socket clientSocket = listenSocket.accept();
                Connection c = new Connection(clientSocket, historico, clients);
                clients.add(c);
            }
        } catch (IOException e) {
            System.out.println("Listen socket: " + e.getMessage());
        }
    }
}

class Connection extends Thread {
    DataInputStream dis;
    DataOutputStream dos;
    Socket clientSocket;
    ArrayList < String > historico;
    ArrayList < Connection > clientsArr;

    public Connection(Socket aClientSocket, ArrayList < String > historico, ArrayList < Connection > clients) {
        try {
            clientSocket = aClientSocket;
            dis = new DataInputStream(clientSocket.getInputStream());
            dos = new DataOutputStream(clientSocket.getOutputStream());
            this.historico = historico;
            this.clientsArr = clients;
            this.start();
        } catch (IOException e) {
            System.out.println("Connection: " + e.getMessage());
        }
    }

    public void run() {
        ArrayList <Connection> arr = this.clientsArr;
        String line;
        String enviar = "";

        try {
            while (true) {
                line = dis.readUTF();

                if (line.equals("exit")) {
                    System.out.println("Cliente " + this.clientSocket + " desconectou-se");
                    this.clientSocket.close();
                    break;
                }

                if (!line.equals("")) {
                    for (Connection c : arr) {
                        if (c != null && c != this) {
                            //c.historico.add(line);
                            c.dos.writeUTF(line);
                            c.dos.flush();
                        } else {
                            c.dos.writeUTF("");
                        }
                    }

                } else {
                    for (Connection c : arr) {
                        if (c != null && c == this) {
                            for (String word : c.historico) {
                                enviar += word + "\n";
                            }
                            c.dos.writeUTF(enviar);
                            c.historico.clear();
                            break;
                        }
                    }
                }
            }

        } catch (EOFException e) {
            System.out.println("EOF: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("readline: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                /*close failed*/ }
        }
    }
}