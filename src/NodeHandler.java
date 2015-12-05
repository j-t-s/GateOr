import java.util.LinkedList;

public class NodeHandler{
  enum Mode {MOVE, SELECT, TOGGLE_POWER};
  private static Mode mode;
  
  /**Gets the Mode of the NodeHandler*/
  static Mode getMode(){
    return mode;
  }
  
  /**Sets the Mode of the NodeHandler*/
  static void setMode(Mode newMode){
    mode = newMode;
  }
  
  /**Constructs a new note determined by type and appends it to the Node linkedList in GateOr.*/
  static void create(Node.Type type){
    Node newOne = null;
    switch(type){
      case POWER: newOne = new Power();break;
      case OUTPUT: newOne = new Output();break;
      case AND: newOne = new AND();break;
      case OR: newOne = new OR();break;
      case XOR: newOne = new XOR();break;
      case NOR: newOne = new NOR();break;
      case NAND: newOne = new NAND();break;
      case NOT: newOne = new NOT();break;
    }
	if (newOne != null)
		GateOr.NodeList.add(newOne);
  }
  /**Retrieves nodes from the selected node list to remove all reference of them found in the node linked list and the input lists of all of the node.*/
  static void delete(){
    LinkedList<Node> rm = GateOr.getSelectedList();
    for (Node node: rm){
      //Remove all selected nodes in the NodeList
      if(GateOr.getNodeList().remove(rm)){//This is just for testing, All items in the selected list should be in the NodeList. Just a test, will be removed in final version
        System.out.println("Item "+rm+" was removed");
      }else{
        System.out.println("Item "+rm+" was not removed!");
      }
    }
  }
  /**Updates the coordinates of the nodes in the selected nodes list based on the user's mouse coordinates*/
  static void move(){
    setMode(Mode.MOVE);
    //Among other things.
  }
  /**Adds or removes the nodes found in the selected node list based on the user mouse input*/
  static void select(){
    setMode(mode = Mode.SELECT);
    /*Just some pseudocode
      if click
        get coordinate and collision detect the nodes until node is found
        GateOr.getSelectedList().addAll(newlySelectedNodes);//Add to the selected list
      if mousepress and drag
        get coordinates to form rectange and collision detect to find all the nodes selected
        GateOr.getSelectedList().addAll(newlySelectedNodes);
    */
    //Among other things.
  }
  /**This method takes the first two nodes defined in the selected node list and adds the first node's reference to the second node's input list*/
  static void connect(){
    if (GateOr.getSelectedList().size() > 2){System.out.println("More than two items are selected.");}//We could do a popup window to inform the user?
    if (GateOr.getSelectedList().size() > 1){//Two or more items are selected and a connection can be make
		if(!GateOr.getSelectedList().get(1).setInput(GateOr.getSelectedList().get(0))){//Make connection and test if bad
			System.out.println("Either already connected, the second selcted item must be disconnected before a new connectioin is made.");
			System.out.println("Or the Power needs to be selcted first not second.");
		}
	
	
	
	
	/*Will be used in the Gate and Output setInput overriden method.
	  if (GateOr.getSelectedList().get(1) instanceof Gate){//If it is a gate.
		  if (GateOr.getSelectedList().get(1).inputs[0] == null){//Not sure if we are using an array for inputs
			GateOr.getSelectedList().get(1).inputs[0] = GateOr.getSelectedList().get(0);
		  }else if (GateOr.getSelectedList().get(1).inputs[1] == null){//Go to the second input
			GateOr.getSelectedList().get(1).inputs[1] = GateOr.getSelectedList().get(0);
		  }else{
			//Gate is already connect, must be disconnected before a new connectioin is made.
			System.out.println("Gate is already connect. It must be disconnected before a new connectioin is made.");
		  }
	  }else if (GateOr.getSelectedList().get(1) instanceof Output){
		  if (GateOr.getSelectedList().get(1).input == null){//Not sure if we are using an array for inputs
			GateOr.getSelectedList().get(1).input = GateOr.getSelectedList().get(0);
		  }else{
			//output is already connect, must be disconnected before a new connectioin is made.
			System.out.println("Output is already connect. It must be disconnected before a new connectioin is made.");
		  }
	  }
	  */
    }
  }
  /**Uses the nodes in the selected node list to remove all references of each other in their input lists*/
  static void disconnect(){
    LinkedList<Node> dc = GateOr.getSelectedList();
    for (Node separateIn: dc){
      for (Node separateOut: dc){
        if (separateIn.getInput(0) == separateOut){separateIn.clrInput(0);}
        if (separateIn.getInput(1) == separateOut){separateIn.clrInput(1);}
      }
    }
  }
  /**Toggles the power for all Power nodes in the selected node list*/
  static void togglePower(){
    setMode(mode = Mode.TOGGLE_POWER);
    for (Node nodes: GateOr.getSelectedList()){
      if (nodes instanceof Power){
        if (nodes.getState() == Node.State.ON){
          nodes.setState(Node.State.OFF);
        }else{
          nodes.setState(Node.State.ON);
        }
      }
    }
  }
}
