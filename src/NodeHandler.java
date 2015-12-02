public class NodeHandler{
  enum Mode {MOVE, SELECT, TOGGLE_POWER};
  private static Mode mode;
  
  static Mode getMode(){
    return mode;
  }
  
  static void setMode(Mode newMode){
    mode = newMode;
  }
  
  /**Constructs a new note determined by type and appends it to the Node linkedList in GateOr.*/
  /*static void create(NodeType type){}//enum NodeType will need to be defined.
    Node newOne;
    switch(type){
      case Power: newOne = new Power();break;
      case Output: newOne = new Output();break;
      case AND: newOne = new AND();break;
      case OR: newOne = new OR();break;
      case XOR: newOne = new XOR();break;
      case NOR: newOne = new NOR();break;
      case NAND: newOne = new NAND();break;
      case NOT: newOne = new NOT();break;
    }
    GateOr.NodeList.add(newOne);
  */
  /**Retrieves nodes from the selected node list to remove all reference of them found in the node linked list and the input lists of all of the node.*/
  static void delete(){}
  /**Updates the coordinates of the nodes in the selected nodes list based on the user's mouse coordinates*/
  static void move(){
    setMode(Mode.MOVE);
    //Among other things.
  }
  /**Adds or removes the nodes found in the selected node list based on the user mouse input*/
  static void select(){
    setMode(mode = Mode.SELECT);
    //Among other things.
  }
  /**This method takes the first two nodes defined in the selected node list and adds the first node's reference to the second node's input list*/
  static void connect(){}
  /**Uses the nodes in the selected node list to remove all references of each other in their input lists*/
  static void disconnect(){}
  /**Toggles the power for all Power nodes in the selected node list*/
  static void togglePower(){
    setMode(mode = Mode.TOGGLE_POWER);
    //Among other things.
  }
}
