package lk.ijse.hotel_management.controller;

import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hotel_management.db.DbConnetion;
import lombok.SneakyThrows;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class NavigationFormController implements Initializable {
    @FXML
    private Button settingBtn;

    @FXML
    private Button managePaymentBtn;

    @FXML
    private Button manageCustomerBtn;

    @FXML
    private Button dashBoardBtn;

    @FXML
    private Button manageRoomBtn;

    @FXML
    private Button manageReservationBtn;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private Button logOut;

    @FXML
    private JFXToggleButton changeMode;
    @FXML
    private Button manageEmployee;

    @FXML
    private Button mailBox;

    private double mode = 0;

    public void initialize() {
        btnChangeModeOnAction(new ActionEvent());
    }

    @FXML
    void btnChangeModeOnAction(ActionEvent event) {
        changeMode.setOnAction(event1 -> {
            if (mode == 0) {
                anchorpane.getStylesheets().add("style/light_mode.css");
                anchorpane.getStylesheets().remove("style/dark_mode.css");
                mode = 1;
            } else if (mode == 1) {
                anchorpane.getStylesheets().add("style/darkMode.css");
                anchorpane.getStylesheets().remove("style/lightMode.css");
                mode = 0;
            }
        });


    }

    @FXML
    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("home");
        stage.show();

        Stage stage1 = (Stage) logOut.getScene().getWindow();
        stage1.close();

    }

    @FXML
    void btnSettingOnAction(ActionEvent event) throws IOException {
        rmoveCLR();
        Parent parent = FXMLLoader.load(getClass().getResource("/view/setting.fxml"));
        anchorpane.getChildren().clear();
        anchorpane.getChildren().add(parent);
        settingBtn.setStyle("-fx-background-color: linear-gradient(to left, #ffffff, #656c69)");
    }


    @FXML
    void btnReservationOnAction(ActionEvent event) throws IOException {
        rmoveCLR();
        Parent parent = FXMLLoader.load(getClass().getResource("/view/Reservation_form.fxml"));
        anchorpane.getChildren().clear();
        anchorpane.getChildren().add(parent);
        manageReservationBtn.setStyle("-fx-background-color: linear-gradient(to left, #ffffff, #656c69)");
    }

    @FXML
    void dashBoardOnAction(ActionEvent event) throws IOException {
        rmoveCLR();
        Parent parent = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        anchorpane.getChildren().clear();
        anchorpane.getChildren().add(parent);
        dashBoardBtn.setStyle("-fx-background-color: linear-gradient(to left, #ffffff, #656c69) ");

    }

    public void rmoveCLR() {
        settingBtn.setStyle("-fx-background-color:  linear-gradient(to right, #0061ff, #60efff) ");
        manageCustomerBtn.setStyle("-fx-background-color:  linear-gradient(to right, #0061ff, #60efff)");
        dashBoardBtn.setStyle("-fx-background-color:  linear-gradient(to right, #0061ff, #60efff)");
        manageRoomBtn.setStyle("-fx-background-color:  linear-gradient(to right, #0061ff, #60efff)");
        manageReservationBtn.setStyle("-fx-background-color:  linear-gradient(to right, #0061ff, #60efff)");
        manageEmployee.setStyle("-fx-background-color:  linear-gradient(to right, #0061ff, #60efff)");
        mailBox.setStyle("-fx-background-color:  linear-gradient(to right, #0061ff, #60efff)");


    }

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rmoveCLR();
        Parent parent = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        anchorpane.getChildren().clear();
        anchorpane.getChildren().add(parent);
        dashBoardBtn.setStyle("-fx-background-color: linear-gradient(to left, #ffffff, #656c69) ");

    }

    @FXML
    void btnManageOnAction(ActionEvent event) throws IOException {
        rmoveCLR();
        Parent parent = FXMLLoader.load(getClass().getResource("/view/rooms_manage.fxml"));
        anchorpane.getChildren().clear();
        anchorpane.getChildren().add(parent);
        manageRoomBtn.setStyle("-fx-background-color: linear-gradient(to left, #ffffff, #656c69) ");

    }

    @FXML
    void customerFormOnAction(ActionEvent event) throws IOException {
        rmoveCLR();
        Parent parent = FXMLLoader.load(getClass().getResource("/view/customer_form.fxml"));
        anchorpane.getChildren().clear();
        anchorpane.getChildren().add(parent);
        manageCustomerBtn.setStyle("-fx-background-color: linear-gradient(to left, #ffffff, #656c69)");
    }

    @FXML
    void EmployeeOnAction(ActionEvent event) throws IOException {
        rmoveCLR();
        Parent parent = FXMLLoader.load(getClass().getResource("/view/manageEmployee.fxml"));
        anchorpane.getChildren().clear();
        anchorpane.getChildren().add(parent);
        manageEmployee.setStyle("-fx-background-color: linear-gradient(to left, #ffffff, #656c69)");
    }

    @FXML
    void mailBoxOnAction(ActionEvent event) throws IOException {
        rmoveCLR();
        Parent parent = FXMLLoader.load(getClass().getResource("/view/mail_form.fxml"));
        anchorpane.getChildren().clear();
        anchorpane.getChildren().add(parent);
        mailBox.setStyle("-fx-background-color: linear-gradient(to left, #ffffff, #656c69)");

    }

}
