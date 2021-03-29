import java.net.*;
import java.io.*;
import java.util.Scanner;

public class UDPClient{
    public static void main(String args[]){
        Scanner scn = new Scanner(System.in);
        DatagramSocket aSocket = null;
        System.out.print("Entre com a formula: ");
        String msg = scn.nextLine();
        try {
            aSocket = new DatagramSocket();
            byte [] m = msg.getBytes();			// MUDAR AQUI
            InetAddress aHost = InetAddress.getByName("localhost");
            int serverPort = 6789;
            DatagramPacket request =
                    new DatagramPacket(m,  msg.length(), aHost, serverPort);		// MUDAR AQUI
            aSocket.send(request);
            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);
            System.out.println("Reply: " + new String(reply.getData()));
        }catch (SocketException e){System.out.println("Socket: " + e.getMessage());
        }catch (IOException e){System.out.println("IO: " + e.getMessage());
        }finally {if(aSocket != null) aSocket.close();}
    }
}