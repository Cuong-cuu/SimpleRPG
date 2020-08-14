package simplerpg;

import java.io.File;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import simplerpg.BaseObj;
import simplerpg.Button.ButtonSetting;
import simplerpg.Config;
import simplerpg.Launcher;
import simplerpg.Adventurer.PlayerState;
import simplerpg.sound.SoundPlayer;

public class GameOver extends BaseObj
{
	private Config cf = Config.getInstance();
	private String ImgLink = cf.getLink();
	private int WIDTH = cf.getWidth();
	private int HEIGHT = cf.getHeight();
	
	private Label title = new Label();

	
	private ImageView Panel = new ImageView();
	private ButtonSetting replay = new ButtonSetting("Replay",WIDTH/2-53,HEIGHT/5+200);
	private ButtonSetting quit = new ButtonSetting("Quit Game",WIDTH/2-60,HEIGHT/5+300);
	private SoundPlayer clickSound;
	public static int VolSE = 8;
	public static int VolM = 8;
	///Operation
	public GameOver()
	{
		///Background
		clickSound = new SoundPlayer(new File("src/simplerpg/data/click.wav"));
		///Title
		title.setText("You're Dead !!");
                title.setId("title");
                title.setLayoutX(WIDTH/2-120);
                title.setLayoutY(HEIGHT/5);

                Panel.setImage(new Image("file:src/simplerpg/data/optionpanel.png"));
                Panel.setX(WIDTH/3-72);
                Panel.setY(HEIGHT/5);
                Panel.setFitWidth(512);
                Panel.setFitHeight(466);
                
                replay.SetUp();
                quit.SetUp();
		
	}
	@Override
	public void render(GraphicsContext gc) 
	{
		if(cf.root.getChildren().indexOf(Panel)<0) cf.root.getChildren().add(Panel);
		if(cf.root.getChildren().indexOf(title)<0) cf.root.getChildren().add(title);
		
		if(cf.root.getChildren().indexOf(quit.getBut())<0) cf.root.getChildren().add(quit.getBut());
		if(cf.root.getChildren().indexOf(replay.getBut())<0) cf.root.getChildren().add(replay.getBut());
		
	
	}
	public void update() 
	{
		///Back Butoon
		replay.getBut().setOnMouseClicked(event ->
		{
			/// Click Sound
			clickSound.play();
			/// Clear
			GameField.getInstance().erase();
			erase();
			
			/// Status
			GameField.ResetGameField();
			PlayerState.ResetState();
			Launcher.choose = 0;
			Launcher.dead=0;
			Launcher.joined=0;
			Launcher.MapId=-1;
			Launcher.difficulty=0;
		});
		
		///SoundEffect Volume 
			///Increase
			quit.getBut().setOnMouseClicked(event ->
			{
				/// Click Sound
                                clickSound.play();
				Platform.exit();
			});
			
	}
	public void erase() 
	{
		//Panel + Title
		if(cf.root.getChildren().indexOf(Panel)>=0) cf.root.getChildren().remove(Panel);
		if(cf.root.getChildren().indexOf(title)>=0) cf.root.getChildren().remove(title);
		
		if(cf.root.getChildren().indexOf(replay.getBut())>=0) cf.root.getChildren().remove(replay.getBut());
        if(cf.root.getChildren().indexOf(quit.getBut())>=0) cf.root.getChildren().remove(quit.getBut());
	}
	
}
