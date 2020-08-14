package simplerpg.Button;

import java.io.File;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import simplerpg.BaseObj;
import simplerpg.Config;
import simplerpg.Launcher;
import simplerpg.sound.SoundPlayer;

public class PlayGame extends BaseObj
{
	private Config cf = Config.getInstance();
	private ButtonSetting Play = new ButtonSetting("Play Game");
	private int WIDTH = cf.getWidth();
	private int HEIGHT = cf.getHeight();
	private String ImgLink = cf.getLink();
	private Image BG = new Image(ImgLink+"background.png");
	
	private Label title = new Label();
	
	private ButtonSetting Easy = new ButtonSetting("EASY",WIDTH/2-60,HEIGHT/5+100);
	private ButtonSetting Medium = new ButtonSetting("MEDIUM",WIDTH/2-62,HEIGHT/5+200);
	private ButtonSetting Hard = new ButtonSetting("HARD",WIDTH/2-60,HEIGHT/5+300);
	private ButtonSetting Back = new ButtonSetting("BACK",48,HEIGHT-48,"backbutton",64,64);
        
        private SoundPlayer clickSound;
        public String aString;
	
	///Operation
	public PlayGame()
	{
                
                clickSound = new SoundPlayer(new File("src/simplerpg/data/click.wav"));
		///Title
		title.setText("CHOOSE DIFFICULTY");
		title.setId("title");
		title.setLayoutX(WIDTH/2-190);
		title.setLayoutY(HEIGHT/5);
		
		///Easy Mode
		Easy.SetUp();
		
		///Medium Mode
		Medium.SetUp();
		
		///Hard Mode
		Hard.SetUp();
		
		///Back
		Back.getBut().getStyleClass().add("volumebutton");
		Back.FitImg();
		Back.SetUpWithImgWithoutName();
	}
	
	@Override
	public void render(GraphicsContext gc) 
	{
		gc.drawImage(BG,0,0);
		if(cf.root.getChildren().indexOf(title)<0) cf.root.getChildren().add(title);	
		if(cf.root.getChildren().indexOf(Easy.getBut())<0) cf.root.getChildren().add(Easy.getBut());
		if(cf.root.getChildren().indexOf(Medium.getBut())<0) cf.root.getChildren().add(Medium.getBut());
		if(cf.root.getChildren().indexOf(Hard.getBut())<0) cf.root.getChildren().add(Hard.getBut());
		if(cf.root.getChildren().indexOf(Back.getBut())<0) cf.root.getChildren().add(Back.getBut());
		
	}
	public void erase() 
	{
		if(cf.root.getChildren().indexOf(BG) >= 0) cf.root.getChildren().remove(BG);
		if(cf.root.getChildren().indexOf(title)>=0) cf.root.getChildren().remove(title);
		if(cf.root.getChildren().indexOf(Easy.getBut())>=0) cf.root.getChildren().remove(Easy.getBut());
		if(cf.root.getChildren().indexOf(Medium.getBut())>=0) cf.root.getChildren().remove(Medium.getBut());
		if(cf.root.getChildren().indexOf(Hard.getBut())>=0) cf.root.getChildren().remove(Hard.getBut());
		if(cf.root.getChildren().indexOf(Back.getBut ())>=0) cf.root.getChildren().remove(Back.getBut());
	}
	public void update() 
	{
            ///Easy Button
		Easy.getBut().setOnMouseClicked(event -> 
		{
			/// Click Sound
			clickSound.play();
			/// Clear
			erase();
			
			///Status
			Launcher.difficulty=cf.getEasy();
		});
		
		///Medium Button
		Medium.getBut().setOnMouseClicked(event -> 
		{
			/// Click Sound
			clickSound.play();
			/// Clear
			erase();
			
			///Status
			Launcher.difficulty=cf.getMedium();
		});
		
		///Hard Button
		Hard.getBut().setOnMouseClicked(event -> 
		{
			/// Click Sound
			clickSound.play();
			/// Clear
			erase();
			
			///Status
			Launcher.difficulty=cf.getHard();
		});
		
		///Back Button
		Back.getBut().setOnMouseClicked(event -> 
		{
			/// Click Sound
			clickSound.play();
			/// Clear
			erase();
			
			///Status
			Launcher.choose = cf.getPanel();
		});
		
		
	}


	

}
