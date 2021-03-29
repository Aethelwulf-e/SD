import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Connection extends Thread {
    private Socket socket = null;

    public Connection(Socket s) {
        socket = s;
        this.start();
    }

    public void run()  {
        try {
            while(true) {
                String received = getRequest();
                if(received.equals("exit")) {
                    break;
                }
                sendResponse(received);
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
        String toSend = "";
        Scanner sc = new Scanner(System.in);
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        System.out.println(request);

        System.out.print(">> ");
        toSend = sc.nextLine();

        dos.writeUTF("[S]: " + toSend);
    }
}
