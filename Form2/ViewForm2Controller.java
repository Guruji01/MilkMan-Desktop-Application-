/**
 * Sample Skeleton for 'ViewForm2.fxml' Controller Class
 */

package Form2;

import java.awt.Label;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;

import Form2.customerbean2;
import MilkMan.myDbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewForm2Controller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="dos"
    private DatePicker dos; // Value injected by FXMLLoader

    @FXML // fx:id="doe"
    private DatePicker doe; // Value injected by FXMLLoader

    @FXML // fx:id="lblComboitem"
    private ComboBox<String> lblComboitem; // Value injected by FXMLLoader

    @FXML // fx:id="lblTable"
    private TableView<customerbean2> lblTable; // Value injected by FXMLLoader
    

    @FXML // fx:id="lab"
    private Label lab; // Value injected by FXMLLoader
    
    ResultSet table;
    
    ObservableList<customerbean2> list;

    @FXML
    void doFetch(ActionEvent event) 
    {
         try {
    		
        	 String name = lblComboitem.getSelectionModel().getSelectedItem();
            PreparedStatement  pst=con.prepareStatement("select * from variation where Cname=?");
        
    	pst.setString(1, name);
            
            getAllRecordsFromTable(pst);
       } 
       catch (SQLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
         lblTable.setItems(list);
    }
    
   @FXML
    void doDelete(ActionEvent event) throws SQLException 
    {
        String name = lblComboitem.getSelectionModel().getSelectedItem();
		PreparedStatement pst=	con.prepareStatement("delete from variation where Cname=?");
		pst.setString(1, name);
		int count=pst.executeUpdate();
	//	lab.setText(count+" Records Deleted..");
    } 
    
    @FXML
    void doShowall(ActionEvent event) {
    	 try {
             PreparedStatement  pst=con.prepareStatement("select * from variation");
             
             getAllRecordsFromTable(pst);
        } 
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
    	 lblTable.setItems(list);
    }
    
    @FXML
    void Dosearch(ActionEvent event)
    {
    	try {	
            PreparedStatement  pst=(PreparedStatement) con.prepareStatement("select * from variation where Date=?");
            LocalDate ldobe=dos.getValue();
    	    java.sql.Date swdob=java.sql.Date.valueOf(ldobe);
    	    pst.setDate(1, swdob);
            
            getAllRecordsFromTable(pst);
       } 
       catch (SQLException e) 
          {
           e.printStackTrace();
          }
    	lblTable.setItems(list);
    }
    
    void getAllRecordsFromTable(PreparedStatement pst)//2
    {
        list=FXCollections.observableArrayList();
        try {
             
             table= pst.executeQuery();
        while(table.next())
        {
        	String name = table.getString("Cname");
        	String path = table.getString("Date");
        	float cowqty = table.getFloat("CowQty");
            float befqty=table.getFloat("BefQty");
      
            
            customerbean2 obj2=new customerbean2(name,path,cowqty,befqty);
           
            list.add(obj2);
             
        }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    void addColumns()

    {
    	 TableColumn<customerbean2, String> cname=new TableColumn<customerbean2, String>("Cname");
         cname.setCellValueFactory(new PropertyValueFactory<>("Cname"));
         
         TableColumn<customerbean2, String> path2=new TableColumn<customerbean2, String>("Date");
         path2.setCellValueFactory(new PropertyValueFactory<>("Date"));
         
         TableColumn<customerbean2, Float> cowqty=new TableColumn<customerbean2, Float>("CowQty");
         cowqty.setCellValueFactory(new PropertyValueFactory<>("CowQty"));
         
         TableColumn<customerbean2, Float> befqty=new TableColumn<customerbean2, Float>("BefQty");
         befqty.setCellValueFactory(new PropertyValueFactory<>("BefQty"));
         
         
         
         lblTable.getColumns().clear();
         lblTable.getColumns().addAll(cname,path2,cowqty,befqty); 
    }
    
    

    java.sql.Connection con;
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() 
    {
    	String ary[]={"Harry Poter","Ron Weasley","Hermione Granger","Voldemort","Albus Dumbledore","Severus Snape",
    			"Draco Malfoy","Rubeus Hagride"};
    	lblComboitem.getItems().addAll(ary);  
    	con= myDbConnection.doConnect();
   	     addColumns();

    }
}
