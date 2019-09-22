package Form1;

import java.awt.Desktop;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import MilkMan.myDbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class ViewForm1Controller 

   {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="dtpdos"
    private DatePicker dtpdos; // Value injected by FXMLLoader

    @FXML // fx:id="tableview"
    private TableView<customerbean> tableview; // Value injected by FXMLLoader
    
    ResultSet table;
    
    ObservableList<customerbean> list;

    @FXML
    void doBufflowdata(MouseEvent event) {
    	try {
            PreparedStatement  pst=con.prepareStatement("select * from milktable where BmQty>0");
            
            getAllRecordsFromTable(pst);
       } 
       catch (SQLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
    	tableview.setItems(list);
    }

    @FXML
    void doCowdata(MouseEvent event) {
    	try {
            PreparedStatement  pst=con.prepareStatement("select * from milktable where CmQty>0 ");
            
            getAllRecordsFromTable(pst);
       } 
       catch (SQLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
    	tableview.setItems(list);
    }

    @FXML
    void doFinddos(ActionEvent event) 
    {
    	try {
    		
            PreparedStatement  pst=con.prepareStatement("select * from milktable where dos=?");
            LocalDate ldobe=dtpdos.getValue();
    	java.sql.Date swdob=java.sql.Date.valueOf(ldobe);
    	pst.setDate(1, swdob);
            
            getAllRecordsFromTable(pst);
       } 
       catch (SQLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
    	tableview.setItems(list);
    }

    @FXML
    void doShowall(ActionEvent event) {
    	 try {
             PreparedStatement  pst=con.prepareStatement("select * from milktable");
             
             getAllRecordsFromTable(pst);
        } 
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
        tableview.setItems(list);
    }

    
    
    void getAllRecordsFromTable(PreparedStatement pst)//2
    {
        list=FXCollections.observableArrayList();
        try {
             
             table= pst.executeQuery();
        while(table.next())
        {
        	String name = table.getString("Cname");
        	String mob = table.getString("Mobile");
        	String add = table.getString("Address");
            float cqty=table.getFloat("CmQty");
            float cprice=table.getFloat("CmPrice");
            float bqty=table.getFloat("BmQty");
            float bprice=table.getFloat("BmPrice");
            String dob=table.getString("dos");
            String path = table.getString("PicPath");
            
           
            customerbean obj=new customerbean(name,mob,add,cqty,cprice,bqty,bprice,dob,path);
           
        
            
            list.add(obj);
            System.out.println(name+"\t"+mob+"\t"+add+"\t"+cqty+"\t"+cprice+"\t"+bqty+"\t"+bprice+"\t"+dob+"\t"+path);
             
        }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    @FXML
    void Excel(MouseEvent event) 
    {

    
    try {
       // Desktop.getDesktop().browse(new URI("http://www.realJavaOnline.com"));
       // Desktop.getDesktop().mail();
      //  Desktop.getDesktop().open(new File("C:\\Python27\\news.txt"));
       // Desktop.getDesktop().open(new File("E:\\Hindi Videos\\akshay.mp4"));
    	Desktop.getDesktop().open(new File("C:\\Program Files\\Microsoft Office\\Office16\\EXCEL.EXE"));
    } catch (Exception e1) 
	{
        e1.printStackTrace();
    }
    }
    
    void addColumns()
    {
    	 TableColumn<customerbean, String> cname=new TableColumn<customerbean, String>("CName");//Dikhava Title
         cname.setCellValueFactory(new PropertyValueFactory<>("Cname"));//bean field name not table col. name
         
         TableColumn<customerbean, String> mobile=new TableColumn<customerbean, String>("MOBILE");//Dikhava Title
         mobile.setCellValueFactory(new PropertyValueFactory<>("Mobile"));
         
         TableColumn<customerbean, String> address=new TableColumn<customerbean, String>("ADDRESS");//Dikhava Title
         address.setCellValueFactory(new PropertyValueFactory<>("Address"));
         
         TableColumn<customerbean, Float> cqty=new TableColumn<customerbean, Float>("CQTY");//Dikhava Title
         cqty.setCellValueFactory(new PropertyValueFactory<>("CmQty"));
         
         TableColumn<customerbean, Float> cprice=new TableColumn<customerbean, Float>("CPRICE");//Dikhava Title
         cprice.setCellValueFactory(new PropertyValueFactory<>("CmPrice"));
         
         TableColumn<customerbean, Float> bqty=new TableColumn<customerbean, Float>("BQTY");//Dikhava Title
         bqty.setCellValueFactory(new PropertyValueFactory<>("BmQty"));
         
         TableColumn<customerbean, Float> bprice=new TableColumn<customerbean, Float>("BPRICE");//Dikhava Title
         bprice.setCellValueFactory(new PropertyValueFactory<>("BmPrice"));
         
         TableColumn<customerbean, String> path=new TableColumn<customerbean, String>("DOS");//Dikhava Title
         path.setCellValueFactory(new PropertyValueFactory<>("PicPath"));
         
         tableview.getColumns().clear();
         tableview.getColumns().addAll(cname,mobile,address,cqty,cprice,bqty,bprice,path); 
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
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Form2/viewForm2.fxml")); 
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

     Connection con;
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() 
    {
    	 con= myDbConnection.doConnect();
    	 addColumns();
    }
}
