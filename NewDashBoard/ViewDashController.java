/**
 * Sample Skeleton for 'ViewDash.fxml' Controller Class
 */

package NewDashBoard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class ViewDashController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
    @FXML // fx:id="board"
    private AnchorPane board; // Value injected by FXMLLoader
    
    URL url;
   	Media media;
   	MediaPlayer mediaplayer;
   	AudioClip audio;
    
    void playSong()
    {
    	url=getClass().getResource("(DJJOhAL.Com).mp3");
		media=new Media(url.toString());
		mediaplayer=new MediaPlayer(media);
		mediaplayer.play();
    }
    
    void playSound()
    {
    	url=getClass().getResource("cowmoo.mp3");
		audio=new AudioClip(url.toString());
		audio.play();
    }

    @FXML
    void BillCall(MouseEvent event) 
    {
    	playSound();
    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Billing/View3.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			//to hide the opened window
			 
			 
			Scene scene1=(Scene)board.getScene();
			   scene1.getWindow().hide();  
			 

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

    }

    @FXML
    void Face(MouseEvent event) 
    {
    	playSound();
    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("MilkMan/View.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			//to hide the opened window
			 
			 
		/*	Scene scene1=(Scene)btnComboApp.getScene();
			   scene1.getWindow().hide();  */
			 

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
    @FXML
    void CustomerList(MouseEvent event) 
    {
    	playSound();
    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Form1/viewForm1.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			//to hide the opened window
			 
			 
		/*	Scene scene1=(Scene)btnComboApp.getScene();
			   scene1.getWindow().hide();  */
			 

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void Variations(MouseEvent event) 
    {
    	playSound();
    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("RoutinEntry/View2.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			//to hide the opened window
			 
			 
		/*	Scene scene1=(Scene)btnComboApp.getScene();
			   scene1.getWindow().hide();  */
			 

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
    
    @FXML
    void VarList(MouseEvent event)
    {
    	playSound();
    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Form2/ViewForm2.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		/*	Scene scene1=(Scene)btnComboApp.getScene();
			   scene1.getWindow().hide();  */
			 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
    
    @FXML
    void BillList(MouseEvent event)
    {
    	playSound();
    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Form3/ViewForm3.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			//to hide the opened window
			 
			 
		/*	Scene scene1=(Scene)btnComboApp.getScene();
			   scene1.getWindow().hide();  */
			 

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
    


    @FXML
    void playSong(MouseEvent event) 
    {
    	playSong();
    }



    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() 
    {
   //  	playSong();
   // 	mediaplayer.setAutoPlay(true);
    }
}
