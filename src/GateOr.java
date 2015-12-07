import java.util.LinkedList;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;


public class GateOr{
	static LinkedList<Node> NodeList;
	static LinkedList<Node> SelectedList;
	
	public static void main(String args[]){
		build();
		buildGUI();
		//plus more possibly
	}
	static class MouseHandler implements MouseListener, MouseMotionListener{
		static int X = 0, Y = 0;
		static int dragX = 0, dragY = 0;
		static int endX = 0, endY = 0;
		public void mouseClicked(MouseEvent e){}
		public void mouseEntered(MouseEvent e){}
		public void mouseExited(MouseEvent e){}
		public void mousePressed(MouseEvent e){
			X = e.getX();
			Y = e.getY();
			System.out.println("X: "+X+", Y: "+Y);
		}
		public void mouseReleased(MouseEvent e){
			endX = e.getX();
			endY = e.getY();
			System.out.println("X: "+endX+", Y: "+endY);
		}
		
		public void mouseDragged(MouseEvent e){
			dragX = e.getX();
			dragY = e.getY();
			System.out.println("X: "+e.getX()+", Y: "+e.getY());
		}
		public void mouseMoved(MouseEvent e){}
	}
	/**Build and run the GUI elements, this includes starting the GUI's thread and renderer*/
	static void buildGUI(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch (UnsupportedLookAndFeelException e) {e.printStackTrace();}catch (ClassNotFoundException e) {e.printStackTrace();}catch (InstantiationException e) {e.printStackTrace();}catch (IllegalAccessException e) {e.printStackTrace();}
		
		JFrame frame = new JFrame("GateOr - Logic Gate Simulator");
		frame.setIconImage((new ImageIcon("workingLogo.png")).getImage().getScaledInstance(64,64,  java.awt.Image.SCALE_SMOOTH));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(854,640);
		frame.setVisible(true);
		MouseHandler mouseHdlr = new MouseHandler();
		frame.addMouseListener(mouseHdlr);
		frame.addMouseMotionListener(mouseHdlr);
	}
	/**Will build and initialize all necessary data structures*/
	static void build(){}
	static LinkedList<Node> getNodeList(){return NodeList;}
	static LinkedList<Node> getSelectedList(){return SelectedList;}

}
