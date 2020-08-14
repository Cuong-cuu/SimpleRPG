package simplerpg.Adventurer;


import java.io.File;
import java.sql.Time;
import java.util.ArrayList;

import javafx.animation.Animation.Status;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import simplerpg.BaseObj;
import simplerpg.Button.Options;
import simplerpg.Config;
import simplerpg.GameField;
import simplerpg.Launcher;
import simplerpg.Enemy.Enemy;
import simplerpg.Enemy.Enemy1;
import simplerpg.GamePanel;
import simplerpg.SpriteAnimation;
import simplerpg.Map.Map;
import simplerpg.sound.SoundPlayer;

public class Player extends BaseObj
{
	private Config cf = Config.getInstance();
	
	private String ImgLink = cf.getLink();
	
	private Rectangle r = new Rectangle();
	private ImageView walking = new ImageView();
	private ImageView attack1= new ImageView();
	private ImageView death = new ImageView();
	private ImageView hurted = new ImageView();
	private ImageView attack2 = new ImageView();
	private ImageView attack3 = new ImageView();
	private ImageView shadow = new ImageView();
	private SpriteAnimation walkingani;
	private SpriteAnimation attackani1;
	private SpriteAnimation deathani;
	private SpriteAnimation hurtedani;
	private SpriteAnimation attackani2;
	private SpriteAnimation attackani3;

	private Circle circle = new Circle();
	public static boolean isDead=false;
    
	private double velX=0,velY=0;
	PlayerState State = PlayerState.getState();
	
	private UI Ui;
	private int curHP = 52;
	private ImageView[] HP = new ImageView[100];
	
