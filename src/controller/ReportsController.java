package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportsController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    private ToggleGroup appointmentsBy;

    @FXML
    private TableColumn<?, ?> apptIdCol;

    @FXML
    private ComboBox<?> contactNameCombo;

    @FXML
    private TableView<?> contactScheduleTableView;

    @FXML
    private TableColumn<?, ?> custIdCol;

    @FXML
    private ComboBox<?> customerIdCombo;

    @FXML
    private TableColumn<?, ?> descCol;

    @FXML
    private TableColumn<?, ?> endDateCol;

    @FXML
    private TableColumn<?, ?> endtimeCol;

    @FXML
    private Button generateCustomerIdReport;

    @FXML
    private RadioButton monthRBtn;

    @FXML
    private TableColumn<?, ?> startDateCol;

    @FXML
    private TableColumn<?, ?> startTimeCol;

    @FXML
    private TableColumn<?, ?> titleCol;

    @FXML
    private TableColumn<?, ?> typeCol;

    @FXML
    private RadioButton typeRBtn;

    @FXML
    void generateTypeOrMonthReport(ActionEvent event) {

    }

}