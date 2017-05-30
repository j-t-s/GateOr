import java.util.LinkedList;

import java.awt.Point;
import java.awt.Rectangle;
/**
This Class handles all the nodes in their creation, movement, selection, deletion, connection, and disconnection.
*/
public class NodeHandler{
  enum Mode {MOVE, SELECT, TOGGLE_POWER};
  private static Mode mode = Mode.SELECT;
  
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
      case POWER: newOne = new Power(Node.State.OFF, new Point(0,0), System.currentTimeMillis()+"");break;
      case OUTPUT: newOne = new Output(new Point(20,20), System.currentTimeMillis()+"");break;
      case AND: newOne = new AND(new Point(40,40), System.currentTimeMillis()+"");break;
      case OR: newOne = new OR(new Point(60,60), System.currentTimeMillis()+"");break;
      case XOR: newOne = new XOR(new Point(80,80), System.currentTimeMillis()+"");break;
      case NOR: newOne = new NOR(new Point(100,100), System.currentTimeMillis()+"");break;
      case NAND: newOne = new NAND(new Point(120,120), System.currentTimeMillis()+"");break;
      case NOT: newOne = new NOT(new Point(140,140), System.currentTimeMillis()+"");break;
    }
	if (newOne != null)
		GateOr.NodeList.add(newOne);
  }
  /**Retrieves nodes from the selected node list to remove all reference of them found in the node linked list and the input lists of all of the node.*/
  static void delete(){
  	disconnect();//Disconnect all reference to it.
    LinkedList<Node> rm = GateOr.getSelectedList();
    for (Node node: rm){
      //Remove all selected nodes in the NodeList
	  for (Node allNode: GateOr.getNodeList()){
		  if (allNode.getInput(0) == node){allNode.clrInput(0);}
		  if (allNode.getInput(1) == node){allNode.clrInput(1);}
		  
	  }
      if(GateOr.getNodeList().remove(node)){//This is just for testing, All items in the selected list should be in the NodeList. Just a test, will be removed in final version
        System.out.println("Item "+node+" was removed");
      }else{
        System.out.println("Item "+node+" was not removed!");
      }
    }
    rm.clear();
  }
  /**Updates the coordinates of the nodes in the selected nodes list based on the user's mouse coordinates*/
  static void move(){
    //This method is called from the MouseHandler if the NodeHandler mode is set to move by the user in the menu.
    for (Node node: GateOr.getSelectedList()){//Go through all the selected nodes
    	node.setLocation(node.getLocation().x + (GateOr.MouseHandler.dragX - GateOr.MouseHandler.X),
    		node.getLocation().y + (GateOr.MouseHandler.dragY - GateOr.MouseHandler.Y));
    	//GateOr.MouseHandler.X = GateOr.MouseHandler.dragX;
	//GateOr.MouseHandler.Y = GateOr.MouseHandler.dragY;
	if (GateOr.getSelectedList().size() < 2){
		GateOr.MouseHandler.X = GateOr.MouseHandler.dragX;
		GateOr.MouseHandler.Y = GateOr.MouseHandler.dragY;
	}
    }
    if (GateOr.getSelectedList().size() > 1){
		GateOr.MouseHandler.X = GateOr.MouseHandler.dragX;
		GateOr.MouseHandler.Y = GateOr.MouseHandler.dragY;
	}
	
  }
  /**Adds or removes the nodes found in the selected node list based on the user mouse input*/
  static void select(int x0, int y0, int x1, int y1, boolean remove){
	LinkedList<Node> allNodes = GateOr.getNodeList();
	LinkedList<Node> chosenOnes = new LinkedList<Node>();
	for (Node node: allNodes){
		if ((new Rectangle(x0, y0, x1 - x0, y1 - y0)).intersects(new Rectangle(node.getLocation().x, node.getLocation().y, node.width, node.height))){
			if (!remove){
				chosenOnes.add(node);
			}else{//Remove the selected
				for (Node selected: GateOr.getSelectedList()){
					if (selected == node){
						GateOr.getSelectedList().remove(node);
					}
				}
			}
			//System.out.println("SELECTED");
		}
		if (x0 == x1 || y0 == y1){
			int nX0 = node.getLocation().x, nY0 = node.getLocation().y, nX1 = node.width, nY1 = node.height;
			if (nY0 > y0 - nY1 && nY0 < y0){
				if (nX0 > x0 - nX1 && nX0 < x0){
					if (!remove){
						chosenOnes.add(node);
					}else{//Remove the selected
						for (Node selected: GateOr.getSelectedList()){
							if (selected == node){
								GateOr.getSelectedList().remove(node);
							}
						}
					}
					//System.out.println("We Got One!!!");
					break;//Only select one.
				}
			}
		}
	}
	if (chosenOnes.size() == 0 && !remove){//If no items where chosen, clear!!
		GateOr.getSelectedList().clear();
	}
	for (Node node: chosenOnes){
		if (!GateOr.getSelectedList().contains(node))
			GateOr.getSelectedList().add(node);
	}
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
			System.out.println("Or the Output needs to be selcted second not first.");
		}
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
