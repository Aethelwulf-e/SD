import java.io.IOException;

public class Proxy {

    TCPClient c = null;

    public Proxy() {
        c = new TCPClient();
    }

    double add(double op1, double op2) throws IOException {
        c.sendRequest("+ " + op1 + " " + op2);
        String response = c.getResponse();
        return Double.parseDouble(response);
    }

    double sub(double op1, double op2) throws IOException {
        c.sendRequest("- " + op1 + " " + op2);
        String response = c.getResponse();
        return Double.parseDouble(response);
    }

    double mult(double op1, double op2) throws IOException {
        c.sendRequest("* " + op1 + " " + op2);
        String response = c.getResponse();
        return Double.parseDouble(response);
    }

    double div(double op1, double op2) throws IOException {
        c.sendRequest("/ " + op1 + " " + op2);
        String response = c.getResponse();
        return Double.parseDouble(response);
    }

    public void close() {
        try {
            if(c != null) {
                c.close();
            }
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }
}