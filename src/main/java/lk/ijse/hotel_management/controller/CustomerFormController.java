package lk.ijse.hotel_management.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lk.ijse.hotel_management.bo.custom.CustomerBO;
import lk.ijse.hotel_management.bo.custom.impl.CustomerBOImpl;
import lk.ijse.hotel_management.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.hotel_management.dto.CustomerDto;
import lk.ijse.hotel_management.entity.Customer;
import lk.ijse.hotel_management.view.tdm.CustomerTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerFormController {

    @FXML
    private TableColumn<CustomerTm, String> colAddress;

    @FXML
    private TableColumn<CustomerTm, String> colId;

    @FXML
    private TableColumn<CustomerTm, String> colMobile;

    @FXML
    private TableColumn<CustomerTm, String> colName;

    @FXML
    private TableColumn<CustomerTm, String> colNic;

    @FXML
    private TableColumn<CustomerTm, String> colEmail;


    @FXML
    private TextField txtSearchBar;

    @FXML
    private TableView<CustomerTm> customerTable;
    @FXML
    private TableColumn<CustomerTm, JFXButton> colAddress1;
    @FXML
    private TableColumn<CustomerTm, JFXButton> updateColumn;

    //CustomerDAOImpl customerDAO = new CustomerDAOImpl();
    CustomerBO customerBO = new CustomerBOImpl();


    @FXML
    void btnAddCustomerOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/addcustomer_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Add Customer");
        stage.show();

    }

    public void loadAllCustomer() {
        CustomerDAOImpl customerModel = new CustomerDAOImpl();

        ObservableList<CustomerTm> observableList = FXCollections.observableArrayList();
        List<CustomerDto> dtoList ;
        try {
             dtoList = customerBO.getAllCustomer();
            System.out.println(dtoList.size());

            for (CustomerDto dto : dtoList) {
                observableList.add(
                        new CustomerTm(
                                dto.getCustomer_id(),
                                dto.getCustomer_firstName() + " " + dto.getCustomer_lastName(),
                                dto.getCustomer_address(),
                                dto.getCustomer_email(),
                                dto.getCustomer_phone(),
                                dto.getCustomer_nic(),
                                new JFXButton("Update"),
                                new JFXButton("Delete")
                        )
                );
                for (int i = 0; i < observableList.size(); i++) {

                    observableList.get(i).getUpdatebtn().setStyle("-fx-background-color: rgba(112,143,189,1)");
                    observableList.get(i).getDeletebtn().setStyle("-fx-background-color: rgba(175,108,108,1) ");
                    observableList.get(i).getUpdatebtn().setTextFill(Color.WHITE);
                    observableList.get(i).getDeletebtn().setTextFill(Color.WHITE);
                }

                for (int i = 0; i < observableList.size(); i++) {

                    int  cusId = observableList.get(i).getCustomer_id();
                    String customerFirstName = dtoList.get(i).getCustomer_firstName();
                    String customerLastName = dtoList.get(i).getCustomer_lastName();
                    String customerNic = dtoList.get(i).getCustomer_nic();
                    String customerEmail = dtoList.get(i).getCustomer_email();
                    String customerPhone = dtoList.get(i).getCustomer_phone();
                    String customerAddress = dtoList.get(i).getCustomer_address();

                    observableList.get(i).getUpdatebtn().setOnAction(event -> {

                        UpdateCustomerFormController.lname = customerLastName;
                        UpdateCustomerFormController.fname = customerFirstName;
                        UpdateCustomerFormController.contact = customerPhone;
                        UpdateCustomerFormController.nic = customerNic;
                        UpdateCustomerFormController.address = customerAddress;
                        UpdateCustomerFormController.customemrID = cusId;
                        UpdateCustomerFormController.email = customerEmail;

                        try {
                            Parent parent = FXMLLoader.load(getClass().getResource("/view/updateCustomer_form.fxml"));
                            Scene scene = new Scene(parent);
                            Stage stage = new Stage();
                            stage.centerOnScreen();
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    });
                    observableList.get(i).getDeletebtn().setOnAction(event -> {
                        try {
                            boolean isDelected = customerBO.deleteCustomer(cusId);
                            if (isDelected){
                                new Alert(Alert.AlertType.CONFIRMATION,"Do you want to delete").show();
                                loadAllCustomer();
                            }else {
                                new Alert(Alert.AlertType.ERROR,"customer not deleted!").show();
                            }
                        } catch (SQLException | ClassNotFoundException e) {
                           // throw new RuntimeException(e);
                            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                        }
                    });
                }

            }
            customerTable.setItems(observableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("customer_Name"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("customer_phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("customer_nic"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("customer_email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("customer_address"));
        updateColumn.setCellValueFactory(new PropertyValueFactory<>("updatebtn"));
        colAddress1.setCellValueFactory(new PropertyValueFactory<>("deletebtn"));

    }

    public void initialize() {
        setCellValueFactory();
        loadAllCustomer();

    }

    @FXML
    void searchOnAction(KeyEvent event) {

        String txt = txtSearchBar.getText();
        if (txtSearchBar.equals(null)) {
            loadAllCustomer();
        } else {
            CustomerDAOImpl customerDAO = new CustomerDAOImpl();

            ObservableList<CustomerTm> observableList = FXCollections.observableArrayList();

            try {
                ArrayList<Customer> list = customerDAO.searchCustomer(txt);

                for (Customer dto : list) {
                    observableList.add(
                            new CustomerTm(
                                    dto.getCustomer_id(),
                                    dto.getCustomer_firstName() + " " + dto.getCustomer_lastName(),
                                    dto.getCustomer_address(),
                                    dto.getCustomer_email(),
                                    dto.getCustomer_phone(),
                                    dto.getCustomer_nic(),
                                    new JFXButton("Update"),
                                    new JFXButton("Delete")

                            )
                    );

                }
                customerTable.setItems(observableList);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }

    }

}
