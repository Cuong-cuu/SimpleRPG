package simplerpg;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import simplerpg.Adventurer.Player;
import simplerpg.Adventurer.PlayerState;
import simplerpg.Bullet.Bullet;
import simplerpg.Button.Options;
import simplerpg.Enemy.Enemy;
import simplerpg.Enemy.Enemy1;
import simplerpg.Enemy.Enemy2;
import simplerpg.Enemy.Enemy3;
import simplerpg.Enemy.Enemy4;
import simplerpg.Enemy.Enemy5;
import simplerpg.Enemy.Enemy6;
import simplerpg.Enemy.Enemy7;
import simplerpg.Enemy.Enemy8;
import simplerpg.Map.EntityInfo;
import simplerpg.Map.Map;
import simplerpg.sound.SoundPlayer;

public class GameField extends BaseObj
{
		private Config cf = Config.getInstance();
		private static GameField instance;       
        private GameOver gameOver = new GameOver();
        public static GameField getInstance()
        {
            if(instance==null)instance= new GameField();
            return instance;
        }
        public static void ResetGameField()
        {
        	PlayerState.ResetState();
        	instance = null;
        }
        
        private Map map;
        private Player player;

        private ArrayList<Enemy> enemyList = new ArrayList<>();
        private ArrayList<Bullet> playerbulletList = new ArrayList<>();
        private ArrayList<Bullet> enemybulletList = new ArrayList<>();
        private SoundPlayer mapSound;
        private SoundPlayer spawnSound;
        private SoundPlayer waterSound;
        private SoundPlayer deathSound;
        //private Enemy1 e1 ;
        public GameField()
        {
     		///Map
            map = new Map(NTS(Launcher.MapId));	
            
            //Sound_map1
            deathSound = new SoundPlayer(new File("src/simplerpg/data/death.wav"));
            mapSound = new SoundPlayer(new File("src/simplerpg/data/map00.wav"));
            spawnSound = new SoundPlayer(new File("src/simplerpg/data/spawn.wav"));
            
            for(int i=1;i<=8-Options.VolM;i++)
            	mapSound.soundDecrease();
            mapSound.playMusic();
                    
            ///Player
            player = new Player();
            SpawnPlayer();
            	
        }
        

