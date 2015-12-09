//package gateor.toolbar.test;

//import gateor.toolbar.menubar;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class window extends JFrame{
	private final int minimumWindowHeight = 300;
	private final int minimumWindowWidth = 450;
	public menubar bar;
	
	public window(final String title){
		super(title);
		
		setSize(450,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		bar = new menubar((int)getSize().getWidth(), (int)getSize().getHeight());
		setJMenuBar(bar);
		
		bar.cog = new JMenu("Cog");
		//cog.setPreferredSize( new Dimension());
	
		bar.add(bar.cog);
		bar.load = new JMenuItem("Load");
		bar.save = new JMenuItem("Save");
		bar.export = new JMenuItem("Export");
		bar.help = new JMenuItem("Help");
		bar.version = new JMenuItem("Version");
		bar.close = new JMenuItem("Close");
		bar.cog.add(bar.load);
		bar.cog.add(bar.save);
		bar.cog.add(bar.export);
		bar.cog.add(bar.help);
		bar.cog.add(bar.version);
		bar.cog.add(bar.close);
		
		String andPath = "and2.png";
		File andFile = new File(andPath);
		BufferedImage andImage = null;
		try {
			andImage = ImageIO.read(andFile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		bar.and = new JButton();
		bar.and.setIcon(new ImageIcon(andImage));
		bar.add(bar.and);
		
		String orPath = "OR-Gate.png";
		File orFile = new File(orPath);
		BufferedImage orImage = null;
		try {
			orImage = ImageIO.read(orFile);
			if(orImage == null){
				System.out.println("OR IS NULL!");
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		bar.or = new JButton();
		bar.or.setIcon(new ImageIcon(orImage));
		bar.add(bar.or);
		
		
		String xorPath = "XOR-Gate.png";
		File xorFile = new File(xorPath);
		BufferedImage xorImage = null;
		try {
			xorImage = ImageIO.read(xorFile);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		bar.xor = new JButton();
		bar.xor.setIcon(new ImageIcon(xorImage));
		bar.add(bar.xor);
		
		
		String nandPath = "NAND-Gate.png";
		File nandFile = new File(nandPath);
		BufferedImage nandImage = null;
		try {
			nandImage = ImageIO.read(nandFile);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		bar.nand = new JButton();
		bar.nand.setIcon(new ImageIcon(nandImage));
		bar.add(bar.nand);
		
		
		String notPath = "NOT-Gate.png";
		File notFile = new File(notPath);
		BufferedImage notImage = null;
		try {
			notImage = ImageIO.read(notFile);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		bar.not = new JButton();
		bar.not.setIcon(new ImageIcon(notImage));
		bar.add(bar.not);
		
		String norPath = "NOR-Gate.png";
		File norFile = new File(norPath);
		BufferedImage norImage = null;
		try {
			norImage = ImageIO.read(norFile);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		bar.nor = new JButton();
		bar.nor.setIcon(new ImageIcon(norImage));
		bar.add(bar.nor);
		
		String linePath = "Line.png";
		File lineFile = new File(linePath);
		BufferedImage lineImage = null;
		try {
			lineImage = ImageIO.read(lineFile);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		bar.line = new JButton();
		bar.line.setIcon(new ImageIcon(lineImage));
		bar.add(bar.line);
		
		
		
		
		bar.addToButtonList(bar.and);
		bar.addToButtonList(bar.or);
		bar.addToButtonList(bar.xor);
		bar.addToButtonList(bar.nand);
		bar.addToButtonList(bar.not);
		bar.addToButtonList(bar.nor);
		bar.addToButtonList(bar.line);
		
		bar.resizeMenuBar(this.getWidth(), this.getHeight());
		
		bar.close.addActionListener( bar.new closeListener());
		bar.help.addActionListener(bar.new helpListener());
		bar.version.addActionListener(bar.new versionListener());
		
		addComponentListener( new WindowComponentListener());
		
		
		setVisible(true);
		
	}
	
	
	class WindowComponentListener implements ComponentListener{

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
			//System.out.println(sizeChange.paramString());
			String sizeOfWindow = sizeChange.paramString();
			
			int indexClose = sizeOfWindow.indexOf(")");
			int indexX = sizeOfWindow.indexOf("x");
			
			String width = sizeOfWindow.substring(indexX - 4, indexX);
			String height = sizeOfWindow.substring(indexX + 1, indexClose);
			//System.out.println("Width: " + width+  "   height:  " + height );
			
			int windowWidth = Integer.parseInt(width.trim());
			int windowHeight = Integer.parseInt(height.trim());
			
			if(windowWidth < minimumWindowWidth){
				sizeChange.getComponent().setSize(minimumWindowWidth, windowHeight);
			}
			
			if(windowHeight < minimumWindowHeight){
				sizeChange.getComponent().setSize((int)sizeChange.getComponent().getSize().getWidth(), minimumWindowHeight);
			}
			
			if(bar != null){
				
				bar.resizeMenuBar(windowWidth, windowHeight);
				bar.repaint();//meaningless outside of the EDT
				
			}
			
			
			
			
			
			
		}

		@Override
		public void componentShown(ComponentEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		
	}		
	
	
public static void main(String[] args){
		
		
		window frame = new window("Menu");		
	
	}	
}
