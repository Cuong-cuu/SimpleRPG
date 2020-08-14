package simplerpg;

import java.awt.Panel;
import java.io.File;
import java.io.FileNotFoundException;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Camera;
import javafx.scene.ImageCursor;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import simplerpg.Button.Options;
import simplerpg.Button.PlayGame;
import simplerpg.Adventurer.Board;
import simplerpg.Adventurer.Player;
import simplerpg.Adventurer.PlayerState;
import simplerpg.Button.Options;
import simplerpg.sound.SoundPlayer;

public class Launcher extends Application
{
	private GraphicsContext gc;
	private final String Name = "SimpleRPG";
	private Config cf = Config.getInstance();
	public static Scene scene;
	
	private int WIDTH = cf.getWidth();
	private int HEIGHT = cf.getHeight();
	
	public static int choose;
	public static double difficulty=0;
	public static int joined =0;
	public static int MapId = 0;
	public static int PlayerId = 1;
	public static int dead = 0;
	public static SoundPlayer backgroundSound;
	///Operation
	@Override
	public void start(Stage stage) throws FileNotFoundException 
	{
		stage.setTitle(Name);
		stage.getIcons().add(new Image(cf.getLink() + "icon.png"));
		
		///Create Canvas
		Canvas canvas = new Canvas(WIDTH,HEIGHT);
		gc = canvas.getGraphicsContext2D();
		cf.root.getChildren().addAll(canvas); 
		
		///Create Scene + Camera
		scene = new Scene(cf.root,WIDTH,HEIGHT);
		scene.setFill(Color.BLACK);
		//cf.camera.
		cf.camera.translateXProperty().set(WIDTH/2);
		cf.camera.translateYProperty().set(HEIGHT/2);
		cf.camera.translateZProperty().set(-1343);
		cf.camera.setNearClip(1);
		cf.camera.setFarClip(1350);
		//cf.camera.
		scene.setCamera(cf.camera);
		//cf.camera.setFar
		
		///Mouse
		Image mouse = new Image(cf.getLink()+"mouse.png");
		scene.setCursor(new ImageCursor(mouse));
		
		///Css
		scene.getStylesheets().add(cf.getLink()+"style.css");
		
		///Push Scene into Stage
		stage.setScene(scene);
		stage.show();
		
		///Sound
		backgroundSound = new SoundPlayer(new File("src/simplerpg/data/backgroundsound.wav"));
		
		///Init
		GamePanel Panel = new GamePanel();
		PlayGame Play = new PlayGame();
		Options Option = new Options();
		Board Board = new Board();
		//AnimationTimer <-> GameLoop
		backgroundSound.playMusic();
		AnimationTimer timer = new AnimationTimer()
		{
			@Override          
			public void handle(long now) 
			{       
                                
				gc.clearRect(0, 0, WIDTH, HEIGHT);
                            ///Panel
                            switch (choose) {
                            /// PlayGame
                                case 0:
                                    Panel.render(gc);
                                    Panel.update();
                                    break;
                            /// Options
                                case 1:
                                    if(joined ==1) ///Playing
                                        
                                    {
                                        if(joined ==1)
                                        {
                                            GameField.getInstance().update();
                                            GameField.getInstance().render(gc);
                                            backgroundSound.stop();
                                        }
                                        
                                    }
                                    else
                                    {
                                        if(difficulty==0) /// Choose Difficulty
                                        {
                                            Play.render(gc);
                                            Play.update();
                                        }
                                        else /// Edit Sate
                                        {
                                            Board.render(gc);
                                            Board.update();
                                        }
                                    }
                                    break;
                            /// QuitGame
                                case 2:
                                    Option.render(gc);
                                    Option.update();
                                    break;
                                case 3:
                                    Platform.exit();
                                    break;
                                default:
                                    break;
                            }
			}
			
		};
		timer.start();   
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}

}
