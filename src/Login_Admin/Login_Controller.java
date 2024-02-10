/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Login_Admin;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import gerer_des_clients.database;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HUAWEI
 */
public class Login_Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button close;

    @FXML
    private FontAwesomeIcon closebtn;

    @FXML
    private Button loginbtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
     private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
   
     public void login(){
         
        String sql="SELECT * FROM admin WHERE  nom = ? AND  password= ?";
        connect = database.connectDb();
        
        try{
            
            prepare=connect.prepareStatement(sql);
            prepare.setString(1, username.getText());
            prepare.setString(2, password.getText());
            result = prepare.executeQuery();
            Alert alert;
            if(username.getText().isEmpty() || password.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }
            else{
                if (!isValidPassword(password.getText())) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Password should contain only numbers and letters");
            alert.showAndWait();
            return; // exit the method to prevent further execution
        }else{
               if (!isValidUsername(username.getText())) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Username should contain only letters");
            alert.showAndWait();
            return; // exit the method to prevent further execution
        } 
               else{      
                if(result.next()){
                   
                   
                    
                   
                    
                    loginbtn.getScene().getWindow().hide();
                     Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("dashbord/dashbord_xml.fxml"));
                    
                    Stage stage=new Stage();
                    Scene scene = new Scene(root);
                    
                    stage.setScene(scene);
                    stage.show();
                }else{
                    alert= new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("wrong Username/Password");
                    alert.showAndWait();
                        }
                    }
                }
            }
            
        }catch(Exception e){e.printStackTrace();}
        
        
    }
     public void close(){
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    private boolean isValidUsername(String username) {
    // Use a regular expression to check if the username contains only letters
    return username.matches("[a-zA-Z]+");
}

private boolean isValidPassword(String password) {
    // Use a regular expression to check if the password contains only numbers and letters
    return password.matches("[a-zA-Z0-9]+");
}
}
