package lk.ijse.hotel_management.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.hotel_management.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.hotel_management.dto.CustomerDto;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateEmployeeFormController  implements Initializable {
    public static int id;
    public static String fname;
    public static String lname;
    public static String email;
    public static String contact;
    public static String address;
    public static String nic;

    @FXML
    private JFXButton cancelBtn;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtMobile;

    @FXML
    private TextField txtNic;

    CustomerDAOImpl customerDAO = new CustomerDAOImpl();

    @FXML
    void cancelBtnOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();

    }
    @FXML
    void createOnAction(ActionEvent event) {
        String firstNameText = txtFirstName.getText();
        String  lastNameText= txtLastName.getText();
        String mobileText = txtMobile.getText();
        String addressText = txtAddress.getText();
        String emailText = txtEmail.getText();
        String nicText = txtNic.getText();

        CustomerDto dto = new CustomerDto(0,firstNameText,lastNameText,mobileText,addressText,emailText,nicText);

        try {
            boolean isUpdated = customerDAO.update(dto);
            System.out.println(isUpdated);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"Employee updated").show();
            }
        } catch (SQLException e) {
            //`throw new RuntimeException(e);
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtFirstName.setText(fname);
        txtLastName.setText(lname);
        txtAddress.setText(address);
        txtNic.setText(nic);
        txtEmail.setText(email);
        txtMobile.setText(contact);

    }

}
