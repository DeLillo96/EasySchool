package Client;

import Client.Controller.*;
import Client.Model.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;

/**
 * This singleton class handles all the renders of the application (popups and notifies)
 */
public class ControllerManager {
    private static ControllerManager instance;
    private static Stage stage;
    private static Parent notify;
    private static Stack<Parent> popup = new Stack<>();

    /**
     * Singleton method
     * @return instance of ControllerManager
     */
    public static ControllerManager getInstance() {
        if (instance == null) instance = new ControllerManager();
        return instance;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        ControllerManager.stage = stage;
    }

    public Scene getScene() {
        return getStage().getScene();
    }

    /**
     * Renders login view
     * @throws IOException
     */
    public void renderLogin() throws IOException {
        renderFXML("Views/login.fxml");
    }

    /**
     * Renders error notification
     * @param errorMessage (Error message shown in the notification)
     */
    public void notifyError(String errorMessage) {
        if (notify != null) removeNotify();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/error.fxml"));
            notify = loader.load();

            addNotify(loader, errorMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Renders success notification
     * @param successMessage (Success message shown in the notification)
     */
    public void notifySuccess(String successMessage) {
        if (notify != null) removeNotify();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/success.fxml"));
            notify = loader.load();

            addNotify(loader, successMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Renders a generic notification
     * @param loader (Current loader)
     * @param message (Message shown in the notification)
     */
    protected void addNotify(FXMLLoader loader, String message) {
        AbstractNotifyController controller = loader.getController();
        controller.setMessage(message);

        Pane mainRoot = (Pane) getScene().getRoot();
        mainRoot.getChildren().add(notify);
    }

    /**
     * Remove last notification
     */
    public void removeNotify() {
        Pane mainRoot = (Pane) getScene().getRoot();
        mainRoot.getChildren().remove(notify);
        notify = null;
    }

    /**
     * Renders home view
     * @throws IOException
     */
    public void renderHome() throws IOException {
        renderFXML("Views/home.fxml");
    }

    /**
     * Renders calendar day popup
     * @param calendarDay (Calendar model assigned to calendar popup)
     */
    public void renderCalendarPopup(CalendarDay calendarDay) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/popupTab.fxml"));
            addPopup(loader.load());

            PopupTabController popupTabController = loader.getController();
            popupTabController.setCalendarDay(calendarDay);
            popupTabController.render();
        } catch (IOException e) {
            notifyError(e.getMessage());
        }
    }

    /**
     * Renders adults popup
     * @param child (Child model assigned to adults popup)
     */
    public void renderAddAdults(Children child) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/adults.fxml"));
            addPopup(loader.load());

            AdultsController adultsController = loader.getController();
            adultsController.setChild(child);

            adultsController.filter();
        } catch (IOException e) {
            notifyError(e.getMessage());
        }
    }

    /**
     * Renders pediatricians popup
     * @param child (Child model assigned to adults popup)
     */
    public void renderAddPediatricians(Children child) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/pediatricians.fxml"));
            addPopup(loader.load());

            PediatricianController pediatricianController = loader.getController();
            pediatricianController.setChild(child);

            pediatricianController.filter();
        } catch (IOException e) {
            e.printStackTrace();
            notifyError(e.getMessage());
        }
    }

    /**
     * Renders suppliers popup
     * @param aliment (Aliment model assigned to adults popup)
     */
    public void renderAddSuppliers(Aliment aliment) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/suppliers.fxml"));
            addPopup(loader.load());

            SuppliersController suppliersController = loader.getController();
            suppliersController.setAliment(aliment);

            suppliersController.filter();
        } catch (IOException e) {
            notifyError(e.getMessage());
        }
    }

    /**
     * Renders eating disorders popup
     * @param child (Child model assigned to eating disorders popup)
     */
    public void renderAddEatingDisorders(Children child) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/eatingDisorders.fxml"));
            addPopup(loader.load());

            EatingDisordersController eatingDisordersController = loader.getController();
            eatingDisordersController.setChild(child);

            eatingDisordersController.filter();
        } catch (IOException e) {
            notifyError(e.getMessage());
        }
    }

    /**
     * Renders buses popup
     * @param trip (Trip model assigned to buses popup
     */
    public void renderAddBuses(Trip trip) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/buses.fxml"));
            addPopup(loader.load());

            BusesController busesController = loader.getController();
            busesController.setTrip(trip);

            busesController.filter();
        } catch (IOException e) {
            e.printStackTrace();
            notifyError(e.getMessage());
        }
    }

    /**
     * Renders recipes popup
     * @param dish (Dish model assigned to recipes popup)
     */
    public void renderRecipes(Dish dish) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/aliment.fxml"));
            addPopup(loader.load());

            AlimentController alimentController = loader.getController();
            alimentController.setDish(dish);

            alimentController.filter();
        } catch (Exception e) {
            notifyError(e.getMessage());
        }
    }

    /**
     * Renders places popup
     * @param trip (Trips model assigned to places popup)
     */
    public void renderAddPlaces(Trip trip) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/places.fxml"));
            addPopup(loader.load());

            PlacesController placesController = loader.getController();
            placesController.setTrip(trip);
            placesController.filter();
        } catch (IOException e) {
            notifyError(e.getMessage());
        }
    }

    /**
     * Renders popup containing the list of children assigned to a daily trip
     * @param trip (Trip model assigned to childInTrip popup)
     */
    public void renderChildInTrip(Trip trip) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/childrenInTrip.fxml"));
            addPopup(loader.load());

            ChildrenInTripController childrenInTripController = loader.getController();
            childrenInTripController.setTrip(trip);
            childrenInTripController.filter();
        } catch (IOException e) {
            notifyError(e.getMessage());
        }
    }

    /**
     * Renders popup containing the list of children assigned to a daily trip
     * @param trip (Trip model assigned to childInTrip popup)
     */
    public void renderChildInVehicles(Trip trip, ChildInTrip childInTrip) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/childreninvehicle.fxml"));
            addPopup(loader.load());

            ChildrenInVehicleController childrenInVehicleController = loader.getController();
            childrenInVehicleController.setTrip(trip);
            childrenInVehicleController.setChildInTrip(childInTrip);
            childrenInVehicleController.filter();
        } catch (IOException e) {
            notifyError(e.getMessage());
        }
    }


    public void renderPresences(Trip trip) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/presences.fxml"));
            addPopup(loader.load());

            PresencesController presencesController = loader.getController();
            presencesController.setTrip(trip);
            presencesController.fillVehicleBox();
            presencesController.filter();
        } catch (IOException e) {
            notifyError(e.getMessage());
        }
    }

    /**
     * Add a popup to a generic stack
     * @param parent (Base of the client's view)
     */
    public void addPopup(Parent parent) {
        int offset = popup.size() * 10;
        AnchorPane.setTopAnchor(parent, 20d + offset);
        AnchorPane.setBottomAnchor(parent, 20d + offset);
        AnchorPane.setLeftAnchor(parent, 10d + offset);
        AnchorPane.setRightAnchor(parent, 10d + offset);

        Pane mainRoot = (Pane) getScene().getRoot();
        mainRoot.getChildren().add(popup.push(parent));
    }

    /**
     * Remove the last inserted popup from the popup stack
     */
    public void removePopup() {
        Pane mainRoot = (Pane) getScene().getRoot();
        mainRoot.getChildren().remove(popup.pop());
    }

    /**
     * Method used to render a specific FXML resource
     * @param location (location of the FXML required resource)
     * @throws IOException
     */
    private void renderFXML(String location) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(location));
        getStage().setScene(new Scene(root));
    }
}
