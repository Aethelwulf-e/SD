import java.net.*;
import java.io.*;

public class TCPServer {
    public static void main(String args[]) {
        new TCPServer();
    }

    private Socket socket = null;
    int port;
    Despachante despachante = null;

    public TCPServer() {
        port = 2234;
        despachante = new Despachante();
        try {
            ServerSocket listenSocket = new ServerSocket(port);
            while(true) {
                socket = listenSocket.accept();
                String request = this.getRequest();
                String resultado = despachante.invoke(request);
                this.sendResponse(resultado);
            }
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }

    public String getRequest() throws IOException {
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        return dis.readUTF();
    }

    public void sendResponse(String request) throws IOException {
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeUTF(request);
    }
}