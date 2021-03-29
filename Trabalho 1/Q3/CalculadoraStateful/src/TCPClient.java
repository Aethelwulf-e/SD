import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPClient {
    public static void main (String args[]) {
        Scanner scn = new Scanner(System.in);
        Socket s = null;

        try{
            System.out.println("Escolha um ID: ");
            String ID = scn.nextLine();

            System.out.println("Entre com a formula: ");
            String formula = scn.nextLine();

            int serverPort = 7896;
            s = new Socket("localhost", serverPort);
            DataInputStream  dis = new DataInputStream (s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            dos.writeUTF(ID);
            dos.writeUTF(formula);

            String result = dis.readUTF();
            System.out.println(result);

        }catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
        }catch (EOFException e){System.out.println("EOF:"+e.getMessage());
        }catch (IOException e){System.out.println("readline:"+e.getMessage());
        }finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
    }
}