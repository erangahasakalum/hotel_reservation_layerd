package lk.ijse.hotel_management.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lk.ijse.hotel_management.dao.custom.impl.UserDAOImpl;
import lk.ijse.hotel_management.dto.UserDto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SettingFormController {

    public static int userId;
    public Label redLblPassword;
    @FXML
    private TextField txtConfirmUsername;

    @FXML
    private TextField txtNewUsername;


    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtConfirmPassword;

    @FXML
    private Label redLblUsername;

    @FXML
    private Label redLblConfirmUsername;

    @FXML
    private TextField txtNewPassword;

    @FXML
    private TextField txtPassword;

    @FXML
    private Label lblConfirmPassword;

    UserDAOImpl userModel = new UserDAOImpl();
    UserDAOImpl userDAO = new UserDAOImpl();

    public void setEditableTextField(TextField textField , boolean onOff ){
        textField.setEditable(onOff);
        textField.setEditable(onOff);

    }

    @FXML
    void btnUserNameChange(ActionEvent event) throws SQLException {

        String confirmUsername = txtConfirmUsername.getText();

        var user = new UserDto(confirmUsername);

        try{
            boolean isUpdated = userDAO.updateUser(confirmUsername ,userId);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"updated").show();
                clearTextField(txtUsername,txtNewUsername,txtConfirmUsername);
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }


    public void verifyUsernameOnAction(javafx.scene.input.KeyEvent keyEvent) throws SQLException {
        setEditableTextField(txtNewUsername , false);
        setEditableTextField(txtConfirmUsername , false);
        String username = txtUsername.getText();

        ArrayList<UserDto> userDtoList = userDAO.getAll();

        for (int i = 0; i < userDtoList.size(); i++){
            if (username.equals(userDtoList.get(i).getUserName())){
                setEditableTextField(txtNewUsername , true);
                setEditableTextField(txtConfirmUsername , true);
            }
        }
       // checkPassword.incorrectCredential(userDtoList , username , "Invalid Username!" , redLblUsername);
    }

    public void confirmUsernameOnAction(javafx.scene.input.KeyEvent keyEvent) {
        String newUsername = txtNewUsername.getText();
        String confirmUsername = txtConfirmUsername.getText();

        if (newUsername.equals(confirmUsername)){
            redLblConfirmUsername.setVisible(false);
        }else {
            redLblConfirmUsername.setText("Please New Username Confirm");
        }
    }

    @FXML
    void btnChangePasswordOnAction(ActionEvent event) throws SQLException {

        String confirmPassword = txtConfirmPassword.getText();
        String oldPassword = txtPassword.getText();

        List<UserDto> userDtoList = userDAO.getAll();

        var user = new UserDto(confirmPassword);
        try {
            boolean isUpdatePassword = userModel.updateUserPassword(confirmPassword , userId);
            if (isUpdatePassword){
                new Alert(Alert.AlertType.CONFIRMATION,"Update Password Successfully!").show();
                clearTextField(txtPassword,txtNewPassword,txtConfirmPassword);
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void confirmPasswordOnAction(javafx.scene.input.KeyEvent keyEvent) {
        String newPassword = txtNewPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();

        if (newPassword.equals(confirmPassword)){
            lblConfirmPassword.setVisible(false);
        }else {
            lblConfirmPassword.setText("Please New Password Confirm");
        }
    }

    public void verifyPasswordOnActon(KeyEvent keyEvent) throws SQLException {
        setEditableTextField(txtNewPassword , false);
        setEditableTextField(txtConfirmPassword , false);
        String password = txtPassword.getText();

        ArrayList<UserDto> userDtoList = userDAO.getAll();

        for (int i = 0; i < userDtoList.size(); i++){
            if (password.equals(userDtoList.get(i).getPassword())){
                setEditableTextField(txtNewPassword , true);
                setEditableTextField(txtConfirmPassword , true);
            }
        }
      //  checkPassword.incorrectCredential(userDtoList , password , "Invalid Password!" , redLblPassword);
    }

    public void clearTextField(TextField oldUsernamePasword , TextField  newUsernamePassword , TextField ConfirmUsernamePasword){
        oldUsernamePasword.setText("");
        newUsernamePassword.setText("");
        ConfirmUsernamePasword.setText("");
    }

}