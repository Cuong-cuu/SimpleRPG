package simplerpg.Adventurer;

import java.io.File;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import simplerpg.BaseObj;
import simplerpg.Config;
import simplerpg.Launcher;
import simplerpg.Button.ButtonSetting;
import simplerpg.sound.SoundPlayer;

public class Board extends BaseObj
{
	private Config cf= Config.getInstance();
	private String ImgLink = cf.getLink();
	private int WIDTH = cf.getWidth();
	private int HEIGHT = cf.getHeight();
	
	private PlayerState Player = PlayerState.getState();
	
	private Image BG = new Image(ImgLink+"background.png");
	private ImageView board = new ImageView();
	
	private Label STR = new Label();
	private ButtonSetting IncreaseSTR = new ButtonSetting("Increase Strength",WIDTH/8+16*45,HEIGHT/4+16*1+16*3+5,"increasestat",40,40);
	private Label STRState = new Label();
	
	private Label AGI = new Label();
	private ButtonSetting IncreaseAGI = new ButtonSetting("Increase Agility",WIDTH/8+16*45,HEIGHT/4+16*5+16*3+5,"increasestat",40,40);
	private Label AGIState = new Label();
	
	private Label DEX = new Label();
	private ButtonSetting IncreaseDEX = new ButtonSetting("Increase Dexterity",WIDTH/8+16*45,HEIGHT/4+16*13+16*3+5,"increasestat",40,40);
	private Label DEXState = new Label();
	
	private Label VIT = new Label();
	private ButtonSetting IncreaseVIT = new ButtonSetting("Increase Vitality",WIDTH/8+16*45,HEIGHT/4+16*9+16*3+5,"increasestat",40,40);
	private Label VITState = new Label();
	
	private Label Point = new Label();
	
	private ButtonSetting Next = new ButtonSetting("Next",WIDTH-48,HEIGHT-48,"accept",64,64);
	private ButtonSetting Back = new ButtonSetting("Back",48,HEIGHT-58,"backbutton",64,64);
	
	private int STR_now = 0;
	private int AGI_now = 0;
	private int VIT_now = 0;
	private int DEX_now = 0;
        
    private SoundPlayer clickSound;
	
    public Board()
	{
		clickSound = new SoundPlayer(new File("src/simplerpg/data/click.wav"));
		///Board
		board.setImage(new Image(ImgLink+"playersboard.png"));
		board.setX(WIDTH/8);
		board.setY(HEIGHT/4);
		///Point
		Point.setText("Point: "+NTS(Player.getPoint()));
		Point.setId("state");
		Point.setLayoutX(WIDTH/8+16);
		Point.setLayoutY(HEIGHT/4+16*16);
		
		///STR + Button + Sate
		STR.setText("STR:");
		STR.setId("state");
		STR.setLayoutX(WIDTH/8+18*16);
		STR.setLayoutY(HEIGHT/4+16*1+16);

		STRState.setText(NTS(Player.getSTR())); 
		STRState.setId("state");
		STRState.setLayoutX(WIDTH/8+16*30);
		STRState.setLayoutY(HEIGHT/4+16*1+16);
		
		IncreaseSTR.getBut().getStyleClass().add("volumebutton");
		IncreaseSTR.FitImg();
		IncreaseSTR.SetUpWithImgWithoutName();
		
		
		///AGI
		AGI.setText("AGI:");
		AGI.setId("state");
		AGI.setLayoutX(WIDTH/8+18*16);
		AGI.setLayoutY(HEIGHT/4+16*5+16);
		
		AGIState.setText(NTS(Player.getAGI()));
		AGIState.setId("state");
		AGIState.setLayoutX(WIDTH/8+16*30);
		AGIState.setLayoutY(HEIGHT/4+16*5+16);
		
		IncreaseAGI.getBut().getStyleClass().add("volumebutton");
		IncreaseAGI.FitImg();
		IncreaseAGI.SetUpWithImgWithoutName();
		
		///VIT
		VIT.setText("VIT:");
		VIT.setId("state");
		VIT.setLayoutX(WIDTH/8+18*16);
		VIT.setLayoutY(HEIGHT/4+16*9+16);
		
		VITState.setText(NTS(Player.getVIT()));
		VITState.setId("state");
		VITState.setLayoutX(WIDTH/8+16*30);
		VITState.setLayoutY(HEIGHT/4+16*9+16);
		
		IncreaseVIT.getBut().getStyleClass().add("volumebutton");
		IncreaseVIT.FitImg();
		IncreaseVIT.SetUpWithImgWithoutName();
		
		///DEX
		DEX.setText("DEX:");
		DEX.setId("state");
		DEX.setLayoutX(WIDTH/8+18*16);
		DEX.setLayoutY(HEIGHT/4+16*13+16);
		
		DEXState.setText(NTS(Player.getDEX()));
		DEXState.setId("state");
		DEXState.setLayoutX(WIDTH/8+16*30);
		DEXState.setLayoutY(HEIGHT/4+16*13+16);
		
		IncreaseDEX.getBut().getStyleClass().add("volumebutton");
		IncreaseDEX.FitImg();
		IncreaseDEX.SetUpWithImgWithoutName();
		
		///Next
		Next.getBut().getStyleClass().add("volumebutton");
		Next.FitImg();
		Next.SetUpWithImgWithoutName();
		
		///Back
		Back.getBut().getStyleClass().add("volumebutton");
		Back.FitImg();
		Back.SetUpWithImgWithoutName();
	}
        
        
	
