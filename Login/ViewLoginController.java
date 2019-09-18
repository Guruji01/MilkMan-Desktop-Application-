/**
 * Sample Skeleton for 'ViewLogin.fxml' Controller Class
 */

package Login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class ViewLoginController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="password"
    private PasswordField password; // Value injected by FXMLLoader
    
    URL url;
   	Media media;
   	MediaPlayer mediaplayer;
   	AudioClip audio;
   	
       void playSong()
       {
       	url=getClass().getResource("bg.mp3");
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

       String xyz = "Londen";

	private Node btnComboApp;
       
    @FXML
    void Mooo(MouseEvent event) 
    {
         if(password.getText().equals(xyz))
         {
        	 playSound();
         	try{
         		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("NewDashBoard/ViewDash.fxml")); 
     			Scene scene = new Scene(root);
     			Stage stage=new Stage();
     			stage.setScene(scene);
     			stage.show();
     			//to hide the opened window
     			 
     			 
     			btnComboApp = null;
				Scene scene1=(Scene)btnComboApp.getScene();
     			   scene1.getWindow().hide();  
     			 

     		}
     		catch(Exception e)
     		{
     			e.printStackTrace();
     		}
         }
         else
         {
        	 System.out.println("Enter Right Password");
         }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() 
    {


    }
}
