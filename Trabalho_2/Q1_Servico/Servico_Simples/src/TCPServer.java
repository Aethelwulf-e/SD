import java.net.*;
import java.io.*;

public class TCPServer {
    public static void main(String args[]) {
        new TCPServer();
    }

    private Socket socket = null;
    int port;
    Validator validator = null;

    public TCPServer() {
        port = 4321;
        try {
            ServerSocket listenSocket = new ServerSocket(port);
            validator = new Validator();
            while (true) {
                socket = listenSocket.accept();
                String request = this.getRequest();
                this.sendResponse(request);
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
        String toSend;
        toSend = validator.validar(request);

        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeUTF(toSend);
    }
}