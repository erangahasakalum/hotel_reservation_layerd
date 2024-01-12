package lk.ijse.hotel_management.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.hotel_management.dao.custom.RoomCategoryDAO;
import lk.ijse.hotel_management.dao.custom.impl.RoomCategoryDAOImpl;
import lk.ijse.hotel_management.dao.custom.impl.RoomDAOImpl;
import lk.ijse.hotel_management.dto.RoomsCategoryDto;
import lk.ijse.hotel_management.dto.RoomsDto;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;


public class AddRoomsFormController implements Initializable {

    @FXML
    private JFXButton cancelBtn;

    @FXML
    private JFXCheckBox clickPool;

    @FXML
    private JFXCheckBox clickTv;

    @FXML
    private JFXCheckBox clickWifi;

    @FXML
    private JFXComboBox<String> roomCategoryComboBox;

    @FXML
    private TextField txtRoomNumber;

    @FXML
    private TextField txtRoomPrice;

    @FXML
    private JFXCheckBox clickHotWater;

    @FXML
    private JFXCheckBox clickSmoking;

    @FXML
    private JFXCheckBox clickAc;

    @FXML
    private TextField txtBedCount;

    @FXML
    private TextField txtNumOfCustomer;

    @FXML
    private JFXButton createBtn;


    RoomsCategoryDto roomsCategoryDto = new RoomsCategoryDto();
    RoomDAOImpl roomDAO = new RoomDAOImpl();
    RoomCategoryDAO roomCategoryDAO = new RoomCategoryDAOImpl();

    public void cancelBtnOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void createOnAction(ActionEvent actionEvent) throws SQLException {
        List<RoomsCategoryDto> roomsCategoryDtos = roomCategoryDAO.getAll();
        for (RoomsCategoryDto roomDto : roomsCategoryDtos) {
            if (roomCategoryComboBox.getValue().equals(roomDto.getName())) {
                roomsCategoryDto.setId(roomDto.getId());
                roomsCategoryDto.setName(roomDto.getName());
                roomsCategoryDto.setDescription(roomDto.getDescription());
            }
        }

        String RoomNumber = txtRoomNumber.getText();
        double price = Double.parseDouble(txtRoomPrice.getText());
        int customerCount = Integer.parseInt(txtNumOfCustomer.getText());
        int bedCount = Integer.parseInt(txtBedCount.getText());
        boolean wifi = clickWifi.isSelected();
        boolean tv = clickTv.isSelected();
        boolean pool = clickPool.isSelected();
        boolean ac = clickAc.isSelected();
        boolean smoking = clickSmoking.isSelected();
        boolean hotWater = clickHotWater.isSelected();
        String category = roomCategoryComboBox.getValue();

        RoomsDto roomsDto = new RoomsDto();
        roomsDto.setId(0);
        roomsDto.setRoom_number(RoomNumber);
        roomsDto.setRoom_price(price);
        roomsDto.setBed_count(bedCount);
        roomsDto.setHas_ac(ac);
        roomsDto.setHas_wifi(wifi);
        roomsDto.setHas_tv(tv);
        roomsDto.setNum_of_guest(customerCount);
        roomsDto.setHas_pool(pool);
        roomsDto.setSmoking(smoking);
        roomsDto.setHot_water(hotWater);
        roomsDto.setCategory_id(1);
        roomsDto.setAvailability("Avalibility");

        roomsCategoryDtos.forEach((room) -> {
            if (roomCategoryComboBox.getSelectionModel().getSelectedItem().equals(room.getName())) {
                roomsDto.setCategory_id(room.getId());
            }

        });
        roomsDto.setCategory_id(roomsDto.getCategory_id());

        try {
            boolean isSaved = roomDAO.save(roomsDto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Room Saved").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private boolean validRooms() {

        if (txtRoomNumber.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Room number field empty").show();
            return false;
        }

        boolean matches = Pattern.matches("^[Rr]\\d{3}$", txtRoomNumber.getText());
        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid Room number").show();
            return false;
        }

        if (txtRoomPrice.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Room price Field Empty").show();
            return false;
        }

        boolean matches1 = Pattern.matches("(\\d{1,3}(,\\d{3})*|\\d+)", txtRoomPrice.getText());
        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid Room Price").show();
            return false;
        }


        if (txtBedCount.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Bed count Field Empty").show();
            return false;
        }

        boolean matches2 = Pattern.matches("\\d", txtBedCount.getText());
        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid bed count").show();
            return false;
        }


        if (txtNumOfCustomer.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Num of customer Field Empty").show();
            return false;
        }

        boolean matches3 = Pattern.matches("\\d", txtNumOfCustomer.getText());
        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid num of customer").show();
            return false;
        }

        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            getCategoryValues();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getCategoryValues() throws SQLException {

        List<RoomsCategoryDto> allRooms = roomCategoryDAO.getAll();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for (RoomsCategoryDto roomCategoryDto : allRooms) {
            observableList.add(roomCategoryDto.getName());
        }
        roomCategoryComboBox.setItems(observableList);

    }


    @FXML
    void hotwaterOnAction(ActionEvent event) {
        createBtn.requestFocus();
    }

    @FXML
    void numOfGuestOnAction(ActionEvent event) {
        txtBedCount.requestFocus();
    }

    @FXML
    void poolOnAction(ActionEvent event) {
        clickAc.requestFocus();
    }

    @FXML
    void roomPriceOnAction(ActionEvent event) {
        clickWifi.requestFocus();
    }

    @FXML
    void smokingOnAction(ActionEvent event) {
        clickHotWater.requestFocus();
    }

    @FXML
    void tvOnAction(ActionEvent event) {
        clickPool.requestFocus();
    }

    @FXML
    void txtRoomCategoryOnAction(ActionEvent event) {
        txtNumOfCustomer.requestFocus();
    }

    @FXML
    void txtRoomNumberOnAction(ActionEvent event) {
        roomCategoryComboBox.requestFocus();

    }

    @FXML
    void wifyOnAction(ActionEvent event) {
        clickTv.requestFocus();
    }


    @FXML
    void acOnAction(ActionEvent event) {
        clickSmoking.requestFocus();
    }

    @FXML
    void bedCountOnAction(ActionEvent event) {
        txtRoomPrice.requestFocus();
    }
}

