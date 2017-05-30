import java.awt.Point;
import java.util.LinkedList;


public class SaveTest {
	static LinkedList<Node> ll = new LinkedList<Node>();
	
	public static void main(String[] args){
		ll.add(new AND(new Point(6,6), "and1"));
		ll.add(new NOT(new Point(6,6), "not1"));
		ll.add(new XOR(new Point(6,6), "xor1"));
		
		ll.add(new Power(Node.State.ON, new Point(12,12), "power1"));
		ll.add(new Power(Node.State.OFF, new Point(12,13), "power2"));
		
		ll.add(new Output(new Point(22,22), "bobTheOutput"));
		
		Tools tool = new Tools(ll);
		tool.save();
	}
}
