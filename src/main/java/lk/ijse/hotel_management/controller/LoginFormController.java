package lk.ijse.hotel_management.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lk.ijse.hotel_management.dao.custom.impl.UserDAOImpl;
import lk.ijse.hotel_management.dto.UserDto;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginFormController {
    public JFXTextField userNameField;
    public JFXPasswordField passwordField;
    public JFXButton loginBtn;
    public ImageView closebtn;
    public Label lblWP;

    @FXML
    private BorderPane root;

    UserDAOImpl userDAO = new UserDAOImpl();

    public void loginBtnOnAction(ActionEvent actionEvent) throws SQLException {
        ArrayList<UserDto> allUser = userDAO.getAll();
        String uname = userNameField.getText();
        String pass = passwordField.getText();

        for (int i = 0; i < allUser.size(); i++) {
            if (allUser.get(i).getUserName().equals(uname) && allUser.get(i).getPassword().equals(pass)) {
                try {

                    Parent parent = FXMLLoader.load(getClass().getResource("/view/navigation_form.fxml "));
                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();

                    Stage stage1 = (Stage) root.getScene().getWindow();
                    stage1.close();

                } catch (IOException e) {
                    throw new RuntimeException(e);

                }
            }else {
                new Alert(Alert.AlertType.ERROR,"wrong password or username try again!").show();
            }
        }

    }
    

    public void closeBtnOnMouseClicked(MouseEvent mouseEvent) {
        System.exit(0);
        
    }

    public void txtPasswordOnAction(ActionEvent actionEvent) {
        loginBtn.requestFocus();

    }

    public void txtUsernameOnAction(ActionEvent actionEvent) {
        passwordField.requestFocus();
    }

    public void txtUserNameOnMouseClicked(MouseEvent mouseEvent) {
         lblWP.setText("");
    }
}