	private SoundPlayer SESound;
	private SoundPlayer deathSound;
	private SoundPlayer stepSound;
        
	
	public Player()
	{
		stepSound = new SoundPlayer(new File("src/simplerpg/data/step.wav"));
		SESound = new SoundPlayer(new File("src/simplerpg/data/hurt.wav"));
		deathSound = new SoundPlayer(new File("src/simplerpg/data/death.wav"));
		///Walking animation
		walking.setViewport(new Rectangle2D(0, 0, State.getBGWIDTH(), State.getBGHEIGHT()));
		walking.setImage(new Image(ImgLink+"walking"+"_"+"player"+"_"+NTS(Launcher.PlayerId)+".png"));
		walkingani =  new SpriteAnimation(walking, Duration.millis(800), 9, 9, State.getBGWIDTH(), State.getBGHEIGHT(),State.getBGWIDTH(),State.getBGHEIGHT());
		
		///Attack animation
		attack1.setViewport(new Rectangle2D(0, 0, State.getBGWIDTH(), State.getBGHEIGHT()));
		attack1.setImage(new Image(ImgLink+"slash"+"_"+"player"+"_"+NTS(Launcher.PlayerId)+".png"));
		attackani1 = new SpriteAnimation(attack1, Duration.millis(800), 6, 6, State.getBGWIDTH(), State.getBGHEIGHT(),State.getBGWIDTH(),State.getBGHEIGHT());
		
		///Attack animation
		attack2.setViewport(new Rectangle2D(0, 0, State.getBGWIDTH(), State.getBGHEIGHT()));
		attack2.setImage(new Image(ImgLink+"thrust"+"_"+"player"+"_"+NTS(Launcher.PlayerId)+".png"));
		attackani2 = new SpriteAnimation(attack2, Duration.millis(800), 6, 6, State.getBGWIDTH(), State.getBGHEIGHT(),State.getBGWIDTH(),State.getBGHEIGHT());
			
		///Attack animation
		attack3.setViewport(new Rectangle2D(0, 0, State.getBGWIDTH(), State.getBGHEIGHT()));
		attack3.setImage(new Image(ImgLink+"bow"+"_"+"player"+"_"+NTS(Launcher.PlayerId)+".png"));
		attackani3 = new SpriteAnimation(attack3, Duration.millis(800), 11, 11, State.getBGWIDTH(), State.getBGHEIGHT(),State.getBGWIDTH(),State.getBGHEIGHT());
						
		///Death animation
		death.setViewport(new Rectangle2D(0, 0, State.getBGWIDTH(), State.getBGHEIGHT()));
		death.setImage(new Image(ImgLink+"death"+"_"+"player"+"_"+NTS(Launcher.PlayerId)+".png"));
		deathani = new SpriteAnimation(death, Duration.millis(800), 6, 6, State.getBGWIDTH(), State.getBGHEIGHT(),State.getBGWIDTH(),State.getBGHEIGHT());
		
		///Hurted animation
		hurted.setViewport(new Rectangle2D(0, 0, State.getBGWIDTH(), State.getBGHEIGHT()));
		hurted.setImage(new Image(ImgLink+"hurted"+"_"+"player"+"_"+NTS(Launcher.PlayerId)+".png"));
		hurtedani = new SpriteAnimation(hurted, Duration.millis(200), 2, 2, State.getBGWIDTH(), State.getBGHEIGHT(),State.getBGWIDTH(),State.getBGHEIGHT());
		
		///Shadow
		circle.setRadius(20);
		circle.setFill(Color.BLACK);
		circle.setScaleY(0.1);
		circle.setOpacity(0.5);
		
		///Box
		r.setWidth(State.getWIDTH());
		r.setHeight(State.getHEIGHT());
		r.setStroke(Color.LIGHTGREEN);
		r.setStrokeWidth(1);
		r.setFill(null);
		
		///HP
		Ui = new UI();
		for(int i=0;i<99;i++)
		{
			HP[i] = new ImageView();
			HP[i].setImage(new Image(ImgLink+"HP"+".png"));
		}
		//curHP=52;
	}
        
        
	@Override
	public void render(GraphicsContext gc) 
	{
		Ui.render(gc);
		
		for(int i=0;i<curHP;i++){
			if(cf.root.getChildren().indexOf(HP[i])<0) {
                            cf.root.getChildren().add(HP[i]);
                        }
                }
		
		if(curHP<0 && deathani.getStatus() != Status.RUNNING) return;
		if(curHP<0 && deathani.getStatus() == Status.RUNNING) {
                    if(cf.root.getChildren().indexOf(death)<0) {
                        cf.root.getChildren().add(death);
                        
                    }
                    return;
                }
		if(cf.root.getChildren().indexOf(circle)<0) cf.root.getChildren().add(circle);
		
		///if(cf.root.getChildren().indexOf(r)<0) cf.root.getChildren().add(r);
		
        if(hurtedani.getStatus() == Status.RUNNING) {
                    if(cf.root.getChildren().indexOf(hurted)<0) {
                        cf.root.getChildren().add(hurted);
                    }
                }
                
		if(attackani1.getStatus() != Status.RUNNING && hurtedani.getStatus() != Status.RUNNING && attackani2.getStatus() != Status.RUNNING && attackani3.getStatus() != Status.RUNNING) {
                    if(cf.root.getChildren().indexOf(walking)<0) {
                        cf.root.getChildren().add(walking);
                        SESound = new SoundPlayer(new File("src/simplerpg/data/step.wav"));
                        if (Options.VolSE > 0 && Options.VolSE< 8) {
                                for (int j = 0; j < 8 - Options.VolSE; j++) {
                                    SESound.soundDecrease();
                            }
                            SESound.play();
                            }
                            else if (Options.VolSE == 8) {
                            SESound.play();
                            }
                            else if (Options.VolSE == 0) {}
                    }
                }
		
		if(attackani1.getStatus() == Status.RUNNING && hurtedani.getStatus() != Status.RUNNING && attackani2.getStatus() != Status.RUNNING && attackani3.getStatus() != Status.RUNNING) {
                    if(cf.root.getChildren().indexOf(attack1)<0) {
                        cf.root.getChildren().add(attack1);
                        SESound = new SoundPlayer(new File("src/simplerpg/data/sword.wav"));
                        if (Options.VolSE > 0 && Options.VolSE< 8) {
                            for (int j = 0; j <= 8 - Options.VolSE; j++) {
                                 SESound.soundDecrease();
                            }
                            SESound.play();
                            }
                            else if (Options.VolSE == 8) {
                            SESound.play();
                            }

                            else if (Options.VolSE == 0) {}
                    }
                }
		
		if(attackani1.getStatus() != Status.RUNNING && hurtedani.getStatus() != Status.RUNNING && attackani2.getStatus() == Status.RUNNING && attackani3.getStatus() != Status.RUNNING) {
                    if(cf.root.getChildren().indexOf(attack2)<0) {
                        cf.root.getChildren().add(attack2);
                        SESound = new SoundPlayer(new File("src/simplerpg/data/spear.wav"));
                        if (Options.VolSE > 0 && Options.VolSE< 8) {
                                for (int j = 0; j <= 8 - Options.VolSE; j++) {
                                    SESound.soundDecrease();
                            }
                            SESound.play();
                            }
                            else if (Options.VolSE == 8) {
                            SESound.play();
                            }

                            else if (Options.VolSE == 0) {}
                    }
                }
	
		if(attackani1.getStatus() != Status.RUNNING && hurtedani.getStatus() != Status.RUNNING && attackani2.getStatus() != Status.RUNNING && attackani3.getStatus() == Status.RUNNING) {
                    if(cf.root.getChildren().indexOf(attack3)<0) {
                        cf.root.getChildren().add(attack3);
                        SESound = new SoundPlayer(new File("src/simplerpg/data/arrow.wav"));
                        if (Options.VolSE > 0 && Options.VolSE< 8) {
                            for (int j = 0; j <= 8 - Options.VolSE; j++) {
                                    SESound.soundDecrease();
                            }
                            SESound.play();
                            }
                            else if (Options.VolSE == 8) {
                            SESound.play();
                            }

                            else if (Options.VolSE == 0) {}
                    }
                }
	}
	private void SetUpAnimation(ImageView IV,Image Img,SpriteAnimation Ani,Duration duration,int Count,int Columns,int offsetX,int offsetY)
	{
		IV.setImage(Img);
		Ani.setDuration(duration);
		Ani.setCount(Count);
		Ani.setColumns(Columns);
		Ani.setOffsetX(offsetX);
		Ani.setOffsetY(offsetY);
	}
	private int curDirection = 1;
	public void	setVelY(Map map,double velY,int type)
	{
		curDirection = type;
		if(attackani1.getStatus() != Status.STOPPED || curHP<0 || hurtedani.getStatus() != Status.STOPPED || attackani2.getStatus()!=Status.STOPPED || attackani3.getStatus()!=Status.STOPPED) return;
		
		double step = State.getRunningSpeed();
		
		Rectangle R = new Rectangle();
		R.setWidth(State.getWIDTH());
		R.setHeight(State.getHEIGHT());
		R.setX(State.getX()-State.getWIDTH()/2);
		R.setY(State.getY()-State.getHEIGHT()/2+velY*step);
		if(checkStep(map,R)==false) return;
		
		this.velY=velY;
	}
	public void	setVelX(Map map,double velX,int type)
	{
		curDirection = type;
		if(attackani1.getStatus() != Status.STOPPED || curHP<0 || hurtedani.getStatus() != Status.STOPPED || attackani2.getStatus()!=Status.STOPPED || attackani3.getStatus()!=Status.STOPPED) return;
		
		double step = State.getRunningSpeed();
		
		Rectangle R = new Rectangle();
		R.setWidth(State.getWIDTH());
		R.setHeight(State.getHEIGHT());
		R.setX(State.getX()-State.getWIDTH()/2+velX*step);
		R.setY(State.getY()-State.getHEIGHT()/2);
		if(checkStep(map,R)==false) return;
		
		this.velX=velX;
	}
	public void UpdatePosition(Map map)
	{
		double step = State.getRunningSpeed();
		Ui.update(State.getX()-(double)512.0*cf.camera.getTranslateZ()/(double)(-1343.0), State.getY()-(double)360.0*cf.camera.getTranslateZ()/(double)(-1343.0));
		double X = State.getX()-(double)512.0*cf.camera.getTranslateZ()/(double)(-1343.0);
		curHP =(int)Math.floor((State.getHP()/State.getMaxHP()) * 52);
		//System.out.println(State.get);
		for(int i=0;i<curHP;i++)
		{
			HP[i].setX(X+i+37);
			HP[i].setY(State.getY()-(double)360.0*cf.camera.getTranslateZ()/(double)(-1343.0)+4);
		}
		if(curHP<=0) {Death();return;}
		if(attackani1.getStatus() != Status.STOPPED || curHP<0 || hurtedani.getStatus() != Status.STOPPED || attackani2.getStatus()!=Status.STOPPED || attackani3.getStatus()!=Status.STOPPED)
		{
			velX=velY=0;
			return;
		}
		
		Rectangle R = new Rectangle();
		R.setWidth(State.getWIDTH());
		R.setHeight(State.getHEIGHT());
		R.setX(State.getX()+velX*step-State.getWIDTH()/2);
		R.setY(State.getY()+velY*step-State.getHEIGHT()/2);
		if(checkStep(map,R)==false)
		{
			Move(map,curDirection);
			return;
		}
		if(velX==0 && velY==0) return;
		State.setX(State.getX()+step*velX);
		State.setY(State.getY()+step*velY);
		
		walking.setX(State.getRenderX());
		walking.setY(State.getRenderY());
		
		Ui.update(State.getX()-(double)512.0*cf.camera.getTranslateZ()/(double)(-1343.0), State.getY()-(double)360.0*cf.camera.getTranslateZ()/(double)(-1343.0));
		
		circle.setTranslateX(State.getX());
		circle.setTranslateY(State.getY()+State.getHEIGHT()/2+5.5);
		
		r.setX(State.getX()-State.getWIDTH()/2);
		r.setY(State.getY()-State.getHEIGHT()/2);
		
		X = State.getX()-(double)512.0*cf.camera.getTranslateZ()/(double)(-1343.0);
		for(int i=0;i<curHP;i++)
		{
			HP[i].setX(X+i+37);
			HP[i].setY(State.getY()-(double)360.0*cf.camera.getTranslateZ()/(double)(-1343.0)+4);
		}
		Move(map,curDirection);
	}
	public void Move(Map map,int type)
	{
		curDirection =type;
		if(attackani1.getStatus() != Status.STOPPED || curHP<0 || hurtedani.getStatus() != Status.STOPPED || attackani2.getStatus()!=Status.STOPPED || attackani3.getStatus()!=Status.STOPPED) return;
		
		if(cf.root.getChildren().indexOf(attack1)>=0) cf.root.getChildren().remove(attack1);
		if(cf.root.getChildren().indexOf(attack2)>=0) cf.root.getChildren().remove(attack2);
		if(cf.root.getChildren().indexOf(hurted)>=0) cf.root.getChildren().remove(hurted);
		if(cf.root.getChildren().indexOf(attack3)>=0) cf.root.getChildren().remove(attack3);
		
		
		SetUpAnimation(
				walking,
				new Image(ImgLink+"walking"+"_"+"player"+"_"+NTS(Launcher.PlayerId)+".png"),
				walkingani,
				Duration.millis(500),
				9,
				9,
				walkingani.getOffsetX(),
				State.getBGHEIGHT()*type
				);
		
		if(stepSound.isRunning()==false)
        {
            if (Options.VolSE > 0 && Options.VolSE< 8) {
                        for (int j = 0; j <= 8 - Options.VolSE; j++) {
                            stepSound.soundDecrease();
                    }
                    stepSound.play();
                    }
                    else if (Options.VolSE == 8) {
                    stepSound.play();
                    }
                    else if (Options.VolSE == 0) {}
        }     
		walkingani.play();
	}
	public void Attack01(int direction,ArrayList<Enemy> enemyList)
	{
		if(curHP<0 || hurtedani.getStatus()!=Status.STOPPED || attackani2.getStatus()!=Status.STOPPED || attackani3.getStatus()!=Status.STOPPED) return;
		
		SetUpAnimation(
				attack1,
				new Image(ImgLink+"slash"+"_"+"player"+"_"+NTS(Launcher.PlayerId)+".png"),
				attackani1,
				Duration.millis(1000*State.getAttackSpeed()),
				6,
				6,
				attackani1.getOffsetX(),
				State.getBGHEIGHT()*direction
				);
		
		/// Set (X,Y)
		attack1.setX(State.getRenderX());
		attack1.setY(State.getRenderY());
		
		/// Remove Walking
		if(cf.root.getChildren().indexOf(walking)>=0) cf.root.getChildren().remove(walking);
		if(cf.root.getChildren().indexOf(hurted)>=0) cf.root.getChildren().remove(hurted);
		if(cf.root.getChildren().indexOf(attack3)>=0) cf.root.getChildren().remove(attack3);
		if(cf.root.getChildren().indexOf(attack2)>=0) cf.root.getChildren().remove(attack2);
		
		Rectangle r1 = new Rectangle();
		r1.setStroke(Color.LIGHTGREEN);
		r1.setStrokeWidth(1);
		r1.setFill(null);
		if(curDirection ==4 || curDirection ==2 )
		{

			r1.setWidth(State.getRange());
			r1.setHeight(State.getWidthRange());
			if(curDirection==4)
			{
				r1.setX(State.getX());
				r1.setY(State.getY());
			}
			else
			{
				r1.setX(State.getX()-r1.getWidth());
				r1.setY(State.getY());
			}
		}
		else
		{
			r1.setWidth(State.getWidthRange());
			r1.setHeight(State.getRange());
			if(curDirection == 1)
			{
				r1.setX(State.getX());
				r1.setY(State.getY()-r1.getHeight()-r1.getHeight()/2);
			}
			else
			{
				r1.setX(State.getX());
				r1.setY(State.getY()+r1.getHeight()/2);
			}
			
		}
		
		//if(cf.root.getChildren().indexOf(r1)<0) cf.root.getChildren().add(r1);
		
		attackani1.setOnFinished(e->
		{
			if(cf.root.getChildren().indexOf(r1)>=0) cf.root.getChildren().remove(r1);
			if(cf.root.getChildren().indexOf(attack1)>=0) cf.root.getChildren().remove(attack1);
			for(int i=0;i<enemyList.size();i++)
			{
				//if(enemyList.get(i).getClass()==Enemy1.class)
				//{
					Enemy enemy = enemyList.get(i);
					if(r1.getBoundsInParent().intersects(enemy.getBox().getBoundsInParent())==true)
					{
						enemy.Hurted(State.DealDamage());
						if(enemy.getHp()<=0)
						{
	                        this.State.setPoint(this.State.getPoint()+1);
	                    }
					}
				//}
			}
		});
		attackani1.play();
	}
	public void Attack02(int direction,ArrayList<Enemy> enemyList)
	{
		if(curHP<0 || hurtedani.getStatus()!=Status.STOPPED || attackani1.getStatus()!=Status.STOPPED || attackani3.getStatus()!=Status.STOPPED) return;
		
		SetUpAnimation(
				attack2,
				new Image(ImgLink+"thrust"+"_"+"player"+"_"+NTS(Launcher.PlayerId)+".png"),
				attackani2,
				Duration.millis(1500*State.getAttackSpeed()),
				6,
				6,
				attackani2.getOffsetX(),
				State.getBGHEIGHT()*direction
				);
		
		/// Set (X,Y)
		attack2.setX(State.getRenderX());
		attack2.setY(State.getRenderY());
		
		/// Remove Walking
		if(cf.root.getChildren().indexOf(walking)>=0) cf.root.getChildren().remove(walking);
		if(cf.root.getChildren().indexOf(hurted)>=0) cf.root.getChildren().remove(hurted);
		if(cf.root.getChildren().indexOf(attack1)>=0) cf.root.getChildren().remove(attack1);
		if(cf.root.getChildren().indexOf(attack3)>=0) cf.root.getChildren().remove(attack3);
		
		Rectangle r1 = new Rectangle();
		r1.setStroke(Color.LIGHTGREEN);
		r1.setStrokeWidth(1);
		r1.setFill(null);
		if(curDirection ==4 || curDirection ==2 )
		{

			r1.setWidth(State.getRange()+State.getRange()/2);
			r1.setHeight(State.getWidthRange());
			if(curDirection==4)
			{
				r1.setX(State.getX());
				r1.setY(State.getY());
			}
			else
			{
				r1.setX(State.getX()-r1.getWidth());
				r1.setY(State.getY());
			}
		}
		else
		{
			r1.setWidth(State.getWidthRange());
			r1.setHeight(State.getRange()+State.getRange()/2);
			if(curDirection == 1)
			{
				r1.setX(State.getX());
				r1.setY(State.getY()-r1.getHeight()-r1.getHeight()/2);
			}
			else
			{
				r1.setX(State.getX());
				r1.setY(State.getY()+r1.getHeight()/2);
			}
			
		}
		//if(cf.root.getChildren().indexOf(r1)<0) cf.root.getChildren().add(r1);
		attackani2.setOnFinished(e->
		{
			if(cf.root.getChildren().indexOf(r1)>=0) cf.root.getChildren().remove(r1);
			if(cf.root.getChildren().indexOf(attack2)>=0) cf.root.getChildren().remove(attack2);
			for(int i=0;i<enemyList.size();i++)
			{
				//if(enemyList.get(i).getClass()==Enemy1.class)
				//{
					Enemy enemy = enemyList.get(i);
					if(r1.getBoundsInParent().intersects(enemy.getBox().getBoundsInParent())==true)
					{
						enemy.Hurted(State.DealDamage());
						if(enemy.getHp()<=0)
						{
	                        this.State.setPoint(this.State.getPoint()+1);
	                    }
					}
				//}
			}
		});
		attackani2.play();
	}
	public void Attack03()
	{
	
	}
	public void Death()
	{
		walkingani.stop();
		attackani1.stop();
		attackani2.stop();
		hurtedani.stop();
		SetUpAnimation(
				death,
				new Image(ImgLink+"death"+"_"+"player"+"_"+NTS(Launcher.PlayerId)+".png"),
				deathani,
				Duration.millis(1000),
				6,
				6,
				deathani.getOffsetX(),
				State.getBGHEIGHT()
				);
		
		/// Set Current HP =-1
		for(int i=curHP-1;i>=0;i--) if(cf.root.getChildren().indexOf(HP[i])>=0) cf.root.getChildren().remove(HP[i]);
		curHP=-1;
		
		/// Set(X,Y)
		death.setX(State.getRenderX());
		death.setY(State.getRenderY());
		
		/// Remove Walking + Attack
		if(cf.root.getChildren().indexOf(walking)>=0) cf.root.getChildren().remove(walking);
		if(cf.root.getChildren().indexOf(attack1)>=0) cf.root.getChildren().remove(attack1);
		if(cf.root.getChildren().indexOf(attack2)>=0) cf.root.getChildren().remove(attack2);
		if(cf.root.getChildren().indexOf(attack3)>=0) cf.root.getChildren().remove(attack3);
		if(cf.root.getChildren().indexOf(hurted)>=0) cf.root.getChildren().remove(hurted);
		/// RemoveAll
		deathani.setOnFinished(e->{erase(); isDead =true;Launcher.dead=1;});
	
		 if(deathSound.isRunning()==false){
             if (Options.VolSE > 0 && Options.VolSE< 8) {
                         for (int j = 0; j <= 8 - Options.VolSE; j++) {
                             deathSound.soundDecrease();
                     }
                     deathSound.play();
                     }
                     else if (Options.VolSE == 8) {
                     deathSound.play();
                     }
                     else if (Options.VolSE == 0) {}
         
         }
		deathani.play();
               
               
	}
	public void Hurted(double Damage)
	{       
		SESound = new SoundPlayer(new File("src/simplerpg/data/hurt.wav"));
		if(curHP<=0) return;
		curHP =(int)Math.floor((State.getHP()/State.getMaxHP()) * 52);
		double trueDamage = (State.getEvasion()==false) ? Damage-State.getDefense()*Damage/100.0 : 0;
		double hp = (State.getHP()-trueDamage>=0) ? State.getHP() - trueDamage : 0;
		int perHP =(int) Math.floor((hp/State.getMaxHP()) *52);
		State.setHP(hp);
		
		for(int i=curHP-1;i>=perHP;i--) if(cf.root.getChildren().indexOf(HP[i])>=0) cf.root.getChildren().remove(HP[i]);
		curHP=perHP;
		if(curHP<=0) {Death();return;}
		
		walkingani.stop(); attackani1.stop();attackani2.stop();attackani3.stop();
		
		SetUpAnimation(
				hurted,
				new Image(ImgLink+"hurted"+"_"+"player"+"_"+NTS(Launcher.PlayerId)+".png"),
				hurtedani,
				Duration.millis(200),
				2,
				2,
				hurtedani.getOffsetX(),
				State.getBGHEIGHT()*curDirection
				);
		
		
		
		///Remove animation
		if(cf.root.getChildren().indexOf(walking)>=0) cf.root.getChildren().remove(walking);
		if(cf.root.getChildren().indexOf(attack1)>=0) cf.root.getChildren().remove(attack1);
		if(cf.root.getChildren().indexOf(attack2)>=0) cf.root.getChildren().remove(attack2);
		if(cf.root.getChildren().indexOf(attack3)>=0) cf.root.getChildren().remove(attack3);
		
		///Set up X,Y
		hurted.setX(State.getRenderX());
		hurted.setY(State.getRenderY());
		hurtedani.play();
		
                if (Options.VolSE > 0 && Options.VolSE< 8) {
                                for (int j = 0; j <= 8 - Options.VolSE; j++) {
                                    SESound.soundDecrease();
                            }
                            SESound.play();
                            }
                            else if (Options.VolSE == 8) {
                            SESound.play();
                            }

                            else if (Options.VolSE == 0) {}
	}

