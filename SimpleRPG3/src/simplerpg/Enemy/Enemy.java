package simplerpg.Enemy;

import java.io.File;
import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import simplerpg.BaseObj;
import simplerpg.Config;
import simplerpg.GameField;
import simplerpg.Launcher;
import simplerpg.SpriteAnimation;
import simplerpg.Adventurer.Player;
import simplerpg.Button.Options;
import simplerpg.Map.Map;
import simplerpg.sound.SoundPlayer;

public class Enemy
{
	protected double X;
	protected double Y;
	
	protected int width;
	protected int height;
	protected int bgwidth;
	protected int bgheight;
	
	protected double MaxHP;
	protected double hp;
	protected double Damage;
	protected double Defense;
	protected double RunningSpeed;
	protected double AttackSpeed;
	protected double Range;
	protected boolean dead = false;
	protected Rectangle r;
	
	protected int[] Dx = {-1,1,-1,0,0};
	protected int[] Dy = {-1,0,0,1,-1};
	
	public double getX() {
		return X;
	}
	public void setX(double x) {
		X = x;
	}
	public double getY() {
		return Y;
	}
	public void setY(double y) {
		Y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getBgwidth() {
		return bgwidth;
	}
	public void setBgwidth(int bgwidth) {
		this.bgwidth = bgwidth;
	}
	public int getBgheight() {
		return bgheight;
	}
	public void setBgheight(int bgheight) {
		this.bgheight = bgheight;
	}
	public double getMaxHP() {
		return MaxHP;
	}
	public void setMaxHP(double maxHP) {
		MaxHP = maxHP;
	}
	public double getHp() {
		return hp;
	}
	public void setHp(double hP) {
		hp = hP;
	}
	public double getDamage() {
		return Damage;
	}
	public void setDamage(double damage) {
		Damage = damage;
	}
	public double getDefense() {
		return Defense;
	}
	public void setDefense(double defense) {
		Defense = defense;
	}
	public double getRunningSpeed() {
		return RunningSpeed;
	}
	public void setRunningSpeed(double runningSpeed) {
		RunningSpeed = runningSpeed;
	}
	public double getAttackSpeed() {
		return AttackSpeed;
	}
	public void setAttackSpeed(double attackSpeed) {
		AttackSpeed = attackSpeed;
	}
	public double getRange() {
		return Range;
	}
	public void setRange(double range) {
		Range = range;
	}
	public double getRenderX()
	{
		return this.X-this.bgwidth/2;
	}
	public double getRenderY()
	{
		return this.Y-this.bgheight/2;
	}
	public Rectangle getBox()
	{
		return r;
	}
	public boolean isDead() {
		return dead;
	}
	public void setDead(boolean dead) {
		this.dead = dead;
	}
	public void SetUpAnimation(ImageView IV,Image Img,SpriteAnimation Ani,Duration duration,int Count,int Columns,int offsetX,int offsetY)
	{
		IV.setImage(Img);
		Ani.setDuration(duration);
		Ani.setCount(Count);
		Ani.setColumns(Columns);
		Ani.setOffsetX(offsetX);
		Ani.setOffsetY(offsetY);
	}
	public void calDirection(Map map,Player player) {}
	private void Move(Map map,int type) {}
	private void Attack(double directionX,Player player) {}
	public void Spawn(double X,double Y) {}
	public void Death() {}
	public void Hurted(double Damage) {}
	public void render(GraphicsContext gc) {}
	public void erase() {}
	public boolean checkStep(Map map,Rectangle R)
	{
		return map.isAvailable(R);
	}
}
