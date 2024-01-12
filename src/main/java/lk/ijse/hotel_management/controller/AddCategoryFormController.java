package lk.ijse.hotel_management.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.hotel_management.dao.custom.impl.RoomCategoryDAOImpl;
import lk.ijse.hotel_management.dto.RoomsCategoryDto;
import lk.ijse.hotel_management.view.tdm.RoomCategoryTm;

import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

public class AddCategoryFormController {

    @FXML
    private JFXButton cancelBtn;

    @FXML
    private TableColumn<RoomCategoryTm, String> categoryDescription;

    @FXML
    private TableColumn<RoomCategoryTm, String> categoryId;

    @FXML
    private TableColumn<RoomCategoryTm, String> categoryName;

    @FXML
    private TableView<RoomCategoryTm> categoryTable;

    @FXML
    private TextField txtCategoryName;

    @FXML
    private TextArea txtDesc;

    RoomCategoryDAOImpl roomCategoryDAO = new RoomCategoryDAOImpl();

    @FXML
    void cancelBtnOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void createOnAction(ActionEvent event) {
        String name = txtCategoryName.getText();
        String description = txtDesc.getText();

        if (name.isEmpty() || description.isEmpty()){
            new Alert(Alert.AlertType.CONFIRMATION,"You're not fill mandatory fields").show();
            return;
        }

        RoomsCategoryDto dto = new RoomsCategoryDto(0,name,description);

        try {
            boolean isSaved = roomCategoryDAO.save(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Category Saved").show();
                clearFields();
                loadAllCategory();
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void clearFields() {
        txtCategoryName.setText(" ");
        txtDesc.setText(" ");
    }

    public void initialize() {
        setCellValueFactory();
        loadAllCategory();
    }

    private void loadAllCategory() {
        RoomCategoryDAOImpl model = new RoomCategoryDAOImpl();

        ObservableList<RoomCategoryTm> obList = FXCollections.observableArrayList();

        try {
            List<RoomsCategoryDto> dtoList = model.getAll();

            for (RoomsCategoryDto dto : dtoList) {
                obList.add(
                        new RoomCategoryTm(
                                dto.getId(),
                                dto.getName(),
                                dto.getDescription(),
                                new JFXButton()

                        )
                );
            }

            categoryTable.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        categoryId.setCellValueFactory(new PropertyValueFactory<>("id"));
        categoryName.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

    }

    @FXML
    void txtDescriptionOnAction(MouseEvent event) {

    }

    @FXML
    void txtNameOnAction(ActionEvent event) {
        txtDesc.requestFocus();

    }

    public void txtDescriptionOnAction(javafx.scene.input.MouseEvent mouseEvent) {
    }

    public void txtAmountOnAction(ActionEvent actionEvent) {
    }
}
