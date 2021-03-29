import java.net.*;
import java.io.*;

public class TCPServer {
    public static void main(String args[]) {
        new TCPServer();
    }

    private Socket socket = null;
    int port;

    public TCPServer() {
        port = 4321;
        try {
            ServerSocket listenSocket = new ServerSocket(port);
            while(true) {
                socket = listenSocket.accept();
                new Connection(socket);
            }
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }
}