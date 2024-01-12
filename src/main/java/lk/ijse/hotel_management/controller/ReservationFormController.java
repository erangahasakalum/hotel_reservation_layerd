package lk.ijse.hotel_management.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lk.ijse.hotel_management.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.hotel_management.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.hotel_management.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.hotel_management.dto.CustomerDto;
import lk.ijse.hotel_management.dto.PaymentDto;
import lk.ijse.hotel_management.dto.ReservationDto;
import lk.ijse.hotel_management.entity.Customer;
import lk.ijse.hotel_management.view.tdm.ReservationTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ReservationFormController implements Initializable {

    @FXML
    private TableColumn<ReservationTm, String> checkOut;

    @FXML
    private TableColumn<ReservationTm, String> checkingDate;

    @FXML
    private TableColumn<ReservationTm, String> colMobile;

    @FXML
    private TableColumn<ReservationTm, String> customerName;

    @FXML
    private TableColumn<ReservationTm, JFXButton> delete;

    @FXML
    private TableColumn<ReservationTm, String> paymentStates;

    @FXML
    private TableColumn<ReservationTm, String > reservationId;

    @FXML
    private TableView<ReservationTm> reservationTable;

    @FXML
    private TextField txtSearchBar;

    @FXML
    private TableColumn<ReservationTm, JFXButton> updateColumn;

    ReservationDAOImpl reservationDAO = new ReservationDAOImpl();
    CustomerDAOImpl customerDAO  = new CustomerDAOImpl();
    PaymentDAOImpl paymentDAO = new PaymentDAOImpl();

    @FXML
    void btnAddOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/addreservation_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("add reservation");
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setValues();
        try {
            loadValues();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadValues() throws SQLException {
        List<ReservationDto> allReservation = reservationDAO.getAll();
        ObservableList<ReservationTm> observableList = FXCollections.observableArrayList();
        for (int i = 0; i < allReservation.size(); i++) {
            int rid = allReservation.get(i).getResevation_id();
            int cid = allReservation.get(i).getCustomer_id();
            int pid = allReservation.get(i).getPayment_id();
            PaymentDto paymentByID = paymentDAO.getPaymentByID(pid);
            Customer customer = customerDAO.getcustomerName(cid);
            ReservationTm reservationModel = new ReservationTm(rid,customer.getCustomer_firstName(),customer.
                    getCustomer_phone(),allReservation.get(i).getCheak_in_date(),allReservation.get(i).getCheak_out_date(),paymentByID.getPayment_method(),new JFXButton("Update"),new JFXButton("Delete"));
            observableList.add(reservationModel);


        }
        reservationTable.setItems(observableList);
    }

    private void setValues() {
        reservationId.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("mobilNumber"));
        checkingDate.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
        checkOut.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));
        paymentStates.setCellValueFactory(new PropertyValueFactory<>("states"));
        updateColumn.setCellValueFactory(new PropertyValueFactory<>("deletebtn"));
        delete.setCellValueFactory(new PropertyValueFactory<>("updatebtn"));
    }

    public void searchOnAction(KeyEvent keyEvent) {
    }

    public void btnPaymentOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/payment_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("payment");
        stage.show();

    }
}