	@Override
	public void render(GraphicsContext gc) 
	{
		gc.drawImage(BG, 0, 0);
		
		if(cf.root.getChildren().indexOf(board)<0) cf.root.getChildren().add(board);
		
		if(cf.root.getChildren().indexOf(Point)<0) cf.root.getChildren().add(Point);
		
		if(cf.root.getChildren().indexOf(STR)<0) cf.root.getChildren().add(STR);
		if(cf.root.getChildren().indexOf(IncreaseSTR.getBut())<0) cf.root.getChildren().add(IncreaseSTR.getBut());
		if(cf.root.getChildren().indexOf(STRState)<0) cf.root.getChildren().add(STRState);
		
		if(cf.root.getChildren().indexOf(AGI)<0) cf.root.getChildren().add(AGI);
		if(cf.root.getChildren().indexOf(IncreaseAGI.getBut())<0) cf.root.getChildren().add(IncreaseAGI.getBut());
		if(cf.root.getChildren().indexOf(AGIState)<0) cf.root.getChildren().add(AGIState);
		
		if(cf.root.getChildren().indexOf(VIT)<0) cf.root.getChildren().add(VIT);
		if(cf.root.getChildren().indexOf(IncreaseVIT.getBut())<0) cf.root.getChildren().add(IncreaseVIT.getBut());
		if(cf.root.getChildren().indexOf(VITState)<0) cf.root.getChildren().add(VITState);
		
		if(cf.root.getChildren().indexOf(DEX)<0) cf.root.getChildren().add(DEX);
		if(cf.root.getChildren().indexOf(IncreaseDEX.getBut())<0) cf.root.getChildren().add(IncreaseDEX.getBut());
		if(cf.root.getChildren().indexOf(DEXState)<0) cf.root.getChildren().add(DEXState);
	
		if(cf.root.getChildren().indexOf(Next.getBut())<0) cf.root.getChildren().add(Next.getBut());
		if(cf.root.getChildren().indexOf(Back.getBut())<0) cf.root.getChildren().add(Back.getBut());
	}
	public void update() 
	{
		Player =PlayerState.getState();
		Point.setText("Point: "+NTS(Player.getPoint() - STR_now - VIT_now -DEX_now -AGI_now));
		STRState.setText(NTS(Player.getSTR()+STR_now));
		AGIState.setText(NTS(Player.getAGI()+AGI_now));
		VITState.setText(NTS(Player.getVIT()+VIT_now));
		DEXState.setText(NTS(Player.getDEX()+DEX_now));
		///Back Button
		Back.getBut().setOnMouseClicked(event -> 
		{
			/// Click Sound
			clickSound.play();
			/// Clear
			Point.setText("Point: "+NTS(Player.getPoint()));
			STRState.setText(NTS(Player.getSTR()));
			AGIState.setText(NTS(Player.getAGI()));
			VITState.setText(NTS(Player.getVIT()));
			DEXState.setText(NTS(Player.getDEX()));
			STR_now=AGI_now=DEX_now=VIT_now=0;
			erase();
					
			///Status
			Launcher.choose = cf.getPlaying();
			if(Launcher.joined==0) Launcher.difficulty = 0;
		});
		
		///Increase STR
                
		IncreaseSTR.getBut().setOnMouseClicked(event->
		{
			///Click Sound
			clickSound.play();
			///Clear
			
			///Status
			if(Player.getPoint()-STR_now-VIT_now-DEX_now-AGI_now>=1)
			{
				STR_now++;
				STRState.setText(NTS(Player.getSTR()+STR_now));
				Point.setText("Point: "+NTS(Player.getPoint()-STR_now-VIT_now-DEX_now-AGI_now));
			}
		});
			
		///Increase AGI
                
		IncreaseAGI.getBut().setOnMouseClicked(event->
		{
			///Click Sound
			clickSound.play();
			///Clear
			
			///Status
			if(Player.getPoint()-STR_now-VIT_now-DEX_now-AGI_now>=1)
			{
				AGI_now++;
				AGIState.setText(NTS(Player.getAGI()+AGI_now));
				Point.setText("Point: "+NTS(Player.getPoint()-STR_now-VIT_now-DEX_now-AGI_now));
			}
		});
			
		///Increase VIT
                
		IncreaseVIT.getBut().setOnMouseClicked(event->
		{
			///Click Sound
			clickSound.play();
			///Clear
			
			///Status
			if(Player.getPoint()-STR_now-VIT_now-DEX_now-AGI_now>=1)
			{
				VIT_now++;
				VITState.setText(NTS(Player.getVIT()+VIT_now));
				Point.setText("Point: "+NTS(Player.getPoint()-STR_now-VIT_now-DEX_now-AGI_now));
			}
		});
			
		///Increase DEX
                
		IncreaseDEX.getBut().setOnMouseClicked(event->
		{
			///Click Sound
			clickSound.play();
			///Clear
			
			///Status
			if(Player.getPoint()-STR_now-VIT_now-DEX_now-AGI_now>=1)
			{
				DEX_now++;
				DEXState.setText(NTS(Player.getDEX()+DEX_now));
				Point.setText("Point: "+NTS(Player.getPoint()-STR_now-VIT_now-DEX_now-AGI_now));
			}
		});
		
		///Next
		Next.getBut().setOnMouseClicked(event->
		{
			///Click Sound
			clickSound.play();
			///Clear
			this.erase();
			
			///Status
			Player.addSTR(STR_now);
			Player.addAGI(AGI_now);
			Player.addVIT(VIT_now);
			Player.addDEX(DEX_now);
			Player.setPoint(Player.getPoint()-STR_now-VIT_now-DEX_now-AGI_now);
			STR_now=VIT_now=DEX_now=AGI_now=0;
			Launcher.joined=cf.getIsPlay();
		});
		
	} 
	public void erase() 
	{
		//STR_now=0;
		//AGI_now = 0;
		//VIT_now = 0;
		//DEX_now = 0;
		if(cf.root.getChildren().indexOf(BG)>=0) cf.root.getChildren().remove(BG);
		
		if(cf.root.getChildren().indexOf(board)>=0) cf.root.getChildren().remove(board);
		
		if(cf.root.getChildren().indexOf(Point)>=0) cf.root.getChildren().remove(Point);
		
		if(cf.root.getChildren().indexOf(STR)>=0) cf.root.getChildren().remove(STR);
		if(cf.root.getChildren().indexOf(IncreaseSTR.getBut())>=0) cf.root.getChildren().remove(IncreaseSTR.getBut());
		if(cf.root.getChildren().indexOf(STRState)>=0) cf.root.getChildren().remove(STRState);
		
		if(cf.root.getChildren().indexOf(AGI)>=0) cf.root.getChildren().remove(AGI);
		if(cf.root.getChildren().indexOf(IncreaseAGI.getBut())>=0) cf.root.getChildren().remove(IncreaseAGI.getBut());
		if(cf.root.getChildren().indexOf(AGIState)>=0) cf.root.getChildren().remove(AGIState);
		
		if(cf.root.getChildren().indexOf(VIT)>=0) cf.root.getChildren().remove(VIT);
		if(cf.root.getChildren().indexOf(IncreaseVIT.getBut())>=0) cf.root.getChildren().remove(IncreaseVIT.getBut());
		if(cf.root.getChildren().indexOf(VITState)>=0) cf.root.getChildren().remove(VITState);
		
		if(cf.root.getChildren().indexOf(DEX)>=0) cf.root.getChildren().remove(DEX);
		if(cf.root.getChildren().indexOf(IncreaseDEX.getBut())>=0) cf.root.getChildren().remove(IncreaseDEX.getBut());
		if(cf.root.getChildren().indexOf(DEXState)>=0) cf.root.getChildren().remove(DEXState);
	
		if(cf.root.getChildren().indexOf(Next.getBut())>=0) cf.root.getChildren().remove(Next.getBut());
		if(cf.root.getChildren().indexOf(Back.getBut())>=0) cf.root.getChildren().remove(Back.getBut());
	}
}
