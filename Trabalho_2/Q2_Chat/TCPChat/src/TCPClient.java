import java.net.*;
import java.io.*;

public class TCPClient {
    private Socket socket = null;
    int port;

    public TCPClient() {
        port = 4321;
        try {
            socket = new Socket("localhost", port);
        } catch (UnknownHostException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }

    public void sendRequest(String request) throws IOException {
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeUTF(request);
    }

    public String getResponse() throws IOException {
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        return dis.readUTF();
    }

    public void close() throws IOException {
        if(socket != null) {
            socket.close();
        }
    }
}