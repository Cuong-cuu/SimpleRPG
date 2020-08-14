package simplerpg.Button;

import java.io.File;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import simplerpg.BaseObj;
import simplerpg.Config;
import simplerpg.Launcher;
import simplerpg.sound.SoundPlayer;

public class Options extends BaseObj
{
	private Config cf = Config.getInstance();
	private String ImgLink = cf.getLink();
	private int WIDTH = cf.getWidth();
	private int HEIGHT = cf.getHeight();
	
	private Label title = new Label();
	
	private Image BG = new Image(ImgLink+"background.png");
	
	private ImageView Panel = new ImageView();
	private ImageView SoundEffect = new ImageView();
	private ImageView Music = new ImageView();
	
	private ButtonSetting Back = new ButtonSetting("BACK",48,HEIGHT-48,"backbutton",64,64);
	
	///Volume
	private ButtonSetting IncreaseSE = new ButtonSetting("IncreaseSoundEffect",WIDTH/2+235,HEIGHT/2-10,"increase",32,32);
	private ButtonSetting DecreaseSE = new ButtonSetting("DecreaseSoundEffect",WIDTH/2-85,HEIGHT/2-10,"decrease",32,32);
	
	private ButtonSetting IncreaseM = new ButtonSetting("IncreaseMusic",WIDTH/2+235,HEIGHT/2+170,"increase",32,32);
	private ButtonSetting DecreaseM = new ButtonSetting("DecreaseMusic",WIDTH/2-85,HEIGHT/2+170,"decrease",32,32);
	
	public int CIBV = 0 ; /// count images base Volume
	private ImageView[] BaseVolumeSE = new ImageView[10];
	private ImageView[] BaseVolumeM = new ImageView[10];
	private ImageView[] VolumeSE = new ImageView[10];
	private ImageView[] VolumeM = new ImageView[10];
	
