import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class TCPServer {
    public static ArrayList < Connection > clients = new ArrayList < Connection > ();

    public static void main(String args[]) {
        try {
            int serverPort = 8080;
            ArrayList < String > historico = new ArrayList < String > ();
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while (true) {
                Socket clientSocket = listenSocket.accept();
                Connection c = new Connection(clientSocket, historico, clients);
                clients.add(c);
            }
        } catch (IOException e) {
            System.out.println("Listen socket: " + e.getMessage());
        }
    }
}

class Connection extends Thread {
    DataInputStream dis;
    DataOutputStream dos;
    Socket clientSocket;
    ArrayList < String > historico;
    ArrayList < Connection > clientsArr;

    public Connection(Socket aClientSocket, ArrayList < String > historico, ArrayList < Connection > clients) {
        try {
            clientSocket = aClientSocket;
            dis = new DataInputStream(clientSocket.getInputStream());
            dos = new DataOutputStream(clientSocket.getOutputStream());
            this.historico = historico;
            this.clientsArr = clients;
            this.start();
        } catch (IOException e) {
            System.out.println("Connection: " + e.getMessage());
        }
    }

    public void run() {
        ArrayList <Connection> arr = this.clientsArr;
        String line;
        String enviar = "";

        try {
            while (true) {
                line = dis.readUTF();

                if (line.equals("exit")) {
                    System.out.println("Cliente " + this.clientSocket + " desconectou-se");
                    this.clientSocket.close();
                    break;
                }

                if (line.equals("curiosidade")) {
                    String a = "Elásticos duram mais quando refrigerados.";
                    String b = "O tubarão é o único peixe que pode piscar com ambos os olhos.";
                    String c = "Existem mais galinhas do que pessoas no mundo.";
                    String d = "Um gato possui 32 músculos em cada orelha.";
                    String e = "A vida útil de uma libélula é de 1 a 6 meses.";
                    String f = "É impossível espirrar de olhos abertos.";
                    String g = "Se você manter um peixe dourado em quarto escuro, ele eventualmente ficará branco.";
                    String h = "A cadeira elétrica foi inventada por um dentista.";

                    Random r = new Random();
                    int rndm = r.nextInt(7);
                    rndm += 1;

                    if(rndm == 1) {
                        dos.writeUTF(a);
                        dos.flush();
                    }
                    else if(rndm == 2) {
                        dos.writeUTF(b);
                        dos.flush();
                    }
                    else if(rndm == 3) {
                        dos.writeUTF(c);
                        dos.flush();
                    }
                    else if(rndm == 4) {
                        dos.writeUTF(d);
                        dos.flush();
                    }
                    else if(rndm == 5) {
                        dos.writeUTF(e);
                        dos.flush();
                    }
                    else if(rndm == 6) {
                        dos.writeUTF(f);
                        dos.flush();
                    }
                    else if(rndm == 7) {
                        dos.writeUTF(g);
                        dos.flush();
                    }
                    else {
                        dos.writeUTF(h);
                        dos.flush();
                    }
                }

                if (!line.equals("")) {
                    for (Connection c : arr) {
                        if (c != null && c != this) {
                            //c.historico.add(line);
                            c.dos.writeUTF(line);
                            c.dos.flush();
                        } else {
                            c.dos.writeUTF("");
                        }
                    }

                } else {
                    for (Connection c : arr) {
                        if (c != null && c == this) {
                            for (String word : c.historico) {
                                enviar += word + "\n";
                            }
                            c.dos.writeUTF(enviar);
                            c.historico.clear();
                            break;
                        }
                    }
                }
            }

        } catch (EOFException e) {
            System.out.println("EOF: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("readline: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                /*close failed*/ }
        }
    }
}