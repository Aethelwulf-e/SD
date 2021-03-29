import java.net.*;
import java.io.*;

import com.google.gson.*;

public class Cliente {
    private Socket socket = null;
    private int port;
    private String host;
    private Mensagem mensagem;

    public Cliente() {
        host = "localhost";
        port = 4321;
        try {
            socket = new Socket(host, port);
        } catch (UnknownHostException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }

    public void doOperation(String remoteObject, String methodID, String arguments, int requestID) {
        mensagem = new Mensagem(0, requestID, Integer.parseInt(methodID), remoteObject, arguments);
        this.sendRequest(mensagem.toString());
    }

    public void sendRequest(String request) {
        try {
            OutputStream out = socket.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(out);
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.flush();

            Gson gson = new Gson();

            buffer.write(gson.toJson(request));
            buffer.flush();
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }

    public String getResponse() {
        String reply = "";
        Gson gson = new Gson();
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            reply = gson.fromJson(buffer.readLine(), String.class);
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
        return reply;
    }

    public void close() {
        try {
            if(socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }
}
