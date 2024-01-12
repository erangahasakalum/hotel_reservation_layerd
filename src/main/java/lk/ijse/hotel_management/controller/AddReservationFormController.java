package lk.ijse.hotel_management.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.hotel_management.dao.custom.impl.*;
import lk.ijse.hotel_management.dto.CustomerDto;
import lk.ijse.hotel_management.dto.PaymentDto;
import lk.ijse.hotel_management.dto.ReservationDto;
import lk.ijse.hotel_management.dto.RoomsDto;
import lk.ijse.hotel_management.view.tdm.RoomsTm;


import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddReservationFormController implements Initializable {

    public ComboBox<Integer> customerComboBox;
    public TextField txtTotal_tex;
    public TextField txtBalance_tex2;
    public TextField txtAmount_tex;
    @FXML
    private TableColumn<?, ?> beds;

    @FXML
    private TableColumn<?, ?> number;

    @FXML
    private TableColumn<?, ?> Price;

    @FXML
    private TableColumn<?, ?> roomcategory;

    @FXML
    private DatePicker txtCheackOutDate;

    @FXML
    private DatePicker txtCheckDate;

    @FXML
    private Button closeButton;

    @FXML
    private TableColumn<?, ?> states;
    @FXML
    private Button btnSave;

    @FXML
    private TableView<RoomsTm> reeservationTable;

    private String roomNumber;

    public static RoomsTm roomsTableModel = new RoomsTm();

    CustomerDAOImpl customerDAO = new CustomerDAOImpl();
    RoomCategoryDAOImpl roomCategoryDAO = new RoomCategoryDAOImpl();
    RoomDAOImpl roomDAO = new RoomDAOImpl();
    ReservationDAOImpl reservationDAO = new ReservationDAOImpl();

    PaymentDAOImpl paymentDAO = new PaymentDAOImpl();

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        Stage stage1 = (Stage) closeButton.getScene().getWindow();
        stage1.close();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellValueFactory();
        try {
            loadAllRooms();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            loadComboBox();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void loadComboBox() throws Exception {
        customerComboBox.getItems().clear();
        List<CustomerDto> getAllCustomer= customerDAO.getAll();
        for (CustomerDto customer_firstName :getAllCustomer){
            customerComboBox.getItems().add(customer_firstName.getCustomer_id());

        }

    }

    private void setCellValueFactory() {
        number.setCellValueFactory(new PropertyValueFactory<>("room_number"));
        roomcategory.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        beds.setCellValueFactory(new PropertyValueFactory<>("bed_count"));
        Price.setCellValueFactory(new PropertyValueFactory<>("room_price"));
        states.setCellValueFactory(new PropertyValueFactory<>("states"));

    }

    private void loadAllRooms() throws SQLException {
        reeservationTable.getItems().clear();
        RoomDAOImpl roomModel = new RoomDAOImpl();
        ArrayList<RoomsDto> allRooms = roomModel.getAll();
        ObservableList<RoomsTm> roomsTableModels = FXCollections.observableArrayList();

        for (int i = 0; i < allRooms.size(); i++) {
            String categoryName = roomCategoryDAO.getCategoryName(allRooms.get(i).getCategory_id());
            RoomsTm roomsTableModel1  = new RoomsTm(allRooms.get(i).getRoom_number(),categoryName,allRooms.get(i).getBed_count(),allRooms.get(i).getRoom_price(),allRooms.get(i).getAvailability());
            roomsTableModels.add(roomsTableModel1);
        }
        reeservationTable.setItems(roomsTableModels);
    }


    public void onClckActon(MouseEvent mouseEvent) {
        RoomsTm selectedItem = reeservationTable.getSelectionModel().getSelectedItem();
        LocalDate value = txtCheckDate.getValue();
        LocalDate value1 = txtCheackOutDate.getValue();
        long daysBetween = ChronoUnit.DAYS.between(value, value1);
        if (selectedItem!=null) {
            double roomPrice = selectedItem.getRoom_price();
            double totall = roomPrice * daysBetween;
            txtTotal_tex.setText(totall+"");
            roomNumber = selectedItem.getRoom_number();
        }
    }

    public void balanceOnAction(ActionEvent actionEvent) {
        txtAmount_tex.setText(Double.parseDouble(txtTotal_tex.getText())-Double.parseDouble(txtBalance_tex2.getText())+"");

    }
    public void totalOnAction(ActionEvent actionEvent) {
    }


    public void bookingOnAction(ActionEvent actionEvent) {
        String romnumb = roomNumber;
        boolean b = roomDAO.changeStatus(romnumb, "Unavaliable");
        if (b){
            LocalDate now = LocalDate.now();
            PaymentDto paymentDto = new PaymentDto(0,Double.parseDouble(txtAmount_tex.getText()),"Half Payment","ads",String.valueOf(now));
            boolean b1 = paymentDAO .save(paymentDto);
            if (b1){
                String datechck = String.valueOf(txtCheckDate.getValue());
                String dateout = String.valueOf(txtCheackOutDate.getValue());
                Integer value = customerComboBox.getValue();
                ArrayList<PaymentDto> allPayment = paymentDAO.getAll();
                int payID = allPayment.get(allPayment.size()-1).getPayment_id();
                ReservationDto reservationDto = new ReservationDto(0,datechck,dateout,"S",value,payID);
                boolean b2 = reservationDAO.save(reservationDto);
                if (b2){
                    new Alert(Alert.AlertType.CONFIRMATION,"Details Saved   ").show();
                    Stage stage = (Stage) btnSave.getScene().getWindow();
                    stage.close();
                }
            }

        }

    }

    private ReservationDto getDto() {

        ReservationDto reservationDto = new ReservationDto(0,
                txtCheckDate.getValue().toString(), txtCheackOutDate.getValue().toString(),
                "Booking", 0, customerComboBox.getSelectionModel().
                getSelectedItem());

        return reservationDto;
    }
}
