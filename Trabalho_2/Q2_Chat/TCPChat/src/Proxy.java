import java.io.IOException;

public class Proxy {
    TCPClient c = null;

    public Proxy() {
        c = new TCPClient();
    }

    public String sendMsg(String msg) throws IOException {
        c.sendRequest(msg);
        return c.getResponse();
    }

    public void close() throws IOException {
        if(c != null) {
            c.close();
        }
    }
}
