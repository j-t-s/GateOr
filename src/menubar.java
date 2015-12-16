import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.*;



public class menubar extends JMenuBar{

	//private JFrame owner; //The window this bar belongs to.
	public JMenuItem load, save, export, help, version, close;
	public JButton and, or, xor, nand, not, nor, line;
	public JMenu cog;
	private JFrame theFrame;
	
	private Tools a = new Tools(GateOr.getNodeList());
	
	private LinkedList<JButton> buttonList = new LinkedList<JButton>();
	
	private final int maximumHeight = 35;
	
		
	public menubar(JFrame frame, int width, int height){
		theFrame = frame;
	}
	
	public void addToButtonList(JButton button){
		buttonList.add(button);
	}
	
	public void resizeMenuBar(int newWidth, int newHeight){
		int count = 1 + buttonList.size(); // 1+ size because we have the cog menu to account for as well
		int individualSize = newWidth / count; //Width of each button/menu
		
		
		setSize((int)getSize().getWidth(), maximumHeight);
		cog.setSize( new Dimension(individualSize, maximumHeight));//Sets the size of the cog menu4
		//cog.repaint();
		
		for( int i = 0; i < buttonList.size(); i ++){
			buttonList.get(i).setSize( new Dimension(individualSize, maximumHeight ));
			buttonList.get(i).setLocation( (i+1)*individualSize, (int)getLocation().getY());
			//buttonList.get(i).repaint();
			
		}
		
		//owner.repaint();
		this.repaint();
		
		
	}
	public class createListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if (e.getActionCommand() == "AND"){NodeHandler.create(Node.Type.AND);}
			else if (e.getActionCommand() == "OR"){NodeHandler.create(Node.Type.OR);}
			else if (e.getActionCommand() == "XOR"){NodeHandler.create(Node.Type.XOR);}
			else if (e.getActionCommand() == "NOR"){NodeHandler.create(Node.Type.NOR);}
			else if (e.getActionCommand() == "NAND"){NodeHandler.create(Node.Type.NAND);}
			else if (e.getActionCommand() == "NOT"){NodeHandler.create(Node.Type.NOT);}
			else if (e.getActionCommand() == "POWER"){NodeHandler.create(Node.Type.POWER);}
			else if (e.getActionCommand() == "OUTPUT"){NodeHandler.create(Node.Type.OUTPUT);}
		}
	}
	
	public class modeListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if (e.getActionCommand() == "Select"){NodeHandler.setMode(NodeHandler.Mode.SELECT);}
			else if (e.getActionCommand() == "Move"){NodeHandler.setMode(NodeHandler.Mode.MOVE);}
			else if (e.getActionCommand() == "Toggle Power"){NodeHandler.setMode(NodeHandler.Mode.TOGGLE_POWER);}
		}
	}
	
	public class handleItemListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if (e.getActionCommand() == "Connect"){NodeHandler.connect();}
			else if (e.getActionCommand() == "Disconnect"){NodeHandler.disconnect();}
			else if (e.getActionCommand() == "Delete"){NodeHandler.delete();}
		}
	}

	public class loadListener implements ActionListener{

		
		public void actionPerformed(ActionEvent arg0) {
			a.load();
		}
		
	}
	public class saveListener implements ActionListener{

			
			public void actionPerformed(ActionEvent e) {
				System.out.println("Saving . . .");
				a.save();
				System.out.println("Saved.");
			}
	}
	public class exportListener implements ActionListener{

				
				public void actionPerformed(ActionEvent e) {
					
					
				}
	}
	public class helpListener implements ActionListener{
					
		public void actionPerformed(ActionEvent e) {
												
			String path = "GateOrHelpWindow.png";
			BufferedImage image = null;
			try {
				image = ImageIO.read(this.getClass().getClassLoader().getResource(path));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			JLabel label = new JLabel(new ImageIcon(image));
			//JFrame f = new JFrame();
			JDialog f = new JDialog(theFrame);
			//f.setLocationRelativeTo(null);
	        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Otherwise it will exit the whole program
	        JScrollPane jsp = new JScrollPane(label);
			f.add(jsp);
			//f.getContentPane().add(label);
			//f.pack();
			f.setSize(new Dimension(640,500));
			GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	        int width = gd.getDisplayMode().getWidth();
	        f.setLocation(width/2-f.getWidth(),0);
			f.setVisible(true);
		}
	}
	public class versionListener implements ActionListener{

						@Override
						public void actionPerformed(ActionEvent e) {
							 String path = "vgCvhgf.png";
						        BufferedImage image = null;
								try {
									image = ImageIO.read(this.getClass().getClassLoader().getResource(path));
								} catch (IOException e1) {
									e1.printStackTrace();
								}
						        JLabel label = new JLabel(new ImageIcon(image));
						        //JFrame f = new JFrame();
								JDialog f = new JDialog(theFrame);
						        f.setLocationRelativeTo(null);
						        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Otherwise it will exit the whole program
						        f.getContentPane().add(label);
						        f.pack();
						        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
						        int width = gd.getDisplayMode().getWidth();
						        int height = gd.getDisplayMode().getHeight();
						        f.setLocation(width/2-f.getWidth(),0);
						        f.setVisible(true);
							
						}
					}
	public class closeListener implements ActionListener{

							
							public void actionPerformed(ActionEvent e) {
								System.exit(0);
								
							}	
	}
}
