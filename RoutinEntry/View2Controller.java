/**
 * Sample Skeleton for 'View2.fxml' Controller Class
 */

package RoutinEntry;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class View2Controller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="listCname"
    private ListView<String> listCname; // Value injected by FXMLLoader

    @FXML // fx:id="Cq"
    private TextField Cq; // Value injected by FXMLLoader

    @FXML // fx:id="Bq"
    private TextField Bq; // Value injected by FXMLLoader

    @FXML // fx:id="Vdate"
    private DatePicker Vdate; // Value injected by FXMLLoader

    @FXML // fx:id="llResult"
    private Label llResult; // Value injected by FXMLLoader
    
    Connection con;
    @FXML
    void doSave(ActionEvent event)
    {
    	try 
		{
			// ObservableList<String> name = Cname.getSelectionModel().getSelectedItems();
			
		LocalDate ldos=	Vdate.getValue();
		java.sql.Date swdob= java.sql.Date.valueOf(ldos);
		String name =listCname.getSelectionModel().getSelectedItem();
		
		PreparedStatement abc=con.prepareStatement("insert into variation values(?,?,?,?)");
		abc.setString(1, name);
		abc.setDate(2, swdob);
		abc.setFloat(3,  Float.parseFloat(Cq.getText()));
		abc.setFloat(4,  Float.parseFloat(Bq.getText()));
		
		abc.executeUpdate();             
		llResult.setText("Saved..");	
		
		listCname.getSelectionModel().clearSelection();
		
		} 
    																																																																																								
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		} 
    }

    @FXML
    void doDeleteOthers(ActionEvent event)
    {
           ObservableList<String> Catch= listCname.getSelectionModel().getSelectedItems();
           listCname.getItems().retainAll(Catch);
    }

    void doFetch() throws Exception
    {
    
         PreparedStatement pst=	con.prepareStatement("select Cname from milktable");
    	 ResultSet rs=pst.executeQuery();
    	
    	while(rs.next())
     	{
     		String name =rs.getString("Cname");
    		listCname.getItems().add(name);  
     
     	}
    } 
    
    URL url;
   	Media media;
   	MediaPlayer mediaplayer;
   	AudioClip audio;
    
    void playSound()
    {
    	url=getClass().getResource("crash.wav");
		audio=new AudioClip(url.toString());
		audio.play();
    }
    
    @FXML
    void donext(MouseEvent event) 
    {
    	playSound();
    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Billing/View3.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			//to hide the opened window
			/* 
			   Scene scene1=(Scene)btnComboApp.getScene();
			   scene1.getWindow().hide();
			 */

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
         
    
    @FXML // This method is alled by the FXMLLoader when initialization is complete
    void initialize() throws Exception 
    {
    	con=(Connection) myDb2Connection.doConnect(); 
        	
     	doFetch();
    	listCname.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
     	  
    }
}
