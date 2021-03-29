import java.net.*;
import java.io.*;

public class TCPServer {
    public static void main(String args[]) {
        new TCPServer();
    }

    private Socket socket = null;
    int port;
    Calculadora calculadora = null;

    public TCPServer() {
        port = 1234;
        calculadora = new Calculadora();
        try {
            ServerSocket listenSocket = new ServerSocket(port);
            while(true) {
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
        String [] splitted = request.split(" ");
        if(splitted.length < 3) {
            toSend = "Error";
        } else {
            String op = splitted[0];
            double val1 = Double.parseDouble(splitted[1]);
            double val2 = Double.parseDouble(splitted[2]);
            double result = 0;

            if(op.equals("+")) {
                result = calculadora.add(val1, val2);
                toSend = String.valueOf(result);
            } else if(op.equals("-")) {
                result = calculadora.sub(val1, val2);
                toSend = String.valueOf(result);
            } else if(op.equals("*")) {
                result = calculadora.mult(val1, val2);
                toSend = String.valueOf(result);
            } else if(op.equals("/")) {
                if(val2 != 0.0) {
                    result = calculadora.div(val1, val2);
                    toSend = String.valueOf(result);
                } else {
                    toSend = "Error";
                }
            } else {
                toSend = "Error";
            }
        }
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeUTF(toSend);
    }
}