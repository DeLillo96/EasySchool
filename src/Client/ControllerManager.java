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

public class ControllerManager {
    private static ControllerManager instance;
    private static Stage stage;
    private static Parent notify;
    private static Stack<Parent> popup = new Stack<>();

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

    public void renderLogin() throws IOException {
        renderFXML("Views/login.fxml");
    }

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

    protected void addNotify(FXMLLoader loader, String message) {
        AbstractNotifyController controller = loader.getController();
        controller.setMessage(message);

        Pane mainRoot = (Pane) getScene().getRoot();
        mainRoot.getChildren().add(notify);
    }

    public void removeNotify() {
        Pane mainRoot = (Pane) getScene().getRoot();
        mainRoot.getChildren().remove(notify);
        notify = null;
    }

    public void renderHome() throws IOException {
        renderFXML("Views/home.fxml");
    }

    public void renderCalendarPopup(Integer calendarId, String date) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/popupTab.fxml"));
            addPopup(loader.load());

            PopupTabController popupTabController = loader.getController();
            popupTabController.setCalendarId(calendarId);
            popupTabController.setDate(date);
            popupTabController.render();
        } catch (IOException e) {
            notifyError(e.getMessage());
        }
    }

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

    public void renderAddBuses(Places place) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/buses.fxml"));
            addPopup(loader.load());

            BusesController busesController = loader.getController();
            busesController.setPlace(place);

            busesController.filter();
        } catch (IOException e) {
            e.printStackTrace();
            notifyError(e.getMessage());
        }
    }

    public void renderDishes(Menu menu) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/dishes.fxml"));
        addPopup(loader.load());

        DishesController dishesController = loader.getController();
        dishesController.render(menu);
    }

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

    public void renderAddPlaces(DayTrips trip) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/places.fxml"));
            addPopup(loader.load());

            PlacesController placesController = loader.getController();
            placesController.setTrip(trip);
        } catch (IOException e) {
            notifyError(e.getMessage());
        }
    }

    public void renderChildInTrip(DailyTrips dailyTrips) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/childrenInTrip.fxml"));
            addPopup(loader.load());

            ChildrenInTripController childrenInTripController = loader.getController();
            childrenInTripController.setDailyTripId(dailyTrips.getId());
            childrenInTripController.filter();
        } catch (IOException e) {
            notifyError(e.getMessage());
        }
    }

    public void addPopup(Parent parent) {
        int offset = popup.size() * 10;
        AnchorPane.setTopAnchor(parent, 20d + offset);
        AnchorPane.setBottomAnchor(parent, 20d + offset);
        AnchorPane.setLeftAnchor(parent, 10d + offset);
        AnchorPane.setRightAnchor(parent, 10d + offset);

        Pane mainRoot = (Pane) getScene().getRoot();
        mainRoot.getChildren().add(popup.push(parent));
    }

    public void removePopup() {
        Pane mainRoot = (Pane) getScene().getRoot();
        mainRoot.getChildren().remove(popup.pop());
    }

    private void renderFXML(String location) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(location));
        getStage().setScene(new Scene(root));
    }
}
