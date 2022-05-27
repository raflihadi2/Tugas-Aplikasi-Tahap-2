
package jam;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField tfId;
     @FXML
    private TextField tfMerek;
     @FXML
    private ComboBox<?> tfWarna;
    @FXML
    private TextField tfHarga;
    @FXML
    private DatePicker tfTanggal;
    @FXML
    private TableView<jam> tvBooks;
     @FXML
    private TableColumn<jam, Integer> colId;
     @FXML
    private TableColumn<jam, String> colMerek;
     @FXML
    private TableColumn<jam, String> colWarna;
      @FXML
    private TableColumn<jam, String> colHarga;
      @FXML
    private TableColumn<jam, String> colTanggal;
      @FXML
    private Button btnInsert;
      @FXML
    private Button btnUpdate;
      @FXML
    private Button btnDelete;

    
   @FXML
    private void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnInsert){
            insertRecord();
        }else if (event.getSource() == btnUpdate){
            updateRecord();
        }else if(event.getSource() == btnDelete){
            deleteButton();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         showBooks();
        ArrayList <String> list = new ArrayList<String>();
        list.add("Hitam");
        list.add("Coklat");
        list.add("Biru");
        list.add("Hijau");
        ObservableList items = FXCollections.observableArrayList(list);
        tfWarna.setItems(items);
    }    
    
    public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jam", "root","");
            return conn;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public ObservableList<jam> getBooksList(){
        ObservableList<jam> bookList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM jam";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            jam jm;
            while(rs.next()){
                jm = new jam(rs.getInt("id"), rs.getString("merek"), rs.getString("warna"), rs.getString("harga"),rs.getString("tanggal"));
                bookList.add(jm);
            }
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return bookList;
    }
    
    public void showBooks(){
        ObservableList<jam> list = getBooksList();
        
        colId.setCellValueFactory(new PropertyValueFactory<jam, Integer>("id"));
        colMerek.setCellValueFactory(new PropertyValueFactory<jam, String>("merek"));
        colWarna.setCellValueFactory(new PropertyValueFactory<jam, String>("warna"));
        colHarga.setCellValueFactory(new PropertyValueFactory<jam, String>("harga"));
        colTanggal.setCellValueFactory(new PropertyValueFactory<jam, String>("tanggal"));
        
        tvBooks.setItems(list);
    }
    
    private void insertRecord(){
        String query = "INSERT INTO vgacard VALUES (" + tfId.getText() + ",'" + tfMerek.getText() + "','" + tfWarna.getValue().toString() + "'," + tfHarga.getText() + "','" 
                + tfTanggal.getValue().toString() + ",";
        executeQuery(query);
        showBooks();
    }
    
    private void updateRecord(){
        String query = "UPDATE  vgacard SET merek  = '" + tfMerek.getText() + "', jenis = '" + tfWarna.getValue().toString() + "', harga  = '" + tfHarga.getText() + "' ,"+ "tanggal = " + tfTanggal.getValue().toString() + " WHERE id = " + tfId.getText() + "";
        executeQuery(query);
        showBooks();
    }
    
    private void deleteButton(){
        String query = "DELETE FROM vgacard WHERE id =" + tfId.getText() + "";
        executeQuery(query);
        showBooks();
    }
    
    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}