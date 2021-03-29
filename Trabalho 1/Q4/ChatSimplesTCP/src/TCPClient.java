import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPClient {
    public static void main (String args[]) {
        Socket s = null;
        Scanner scn = new Scanner(System.in);

        try{
            int serverPort = 7896;
            s = new Socket("localhost", serverPort);
            DataInputStream  dis  = new DataInputStream (s.getInputStream());
            DataOutputStream dos  = new DataOutputStream(s.getOutputStream());

            System.out.println("Diga seu nome: ");
            String nome = scn.nextLine();

            while(true) {
                System.out.print(">> ");
                String enviar = scn.nextLine();
                dos.writeUTF("[" + nome + "]: " + enviar);
                String receber = dis.readUTF();
                System.out.println(receber);
            }

        }catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
        }catch (EOFException e){System.out.println("EOF:"+e.getMessage());
        }catch (IOException e){System.out.println("readline:"+e.getMessage());
        }finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
    }
}