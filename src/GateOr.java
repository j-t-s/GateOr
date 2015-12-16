import java.util.LinkedList;

import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;




public class GateOr{
	static LinkedList<Node> NodeList = new LinkedList<Node>();
	static LinkedList<Node> SelectedList = new LinkedList<Node>();
	
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
			NodeHandler.select(X,Y,X,Y, e.isShiftDown());
			switch(NodeHandler.getMode()){
				//case Move: NodeHandler.move();break;

				case TOGGLE_POWER: {
					GateOr.getSelectedList().clear();//Clear selected
					NodeHandler.select(X,Y,X,Y, e.isShiftDown());//Reselect one to be toggled
					NodeHandler.togglePower();//Toggle
					break;
				}
			}
			//System.out.println("X: "+X+", Y: "+Y);
		}
		public void mouseReleased(MouseEvent e){
			endX = e.getX();
			endY = e.getY();
			switch(NodeHandler.getMode()){
				//case Move: NodeHandler.move();break;
				case SELECT: NodeHandler.select(X,Y,endX,endY, e.isShiftDown());break;
			}
			//System.out.println("X: "+endX+", Y: "+endY);
			X = Y = dragX = dragY = endX = endY = 0;//Reset everything
		}
		
		public void mouseDragged(MouseEvent e){
			dragX = e.getX();
			dragY = e.getY();
			/*switch(NodeHandler.getMode()){
				case MOVE: NodeHandler.move();break;
			}*/
			NodeHandler.move();
			//System.out.println("Drag X: "+e.getX()+", Y: "+e.getY());
		}
		public void mouseMoved(MouseEvent e){}
	}
	static class KeyHandler implements KeyListener{
		public void keyPressed(KeyEvent e){
			if (e.getKeyCode() == e.VK_M){
				NodeHandler.setMode(NodeHandler.Mode.MOVE);
			}else if (e.getKeyCode() == e.VK_S){
				NodeHandler.setMode(NodeHandler.Mode.SELECT);
			}else if (e.getKeyCode() == e.VK_T){
				//NodeHandler.setMode(NodeHandler.Mode.TOGGLE_POWER);
				NodeHandler.togglePower();
			}else if (e.getKeyCode() == e.VK_DELETE){
				NodeHandler.delete();
			}else if (e.getKeyCode() == e.VK_C){
				NodeHandler.connect();
			}else if (e.getKeyCode() == e.VK_D){
				NodeHandler.disconnect();
			}
		}
		public void keyReleased(KeyEvent e){}
		public void keyTyped(KeyEvent e){}
	}
	/**Build and run the GUI elements, this includes starting the GUI's thread and renderer*/
	static void buildGUI(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch (UnsupportedLookAndFeelException e) {e.printStackTrace();}catch (ClassNotFoundException e) {e.printStackTrace();}catch (InstantiationException e) {e.printStackTrace();}catch (IllegalAccessException e) {e.printStackTrace();}
			
		
		
		JFrame frame = new JFrame("GateOr - Logic Gate Simulator");
		frame.setIconImage((new ImageIcon(GateOr.class.getResource("workingLogo.png"))).getImage().getScaledInstance(64,64,  java.awt.Image.SCALE_SMOOTH));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(775,320));
        frame.setSize(854,640);
		//Mouse Handler
		MouseHandler mouseHdlr = new MouseHandler();

		//Renderer
		Renderer rend = new Renderer(NodeList);
		rend.setFocusable(true);
        rend.requestFocusInWindow();
		rend.addKeyListener(new KeyHandler());
		        	
		rend.addMouseListener(mouseHdlr);
		rend.addMouseMotionListener(mouseHdlr);
		frame.add(rend);
		(new Timer(33, rend)).start();
		
		//Menu Bar
		menubar bar = new menubar(frame, 854, 640);
		
		//bar.cog = new JMenu("Cog");
		bar.cog = new JMenu();
		bar.cog.setIcon(new ImageIcon((new ImageIcon(GateOr.class.getResource("cog.png"))).getImage().getScaledInstance(56, 32,  java.awt.Image.SCALE_SMOOTH)));
		bar.add(bar.cog);
		bar.load = new JMenuItem("Load");
		bar.save = new JMenuItem("Save");
		bar.export = new JMenuItem("Export");
		
		JMenu mode = new JMenu("Mode");
		JMenuItem select = new JMenuItem("Select");
		//JMenuItem move = new JMenuItem("Move");//Move is no longer needed since now if the mouse is dragged, the selected is moved
		JMenuItem toggle = new JMenuItem("Toggle Power");
		mode.add(select);
		//mode.add(move);
		mode.add(toggle);
		
		select.addActionListener(bar.new modeListener());
		//move.addActionListener(bar.new modeListener());
		toggle.addActionListener(bar.new modeListener());
		
		JMenu handleItems = new JMenu("Handle Items");
		JMenuItem connect = new JMenuItem("Connect");
		JMenuItem disconnect = new JMenuItem("Disconnect");
		JMenuItem delete = new JMenuItem("Delete");
		handleItems.add(connect);
		handleItems.add(disconnect);
		handleItems.add(delete);
		
		connect.addActionListener(bar.new handleItemListener());
		disconnect.addActionListener(bar.new handleItemListener());
		delete.addActionListener(bar.new handleItemListener());
		
		bar.help = new JMenuItem("Help");
		bar.version = new JMenuItem("Version");
		bar.close = new JMenuItem("Close");
		bar.cog.add(bar.load);
		bar.cog.add(bar.save);
		bar.cog.add(bar.export);
		bar.cog.add(mode);
		bar.cog.add(handleItems);
		bar.cog.add(bar.help);
		bar.cog.add(bar.version);
		bar.cog.add(bar.close);
		
		
		int gateButtonX = 64, gateButtonY = 32;
		int powerButtonX = 32, powerButtonY = 32;
		int outputButtonX = 32, outputButtonY = 32;
		bar.and = new JButton();
		//TODO the images will be inside of the jar and will need to be accessed as a reference 
		bar.and.setIcon(new ImageIcon((new ImageIcon(GateOr.class.getResource("AND.png"))).getImage().getScaledInstance(gateButtonX, gateButtonY,  java.awt.Image.SCALE_SMOOTH)));
		bar.and.setActionCommand("AND");
		bar.and.addActionListener(bar.new createListener());
		bar.add(bar.and);
		
		bar.or = new JButton();
		//TODO the images will be inside of the jar and will need to be accessed as a reference 
		bar.or.setIcon(new ImageIcon((new ImageIcon(GateOr.class.getResource("OR.png"))).getImage().getScaledInstance(gateButtonX, gateButtonY,  java.awt.Image.SCALE_SMOOTH)));
		bar.or.setActionCommand("OR");
		bar.or.addActionListener(bar.new createListener());
		bar.add(bar.or);
		
		bar.xor = new JButton();
		//TODO the images will be inside of the jar and will need to be accessed as a reference 
		bar.xor.setIcon(new ImageIcon((new ImageIcon(GateOr.class.getResource("XOR.png"))).getImage().getScaledInstance(gateButtonX, gateButtonY,  java.awt.Image.SCALE_SMOOTH)));
		bar.xor.setActionCommand("XOR");
		bar.xor.addActionListener(bar.new createListener());
		bar.add(bar.xor);
		
		bar.nor = new JButton();
		//TODO the images will be inside of the jar and will need to be accessed as a reference 
		bar.nor.setIcon(new ImageIcon((new ImageIcon(GateOr.class.getResource("NOR.png"))).getImage().getScaledInstance(gateButtonX, gateButtonY,  java.awt.Image.SCALE_SMOOTH)));
		bar.nor.setActionCommand("NOR");
		bar.nor.addActionListener(bar.new createListener());
		bar.add(bar.nor);
		
		bar.nand = new JButton();
		//TODO the images will be inside of the jar and will need to be accessed as a reference 
		bar.nand.setIcon(new ImageIcon((new ImageIcon(GateOr.class.getResource("NAND.png"))).getImage().getScaledInstance(gateButtonX, gateButtonY,  java.awt.Image.SCALE_SMOOTH)));
		bar.nand.setActionCommand("NAND");
		bar.nand.addActionListener(bar.new createListener());
		bar.add(bar.nand);
		
		bar.not = new JButton();
		//TODO the images will be inside of the jar and will need to be accessed as a reference 
		bar.not.setIcon(new ImageIcon((new ImageIcon(GateOr.class.getResource("NOT.png"))).getImage().getScaledInstance(gateButtonX, gateButtonY,  java.awt.Image.SCALE_SMOOTH)));
		bar.not.setActionCommand("NOT");
		bar.not.addActionListener(bar.new createListener());
		bar.add(bar.not);
		
		JButton power = new JButton();
		//TODO the images will be inside of the jar and will need to be accessed as a reference 
		power.setIcon(new ImageIcon((new ImageIcon(GateOr.class.getResource("powerOff.png"))).getImage().getScaledInstance(powerButtonX, powerButtonY,  java.awt.Image.SCALE_SMOOTH)));
		power.setActionCommand("POWER");
		power.addActionListener(bar.new createListener());
		bar.add(power);
		
		JButton output = new JButton();
		//TODO the images will be inside of the jar and will need to be accessed as a reference 
		output.setIcon(new ImageIcon((new ImageIcon(GateOr.class.getResource("OutputOFF.png"))).getImage().getScaledInstance(powerButtonX, powerButtonY,  java.awt.Image.SCALE_SMOOTH)));
		output.setActionCommand("OUTPUT");
		output.addActionListener(bar.new createListener());
		bar.add(output);
		
		bar.addToButtonList(bar.and);
		bar.addToButtonList(bar.or);
		bar.addToButtonList(bar.xor);
		bar.addToButtonList(bar.nand);
		bar.addToButtonList(bar.not);
		bar.addToButtonList(bar.nor);
		bar.addToButtonList(bar.line);
		
		//bar.resizeMenuBar(this.getWidth(), this.getHeight());
		
		bar.load.addActionListener(bar.new loadListener());
		bar.save.addActionListener(bar.new saveListener());
		bar.close.addActionListener(bar.new closeListener());
		bar.help.addActionListener(bar.new helpListener());
		bar.version.addActionListener(bar.new versionListener());
		
		
		frame.setJMenuBar(bar);
		
		ActionListener simulate = new ActionListener() {
			public void actionPerformed(ActionEvent e){
				for (Node node: GateOr.getNodeList()){
					node.updateState();
				}
			}
		};
		(new Timer(3, simulate)).start();
		
		//try{Thread.sleep(800);}catch(Exception e){}//This is a pause for the splash screen if needed.
		frame.setVisible(true);
		
	}
	/**Will build and initialize all necessary data structures*/
	static void build(){
//		NodeHandler.create(Node.Type.AND);
//		NodeHandler.create(Node.Type.AND);
//		NodeList.get(0).setLocation(150,75);
		//SelectedList.add(NodeList.get(0));//make all selected;

		
	}
	static LinkedList<Node> getNodeList(){return NodeList;}
	static LinkedList<Node> getSelectedList(){return SelectedList;}
}
