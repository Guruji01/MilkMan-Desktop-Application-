/**
 * Sample Skeleton for 'View.fxml' Controller Class
 */

package MilkMan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class ViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="mobile"
    private TextField mobile; // Value injected by FXMLLoader

    @FXML // fx:id="address"
    private TextArea address; // Value injected by FXMLLoader

    @FXML // fx:id="cow"
    private TextField cow; // Value injected by FXMLLoader

    @FXML // fx:id="Beefalo"
    private TextField Beefalo; // Value injected by FXMLLoader

    @FXML // fx:id="Cprice"
    private TextField Cprice; // Value injected by FXMLLoader

    @FXML // fx:id="Bprice"
    private TextField Bprice; // Value injected by FXMLLoader
    
    @FXML // fx:id="dos"
    private DatePicker dosb; // Value injected by FXMLLoader
    Connection con;

    @FXML // fx:id="lblResult"
    private Label lblResult; // Value injected by FXMLLoader

    @FXML // fx:id="comboItems"
    private ComboBox<String> comboItems; // Value injected by FXMLLoader
    
    
    @FXML // fx:id="idImage"
    private ImageView idImage; // Value injected by FXMLLoader
    
    PreparedStatement pst;
    
    String path;
    
    @FXML
    void doBrowser(ActionEvent event) throws FileNotFoundException 
    {
    	FileChooser fc = new FileChooser();
    //	fc.getExtensionFilters().addAll(new ExtensionFilter("PDF Files","*.pdf"));
    	fc.getExtensionFilters().addAll(new ExtensionFilter("pc image","*.jpg"));
    	File sf = fc.showOpenDialog(null);
    	if(sf!=null)
    	{
    		Image img = new Image(new FileInputStream(sf));
    		idImage.setImage(img);	
    	}
    	else
    	{
    		System.out.println("file not found");
    	}
    	 path = sf.getAbsolutePath();
    	 lblResult.setText(sf.getAbsolutePath());
    } 

    @FXML
    void doDelete(ActionEvent event) 
    {
    	try 
		{
    		String name = comboItems.getSelectionModel().getSelectedItem();
			
		PreparedStatement pst=	con.prepareStatement("delete from MilkTable where Cname=?");
		pst.setString(1, name);
		int count=pst.executeUpdate();
		lblResult.setText(count+" Records Deleted");
		
		}
    	catch(Exception exp){}
    }

    @FXML
    void doFetch(ActionEvent event) 
    {
    	lblResult.setText("");
    	
    	try {
    		String name = comboItems.getSelectionModel().getSelectedItem();
    		
			PreparedStatement pst=con.prepareStatement("select * from MilkTable where Cname=?");
			pst.setString(1, name);
			ResultSet table=pst.executeQuery();
		
		boolean jasus=false;
		while(table.next())
		{
			jasus=true;
			float per=table.getFloat("Mobile");
			String name1=table.getString("Address");
			
			float per2=table.getFloat("CmQty");
			float per3=table.getFloat("CmPrice");
			float per4=table.getFloat("BmQty");
			float per5=table.getFloat("BmPrice");
		//	String per6 = table.getString("PicPath");
			
			java.sql.Date dob=table.getDate("dos");
			//System.out.println(roll+"\t"+per+"\t"+name+"\t"+dob.toString());
			mobile.setText(per+"");
			address.setText(name1);
			
			cow.setText(per2+"");
			Cprice.setText(per3+"");
			Beefalo.setText(per4+"");
			Bprice.setText(per5+"");
			//idImage.setImage(per6+"");
			
			dosb.getEditor().setText(dob.toString());
			
		}
		
		if(jasus==false)
			lblResult.setText("Invalid Name");
		} 
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    }

    
    @FXML
    void doSave(ActionEvent event)
    {
    	try 
		{
			String name = comboItems.getSelectionModel().getSelectedItem();
	
			
		LocalDate ldos=	dosb.getValue();
		java.sql.Date swdob= java.sql.Date.valueOf(ldos);
		
		
		PreparedStatement pst=	con.prepareStatement("insert into MilkTable values(?,?,?,?,?,?,?,?,?)");
		pst.setString(1, name);
		pst.setString(2, mobile.getText());
		pst.setString(3, address.getText());
		pst.setFloat(4,  Float.parseFloat(cow.getText()));
		pst.setFloat(5,  Float.parseFloat(Cprice.getText()));
		pst.setFloat(6,  Float.parseFloat(Beefalo.getText()));
		pst.setFloat(7,  Float.parseFloat(Bprice.getText()));
		pst.setDate(8, swdob);
		pst.setString(9,  lblResult.getText());
		
		pst.executeUpdate();
		lblResult.setText("Saved..");	
		} 
    																																																																																								
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		} 
    }
    
    @FXML
    void doNew(ActionEvent event) 
    {
    	mobile.clear();
    	address.clear();
    	cow.clear();
    	Beefalo.clear();
    	Cprice.clear();
    	Bprice.clear();
    	lblResult.setText("");
    }

    @FXML
    void doUpdate(ActionEvent event) 
    {
    	try 
		{
    		String name = comboItems.getSelectionModel().getSelectedItem();
    		
			
			String name2 = mobile.getText();  
			String name3 = address.getText();
			float per1= Float.parseFloat(cow.getText());
			float per2= Float.parseFloat(Cprice.getText());
			float per3=  Float.parseFloat(Beefalo.getText());
			float per4=  Float.parseFloat(Bprice.getText());

			String per5 = lblResult.getText();  
			
			
			
			
		
		java.sql.Date swdob=null;
		String stwdob="";
		
		LocalDate lwdob=dosb.getValue();
		if(lwdob==null)
			{
				stwdob=dosb.getEditor().getText();
				lwdob=LocalDate.parse(stwdob);
			}
			swdob= java.sql.Date.valueOf(lwdob);
		
			//              									   tableName	
		PreparedStatement pst=	con.prepareStatement("update MilkTable set Mobile=?, Address=?, CmQty=?, CmPrice=?, BmQty=?, BmPrice=?, dos=?, PicPath=? where Cname=?");
		pst.setString(9, name); 
		pst.setString(1, name2);
		pst.setString(2, name3);
		pst.setFloat(3, per1);
		pst.setFloat(4, per2);
		pst.setFloat(5, per3);
		pst.setFloat(6, per4);
		pst.setDate(7, swdob);
		pst.setString(8,per5);
		
		pst.executeUpdate();//fire query in table
		lblResult.setText("Update..");
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//in parameters
		
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
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("RoutinEntry/View2.fxml")); 
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

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() 
    {
    	String ary[]={"Harry Poter","Ron Weasley","Hermione Granger","Voldemort","Albus Dumbledore","Severus Snape",
    			"Draco Malfoy","Rubeus Hagride"};
    	   comboItems.getItems().addAll(ary);   
    	   con=myDbConnection.doConnect();
    }
}








