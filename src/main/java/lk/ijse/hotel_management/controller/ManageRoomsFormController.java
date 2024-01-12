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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.hotel_management.dao.custom.impl.RoomCategoryDAOImpl;
import lk.ijse.hotel_management.dao.custom.impl.RoomDAOImpl;
import lk.ijse.hotel_management.dto.*;
import lk.ijse.hotel_management.view.tdm.RoomsDetailsTm;
import lk.ijse.hotel_management.view.tdm.RoomsTm;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ManageRoomsFormController implements Initializable {

    @FXML
    private TableColumn<RoomsDetailsTm, ?> ac;

    @FXML
    private TableColumn<RoomsDetailsTm, ?> bedCount;

    @FXML
    private TableColumn<RoomsDetailsTm, ?> category;

    @FXML
    private JFXButton createBtn;

    @FXML
    private JFXButton createRoomsBtn;

    @FXML
    private TableColumn<RoomsDetailsTm, ?> delete;

    @FXML
    private TableColumn<RoomsDetailsTm, ?> hotwater;

    @FXML
    private TableColumn<RoomsDetailsTm, ?> pool;

    @FXML
    private TableColumn<RoomsDetailsTm, ?> price;

    @FXML
    private TableColumn<RoomsDetailsTm, ?> roomNumber;

    @FXML
    private TableView<RoomsDetailsTm> roomsTable;

    @FXML
    private TableColumn<RoomsDetailsTm, ?> smking;

    @FXML
    private TableColumn<RoomsDetailsTm, ?> tv;

    @FXML
    private TableColumn<RoomsDetailsTm, ?> update;

    @FXML
    private TableColumn<RoomsDetailsTm, ?> wifi;

    RoomDAOImpl roomDAO = new RoomDAOImpl();

    RoomCategoryDAOImpl roomCategoryDAO = new RoomCategoryDAOImpl();

    @FXML
    void createBtnOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/addRooms_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("create rooms");
        stage.show();
    }


    public void createCategoryOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/addCategory_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("create category");
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
        RoomsDetailsTm roomsDetailsTableMOdel = new RoomsDetailsTm();
        RoomsTm roomsTableModel = new RoomsTm();
        List<RoomsDto> allRooms = roomDAO.getAll();
        ObservableList<RoomsDetailsTm> observableList = FXCollections.observableArrayList();
        System.out.println(observableList);


        for (RoomsDto room: allRooms) {
           String categoryName =  roomCategoryDAO.getCategoryName(room.getCategory_id());
           roomsDetailsTableMOdel.setRoom_number(room.getRoom_number());
           roomsDetailsTableMOdel.setRoom_price(room.getRoom_price());
           roomsDetailsTableMOdel.setBed_count(room.getBed_count());
           roomsDetailsTableMOdel.setRoom_category(categoryName);
           roomsDetailsTableMOdel.setHot_water(String.valueOf(room.isHot_water()));
           roomsDetailsTableMOdel.setAc(String.valueOf(room.isHas_ac()));
           roomsDetailsTableMOdel.setPool(String.valueOf(room.isHas_pool()));
           roomsDetailsTableMOdel.setSmoking(String.valueOf(room.isSmoking()));
           roomsDetailsTableMOdel.setTv(String.valueOf(room.isHas_tv()));
           roomsDetailsTableMOdel.setWifi(String.valueOf(room.isHas_wifi()));
            observableList.add(roomsDetailsTableMOdel);
        }

        roomsTable.setItems(observableList);
        roomsTable.getColumns().addAll( roomNumber,category, bedCount,wifi,ac,hotwater,smking,pool
                ,tv, price, delete, update);
    }

    private void setValues() {
        roomNumber.setCellValueFactory(new PropertyValueFactory<>("room_number"));
        price.setCellValueFactory(new PropertyValueFactory<>("room_price"));
        category.setCellValueFactory(new PropertyValueFactory<>("rooms_category"));
        bedCount.setCellValueFactory(new PropertyValueFactory<>("bed_count"));
        delete.setCellValueFactory(new PropertyValueFactory<>("deletebtn"));
        update.setCellValueFactory(new PropertyValueFactory<>("updatebtn"));
        pool.setCellValueFactory(new PropertyValueFactory<>("pool"));
        ac.setCellValueFactory(new PropertyValueFactory<>("ac"));
        tv.setCellValueFactory(new PropertyValueFactory<>("tv"));
        wifi.setCellValueFactory(new PropertyValueFactory<>("wifi"));
        smking.setCellValueFactory(new PropertyValueFactory<>("smoking"));
        hotwater.setCellValueFactory(new PropertyValueFactory<>("hot_water"));
    }

    public void getCategory() {
    }

}
