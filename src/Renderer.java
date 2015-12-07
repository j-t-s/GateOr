import java.util.LinkedList;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Color;

public class Renderer extends JPanel implements ActionListener{
	private Dimension windowSize;
	private LinkedList<Node> nodeList;
	
	public Renderer(LinkedList<Node> nlist){
		this.nodeList = nlist;
	}
	
	public void actionPerformed(ActionEvent e){
		this.repaint();
	}
	
	public Dimension getPreferredSize(){
		return new Dimension(854,640);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for (int i = 0; i < nodeList.size(); i++){
			nodeList.get(i).draw(g);
		}
		for (int i = 0; i < GateOr.getSelectedList().size(); i++){
			Node node = GateOr.getSelectedList().get(i);
			g.setColor(Color.BLACK);
			g.drawString("Selected", node.getLocation().x, node.getLocation().y);
		}	
	}
}
