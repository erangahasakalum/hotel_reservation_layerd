package lk.ijse.hotel_management.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hotel_management.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.hotel_management.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class AddEmployeeFormController {

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

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXButton createBtn;

    EmployeeeFormController employeeFormController = new EmployeeeFormController();
    EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();

    @FXML
    void cancelBtnOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();

    }

    @FXML
    void saveOnAction(ActionEvent event) throws SQLException {

        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String address = txtAddress.getText();
        String nic = txtNic.getText();
        String mobile = txtMobile.getText();
        String email = txtEmail.getText();

        boolean isEmployeeValid = validateEmployee();
        if (isEmployeeValid) {

            EmployeeDto dto = new EmployeeDto(0, firstName, lastName, address, nic, mobile, email);

            try {
                boolean isSaved = employeeDAO.save(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Employee Saved!").show();
                    clearFields();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

        }
    }

    private boolean validateEmployee() {
        if (txtFirstName.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Employee First Name field empty").show();
            return false;
        }

        boolean matches = Pattern.matches("^[A-Za-z]+(?:[ '-][A-Za-z]+)*$", txtFirstName.getText());
        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid Employee First Name").show();
            return false;
        }

        if (txtLastName.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Employee Last Name Field Empty").show();
            return false;
        }

        boolean matches1 = Pattern.matches("^[A-Za-z]+(?:[ '-][A-Za-z]+)*$", txtLastName.getText());
        if (!matches1) {
            new Alert(Alert.AlertType.ERROR, "Invalid Employee Name Field Empty").show();
            return false;
        }

        if (txtAddress.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Address Field Empty").show();
            return false;
        }

      /*  boolean matches2 = Pattern.matches("^[0-9A-Za-z\\\\s.,#-]+$", txtAddress.getText());
        if (!matches2) {
            new Alert(Alert.AlertType.ERROR, "Invalid Address").show();
            return false;
        }*/

        if (txtNic.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "NIC Field Empty").show();
            return false;
        }
        boolean matches3 = Pattern.matches("^(?:19|20)?\\d{2}[0-9]{10}|[0-9]{9}[x|X|v|V]$", txtNic.getText());
        if (!matches3) {
            new Alert(Alert.AlertType.ERROR, "Invalid NIC Number").show();
            return false;
        }

        if (txtMobile.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Mobile Number Field Empty").show();
            return false;
        }
        boolean matches4 = Pattern.matches("^0[1-9]\\d{8}$", txtMobile.getText());
        if (!matches4) {
            new Alert(Alert.AlertType.ERROR, "Invalid Mobile Number").show();
            return false;
        }

        boolean matches5 = Pattern.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"" + "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")" + "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])" + "|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:" + "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", txtEmail.getText());
        if (!matches5) {
            new Alert(Alert.AlertType.ERROR, "Invalid Email").show();
            return false;
        }

        return true;

    }

    private void clearFields() {
        txtFirstName.setText("");
        txtLastName.setText("");
        txtAddress.setText("");
        txtNic.setText("");
        txtEmail.setText("");
        txtMobile.setText("");
    }

    @FXML
    void txtAddressOnAction(ActionEvent event) {
            txtNic.requestFocus();
    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {
            createBtn.requestFocus();
    }

    @FXML
    void txtFirstNameOnActiion(ActionEvent event) {
            txtLastName.requestFocus();
    }

    @FXML
    void txtLastNameOnAction(ActionEvent event) {
            txtAddress.requestFocus();
    }

    @FXML
    void txtMobileOnAction(ActionEvent event) {
            txtEmail.requestFocus();
    }

    @FXML
    void txtNicOnAction(ActionEvent event) {
            txtMobile.requestFocus();
    }
}
