package simplerpg.Button;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import simplerpg.Config;

public class ButtonSetting 
{
	private Button but = new Button();
	private String ButtonName ;
	private int X;
	private int Y;
	private int FitWidth;
	private int FitHeight;
	private Config cf = Config.getInstance();
	private String ImgLink = cf.getLink();
	
	private ImageView img ;
	
	///Operation
	public ButtonSetting(String Name)
	{
		this.ButtonName=Name;
	}
	public ButtonSetting(String Name,int X,int Y)
	{
		this.ButtonName=Name;
		this.X=X;
		this.Y=Y;
	}
	public ButtonSetting(String Name,int X,int Y,String ImgName)
	{
		this.ButtonName=Name;
		this.X=X;
		this.Y=Y;
		this.img=new ImageView(ImgLink+ImgName+".png");
	}
	public ButtonSetting(String Name,int X,int Y,String ImgName,int FitW,int FitH)
	{
		this.ButtonName=Name;
		this.X=X;
		this.Y=Y;
		this.img=new ImageView(ImgLink+ImgName+".png");
		this.FitWidth=FitW;
		this.FitHeight=FitH;
	}
	public void FitImg()
	{
		img.setFitWidth(this.FitWidth);
		img.setFitHeight(this.FitHeight);
	}
	public void SetUpWithImgWithoutName()
	{
		but.setLayoutX(X);
		but.setLayoutY(Y);
		but.setGraphic(img);
	}
	public void SetUpWithImg()
	{
		but.setText(ButtonName);
		but.setLayoutX(X);
		but.setLayoutY(Y);
		but.setGraphic(img);
	}
	public void SetUp()
	{
		but.setText(ButtonName);
		but.setLayoutX(X);
		but.setLayoutY(Y);
	}
	public Button getBut()
	{
		return this.but;
	}
	public int getFitWidth()
	{
		return this.FitWidth;
	}
	public int getFitHeight()
	{
		return this.FitHeight;
	}
	public int getX()
	{
		return this.X;
	}
	public int getY()
	{
		return this.Y;
	}
	
}
