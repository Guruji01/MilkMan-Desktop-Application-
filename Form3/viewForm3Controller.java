package Form3;

/**
 * Sample Skeleton for 'viewForm3.fxml' Controller Class
 */

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Form2.customerbean2;
import MilkMan.myDbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class viewForm3Controller 
{
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tableview"
    private TableView<custbean3> tableview; // Value injected by FXMLLoader
    

    @FXML // fx:id="Dos"
    private DatePicker Dos; // Value injected by FXMLLoader
    
ResultSet table;
    
    ObservableList<custbean3> List;

    @FXML
    void doFetch(ActionEvent event)
    {
    	try {	
            PreparedStatement  pst=(PreparedStatement) con.prepareStatement("select * from BillingLog where Dos =?");
            LocalDate ldobe=Dos.getValue();
    	    java.sql.Date swdob=java.sql.Date.valueOf(ldobe);
    	    pst.setDate(1, swdob);
            
            getAllRecordsFromTable(pst);
       } 
       catch (SQLException e) 
          {
           e.printStackTrace();
          }
    	tableview.setItems(List);
    	}

    @FXML
    void doShowall(ActionEvent event)
    {
    	try {
            PreparedStatement  pst=con.prepareStatement("select * from BillingLog");
            
            getAllRecordsFromTable(pst);
       } 
       catch (SQLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
        
    	tableview.setItems(List);
    }
    
    void getAllRecordsFromTable(PreparedStatement pst)
    {
        List=FXCollections.observableArrayList();
        try {
             
             table= pst.executeQuery();
        while(table.next())
        {
        	String Cname, Dos, Doe, Days;
        	float Bqty,Cqty , TotalBill;
        	
        	String name = table.getString("Cname");
        	String dos1 = table.getString("Dos");
        	String doe1 = table.getString("Doe");
        	String days1 = table.getString("Days");
        	float bqty1 = table.getFloat("Bqty");
            float cqty1 = table.getFloat("Cqty");
            float tbill = table.getFloat("TotalBill");
      
            
            custbean3 obj2=new custbean3(name,dos1,doe1,days1,bqty1,cqty1,tbill);
           
            List.add(obj2);
             
        }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    void addColumns()

    {
    	 TableColumn<custbean3, String> cname=new TableColumn<custbean3, String>("Cname");
         cname.setCellValueFactory(new PropertyValueFactory<>("Cname"));
         
         TableColumn<custbean3, String> dos2=new TableColumn<custbean3, String>("Dos");
         dos2.setCellValueFactory(new PropertyValueFactory<>("Dos"));
         
         TableColumn<custbean3, String> doe2=new TableColumn<custbean3, String>("Doe");
         doe2.setCellValueFactory(new PropertyValueFactory<>("Doe"));
         
         TableColumn<custbean3, String> days2=new TableColumn<custbean3, String>("Days");
         days2.setCellValueFactory(new PropertyValueFactory<>("Days"));
         
         TableColumn<custbean3, Float> bqty2=new TableColumn<custbean3, Float>("Bqty");
         bqty2.setCellValueFactory(new PropertyValueFactory<>("Bqty"));
         
         TableColumn<custbean3, Float> cqty2=new TableColumn<custbean3, Float>("Cqty");
         cqty2.setCellValueFactory(new PropertyValueFactory<>("Cqty"));
         
         TableColumn<custbean3, Float> tbill2=new TableColumn<custbean3, Float>("Total Bill");
         tbill2.setCellValueFactory(new PropertyValueFactory<>("TotalBill"));
         
         
         
         tableview.getColumns().clear();
         tableview.getColumns().addAll(cname,dos2,doe2,days2,bqty2,cqty2,tbill2); 
    }


    Connection con;
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() 
    {
    	con= myDbConnection.doConnect();
      	 addColumns();
    }
}
