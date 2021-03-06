package Client.Model;

import Client.Controller.AbstractTableController;
import Client.ControllerManager;
import Client.Remote.RemoteManager;
import Shared.EatingDisorderService;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import org.json.simple.JSONObject;

public class EatingDisorder extends AbstractRowModel {
    private Text name = new Text();
    private ChoiceBox<String> type = new ChoiceBox<>();
    private Children child;

    public EatingDisorder(AbstractTableController tableController) throws Exception {
        this(tableController, new JSONObject());
    }

    public EatingDisorder(AbstractTableController tableController, JSONObject data) throws Exception {
        super(RemoteManager.getInstance().getRemoteServicesManager().getAlimentService(), tableController, data);

        getType().getItems().addAll(null, "allergy", "intolerance");
        getType().setPrefSize(380, 40);
        getType().setMinSize(380, 40);
        getType().setMaxSize(380, 40);

        refreshModel();
        events();
        buttons.getChildren().remove(delete);
    }

    @Override
    public void refreshModel() {
        setName((String) data.get("name"));
        save.getStyleClass().remove("red-button");
    }

    /**
     * Method used to set listeners and related events to trigger
     */
    public void events() {
        type.setOnAction(event -> {
            needToSave();
            data.put("type", type.getValue());
        });
    }

    @Override
    public void save() {
        try {
            JSONObject result;
            EatingDisorderService service = RemoteManager.getInstance().getRemoteServicesManager().getEatingDisorderService();
            if(type.getValue()!= null) {
                if(type.getValue().equals("allergy")) {
                    result = service.assignAllergy(getId(), child.getId());
                } else {
                    result = service.assignIntolerance(getId(), child.getId());
                }
            } else {
                result = service.deAssign(getId(), child.getId());
            }
            if ((boolean) result.get("success")) {
                refreshModel();
                save.getStyleClass().remove("red-button");
            }
            notifyResult(result);
        } catch (Exception e) {
            ControllerManager.getInstance().notifyError(e.getMessage());
        }
    }

    public Integer getId() {
        return Integer.parseInt((String) data.get("id"));
    }

    public Text getName() {
        return name;
    }

    public void setName(Text name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public String getStringName() {
        return name.getText();
    }

    public ChoiceBox<String> getType() {
        return type;
    }

    public void setType(ChoiceBox<String> type) {
        this.type = type;
    }

    public Children getChild() {
        return child;
    }

    public void setChild(Children child) {
        this.child = child;
    }
}
