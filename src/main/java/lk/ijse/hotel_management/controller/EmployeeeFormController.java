package lk.ijse.hotel_management.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lk.ijse.hotel_management.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.hotel_management.dto.EmployeeDto;
import lk.ijse.hotel_management.view.tdm.EmployeeTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeeFormController {
    @FXML
    private TableView<EmployeeTm> EmployeeTable;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colAddress1;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colMobile;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colNic;

    @FXML
    private TextField txtSearchBar;

    @FXML
    private TableColumn<?, ?> updateColumn;

    EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
    @FXML
    void addEmployeeOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/addEmployee.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Add Employee");
        stage.show();
    }

    public void loadAllEmployee() {
        EmployeeDAOImpl employeeModel = new EmployeeDAOImpl();

        ObservableList<EmployeeTm> observableList = FXCollections.observableArrayList();
        List<EmployeeDto> dtoList ;
        try {
            dtoList = employeeDAO.getAll();
            System.out.println(dtoList.size());

            for (EmployeeDto dto : dtoList) {
                observableList.add(
                        new EmployeeTm(
                                dto.getId(),
                                dto.getFirstName() + " " + dto.getLastName(),
                                dto.getAddress(),
                                dto.getEmail(),
                                dto.getPhone(),
                                dto.getNic(),
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
                    int  employeeId = observableList.get(i).getId();
                    String employeeFirstName = dtoList.get(i).getFirstName();
                    String employeeLastName = dtoList.get(i).getFirstName();
                    String employeeNic = dtoList.get(i).getLastName();
                    String employeeEmail = dtoList.get(i).getEmail();
                    String employeePhone = dtoList.get(i).getPhone();
                    String employeeAddress = dtoList.get(i).getAddress();

                    observableList.get(i).getUpdatebtn().setOnAction(event -> {
                        UpdateEmployeeFormController.lname = employeeFirstName;
                        UpdateEmployeeFormController.fname = employeeLastName;
                        UpdateEmployeeFormController.contact = employeePhone;
                        UpdateEmployeeFormController.nic = employeeNic;
                        UpdateEmployeeFormController.address = employeeAddress;
                        UpdateEmployeeFormController.id = employeeId;
                        UpdateEmployeeFormController.email = employeeEmail;

                        try {
                            Parent parent = FXMLLoader.load(getClass().getResource("/view/addEmployee.fxml"));
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
                            boolean isDelected = employeeDAO.delete(employeeId);
                            if (isDelected){
                                new Alert(Alert.AlertType.CONFIRMATION,"Do you want to delete").show();
                                loadAllEmployee();
                            }else {
                                new Alert(Alert.AlertType.ERROR,"employee not deleted!").show();
                            }
                        } catch (SQLException e) {
                            // throw new RuntimeException(e);
                            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                        }
                    });
                }

            }
            EmployeeTable.setItems(observableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        updateColumn.setCellValueFactory(new PropertyValueFactory<>("updatebtn"));
        colAddress1.setCellValueFactory(new PropertyValueFactory<>("deletebtn"));

    }

    public void initialize() {
        setCellValueFactory();
        loadAllEmployee();

    }

    @FXML
    void searchOnAction(KeyEvent event) {

        String txt = txtSearchBar.getText();
        if (txtSearchBar.equals(null)) {
            loadAllEmployee();
        } else {
            EmployeeDAOImpl model = new EmployeeDAOImpl();

            ObservableList<EmployeeTm> observableList = FXCollections.observableArrayList();

            try {
                ArrayList<EmployeeDto> list = model.searchEmployee(txt);

                for (EmployeeDto dto : list) {
                    observableList.add(
                            new EmployeeTm(
                                    dto.getId(),
                                    dto.getFirstName() + " " + dto.getLastName(),
                                    dto.getAddress(),
                                    dto.getEmail(),
                                    dto.getPhone(),
                                    dto.getNic(),
                                    new JFXButton("Update"),
                                    new JFXButton("Delete")

                            )
                    );

                }
                EmployeeTable.setItems(observableList);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

    }

}
