package simplerpg.Map;

public class EntityInfo 
{
	private int Width;
	private int Height;
	private int NumTileX;
	private int NumTileY;
	private int Type;
	private String Name;
	private String InputLink;
	private String ImgLink;
	private double MinX ;
	private double MaxX ; 
	private double MinY ;
	private double MaxY ;
	private int typeRoom ;
	private int state;
	
	public EntityInfo(int Id)
	{
		this.Type = Id;
		switch(Id)
		{
			case 0: ///Room
				this.InputLink ="src/simplerpg/data/map01/room.input";
				this.typeRoom = 0;
				this.state = 1;
				break;
			case 1: /// jar
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 3;
				this.NumTileY = 3;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name = "jar";
				break;
			case 2: /// broken jar
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 3;
				this.NumTileY = 3;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name="brokenjar";
			case 3: /// stair_01
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 3;
				this.NumTileY = 3;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name="stair_01";
				break;
			case 4: /// stair_02
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 3;
				this.NumTileY = 3;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name="stair_02";
				break;
			case 5: /// brokenpillar_01
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 3;
				this.NumTileY = 3;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name="brokenpillar_01";
				break;
			case 6: /// brokenpillar_02
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 3;
				this.NumTileY = 3;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name="brokenpillar_02";
				break;
			case 7: /// brokenpillar_03
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 3;
				this.NumTileY = 3;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name="brokenpillar_03";
				break;
			case 8: /// skeleton_01
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 3;
				this.NumTileY = 3;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name="skeleton_01";
				break;
			case 9: /// skeleton_02
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 3;
				this.NumTileY = 3;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name="skeleton_02";
				break;
			case 10: /// teleport
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 7;
				this.NumTileY = 6;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name="teleport";
				break;
			case 11: ///stone
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 3;
				this.NumTileY = 3;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name = "stone";
				break;
			case 12: ///short tree
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 3;
				this.NumTileY = 3;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name = "short_tree";
				break;
			case 13: /// log1
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 3;
				this.NumTileY = 3;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name = "log1";
				break;
			case 14: /// log2
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 3;
				this.NumTileY = 3;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name = "log2";
				break;
			case 15: /// small root	
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 3;
				this.NumTileY = 3;
				this.InputLink = "src/simplerpg/data";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name = "small_root";
				break;
			case 16: /// big root
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 5;
				this.NumTileY = 4;
				this.InputLink = "src/simplerpg/data";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name = "big_root";
				break;
			case 17: /// hole
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 3;
				this.NumTileY = 3;
				this.InputLink = "src/simplerpg/data";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name = "hole";
				break;
			case 18: /// two tall tree
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 3;
				this.NumTileY = 9;
				this.InputLink = "src/simplerpg/data";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name = "two_tall_tree";
				break;
			case 19: /// vertical tree row
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 6;
				this.NumTileY = 21;
				this.InputLink = "src/simplerpg/data";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name = "vertical_tree_row";
				break;
			case 20: /// horizontal tree row
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 15;
				this.NumTileY = 6;
				this.InputLink = "src/simplerpg/data";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name = "horizontal_tree_row";
				break;
			case 21: /// small normal tree
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 4;
				this.NumTileY = 6;
				this.InputLink = "src/simplerpg/data";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name = "small_normal_tree";
				break;
			case 22: ///  big single tree
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 6;
				this.NumTileY = 6;
				this.InputLink = "src/simplerpg/data";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name = "big_single_tree";
				break;
			case 23: /// upper half big tree
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 6;
				this.NumTileY = 3;
				this.InputLink = "src/simplerpg/data";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name = "upper_half_big_tree";
				break;
			case 24: /// lower half big tree
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 6;
				this.NumTileY = 3;
				this.InputLink = "src/simplerpg/data";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name = "lower_half_big_tree";
				break;
			case 25: /// right half big tree
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 3;
				this.NumTileY = 6;
				this.InputLink = "src/simplerpg/data";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name = "right_half_big_tree";
				break;
			case 26: /// left half big tree
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 3;
				this.NumTileY = 6;
				this.InputLink = "src/simplerpg/data";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name = "left_half_big_tree";
				break;
			case 27: /// forest 1
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 12;
				this.NumTileY = 12;
				this.InputLink = "src/simplerpg/data";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name = "forest1";
				break;
			case 28: /// forest 2
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 14;
				this.NumTileY = 21;
				this.InputLink = "src/simplerpg/data";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name = "forest2";
				break;
			case 29: /// river
				this.Width = 16;
				this.Height = 16;
				this.InputLink = "src/simplerpg/data";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name = "river";
				break;
			case 30: /// mountain 1
				this.Width = 16;
				this.Height = 16;
				this.InputLink = "src/simplerpg/data";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name = "mountain1";
				break;
			case 31: /// mountain 2
				this.Width = 16;
				this.Height = 16;
				this.InputLink = "src/simplerpg/data";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name = "mountain2";
				break;	
			case 32: /// mountain 3
				this.Width = 16;
				this.Height = 16;
				this.InputLink = "src/simplerpg/data";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name = "mountain3";
				break;
			case 33: /// whirlpool
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 3;
				this.NumTileY = 2;
				this.InputLink = "src/simplerpg/data";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name = "whirlpool";
				break;
			case 34: /// stele
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 7;
				this.NumTileY = 6;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name="stele";
				break;	
			case 35: /// tall pillar 01
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 3;
				this.NumTileY = 6;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name="tallpillar_01";
				break;
			case 36: /// tall pillar 02
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 3;
				this.NumTileY = 6;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name="tallpillar_02";
				break;
                                case 37: // high tree
				this.Width = 16;
				this.Height = 16;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name="high_tree";
				break;
			case 38: /// light
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 3;
				this.NumTileY = 2;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name="light";
				break;
			case 39: ///tree without leaves
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 3;
				this.NumTileY = 3;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name="tree_without_leaves";
				break;
			case 40: ///small_house_1
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 16;
				this.NumTileY = 19;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name="small_house_1";
				break; 
			case 41: ///small_house_2
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 14;
				this.NumTileY = 10;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name="small_house_2";
				break;
			case 42: ///big_house
				this.Width = 16;
				this.Height = 16;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name="big_house";
				break;
			case 43: ///outside_map
				this.Width = 16;
				this.Height = 16;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name="outside_map";
				break;
			case 44: ///wall
				this.Width = 16;
				this.Height = 16;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name="wall";
				break;
                        case 45: // bunches of trees
                                this.Width = 16;
				this.Height = 16;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name="bunches_of_trees";
				break;
			case 46 : /// spin whrilpool
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 8;
				this.NumTileY = 8;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name="spin_whrilpool";
				break;
			case 47: /// torch
				this.Width = 16;
				this.Height = 16;
				this.NumTileX = 18;
				this.NumTileY = 4;
				this.InputLink = "src/simplerpg/data/";
				this.ImgLink = "file:src/simplerpg/data/";
				this.Name="torch";
				break;
			}
	}
	public int getHeight() {
		return Height;
	}
	public void setHeight(int height) {
		Height = height;
	}
	public int getNumTileX() {
		return NumTileX;
	}
	public void setNumTileX(int numTileX) {
		NumTileX = numTileX;
	}
	public int getNumTileY() {
		return NumTileY;
	}
	public void setNumTileY(int numTileY) {
		NumTileY = numTileY;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	public int getWidth() {
		return Width;
	}
	public void setWidth(int width) {
		Width = width;
	}
	public String getInputLink() {
		return InputLink;
	}
	public void setInputLink(String inputLink) {
		InputLink = inputLink;
	}
	public String getImgLink() {
		return ImgLink;
	}
	public void setImgLink(String imgLink) {
		ImgLink = imgLink;
	}
	public double getMinX() {
		return MinX;
	}
	public void setMinX(double minX) {
		MinX = minX;
	}
	public double getMaxX() {
		return MaxX;
	}
	public void setMaxX(double maxX) {
		MaxX = maxX;
	}
	public double getMinY() {
		return MinY;
	}
	public void setMinY(double minY) {
		MinY = minY;
	}
	public double getMaxY() {
		return MaxY;
	}
	public void setMaxY(double maxY) {
		MaxY = maxY;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getTypeRoom() {
		return typeRoom;
	}
	public void setTypeRoom(int typeRoom) {
		this.typeRoom = typeRoom;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	
}
