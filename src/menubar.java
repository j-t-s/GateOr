

/*import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;



public class menubar extends JFrame{

	JMenuBar bar;
	JMenu save, export, help, version, close, and, or, 
	xor, nand, not, nor, cog;
	
	
	public static void main(String[] args){
		
		
		JFrame frame = new JFrame("Menu");
		frame.setVisible(true);
		frame.setSize(400,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.addComponentListener(new ComponentListener(){

			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentResized(ComponentEvent sizeChange) {
				System.out.println(sizeChange.paramString());
				String sizeOfWindow = sizeChange.paramString();
				//int width = Integer.parseInt(sizeChange.paramString().substring(24, 28));
				//System.out.println(width);
				int indexClose = sizeOfWindow.indexOf(")");
				int indexX = sizeOfWindow.indexOf("x");
				
				String width = sizeOfWindow.substring(indexX - 4, indexX);
				String height = sizeOfWindow.substring(indexX + 1, indexClose);
				System.out.println("Width: " + width+  "   height:  " + height );
				
				windowWidth = Integer.parseInt(width);
				windowHeight = Integer.parseInt(height);
				
			}

			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			
		});		
		
		JMenuBar bar = new JMenuBar();
		frame.setJMenuBar(bar);
		
		JMenu cog = new JMenu("Cog");
		//cog.setPreferredSize( new Dimension());
	
		bar.add(cog);
		JMenuItem load = new JMenuItem("Load");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem export = new JMenuItem("Export");
		JMenuItem help = new JMenuItem("Help");
		JMenuItem version = new JMenuItem("Version");
		JMenuItem close = new JMenuItem("Close");
		cog.add(load);
		cog.add(save);
		cog.add(export);
		cog.add(help);
		cog.add(version);
		cog.add(close);
		
		
		JButton and = new JButton("AND");
		and.setSize(90,5);
		bar.add(and);
		
		JButton or = new JButton("OR");
		
		bar.add(or);
		JButton xor = new JButton("XOR");
		bar.add(xor);
		JButton nand = new JButton("NAND");
		bar.add(nand);
		JButton not = new JButton("NOT");
		bar.add(not);
		JButton nor = new JButton("NOR");
		bar.add(nor);
		
		

	class loadListener implements ActionListener{

		
		public void actionPerformed(ActionEvent arg0) {
			
			
		}
		
	}
		class saveListener implements ActionListener{

			
			public void actionPerformed(ActionEvent e) {
				
				
			}
		}
			class exportListener implements ActionListener{

				
				public void actionPerformed(ActionEvent e) {
					
					
				}
			}
				class helpListener implements ActionListener{
					
					public void actionPerformed(ActionEvent e) {
												
					        String path = "XmIKyrv.jpg";
					        File file = new File(path);
					        BufferedImage image = null;
							try {
								image = ImageIO.read(file);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
					        JLabel label = new JLabel(new ImageIcon(image));
					        JFrame f = new JFrame();
					        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					        f.getContentPane().add(label);
					        f.pack();
					        f.setLocation(200,200);
					        f.setVisible(true);
					
				}}
					class versionListener implements ActionListener{

						@Override
						public void actionPerformed(ActionEvent e) {
							 String path = "XmIKyrv.jpg";
						        File file = new File(path);
						        BufferedImage image = null;
								try {
									image = ImageIO.read(file);
								} catch (IOException e1) {
									e1.printStackTrace();
								}
						        JLabel label = new JLabel(new ImageIcon(image));
						        JFrame f = new JFrame();
						        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						        f.getContentPane().add(label);
						        f.pack();
						        f.setLocation(200,200);
						        f.setVisible(true);
							
						}
					}
						class closeListener implements ActionListener{

							
							public void actionPerformed(ActionEvent e) {
								System.exit(0);
								
							}	
	}
	
	close.addActionListener(new closeListener());
	help.addActionListener(new helpListener());
	version.addActionListener(new versionListener());
}
}
*/


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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;



public class menubar extends JMenuBar{

	//private JFrame owner; //The window this bar belongs to.
	public JMenuItem load, save, export, help, version, close;
	public JButton and, or, xor, nand, not, nor, line;
	public JMenu cog;
	
	private LinkedList<JButton> buttonList = new LinkedList<JButton>();
	
	private final int maximumHeight = 35;
	
		
	public menubar(int width, int height){
		
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
	

	public class loadListener implements ActionListener{

		
		public void actionPerformed(ActionEvent arg0) {
			
			
		}
		
	}
	public class saveListener implements ActionListener{

			
			public void actionPerformed(ActionEvent e) {
				
				
			}
	}
	public class exportListener implements ActionListener{

				
				public void actionPerformed(ActionEvent e) {
					
					
				}
	}
	public class helpListener implements ActionListener{
					
		public void actionPerformed(ActionEvent e) {
												
			String path = "vgCvhgf.png";
			File file = new File(path);
			BufferedImage image = null;
			try {
				image = ImageIO.read(file);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			JLabel label = new JLabel(new ImageIcon(image));
			JFrame f = new JFrame();
			//f.setLocationRelativeTo(null);
	        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Otherwise it will exit the whole program
	        
			f.getContentPane().add(label);
			f.pack();
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
						        File file = new File(path);
						        BufferedImage image = null;
								try {
									image = ImageIO.read(file);
								} catch (IOException e1) {
									e1.printStackTrace();
								}
						        JLabel label = new JLabel(new ImageIcon(image));
						        JFrame f = new JFrame();
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
