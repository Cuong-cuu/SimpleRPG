package simplerpg;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import simplerpg.Adventurer.Player;

import simplerpg.Button.ButtonSetting;
import simplerpg.Button.Options;
import simplerpg.Button.Quit;
import simplerpg.sound.SoundPlayer;
public class GamePanel extends BaseObj
{
	private final String Name = "SimpleRPG";
	
	private Config cf = Config.getInstance() ;
	private int WIDTH = cf.getWidth();
	private int HEIGHT = cf.getHeight();
	
	private String ImgLink = cf.getLink();
	private Image BG = new Image(ImgLink+"background.png");
        
	
	private Label title = new Label();
	
	private ButtonSetting Start = new ButtonSetting("Play Game",WIDTH/2-60,HEIGHT/5+100);
	private ButtonSetting Options = new ButtonSetting("Options",WIDTH/2-53,HEIGHT/5+200);
	private ButtonSetting Quit = new ButtonSetting("Quit Game",WIDTH/2-60,HEIGHT/5+300);
        
        private SoundPlayer clickSound;
	
	
	///Operation
	public GamePanel()
	{
		clickSound = new SoundPlayer(new File("src/simplerpg/data/click.wav"));
		///Background
		
		/// Title 
		title.setText(Name);
		title.setId("title");
		title.setLayoutX(WIDTH/2-100);
		title.setLayoutY(HEIGHT/5);
		
		///Start Button
		Start.SetUp();
		
		///Options Button
		Options.SetUp();
		
		///Quit Buttion
		Quit.SetUp();
        }
	public void resetGame(){
                    ///Background
		
		/// Title 
		title.setText(Name);
		title.setId("title");
		title.setLayoutX(WIDTH/2-100);
		title.setLayoutY(HEIGHT/5);
		
		///Start Button
		Start.SetUp();
		
		///Options Button
		Options.SetUp();
		
		///Quit Buttion
		Quit.SetUp();
        }
        
        @Override
	public void render(GraphicsContext gc) 
	{
		gc.drawImage(BG, 0, 0);
		if(cf.root.getChildren().indexOf(title) < 0) cf.root.getChildren().add(title);
		if(cf.root.getChildren().indexOf(Start.getBut()) < 0) cf.root.getChildren().add(Start.getBut());
		if(cf.root.getChildren().indexOf(Options.getBut()) < 0) cf.root.getChildren().add(Options.getBut());
		if(cf.root.getChildren().indexOf(Quit.getBut()) < 0) cf.root.getChildren().add(Quit.getBut());
	}
	public void erase()
	{
		if(cf.root.getChildren().indexOf(BG) >= 0) cf.root.getChildren().remove(BG);
		if(cf.root.getChildren().indexOf(title) >= 0) cf.root.getChildren().remove(title);
		if(cf.root.getChildren().indexOf(Start.getBut()) >= 0) cf.root.getChildren().remove(Start.getBut());
		if(cf.root.getChildren().indexOf(Options.getBut()) >= 0) cf.root.getChildren().remove(Options.getBut());
		if(cf.root.getChildren().indexOf(Quit.getBut()) >= 0) cf.root.getChildren().remove(Quit.getBut());
	}
        
	public void update()
	{
		/// Start button
		Start.getBut().setOnMouseClicked(event ->
		{
			/// Click Sound
			clickSound.play();
			/// Clear
			erase();
			
			///Status
			Launcher.choose = cf.getPlaying();
			
		});
		
		///Options button
		Options.getBut().setOnMouseClicked(event ->
		{
			/// Click Sound
			clickSound.play();
			///Clear
			erase();
			
			///Status
			Launcher.choose = cf.getOptions();
		});
		
		///Quit button
                
		Quit.getBut().setOnMouseClicked(event ->
		{
			/// Click Sound
			clickSound.play();
			///Clear
			erase();
			
			///Status
			Launcher.choose = cf.getQuit();
		});
		
	}

	
	
}
