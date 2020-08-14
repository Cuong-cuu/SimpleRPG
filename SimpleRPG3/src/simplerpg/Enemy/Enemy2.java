package simplerpg.Enemy;

import javafx.animation.Animation.Status;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import simplerpg.Config;
import simplerpg.GameField;
import simplerpg.Launcher;
import simplerpg.SpriteAnimation;
import simplerpg.Adventurer.Player;
import simplerpg.Button.Options;
import simplerpg.Map.Map;
import simplerpg.sound.SoundPlayer;

public class Enemy2 extends Enemy
{
	private Config cf = Config.getInstance();
	private String ImgLink = cf.getLink();
	
	private ImageView walking = new ImageView();
	private ImageView attack = new ImageView();
	private ImageView death = new ImageView();
	private ImageView hurted = new ImageView();
	private ImageView spawn = new ImageView();
	
	private SpriteAnimation walkingani ;
	private SpriteAnimation attackani;
	private SpriteAnimation deathani;
	private SpriteAnimation hurtedani;
	private SpriteAnimation spawnani;
	
	
	private Circle circle = new Circle();
	private ImageView baseHP = new ImageView();
	private int curHP = 52;
	private ImageView[] HP = new ImageView[52];
	private SoundPlayer ehurtSound;
	
	private double directionX=0;
	private double directionY=0;
	
	public Enemy2()
	{
		this.height = 42;
		this.width = 18;
		this.bgheight=64;
		this.bgwidth= 64;
		
		this.MaxHP =50*Launcher.difficulty;
		this.hp = 50*Launcher.difficulty;
		this.Damage = 10*Launcher.difficulty;
		this.Defense = 5*Launcher.difficulty;
		this.RunningSpeed = 0.8*Launcher.difficulty;
		this.AttackSpeed = 0.5*Launcher.difficulty;
		this.Range = 24;
		
		///Shadow
		circle.setRadius(20);
		circle.setFill(Color.BLACK);
		circle.setScaleY(0.1);
		circle.setOpacity(0.5);
		
		///Box
		r= new Rectangle();
		r.setWidth(width);
		r.setHeight(height);
		r.setStroke(Color.LIGHTGREEN);
		r.setStrokeWidth(1);
		r.setFill(null);
		
		///Walking animation
		walking.setViewport(new Rectangle2D(0, 0, this.getBgwidth(), this.getBgheight()));
		walking.setImage(new Image(ImgLink+"walking"+"_"+"enemy"+"_"+"02"+".png"));
		walkingani = new SpriteAnimation(walking, Duration.millis(800), 4, 4, this.getBgwidth(), this.getBgheight(),this.getBgwidth(),this.getBgheight());		
	
		///Attack animation
		attack.setViewport(new Rectangle2D(0, 0, this.getBgwidth(), this.getBgheight()));
		attack.setImage(new Image(ImgLink+"attack"+"_"+"enemy"+"_"+"02"+".png"));
		attackani = new SpriteAnimation(attack, Duration.millis(1500*this.AttackSpeed), 4, 4, this.getBgwidth(), this.getBgheight(),this.getBgwidth(),this.getBgheight());
				
		///Death animation
		death.setViewport(new Rectangle2D(0, 0, this.getBgwidth(), this.getBgheight()));
		death.setImage(new Image(ImgLink+"death"+"_"+"enemy"+"_"+"02"+".png"));
		deathani = new SpriteAnimation(death, Duration.millis(1000), 7, 7, this.getBgwidth(), this.getBgheight(),this.getBgwidth(),this.getBgheight());
				
		///Hurted animation
		hurted.setViewport(new Rectangle2D(0, 0, this.getBgwidth(), this.getBgheight()));
		hurted.setImage(new Image(ImgLink+"hurted"+"_"+"player"+"_"+"02"+".png"));
		hurtedani = new SpriteAnimation(hurted, Duration.millis(200), 3, 3, this.getBgwidth(), this.getBgheight(),this.getBgwidth(),this.getBgheight());
				
		///Spawn
		spawn.setViewport(new Rectangle2D(0, 0, this.getBgwidth(), this.getBgheight()));
		spawn.setImage(new Image(ImgLink+"spawn"+"_"+"enemy"+"_"+"02"+".png"));
		spawnani = new SpriteAnimation(spawn,Duration.millis(1000),7, 7, this.getBgwidth(), this.getBgheight(),this.getBgwidth(),this.getBgheight());
		
		///Base HP
		baseHP.setImage(new Image(ImgLink+"base_hp"+".png"));
		for(int i=0;i<curHP;i++)
		{
			HP[i] = new ImageView();
			HP[i].setImage(new Image(ImgLink+"HP"+".png"));
		}
	}
	
