import java.awt.geom.CubicCurve2D;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.*;

public class Output extends Node{
	Node input = null;
	Image imgON = null;
	Image imgOFF = null;
	Image img = null;
  	public boolean setInput(Node addedInput){
		if(addedInput instanceof Output){
			return false;
		}
		if (input == null){
			input = addedInput;
		}else{
			return false;
		}
		return true;
	}
	public Node getInput(int index){//regardless of index, input is returned
		return input;
	}
	public void clrInput(int index){//regardless of index, input is set to null
		input = null;
	}
	public Output(Point coord, String name){
		super(Node.State.UNDEF, coord, name);
		height = 64;
		width = 64;
		imgName = "OutputOFF.png";
		
		//get the images
		try {
			imgON = ImageIO.read(this.getClass().getClassLoader().getResource("OutputON.png"));
			imgOFF = ImageIO.read(this.getClass().getClassLoader().getResource("OutputOFF.png"));
		}catch (IOException e){
			System.out.println("Could not get Output images");
		}
	}
	@Override
	public void updateState(){//needs implemented
		if (this.getInput(0) == null){
			this.setState(Node.State.UNDEF);
		}else{
			this.setState(this.getInput(0).getState());
		}
	}
	@Override
	public void draw(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		if (imgON == null || imgOFF == null){
			return;//Images were not loaded.
		}
		
		if (getState() == Node.State.ON){//Change the images appearance according to state
			img = imgON;
		}else{
			img = imgOFF;
		}
		//draw the image
		g.drawImage(img.getScaledInstance(width, height, Image.SCALE_SMOOTH), getLocation().x, getLocation().y, null);
		//draw the line
		if (this.getInput(0) != null){
			CubicCurve2D c = new CubicCurve2D.Double();
			c.setCurve(getLocation().x + 10, getLocation().y+height/2,
				getLocation().x - 64, getLocation().y+height/2,
				getInput(0).getLocation().x+getInput(0).width + 64, getInput(0).getLocation().y+getInput(0).height/2,
				getInput(0).getLocation().x+getInput(0).width - 10, getInput(0).getLocation().y+getInput(0).height/2);
			Stroke tmpStroke = g2.getStroke();	
			g2.setStroke(new BasicStroke(3));
			g2.draw(c);
			g2.setStroke(tmpStroke);
		}
	}
}
