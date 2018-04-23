package Server.Remote.RMI;

import Server.Remote.ChildrenServiceImplementation;
import Server.Remote.DishServiceImplementation;
import Server.Remote.MenuServiceImplementation;
import Server.Remote.UserServiceImplementation;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIManager {
    final private int port = 3692;

    public RMIManager() throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(port);

        registry.rebind("users", new UserServiceImplementation());
        registry.rebind("children", new ChildrenServiceImplementation());
        registry.rebind("menu", new MenuServiceImplementation());
        registry.rebind("dish", new DishServiceImplementation());
    }

    public int getPort() {
        return port;
    }
}
