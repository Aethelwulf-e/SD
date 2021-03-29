import java.net.*;
import java.io.*;

public class TCPClient {
    private Socket socket = null;
    int port;

    public TCPClient() {
        port = 1234;
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
        String received;
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        received = dis.readUTF();
        return received;
    }

    public void close() throws IOException {
        if(socket != null) {
            socket.close();
        }
    }
}