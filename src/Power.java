public class Power extends Node{
  public boolean setInput(Node addedInput){
		return false;
	}
	public Node getInput(int index){
		return null;
	}
	public void clrInput(int index){}
	public Power(State state, java.awt.Point coord, String name){
		super(state, coord, name);
	}
	@Override
	public void updateState(){//needs implemented
	
	}
	@Override
	public void draw(java.awt.Graphics g){}//needs implemented
}
