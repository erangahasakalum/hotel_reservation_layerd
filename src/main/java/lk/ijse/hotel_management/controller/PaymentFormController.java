package lk.ijse.hotel_management.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hotel_management.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.hotel_management.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.hotel_management.dto.CustomerDto;
import lk.ijse.hotel_management.dto.PaymentDto;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentFormController implements Initializable {

    @FXML
    private JFXButton cancelBtn;

    @FXML
    private JFXButton createBtn;

    @FXML
    private ComboBox<String> customerCombo;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField txtPaymentAmount;

    PaymentDAOImpl paymentDAO = new PaymentDAOImpl();
    CustomerDAOImpl customerDAO = new CustomerDAOImpl();

    @FXML
    void cancelBtnOnAction(ActionEvent event) {
        Stage stage= (Stage) pane.getScene().getWindow();
        stage.close();

    }

    @FXML
    void createOnAction(ActionEvent event) {
        String value = customerCombo.getValue();
        double paymentAmountText = Double.parseDouble(txtPaymentAmount.getText());
        LocalDate now = LocalDate.now();
        PaymentDto paymentDto= new PaymentDto(0,paymentAmountText,"Complete","ads",String.valueOf(now));
        boolean b = paymentDAO.save(paymentDto);
        if (b){
            new Alert(Alert.AlertType.CONFIRMATION,"Succefully added").show();
            Stage stage = (Stage) customerCombo.getScene().getWindow();
            stage.close();
        }

    }

    @FXML
    void txtAmountOnAction(ActionEvent event) {
        createBtn.isFocused();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            List<CustomerDto> allCustomer = customerDAO.getAll();
            ObservableList<String> observableList = FXCollections.observableArrayList();
            for (int i = 0; i < allCustomer.size(); i++) {
                observableList.add(String.valueOf(allCustomer.get(i).getCustomer_id()));
            }
            customerCombo.setItems(observableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
