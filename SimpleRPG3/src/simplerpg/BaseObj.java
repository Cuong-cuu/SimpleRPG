package simplerpg;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import simplerpg.Config;

public abstract class BaseObj 
{
	public abstract void render(GraphicsContext gc);
	//public abstract void update();
	//public abstract void erase();
	public BaseObj() {};
	public String NTS(long x)
	{
		String text ="";
		while(x>0)
		{
			text= (char)(x%10+'0') +text;
			x/=10;
		}
		int n= text.length();
		for(int i=1;i<=2-n;i++) text="0"+text;
		return text;
	}
}