	public void calDirection(Map map,Player player)
	{
		if(attackani.getStatus() == Status.RUNNING || deathani.getStatus() == Status.RUNNING || hurtedani.getStatus() == Status.RUNNING || curHP<=0 || spawnani.getStatus()==Status.RUNNING) return;
		
		
		directionX= X-player.getX();
		directionY= Y-player.getY();
		
		if(directionX*directionX+directionY*directionY <= Range*Range)
		{
			Attack(directionX,player);
		}
		else
		{
			if(directionX<=0 && directionY<=0)
			{
				int move = (int)(Math.random()*10) %2;
				if(move==1)
				{
					Move(map,1);
					walkingani.play();
					Move(map,3);
					walkingani.play();
				}
				else
				{
					Move(map,3);
					walkingani.play();
					Move(map,1);
					walkingani.play();
				}
			}
			else
			if(directionX<=0 && directionY>=0)
			{
				int move = (int)(Math.random()*10) %2;
				if(move==1)
				{
					Move(map,1);
					walkingani.play();
					Move(map,4);
					walkingani.play();
				}
				else
				{
					Move(map,4);
					walkingani.play();
					Move(map,1);
					walkingani.play();
				}
			}
			else
			if(directionX>=0 && directionY<=0)
			{
				int move = (int)(Math.random()*10) %2;
				if(move==1)
				{
					Move(map,2);
					walkingani.play();
					Move(map,3);
					walkingani.play();
				}
				else
				{
					Move(map,3);
					walkingani.play();
					Move(map,2);
					walkingani.play();
				}
			}
			else
			if(directionX>=0 && directionY>=0)
			{
				int move = (int)(Math.random()*10) %2;
				if(move==1)
				{
					Move(map,2);
					walkingani.play();
					Move(map,4);
					walkingani.play();
				}
				else
				{
					Move(map,4);
					walkingani.play();
					Move(map,2);
					walkingani.play();
				}
			}
		}
	}
	
	private void Move(Map map,int type)
	{	
		SetUpAnimation(
				walking,
				new Image(ImgLink+"walking"+"_"+"enemy"+"_"+"02"+".png"),
				walkingani,
				Duration.millis(800),
				4,
				4,
				walkingani.getOffsetX(),
				bgheight*((directionX<0) ? 1 : 2)
				);
		
		if(cf.root.getChildren().indexOf(attack)>=0) cf.root.getChildren().remove(attack);
		if(cf.root.getChildren().indexOf(hurted)>=0) cf.root.getChildren().remove(hurted);
		Rectangle R = new Rectangle();
		R.setWidth(width);
		R.setHeight(height);
		R.setX(X+Dx[type]*RunningSpeed-width/2);
		R.setY(Y+Dy[type]*RunningSpeed-height/2);
		if(checkStep(map,R)==true
				&&
		GameField.getInstance().checkImpact(this,R)==true)
		{
			X=X+Dx[type]*RunningSpeed;
			Y=Y+Dy[type]*RunningSpeed;
			walking.setX(getRenderX());
			walking.setY(getRenderY());
			
			baseHP.setX(X-width/2-(50-width)/2);
			baseHP.setY(Y-bgheight/2);
			
			circle.setTranslateX(X);
			circle.setTranslateY(Y+height/2+5.5);
			
			r.setX(X-width/2);
			r.setY(Y-height/2);
			double x = X-width/2-(50-width)/2;
			for(int i=0;i<curHP;i++)
			{
				HP[i].setX(x+i);
				HP[i].setY(Y-bgheight/2);
			}
		}
	}
	
