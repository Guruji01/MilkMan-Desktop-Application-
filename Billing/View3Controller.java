/**
 * Sample Skeleton for 'View3.fxml' Controller Class
 */

package Billing;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.event.*;

public class View3Controller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="Bqty"
    private TextField Bqty; // Value injected by FXMLLoader

    @FXML // fx:id="Cqty"
    private TextField Cqty; // Value injected by FXMLLoader

    @FXML // fx:id="Cbill"
    private TextField Cbill; // Value injected by FXMLLoader

    @FXML // fx:id="Bbill"
    private TextField Bbill; // Value injected by FXMLLoader

    @FXML // fx:id="totalBill"
    private TextField totalBill; // Value injected by FXMLLoader

    @FXML // fx:id="lbldos"
    private DatePicker lbldos; // Value injected by FXMLLoader

    @FXML // fx:id="lbldoe"
    private DatePicker lbldoe; // Value injected by FXMLLoader

    @FXML // fx:id="mobile"
    private TextField mobile; // Value injected by FXMLLoader

    @FXML // fx:id="CQ"
    private TextField CQ; // Value injected by FXMLLoader

    @FXML // fx:id="BQ"
    private TextField BQ; // Value injected by FXMLLoader

    @FXML // fx:id="ddays"
    private TextField ddays; // Value injected by 

    @FXML // fx:id="lblList2"
    private ListView<String> lblList2; // Value injected by FXMLLoader

    Connection con;
    
    @FXML
    void doGenerateBill(ActionEvent event)
    {
    	totalBill.setText(String.valueOf(fbill));
    }

    java.sql.Date swdobs;
    java.sql.Date swdobe;
    float totalc;
    float amount=0;
    float totalb;
    int days;
    int p = 0;
    
    @FXML
    void doGetDays(ActionEvent event) 
    {
    	try{
    		LocalDate ldobs = lbldos.getValue();
    		LocalDate ldobe = lbldoe.getValue();
    		 days = (int)ChronoUnit.DAYS.between(ldobs, ldobe)+1;
    		 ddays.setText(days+"");
    		 System.out.println(days);
    	}
    	
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
	float sumc=0;
	float sumb=0;
	float cbill;
	float bbill;
	float fbill;


    @FXML
    void doGetVariation(ActionEvent event) 
    {
    	// PreparedStatement pst1=con.prepareStatement("select sum(CowQty) as 'sumCowQty',sum(BefQty) as 'sumBefQty' from variation where  Date>=? and Date<=? and Cname=?");
    				String name2=lblList2.getSelectionModel().getSelectedItem();
    				
    				 try {
    						PreparedStatement pst=	con.prepareStatement("select sum(CowQty) as 'sumCowQty',sum(BefQty) as 'sumBefQty' from variation where Cname=?");
    						pst.setString(1, name2);
    						ResultSet table=pst.executeQuery();
    						while(table.next())
    						{
    							float cqty2= table.getFloat("sumCowQty");
    							float bqty2= table.getFloat("sumBefQty");
    							
    							float total = cqty2 + Float.parseFloat(Cqty.getText());
    							float total2 = bqty2 + Float.parseFloat(Bqty.getText());
    							
    							
    							CQ.setText(String.valueOf(total));  
    				    		BQ.setText(String.valueOf(total2));	 
    							
    							cbill = (Float.parseFloat(Cbill.getText())*Float.parseFloat(CQ.getText()));
    							bbill = (Float.parseFloat(Bbill.getText())*Float.parseFloat(BQ.getText()));
    							fbill = cbill+bbill;
    							System.out.println(cbill +"   "+bbill);
    								
    						}
    				     }
    						
    	                   catch (SQLException e) 
    					{
    						e.printStackTrace();
    					}
    	    	}		    
    	    void doFetch() throws Exception
    	    {
    	    
    	         PreparedStatement pst=	con.prepareStatement("select Cname from milktable");
    	    	 ResultSet rs=pst.executeQuery();
    	    	
    	    	while(rs.next())
    	     	{
    	     		String name =rs.getString("Cname");
    	     		lblList2.getItems().add(name);
    	     	}
    }
    	    

    	    @FXML
    	    void doSave(ActionEvent event)
    	    {
    	    	try 
    			{
    				String name = lblList2.getSelectionModel().getSelectedItem();
    		
    				
    			LocalDate ldos=	lbldos.getValue();
    			java.sql.Date swdob= java.sql.Date.valueOf(ldos);
    			
    			LocalDate ldos2=	lbldoe.getValue();
    			java.sql.Date swdob2= java.sql.Date.valueOf(ldos2);
    			
    			
    			PreparedStatement pst=	con.prepareStatement("insert into BillingLog values(?,?,?,?,?,?,?)");
    			pst.setString(1, name);
    			pst.setDate(2, swdob);
    			pst.setDate(3, swdob2);
    			pst.setString(4, ddays.getText());
    			pst.setString(5, BQ.getText());
    			pst.setString(6, CQ.getText());
    			pst.setString(7,  totalBill.getText());
    			
    			pst.executeUpdate();
    		//	lbl.setText("Saved..");	
    			} 
    	    																																																																																								
    	    	catch (SQLException e) 
    	    	{
    				e.printStackTrace();
    			} 
    	    }
    	    
    	    

    	    @FXML
    	    void onMouseClicked(MouseEvent event) 
    	    {
    	    	String name = lblList2.getSelectionModel().getSelectedItem();
            	
    	        if(event.getClickCount() == 2)
    	        	{ 
    	        		 try {
    	    				PreparedStatement pst=	con.prepareStatement("select CmQty,CmPrice,BmQty,BmPrice,Mobile  from milktable where Cname=?");
    	    				pst.setString(1, name);
    	    				ResultSet table=pst.executeQuery();
    	    				while(table.next())
    	    				{
    	    					float cqty= table.getFloat("CmQty");
    	    					float cprice= table.getFloat("CmPrice");
    	    					float bqty= table.getFloat("BmQty");
    	    					float bprice= table.getFloat("BmPrice");
    	    					String mob= table.getString("Mobile");
    	
    	    					float total = cqty*Float.parseFloat(ddays.getText());
    	    					float total2 = bqty*Float.parseFloat(ddays.getText());
    	    					
    	    					Cqty.setText(String.valueOf(total));
    	    		    		Bqty.setText(String.valueOf(total2)); 
    	    		    		
    	    		    		Cbill.setText(String.valueOf(cprice));
    	    		    		Bbill.setText(String.valueOf(bprice));
    	    		    		
    	    		    		mobile.setText(String.valueOf(mob));
    	    				}	
    	    			}
    	        		 catch (SQLException e)
    	        		 {
    	    				// TODO Auto-generated catch block
    	    				e.printStackTrace();
    	     		}
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
    	    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Form1/viewForm1.fxml")); 
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
    void initialize() throws Exception 
    {
    	con=(Connection) myDb3Connection.doConnect(); 
    	doFetch();
    }
}
