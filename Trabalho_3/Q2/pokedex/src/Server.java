import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    public static void main(String args[]) {
        System.setSecurityManager(new SecurityManager());
        try {
            InterfaceRemota refServant = new Servant();
            InterfaceRemota stub = (InterfaceRemota) UnicastRemoteObject.exportObject(refServant, 0);
            Naming.rebind("Pokedex", refServant);
            System.out.println("Ready to server...");
        } catch (Exception e) {
            System.out.println("Fail to run Server: " + e.getMessage());
        }
    }
}