	private void Attack(double directionX,Player player)
	{
		SetUpAnimation(
				attack,
				new Image(ImgLink+"attack"+"_"+"enemy"+"_"+"02"+".png"),
				attackani,
				Duration.millis(1500*AttackSpeed),
				4,
				4,
				attackani.getOffsetX(), 
				bgheight*((directionX<0) ? 1 : 2)
				);
		
		attack.setX(getRenderX());
		attack.setY(getRenderY());
	
		if(cf.root.getChildren().indexOf(walking)>=0) cf.root.getChildren().remove(walking);
		if(cf.root.getChildren().indexOf(hurted)>=0) cf.root.getChildren().remove(hurted);
		
		Rectangle r1 = new Rectangle();

		r1.setWidth(Range);
		r1.setHeight(height);
		r1.setStroke(Color.LIGHTGREEN);
		r1.setStrokeWidth(1);
		r1.setFill(null);
		
		if(directionX<0)
		{
			r1.setX(X);
			r1.setY(Y-height/2);
		}
		else
		{
			r1.setX(X-width);
			r1.setY(Y-height/2);
		}
		//if(cf.root.getChildren().indexOf(r1)<0) cf.root.getChildren().add(r1);
		attackani.setOnFinished(e->
		{
			if(cf.root.getChildren().indexOf(r1)>=0) cf.root.getChildren().remove(r1);
			if(r1.getBoundsInParent().intersects(player.getBox().getBoundsInParent())==true)
			{
				player.Hurted(getDamage());
			}
		});
		
		attackani.play();
	}

	public void Spawn(double X,double Y)
	{
		double directionX = Math.random()*10;
		
		SetUpAnimation(
				spawn,
				new Image(ImgLink+"spawn"+"_"+"enemy"+"_"+"02"+".png"),
				spawnani,
				Duration.millis(1000),
				7,
				7,
				spawnani.getOffsetX(),
				bgheight*((directionX<=5) ? 1 : 2)
				);
		this.X=X;
		this.Y=Y;
		spawn.setX(getRenderX());
		spawn.setY(getRenderY());
		
		baseHP.setX(X-width/2-(50-width)/2);
		baseHP.setY(Y-bgheight/2);
		
		circle.setTranslateX(X);
		circle.setTranslateY(Y+height/2+5.5);
		
		r.setX(X-width/2);
		r.setY(Y-height/2);
		
		double x = X-width/2-(50-width)/2;
		for(int i=0;i<curHP;i++)
		{
			HP[i].setX(x+i);
			HP[i].setY(Y-bgheight/2);
		}
		
		spawnani.setOnFinished(e->{if(cf.root.getChildren().indexOf(spawn)>=0) cf.root.getChildren().remove(spawn);});
		spawnani.play();
	}
	public void Death()
	{
		
		SetUpAnimation(
				death,
				new Image(ImgLink+"death"+"_"+"enemy"+"_"+"02"+".png"),
				deathani,
				Duration.millis(1000),
				7,
				7,
				deathani.getOffsetX(),
				bgheight*((directionX<0) ? 1 : 2)
				);
		
		/// Set Current HP =-1
		for(int i=curHP-1;i>=0;i--) if(cf.root.getChildren().indexOf(HP[i])>=0) cf.root.getChildren().remove(HP[i]);
		curHP=-1;
		
		/// Set(X,Y)
		death.setX(getRenderX());
		death.setY(getRenderY());
	
		/// Remove Walking + Attack
		if(cf.root.getChildren().indexOf(walking)>=0) cf.root.getChildren().remove(walking);
		if(cf.root.getChildren().indexOf(attack)>=0) cf.root.getChildren().remove(attack);
		if(cf.root.getChildren().indexOf(hurted)>=0) cf.root.getChildren().remove(hurted);
		
		/// Remove All
		deathani.setOnFinished(e->{erase();dead=true;});
		deathani.play();
	}
	public void Hurted(double Damage)
	{
		
		if(curHP<=0) return;
		walkingani.stop(); attackani.stop();
		double trueDamage = Damage-getDefense()*Damage/100.0 ;
		double nowhp = (hp-trueDamage>=0) ? hp - trueDamage : 0;
		int perHP =(int) Math.floor((hp/MaxHP) * 52);
		hp=nowhp;
		
		for(int i=curHP-1;i>=perHP;i--) if(cf.root.getChildren().indexOf(HP[i])>=0) cf.root.getChildren().remove(HP[i]);
		curHP=perHP;
		if(curHP<=0)  {hp=0;Death();return;}
		
		
		
		SetUpAnimation(
				hurted,
				new Image(ImgLink+"hurted"+"_"+"enemy"+"_"+"02"+".png"),
				hurtedani,
				Duration.millis(500),
				3,
				3,
				hurtedani.getOffsetX(),
				bgheight*((directionX<0) ? 1 : 2)
				);
		
		
		
		///Remove animation
		if(cf.root.getChildren().indexOf(walking)>=0) cf.root.getChildren().remove(walking);
		if(cf.root.getChildren().indexOf(attack)>=0) cf.root.getChildren().remove(attack);
		
		///Set up X,Y
		hurted.setX(getRenderX());
		hurted.setY(getRenderY());
		/*if (Options.VolSE > 0 && Options.VolSE< 8) {
                for (int j = 0; j <= 8 - Options.VolSE; j++) {
                    ehurtSound.soundDecrease();
                }
                ehurtSound.play();
                            }
                else if (Options.VolSE == 8) {
                ehurtSound.play();
                }

                else if (Options.VolSE == 0) {}*/
		hurtedani.play();
	}
	
