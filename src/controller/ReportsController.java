package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportsController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    Stage stage;
    Parent scene;

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
    void displayMain(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void generateCustomerIdReport(ActionEvent event) {

    }

    @FXML
    void generateTypeOrMonthReport(ActionEvent event) {

    }

}