package simplerpg.Map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javafx.animation.Animation;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import simplerpg.BaseObj;
import simplerpg.Config;
import simplerpg.Launcher;
import simplerpg.SpriteAnimation;

public class Map extends BaseObj
{
	private Config cf = Config.getInstance();
	private String ImgLink = cf.getLink();
	private String mapId;
	private ImageView map;
	private ImageView spinning = new ImageView();
	private ImageView torching = new ImageView();
    private SpriteAnimation[] spinningani = new SpriteAnimation[3];
    private SpriteAnimation[] torchingani = new SpriteAnimation[13];
	
	private ImageView[] ImgEntity;
	private Rectangle[] r1;
	private ArrayList<EntityInfo> Entity = new ArrayList<>();
	private ArrayList<EntityInfo> Room = new ArrayList<>();
	
	private int numEnemy1 = 0;
	private int numEnemy2 = 0;
	private int numEnemy3 = 0;
	private int numEnemy4 = 0;
	private int numEnemy5 = 0;
	private int numEnemy6 = 0;
	private int numEnemy7 = 0;
	private int numEnemy8 = 0;
	
	public Map(String Id)
	{
		///Name of Map
		this.mapId=Id;
		
		///Map
		map = new ImageView();
		map.setImage(new Image(ImgLink+"/map"+this.mapId+"/"+"map"+this.mapId+".png"));
		map.setX(0);
		map.setY(0);
		
		/// Input Map
		switch(Id)
		{
			case "00":
		    	Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/hole.input",17));
		    	Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/big_single_tree.input",22));
		    	Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/small_root.input",15));
		    	Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/log2.input",14));
		    	Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/high_tree.input",37));
		    	Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/light.input",38));
		    	Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/tree_without_leaves.input",39));
		    	Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/small_house_1.input",40));
		    	Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/small_house_2.input",41));
		    	Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/big_house.input",42));
		    	Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/outside_map.input",43));
		    	Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/wall.input",44));
		    	Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/teleport.input",10));
		    	
		    	numEnemy1 =  1;
		    	numEnemy2 =  1;
		    	numEnemy3 =  1;
		    	numEnemy4 =  3;
		    	numEnemy5 =  1;
		    	numEnemy6 =  3;
		    	numEnemy7 =  3;
		    	numEnemy8 =  1;
		    	
		    	
				Room.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/room.input",0));
				for(int i=0;i<Room.size();i++)
				{
					if(i<6) Room.get(i).setTypeRoom(1);//fight room
					if(i==6) Room.get(i).setTypeRoom(2);///base
					if(i==7) Room.get(i).setTypeRoom(3);///lobby room
					
				}
				break;
				
			case "01":
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/jar.input",1));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/brokenjar.input",2));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/stair_01.input",3));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/stair_02.input",4));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/brokenpillar_01.input",5));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/brokenpillar_02.input",6));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/brokenpillar_03.input",7));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/skeleton_01.input",8));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/skeleton_02.input",9));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/stele.input",34));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/tallpillar_01.input",35));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/tallpillar_02.input",36));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/torch.input",47));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/teleport.input",10));
				
				numEnemy1 =  3;
		    	numEnemy2 =  3;
		    	numEnemy3 =  1;
		    	numEnemy4 =  1;
		    	numEnemy5 =  3;
		    	numEnemy6 =  1;
		    	numEnemy7 =  1;
		    	numEnemy8 =  1;
				
				Room.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/room.input",0));
				for(int i=0;i<Room.size();i++)
				{
					
					if(i<=4) Room.get(i).setTypeRoom(1); /// Fight Room
					if(i==5) Room.get(i).setTypeRoom(2); /// Base
					if(i>5) Room.get(i).setTypeRoom(3); /// Lobby
				}
				break;
			
			case "02":
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/stone.input",11));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/short_tree.input",12));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/log1.input",13));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/log2.input",14));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/small_root.input",15));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/big_root.input",16));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/hole.input",17));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/two_tall_tree.input",18));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/vertical_tree_row.input",19));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/horizontal_tree_row.input",20));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/small_normal_tree.input",21));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/big_single_tree.input",22));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/upper_half_big_tree.input",23));				
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/lower_half_big_tree.input",24));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/right_half_big_tree.input",25));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/left_half_big_tree.input",26));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/forest1.input",27));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/forest2.input",28));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/river.input",29));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/mountain1.input",30));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/mountain2.input",31));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/mountain3.input",32));
				Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/spin_whrilpool.input",46));
                Entity.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/bunches_of_trees.input",45));
			
                numEnemy1 =  1;
		    	numEnemy2 =  1;
		    	numEnemy3 =  3;
		    	numEnemy4 =  1;
		    	numEnemy5 =  1;
		    	numEnemy6 =  1;
		    	numEnemy7 =  3;
		    	numEnemy8 =  3;
		    	
				Room.addAll(getCoordinate("src/simplerpg/data/map"+mapId+"/room.input",0));
				for(int i=0;i<Room.size();i++)
				{
					//if(Launcher.MapId==1) System.out.println(Room.get(i).getMinX()+" "+Room.get(i).getMaxX()+" "+Room.get(i).getMinY()+" "+Room.get(i).getMaxY());
					if(i<=6) Room.get(i).setTypeRoom(1); /// Fight Room
					if(i==7) Room.get(i).setTypeRoom(2); /// Base
					if(i>7) Room.get(i).setTypeRoom(3); /// Lobby
					
				}
				
				break;
				
		}
		/// Image Entity
		ImgEntity = new ImageView[Entity.size()];
		r1= new Rectangle[Entity.size()];
		for(int i=0;i<Entity.size();i++)
		{
			ImgEntity[i] = new ImageView();
			
			EntityInfo e = Entity.get(i);
			r1[i]= new Rectangle();
			r1[i].setStroke(Color.LIGHTSALMON);
			r1[i].setStrokeWidth(1);
			r1[i].setFill(null);
			r1[i].setX(e.getMinX());
			r1[i].setY(e.getMinY());
			r1[i].setWidth(e.getMaxX()-e.getMinX());
			r1[i].setHeight(e.getMaxY()-e.getMinY());
			switch(e.getType())
			{
				case 1:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/jar.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;
				case 2:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/brokenjar.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;
				case 3:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/stair_01.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;
				case 4:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/stair_02.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;
				case 5:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/brokenpillar_01.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;
				case 6:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/brokenpillar_02.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;
				case 7:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/brokenpillar_03.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;
				case 8:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/skeleton_01.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;
				case 9:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/skeleton_02.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;
				case 10:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/teleport.png"));
					ImgEntity[i].setX(e.getMinX());	
					ImgEntity[i].setY(e.getMinY());
					break;
				case 11:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/stone.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;
				case 12: 
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/short_tree.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;
				case 13:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/log1.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;
				case 14:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/log2.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;
				case 15:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/small_root.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;
				case 16:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/big_root.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;
				case 17:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/hole.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;
				case 18:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/two_tall_tree.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;
				case 19:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/vertical_tree_row.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;
				case 20:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/horizontal_tree_row.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;
				case 21:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/small_normal_tree.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;
				case 22:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/big_single_tree.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;	
				case 23:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/upper_half_big_tree.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;	
				case 24:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/lower_half_big_tree.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;	
				case 25:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/right_half_big_tree.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;	
				case 26:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/left_half_big_tree.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;	
				case 27:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/forest1.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;	
				case 28:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/forest2.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;		
				case 34:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/stele.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;
				case 35:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/tallpillar_01.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;
				case 36:
					ImgEntity[i].setImage(new Image(e.getImgLink()+"/map"+mapId+"/tallpillar_02.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					break;	
					
				case 46:
					///Spinning animation
					spinning = ImgEntity[i];
					ImgEntity[i].setViewport(new Rectangle2D(0,0,128,128));
					ImgEntity[i].setImage(new Image(ImgLink+"/map"+mapId+"/spin"+"_"+"whrilpool.png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					for(int j=0;j<3;j++)
					{
						spinningani[j] =  new SpriteAnimation(ImgEntity[i], Duration.millis(1000), 4, 4,128 , 128,128,128);
						spinningani[j].setCycleCount(Animation.INDEFINITE);
						spinningani[j].play();
					}
					break;
					
				case 47:
					///Torching animation
					torching = ImgEntity[i];
					ImgEntity[i].setViewport(new Rectangle2D(0,0,32,64));
					ImgEntity[i].setImage(new Image(ImgLink+"/map"+mapId+"/torch"+".png"));
					ImgEntity[i].setX(e.getMinX());
					ImgEntity[i].setY(e.getMinY());
					for(int j=0;j<13;j++)
					{
						torchingani[j] =  new SpriteAnimation(ImgEntity[i], Duration.millis(1000), 9,9, 32 , 64,32,64);
						torchingani[j].setCycleCount(Animation.INDEFINITE);
						torchingani[j].play();
					}
					break;	
			}		
		}
	}
	@Override
	public void render(GraphicsContext gc) 
	{
		if(cf.root.getChildren().indexOf(map)<0) cf.root.getChildren().add(map);
		if(cf.root.getChildren().indexOf(spinning)<0) cf.root.getChildren().add(spinning);
		if(cf.root.getChildren().indexOf(torching)<0) cf.root.getChildren().add(torching);
		//for(int i=0;i<Entity.size();i++)
			//if(cf.root.getChildren().indexOf(r1[i])<0) cf.root.getChildren().add(r1[i]);
	}
	public void erase() 
	{
		if(cf.root.getChildren().indexOf(map)>=0) cf.root.getChildren().remove(map);
		for(int i=0;i<Entity.size();i++)if(cf.root.getChildren().indexOf(ImgEntity[i])>=0)
		{
			cf.root.getChildren().remove(ImgEntity[i]);
			cf.root.getChildren().remove(r1[i]);
		}
		if(cf.root.getChildren().indexOf(spinning)>=0) cf.root.getChildren().remove(spinning);
		if(cf.root.getChildren().indexOf(torching)>=0) cf.root.getChildren().remove(torching);
	}
	private ArrayList<EntityInfo> getCoordinate(String Link,int type)
	{
		ArrayList <EntityInfo> EI = new ArrayList<>();
		File file = new File(Link);
		try
		{
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine())
			{
				String input = scan.nextLine();
				EntityInfo entity = new EntityInfo(type);
				int x1=-1,y1=-1;
				int x2=-1,y2=-1;
				int x3=-1,y3=-1;
				int x4=-1,y4=-1;
				int num=0;
				for(int i=0;i<input.length();i++)
				{
					if(input.charAt(i)=='(' || input.charAt(i)==',') continue;
					if(input.charAt(i)==' ' || input.charAt(i)==')')
					{
						if(x1==-1) x1=num;
						else if(y1==-1) y1=num;
						else if(x2==-1) x2=num;
						else if(y2==-1) y2=num;
						else if(x3==-1) x3=num;
						else if(y3==-1) y3=num;
						else if(x4==-1) x4=num;
						else if(y4==-1) y4=num;
						num=0;
						continue;
					}
					num=num*10+(input.charAt(i)-'0');
				}
				entity.setMinX(Math.min(x1,Math.min(x2, Math.min(x3, x4))));
				entity.setMaxX(Math.max(x1,Math.max(x2, Math.max(x3, x4))));
				entity.setMinY(Math.min(y1,Math.min(y2, Math.min(y3, y4))));
				entity.setMaxY(Math.max(y1,Math.max(y2, Math.max(y3, y4))));
				EI.add(entity);
			}
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		return EI;
	}
	public ImageView getImageEntity(int i)
	{
		return ImgEntity[i];
	}
	public ArrayList<EntityInfo> getEntity()
	{
		return Entity;
	}
	public ArrayList<EntityInfo> getRoom()
	{
		return Room;
	}
	public EntityInfo isRoom(Rectangle R)
	{
		for(int i=0;i<Room.size();i++)
		{
			EntityInfo e= Room.get(i);
			Rectangle RRoom = new Rectangle();
			RRoom.setWidth(e.getMaxX()-e.getMinX());
			RRoom.setHeight(e.getMaxY()-e.getMinY());
			RRoom.setX(e.getMinX());
			RRoom.setY(e.getMinY());
			if(R.getBoundsInParent().intersects(RRoom.getBoundsInParent())  && e.getState() == 1 && e.getTypeRoom()==1) return e;
		}
		return null;
	}
	public boolean isTeleport(double X,double Y)
	{
		if(Entity.size()==0) return false;
		EntityInfo e = Entity.get(Entity.size()-1);
		if(X>=e.getMinX() && X<=e.getMaxX() && Y>=e.getMinY() && Y<=e.getMaxY()) return true;
		return false;
	}
	public boolean isAvailable(Rectangle R)
	{
		boolean isRoom =false;
		double minX = R.getX();
		double minY = R.getY();
		double maxX = R.getX()+R.getWidth();
		double maxY = R.getY()+R.getHeight();
		minY = maxY-5;
		R.setY(maxY-5);
		R.setHeight(5);
		for(int i=0;i<Room.size();i++)
		{
			EntityInfo e = Room.get(i);
			
			if(e.getMinX()<=minX && minX<=e.getMaxX() && e.getMinY()<=minY && minY<=e.getMaxY()) 
			{
				isRoom=true;
				
				break;
			}
		}
		if(isRoom==false) return false;
		isRoom =false;
		for(int i=0;i<Room.size();i++)
		{
			EntityInfo e = Room.get(i);
			
			if(e.getMinX()<=maxX && maxX<=e.getMaxX() && e.getMinY()<=maxY && maxY<=e.getMaxY()) 
			{
				isRoom=true;
				break;
			}
		}
		if(isRoom==false) return false;	
		for(int i=0;i<Entity.size();i++)
		{
			EntityInfo e= Entity.get(i);
			if(e.getType()==10) continue;
			if(r1[i].getBoundsInParent().intersects(R.getBoundsInParent())==true
			|| (e.getMinX()<=R.getX() && R.getX()+R.getWidth()<=e.getMaxX() && e.getMinY()<=R.getY() && R.getY()+R.getHeight()<=e.getMaxY())) return false;
		}
		return true;
	}
	public boolean isAvailableToSpawn(double x,double y,int width,int height,double minX,double maxX,double minY,double maxY)
	{
		for(int i=(int)x-width/2;i<=(int)x+width/2;i++)
		{
			for(int j=(int) y-height/2;j<=(int) y+height/2;j++)
			{
				boolean checked=false;
				if(minX<=i && i<=maxX && minY<=j && j<=maxY) checked=true;
				for(int z=0;z<Entity.size();z++)
				{
					EntityInfo e =Entity.get(z);
					if(e.getType() == 10) continue;
					if(e.getMinX()<=i && i<=e.getMaxX() && e.getMinY()<=j && e.getMaxY()>=j) return false;
				}
				if(checked == false) return false;
			}
		}	
		
		
		return true;
	}
 	public int getNumEnemy1() {
		return numEnemy1;
	}
	public int getNumEnemy2() {
		return numEnemy2;
	}
	public int getNumEnemy3() {
		return numEnemy3;
	}
	public int getNumEnemy4() {
		return numEnemy4;
	}
	public int getNumEnemy5() {
		return numEnemy5;
	}
	public int getNumEnemy6() {
		return numEnemy6;
	}
	public int getNumEnemy7() {
		return numEnemy7;
	}
	public int getNumEnemy8() {
		return numEnemy8;
	}
}
