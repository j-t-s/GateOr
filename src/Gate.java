public abstract class Gate extends Node{
	Node[] inputs = new Node[2];//For simplicity, inputs is an array of two items. The graphics only support two inputs.
	public boolean setInput(Node addedInput){
		if (inputs[0] == null){
			inputs[0] = addedInput;
		}else if (inputs[1] == null){
			inputs[1] = addedInput;
		}else{
			return false;
		}
		return true;
	}
	public Node getInput(int index){
		if (index == 0 || index == 1){
			return inputs[index];
		}else{
			return null;
		}
	}
	public void clrInput(int index){
		if (index == 0 || index == 1){
			inputs[index] = null;
		}
	}
	public Gate(java.awt.Point coord, String name){
		super(Node.State.UNDEF, coord, name);
	}
}
