package Client.Model;

import Client.Controller.AbstractTableController;
import Client.ControllerManager;
import Client.Remote.RemoteManager;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;

public class DayTrips extends AbstractRowModel {
    private TextField name = new TextField();

    public DayTrips(AbstractTableController tableController) throws Exception {
        this(tableController, new JSONObject());
    }

    public DayTrips(AbstractTableController tableController, JSONObject data) throws Exception {
        super(RemoteManager.getInstance().getRemoteServicesManager().getDayTripService(), tableController, data);
        setName((String) data.get("name"));
        events();
    }

    @Override
    protected void initializeButtons() {
        super.initializeButtons();

        Button places = new Button();
        defineImageButton(places, "Client/Resources/Images/place.png");
        places.setOnAction(actionEvent -> places());

        getButtons().getChildren().addAll(places);
    }

    public void events() {
        name.textProperty().addListener((obs, oldText, newText) -> {
            needToSave();
            data.put("name", newText);
        });
    }

    public void places() {
        ControllerManager.getInstance().renderAddPlaces(this);
    }

    public int getId() {
        return Integer.parseInt((String) data.get("id"));
    }

    public TextField getName() {
        return name;
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setName(TextField name) {
        this.name = name;
    }

    public String getStringName() {
        return name.getText();
    }

}
