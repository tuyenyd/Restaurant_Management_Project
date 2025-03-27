package com.restaurant.restaurant_management_project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;

public class LoginController {
    @FXML
    private TextField userName;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private ToggleButton showPasswordBtn;
    @FXML
    private Button loginButton;
    @FXML
    private Text forgotPassword;
    @FXML
    public void initialize()
    {
        passwordField.textProperty().bindBidirectional(passwordTextField.textProperty());
    }
    @FXML
    public void showPassword()
    {
        if(showPasswordBtn.isSelected())
        {
            passwordTextField.toFront();
        }else
            passwordField.toFront();
    }
    @FXML
    public void forgotPassword()
    {

    }
}
