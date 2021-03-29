import java.net.*;
import java.io.*;

// netstat -tulpn

public class UDPServer{
    public static void main(String args[]){
        DatagramSocket aSocket = null;
        try{
            aSocket = new DatagramSocket(6789);
            // create socket at agreed port
            byte[] buffer = new byte[1000];
            while(true){
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                // CALCULADORA
                String operation = new String(request.getData());
                String [] values = operation.split(" ");

                double result = 0;
                double firstValue = Double.parseDouble(values[1]);
                double secondValue = Double.parseDouble(values[2]);

                if(values[0].equals("-"))
                    result = firstValue - secondValue;
                else if(values[0].equals("+"))
                    result = firstValue + secondValue;
                else if(values[0].equals("*"))
                    result = firstValue * secondValue;
                else if(values[0].equals("/")) {
                    if(secondValue != 0.0) {
                        result = firstValue / secondValue;
                    }
                }
                // FIM CALCULADORA

                String resultSend = String.valueOf(result);

                if(values[0].equals("/") && secondValue == 0.0) {
                    resultSend = "Error";
                }

                byte [] bytes = resultSend.getBytes();

                request.setData(bytes);

                DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(),
                        request.getAddress(), request.getPort());
                aSocket.send(reply);
            }
        }catch (SocketException e){System.out.println("Socket: " + e.getMessage());
        }catch (IOException e) {System.out.println("IO: " + e.getMessage());
        }finally {if(aSocket != null) aSocket.close();}
    }
}
