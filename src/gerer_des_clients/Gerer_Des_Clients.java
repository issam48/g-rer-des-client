/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package gerer_des_clients;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author HUAWEI
 */
public class Gerer_Des_Clients extends Application {
    
  private double x =0;
    private double y= 0;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Login_Admin/LoginFXML.fxml"));
        
        Scene scene = new Scene(root);
        root.setOnMouseDragged((MouseEvent event)->{
            
           stage.setX(event.getScreenX() - x);
           stage.setY(event.getScreenY() - y);
          
            
        });
        root.setOnMousePressed((MouseEvent event)->{
            x=event.getSceneX();
            y = event.getSceneY();
        });
        
        
         
        
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