        private void Next()
        {
            enemyList.clear();
            //arrowList.clear();
            map = new Map(NTS(Launcher.MapId));

            if (Launcher.MapId == 1) {
                mapSound.stop();
                mapSound.close();
                mapSound = new SoundPlayer(new File("src/simplerpg/data/map01.wav"));
                for(int i=1;i<=8-Options.VolM;i++)
                	mapSound.soundDecrease();
                mapSound.playMusic();
            }

            else if (Launcher.MapId == 2) {
                mapSound.stop();
                mapSound.close();
                mapSound = new SoundPlayer(new File("src/simplerpg/data/map02.wav"));
                waterSound = new SoundPlayer(new File("src/simplerpg/data/water.wav"));
                for(int i=1;i<=8-Options.VolM;i++)
                {
                	mapSound.soundDecrease();
                	waterSound.soundDecrease();
                }
                mapSound.playMusic();
                waterSound.playMusic();
            }

            SpawnPlayer();

        }
        @Override
        public void render(GraphicsContext gc) 
        {	
        			
                /// Render Map
                map.render(gc);

                /// Render Entity
                ArrayList<EntityInfo> Entity = map.getEntity();
                for(int i=0;i<Entity.size();i++) 
                        if(cf.root.getChildren().indexOf(map.getImageEntity(i))<0) cf.root.getChildren().add(map.getImageEntity(i));

                ///player's bullet
                for(int i=0;i<playerbulletList.size();i++)
            	{
                	if(playerbulletList.get(i).isRunning()==true)
                		playerbulletList.get(i).render(gc);
            	}
                ///Enemies' Bullet
                for(int i=0;i<enemybulletList.size();i++)
            	{
                	if(enemybulletList.get(i).isRunning()==true)
                		enemybulletList.get(i).render(gc);
            	}
                
                ///Enemies
                for(int i=0;i<enemyList.size();i++)
                {
                	Enemy enemy = enemyList.get(i);
                	enemy.render(gc);
                }
                
                /// Render player
                player.render(gc);
                
                ///Check dead
                if (Launcher.dead==1) 
                {
                    mapSound.stop();
                    erase();
                    cf.camera.translateXProperty().set(cf.getWidth()/2);
                    cf.camera.translateYProperty().set(cf.getHeight()/2);
                    cf.camera.translateZProperty().set(-1343);
                    gameOver.update();
                    gameOver.render(gc);
                    if(Launcher.choose == 0 &&
        			Launcher.dead == 0 &&
        			Launcher.joined == 0 &&
        			Launcher.MapId ==0 &&
        			Launcher.difficulty ==0)
                    this.ResetGameField();
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
                
        }
        private int direction =1;
        public void update() 
        {
        	/// Get Mouse
        	Launcher.scene.setOnMouseClicked(new EventHandler<MouseEvent>()
        	{
        		@Override
				public void handle(MouseEvent e)
				{
        			playerbulletList.add(new Bullet(player.getX(),player.getY(),e.getX(),e.getY(),5,10,10));
				}
        	});
   
        	/// Player's Bullet
        	ArrayList<Bullet> playerbulletListMirror = new ArrayList<>();
        	
        	for(int i=0;i<playerbulletList.size();i++)
        	{
        		if(playerbulletList.get(i).isRunning()==true)
        		{
        			playerbulletListMirror.add(playerbulletList.get(i));
        		}
        		else playerbulletList.get(i).erase();
        	}
        	for(int i=0;i<playerbulletList.size();i++)
        		playerbulletList.get(i).update(map, enemyList);
        	
        	///Enemies' Bullet
        	ArrayList<Bullet> enemybulletListMirror = new ArrayList<>();
        	
        	for(int i=0;i<enemybulletList.size();i++)
        	{
        		if(enemybulletList.get(i).isRunning()==true)
        		{
        			enemybulletListMirror.add(enemybulletList.get(i));
        		}
        		else enemybulletList.get(i).erase();
        	}
        	for(int i=0;i<enemybulletList.size();i++)
        		enemybulletList.get(i).update(map, player);
        	
        	/// Get Key
            Launcher.scene.setOnKeyPressed(new EventHandler<KeyEvent>()
            {

				@Override
				public void handle(KeyEvent e) 
				{
					if(e.getCode() == KeyCode.PAGE_UP && cf.camera.getTranslateZ()+10<=0) 
                    {
                            cf.camera.translateZProperty().set(cf.camera.getTranslateZ()+10);
                            cf.View+=10;
                    }
					else
                    if(e.getCode() == KeyCode.PAGE_DOWN && cf.camera.getTranslateZ()-10>=-1350)
                    {
                            cf.camera.translateZProperty().set(cf.camera.getTranslateZ()-10);
                            cf.View-=10;
                    }
                    else
                    if(e.getCode() == KeyCode.W || e.getCode() == KeyCode.UP)
                    {
                            direction = 1;
                            player.setVelY(map, -1, direction);
                    }
                    else
                    if(e.getCode() == KeyCode.S || e.getCode() == KeyCode.DOWN)
                    {
                            direction = 3;
                            player.setVelY(map, 1, direction);				
                    }
                    else
                    if(e.getCode() ==  KeyCode.A || e.getCode() == KeyCode.LEFT)
                    {
                            direction = 2;
                            player.setVelX(map, -1, direction);
                    }
                    else
                    if(e.getCode() == KeyCode.D || e.getCode() == KeyCode.RIGHT)
                    {
                            direction = 4;
                            player.setVelX(map, 1, direction);
                    }
                    else
                    if(e.getCode() == KeyCode.Q)
                    {
                            player.Attack01(direction,enemyList);
                            //player.setVelX(map, 0, direction);
                            //player.setVelY(map, 0, direction);
                    }
                    else
                    if(e.getCode() == KeyCode.E)
                    {
                            player.Attack02(direction,enemyList);
                            //player.setVelX(map, 0, direction);
                            //player.setVelY(map, 0, direction);
                    }
                    else
                    if(e.getCode() == KeyCode.P)
                    {
                    	
                    	if(Launcher.joined==1)
                    	{
                    		erase();
                    		cf.camera.translateXProperty().set(cf.getWidth()/2);
                    		cf.camera.translateYProperty().set(cf.getHeight()/2);
                    		cf.camera.translateZProperty().set(-1343);
                    		Launcher.joined = 2;
                        }
                        else Launcher.joined = 1;
                    }
                    else
                    	if (e.getCode() == KeyCode.O) 
                    	{
                    		if (Launcher.joined == 1)
                    		{
                    			erase();
                    			cf.camera.translateXProperty().set(cf.getWidth()/2);
                    			cf.camera.translateYProperty().set(cf.getHeight()/2);
                    			cf.camera.translateZProperty().set(-1343);
                    			Launcher.choose = 0;
                    		}
                            else Launcher.joined = 2;
                        }
				}
            });
            
            player.UpdatePosition(map);
            Launcher.scene.setOnKeyReleased(new EventHandler<KeyEvent>()
            {

				@Override
				public void handle(KeyEvent e) 
				{
					if(e.getCode() == KeyCode.W)
					{
						player.setVelY(map, 0, direction); 
					}
					else
					if(e.getCode() == KeyCode.S)
					{
						player.setVelY(map, 0, direction); 
					}
					else
					if(e.getCode() == KeyCode.A)
					{
						player.setVelX(map, 0, direction);
					}
					else
					if(e.getCode() == KeyCode.D)
					{
						player.setVelX(map, 0, direction);
					}	
				}
            });
            
            /// edit camera
            cf.camera.translateXProperty().set(player.getX());
            cf.camera.translateYProperty().set(player.getY());
            cf.camera.translateZProperty().set(cf.View);

            /// change Map
            if(map.isTeleport(player.getX(), player.getY()+player.getHeight()/2)==true || Launcher.MapId==-1)
            {
            		if(Launcher.MapId==-1)
            			player = new Player();
                    Launcher.MapId++;
                    erase();
                    Next();
            }

            /// Update Bullets + enemies
            ArrayList <Enemy> enemyListMirror = new ArrayList<>();
            for(int i=0;i<enemyList.size();i++)
            {
            	Enemy enemy = enemyList.get(i);
            	if(enemy.isDead()==true) continue;
        		enemyListMirror.add(enemy);

            }
            
            enemyList = enemyListMirror;
            
            for(int i=0;i<enemyList.size();i++)
            {
            	Enemy enemy = enemyList.get(i);
            	if(enemy.isDead()==true) continue;
            	enemy.calDirection(map, player);

            }
            //Entered Fighting area -> Spawn Enemies
            EntityInfo e = map.isRoom(player.getBox());
            if(e!= null)
            {
            	spawnEnemy(e.getMinX(),e.getMaxX(),e.getMinY(),e.getMaxY());
	            if (Options.VolSE > 0 && Options.VolSE< 8) {
	                for (int j = 0; j <= 8 - Options.VolSE; j++) {
	                    spawnSound.soundDecrease();
	            }
	            spawnSound.play();
	            }
	            else if (Options.VolSE == 8) {
	            spawnSound.play();
	            }
	
	            else if (Options.VolSE == 0) {}
	            e.setState(2);
            }    

            
        }
        public void erase() 
        {       
                map.erase();
                player.erase();
                for(int i=0;i<playerbulletList.size();i++)
            		playerbulletList.get(i).erase();
                
                for(int i=0;i<enemybulletList.size();i++)
            		enemybulletList.get(i).erase();

                for(int i=0;i<enemyList.size();i++)
                {
                	Enemy enemy = enemyList.get(i);
                	enemy.erase();
                }
                if (Launcher.MapId == 1) {
                mapSound.stop();
                }
                if (Launcher.MapId == 2) {
                mapSound.close();
                }
                if (Launcher.MapId == 3) {
                mapSound.close();
            }
        }
        private void spawnEnemy(double MinX,double MaxX,double MinY,double MaxY)
        {
                Random rand = new Random();
                
                int numEnemy1 =	rand.nextInt(map.getNumEnemy1());
                
                for(int i=1;i<=numEnemy1;i++)
                {
                	int x=0,y=0;
                	Enemy1 enemy = new Enemy1();    	
                	do
                	{
                		x= (int)(rand.nextInt((int)MaxX-(int)MinX)+MinX);
                		y= (int)(rand.nextInt((int)MaxY-(int)MinY)+MinY);          		
                	}while(map.isAvailableToSpawn(x, y, enemy.getWidth(), enemy.getHeight(), MinX, MaxX, MinY, MaxY)==false);
                	enemy.Spawn(x, y);
                	enemyList.add(enemy);
                }
                
                int numEnemy2 = rand.nextInt(map.getNumEnemy2());
                
                for(int i=1;i<=numEnemy2;i++)
                {
                	int x=0,y=0;
                	Enemy2 enemy = new Enemy2();
                	do
                	{
                		x= (int)(rand.nextInt((int)MaxX-(int)MinX)+MinX);
                		y= (int)(rand.nextInt((int)MaxY-(int)MinY)+MinY);          		
                	}while(map.isAvailableToSpawn(x, y, enemy.getWidth(), enemy.getHeight(), MinX, MaxX, MinY, MaxY)==false);
                	enemy.Spawn(x, y);
                	enemyList.add(enemy);
                }
                
                int numEnemy3 = rand.nextInt(map.getNumEnemy3());
                
                for(int i=1;i<=numEnemy3;i++)
                {
                	int x=0,y=0;
                	Enemy3 enemy = new Enemy3();
                	
                	do
                	{
                		x= (int)(rand.nextInt((int)MaxX-(int)MinX)+MinX);
                		y= (int)(rand.nextInt((int)MaxY-(int)MinY)+MinY);          		
                	}while(map.isAvailableToSpawn(x, y, enemy.getWidth(), enemy.getHeight(), MinX, MaxX, MinY, MaxY)==false);
                	enemy.Spawn(x, y);
                	enemyList.add(enemy);
                }
                
                int numEnemy4 = rand.nextInt(map.getNumEnemy4());
                
                for(int i=1;i<=numEnemy4;i++)
                {
                	int x=0,y=0;
                	Enemy4 enemy = new Enemy4();
                	
                	do
                	{
                		x= (int)(rand.nextInt((int)MaxX-(int)MinX)+MinX);
                		y= (int)(rand.nextInt((int)MaxY-(int)MinY)+MinY);          		
                	}while(map.isAvailableToSpawn(x, y, enemy.getWidth(), enemy.getHeight(), MinX, MaxX, MinY, MaxY)==false);
                	enemy.Spawn(x, y);
                	enemyList.add(enemy);
                }
                
                int numEnemy5 = rand.nextInt(map.getNumEnemy5());
                
                for(int i=1;i<=numEnemy5;i++)
                {
                	int x=0,y=0;
                	Enemy5 enemy = new Enemy5();
                	do
                	{
                		x= (int)(rand.nextInt((int)MaxX-(int)MinX)+MinX);
                		y= (int)(rand.nextInt((int)MaxY-(int)MinY)+MinY);          		
                	}while(map.isAvailableToSpawn(x, y, enemy.getWidth(), enemy.getHeight(), MinX, MaxX, MinY, MaxY)==false);
                	enemy.Spawn(x, y);
                	enemyList.add(enemy);
                }
                
                int numEnemy6 = rand.nextInt(map.getNumEnemy6());
                
                for(int i=1;i<=numEnemy6;i++)
                {
                	int x=0,y=0;
                	Enemy6 enemy = new Enemy6();
                	
                	do
                	{
                		x= (int)(rand.nextInt((int)MaxX-(int)MinX)+MinX);
                		y= (int)(rand.nextInt((int)MaxY-(int)MinY)+MinY);          		
                	}while(map.isAvailableToSpawn(x, y, enemy.getWidth(), enemy.getHeight(), MinX, MaxX, MinY, MaxY)==false);
                	enemy.Spawn(x, y);
                	enemyList.add(enemy);
                }
                
                int numEnemy7 = rand.nextInt(map.getNumEnemy7());
                
                for(int i=1;i<=numEnemy7;i++)
                {
                	int x=0,y=0;
                	Enemy7 enemy = new Enemy7();
                	
                	do
                	{
                		x= (int)(rand.nextInt((int)MaxX-(int)MinX)+MinX);
                		y= (int)(rand.nextInt((int)MaxY-(int)MinY)+MinY);          		
                	}while(map.isAvailableToSpawn(x, y, enemy.getWidth(), enemy.getHeight(), MinX, MaxX, MinY, MaxY)==false);
                	enemy.Spawn(x, y);
                	enemyList.add(enemy);
                }
                
                int numEnemy8 = rand.nextInt(map.getNumEnemy8());
                
                for(int i=1;i<=numEnemy8;i++)
                {
                	int x=0,y=0;
                	Enemy8 enemy = new Enemy8();
                	
                	do
                	{
                		x= (int)(rand.nextInt((int)MaxX-(int)MinX)+MinX);
                		y= (int)(rand.nextInt((int)MaxY-(int)MinY)+MinY);          		
                	}while(map.isAvailableToSpawn(x, y, enemy.getWidth(), enemy.getHeight(), MinX, MaxX, MinY, MaxY)==false);
                	enemy.Spawn(x, y);
                	enemyList.add(enemy);
                }
        }
        private void SpawnPlayer()
        {
                Random rand = new Random();
                ///player = new Player(); 
                ArrayList<EntityInfo> Room=map.getRoom();
                for(int i=0;i<Room.size();i++)
                {
                        EntityInfo e = Room.get(i);
                        
                        if(e.getTypeRoom() == 2)
                        {
                                int x,y;
                                do
                                {
                                        x= rand.nextInt((int)(e.getMaxX()-e.getMinX()))+(int)e.getMinX();
                                        y= rand.nextInt((int)(e.getMaxY()-e.getMinY()))+(int)e.getMinY();
                                }while(map.isAvailableToSpawn(x, y, player.getWidth(), player.getHeight(), e.getMinX(), e.getMaxX(), e.getMinY(), e.getMaxY())==false);
                                player.setX(x); player.setY(y);
                        }
                }
        }
        public boolean checkImpact(Enemy e ,Rectangle R)
        {
        		for(int i=0;i<enemyList.size();i++)
                {
                	if(enemyList.get(i)==e)
                	{
                		continue;
                	}
                	if(enemyList.get(i).getBox().getBoundsInParent().intersects(R.getBoundsInParent())==true)return false;
                }
                return true;
        }
        
        public void addEnemybullet(Bullet b)
        {
        	this.enemybulletList.add(b);
        }
            

            
            
}
