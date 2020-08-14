package simplerpg.Adventurer;

import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import simplerpg.Config;

public class UI 
{
	private Config cf = Config.getInstance();
	private ImageView UIImage = new ImageView();
	public UI()
	{
		UIImage.setImage(new Image(cf.getLink()+"UI.png"));
	}
	public void update(double X,double Y)
	{
		UIImage.setX(X);
		UIImage.setY(Y);
	}
	public void render(GraphicsContext gc)
	{
		if(cf.root.getChildren().indexOf(UIImage)<0) cf.root.getChildren().add(UIImage);
	}
	public void erase()
	{
		if(cf.root.getChildren().indexOf(UIImage)>=0) cf.root.getChildren().remove(UIImage);
	}
}
