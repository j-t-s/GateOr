public class Output extends Node{
	Node input = null;
  	public boolean setInput(Node addedInput){
		if (input == null){
			input = addedInput;
		}else{
			return false;
		}
		return true;
	}
	public Node getInput(int index){//regardless of index, input is returned
		return input;
	}
	public void clrInput(int index){//regardless of index, input is set to null
		input = null;
	}
	public Output(java.awt.Point coord, String name){
		super(Node.State.UNDEF, coord, name);
	}
	@Override
	public void updateState(){//needs implemented
	
	}
	@Override
  	public void draw(java.awt.Graphics g){}//needs implemented
}
