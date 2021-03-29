import java.rmi.*;
import java.util.ArrayList;

public interface InterfaceRemota extends Remote {
    ArrayList<Pokemon> getPokedex() throws RemoteException;

    Pokemon Battle(String name_1, String name_2) throws RemoteException;

    ArrayList<Pokemon> GenerateRandomTeam(int generateNumber) throws RemoteException;

    Pokemon getPokemonStats(String name) throws RemoteException;
    
    void generateFileTeam(ArrayList<Pokemon> team, String caminho) throws RemoteException;
}