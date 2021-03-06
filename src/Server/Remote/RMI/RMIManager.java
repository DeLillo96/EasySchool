package Server.Remote.RMI;

import Server.Remote.*;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Class used for RMI services, replaces the binding for the specified name contained in registry with an implementation
 * of the required services interface
 */
public class RMIManager {
    final private int port = 3692;

    public RMIManager() throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(port);

        registry.rebind("user", new UserServiceImplementation());
        registry.rebind("child", new ChildrenServiceImplementation());
        registry.rebind("adult", new AdultServiceImplementation());
        registry.rebind("supplier", new SupplierServiceImplementation());
        registry.rebind("pediatrician", new PediatricianServiceImplementation());
        registry.rebind("aliment", new AlimentServiceImplementation());
        registry.rebind("bus", new BusServiceImplementation());
        registry.rebind("calendar", new CalendarServiceImplementation());
        registry.rebind("daytrip", new TripServiceImplementation());
        registry.rebind("eatingdisorder", new EatingDisorderServiceImplementation());
        registry.rebind("place", new PlaceServiceImplementation());
        registry.rebind("recipes", new RecipesServiceImplementation());
        registry.rebind("parents", new ParentServiceImplementation());
        registry.rebind("supply", new SupplyingServiceImplementation());
        registry.rebind("follow", new ChildPediatricianServiceImplementation());
        registry.rebind("tripplace", new TripPlaceServiceImplementation());
        registry.rebind("dailydish", new DailyDishServiceImplementation());
        registry.rebind("dailytrip", new DailyTripServiceImplementation());
        registry.rebind("childintrip", new ChildrenInTripServiceImplementation());
        registry.rebind("staff", new StaffServiceImplementation());
        registry.rebind("dish", new DishServiceImplementation());
        registry.rebind("firstdish", new FirstDishServiceImplementation());
        registry.rebind("seconddish", new SecondDishServiceImplementation());
        registry.rebind("sidedish", new SideDishServiceImplementation());
        registry.rebind("sweetdish", new SweetDishServiceImplementation());
        registry.rebind("bustrip", new BusTripServiceImplementation());
        registry.rebind("placeintrip", new PlaceInTripServiceImplementation());
        registry.rebind("childinvehicle", new ChildInVehicleServiceImplementation());
    }

    public int getPort() {
        return port;
    }
}