	public void erase() 
	{
		if(cf.root.getChildren().indexOf(circle)>=0) cf.root.getChildren().remove(circle);
		Ui.erase();
		if(cf.root.getChildren().indexOf(r)>=0) cf.root.getChildren().remove(r);
		if(cf.root.getChildren().indexOf(walking)>=0) cf.root.getChildren().remove(walking);
		if(cf.root.getChildren().indexOf(attack1)>=0) cf.root.getChildren().remove(attack1);
		if(cf.root.getChildren().indexOf(attack2)>=0) cf.root.getChildren().remove(attack2);
		if(cf.root.getChildren().indexOf(attack3)>=0) cf.root.getChildren().remove(attack3);
		if(cf.root.getChildren().indexOf(death)>=0) cf.root.getChildren().remove(death);
		if(cf.root.getChildren().indexOf(hurted)>=0) cf.root.getChildren().remove(hurted);
		if(cf.root.getChildren().indexOf(shadow)>=0) cf.root.getChildren().remove(shadow);
		for(int i=0;i<52;i++)
			if(cf.root.getChildren().indexOf(HP[i])>=0) cf.root.getChildren().remove(HP[i]);
		
	}	
	
	public Rectangle getBox()
	{
		return r;
	}
	public boolean isFighting()
	{
		return State.isFighting();
	}
	public double getX()
	{
		return State.getX();
	}
	public double getY()
	{
		return State.getY();
	}
	public void setX(double x)
	{
		State.setX(x);
		walking.setX(State.getRenderX());

		circle.setTranslateX(State.getX());
		
		r.setX(State.getX()-State.getWIDTH()/2);
		
		
	}
	public void setY(double y)
	{
		State.setY(y);
		walking.setY(State.getRenderY());
		
		circle.setTranslateY(State.getY()+State.getHEIGHT()/2+5.5);
		
		r.setY(State.getY()-State.getHEIGHT()/2);
	}
	public int getWidth()
	{
		return this.State.getWIDTH();
	}
	public int getHeight()
	{
		return this.State.getHEIGHT();
	}
	public double getHP()
	{
		return this.State.getHP();
	}
	public boolean checkStep(Map map,Rectangle R)
	{
		return map.isAvailable(R);
	}
	
}
