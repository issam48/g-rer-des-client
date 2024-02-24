/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package dashbord;

import gerer_des_clients.database;
import java.awt.Label;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author HUAWEI
 */
public class Dashbord_xmlController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
    private Button ADDCLIENTBTN;

    @FXML
    private TableColumn<clientdatabase, String> ADRESSECLIENT;

    @FXML
    private TableView<clientdatabase> AVALIABLETABLE;

    @FXML
    private TextField AVALID_Adresse;

    @FXML
    private TextField AVALID_IDCLIENT;

    @FXML
    private TextField AVALID_Nif;

    @FXML
    private TextField AVALID_Nom;

    @FXML
    private TextField AVALID_RC;

    @FXML
    private Button CLIERCLIENTBTN;

    @FXML
    private Button DELETECLIENTBTN;

    @FXML
    private AnchorPane MAINFORMAVALIABLE;
    
    @FXML
    private AnchorPane menu_des_produits;

    @FXML
    private TableColumn<clientdatabase, String> NCLIENT;

    @FXML
    private TableColumn<clientdatabase, String> NIFCLENT;

    @FXML
    private TableColumn<clientdatabase, String> NOMKLIENT;

    @FXML
    private TableColumn<clientdatabase, String> RCCLIENT;

    @FXML
    private Button UPDATACLIENTBTN;

    @FXML
    private TextField analid_serch;
    @FXML
    private Button logoutBtn;

    
    @FXML
    private Button des_produits_btn;
       @FXML
    private Button hom_btn;
       
       private Connection connect;
       private PreparedStatement prepare;
       private Statement statement;
       private ResultSet result;
      
       ObservableList<clientdatabase> ClientList=FXCollections.observableArrayList();
       public void insertClient(){
           String sql="INSERT INTO client (Client_ID,Client_Name,Address,RC,NIF)"
                   +"VALUES(?,?,?,?,?)";
           connect=database.connectDb();
          Alert alert;
           try{
               
                if(AVALID_IDCLIENT.getText().isEmpty()|| 
                   AVALID_Nom.getText().isEmpty() ||
                  AVALID_Adresse.getText().isEmpty()||
                   AVALID_RC.getText().isEmpty() ||
                   AVALID_Nif.getText().isEmpty()){
               alert = new Alert(AlertType.ERROR);
               alert.setTitle("Error Message");
               alert.setHeaderText(null);
               alert.setContentText("please fill all blank fields");
               alert.showAndWait();
           }else{
                    String checkData="SELECT Client_ID FROM client WHERE Client_ID= '"
                            +AVALID_IDCLIENT.getText()+"'  ";
                    statement = connect.createStatement();
                    result=statement.executeQuery(checkData);
                    if(result.next()){
                         alert = new Alert(AlertType.ERROR);
               alert.setTitle("Error Message");
               alert.setHeaderText(null);
               alert.setContentText("client id"+AVALID_IDCLIENT.getText()+"was already exist!");
               alert.showAndWait();
                    }else{
                          prepare = connect.prepareStatement(sql);
               prepare.setString(1,AVALID_IDCLIENT.getText() );
               prepare.setString(2,AVALID_Nom.getText() );
               prepare.setString(3,AVALID_Adresse.getText() );
               prepare.setString(4,AVALID_RC.getText() );
               prepare.setString(5,AVALID_Nif.getText() );
              // prepare.setString(6,"1" );  
               prepare.executeUpdate();
               
               availableClientShowListData();
               
               availableclientclear();
                    }
                    
                
                }
               
               
           }catch(Exception e){e.printStackTrace();};
           
       }
       
       public void availableFlowersUpdate(){
           String sql = "UPDATE client SET Client_Name='"
                   +AVALID_Nom.getText()+"',Address='"
                   +AVALID_Adresse.getText()+"',RC='"
                   +AVALID_RC.getText()+"',NIF='"
                   +AVALID_Nif.getText()+"' WHERE Client_ID='"
                   +AVALID_IDCLIENT.getText()+"'";
           connect=database.connectDb();
           try{
               Alert alert;
               if(AVALID_IDCLIENT.getText().isEmpty()|| 
                   AVALID_Nom.getText().isEmpty() ||
                  AVALID_Adresse.getText().isEmpty()||
                   AVALID_RC.getText().isEmpty() ||
                   AVALID_Nif.getText().isEmpty()){
               alert = new Alert(AlertType.ERROR);
               alert.setTitle("Error Message");
               alert.setHeaderText(null);
               alert.setContentText("please fill all blank fields");
               alert.showAndWait();
           }else{
                   alert = new Alert(AlertType.CONFIRMATION);
               alert.setTitle("Confirmation Message");
               alert.setHeaderText(null);
               alert.setContentText("are you sure you want to UPDATE CLIENT");
               Optional<ButtonType>option=alert.showAndWait();
               if (option.get().equals(ButtonType.OK)){
                   statement=connect.createStatement();
                   statement.executeUpdate(sql);
                   availableClientShowListData();
                   availableclientclear();
                   
               }
               statement=connect.createStatement();}
           }catch(Exception e){e.printStackTrace();}
       }
       public void DeleteClient(){
           String sql="DELETE FROM client WHERE Client_ID='"
                   +AVALID_IDCLIENT.getText()+"'";
           connect=database.connectDb();
           try{
               Alert alert;
               if(AVALID_IDCLIENT.getText().isEmpty()|| 
                   AVALID_Nom.getText().isEmpty() ||
                  AVALID_Adresse.getText().isEmpty()||
                   AVALID_RC.getText().isEmpty() ||
                   AVALID_Nif.getText().isEmpty()){
               alert = new Alert(AlertType.ERROR);
               alert.setTitle("Error Message");
               alert.setHeaderText(null);
               alert.setContentText("please fill all blank fields");
               alert.showAndWait();
           }else{
                   alert = new Alert(AlertType.CONFIRMATION);
               alert.setTitle("Confirmation Message");
               alert.setHeaderText(null);
               alert.setContentText("are you sure you want to DELET CLIENT");
               Optional<ButtonType>option=alert.showAndWait();
               if (option.get().equals(ButtonType.OK)){
                   statement=connect.createStatement();
                   statement.executeUpdate(sql);
                   availableClientShowListData();
                   availableclientclear();
                   
               }}
           }catch(Exception e){e.printStackTrace();}
       }
       
       public void availableclientclear(){
           AVALID_IDCLIENT.setText("");
           AVALID_Nom.setText("");
           AVALID_Adresse.setText("");
           AVALID_RC.setText("");
           AVALID_Nif.setText("");
           
       }
      
  public ObservableList<clientdatabase> availablelistClient(){
       System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                              System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
       ObservableList<clientdatabase> listData = FXCollections.observableArrayList();
       String sql ="SELECT * FROM client";
       connect = database.connectDb();
       
       try{
           
           prepare = connect.prepareStatement(sql);
           result = prepare.executeQuery();
           System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                              System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
          clientdatabase client;
           while(result.next()){
               System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                  System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                   System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
               System.out.println(result.getInt("Client_ID")+"++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
               System.out.println(result.getString("Client_Name")+"++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
               System.out.println(result.getString("Address")+"++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
               System.out.println(result.getString("RC")+"++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");


               
               client= new clientdatabase(result.getInt("Client_ID"),
                       result.getString("Client_Name"),
                       result.getString("Address"),
                        result.getInt("NIF"),
                       result.getString("RC")
                     
               );
                listData.add(client);
           }
          
          
       }catch(Exception e){e.printStackTrace();}
        System.out.println("==================================");
         for(int i=0;i<listData.size();i++){ System.out.println(listData.get(i).getName()); }
       return listData;
   }
  public void availableFlowersSelect(){
      clientdatabase client=AVALIABLETABLE.getSelectionModel().getSelectedItem();
      int num=AVALIABLETABLE.getSelectionModel().getSelectedIndex();
      
      if((num-1)<-1)return;
      AVALID_IDCLIENT.setText(String.valueOf(client.getNclient()));
      AVALID_RC.setText((client.getRC()));
      AVALID_Nif.setText(String.valueOf(client.getNIF()));
      AVALID_Adresse.setText((client.getAdresse()));
      AVALID_Nom.setText((client.getName()));
      
  }
     /*  public ObservableList<clientdatabase> availableClient() {
    ObservableList<clientdatabase> listData = FXCollections.observableArrayList();
    String sql = "SELECT * FROM client";
    connect = database.connectDb();
    try {
        
        prepare = connect.prepareStatement(sql);
        result = prepare.executeQuery();
        
        clientdatabase client;
        
        while (result.next()) {
             client = new clientdatabase(
                    result.getInt("Client_ID"),
                    result.getString("Client_Name"),
                    result.getString("Address"),
                    result.getInt("NIF"),
                    result.getString("RC")
                    
            );
            listData.add(client);
            
           
            
        }
        System.out.println("==================================");
         for(int i=0;i<listData.size();i++){ System.out.println(listData.get(i)); }
        
        System.out.println(listData);
    } catch (SQLException e)  {
            e.printStackTrace();
        };
    
    return listData;
}*/

      
        private ObservableList<clientdatabase> avClient;
   public void availableClientShowListData(){
       avClient = availablelistClient();
    System.out.println("==================================");
         for(int i=0;i<avClient.size();i++){ System.out.println(avClient.get(i).getName()); }
    // Make sure to use the correct property names in PropertyValueFactory
    
    NCLIENT.setCellValueFactory(new PropertyValueFactory<>("Nclient"));
    
    NOMKLIENT.setCellValueFactory(new PropertyValueFactory<>("Name"));
    ADRESSECLIENT.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
    RCCLIENT.setCellValueFactory(new PropertyValueFactory<>("RC"));
    NIFCLENT.setCellValueFactory(new PropertyValueFactory<>("NIF"));
    AVALIABLETABLE.setItems(avClient);
      
   }
   public void SearchClient(){
       FilteredList<clientdatabase> filter=new FilteredList<>(avClient, e -> true);
       analid_serch.textProperty().addListener((Observable,oldValue,newValue)->{
       filter.setPredicate(PrediateClientdata ->{
       
           if(newValue.isEmpty() || newValue==null){
               return true;
           }
           String searchKey=newValue.toLowerCase();
          if(PrediateClientdata.getName().contains(searchKey)){
              return true;
          }else  return false;
          
       });
   });
       SortedList<clientdatabase>sortList = new SortedList<>(filter);
       sortList.comparatorProperty().bind(AVALIABLETABLE.comparatorProperty());
       AVALIABLETABLE.setItems(sortList);
   }
      public void switchForm(ActionEvent event){
          if(event.getSource()==hom_btn){
              MAINFORMAVALIABLE.setVisible(true);
              menu_des_produits.setVisible(false);
              
              hom_btn.setStyle(" -fx-background-color:linear-gradient(to bottom, #ffffff,#656262);");
              des_produits_btn.setStyle("-fx-backgroun-color:transparent");
              
                     availableClientShowListData();
                     SearchClient();
          }else if(event.getSource()==des_produits_btn){
              MAINFORMAVALIABLE.setVisible(false);
              menu_des_produits.setVisible(true);
              
              des_produits_btn.setStyle(" -fx-background-color:linear-gradient(to bottom, #ffffff,#656262);");
              hom_btn.setStyle("-fx-backgroun-color:transparent");
          }
      }
      
      private double x =0;
      private double y=0;
      @FXML
    private AnchorPane main_form;
      public void logout(){
          try{
              Alert alert = new Alert(AlertType.CONFIRMATION);
              alert.setTitle("Confirmation Message");
              alert.setHeaderText(null);
              alert.setContentText("Are you sure you want to logout?");
              alert.showAndWait();
              
              Optional<ButtonType> option= alert.showAndWait();
              System.out.print("==========================================++");
              if (option.get().equals(ButtonType.OK)){
                  System.out.print("==========================================");
                  logoutBtn.getScene().getWindow().hide();
                  Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Login_Admin/LoginFXML.fxml"));
                  Scene scene= new Scene(root);
                  Stage stage= new Stage();
                  
                  root.setOnMousePressed((MouseEvent event)->{
                      x=event.getSceneX();
                      y=event.getSceneY();
                  });
                  root.setOnMouseDragged((MouseEvent event)->{
                      stage.setX(event.getScreenX()-x);
                      stage.setY(event.getScreenY()-y);
                     
                  });
                  
                  stage.initStyle(StageStyle.TRANSPARENT);
                  stage.setScene(scene);
                  stage.show();
              }
          }catch(Exception e){e.printStackTrace();}
      }
      
      
    public void close(){
        System.exit(0);
    }
    public void minimize(){
        Stage stage= (Stage)main_form.getScene().getWindow();
        stage.setIconified(true);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       availableClientShowListData();
      //loadDate();
         
    }    
  /*  private void refreshTable(){
            try {
                ClientList.clear();
                query="SELECT * FROM `clients`";
                prepare=connect.prepareStatement(query);
                result=prepare.executeQuery();
                
                while(result.next()){
                    ClientList.add(new clientdatabase(
                    result.getInt("Client_ID"),
                    result.getString("Client_Name"),
                    result.getString("Address"),
                    result.getString("RC"),
                    result.getInt("NIF")
                    ));
                    AVALIABLETABLE.setItems(ClientList);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Dashbord_xmlController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }*/

  /*  private void loadDate() {
        
        connect=database.connectDb();
        refreshTable();
        NCLIENT.setCellValueFactory(new PropertyValueFactory<>("Client_ID"));
        NOMKLIENT.setCellValueFactory(new PropertyValueFactory<>("Client_Name"));
        ADRESSECLIENT.setCellValueFactory(new PropertyValueFactory<>("Address"));
        RCCLIENT.setCellValueFactory(new PropertyValueFactory<>("RC"));
        NIFCLENT.setCellValueFactory(new PropertyValueFactory<>("NIF"));
    }*/
    
}
