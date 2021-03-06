package Client.Model;

import Client.Controller.AbstractTableController;
import Client.ControllerManager;
import Client.Remote.RemoteManager;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import org.json.simple.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Trip extends AbstractRowModel {
    private TextField code = new TextField();
    private Button child;
    private Button place;
    private Button planning;
    private Button appeal;

    private CalendarDay calendar;

    public Trip(AbstractTableController tableController) throws Exception {
        this(tableController, new JSONObject());
    }

    public Trip(AbstractTableController tableController, JSONObject dailyTrip) throws Exception {
        super(RemoteManager.getInstance().getRemoteServicesManager().getDailyTripService(), tableController, dailyTrip);

        events();
    }

    public void events() {
        code.textProperty().addListener((obj, oldText, newText) -> {
            needToSave();
            data.put("code", newText);
            child.setVisible(getId() != null);
            place.setVisible(getId() != null);
        });
    }

    @Override
    protected void initializeButtons() {
        super.initializeButtons();

        appeal = new Button();
        defineImageButton(appeal, "Client/Resources/Images/check.png");
        appeal.setOnAction(actionEvent -> openAppealPopup());
        appeal.setVisible(getId() != null);
        appeal.setTooltip(new Tooltip("Check presences"));

        planning = new Button();
        defineImageButton(planning, "Client/Resources/Images/addbus.png");
        planning.setOnAction(actionEvent -> openBusPopup());
        planning.setVisible(getId() != null);
        planning.setTooltip(new Tooltip("Rent vehicles"));

        place = new Button();
        defineImageButton(place, "Client/Resources/Images/addplace.png");
        place.setOnAction(actionEvent -> openPlaceInTripPopup());
        place.setVisible(getId() != null);
        place.setTooltip(new Tooltip("Add places"));

        child = new Button();
        defineImageButton(child, "Client/Resources/Images/add.png");
        child.setOnAction(actionEvent -> openChildInTripPopup());
        child.setVisible(getId() != null);
        child.setTooltip(new Tooltip("Set children to vehicles"));

        getButtons().getChildren().addAll(planning, place, child, appeal);
    }

    private void openAppealPopup() {
        ControllerManager.getInstance().renderPresences(this);
    }

    private void openBusPopup() {
        ControllerManager.getInstance().renderAddBuses(this);
    }

    private void openPlaceInTripPopup() {
        ControllerManager.getInstance().renderAddPlaces(this);
    }

    private void openChildInTripPopup() {
        ControllerManager.getInstance().renderChildInTrip(this);
    }

    @Override
    public void refreshModel() {
        setCode((String) data.get("code"));
        save.getStyleClass().remove("red-button");
    }

    public Integer getId() {
        return data.get("id") != null ? Integer.parseInt((String) data.get("id")) : null;
    }

    public TextField getCode() {
        return code;
    }

    public void setCode(TextField code) {
        this.code = code;
    }

    public void setCode(String code) {
        this.code.setText(code);
    }

    public Integer getCalendarId() {
        return Integer.parseInt((String) ((JSONObject) data.get("day")).get("id"));
    }

    public void setCalendarId(Integer calendarId) {
        JSONObject day = (JSONObject) data.get("day");
        if(day == null) {
            day = new JSONObject();
            data.put("day", day);
        }
        day.put("id", calendarId);
    }

    public CalendarDay getCalendar() {
        return calendar;
    }

    public void setCalendar(CalendarDay calendar) {
        this.calendar = calendar;
        setCalendarId(calendar.getCalendarId());
    }
}
