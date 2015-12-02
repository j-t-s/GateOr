public class NodeHandler{
  enum Mode {MOVE, SELECT, TOGGLE_POWER};
  Mode mode;
  
  /**Constructs a new note determined by type and appends it to the Node linkedList in GateOr.*/
  /*void create(NodeType type){}//enum NodeType will need to be defined.
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
  void delete(){}
  /**Updates the coordinates of the nodes in the selected nodes list based on the user's mouse coordinates*/
  void move(){
    mode = Mode.MOVE;
    //Among other things.
  }
  /**Adds or removes the nodes found in the selected node list based on the user mouse input*/
  void select(){
    mode = Mode.SELECT;
    //Among other things.
  }
  /**This method takes the first two nodes defined in the selected node list and adds the first node's reference to the second node's input list*/
  void connect(){}
  /**Uses the nodes in the selected node list to remove all references of each other in their input lists*/
  void disconnect(){}
  /**Toggles the power for all Power nodes in the selected node list*/
  void togglePower(){
    mode = Mode.TOGGLE_POWER;
    //Among other things.
  }
}
