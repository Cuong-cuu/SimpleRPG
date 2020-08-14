package simplerpg;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


public class SpriteAnimation extends Transition {

    private final ImageView imageView;
    private Duration duration;
    private int count;
    private int columns;
    private int offsetX;
    private int offsetY;
    private final int width;
    private final int height;
    private int lastIndex;

    public SpriteAnimation(
            ImageView imageView,
            Duration duration,
            int count,  int columns,
            int offsetX, int offsetY,
            int width,   int height) 
    {
        this.imageView = imageView;
        this.duration = duration;
        this.count     = count;
        this.columns   = columns;
        this.offsetX   = offsetX;
        this.offsetY   = offsetY;
        this.width     = width;
        this.height    = height;
        setCycleDuration(this.duration);
        setCycleCount(1);
        setInterpolator(Interpolator.LINEAR);
        this.imageView.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
    }

    public void setOffsetX(int x)
    {
    	this.offsetX=x;
    
    }
    public int getOffsetX()
    {
    	return this.offsetX;
    }
    public int getOffsetY()
    {
    	return this.offsetY;
    }
    public void setOffsetY(int y)
    {
    	this.offsetY=y;
    }
    public void setCount(int count)
    {
    	this.count=count;
    }
    public void setColumns(int columns)
    {
    	this.columns=columns;
    }
    public void setDuration(Duration duration)
    {
    	this.duration = duration;
    	setCycleDuration(this.duration);
    }
	protected void interpolate(double k)
	{
		final int index = Math.min((int) Math.floor(k * count), count-1 );
		final int x = (index % columns-1) * width  + offsetX;
		final int y = (index / columns-1) * height + offsetY;  
		imageView.setViewport(new Rectangle2D(x, y, width, height));
    }
}