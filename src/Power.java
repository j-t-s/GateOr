public class Power extends Node{
  public boolean setInput(Node addedInput){
		return false;
	}
	public Node getInput(int index){
		return null;
	}
	public void clrInput(int index){}
	public Power(State state, Point coord, String name){
		super(state, coord, name);
	}
	@Override
	void updateState(){//needs implemented
	
	}
	@Override
	void draw(java.awt.Graphics g){}//needs implemented
}
