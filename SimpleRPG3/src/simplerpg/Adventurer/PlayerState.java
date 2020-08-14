package simplerpg.Adventurer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;



public class PlayerState extends Pane
{
	private static PlayerState State ;
	
	private static long Point;

	private long Strength;
	private long Agility;
	private long Dexterity;
	private long Intelligence ;
	private long Vitality;
	
	private double MaxHP;
	private double HP ;
	private double Damage ;
	private double Defense ;
	private double Evasion ;
	private double AttackSpeed ;
	private double RunningSpeed;
	private double WidthRange;
	private double Range;
	private boolean fighting;
	private boolean death;
	
	private double X;
	private double Y;
	
	private int width=25;
	private int height=48;
	private int bgwidth = 64;
	private int bgheight = 64;
	
	///Operation
	public PlayerState()
	{
		this.fighting = false;
		this.death = false;
		
		this.Strength = 10;
		this.Agility = 50;
		this.Dexterity = 0;
		this.Intelligence = 0;
		this.Vitality = 0;
		
		this.Point = 10;
		this.MaxHP=100;
		this.Damage =15;
		this.Defense = 5;
		this.Evasion = 1;
		this.AttackSpeed = 0.2;
		this.RunningSpeed = 3;
		this.Range = 30;
		this.WidthRange = 16;
		
		
		this.HP=5.0*this.Strength+MaxHP;
		this.MaxHP = 5.0*this.Strength+MaxHP;
	}
	public static PlayerState getState()
	{
		if(State == null) State = new PlayerState();
		return State;
	}
	public static void ResetState()
	{
		State = null;
	}
	public double getMaxHP()
	{
		return MaxHP;
	}
	public double getHP()
	{
		return this.HP;
	}
	public void setHP(double x)
	{
		this.HP=x;
	}
	public double DealDamage()
	{
		return Strength*2+Damage;
	}
	public double getDefense()
	{
		return ((Defense+Vitality*0.5<=100.0)?Defense+Vitality*0.5:100.0);
	}
	public boolean getEvasion()
	{
		double chance = (Math.random()*10);
		if(chance<=Evasion+Dexterity*0.5) return true;
		return false;
	}
	public double getAttackSpeed()
	{
		return Math.max(this.AttackSpeed-Agility*0.000015,0.0001);
	}
	public double getRunningSpeed()
	{
		return this.RunningSpeed+Agility*0.01;
	}
	public double getRange()
	{
		return this.Range;
	}
	public double getWidthRange()
	{
		return this.WidthRange;
	}
	public void setSTR(long STR)
	{
		this.Strength=STR;
	}
	public void setAGI(long AGI)
	{
		this.Agility= AGI;
	}
	public void setDEX(long DEX)
	{
		this.Dexterity=DEX;
	}
	public void setINT(long INT)
	{
		this.Intelligence=INT;
	}
	public void setVIT(long VIT)
	{
		this.Vitality=VIT;
	}
	public void setPoint(long Point)
	{
		this.Point=Point;
	}
	public long getSTR()
	{
		return this.Strength;
	}
	public long getAGI()
	{
		return this.Agility;
	}
	public long getDEX()
	{
		return this.Dexterity;
	}
	public long getINT()
	{
		return this.Intelligence;
	}
	public long getVIT()
	{
		return this.Vitality;
	}
	public long getPoint()
	{
		return this.Point;
	}
	public void addPoint(long x)
	{
		this.Point+=x;
	}
	public void addSTR(long x)
	{
		this.Strength+=x;
		this.MaxHP+=5.0*x;
		this.HP+=5.0*x;
	}
	public void addVIT(long x)
	{
		this.Vitality+=x;
	}
	public void addAGI(long x)
	{
		this.Agility+=x;
	}
	public void addINT(long x)
	{
		this.Intelligence+=x;
	}
	public void addDEX(long x)
	{
		this.Dexterity+=x;
	}
	public double getX()
	{
		return this.X;
	}
	public double getY()
	{
		return this.Y;
	}
	public void setX(double X)
	{
		this.X=X;
	}
	public void setY(double Y)
	{
		this.Y=Y;
	}
	public void setWIDTH(int width)
	{
		this.width=width;
	}
	public void setHEIGHT(int height)
	{
		this.height=height;
	}
	public int getWIDTH()
	{
		return this.width;
	}
	public int getHEIGHT()
	{
		return this.height;
	}
	public void setBGWIDTH(int width)
	{
		this.bgwidth=width;
	}
	public void setBGHEIGHT(int height)
	{
		this.bgheight=height;
	}
	public int getBGWIDTH()
	{
		return this.bgwidth;
	}
	public int getBGHEIGHT()
	{
		return this.bgheight;
	}
	public double getRenderX()
	{
		return this.X-this.bgwidth/2;
	}
	public double getRenderY()
	{
		return this.Y-this.bgheight/2;
	}
	public boolean isFighting() {
		return fighting;
	}
	public void setFighting(boolean fighting) {
		this.fighting = fighting;
	}
        public boolean isDeath() {
		return death;
	}
	public void setDeath(boolean death) {
		this.death = death;
	}
	
}
