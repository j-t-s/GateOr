import java.awt.Point;
import java.awt.Graphics;

public abstract class Node{
  public enum Type {POWER, OUTPUT, AND, OR, XOR, NOR, NAND, NOT};
  public enum State {ON, OFF, UNDEF};
  
  State state = State.UNDEF;
  private Point coordinates = new Point(10, 10);//Upper left corner of the node
  String name = "";
  String imgName = "";
  
  int height = 75, width = 150;
  
  /**sets coordinates*/
  public void setLocation(int x, int y){
  	coordinates.x = x;
  	coordinates.y = y;
  }
  /**gets coordinates*/
  public Point getLocation(){
  	return coordinates;
  }
  /**
	Sets a non-null input to the Node and returns true. If there are no non-null inputs no input is et and returns false
	Gate class use this to set the inputs list.
	Output class use this to set the input reference.
	Power implement this method by always returning false.
  */
  public abstract boolean setInput(Node addedInput);
  
  /**
	Gets the inputs at a certain index.
	Gate class use this to get an input from the inputs list.
	Output class use this to get the input reference.
	Power implement this method by always null.
  */
  public abstract Node getInput(int index);
  /**
	Clear the inputs at a certain index.
	Gate class use this to clear an input from the inputs list.
	Output class use this to clear the input reference.
	Power implement this method by being empty.
  */
  public abstract void clrInput(int index);
  
  public State getState(){return state;}
  protected void setState(State state){this.state = state;}
  public abstract void updateState();
  public abstract void draw(Graphics g);
  
  public Node(State state, Point coord, String name){
  	setState(state);
  	setLocation(coord.x, coord.y);
  	this.name = name;
  }
  public Node(){
  	name = System.currentTimeMillis()+"";//Make the name the time to be unique
  }
  
}
