import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TCPServer {
    public static Map<String, String> hist = new HashMap<String, String>();

    public static void main (String args[]) {
        try{
            int serverPort = 7896; // the server port
            ServerSocket listenSocket = new ServerSocket(serverPort);

            while(true) {
                Socket clientSocket = listenSocket.accept();
                Connection c = new Connection(clientSocket, hist);
            }

        } catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());}
    }
}

class Connection extends Thread {
    DataInputStream  dis;
    DataOutputStream dos;
    Socket clientSocket;
    Map<String, String> historico;

    public Connection (Socket aClientSocket, Map<String, String> m) {
        try {
            clientSocket = aClientSocket;
            dis = new DataInputStream (clientSocket.getInputStream());
            dos = new DataOutputStream(clientSocket.getOutputStream());
            historico = m;

            this.start();
        } catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
    }

    public void run(){
        try {
            String id = dis.readUTF();
            String formula = dis.readUTF();

            if(formula.equals("historico")) {
                String m = historico.get(id);
                if(m == null) {
                    dos.writeUTF("NaN");
                } else {
                    dos.writeUTF(m);
                }
                return;
            }

            String eq [] = new String(formula).split(" ");
            double a, b;
            double res = 0;

            a = Double.parseDouble(eq[1]);
            b = Double.parseDouble(eq[2]);

            if(eq[0].equals("+")) {
                res = a + b;
            }
            if(eq[0].equals("-")) {
                res = a - b;
            }
            if(eq[0].equals("*")) {
                res = a * b;
            }
            if(eq[0].equals("/")) {
                res = a / b;
            }

            String toSend = eq[0] + " " + eq[1]  + " " + eq[2] + " = " + String.valueOf(res);

            historico.put(id, toSend);

            dos.writeUTF(String.valueOf(res));



        }catch (EOFException e){System.out.println("EOF:"+e.getMessage());
        } catch(IOException e) {System.out.println("readline:"+e.getMessage());
        } finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}
    }
}