	public void render(GraphicsContext gc) 
	{
		for(int i=0;i<curHP;i++)
			if(cf.root.getChildren().indexOf(HP[i])<0) cf.root.getChildren().add(HP[i]);
		
		if(spawnani.getStatus() == Status.RUNNING)
		{
			if(cf.root.getChildren().indexOf(spawn)<0) cf.root.getChildren().add(spawn);
			return;
		}
		if(curHP<0 && deathani.getStatus() != Status.RUNNING) return;
		if(curHP<0 && deathani.getStatus() == Status.RUNNING) {if(cf.root.getChildren().indexOf(death)<0) cf.root.getChildren().add(death);return;}
		
		if(cf.root.getChildren().indexOf(circle)<0) cf.root.getChildren().add(circle);
		//if(cf.root.getChildren().indexOf(r)<0) cf.root.getChildren().add(r);
		
		if(hurtedani.getStatus() == Status.RUNNING) if(cf.root.getChildren().indexOf(hurted)<0) cf.root.getChildren().add(hurted);
		if(attackani.getStatus() != Status.RUNNING && hurtedani.getStatus() != Status.RUNNING) if(cf.root.getChildren().indexOf(walking)<0) cf.root.getChildren().add(walking);
		
		if(attackani.getStatus() == Status.RUNNING && hurtedani.getStatus() != Status.RUNNING) if(cf.root.getChildren().indexOf(attack)<0) cf.root.getChildren().add(attack);
		
		if(cf.root.getChildren().indexOf(baseHP)<0) cf.root.getChildren().add(baseHP);
		
		
	}
	public void erase()
	{
		if(cf.root.getChildren().indexOf(r)>=0) cf.root.getChildren().remove(r);
		if(cf.root.getChildren().indexOf(circle)>=0) cf.root.getChildren().remove(circle);
		if(cf.root.getChildren().indexOf(spawn)>=0) cf.root.getChildren().remove(spawn);
		if(cf.root.getChildren().indexOf(walking)>=0) cf.root.getChildren().remove(walking);
		if(cf.root.getChildren().indexOf(attack)>=0) cf.root.getChildren().remove(attack);
		if(cf.root.getChildren().indexOf(death)>=0) cf.root.getChildren().remove(death);
		if(cf.root.getChildren().indexOf(hurted)>=0) cf.root.getChildren().remove(hurted);
		if(cf.root.getChildren().indexOf(baseHP)>=0) cf.root.getChildren().remove(baseHP);
		
		for(int i=0;i<52;i++)
			if(cf.root.getChildren().indexOf(HP[i])>=0) cf.root.getChildren().remove(HP[i]);
	}
	
}
