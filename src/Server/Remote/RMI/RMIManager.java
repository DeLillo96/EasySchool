package Server.Remote.RMI;

import Server.Remote.UserServiceImplementation;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIManager {
    final private int port = 3692;

    public RMIManager() throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(port);

        registry.rebind("users", new UserServiceImplementation());
    }

    public int getPort() {
        return port;
    }
}