	private SoundPlayer clickSound;
	public static int VolSE = 8;
	public static int VolM = 8;
	///Operation
	public Options()
	{
		///Background
		clickSound = new SoundPlayer(new File("src/simplerpg/data/click.wav"));
		///Title
		title.setText("OPTIONS");
		title.setId("title");
		title.setLayoutX(WIDTH/2-85);
		title.setLayoutY(HEIGHT/5);
		
		///Panel
		Panel.setImage(new Image(ImgLink+"optionpanel.png"));
		Panel.setX(WIDTH/3-72);
		Panel.setY(HEIGHT/5);
		Panel.setFitWidth(512);
		Panel.setFitHeight(466);
		
		
		///Back Button
		Back.getBut().getStyleClass().add("volumebutton");
		Back.FitImg();
		Back.SetUpWithImgWithoutName();
		
		///SoundEffect
		SoundEffect.setImage(new Image(ImgLink+"soundeffect.png"));
		SoundEffect.setX(WIDTH/4+10);
		SoundEffect.setY(HEIGHT/2-150);
		
		///Music
		Music.setImage(new Image(ImgLink+"music.png"));
		Music.setX(WIDTH/4+10);
		Music.setY(HEIGHT/2+30);
	
		///Increase Sound Effect Button
		IncreaseSE.getBut().getStyleClass().add("volumebutton");
		IncreaseSE.FitImg();
		IncreaseSE.SetUpWithImgWithoutName();
		
		///Decrease Sound Effect Button
		DecreaseSE.getBut().getStyleClass().add("volumebutton");
		DecreaseSE.FitImg();
		DecreaseSE.SetUpWithImgWithoutName();
		
		///Increase Music Button
		IncreaseM.getBut().getStyleClass().add("volumebutton");
		IncreaseM.FitImg();
		IncreaseM.SetUpWithImgWithoutName();
		
		///Decrease Music Button
		DecreaseM.getBut().getStyleClass().add("volumebutton");
		DecreaseM.FitImg();
		DecreaseM.SetUpWithImgWithoutName();
	
		///Base Volume 
		CIBV=0;
		for(int i=DecreaseSE.getX()+DecreaseSE.getFitWidth();i<IncreaseSE.getX()-DecreaseSE.getFitWidth();i=i+DecreaseSE.getFitWidth())
		{
			CIBV++;
			String Number = "02";
			if(CIBV==1) Number="01";
			if(i+DecreaseSE.getFitWidth()>=IncreaseSE.getX()-DecreaseSE.getFitWidth()) Number="03";
			
			BaseVolumeSE[CIBV]=new ImageView();
			BaseVolumeSE[CIBV].setImage(new Image(ImgLink+"basevolume"+Number+".png"));
			BaseVolumeSE[CIBV].setX(i);
			BaseVolumeSE[CIBV].setY(DecreaseSE.getY()-12);
			BaseVolumeSE[CIBV].setFitWidth(DecreaseSE.getFitWidth());
			BaseVolumeSE[CIBV].setFitHeight(DecreaseSE.getFitHeight());
			
			BaseVolumeM[CIBV]=new ImageView();
			BaseVolumeM[CIBV].setImage(new Image(ImgLink+"basevolume"+Number+".png"));
			BaseVolumeM[CIBV].setX(i);
			BaseVolumeM[CIBV].setY(DecreaseM.getY()-12);
			BaseVolumeM[CIBV].setFitWidth(DecreaseM.getFitWidth());
			BaseVolumeM[CIBV].setFitHeight(DecreaseM.getFitHeight());
		
		}
		
		///ID Volume
		CIBV=0;
		for(int i=DecreaseSE.getX()+DecreaseSE.getFitWidth();i<IncreaseSE.getX()-DecreaseSE.getFitWidth();i=i+DecreaseSE.getFitWidth())
		{
			CIBV++;
			String Number = "02";
			if(CIBV==1) Number="01";
			if(i+DecreaseSE.getFitWidth()>=IncreaseSE.getX()-DecreaseSE.getFitWidth()) Number="03";
			
			VolumeSE[CIBV]=new ImageView();
			VolumeSE[CIBV].setImage(new Image(ImgLink+"volume"+Number+".png"));
			VolumeSE[CIBV].setX(i);
			VolumeSE[CIBV].setY(DecreaseSE.getY()-12);
			VolumeSE[CIBV].setFitWidth(DecreaseSE.getFitWidth());
			VolumeSE[CIBV].setFitHeight(DecreaseSE.getFitHeight());
			
			VolumeM[CIBV]=new ImageView();
			VolumeM[CIBV].setImage(new Image(ImgLink+"volume"+Number+".png"));
			VolumeM[CIBV].setX(i);
			VolumeM[CIBV].setY(DecreaseM.getY()-12);
			VolumeM[CIBV].setFitWidth(DecreaseM.getFitWidth());
			VolumeM[CIBV].setFitHeight(DecreaseM.getFitHeight());
		
		}
		
	}
	@Override
	public void render(GraphicsContext gc) 
	{
		gc.drawImage(BG,0,0);
		if(cf.root.getChildren().indexOf(Panel)<0) cf.root.getChildren().add(Panel);
		if(cf.root.getChildren().indexOf(title)<0) cf.root.getChildren().add(title);
		
		if(cf.root.getChildren().indexOf(Back.getBut())<0) cf.root.getChildren().add(Back.getBut());
		if(cf.root.getChildren().indexOf(SoundEffect)<0) cf.root.getChildren().add(SoundEffect);
		if(cf.root.getChildren().indexOf(Music)<0) cf.root.getChildren().add(Music);
		
		if(cf.root.getChildren().indexOf(IncreaseSE.getBut())<0) cf.root.getChildren().add(IncreaseSE.getBut());
		if(cf.root.getChildren().indexOf(DecreaseSE.getBut())<0) cf.root.getChildren().add(DecreaseSE.getBut());
	
		if(cf.root.getChildren().indexOf(IncreaseM.getBut())<0) cf.root.getChildren().add(IncreaseM.getBut());
		if(cf.root.getChildren().indexOf(DecreaseM.getBut())<0) cf.root.getChildren().add(DecreaseM.getBut());
	
		for(int i=1;i<=CIBV;i++)
		{
			if(cf.root.getChildren().indexOf(BaseVolumeSE[i])<0) cf.root.getChildren().add(BaseVolumeSE[i]);
			if(cf.root.getChildren().indexOf(BaseVolumeM[i])<0) cf.root.getChildren().add(BaseVolumeM[i]);
		}
		
		for(int i=1;i<=VolSE;i++)
			if(cf.root.getChildren().indexOf(VolumeSE[i])<0) cf.root.getChildren().add(VolumeSE[i]);
		for(int i=1;i<=VolM;i++)
			if(cf.root.getChildren().indexOf(VolumeM[i])<0) cf.root.getChildren().add(VolumeM[i]);
	}
	public void update() 
	{
		///Back Butoon
		Back.getBut().setOnMouseClicked(event ->
		{
			/// Click Sound
			clickSound.play();
			/// Clear
			erase();
			
			/// Status
			Launcher.choose = cf.getPanel();
		});
		
		///SoundEffect Volume 
			///Increase
			IncreaseSE.getBut().setOnMouseClicked(event ->
			{
				/// Click Sound
                                clickSound.play();
				/// Clear
			
				/// Status
                                if(VolSE<8)
                                   VolSE++;
			});
			
			///Decrease
			DecreaseSE.getBut().setOnMouseClicked(event ->
			{
				/// Click Sound
                                clickSound.play();
				/// Clear
				if(cf.root.getChildren().indexOf(VolumeSE[VolSE]) >= 0) cf.root.getChildren().remove(VolumeSE[VolSE]);
				
				/// Status
                                if(VolSE>0)
                                    VolSE--;
			});
		
		///Music Volume
			///Increase
			IncreaseM.getBut().setOnMouseClicked(event ->
			{
				/// Click Sound
                                clickSound.play();
				/// Clear
				
				/// Status
                                if(VolM<8)
                                {
                                	Launcher.backgroundSound.soundIncrease();
                                	VolM++;
                                }
			});
			
			//Decrease
			DecreaseM.getBut().setOnMouseClicked(event ->
			{
				/// Click Sound
                                clickSound.play();
				/// Clear
				if(cf.root.getChildren().indexOf(VolumeM[VolM]) >= 0) cf.root.getChildren().remove(VolumeM[VolM]);
				
				/// Status
                if(VolM>0)
                {
                	Launcher.backgroundSound.soundDecrease();
                	VolM--;
                }
			});
                       
	}
	public void erase() 
	{
		/// Background + Panel + Title
		if(cf.root.getChildren().indexOf(BG) >= 0) cf.root.getChildren().remove(BG);
		if(cf.root.getChildren().indexOf(Panel)>=0) cf.root.getChildren().remove(Panel);
		if(cf.root.getChildren().indexOf(title)>=0) cf.root.getChildren().remove(title);
		
		/// Back button + ImgSoundEffect + ImgMusic
		if(cf.root.getChildren().indexOf(Back.getBut())>=0) cf.root.getChildren().remove(Back.getBut());
		if(cf.root.getChildren().indexOf(SoundEffect)>=0) cf.root.getChildren().remove(SoundEffect);
		if(cf.root.getChildren().indexOf(Music)>=0) cf.root.getChildren().remove(Music);
		
		/// Increase + Decrease Volume
		if(cf.root.getChildren().indexOf(IncreaseSE.getBut())>=0) cf.root.getChildren().remove(IncreaseSE.getBut());
		if(cf.root.getChildren().indexOf(DecreaseSE.getBut())>=0) cf.root.getChildren().remove(DecreaseSE.getBut());
	
		if(cf.root.getChildren().indexOf(IncreaseM.getBut())>=0) cf.root.getChildren().remove(IncreaseM.getBut());
		if(cf.root.getChildren().indexOf(DecreaseM.getBut())>=0) cf.root.getChildren().remove(DecreaseM.getBut());
	
		for(int i=1;i<=CIBV;i++)
		{
			if(cf.root.getChildren().indexOf(BaseVolumeSE[i])>=0) cf.root.getChildren().remove(BaseVolumeSE[i]);
			if(cf.root.getChildren().indexOf(BaseVolumeM[i])>=0) cf.root.getChildren().remove(BaseVolumeM[i]);
			if(cf.root.getChildren().indexOf(VolumeSE[i])>=0) cf.root.getChildren().remove(VolumeSE[i]);
			if(cf.root.getChildren().indexOf(VolumeM[i])>=0) cf.root.getChildren().remove(VolumeM[i]);
		}
	}
	
}
