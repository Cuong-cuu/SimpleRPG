package simplerpg;

import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;

public class Config 
{
	private static Config instance = null;
	
	private final String ImgLink = "file:src/simplerpg/data/";
	private final int width = 1024;
	private final int height = 720;
	
	public final Group root = new Group();
	public static final Camera camera = new PerspectiveCamera(true);
	public static int View = -700;
	///button menu
	private final int Panel = 0;
	private final int Playing = 1;
	private final int Options = 2;
	private final int Quit = 3;
	private final int isPlay = 1;
    private final int GameOver = 4;
    private final int GameClear = 5;
	
	///Difficulty
	private final double Easy = 1.0;
	private final double Medium = 1.2;
	private final double Hard = 1.5;
	
	///Animated
	private final int running = 12;
	
	///Operation
	public Config()
	{
		
	}
	public static Config getInstance()
	{
		if(instance == null) instance = new Config();
		return instance;
	}
	public int getWidth()
	{
		return this.width;
	}
	public int getHeight()
	{
		return this.height;
	}	
	public String getLink()
	{
		return this.ImgLink;
	}
	public int getPlaying()
	{
		return this.Playing;
	}
	public int getOptions()
	{
		return this.Options;
	}
	public int getQuit()
	{
		return this.Quit;
	}
	public int getPanel()
	{
		return this.Panel;
	}
	public double getEasy()
	{
		return this.Easy;
	}
	public double getMedium()
	{
		return this.Medium;
	}
	public double getHard()
	{
		return this.Hard;
	}
	public int getIsPlay()
	{
		return this.isPlay;
	}
	
	public int getAnimatedRunning()
	{
		return this.running;
	}

        public int getGameOver() {
                return this.GameOver;
        }

        public int getGameClear() {
            return this.GameClear;
        }
        
        
}
