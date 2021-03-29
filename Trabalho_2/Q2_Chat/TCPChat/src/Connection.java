import java.net.*;
import java.io.*;

public class Connection extends Thread {
    private Socket socket = null;
    Despachante despachante = null;

    public Connection(Socket s) {
        socket = s;
        despachante = new Despachante();
        this.start();
    }

    public void run()  {
        try {
            while(true) {
                String received = getRequest();
                if(received.equals("exit")) {
                    break;
                }
                String response = despachante.invoke(received);
                sendResponse(response);
            }
            socket.close();
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
