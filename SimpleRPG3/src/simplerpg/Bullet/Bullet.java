package simplerpg.Bullet;

import java.util.ArrayList;

import com.sun.javafx.geom.Vec2d;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import simplerpg.Config;
import simplerpg.Launcher;
import simplerpg.SpriteAnimation;
import simplerpg.Adventurer.Player;
import simplerpg.Enemy.Enemy;
import simplerpg.Enemy.Enemy1;
import simplerpg.Map.Map;

public class Bullet 
{
	private Config cf = Config.getInstance();
	private String ImgLink = cf.getLink();
	private double X,Y;
	private double finX,finY;
	private Vec2d vector;
	private double XSpeed,YSpeed;
	private double XYSpeed=10;
	private Image Form ;
	private ImageView form = new ImageView();
	private Rectangle r = new Rectangle();
	private SpriteAnimation ani;
	private boolean running = true;
	private double Damage = 2.0;
	
	public Bullet(double X,double Y,double finX,double finY,double Damage,double XYSpeed,int numPic,int picwidth,int picheight,int width,int height,String id)
	{
		this.X=X;
		this.Y=Y;
		this.finX=finX;
		this.finY=finY;
		this.Damage =  Damage;
		this.XYSpeed = XYSpeed;
		vector = new Vec2d(this.finX-this.X,this.finY-this.Y);
		
		Form = new Image(ImgLink+"bullet"+id+".png");
		form.setViewport(new Rectangle2D(0,0,picwidth,picheight));
		form.setImage(Form);
		ani = new SpriteAnimation(form,Duration.millis(100),numPic,numPic,picwidth,picheight,picwidth,picheight);
		
		
		YSpeed = Math.sqrt((XYSpeed*XYSpeed /((vector.x/vector.y)*(vector.x/vector.y)+1)));
		XSpeed = YSpeed*Math.abs(vector.x/vector.y);
		if(vector.x <0) XSpeed *=-1;
		if(vector.y<0) YSpeed *=-1;
		r.setFill(null);
		r.setStroke(Color.LIGHTSKYBLUE);
		r.setWidth(width);
		r.setHeight(height);
		
		r.setX(X);r.setY(Y);
	}
	public Bullet(double X,double Y,double finX,double finY,int numPic,int width,int height)
	{
		this.X=X;
		this.Y=Y;
		this.finX=finX;
		this.finY=finY;
		vector = new Vec2d(this.finX-this.X,this.finY-this.Y);
		
		Form = new Image(ImgLink+"shuriken.png");
		form.setViewport(new Rectangle2D(0,0,width,height));
		form.setImage(Form);
		ani = new SpriteAnimation(form,Duration.millis(100),numPic,numPic,width,height,width,height);
		
		
		YSpeed = Math.sqrt((XYSpeed*XYSpeed /((vector.x/vector.y)*(vector.x/vector.y)+1)));
		XSpeed = YSpeed*Math.abs(vector.x/vector.y);
		if(vector.x <0) XSpeed *=-1;
		if(vector.y<0) YSpeed *=-1;
		r.setFill(null);
		r.setStroke(Color.LIGHTSKYBLUE);
		r.setWidth(width);
		r.setHeight(width);
		
		r.setX(X);r.setY(Y);
	}
	public void render(GraphicsContext gc)
	{
		//if(cf.root.getChildren().indexOf(r)<0) cf.root.getChildren().add(r);
		if(cf.root.getChildren().indexOf(form)<0) cf.root.getChildren().add(form);
	}
	public void update(Map map,ArrayList<Enemy> enemyList)
	{
		if(this.running ==false)
		{
			erase();
			return;
		}
		X+=XSpeed;Y+=YSpeed;
		r.setX(X); r.setY(Y);
		form.setX(X);form.setY(Y);
		ani.play();
		if(map.isAvailable(r)==false)
		{
			this.running= false;
			return;
		}
		for(int i=0;i<enemyList.size();i++)
		{
			if(enemyList.get(i).getBox().getBoundsInParent().intersects(r.getBoundsInParent())==true)
			{
				this.running=false;
				Enemy enemy = enemyList.get(i);
				enemy.Hurted(this.Damage);
				return;
			}
		}
		
	}
	public void update(Map map, Player player)
	{
		if(this.running ==false)
		{
			erase();
			return;
		}
		X+=XSpeed;Y+=YSpeed;
		r.setX(X); r.setY(Y);
		form.setX(X);form.setY(Y);
		ani.play();
		if(map.isAvailable(r)==false)
		{
			this.running= false;
			return;
		}
		if(player.getBox().getBoundsInParent().intersects(r.getBoundsInParent())==true)
		{
			this.running=false;
			player.Hurted(Damage);
			return;
		}
	}
	public void erase()
	{
		//if(cf.root.getChildren().indexOf(r)>=0) cf.root.getChildren().remove(r);
		if(cf.root.getChildren().indexOf(form)>=0) cf.root.getChildren().remove(form);
	}
	public boolean isRunning()
	{
		return running;
	}
}
